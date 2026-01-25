package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction

/*
 KeyboardActions - Handle IME action button clicks

 When user clicks the action button (Done, Next, Search, etc)
 you can perform custom actions

 Common actions:
 - Move focus to next field
 - Submit form
 - Perform search
 - Close keyboard
 - Validate input
*/

/*
 Close keyboard on Done

 LocalFocusManager.clearFocus() closes the keyboard
*/
@Composable
fun CloseKeyboardOnDone() {
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Press Done to close") },
        keyboardActions = KeyboardActions(
            onDone = {
                // Clear focus to close keyboard
                focusManager.clearFocus()
            }
        )
    )
}

/*
 Perform search on Search action

 Common pattern for search bars
*/
@Composable
fun SearchTextField(
    onSearch: (String) -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        label = { Text("Search") },
        keyboardActions = KeyboardActions(
            onSearch = {
                // Trigger search when user presses search button
                onSearch(searchQuery)
            }
        )
    )
}

/*
 Submit form on Done

 Common for login forms, single-field forms
*/
@Composable
fun SubmitOnDone(
    onSubmit: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Enter and submit") },
        keyboardActions = KeyboardActions(
            onDone = {
                onSubmit(text)
                focusManager.clearFocus()
            }
        )
    )
}

/*
 All IME actions

 KeyboardActions supports all ImeAction types:
 - onDone
 - onGo
 - onNext
 - onPrevious
 - onSearch
 - onSend
*/
@Composable
fun AllKeyboardActionsExample() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        keyboardActions = KeyboardActions(
            onDone = { /* Handle done */ },
            onGo = { /* Handle go */ },
            onNext = { /* Handle next */ },
            onPrevious = { /* Handle previous */ },
            onSearch = { /* Handle search */ },
            onSend = { /* Handle send */ }
        )
    )
}

