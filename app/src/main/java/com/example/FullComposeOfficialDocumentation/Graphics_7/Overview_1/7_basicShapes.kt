package com.example.FullComposeOfficialDocumentation.Graphics_7.Overview_1

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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
DRAW BASIC SHAPES

There are many shape drawing functions on DrawScope
To draw a shape use one of the predefined draw functions

This file demonstrates all the basic shape drawing functions available
*/

/*
Draw Circle

drawCircle draws a filled circle by default
You can also draw just the outline using Stroke style
*/

@Composable
fun DrawCircleExample() {
    val purpleColor = Color(0xFFBA68C8)

    Canvas(
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp),
        onDraw = {
            drawCircle(purpleColor)
        }
    )
}

/*
Draw circle with stroke outline

Using Stroke style draws only the outline of the circle
*/

@Composable
fun DrawCircleStrokeExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawCircle(
            color = Color.Blue,
            radius = 80.dp.toPx(),
            style = Stroke(width = 4.dp.toPx())
        )
    }
}

/*
Draw Rectangle

drawRect draws a filled rectangle by default
*/

@Composable
fun DrawRectExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawRect(
            color = Color.Red,
            topLeft = Offset(20.dp.toPx(), 20.dp.toPx()),
            size = Size(160.dp.toPx(), 160.dp.toPx())
        )
    }
}

/*
Draw rectangle with stroke

Rectangle with only an outline
*/

@Composable
fun DrawRectStrokeExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawRect(
            color = Color.Green,
            style = Stroke(width = 5.dp.toPx())
        )
    }
}

/*
Draw Rounded Rectangle

drawRoundRect draws a rectangle with rounded corners
*/

@Composable
fun DrawRoundedRectExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawRoundRect(
            color = Color.Magenta,
            topLeft = Offset(20.dp.toPx(), 20.dp.toPx()),
            size = Size(160.dp.toPx(), 160.dp.toPx()),
            cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
        )
    }
}

/*
Draw rounded rectangle with different corner radii

You can specify different corner radius for x and y
*/

@Composable
fun DrawRoundedRectDifferentRadiiExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawRoundRect(
            color = Color.Cyan,
            cornerRadius = CornerRadius(x = 40.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = 3.dp.toPx())
        )
    }
}

/*
Draw Line

drawLine draws a straight line from start to end point
*/

@Composable
fun DrawLineExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawLine(
            color = Color.Blue,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height),
            strokeWidth = 4.dp.toPx()
        )
    }
}

/*
Draw multiple lines

You can draw multiple lines to create patterns or grids
*/

@Composable
fun DrawMultipleLinesExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* Horizontal lines */
        for (i in 0..5) {
            drawLine(
                color = Color.Red,
                start = Offset(0f, i * 40.dp.toPx()),
                end = Offset(size.width, i * 40.dp.toPx()),
                strokeWidth = 2.dp.toPx()
            )
        }

        /* Vertical lines */
        for (i in 0..5) {
            drawLine(
                color = Color.Blue,
                start = Offset(i * 40.dp.toPx(), 0f),
                end = Offset(i * 40.dp.toPx(), size.height),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}

/*
Draw Oval

drawOval draws an ellipse shape
*/

@Composable
fun DrawOvalExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawOval(
            color = Color(0xFFFFB74D),
            topLeft = Offset(20.dp.toPx(), 40.dp.toPx()),
            size = Size(160.dp.toPx(), 120.dp.toPx())
        )
    }
}

/*
Draw oval with stroke

Oval with just an outline
*/

@Composable
fun DrawOvalStrokeExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawOval(
            color = Color(0xFF9C27B0),
            style = Stroke(width = 6.dp.toPx())
        )
    }
}

/*
Draw Arc

drawArc draws a portion of an oval
You specify the start angle and sweep angle in degrees
*/

@Composable
fun DrawArcExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawArc(
            color = Color(0xFF4CAF50),
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            topLeft = Offset(20.dp.toPx(), 20.dp.toPx()),
            size = Size(160.dp.toPx(), 160.dp.toPx())
        )
    }
}

/*
Draw arc without center

When useCenter is false the arc is drawn as a curved line
*/

@Composable
fun DrawArcNoCenterExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawArc(
            color = Color(0xFFE91E63),
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(width = 8.dp.toPx())
        )
    }
}

/*
Draw pie chart using arcs

This shows a practical use of arcs to create a pie chart
*/

