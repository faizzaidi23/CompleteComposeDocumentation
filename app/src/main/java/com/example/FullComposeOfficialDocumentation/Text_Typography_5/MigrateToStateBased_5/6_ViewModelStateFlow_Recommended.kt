package com.example.FullComposeOfficialDocumentation.Text_Typography_5.MigrateToStateBased_5

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 ViewModel StateFlow Architecture - Migration

 Common architecture pattern:
 - ViewModel holds MutableStateFlow<UiState>
 - UiState is immutable data class
 - UI observes and updates through ViewModel

 Problem with value-based TextField:
 - Deep synchronization issues
 - onValueChange creates async updates
 - StateFlow emissions can conflict with user input
 - Unpredictable behavior

 State-based TextField incompatibility:
 - TextFieldState is mutable, doesn't fit in immutable UiState
 - But this is actually OK!

 TextFieldState is a state holder (like MutableState, StateFlow)
 It's a data structure, not a UI component
 Having it in ViewModel is NOT bad practice
*/

/*
 VALUE-BASED - Traditional ViewModel approach (Current)

 This is the common pattern but has synchronization issues
*/
class OldLoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    fun updateUsername(username: String) = _uiState.update {
        it.copy(username = username)
    }

    fun updatePassword(password: String) = _uiState.update {
        it.copy(password = password)
    }
}

data class UiState(
    val username: String = "",
    val password: String = ""
)

@Composable
fun OldLoginForm(
    loginViewModel: OldLoginViewModel,
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
            onValueChange = { loginViewModel.updatePassword(it) },
            visualTransformation = PasswordVisualTransformation()
        )
    }
}

/*
 Problems with this approach:

 1. User types "a"
 2. onValueChange called with "a"
 3. ViewModel updates StateFlow
 4. StateFlow emits new UiState
 5. Composable recomposes
 6. TextField receives new value

 If user types fast or StateFlow has delays:
 - Text can appear out of order
 - Cursor jumps
 - Characters lost
 - Unpredictable behavior

 This is explained in detail in:
 "Effective state management for TextField in Compose" blog post
*/

/*
 RECOMMENDED APPROACH - Extract TextFieldState to ViewModel

 Example (requires newer Compose version):

 class NewLoginViewModel : ViewModel() {
     val usernameState = TextFieldState()
     val passwordState = TextFieldState()

     // Can still access values when needed:
     fun login() {
         val username = usernameState.text.toString()
         val password = passwordState.text.toString()
         // Perform login
     }
 }

 @Composable
 fun NewLoginForm(
     loginViewModel: NewLoginViewModel,
     modifier: Modifier = Modifier
 ) {
     Column(modifier) {
         TextField(state = loginViewModel.usernameState)
         SecureTextField(state = loginViewModel.passwordState)
     }
 }

 Benefits:
 - No StateFlow synchronization issues
 - TextFieldState is the source of truth
 - Direct, predictable updates
 - Simpler code
 - Better performance

 Note about "No UI dependencies in ViewModel":
 - TextFieldState is a data structure, not a UI component
 - Like MutableState or StateFlow - it's a state holder
 - Backed by Compose's Snapshot system
 - Perfectly fine to use in ViewModel
 - Re-evaluate the principle - it's about UI components, not state holders
*/

