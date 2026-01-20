package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.ManagingStates_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelloScreens(){
    var name by rememberSaveable { mutableStateOf("") }
    HelloContents(name=name,onNameChange={name=it})
}



// The child-->HelloContent


/*
Use remember for state that is cheap to recalculate.
Use rememberSaveable for state that the user has entered or
that is important to keep, even if they rotate their phone.
*/

@Composable
fun HelloContents(
    name:String,
    onNameChange:(String)->Unit
){
    Column(modifier=Modifier.padding(16.dp)){
        Text(
            text="Hello, $name",
            modifier=Modifier.padding(bottom=8.dp),
            style=MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value=name,
            onValueChange = onNameChange,
            label={Text("Name")}
        )
    }
}


/*
ViewModel vs RememberSaveable



ViewModel is for Business Logic State or Screen State. This is the important data your app works with (e.g., user profiles, list of products, form data that needs to be saved).

rememberSaveable is for transient UI State. This is temporary state that only matters to the UI itself and has no impact on your app's data (e.g., scroll position, text in a search bar before you hit search).
*/


/*
remember vs rememberSaveable


## The Part That is SIMILAR
Both remember and rememberSaveable survive recomposition.

A recomposition happens when a state changes and Compose redraws a part of your screen. It's like a painter touching up a small part of a painting. In this case, both tools remember their value.

remember: Survives this.

rememberSaveable: Survives this.

This is the "similar" behavior the documentation is talking about.

## The Part That is DIFFERENT (The Superpower)
rememberSaveable also survives activity/process recreation.

This is a much bigger event, like a screen rotation, where Android destroys and completely recreates your UI. It's like the painter's entire canvas is taken away and replaced with a fresh one.

remember: Does NOT survive this.  Its value is wiped clean and reset to the default.

rememberSaveable: DOES survive this.  It saves its value before the UI is destroyed and restores it on the new canvas.
*/


/*
## When to use remember
Use remember for state that is cheap to create and not critical for the user experience if it gets reset. It's for temporary UI state that only matters while the screen is in its current configuration.

Scenarios:
UI Element Visibility Toggles:

Example: A button that shows or hides a tooltip when pressed.

Why remember? If the user rotates the screen, it's perfectly acceptable for the tooltip to be hidden again. It's not a frustrating loss of state.

Kotlin

var tooltipVisible by remember { mutableStateOf(false) }
Animation States:

Example: Animate a button's color or size when it's clicked.

Why remember? Animation state is transient. If the animation is interrupted by a screen rotation, it's fine for it to reset to its default state. The ViewModel doesn't care about the button's current color.

Kotlin

val color by animateColorAsState(if (isSelected) Blue else Gray)
// The internal state of this animation uses 'remember'.
Calculated State Based on ViewModel Data:

Example: A boolean flag that checks if a list from the ViewModel is empty.

Why remember? This state is easily recalculated from the ViewModel's data. When the screen rotates, the ViewModel provides the list again, and the isEmpty check can be run instantly.

Kotlin

val userList = viewModel.users.collectAsState()
val isListEmpty by remember(userList.value) {
    derivedStateOf { userList.value.isEmpty() }
}
Rule of Thumb for remember: If the state can be reset on rotation without annoying the user, use remember.

## When to use rememberSaveable
Use rememberSaveable for any state that the user has directly or indirectly created, or state that is critical to maintaining their place in the UI. Losing this state would be frustrating.

Scenarios:
User Input in Text Fields:

Example: A user typing their name, a search query, or a message in a form.

Why rememberSaveable? This is the most critical use case. If a user types a long message and then rotates their phone, erasing the text is a terrible user experience.

p    var searchText by rememberSaveable { mutableStateOf("") }
TextField(value = searchText, onValueChange = { searchText = it })
```

Scroll Position in Lists:

Example: The user scrolls halfway down a long LazyColumn of news articles.

Why rememberSaveable? When they rotate the screen, they expect to be in the same spot, not back at the top of the list. rememberLazyListState() uses rememberSaveable internally to achieve this.

Kotlin

val listState = rememberLazyListState() // This uses rememberSaveable
LazyColumn(state = listState) { /* ... */ }
State of Complex UI Components:

Example: The currently selected tab in a TabRow, the current page in a Pager, or the state of a BottomSheet.

Why rememberSaveable? The user made a conscious choice to be on a specific tab or page. Resetting this choice on rotation forces them to navigate again.

Kotlin

var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
Dialogs and Pop-ups:

Example: A user clicks a button to open a selection dialog.

Why rememberSaveable? If they rotate the screen while the dialog is open, it should ideally still be open after rotation.

Kotlin

var showDialog by rememberSaveable { mutableStateOf(false) }
Rule of Thumb for rememberSaveable: If losing the state on rotation would make the user say "Ugh!", use rememberSaveable.
*/