package com.example.FullComposeOfficialDocumentation.Components_3.TimePickers_28

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
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