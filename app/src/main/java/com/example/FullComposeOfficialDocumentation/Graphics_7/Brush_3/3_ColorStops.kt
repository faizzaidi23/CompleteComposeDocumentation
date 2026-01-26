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
COLOR STOPS

To customize how the colors appear in the gradient, you can tweak the colorStops value for each one
colorStops should be specified as a fraction, between 0 and 1
Values greater than 1 will result in those colors not rendering as part of the gradient

You can configure the color stops to have different amounts, such as less or more of one color
This gives you precise control over where each color appears in the gradient
*/

/*
Basic Color Stops Example

This example shows how to distribute colors unevenly across the gradient
- Yellow appears from 0.0 to 0.2 (20% of the gradient)
- Red appears from 0.2 to 1.0 (80% of the gradient with Blue)
- Blue appears at the end
*/

@Composable
fun BasicColorStopsExample() {
    val colorStops = arrayOf(
        0.0f to Color.Yellow,
        0.2f to Color.Red,
        1f to Color.Blue
    )

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.horizontalGradient(colorStops = colorStops))
    )
}

/*
Even Distribution (Default)

Without specifying color stops, colors are distributed evenly
This is equivalent to specifying stops at 0.0, 0.5, and 1.0 for three colors
*/

@Composable
fun EvenDistributionExample() {
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(Color.Yellow, Color.Red, Color.Blue)
                )
            )
    )
}

/*
Custom Distribution Example

This shows more control - emphasizing certain colors over others
*/

@Composable
fun CustomDistributionExample() {
    val colorStops = arrayOf(
        0.0f to Color(0xFF9C27B0),  // Purple
        0.3f to Color(0xFF2196F3),  // Blue - appears early
        0.9f to Color(0xFF4CAF50),  // Green - appears late
        1.0f to Color(0xFFFFEB3B)   // Yellow - small portion at end
    )

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.horizontalGradient(colorStops = colorStops))
    )
}

/*
Color Stops with Vertical Gradient

Color stops work the same way with vertical gradients
*/

@Composable
fun VerticalColorStopsExample() {
    val colorStops = arrayOf(
        0.0f to Color(0xFFFF5722),  // Deep Orange at top
        0.5f to Color(0xFFFFC107),  // Amber in middle
        1.0f to Color(0xFF4CAF50)   // Green at bottom
    )

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.verticalGradient(colorStops = colorStops))
    )
}

/*
Color Stops with Radial Gradient

For radial gradients, stops control the distance from the center
0.0 is at center, 1.0 is at the edge
*/

@Composable
fun RadialColorStopsExample() {
    val colorStops = arrayOf(
        0.0f to Color.White,         // Center
        0.3f to Color(0xFF2196F3),   // Blue ring
        0.7f to Color(0xFF3F51B5),   // Indigo ring
        1.0f to Color(0xFF1A237E)    // Dark blue edge
    )

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(Brush.radialGradient(colorStops = colorStops))
    )
}

/*
Comparison Showcase

This shows the difference between default and custom color stops
*/

@Composable
fun ColorStopsComparisonShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Color Stops Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Default even distribution
        Text("Even Distribution (Default)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.Yellow, Color.Red, Color.Blue)
                    )
                )
        )

        // Less yellow, more red and blue
        Text("Less Yellow, More Red/Blue", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Yellow,
                            0.2f to Color.Red,
                            1.0f to Color.Blue
                        )
                    )
                )
        )

        // Concentrated in middle
        Text("Concentrated Colors", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Blue,
                            0.4f to Color.Red,
                            0.6f to Color.Yellow,
                            1.0f to Color.Green
                        )
                    )
                )
        )

        // Sharp transitions
        Text("Sharp Transitions", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Magenta,
                            0.33f to Color.Magenta,
                            0.34f to Color.Cyan,
                            0.66f to Color.Cyan,
                            0.67f to Color.Yellow,
                            1.0f to Color.Yellow
                        )
                    )
                )
        )
    }
}

