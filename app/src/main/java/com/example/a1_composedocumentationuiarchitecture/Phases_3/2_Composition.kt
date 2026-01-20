package com.example.a1_composedocumentationuiarchitecture.Phases_3

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
This is the most common phase. When you read a state's value directly inside your composable function, you are reading it during Composition.

When the state changes: It triggers a recomposition. This means Compose re-runs your function to create a new blueprint of the UI.
 This might also cause the Layout and Drawing phases to run
 if the blueprint changes.

*/

// Code


@Composable
fun Composition(){
    var padding  by remember{mutableStateOf(8.dp)}
    Text(
        text = "Hello",
        // The `padding` state is read here, during the Composition phase.
        modifier = Modifier.padding(padding)
    )
}

// If we change the value of the padding the whole composable will recompose and there will be a recomposition

