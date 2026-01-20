package com.example.a1_composedocumentationuiarchitecture.Phases_3

import android.graphics.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
This is the final phase where pixels are actually painted to the screen
You can read state here inside drawing specific code

When the state changes:It only restarts the drawing phase.
This is the most efficient update possible
*/

/*
The code inside the Canvas{...} block is the drawing code. It runs during the drawing phase

If we change the color eg blue to red the compose does not recomposes or re layout anything. It  just re runs the drawing instructions for this Canvas which is extremely fast
*/

@Composable
fun Drawing(modifier:Modifier= Modifier){
    var color by remember{mutableStateOf(Color.Red)}

    androidx.compose.foundation.Canvas(modifier=modifier){
        drawRect(color)
    }
}