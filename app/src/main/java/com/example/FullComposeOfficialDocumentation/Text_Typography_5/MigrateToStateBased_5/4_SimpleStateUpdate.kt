package com.example.FullComposeOfficialDocumentation.Text_Typography_5.MigrateToStateBased_5

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/*
 Updating State - Simple Migration

 Scenario: Update TextField text programmatically (e.g., from network/database)

 Old approach: Direct assignment to state variable
 New approach: Use TextFieldState.setTextAndPlaceCursorAtEnd()

 Use cases:
 - Load user data from repository
 - Auto-fill forms
 - Reset form fields
 - Apply suggestions
*/

/*
 Mock repository for example
*/
class UserRepository {
    suspend fun fetchUsername(): String {
        // Simulate network delay
        kotlinx.coroutines.delay(1000)
        return "john_doe"
    }
}

/*
 VALUE-BASED - Update from repository (Current approach)

 Uses LaunchedEffect to fetch data
 Directly assigns to state variable
*/
@Composable
fun OldTextFieldStateUpdate(userRepository: UserRepository) {
    var username by remember { mutableStateOf("") }

    // Fetch username when composable first appears
    LaunchedEffect(Unit) {
        username = userRepository.fetchUsername()
    }

    TextField(
        value = username,
        onValueChange = { username = it }
    )
}

/*
 STATE-BASED - Update from repository (Future approach)

 Migration steps:
 1. Replace remember { mutableStateOf("") } with rememberTextFieldState()
 2. Replace direct assignment with textFieldState.setTextAndPlaceCursorAtEnd()

 Example (requires newer Compose version):

 @Composable
 fun NewTextFieldStateUpdate(userRepository: UserRepository) {
     val usernameState = rememberTextFieldState()

     LaunchedEffect(Unit) {
         val fetchedUsername = userRepository.fetchUsername()
         // Use setTextAndPlaceCursorAtEnd instead of direct assignment
         usernameState.setTextAndPlaceCursorAtEnd(fetchedUsername)
     }

     TextField(state = usernameState)
 }

 Benefits:
 - setTextAndPlaceCursorAtEnd is explicit about cursor position
 - More predictable behavior
 - Clearer intent in code
*/

/*
 Other state update methods (Future API)

 TextFieldState provides several methods:

 1. setTextAndPlaceCursorAtEnd(text)
    - Replaces all text
    - Places cursor at end
    - Good for loading data

 2. edit { ... }
    - For complex edits
    - Access to TextFieldBuffer
    - Can insert, delete, replace, select

 3. clearText()
    - Removes all text
    - Resets cursor to position 0
    - Good for reset buttons

 Examples:

 // Clear the field
 textFieldState.clearText()

 // Insert at specific position
 textFieldState.edit {
     insert(5, "inserted text")
 }

 // Replace range
 textFieldState.edit {
     replace(0, 5, "new text")
 }

 // Select all
 textFieldState.edit {
     selectAll()
 }
*/

/*
 More examples (value-based, current approach)
*/

@Composable
fun LoadDataExample() {
    var email by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // Simulate loading from database
        kotlinx.coroutines.delay(500)
        email = "user@example.com"
    }

    TextField(
        value = email,
        onValueChange = { email = it }
    )
}

@Composable
fun ResetButtonExample() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it }
    )

    androidx.compose.material3.Button(
        onClick = { text = "" }
    ) {
        androidx.compose.material3.Text("Reset")
    }
}

/*
 STATE-BASED equivalents (Future approach)

 Load data:
 @Composable
 fun NewLoadDataExample() {
     val emailState = rememberTextFieldState()

     LaunchedEffect(Unit) {
         kotlinx.coroutines.delay(500)
         emailState.setTextAndPlaceCursorAtEnd("user@example.com")
     }

     TextField(state = emailState)
 }

 Reset button:
 @Composable
 fun NewResetButtonExample() {
     val textState = rememberTextFieldState()

     TextField(state = textState)

     Button(onClick = { textState.clearText() }) {
         Text("Reset")
     }
 }
*/

