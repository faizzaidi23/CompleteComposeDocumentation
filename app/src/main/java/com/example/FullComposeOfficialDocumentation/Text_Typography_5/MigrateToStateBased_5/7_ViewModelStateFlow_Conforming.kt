package com.example.FullComposeOfficialDocumentation.Text_Typography_5.MigrateToStateBased_5

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 ViewModel StateFlow - Conforming Approach

 When you CAN'T change your ViewModel architecture:
 - Existing codebase too large
 - Team doesn't want to refactor
 - Time constraints
 - Still want to use state-based TextFields

 Solution: Hybrid approach
 - Keep StateFlow<UiState> in ViewModel
 - Use TextFieldState in composable
 - Sync TextFieldState changes back to ViewModel
 - ViewModel becomes data holder, not source of truth for TextField
*/

/*
 VALUE-BASED - Keep as-is (Current approach)
*/
class ConformingLoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConformingUiState())
    val uiState: StateFlow<ConformingUiState>
        get() = _uiState.asStateFlow()

    fun updateUsername(username: String) = _uiState.update {
        it.copy(username = username)
    }

    fun updatePassword(password: String) = _uiState.update {
        it.copy(password = password)
    }
}

data class ConformingUiState(
    val username: String = "",
    val password: String = ""
)

/*
 STATE-BASED - Conforming approach (Future)

 This approach:
 1. Keeps ViewModel and UiState unchanged
 2. Creates TextFieldState in composable
 3. Initializes TextFieldState from UiState
 4. Observes TextFieldState changes with snapshotFlow
 5. Updates ViewModel when TextFieldState changes

 Example (requires newer Compose version):

 @Composable
 fun ConformingLoginForm(
     loginViewModel: ConformingLoginViewModel,
     modifier: Modifier = Modifier
 ) {
     // Get initial values from ViewModel once
     val initialUiState = remember(loginViewModel) {
         loginViewModel.uiState.value
     }

     Column(modifier) {
         // Username field
         val usernameState = rememberTextFieldState(initialUiState.username)

         // Observe changes to usernameState.text
         // Update ViewModel when it changes
         LaunchedEffect(usernameState) {
             snapshotFlow { usernameState.text.toString() }
                 .collectLatest { loginViewModel.updateUsername(it) }
         }

         TextField(usernameState)

         // Password field
         val passwordState = rememberTextFieldState(initialUiState.password)

         LaunchedEffect(passwordState) {
             snapshotFlow { passwordState.text.toString() }
                 .collectLatest { loginViewModel.updatePassword(it) }
         }

         SecureTextField(passwordState)
     }
 }

 How it works:

 1. remember(loginViewModel) { loginViewModel.uiState.value }
    - Gets initial value ONCE when composable is first created
    - Only used for initialization
    - Doesn't react to future changes

 2. rememberTextFieldState(initialUiState.username)
    - Creates TextFieldState with initial value
    - This is now the source of truth for the TextField

 3. snapshotFlow { usernameState.text.toString() }
    - Observes changes to TextFieldState.text
    - snapshotFlow tracks Snapshot state changes
    - Emits whenever text changes

 4. collectLatest { loginViewModel.updateUsername(it) }
    - Receives each text change
    - Updates ViewModel
    - ViewModel still has latest values

 5. TextField(usernameState)
    - Driven by TextFieldState, not by ViewModel
    - No synchronization issues
    - Immediate, predictable updates

 Key insight:
 - TextFieldState is source of truth for UI
 - ViewModel is just a data holder
 - One-way sync: TextFieldState → ViewModel
 - NOT: ViewModel → TextFieldState (which causes issues)
*/

/*
 Current implementation (value-based)
*/
@Composable
fun ConformingLoginFormCurrent(
    loginViewModel: ConformingLoginViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by loginViewModel.uiState.collectAsState()

    Column(modifier) {
        TextField(
            value = uiState.username,
            onValueChange = { loginViewModel.updateUsername(it) }
        )

        TextField(
            value = uiState.password,
            onValueChange = { loginViewModel.updatePassword(it) }
        )
    }
}

/*
 Why snapshotFlow?

 snapshotFlow is a special Kotlin Flow that:
 - Integrates with Compose's Snapshot system
 - Observes changes to Snapshot state (like State, TextFieldState)
 - Emits whenever the observed state changes
 - Efficient - only emits when value actually changes

 Alternative approaches DON'T work:

 ❌ Direct observation:
 LaunchedEffect(usernameState.text) {
     // This won't recompose when text changes
     // text is not a State<String>, it's CharSequence
 }

 ✅ snapshotFlow:
 LaunchedEffect(usernameState) {
     snapshotFlow { usernameState.text.toString() }.collectLatest {
         // This works! Emits on every text change
     }
 }
*/

/*
 Comparison of approaches:

 ┌─────────────────────────┬──────────────────┬─────────────────────┐
 │ Aspect                  │ Recommended      │ Conforming          │
 ├─────────────────────────┼──────────────────┼─────────────────────┤
 │ ViewModel change        │ Yes (remove      │ No (keep as-is)     │
 │                         │ StateFlow fields)│                     │
 ├─────────────────────────┼──────────────────┼─────────────────────┤
 │ Source of truth         │ TextFieldState   │ TextFieldState      │
 │                         │ in ViewModel     │ in Composable       │
 ├─────────────────────────┼──────────────────┼─────────────────────┤
 │ Sync complexity         │ None             │ snapshotFlow        │
 ├─────────────────────────┼──────────────────┼─────────────────────┤
 │ Code simplicity         │ Simpler          │ More boilerplate    │
 ├─────────────────────────┼──────────────────┼─────────────────────┤
 │ Migration effort        │ Medium           │ Low                 │
 ├─────────────────────────┼──────────────────┼─────────────────────┤
 │ Best for                │ New code         │ Legacy codebases    │
 │                         │ Refactoring      │ Quick migration     │
 └─────────────────────────┴──────────────────┴─────────────────────┘

 Choose Recommended when:
 - Starting new project
 - Can refactor ViewModel
 - Want cleanest architecture

 Choose Conforming when:
 - Large existing codebase
 - Can't change ViewModel
 - Need quick migration
 - Multiple teams/dependencies
*/

