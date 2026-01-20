package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Phases_3

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

/*
This phase happens after Composition.
It's when Compose figures out the size and position of your UI elements.
You can read state here by using special versions of
modifiers that take a lambda (a block of code).
*/

@Composable
fun Layout(){
    var offSetX by remember{mutableStateOf(8.dp)}
    Text(text="Hello",modifier=Modifier.offset{
        IntOffset(offSetX.roundToPx(),0)
    })
}

/*
IntOffset is a simple data class that holds two integer values:
an x and a y coordinate. It's essentially a container for a position
on the screen, measured in pixels.



The Modifier.offset lambda requires you to return an IntOffset because it needs to know exactly how many pixels to shift the UI element horizontally (x) and vertically (y).

So, your code is doing the following:

offsetX.roundToPx(): Takes your 8.dp value and converts it into the corresponding number of integer pixels for the device's screen density.

IntOffset(..., 0): Creates the required position object, telling the modifier to shift the Text by that many pixels on the x-axis and by 0 pixels on the y-axis.
*/