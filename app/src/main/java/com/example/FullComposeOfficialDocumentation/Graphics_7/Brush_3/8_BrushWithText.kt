package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
BRUSH WITH TEXT STYLE

Brushes can be applied to text using the TextStyle brush parameter
This allows you to render text with gradients, patterns, or any custom brush effect

The brush is applied to the text glyphs themselves, creating visually striking text effects
This is commonly used for:
- Gradient text
- Rainbow text effects
- Image-textured text
- Custom shader effects on text
*/

/*
Basic Gradient Text

Apply a horizontal gradient to text
*/

@Composable
fun BasicGradientTextExample() {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFFF6B6B),
            Color(0xFF4ECDC4),
            Color(0xFF45B7D1)
        )
    )

    Text(
        text = "Gradient Text",
        style = TextStyle(
            brush = gradientBrush,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 48.sp
        )
    )
}

/*
Vertical Gradient Text

Apply a vertical gradient to text
*/

@Composable
fun VerticalGradientTextExample() {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFD700),
            Color(0xFFFF8C00),
            Color(0xFFFF4500)
        )
    )

    Text(
        text = "FIRE TEXT",
        style = TextStyle(
            brush = gradientBrush,
            fontWeight = FontWeight.Black,
            fontSize = 40.sp
        )
    )
}

/*
Linear Gradient Text

Apply a diagonal linear gradient to text
*/

@Composable
fun LinearGradientTextExample() {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF667eea),
            Color(0xFF764ba2),
            Color(0xFFF093FB)
        )
    )

    Text(
        text = "Beautiful",
        style = TextStyle(
            brush = gradientBrush,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp
        )
    )
}

/*
Rainbow Text

Create a rainbow effect using sweep gradient
*/

@Composable
fun RainbowTextExample() {
    val rainbowBrush = Brush.sweepGradient(
        colors = listOf(
            Color.Red,
            Color(0xFFFF7F00), // Orange
            Color.Yellow,
            Color.Green,
            Color.Blue,
            Color(0xFF4B0082), // Indigo
            Color(0xFF9400D3), // Violet
            Color.Red
        )
    )

    Text(
        text = "RAINBOW",
        style = TextStyle(
            brush = rainbowBrush,
            fontWeight = FontWeight.Black,
            fontSize = 48.sp
        )
    )
}

/*
Radial Gradient Text

Apply a radial gradient to text
*/

@Composable
fun RadialGradientTextExample() {
    val radialBrush = Brush.radialGradient(
        colors = listOf(
            Color.White,
            Color(0xFF00BCD4),
            Color(0xFF3F51B5)
        )
    )

    Text(
        text = "RADIAL",
        style = TextStyle(
            brush = radialBrush,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 46.sp
        )
    )
}

/*
Gold Text Effect

Create a metallic gold effect using gradient
*/

@Composable
fun GoldTextExample() {
    val goldBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFD700),
            Color(0xFFFFE55C),
            Color(0xFFB8860B),
            Color(0xFFFFD700)
        )
    )

    Text(
        text = "GOLD",
        style = TextStyle(
            brush = goldBrush,
            fontWeight = FontWeight.Black,
            fontSize = 52.sp
        )
    )
}

/*
All Text Brush Examples Showcase

Displays various text styling with brushes
*/

@Composable
fun TextBrushShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Brush with Text",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Horizontal Gradient
        Text(
            text = "Horizontal",
            style = TextStyle(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF6B6B),
                        Color(0xFF4ECDC4)
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 42.sp
            )
        )

        // Vertical Gradient
        Text(
            text = "Vertical",
            style = TextStyle(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFD700),
                        Color(0xFFFF4500)
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 42.sp
            )
        )

        // Linear Gradient
        Text(
            text = "Linear",
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF667eea),
                        Color(0xFFF093FB)
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 42.sp
            )
        )

        // Rainbow
        Text(
            text = "RAINBOW",
            style = TextStyle(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color.Red,
                        Color(0xFFFF7F00),
                        Color.Yellow,
                        Color.Green,
                        Color.Blue,
                        Color(0xFF4B0082),
                        Color(0xFF9400D3),
                        Color.Red
                    )
                ),
                fontWeight = FontWeight.Black,
                fontSize = 44.sp
            )
        )

        // Gold
        Text(
            text = "GOLD",
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFD700),
                        Color(0xFFFFE55C),
                        Color(0xFFB8860B),
                        Color(0xFFFFD700)
                    )
                ),
                fontWeight = FontWeight.Black,
                fontSize = 48.sp
            )
        )

        // Radial
        Text(
            text = "Radial",
            style = TextStyle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFF00BCD4),
                        Color(0xFF3F51B5)
                    )
                ),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 44.sp
            )
        )
    }
}
