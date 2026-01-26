package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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
GRADIENT BRUSHES

There are many built-in gradient brushes that can be used to achieve different gradient effects
These brushes allow you to specify the list of colors that you would like to create a gradient from

Available gradient brush types:
- Brush.horizontalGradient(colorList) - Horizontal Gradient
- Brush.linearGradient(colorList) - Linear Gradient (diagonal by default)
- Brush.verticalGradient(colorList) - Vertical Gradient
- Brush.sweepGradient(colorList) - Sweep Gradient (circular sweep)
- Brush.radialGradient(colorList) - Radial Gradient (from center outward)
*/

/*
Horizontal Gradient

Gradient flows from left to right
*/

@Composable
fun HorizontalGradientExample() {
    val colorList = listOf(Color.Red, Color.Blue, Color.Green)

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.horizontalGradient(colorList))
    )
}

/*
Linear Gradient

Gradient flows diagonally from top-left to bottom-right by default
Can be customized with start and end offsets
*/

@Composable
fun LinearGradientExample() {
    val colorList = listOf(Color.Cyan, Color.Magenta, Color.Yellow)

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.linearGradient(colorList))
    )
}

/*
Vertical Gradient

Gradient flows from top to bottom
*/

@Composable
fun VerticalGradientExample() {
    val colorList = listOf(Color.Yellow, Color.Red, Color.Blue)

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.verticalGradient(colorList))
    )
}

/*
Sweep Gradient

Creates a circular sweep gradient (like a color wheel)
Note: To get a smooth transition between colors - set the last color to the start color
*/

@Composable
fun SweepGradientExample() {
    val colorList = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Blue,
        Color.Magenta,
        Color.Red  // Same as first color for smooth transition
    )

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.sweepGradient(colorList))
    )
}

/*
Radial Gradient

Gradient radiates from center outward in a circular pattern
*/

@Composable
fun RadialGradientExample() {
    val colorList = listOf(Color.White, Color.Blue, Color.Black)

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.radialGradient(colorList))
    )
}

/*
All Gradient Types Showcase

This composable displays all gradient types side by side for comparison
*/

@Composable
fun AllGradientTypesShowcase() {
    val colorList = listOf(Color.Red, Color.Yellow, Color.Blue)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Gradient Brushes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Horizontal Gradient
        Text("Horizontal Gradient", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(Brush.horizontalGradient(colorList))
        )

        // Linear Gradient
        Text("Linear Gradient", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(Brush.linearGradient(colorList))
        )

        // Vertical Gradient
        Text("Vertical Gradient", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(Brush.verticalGradient(colorList))
        )

        // Sweep Gradient
        Text("Sweep Gradient", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.sweepGradient(
                        listOf(
                            Color.Red,
                            Color.Yellow,
                            Color.Green,
                            Color.Cyan,
                            Color.Blue,
                            Color.Magenta,
                            Color.Red
                        )
                    )
                )
        )

        // Radial Gradient
        Text("Radial Gradient", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(Brush.radialGradient(colorList))
        )
    }
}

