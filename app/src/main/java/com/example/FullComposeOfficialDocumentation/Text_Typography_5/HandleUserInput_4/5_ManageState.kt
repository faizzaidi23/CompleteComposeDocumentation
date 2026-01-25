package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/*
 Managing TextField State

 State management options:
 1. remember - State survives recomposition but NOT configuration changes
 2. rememberSaveable - State survives both recomposition AND configuration changes (screen rotation, process death)
 3. State hoisting - Moving state up to parent composable for sharing

 rememberSaveable uses Bundle to save/restore state automatically
 This is crucial for good user experience
*/

/*
 Using remember - Basic state management

 State is lost on configuration change (screen rotation)
 Good for temporary UI state that doesn't need persistence
*/
@Composable
fun TextFieldWithRemember() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Lost on rotation") }
    )
}

/*
 Using rememberSaveable - Persistent state

 State survives:
 - Recomposition
 - Configuration changes (rotation)
 - Process death (system kills app)

 Recommended for user input that shouldn't be lost
*/
@Composable
fun TextFieldWithRememberSaveable() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Survives rotation") }
    )
}

/*
 State hoisting - Moving state to parent

 Benefits:
 - Share state between composables
 - Access state from outside the TextField
 - Test composable more easily
 - Single source of truth
*/
@Composable
fun StatefulTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) }
    )
}

@Composable
fun ParentWithHoistedState() {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    StatefulTextField(
        value = username,
        onValueChange = { username = it },
        label = "Username"
    )

    StatefulTextField(
        value = email,
        onValueChange = { email = it },
        label = "Email"
    )

    // Can access username and email here
    // Can validate, submit, or share with other composables
}

/*
 Initial value with rememberSaveable

 You can provide initial text that appears when composable is first created
 State is only initialized once, not on every recomposition
*/
@Composable
fun TextFieldWithInitialValue() {
    var text by rememberSaveable { mutableStateOf("Initial text") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("With initial value") }
    )
}

