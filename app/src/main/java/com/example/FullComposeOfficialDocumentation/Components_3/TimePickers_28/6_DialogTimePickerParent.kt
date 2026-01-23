package com.example.FullComposeOfficialDocumentation.Components_3.TimePickers_28

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Parent composable demonstrating both basic and advanced dialog time pickers.

This shows:
1. How to manage dialog visibility state
2. How to handle time selection from dialogs
3. How to display selected time
4. Difference between basic and advanced implementations

Usage pattern:
- User clicks button to open dialog
- Dialog appears with time picker
- User selects time and clicks OK (or Cancel)
- Selected time is saved and displayed
- Dialog closes automatically
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialogParentExample() {
    // State to control which dialog is shown
    var showBasicDialog by remember { mutableStateOf(false) }
    var showAdvancedDialog by remember { mutableStateOf(false) }

    // State to store selected times
    var basicSelectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var advancedSelectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            // Basic Dialog Section
            Text(
                text = "Basic Dialog Time Picker",
                fontSize = 20.sp
            )

            if (basicSelectedTime != null) {
                Text(
                    text = "Selected: ${String.format("%02d:%02d", basicSelectedTime!!.first, basicSelectedTime!!.second)}",
                    fontSize = 18.sp
                )
            } else {
                Text("No time selected")
            }

            Button(onClick = { showBasicDialog = true }) {
                Text("Open Basic Dialog")
            }

            // Divider
            Text(
                text = "─────────────────",
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Advanced Dialog Section
            Text(
                text = "Advanced Dialog Time Picker",
                fontSize = 20.sp
            )

            if (advancedSelectedTime != null) {
                Text(
                    text = "Selected: ${String.format("%02d:%02d", advancedSelectedTime!!.first, advancedSelectedTime!!.second)}",
                    fontSize = 18.sp
                )
            } else {
                Text("No time selected")
            }

            Button(onClick = { showAdvancedDialog = true }) {
                Text("Open Advanced Dialog")
            }
        }

        // Show dialogs when state is true
        if (showBasicDialog) {
            DialWithDialogExample(
                onConfirm = { timePickerState ->
                    basicSelectedTime = Pair(timePickerState.hour, timePickerState.minute)
                    showBasicDialog = false
                },
                onDismiss = {
                    showBasicDialog = false
                }
            )
        }

        if (showAdvancedDialog) {
            AdvancedTimePickerExample(
                onConfirm = { timePickerState ->
                    advancedSelectedTime = Pair(timePickerState.hour, timePickerState.minute)
                    showAdvancedDialog = false
                },
                onDismiss = {
                    showAdvancedDialog = false
                }
            )
        }
    }
}

