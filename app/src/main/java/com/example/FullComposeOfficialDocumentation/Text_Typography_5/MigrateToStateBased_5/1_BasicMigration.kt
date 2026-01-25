package com.example.FullComposeOfficialDocumentation.Text_Typography_5.MigrateToStateBased_5

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/*
 Basic Migration Example

 This file shows the difference between value-based and state-based TextFields

 Value-based (OLD - current implementation):
 - Uses String value with onValueChange callback
 - State managed with remember/rememberSaveable
 - You manually handle state updates

 State-based (NEW - requires newer Compose version):
 - Uses TextFieldState object
 - State encapsulated in TextFieldState
 - No onValueChange callback needed
 - Better synchronization with keyboard

 Note: State-based TextField requires Compose Foundation 1.7.0+ and Material3 1.3.0+
 Your project currently uses value-based TextField
*/

/*
 VALUE-BASED TextField (Current approach)

 This is what we're using now in this project
*/
@Composable
fun OldSimpleTextField() {
    var state by rememberSaveable { mutableStateOf("") }

    TextField(
        value = state,
        onValueChange = { state = it },
        singleLine = true
    )
}

/*
 STATE-BASED TextField (Future approach)

 This is what it would look like after migration

 Changes:
 1. Replace remember { mutableStateOf("") } with rememberTextFieldState()
 2. Replace value + onValueChange with state parameter
 3. Replace singleLine = true with lineLimits = TextFieldLineLimits.SingleLine

 Benefits:
 - No async issues from onValueChange
 - State survives recomposition and process death automatically
 - Better keyboard synchronization
 - More predictable behavior

 Example (requires newer Compose version):

 @Composable
 fun NewSimpleTextField() {
     val state = rememberTextFieldState()

     TextField(
         state = state,
         lineLimits = TextFieldLineLimits.SingleLine
     )
 }
*/

