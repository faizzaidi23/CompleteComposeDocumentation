package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.ManagingStates_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HelloScreen(){
    var name by rememberSaveable { mutableStateOf("") }
    HelloContent(name=name,onNameChange={name=it})
}



// The child-->HelloContent


/*
Use remember for state that is cheap to recalculate.
Use rememberSaveable for state that the user has entered or
that is important to keep, even if they rotate their phone.
*/

@Composable
fun HelloContent(
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