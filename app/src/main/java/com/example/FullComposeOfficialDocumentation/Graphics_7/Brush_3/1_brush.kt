package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
BRUSH IN COMPOSE

A brush in Compose describes how something is drawn on screen.

It determines the color(s) that are drawn in the drawing area (i.e. a circle, square, path)
There are a few built-in Brushes that are useful for drawing such as:
- LinearGradient
- RadialGradient
- SolidColor brush

Brushes can be used with:
- Modifier.background()
- TextStyle
- DrawScope draw calls

to apply the painting style to the content being drawn
*/

/*
BASIC BRUSH USAGE

A horizontal gradient brush can be applied to drawing a circle in DrawScope
This example creates a simple horizontal gradient from Red to Blue
*/

@Composable
fun HorizontalGradientCircleExample() {
    val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush)
        }
    )
}

/*
Brush with background modifier

This shows how to use a brush with Modifier.background()
*/

@Composable
fun BrushWithBackgroundExample() {
    val brush = Brush.horizontalGradient(
        listOf(
            Color(0xFFFF6B6B),
            Color(0xFF4ECDC4)
        )
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(brush)
    )
}

/*
Multiple horizontal gradients example

Demonstrates different color combinations with horizontal gradients
*/

@Composable
fun MultipleHorizontalGradientsExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Horizontal Gradient Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Red to Blue gradient
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.Red, Color.Blue)
                    )
                )
        )

        Text("Red to Blue", fontSize = 16.sp)

        // Yellow to Green gradient
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.Yellow, Color.Green)
                    )
                )
        )

        Text("Yellow to Green", fontSize = 16.sp)

        // Multi-color gradient
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color.Magenta,
                            Color.Cyan,
                            Color.Yellow
                        )
                    )
                )
        )

        Text("Multi-color", fontSize = 16.sp)
    }
}
