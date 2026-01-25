package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

/*
 Keyboard Options - Configure the software keyboard

 KeyboardOptions lets you customize:
 - capitalization: Auto-capitalize words, sentences, or characters
 - keyboardType: Number, email, phone, password, etc
 - imeAction: Done, Next, Search, Send, etc

 Note: These are suggestions to the keyboard
 Some keyboards may not comply with all options
*/

/*
 Capitalization options

 KeyboardCapitalization.None: No auto-capitalization
 KeyboardCapitalization.Characters: ALL CAPS
 KeyboardCapitalization.Words: Capitalize First Letter Of Each Word
 KeyboardCapitalization.Sentences: Capitalize first letter of sentences
*/
@Composable
fun CapitalizationExample() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words
        )
    )
}

/*
 Keyboard type examples

 KeyboardType determines which keyboard layout appears:
 - Text: Standard QWERTY keyboard
 - Number: Numeric keyboard
 - Phone: Phone number pad
 - Email: Keyboard with @ and .com
 - Password: May hide characters
*/
@Composable
fun EmailKeyboardExample() {
    var email by remember { mutableStateOf("") }

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        )
    )
}

@Composable
fun PhoneKeyboardExample() {
    var phone by remember { mutableStateOf("") }

    TextField(
        value = phone,
        onValueChange = { phone = it },
        label = { Text("Phone") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        )
    )
}

@Composable
fun NumberKeyboardExample() {
    var number by remember { mutableStateOf("") }

    TextField(
        value = number,
        onValueChange = { number = it },
        label = { Text("Number") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}

/*
 IME Action - The action button on keyboard

 ImeAction determines what the "enter" button shows:
 - Done: Shows "Done" - typically closes keyboard
 - Next: Moves to next field
 - Search: Shows search icon
 - Send: Shows send icon
*/
@Composable
fun ImeActionExample() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        )
    )
}

/*
 Complete keyboard configuration for email input
*/
@Composable
fun EmailInputField() {
    var email by remember { mutableStateOf("") }

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.None
        )
    )
}

/*
 Password field configuration
*/
@Composable
fun PasswordInputField() {
    var password by remember { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )
}

