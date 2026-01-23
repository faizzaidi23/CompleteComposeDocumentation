package com.example.FullComposeOfficialDocumentation.Components_3.Switch_26

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

/*
The switch component allows users to toggle between two states checked and unchecked
*/


/*
Basic Implementation-->
The API definition-->

checked-->The initial state of the switch
onCheckedChange-->A callback that is called when the state of the switch changes
enabled-->Whether the switch is enabled or disabled
colors-->The colors used for the switch
*/


@Composable
fun SwitchExample(){
    var checked by remember{ mutableStateOf(true)}

    Switch(
        checked=checked,
        onCheckedChange = {
            checked=it
        }
    )

}