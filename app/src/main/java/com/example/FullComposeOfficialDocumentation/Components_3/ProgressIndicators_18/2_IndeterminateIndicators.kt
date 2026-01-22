package com.example.FullComposeOfficialDocumentation.Components_3.ProgressIndicators_18

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
An indeterminate indicator does not reflect how close to completion an operation is.
Rather it uses motion to the user that processing is ongoing, though without specifying any degree of completion


To create an indeterminate progress indicator
we use the LinearProgressIndicator or the CircularProgressIndicator composable
but do not pass in a value for progress.

*/


@Composable
fun IndeterminateCircularIndicator(){
    var loading by remember{mutableStateOf(false)}
    Button(
        onClick = {loading=true},enabled=!loading
    ) {
        Text("Start to load")
    }

    if(!loading)return
    CircularProgressIndicator(
        modifier=Modifier.width(64.dp),
        color=MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant
    )
}