package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
BRUSH WITH DRAWSCOPE

Brushes can be used with DrawScope draw calls in Canvas
This allows you to draw custom shapes and paths with gradients and other brush effects

DrawScope provides various drawing methods:
- drawCircle(brush)
- drawRect(brush)
- drawRoundRect(brush)
- drawPath(brush)
- drawOval(brush)
- drawArc(brush)
- drawLine(brush)

All of these can accept a Brush instead of a solid Color
*/

/*
Circle with Gradient Brush

Draw a circle filled with a radial gradient
*/

@Composable
fun GradientCircleExample() {
    val brush = Brush.radialGradient(
        colors = listOf(
            Color(0xFFFFEB3B),
            Color(0xFFFF9800),
            Color(0xFFFF5722)
        )
    )

    Canvas(modifier = Modifier.size(200.dp)) {
        drawCircle(brush)
    }
}

/*
Rectangle with Linear Gradient

Draw a rectangle with a diagonal linear gradient
*/

@Composable
fun GradientRectExample() {
    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00BCD4),
            Color(0xFF3F51B5)
        )
    )

    Canvas(modifier = Modifier.size(200.dp)) {
        drawRect(brush)
    }
}

/*
Path with Gradient Stroke

Draw a custom path with a gradient stroke
*/

@Composable
fun GradientPathExample() {
    val brush = Brush.horizontalGradient(
        colors = listOf(
            Color.Red,
            Color.Yellow,
            Color.Green,
            Color.Blue
        )
    )

    Canvas(modifier = Modifier.size(200.dp)) {
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
            brush = brush,
            style = Stroke(width = 10f)
        )
    }
}

/*
Oval with Sweep Gradient

Draw an oval with a sweep gradient
*/

@Composable
fun GradientOvalExample() {
    val brush = Brush.sweepGradient(
        colors = listOf(
            Color.Magenta,
            Color.Cyan,
            Color.Yellow,
            Color.Magenta
        )
    )

    Canvas(modifier = Modifier.size(200.dp)) {
        drawOval(brush)
    }
}

/*
Arc with Gradient

Draw an arc (pie slice) with a gradient
*/

@Composable
fun GradientArcExample() {
    val brush = Brush.sweepGradient(
        colors = listOf(
            Color(0xFF4CAF50),
            Color(0xFF8BC34A),
            Color(0xFFCDDC39),
            Color(0xFFFFEB3B)
        )
    )

    Canvas(modifier = Modifier.size(200.dp)) {
        drawArc(
            brush = brush,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true
        )
    }
}

/*
Multiple Shapes with Different Brushes

Demonstrates drawing multiple shapes with different brush effects
*/

@Composable
fun MultipleShapesExample() {
    Canvas(modifier = Modifier.size(300.dp)) {
        // Background circle with radial gradient
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.White, Color.LightGray)
            ),
            radius = size.minDimension / 2
        )

        // Top left circle
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red, Color.Yellow),
                center = Offset(size.width * 0.3f, size.height * 0.3f),
                radius = 60f
            ),
            center = Offset(size.width * 0.3f, size.height * 0.3f),
            radius = 60f
        )

        // Top right circle
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Blue, Color.Cyan),
                center = Offset(size.width * 0.7f, size.height * 0.3f),
                radius = 60f
            ),
            center = Offset(size.width * 0.7f, size.height * 0.3f),
            radius = 60f
        )

        // Bottom center circle
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Green, Color(0xFFCDDC39)),
                center = Offset(size.width * 0.5f, size.height * 0.7f),
                radius = 60f
            ),
            center = Offset(size.width * 0.5f, size.height * 0.7f),
            radius = 60f
        )
    }
}

/*
Line with Gradient

Draw a thick line with a gradient brush
*/

@Composable
fun GradientLineExample() {
    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFE91E63),
            Color(0xFF9C27B0),
            Color(0xFF673AB7)
        ),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Canvas(modifier = Modifier.size(200.dp)) {
        drawLine(
            brush = brush,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height),
            strokeWidth = 20f
        )
    }
}

/*
All DrawScope Brush Examples Showcase

Displays various shapes drawn with brushes
*/

@Composable
fun DrawScopeBrushShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Brush with DrawScope",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Gradient Circle
        Text("Gradient Circle", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFEB3B),
                        Color(0xFFFF9800),
                        Color(0xFFFF5722)
                    )
                )
            )
        }

        // Gradient Rectangle
        Text("Gradient Rectangle", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Canvas(modifier = Modifier.size(200.dp)) {
            drawRect(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF00BCD4),
                        Color(0xFF3F51B5)
                    )
                )
            )
        }

        // Gradient Oval
        Text("Gradient Oval", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Canvas(modifier = Modifier.size(200.dp)) {
            drawOval(
                Brush.sweepGradient(
                    colors = listOf(
                        Color.Magenta,
                        Color.Cyan,
                        Color.Yellow,
                        Color.Magenta
                    )
                )
            )
        }

        // Gradient Arc
        Text("Gradient Arc", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Canvas(modifier = Modifier.size(200.dp)) {
            drawArc(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color(0xFF4CAF50),
                        Color(0xFF8BC34A),
                        Color(0xFFCDDC39),
                        Color(0xFFFFEB3B)
                    )
                ),
                startAngle = 0f,
                sweepAngle = 270f,
                useCenter = true
            )
        }

        // Gradient Path
        Text("Gradient Path", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Canvas(modifier = Modifier.size(200.dp)) {
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
                    colors = listOf(
                        Color.Red,
                        Color.Yellow,
                        Color.Green,
                        Color.Blue
                    )
                ),
                style = Stroke(width = 10f)
            )
        }
    }
}
