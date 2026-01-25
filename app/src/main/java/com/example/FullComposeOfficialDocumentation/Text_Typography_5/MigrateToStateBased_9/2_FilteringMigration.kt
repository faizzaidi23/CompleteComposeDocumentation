package com.example.FullComposeOfficialDocumentation.Text_Typography_5.MigrateToStateBased_9

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/*
 Filtering Input - Migration Example

 Old approach: Filter in onValueChange callback
 New approach: Use InputTransformation

 Problem with old approach:
 - onValueChange creates async behavior
 - Can cause synchronization issues with keyboard
 - State update happens after user input

 Benefits of new approach:
 - InputTransformation is called immediately after user input
 - Changes applied synchronously
 - No sync issues with software keyboard
 - More predictable behavior
*/

/*
 VALUE-BASED - Filter leading zeros (Current approach)

 Filters out leading zeros as user types
 Uses onValueChange callback to manipulate input
*/
@Composable
fun OldNoLeadingZeroes() {
    var input by rememberSaveable { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = { newText ->
            // Filter logic in onValueChange
            input = newText.trimStart { it == '0' }
        }
    )
}

/*
 STATE-BASED - Filter leading zeros (Future approach)

 Migration steps:
 1. Replace value/onValueChange with rememberTextFieldState()
 2. Move filtering logic to InputTransformation
 3. Use TextFieldBuffer to modify state

 Example (requires newer Compose version):

 @Composable
 fun NewNoLeadingZeroes() {
     val state = rememberTextFieldState()

     TextField(
         state = state,
         inputTransformation = {
             // Use TextFieldBuffer from receiver scope
             // Filter out leading zeros
             if (asCharSequence().firstOrNull() == '0') {
                 delete(0, 1)
             }
         }
     )
 }

 Key differences:
 - InputTransformation gets TextFieldBuffer in receiver scope
 - Can directly modify buffer (delete, insert, replace)
 - Changes happen immediately, no async behavior
 - Better for complex filtering logic
*/

/*
 More filtering examples (value-based, current approach)
*/

@Composable
fun FilterDigitsOnly() {
    var input by rememberSaveable { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = { newText ->
            // Only accept digits
            if (newText.all { it.isDigit() }) {
                input = newText
            }
        }
    )
}

@Composable
fun FilterMaxLength() {
    var input by rememberSaveable { mutableStateOf("") }
    val maxLength = 10

    TextField(
        value = input,
        onValueChange = { newText ->
            // Limit to max length
            if (newText.length <= maxLength) {
                input = newText
            }
        }
    )
}

/*
 STATE-BASED equivalents (Future approach)

 For FilterDigitsOnly:

 @Composable
 fun NewFilterDigitsOnly() {
     val state = rememberTextFieldState()

     TextField(
         state = state,
         inputTransformation = {
             // Revert changes if non-digit found
             if (!asCharSequence().all { it.isDigit() }) {
                 revertAllChanges()
             }
         }
     )
 }

 For FilterMaxLength:

 @Composable
 fun NewFilterMaxLength() {
     val state = rememberTextFieldState()

     TextField(
         state = state,
         inputTransformation = InputTransformation.maxLength(10)
     )
 }

 Built-in InputTransformation.maxLength() is provided
*/