@Composable
fun DrawPieChartExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val colors = listOf(
            Color(0xFFFF6B6B),
            Color(0xFF4ECDC4),
            Color(0xFF45B7D1),
            Color(0xFFFFA07A)
        )
        val values = listOf(90f, 120f, 80f, 70f)
        var startAngle = 0f

        values.forEachIndexed { index, value ->
            drawArc(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = value,
                useCenter = true,
                topLeft = Offset(20.dp.toPx(), 20.dp.toPx()),
                size = Size(160.dp.toPx(), 160.dp.toPx())
            )
            startAngle += value
        }
    }
}

/*
Draw Points

drawPoints draws individual points or connects them
You can use different PointMode options
*/

@Composable
fun DrawPointsExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val points = listOf(
            Offset(50.dp.toPx(), 50.dp.toPx()),
            Offset(100.dp.toPx(), 80.dp.toPx()),
            Offset(150.dp.toPx(), 40.dp.toPx()),
            Offset(120.dp.toPx(), 120.dp.toPx())
        )

        /* Draw individual points */
        drawPoints(
            points = points,
            pointMode = PointMode.Points,
            color = Color.Red,
            strokeWidth = 10.dp.toPx()
        )
    }
}

/*
Draw points as lines

PointMode.Lines connects pairs of points with lines
*/

@Composable
fun DrawPointsLinesExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val points = listOf(
            Offset(20.dp.toPx(), 20.dp.toPx()),
            Offset(180.dp.toPx(), 20.dp.toPx()),
            Offset(180.dp.toPx(), 180.dp.toPx()),
            Offset(20.dp.toPx(), 180.dp.toPx())
        )

        drawPoints(
            points = points,
            pointMode = PointMode.Lines,
            color = Color.Blue,
            strokeWidth = 3.dp.toPx()
        )
    }
}

/*
Draw points as polygon

PointMode.Polygon connects all points in sequence
*/

@Composable
fun DrawPointsPolygonExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val points = listOf(
            Offset(100.dp.toPx(), 20.dp.toPx()),
            Offset(170.dp.toPx(), 80.dp.toPx()),
            Offset(140.dp.toPx(), 160.dp.toPx()),
            Offset(60.dp.toPx(), 160.dp.toPx()),
            Offset(30.dp.toPx(), 80.dp.toPx())
        )

        drawPoints(
            points = points,
            pointMode = PointMode.Polygon,
            color = Color(0xFFFF9800),
            strokeWidth = 4.dp.toPx()
        )
    }
}

/*
Display all basic shape examples
*/

@Composable
fun AllBasicShapeExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Basic Shape Drawing",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Draw Circle (Filled)", fontWeight = FontWeight.Medium)
        DrawCircleExample()

        Text(text = "Draw Circle (Stroke)", fontWeight = FontWeight.Medium)
        DrawCircleStrokeExample()

        Text(text = "Draw Rectangle (Filled)", fontWeight = FontWeight.Medium)
        DrawRectExample()

        Text(text = "Draw Rectangle (Stroke)", fontWeight = FontWeight.Medium)
        DrawRectStrokeExample()

        Text(text = "Draw Rounded Rectangle", fontWeight = FontWeight.Medium)
        DrawRoundedRectExample()

        Text(text = "Rounded Rect Different Radii", fontWeight = FontWeight.Medium)
        DrawRoundedRectDifferentRadiiExample()

        Text(text = "Draw Line", fontWeight = FontWeight.Medium)
        DrawLineExample()

        Text(text = "Draw Multiple Lines (Grid)", fontWeight = FontWeight.Medium)
        DrawMultipleLinesExample()

        Text(text = "Draw Oval (Filled)", fontWeight = FontWeight.Medium)
        DrawOvalExample()

        Text(text = "Draw Oval (Stroke)", fontWeight = FontWeight.Medium)
        DrawOvalStrokeExample()

        Text(text = "Draw Arc with Center", fontWeight = FontWeight.Medium)
        DrawArcExample()

        Text(text = "Draw Arc without Center", fontWeight = FontWeight.Medium)
        DrawArcNoCenterExample()

        Text(text = "Pie Chart using Arcs", fontWeight = FontWeight.Medium)
        DrawPieChartExample()

        Text(text = "Draw Points", fontWeight = FontWeight.Medium)
        DrawPointsExample()

        Text(text = "Draw Points as Lines", fontWeight = FontWeight.Medium)
        DrawPointsLinesExample()

        Text(text = "Draw Points as Polygon", fontWeight = FontWeight.Medium)
        DrawPointsPolygonExample()
    }
}

