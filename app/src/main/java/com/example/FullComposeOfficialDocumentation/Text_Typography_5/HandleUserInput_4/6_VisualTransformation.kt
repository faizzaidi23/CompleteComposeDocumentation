package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/*
 VisualTransformation - Transform how text is displayed

 Changes the visual representation WITHOUT changing the actual value
 The underlying state remains unchanged

 Common use cases:
 - Password masking (show bullets instead of characters)
 - Phone number formatting (123) 456-7890
 - Credit card formatting (1234 5678 9012 3456)
 - Currency formatting ($1,234.56)

 Key difference from value transformation:
 - VisualTransformation: Only affects display
 - Value transformation: Changes the actual state
*/

/*
 Password masking

 PasswordVisualTransformation is built-in
 Shows bullet (•) or asterisk (*) instead of actual characters
 Actual password value is stored normally in state
*/
@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        visualTransformation = PasswordVisualTransformation()
    )
}

/*
 Custom VisualTransformation - Phone number formatting

 Formats as: (XXX) XXX-XXXX

 VisualTransformation requires:
 1. filter() method - returns TransformedText
 2. TransformedText contains:
    - text: The formatted text to display
    - offsetMapping: Maps between original and transformed positions
*/
class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 10) text.text.substring(0, 10) else text.text
        var formatted = ""

        for (i in trimmed.indices) {
            when (i) {
                0 -> formatted += "("
                3 -> formatted += ") "
                6 -> formatted += "-"
            }
            formatted += trimmed[i]
        }

        /*
         OffsetMapping - Maps cursor position between original and formatted text

         originalToTransformed: original index → formatted index
         transformedToOriginal: formatted index → original index

         This ensures cursor moves correctly when user edits
        */
        val offsetMapping = object : OffsetMapping {
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

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

@Composable
fun FormattedPhoneNumberField() {
    var phone by rememberSaveable { mutableStateOf("") }

    TextField(
        value = phone,
        onValueChange = {
            // Only allow digits, max 10 characters
            if (it.all { char -> char.isDigit() } && it.length <= 10) {
                phone = it
            }
        },
        label = { Text("Phone Number") },
        visualTransformation = PhoneNumberVisualTransformation(),
        singleLine = true
    )
}

/*
 Credit card formatting

 Formats as: XXXX XXXX XXXX XXXX
 Groups of 4 digits with spaces
*/
class CreditCardVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0, 16) else text.text
        var formatted = ""

        trimmed.forEachIndexed { index, char ->
            if (index > 0 && index % 4 == 0) {
                formatted += " "
            }
            formatted += char
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset + (offset / 4)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset - (offset / 5)
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}

@Composable
fun CreditCardField() {
    var cardNumber by rememberSaveable { mutableStateOf("") }

    TextField(
        value = cardNumber,
        onValueChange = {
            if (it.all { char -> char.isDigit() } && it.length <= 16) {
                cardNumber = it
            }
        },
        label = { Text("Card Number") },
        visualTransformation = CreditCardVisualTransformation(),
        singleLine = true
    )
}

/*
 Toggle password visibility

 Common pattern: Show/hide password based on user toggle
 VisualTransformation.None shows actual text
*/
@Composable
fun PasswordWithToggle() {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        singleLine = true
        // In real app, add trailing icon button to toggle passwordVisible
    )
}

