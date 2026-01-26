package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
BRUSH WITH SHAPES

Brushes can be combined with shape modifiers to create interesting effects
Using clip() or background() with different shapes creates various visual designs

Common combinations:
- Brush + CircleShape
- Brush + RoundedCornerShape
- Brush + Custom shapes
*/

/*
Circular Gradient

Combine a radial gradient brush with CircleShape for a perfect circular gradient
*/

@Composable
fun CircularGradientExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFEB3B),
                        Color(0xFFFF9800),
                        Color(0xFFE91E63)
                    )
                )
            )
    )
}

/*
Rounded Rectangle Gradient

Combine a linear gradient with rounded corners
*/

@Composable
fun RoundedRectangleGradientExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF667eea),
                        Color(0xFF764ba2)
                    )
                )
            )
    )
}

/*
Pill Shape Gradient

Create a pill-shaped button with gradient
*/

@Composable
fun PillShapeGradientExample() {
    Box(
        modifier = Modifier
            .size(width = 200.dp, height = 80.dp)
            .clip(RoundedCornerShape(50))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF11998e),
                        Color(0xFF38ef7d)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Button",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

/*
Circular with Sweep Gradient

Sweep gradient in a circular shape creates a color wheel effect
*/

@Composable
fun CircularSweepGradientExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(
                Brush.sweepGradient(
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
            )
    )
}

/*
Multiple Rounded Corners

Different corner radii with gradient background
*/

@Composable
fun CustomCornersGradientExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 48.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 48.dp
                )
            )
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFf093fb),
                        Color(0xFFf5576c)
                    )
                )
            )
    )
}

/*
Layered Gradients with Shapes

Create interesting effects by layering multiple shapes with different gradients
*/

@Composable
fun LayeredGradientsExample() {
    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        // Outer circle
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF4CAF50),
                            Color(0xFF1B5E20)
                        )
                    )
                )
        )

        // Middle circle
        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF2196F3),
                            Color(0xFF0D47A1)
                        )
                    )
                )
        )

        // Inner circle
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFEB3B),
                            Color(0xFFF57F17)
                        )
                    )
                )
        )
    }
}

/*
All Shape + Brush Combinations Showcase

Displays various shape and brush combinations
*/

@Composable
fun ShapeBrushShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Brush with Shapes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Circular Gradient
        Text("Circular Shape", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFEB3B),
                            Color(0xFFFF9800),
                            Color(0xFFE91E63)
                        )
                    )
                )
        )

        // Rounded Rectangle
        Text("Rounded Rectangle", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF667eea),
                            Color(0xFF764ba2)
                        )
                    )
                )
        )

        // Pill Shape
        Text("Pill Shape", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .clip(RoundedCornerShape(50))
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF11998e),
                            Color(0xFF38ef7d)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Button",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        // Color Wheel
        Text("Color Wheel (Sweep)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(
                    Brush.sweepGradient(
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
                    )
                )
        )

        // Custom Corners
        Text("Custom Corners", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 48.dp,
                        topEnd = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 48.dp
                    )
                )
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFf093fb),
                            Color(0xFFf5576c)
                        )
                    )
                )
        )

        // Layered Circles
        Text("Layered Gradients", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier.size(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFF4CAF50), Color(0xFF1B5E20))
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFF2196F3), Color(0xFF0D47A1))
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFFFFEB3B), Color(0xFFF57F17))
                        )
                    )
            )
        }
    }
}
