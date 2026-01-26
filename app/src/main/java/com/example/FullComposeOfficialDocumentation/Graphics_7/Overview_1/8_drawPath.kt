package com.example.FullComposeOfficialDocumentation.Graphics_7.Overview_1

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

/*
DRAW PATH

A path is a series of mathematical instructions that result in a drawing once executed
DrawScope can draw a path using the DrawScope.drawPath method

For example say you wanted to draw a triangle
You can generate a path with functions such as lineTo and moveTo using the size of the drawing area
Then call drawPath with this newly created path to get a triangle
*/

/*
Drawing a triangle with Path

This example creates a triangle using path operations
moveTo moves to a starting point without drawing
lineTo draws a line from current position to the specified point
close connects the last point back to the first point
*/

@Composable
fun DrawTriangleExample() {
    Spacer(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val path = Path()
                path.moveTo(0f, 0f)
                path.lineTo(size.width / 2f, size.height / 2f)
                path.lineTo(size.width, 0f)
                path.close()

                onDrawBehind {
                    drawPath(path, Color.Magenta, style = Stroke(width = 10f))
                }
            }
    )
}

/*
Drawing a filled triangle

The same triangle but filled instead of just an outline
*/

@Composable
fun DrawFilledTriangleExample() {
    Spacer(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val path = Path()
                path.moveTo(size.width / 2f, 0f)
                path.lineTo(size.width, size.height)
                path.lineTo(0f, size.height)
                path.close()

                onDrawBehind {
                    drawPath(path, Color.Blue, style = Fill)
                }
            }
    )
}

/*
Drawing a star with Path

This creates a 5-pointed star using path operations
*/

@Composable
fun DrawStarExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val outerRadius = size.minDimension / 2f * 0.9f
            val innerRadius = outerRadius * 0.4f

            for (i in 0 until 10) {
                val angle = (i * 36 - 90) * (Math.PI / 180).toFloat()
                val radius = if (i % 2 == 0) outerRadius else innerRadius
                val x = centerX + radius * cos(angle)
                val y = centerY + radius * sin(angle)

                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }

        drawPath(path, Color(0xFFFFD700))
    }
}

/*
Drawing a heart shape with Path

This demonstrates creating a complex shape using curves
cubicTo creates a cubic bezier curve
*/

@Composable
fun DrawHeartExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            val width = size.width
            val height = size.height

            moveTo(width / 2f, height / 5f)

            cubicTo(
                5f * width / 14f, 0f,
                0f, height / 15f,
                width / 28f, 2f * height / 5f
            )

            cubicTo(
                width / 14f, 2f * height / 3f,
                3f * width / 7f, 5f * height / 6f,
                width / 2f, height
            )

            cubicTo(
                4f * width / 7f, 5f * height / 6f,
                13f * width / 14f, 2f * height / 3f,
                27f * width / 28f, 2f * height / 5f
            )

            cubicTo(
                width, height / 15f,
                9f * width / 14f, 0f,
                width / 2f, height / 5f
            )
        }

        drawPath(path, Color(0xFFE91E63))
    }
}

/*
Drawing a zigzag pattern with Path

This creates a zigzag line using lineTo
*/

@Composable
fun DrawZigzagExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            val points = 10
            val stepX = size.width / points

            moveTo(0f, size.height / 2f)

            for (i in 1..points) {
                val y = if (i % 2 == 0) size.height / 3f else 2f * size.height / 3f
                lineTo(i * stepX, y)
            }
        }

        drawPath(path, Color.Blue, style = Stroke(width = 4.dp.toPx()))
    }
}

/*
Drawing a wave pattern with Path

This uses quadraticBezierTo to create smooth curves
*/

@Composable
fun DrawWaveExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            val waveCount = 4
            val waveWidth = size.width / waveCount
            val waveHeight = size.height / 4f

            moveTo(0f, size.height / 2f)

            for (i in 0 until waveCount) {
                quadraticBezierTo(
                    waveWidth * i + waveWidth / 2f,
                    size.height / 2f - waveHeight,
                    waveWidth * (i + 1),
                    size.height / 2f
                )
            }
        }

        drawPath(path, Color(0xFF00BCD4), style = Stroke(width = 5.dp.toPx()))
    }
}

/*
Drawing a complex polygon

This creates a hexagon using path operations
*/

@Composable
fun DrawHexagonExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            val centerX = size.width / 2f
            val centerY = size.height / 2f
            val radius = size.minDimension / 2f * 0.8f
            val sides = 6

            for (i in 0 until sides) {
                val angle = (i * 60 - 90) * (Math.PI / 180).toFloat()
                val x = centerX + radius * cos(angle)
                val y = centerY + radius * sin(angle)

                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }

        drawPath(path, Color(0xFF9C27B0), style = Stroke(width = 6.dp.toPx()))
    }
}

/*
Drawing an arrow with Path

This demonstrates creating a practical shape
*/

@Composable
fun DrawArrowExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            /* Arrow shaft */
            moveTo(20.dp.toPx(), size.height / 2f)
            lineTo(140.dp.toPx(), size.height / 2f)

            /* Arrow head */
            lineTo(140.dp.toPx(), size.height / 2f - 30.dp.toPx())
            lineTo(180.dp.toPx(), size.height / 2f)
            lineTo(140.dp.toPx(), size.height / 2f + 30.dp.toPx())
            lineTo(140.dp.toPx(), size.height / 2f)
        }

        drawPath(path, Color(0xFF4CAF50), style = Stroke(width = 8.dp.toPx()))
    }
}

/*
Creating a filled and stroked path

You can draw the same path twice with different styles
*/

@Composable
fun DrawFilledAndStrokedExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val path = Path().apply {
            addOval(
                Rect(
                    left = 40.dp.toPx(),
                    top = 40.dp.toPx(),
                    right = 160.dp.toPx(),
                    bottom = 160.dp.toPx()
                )
            )
        }

        /* Draw filled */
        drawPath(path, Color(0xFFFFEB3B))

        /* Draw stroke on top */
        drawPath(path, Color(0xFFFF5722), style = Stroke(width = 4.dp.toPx()))
    }
}

/*
Display all path drawing examples
*/

@Composable
fun AllPathDrawingExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Path Drawing Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Use Path with moveTo, lineTo, and close to create custom shapes",
            fontSize = 14.sp
        )

        Text(text = "Triangle (Stroke)", fontWeight = FontWeight.Medium)
        DrawTriangleExample()

        Text(text = "Triangle (Filled)", fontWeight = FontWeight.Medium)
        DrawFilledTriangleExample()

        Text(text = "5-Pointed Star", fontWeight = FontWeight.Medium)
        DrawStarExample()

        Text(text = "Heart Shape", fontWeight = FontWeight.Medium)
        DrawHeartExample()

        Text(text = "Zigzag Pattern", fontWeight = FontWeight.Medium)
        DrawZigzagExample()

        Text(text = "Wave Pattern", fontWeight = FontWeight.Medium)
        DrawWaveExample()

        Text(text = "Hexagon", fontWeight = FontWeight.Medium)
        DrawHexagonExample()

        Text(text = "Arrow", fontWeight = FontWeight.Medium)
        DrawArrowExample()

        Text(text = "Filled and Stroked", fontWeight = FontWeight.Medium)
        DrawFilledAndStrokedExample()
    }
}

