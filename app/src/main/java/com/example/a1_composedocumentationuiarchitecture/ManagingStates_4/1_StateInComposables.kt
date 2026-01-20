package com.example.a1_composedocumentationuiarchitecture.ManagingStates_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Use of remember and mutableStateOf


/*
Any changes to value schedules recomposition of any composable functions that read value.

There are three ways to declare a MutableState object in a composable:

val mutableState = remember { mutableStateOf(default) }
var value by remember { mutableStateOf(default) }
val (value, setValue) = remember { mutableStateOf(default) }
*/


@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}


/*
val myItems= remember{mutableStateOf(mutableListOf("first" , "second")}

here we can make changes in this list like myItems.add("Third")

*/

// Corrected code--->Just replace the whole object

// State holds an immutable list

@Composable
fun corrected(){

    var items by remember { mutableStateOf(listOf("Task 1", "Task 2")) }

    Button(onClick = {
        // To add an item:
        // 1. Create a new list by copying the old one and adding the new item.
        // 2. Assign this *new list* to the state variable.
        items = items + "Task 3"
    }) {
        Text("Add Task")
    }

}
