package com.example.FullComposeOfficialDocumentation.Components_3.TimePickers_28

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import java.util.Calendar

/*
Time pickers in dialogs - Basic Example

Time pickers often appear in dialogs rather than directly on the screen.
This provides a focused experience for time selection.

Key concepts:
- AlertDialog: Simple built-in dialog with standard buttons
- onDismissRequest: Called when user clicks outside or presses back
- dismissButton & confirmButton: Standard action buttons
- text parameter: Contains the dialog content (TimePicker in this case)

This basic approach is good for:
- Simple time selection needs
- Quick implementation
- Standard Material Design appearance
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialWithDialogExample(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    TimePickerDialog(
        onDismiss = { onDismiss() },
        onConfirm = { onConfirm(timePickerState) }
    ) {
        TimePicker(
            state = timePickerState,
        )
    }
}

/*
Reusable TimePickerDialog component using AlertDialog.

Parameters:
- onDismiss: Called when dialog is dismissed (back button, outside click, or Dismiss button)
- onConfirm: Called when user clicks OK button
- content: The composable to display (TimePicker, TimeInput, etc.)

This creates a standard Material Design dialog with:
- Dismiss button on the left
- Confirm button on the right
- Customizable content in the middle
*/

@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("OK")
            }
        },
        text = { content() }
    )
}

