package com.example.FullComposeOfficialDocumentation.Text_Typography_5.EnableAutoFill_9

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun BasicAutofillUsername() {
    val textFieldValue = remember { mutableStateOf("") }

    TextField(
        value = textFieldValue.value,
        onValueChange = { textFieldValue.value = it },
        label = { Text("Username") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}