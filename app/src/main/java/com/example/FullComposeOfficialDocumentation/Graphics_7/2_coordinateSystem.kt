package com.example.FullComposeOfficialDocumentation.Graphics_7

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
COORDINATE SYSTEM IN COMPOSE

To draw something on screen you need to know the offset (x and y) and size of your item
With many of the draw methods on DrawScope the position and size are provided by default parameter values

The default parameters generally position the item at the [0, 0] point on the canvas
and provide a default size that fills the entire drawing area

To adjust the size and position of your item you need to understand the coordinate system in Compose
*/

/*
The origin of the coordinate system [0,0] is at the top leftmost pixel in the drawing area
x increases as it moves right
y increases as it moves downwards

Note: All drawing operations are performed using pixel sizing
To ensure consistent sizing of your items across different device densities and screen sizes
be sure to either convert from dp using .toPx or work in fractions of the size
*/

/*
Drawing at the origin [0, 0]

By default shapes are drawn at the top left corner [0, 0]
This example draws a circle at the default position
*/

@Composable
fun DrawAtOriginExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawCircle(
            color = Color.Blue,
            radius = 50.dp.toPx(),
            /* Default center is at [0, 0] but circle extends from center */
            /* so it appears partially off screen */
        )
    }
}

/*
Drawing with custom offset

You can specify the exact position using Offset
Offset takes x and y coordinates in pixels
*/

@Composable
fun DrawWithOffsetExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawCircle(
            color = Color.Red,
            radius = 50.dp.toPx(),
            center = Offset(x = 100.dp.toPx(), y = 100.dp.toPx())
        )
    }
}

/*
Drawing a diagonal line from top-right to bottom-left

If you want to draw a diagonal line from the top-right corner to the bottom-left corner
you can use DrawScope.drawLine function
and specify a start and end offset with the corresponding x and y positions
*/

@Composable
fun DiagonalLineExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Blue,
            strokeWidth = 5.dp.toPx()
        )
    }
}

/*
Understanding size in DrawScope

The size field in DrawScope gives you the width and height of the drawing area
You can use this to position elements relative to the canvas size
*/

@Composable
fun UsingSizeExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* Draw a circle in the center of the canvas */
        drawCircle(
            color = Color.Green,
            radius = 30.dp.toPx(),
            center = Offset(x = size.width / 2, y = size.height / 2)
        )
    }
}

/*
Drawing at corners

This example shows how to draw at different corners using the coordinate system
*/

@Composable
fun DrawAtCornersExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val radius = 20.dp.toPx()

        /* Top-left corner [0, 0] */
        drawCircle(
            color = Color.Red,
            radius = radius,
            center = Offset(x = 0f, y = 0f)
        )

        /* Top-right corner [width, 0] */
        drawCircle(
            color = Color.Blue,
            radius = radius,
            center = Offset(x = size.width, y = 0f)
        )

        /* Bottom-left corner [0, height] */
        drawCircle(
            color = Color.Green,
            radius = radius,
            center = Offset(x = 0f, y = size.height)
        )

        /* Bottom-right corner [width, height] */
        drawCircle(
            color = Color.Yellow,
            radius = radius,
            center = Offset(x = size.width, y = size.height)
        )
    }
}

/*
Working in fractions of size

Instead of using fixed pixel values you can work in fractions of the canvas size
This makes your drawings responsive to different screen sizes
*/

@Composable
fun FractionalSizingExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* Draw a rectangle that takes up 1/4 of the canvas */
        drawRect(
            color = Color.Magenta,
            topLeft = Offset(x = size.width / 4, y = size.height / 4),
            size = size / 2f
        )
    }
}

/*
Drawing a grid to visualize coordinate system

This example draws a grid to help visualize the coordinate system
*/

@Composable
fun CoordinateGridExample() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val gridSize = 10
        val cellWidth = size.width / gridSize
        val cellHeight = size.height / gridSize

        /* Draw vertical lines */
        for (i in 0..gridSize) {
            drawLine(
                color = Color.LightGray,
                start = Offset(x = i * cellWidth, y = 0f),
                end = Offset(x = i * cellWidth, y = size.height),
                strokeWidth = 1.dp.toPx()
            )
        }

        /* Draw horizontal lines */
        for (i in 0..gridSize) {
            drawLine(
                color = Color.LightGray,
                start = Offset(x = 0f, y = i * cellHeight),
                end = Offset(x = size.width, y = i * cellHeight),
                strokeWidth = 1.dp.toPx()
            )
        }

        /* Draw a red dot at origin */
        drawCircle(
            color = Color.Red,
            radius = 8.dp.toPx(),
            center = Offset(0f, 0f)
        )
    }
}

/*
Converting from dp to pixels

All drawing operations use pixel sizing
To ensure consistent sizing across different device densities
convert from dp using .toPx
*/

@Composable
fun DpToPixelExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* Using dp.toPx() for consistent sizing */
        val circleSizeDp = 50.dp
        val circleSizePx = circleSizeDp.toPx()

        drawCircle(
            color = Color.Cyan,
            radius = circleSizePx,
            center = Offset(x = size.width / 2, y = size.height / 2)
        )
    }
}

/*
Display all coordinate system examples
*/

@Composable
fun AllCoordinateSystemExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Coordinate System Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Origin is at top-left [0,0]. X increases right, Y increases down.",
            fontSize = 14.sp
        )

        Text(text = "Draw at Origin", fontWeight = FontWeight.Medium)
        DrawAtOriginExample()

        Text(text = "Draw with Custom Offset", fontWeight = FontWeight.Medium)
        DrawWithOffsetExample()

        Text(text = "Diagonal Line (top-right to bottom-left)", fontWeight = FontWeight.Medium)
        DiagonalLineExample()

        Text(text = "Circle at Center Using Size", fontWeight = FontWeight.Medium)
        UsingSizeExample()

        Text(text = "Draw at All Four Corners", fontWeight = FontWeight.Medium)
        DrawAtCornersExample()

        Text(text = "Fractional Sizing", fontWeight = FontWeight.Medium)
        FractionalSizingExample()

        Text(text = "Coordinate Grid", fontWeight = FontWeight.Medium)
        CoordinateGridExample()

        Text(text = "DP to Pixel Conversion", fontWeight = FontWeight.Medium)
        DpToPixelExample()
    }
}

