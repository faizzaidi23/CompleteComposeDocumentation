package com.example.FullComposeOfficialDocumentation.Text_Typography_5.MigrateToStateBased_9

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/*
 Credit Card Formatter - Migration Example

 Old approach: VisualTransformation
 - Manually format text with dashes
 - Manually calculate offset mapping (complex!)
 - Maps cursor position between original and visual text

 New approach: InputTransformation + OutputTransformation
 - InputTransformation: Limit to 16 digits
 - OutputTransformation: Add dashes (no offset mapping needed!)
 - Offset mapping calculated automatically
*/

/*
 VALUE-BASED - Credit Card Formatter (Current approach)

 Formats as: XXXX-XXXX-XXXX-XXXX

 Complexity:
 - Must implement offsetMapping manually
 - originalToTransformed: maps original index → visual index
 - transformedToOriginal: maps visual index → original index
 - Easy to make mistakes in calculations
*/
@Composable
fun OldTextFieldCreditCardFormatter() {
    var state by remember { mutableStateOf("") }

    TextField(
        value = state,
        onValueChange = {
            // Filter: only allow up to 16 digits
            if (it.length <= 16) state = it
        },
        visualTransformation = VisualTransformation { text ->
            // Making XXXX-XXXX-XXXX-XXXX string
            var out = ""
            for (i in text.indices) {
                out += text[i]
                if (i % 4 == 3 && i != 15) out += "-"
            }

            /*
             OffsetMapping is complex!
             Must manually calculate position mappings

             Example: User types "1234567890123456"
             Visual shows: "1234-5678-9012-3456"

             If cursor is at position 5 in original (between 5 and 6):
             Visual position should be 6 (after the dash)

             originalToTransformed(5) = 6
             transformedToOriginal(6) = 5
            */
            TransformedText(
                text = AnnotatedString(out),
                offsetMapping = object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        if (offset <= 3) return offset
                        if (offset <= 7) return offset + 1
                        if (offset <= 11) return offset + 2
                        if (offset <= 16) return offset + 3
                        return 19
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        if (offset <= 4) return offset
                        if (offset <= 9) return offset - 1
                        if (offset <= 14) return offset - 2
                        if (offset <= 19) return offset - 3
                        return 16
                    }
                }
            )
        }
    )
}

/*
 STATE-BASED - Credit Card Formatter (Future approach)

 Much simpler! No manual offset mapping needed.

 Migration steps:
 1. Replace value/onValueChange with rememberTextFieldState()
 2. Replace onValueChange filtering with InputTransformation
 3. Replace VisualTransformation with OutputTransformation
 4. No need to calculate offset mapping!

 Example (requires newer Compose version):

 @Composable
 fun NewTextFieldCreditCardFormatter() {
     val state = rememberTextFieldState()

     TextField(
         state = state,
         // InputTransformation: Limit to 16 characters
         inputTransformation = InputTransformation.maxLength(16),
         // OutputTransformation: Add dashes
         outputTransformation = {
             // Use TextFieldBuffer from receiver scope
             // Just insert dashes at the right positions
             if (length > 4) insert(4, "-")
             if (length > 9) insert(9, "-")
             if (length > 14) insert(14, "-")
             // Offset mapping calculated automatically!
         }
     )
 }

 Benefits:
 - No manual offset calculation
 - Simpler, less error-prone code
 - Separation of concerns:
   - InputTransformation: What goes into state
   - OutputTransformation: How it's displayed
*/

/*
 Phone Number Formatter Example (value-based, current)
*/
@Composable
fun OldPhoneNumberFormatter() {
    var phone by remember { mutableStateOf("") }

    TextField(
        value = phone,
        onValueChange = { if (it.length <= 10) phone = it },
        visualTransformation = VisualTransformation { text ->
            var out = ""
            for (i in text.indices) {
                when (i) {
                    0 -> out += "("
                    3 -> out += ") "
                    6 -> out += "-"
                }
                out += text[i]
            }

            TransformedText(
                text = AnnotatedString(out),
                offsetMapping = object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        return when {
                            offset <= 0 -> offset
                            offset <= 3 -> offset + 1
                            offset <= 6 -> offset + 3
                            offset <= 10 -> offset + 4
                            else -> 14
                        }
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        return when {
                            offset <= 1 -> 0
                            offset <= 5 -> offset - 1
                            offset <= 9 -> offset - 3
                            offset <= 14 -> offset - 4
                            else -> 10
                        }
                    }
                }
            )
        }
    )
}

/*
 STATE-BASED Phone Number (Future approach)

 @Composable
 fun NewPhoneNumberFormatter() {
     val state = rememberTextFieldState()

     TextField(
         state = state,
         inputTransformation = InputTransformation.maxLength(10),
         outputTransformation = {
             if (length > 0) insert(0, "(")
             if (length > 4) insert(4, ")")
             if (length > 8) insert(8, "-")
         }
     )
 }

 Much cleaner and easier to maintain!
*/

