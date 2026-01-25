package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/*
 Style TextField input with Brush API

 You can apply gradients and advanced brush effects to TextField input
 The brush is applied through TextStyle
 Works on user input as they type
*/

/*
 Rainbow gradient TextField

 Brush.linearGradient creates a gradient from multiple colors
 The gradient effect appears on the text as user types
*/
@Composable
fun GradientTextField() {
    var text by remember { mutableStateOf("") }

    val brush = remember {
        Brush.linearGradient(
            colors = listOf(
                Color.Red,
                Color.Yellow,
                Color.Green,
                Color.Blue,
                Color.Magenta
            )
        )
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(brush = brush)
    )
}

/*
 Horizontal gradient TextField
*/
@Composable
fun HorizontalGradientTextField() {
    var text by remember { mutableStateOf("Gradient Text") }

    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color.Cyan, Color.Blue, Color.Magenta)
        )
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        textStyle = TextStyle(brush = brush)
    )
}

