package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/*
 TextField allows users to enter and modify text

 Two types available:
 1. State-based TextField (newer, uses TextFieldState)
 2. Value-based TextField (current implementation shown here)

 Value-based TextField:
 - Uses String value and onValueChange callback
 - State management is your responsibility
 - Works with remember and mutableStateOf

 Two Material Design implementations:
 1. TextField - Filled style (default)
 2. OutlinedTextField - Outlined border style
*/

/*
 TextField - Material Design filled style

 Uses remember and mutableStateOf to manage state
 onValueChange updates the state when user types
*/
@Composable
fun MaterialTextField() {
    var text by remember { mutableStateOf("Hello") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}

/*
 OutlinedTextField - Material Design outlined style

 Same state management as TextField
 Different visual appearance with outline border
*/
@Composable
fun MaterialOutlinedTextField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}