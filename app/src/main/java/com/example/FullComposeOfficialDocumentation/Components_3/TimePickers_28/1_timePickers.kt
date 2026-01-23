package com.example.FullComposeOfficialDocumentation.Components_3.TimePickers_28

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import java.util.Calendar

/*
Time pickers provide a way for users to select a time we can use the TimePicker and TimeInput composable
to implement a time picker in the app

Types-->

Dial-->Lets users set a time by moving a handle around a dial
Input-->Lets users set a time using their keyboard
*/

/*
API Surface-->

To implement a time picker we use either the TimePicker or the TimeInput composable

TimePicker-->Implements a dial for time pickers
TimeInput-->Implements an input time picker
*/

/*
State-->
For both TimePicker and TimeInput we must also pass a TimePickerState
This lets us set the default selected time that appears on the picker
It also captures the time that the user has selected using the picker

Key parameters:
- initialHour: Starting hour (0-23 for 24-hour, 1-12 for 12-hour)
- initialMinute: Starting minute (0-59)
- is24Hour: true for 24-hour format, false for 12-hour with AM/PM
*/

/*
Dialog-->
Time pickers appear in dialogs.
The examples in this guide don't use dialogs
*/

// Dial time picker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    Column {
        TimePicker(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }

        Button(onClick = onConfirm) {
            Text("Confirm Button")
        }
    }
}