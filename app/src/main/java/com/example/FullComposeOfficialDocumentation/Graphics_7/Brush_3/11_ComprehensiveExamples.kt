package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
COMPREHENSIVE BRUSH EXAMPLES

This file demonstrates all the major Brush concepts in Jetpack Compose:

1. Basic Gradient Brushes (Horizontal, Vertical, Linear, Radial, Sweep)
2. Color Stops (Custom color distribution)
3. TileMode (Repeated patterns)
4. Brush with Text
5. Brush with Shapes
6. Brush with Canvas/DrawScope

This serves as a comprehensive reference and showcase for all Brush features
*/

/*
Main Showcase Composable

Displays all brush examples in a scrollable layout
*/

@Composable
fun ComprehensiveBrushShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Title
        Text(
            "Brush in Compose - Complete Guide",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Section 1: Basic Gradients
        SectionTitle("1. Basic Gradient Brushes")
        BasicGradientsSection()

        // Section 2: Color Stops
        SectionTitle("2. Color Stops")
        ColorStopsSection()

        // Section 3: TileMode
        SectionTitle("3. TileMode Examples")
        TileModeSection()

        // Section 4: Brush with Text
        SectionTitle("4. Brush with Text")
        BrushWithTextSection()

        // Section 5: Brush with Shapes
        SectionTitle("5. Brush with Shapes")
        BrushWithShapesSection()

        // Section 6: Brush with Canvas
        SectionTitle("6. Brush with Canvas")
        BrushWithCanvasSection()
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1976D2),
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun ExampleCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            content()
        }
    }
}

/*
Section 1: Basic Gradients
*/

@Composable
fun BasicGradientsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Horizontal Gradient
        ExampleCard("Horizontal Gradient") {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFFFF6B6B), Color(0xFF4ECDC4))
                        )
                    )
            )
        }

        // Vertical Gradient
        ExampleCard("Vertical Gradient") {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFFFD700), Color(0xFFFF4500))
                        )
                    )
            )
        }

        // Linear Gradient
        ExampleCard("Linear Gradient (Diagonal)") {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF667eea), Color(0xFF764ba2))
                        )
                    )
            )
        }

        // Radial Gradient
        ExampleCard("Radial Gradient") {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        Brush.radialGradient(
                            listOf(Color.White, Color(0xFF2196F3), Color(0xFF0D47A1))
                        )
                    )
            )
        }

        // Sweep Gradient
        ExampleCard("Sweep Gradient") {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        Brush.sweepGradient(
                            listOf(
                                Color.Red, Color.Yellow, Color.Green,
                                Color.Cyan, Color.Blue, Color.Magenta, Color.Red
                            )
                        )
                    )
            )
        }
    }
}

/*
Section 2: Color Stops
*/

@Composable
fun ColorStopsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Even Distribution
        ExampleCard("Even Distribution (Default)") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color.Yellow, Color.Red, Color.Blue)
                        )
                    )
            )
        }

        // Custom Color Stops
        ExampleCard("Custom Color Stops (Less Yellow)") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
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
        }
    }
}

/*
Section 3: TileMode
*/

@Composable
fun TileModeSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Note: TileMode examples need specific sizing which is complex in a card layout
        ExampleCard("See TileMode.kt for examples") {
            Text(
                "TileMode.Repeated\nTileMode.Mirror\nTileMode.Clamp\nTileMode.Decal",
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}

/*
Section 4: Brush with Text
*/

@Composable
fun BrushWithTextSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ExampleCard("Gradient Text") {
            Text(
                text = "GRADIENT",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFF6B6B),
                            Color(0xFF4ECDC4)
                        )
                    ),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 36.sp
                )
            )
        }

        ExampleCard("Rainbow Text") {
            Text(
                text = "RAINBOW",
                style = TextStyle(
                    brush = Brush.sweepGradient(
                        colors = listOf(
                            Color.Red, Color(0xFFFF7F00), Color.Yellow,
                            Color.Green, Color.Blue, Color(0xFF9400D3), Color.Red
                        )
                    ),
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp
                )
            )
        }
    }
}

/*
Section 5: Brush with Shapes
*/

@Composable
fun BrushWithShapesSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Circular shape
        ExampleCard("Circle") {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            listOf(
                                Color(0xFFFFEB3B),
                                Color(0xFFFF9800),
                                Color(0xFFE91E63)
                            )
                        )
                    )
            )
        }

        // Rounded rectangle
        ExampleCard("Rounded") {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF11998e),
                                Color(0xFF38ef7d)
                            )
                        )
                    )
            )
        }
    }
}

/*
Section 6: Brush with Canvas
*/

@Composable
fun BrushWithCanvasSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ExampleCard("Gradient Circle") {
            Canvas(modifier = Modifier.size(120.dp)) {
                drawCircle(
                    Brush.radialGradient(
                        listOf(
                            Color(0xFFFFEB3B),
                            Color(0xFFFF9800),
                            Color(0xFFFF5722)
                        )
                    )
                )
            }
        }

        ExampleCard("Gradient Path") {
            Canvas(modifier = Modifier.size(150.dp, 80.dp)) {
                val path = Path().apply {
                    moveTo(0f, size.height / 2)
                    cubicTo(
                        size.width / 4, 0f,
                        size.width * 3 / 4, size.height,
                        size.width, size.height / 2
                    )
                }

                drawPath(
                    path = path,
                    brush = Brush.horizontalGradient(
                        listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue)
                    ),
                    style = Stroke(width = 8f)
                )
            }
        }
    }
}

/*
SUMMARY

This file provides a comprehensive overview of all Brush features in Compose:

FILE STRUCTURE:
1_brush.kt - Basic introduction and horizontal gradients
2_GradientBrushes.kt - All gradient types (horizontal, vertical, linear, radial, sweep)
3_ColorStops.kt - Custom color distribution in gradients
4_TileMode.kt - Repeating gradient patterns
5_BrushSize.kt - Custom shader brushes and dynamic sizing
6_ImageShader.kt - Using images as brushes
7_AGSLShader.kt - Advanced custom shaders (requires Android 13+)
8_BrushWithText.kt - Applying brushes to text
9_BrushWithDrawScope.kt - Using brushes with Canvas drawing
10_BrushWithShapes.kt - Combining brushes with shapes
11_ComprehensiveExamples.kt - This file, showcasing all concepts

KEY CONCEPTS:
- Brushes determine how content is painted (colors, gradients, patterns)
- Can be used with Modifier.background(), TextStyle, and DrawScope
- Support different gradient types and custom shaders
- ColorStops control color distribution
- TileMode enables pattern repetition
- Work with any shape or drawing operation
*/

