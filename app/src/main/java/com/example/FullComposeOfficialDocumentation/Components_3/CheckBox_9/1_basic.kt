package com.example.FullComposeOfficialDocumentation.Components_3.CheckBox_9

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

/*
Checkboxes let users select one or more items from a list.
We might use a checkbox to let the user do the following things


Turn an item on or off
Select from multiple options in a list
Indicate agreement or acceptance
*/

/*
Anatomy-->

Box-->This is the container for the checkbox
Check-->This is the visual indicator that shows whether the checkbox is selected or not
Label-->This is the text that describes the checkbox
*/


/*
States-->

Unselected--->The checkbox is not selected.The box is empty

Intermediate-->The checkbox is in an intermediate state.The box contains a dash

Selected-->The checkbox is selected.The box contains a checkmark
*/


@Composable
fun CheckboxParentExample(){
    val childCheckedStates = remember { mutableStateListOf(false, false, false) }

    val parentState = when {
        childCheckedStates.all { it } -> ToggleableState.On
        childCheckedStates.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val newState = parentState != ToggleableState.On
                    childCheckedStates.forEachIndexed { index, _ ->
                        childCheckedStates[index] = newState
                    }
                }
            )
            Text("Select All")
        }

        childCheckedStates.forEachIndexed { index, checked ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        childCheckedStates[index] = isChecked
                    }
                )
                Text("Option ${index + 1}")
            }
        }
    }
}

@Composable
fun CheckboxBasicExample() {
    val checkedState = remember { mutableStateListOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Checkbox(
            checked = checkedState[0],
            onCheckedChange = { isChecked ->
                checkedState[0] = isChecked
            }
        )
        Text("Accept terms and conditions")
    }
}

@Composable
fun CheckboxMultipleExample() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    val checkedStates = remember { mutableStateListOf(false, false, false, false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Select your preferences:", modifier = Modifier.padding(bottom = 8.dp))

        options.forEachIndexed { index, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked ->
                        checkedStates[index] = isChecked
                    }
                )
                Text(option)
            }
        }
    }
}