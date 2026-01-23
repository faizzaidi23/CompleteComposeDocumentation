package com.example.FullComposeOfficialDocumentation.Components_3.TimePickers_28

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.util.Calendar

/*
Advanced Time Picker Dialog with Mode Toggle

This demonstrates a more sophisticated dialog implementation with:
- Custom Dialog (not AlertDialog) for more flexibility
- Toggle between Dial and Input modes
- Custom styling with Surface, shape, and elevation
- Intrinsic sizing for responsive layout

Key differences from basic dialog:
- Uses Dialog instead of AlertDialog for full control
- DialogProperties(usePlatformDefaultWidth = false) for custom sizing
- Surface provides Material Design styling (shape, elevation, colors)
- IntrinsicSize.Min makes dialog size fit its content
- Toggle button switches between TimePicker (dial) and TimeInput (keyboard)

When to use advanced approach:
- Need to switch between dial and input modes
- Want custom styling beyond AlertDialog
- Need more control over layout and appearance
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTimePickerExample(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    // Determines whether the time picker is dial or input
    var showDial by remember { mutableStateOf(true) }

    // The icon used for the icon button that switches from dial to input
    val toggleIcon = if (showDial) {
        Icons.Filled.Edit      // Show this when dial is active (click to switch to input)
    } else {
        Icons.Filled.Schedule  // Show this when input is active (click to switch to dial)
    }

    AdvancedTimePickerDialog(
        onDismiss = { onDismiss() },
        onConfirm = { onConfirm(timePickerState) },
        toggle = {
            IconButton(onClick = { showDial = !showDial }) {
                Icon(
                    imageVector = toggleIcon,
                    contentDescription = "Time picker type toggle",
                )
            }
        },
    ) {
        if (showDial) {
            TimePicker(state = timePickerState)
        } else {
            TimeInput(state = timePickerState)
        }
    }
}

/*
Reusable Advanced Dialog Component

Parameters:
- title: Dialog title text (default "Select Time")
- onDismiss: Called when dialog is dismissed
- onConfirm: Called when OK is clicked
- toggle: Composable for toggle button (IconButton to switch modes)
- content: The time picker content (TimePicker or TimeInput)

Key implementation details:
- Dialog with custom properties for sizing control
- Surface provides Material Design container with shape and elevation
- IntrinsicSize.Min makes width and height wrap content
- Column for vertical layout (title, content, buttons)
- Row for horizontal button layout with Spacer for alignment
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTimePickerDialog(
    title: String = "Select Time",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )

                // Time picker content (dial or input)
                content()

                // Bottom row with toggle and action buttons
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    TextButton(onClick = onConfirm) { Text("OK") }
                }
            }
        }
    }
}
