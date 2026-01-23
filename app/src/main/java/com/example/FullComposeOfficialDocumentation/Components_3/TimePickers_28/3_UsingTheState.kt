package com.example.FullComposeOfficialDocumentation.Components_3.TimePickers_28

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

/*

To make the use of the time that the user has selected in a time piker pass the appropriate
TimePickerState to the onConfirm function .
The parent composable can then access the selected time through TimePickerState.hour and the TimePickerState.minute

*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialUseStateExample(
    onConfirm:(TimePickerState)-> Unit,
    onDismiss:()-> Unit
){
    val currentTime=Calendar.getInstance()

    val timePickerState= rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )
    Column{
        TimePicker(
            state=timePickerState
        )
        Button(
            onClick = onDismiss
        ){
            Text("Dismiss Picker")
        }

        Button(onClick = {onConfirm(timePickerState)}){
            Text("Confirm selection")
        }
    }
}

/*
Parent composable that uses DialUseStateExample.
This demonstrates how to:
1. Show/hide the time picker with state
2. Handle the confirmed time selection
3. Access hour and minute from TimePickerState
4. Display the selected time to the user
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerParentExample() {
    // State to control whether time picker is visible
    var showTimePicker by remember { mutableStateOf(false) }

    // State to store the selected time
    var selectedHour by remember { mutableStateOf<Int?>(null) }
    var selectedMinute by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (showTimePicker) {
            // Show the time picker dialog
            DialUseStateExample(
                onConfirm = { timePickerState ->
                    // Extract hour and minute from TimePickerState
                    selectedHour = timePickerState.hour
                    selectedMinute = timePickerState.minute
                    // Hide the picker
                    showTimePicker = false
                },
                onDismiss = {
                    // Just hide the picker without saving
                    showTimePicker = false
                }
            )
        } else {
            // Show the selected time and button to open picker
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                // Display selected time
                if (selectedHour != null && selectedMinute != null) {
                    Text(
                        text = "Selected Time: ${String.format("%02d:%02d", selectedHour, selectedMinute)}",
                        fontSize = 24.sp
                    )
                } else {
                    Text(
                        text = "No time selected",
                        fontSize = 18.sp
                    )
                }

                // Button to show time picker
                Button(onClick = { showTimePicker = true }) {
                    Text("Select Time")
                }
            }
        }
    }
}
