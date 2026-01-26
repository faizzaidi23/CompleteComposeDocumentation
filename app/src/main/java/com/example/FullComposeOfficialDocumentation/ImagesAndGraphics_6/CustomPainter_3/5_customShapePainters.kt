package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.CustomPainter_3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

/*
Custom painters do not have to just draw images
You can create painters that draw custom shapes graphics and patterns
This is useful for creating custom icons backgrounds or decorative elements
*/

/*
Custom painter that draws a circle with a border
This painter draws a filled circle with a stroke border
You can customize the color size and border width
*/

class CirclePainter(
    private val fillColor: Color,
    private val strokeColor: Color,
    private val strokeWidth: Float = 4f
) : Painter() {

    override fun DrawScope.onDraw() {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val center = Offset(size.width / 2f, size.height / 2f)

        /* Draw filled circle */
        drawCircle(
            color = fillColor,
            radius = radius,
            center = center
        )

        /* Draw stroke border */
        drawCircle(
            color = strokeColor,
            radius = radius,
            center = center,
            style = Stroke(width = strokeWidth)
        )
    }

    override val intrinsicSize: Size
        get() = Size(200f, 200f)
}

/*
Custom painter that draws a gradient circle
This creates a radial gradient effect
*/

class GradientCirclePainter(
    private val centerColor: Color,
    private val edgeColor: Color
) : Painter() {

    override fun DrawScope.onDraw() {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val center = Offset(size.width / 2f, size.height / 2f)

        /* Draw multiple circles with decreasing opacity for gradient effect */
        for (i in 0..20) {
            val currentRadius = radius * (1f - i / 20f)
            val alpha = (i / 20f)
            val currentColor = Color(
                red = centerColor.red * (1f - alpha) + edgeColor.red * alpha,
                green = centerColor.green * (1f - alpha) + edgeColor.green * alpha,
                blue = centerColor.blue * (1f - alpha) + edgeColor.blue * alpha,
                alpha = 1f
            )

            drawCircle(
                color = currentColor,
                radius = currentRadius,
                center = center
            )
        }
    }

    override val intrinsicSize: Size
        get() = Size(200f, 200f)
}

/*
Custom painter that draws a star shape
This demonstrates how to draw custom paths
*/

class StarPainter(
    private val fillColor: Color,
    private val points: Int = 5
) : Painter() {

    override fun DrawScope.onDraw() {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radius = size.minDimension / 2f
        val innerRadius = radius * 0.4f

        val path = androidx.compose.ui.graphics.Path()

        /* Calculate star points */
        for (i in 0 until points * 2) {
            val angle = (i * Math.PI / points).toFloat()
            val r = if (i % 2 == 0) radius else innerRadius
            val x = centerX + r * cos(angle - Math.PI.toFloat() / 2f)
            val y = centerY + r * sin(angle - Math.PI.toFloat() / 2f)

            if (i == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        path.close()

        drawPath(path, fillColor)
    }

    override val intrinsicSize: Size
        get() = Size(200f, 200f)
}

/*
Custom painter that draws a checkerboard pattern
Useful for backgrounds or showing transparency
*/

class CheckerboardPainter(
    private val color1: Color,
    private val color2: Color,
    private val squares: Int = 8
) : Painter() {

    override fun DrawScope.onDraw() {
        val squareSize = size.width / squares

        for (row in 0 until squares) {
            for (col in 0 until squares) {
                val color = if ((row + col) % 2 == 0) color1 else color2
                drawRect(
                    color = color,
                    topLeft = Offset(col * squareSize, row * squareSize),
                    size = Size(squareSize, squareSize)
                )
            }
        }
    }

    override val intrinsicSize: Size
        get() = Size(200f, 200f)
}

/*
Custom painter that draws concentric circles
Creates a target or ripple effect
*/

class ConcentricCirclesPainter(
    private val color: Color,
    private val rings: Int = 5
) : Painter() {

    override fun DrawScope.onDraw() {
        val maxRadius = size.minDimension / 2f
        val center = Offset(size.width / 2f, size.height / 2f)

        for (i in 0 until rings) {
            val radius = maxRadius * (rings - i) / rings
            val alpha = 1f - (i.toFloat() / rings)

            drawCircle(
                color = color.copy(alpha = alpha),
                radius = radius,
                center = center,
                style = Stroke(width = maxRadius / rings)
            )
        }
    }

    override val intrinsicSize: Size
        get() = Size(200f, 200f)
}

/*
Examples using the custom shape painters
*/

@Composable
fun CirclePainterExample(){
    val painter = remember {
        CirclePainter(
            fillColor = Color(0xFF2196F3),
            strokeColor = Color(0xFF1976D2),
            strokeWidth = 8f
        )
    }

    Image(
        painter = painter,
        contentDescription = "Circle",
        modifier = Modifier.size(150.dp)
    )
}

@Composable
fun GradientCirclePainterExample(){
    val painter = remember {
        GradientCirclePainter(
            centerColor = Color(0xFFFFEB3B),
            edgeColor = Color(0xFFFF5722)
        )
    }

    Image(
        painter = painter,
        contentDescription = "Gradient Circle",
        modifier = Modifier.size(150.dp)
    )
}

@Composable
fun StarPainterExample(){
    val painter = remember {
        StarPainter(
            fillColor = Color(0xFFFFD700),
            points = 5
        )
    }

    Image(
        painter = painter,
        contentDescription = "Star",
        modifier = Modifier.size(150.dp)
    )
}

@Composable
fun CheckerboardPainterExample(){
    val painter = remember {
        CheckerboardPainter(
            color1 = Color(0xFFCCCCCC),
            color2 = Color(0xFFFFFFFF),
            squares = 8
        )
    }

    Image(
        painter = painter,
        contentDescription = "Checkerboard",
        modifier = Modifier.size(150.dp)
    )
}

@Composable
fun ConcentricCirclesPainterExample(){
    val painter = remember {
        ConcentricCirclesPainter(
            color = Color(0xFF9C27B0),
            rings = 6
        )
    }

    Image(
        painter = painter,
        contentDescription = "Concentric Circles",
        modifier = Modifier.size(150.dp)
    )
}

/*
Display all custom shape painter examples
*/

@Composable
fun AllCustomShapePainterExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Custom Shape Painters",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Painters can draw shapes and graphics not just images",
            fontSize = 14.sp
        )

        Text(text = "Circle with Border", fontWeight = FontWeight.Medium)
        CirclePainterExample()

        Text(text = "Gradient Circle", fontWeight = FontWeight.Medium)
        GradientCirclePainterExample()

        Text(text = "Star Shape", fontWeight = FontWeight.Medium)
        StarPainterExample()

        Text(text = "Checkerboard Pattern", fontWeight = FontWeight.Medium)
        CheckerboardPainterExample()

        Text(text = "Concentric Circles", fontWeight = FontWeight.Medium)
        ConcentricCirclesPainterExample()
    }
}

