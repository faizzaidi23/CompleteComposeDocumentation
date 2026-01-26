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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
BASIC TRANSFORMATIONS

DrawScope offers transformations to change where or how the drawing commands are executed

Note: These transformations only apply in the draw phase of the Composable lifecycle
Any changes to size or position will not change the layout size and position
Elements may draw over other elements if they are moved out of their layout size and position
*/

/*
SCALE TRANSFORMATION

Use DrawScope.scale to increase the size of your drawing operations by a factor
Operations like scale apply to all drawing operations within the corresponding lambda

For example you can increase scaleX 10 times and scaleY 15 times
This creates non-uniform scaling that stretches the shape differently in each direction
*/

@Composable
fun ScaleExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        scale(scaleX = 10f, scaleY = 15f) {
            drawCircle(Color.Blue, radius = 20.dp.toPx())
        }
    }
}

/*
Uniform scaling

This scales the drawing equally in both directions
*/

@Composable
fun UniformScaleExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        scale(scale = 2f) {
            drawCircle(
                color = Color.Green,
                radius = 30.dp.toPx()
            )
        }
    }
}

/*
Scale with pivot point

You can specify a pivot point for the scaling
The pivot point is the point around which the scaling occurs
*/

@Composable
fun ScaleWithPivotExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* Scale around the top-left corner */
        scale(
            scale = 2f,
            pivot = Offset(0f, 0f)
        ) {
            drawRect(
                color = Color.Red,
                size = size / 4f
            )
        }
    }
}

/*
TRANSLATE TRANSFORMATION

Use DrawScope.translate to move your drawing operations up down left or right
For example you can move the drawing 100 px to the right and 300 px up

Note: Positive left values move right negative values move left
Positive top values move down negative values move up
*/

@Composable
fun TranslateExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = 100f, top = -300f) {
            drawCircle(Color.Blue, radius = 200.dp.toPx())
        }
    }
}

/*
Translate to center

This example translates a rectangle to the center of the canvas
*/

@Composable
fun TranslateToCenterExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val rectSize = 50.dp.toPx()

        translate(
            left = size.width / 2 - rectSize / 2,
            top = size.height / 2 - rectSize / 2
        ) {
            drawRect(
                color = Color.Magenta,
                size = Size(rectSize, rectSize)
            )
        }
    }
}

/*
Multiple translate operations

You can nest translate operations to create cumulative translations
*/

@Composable
fun MultipleTranslateExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        translate(left = 50f, top = 50f) {
            drawCircle(color = Color.Red, radius = 20.dp.toPx())

            translate(left = 100f, top = 0f) {
                drawCircle(color = Color.Green, radius = 20.dp.toPx())
            }
        }
    }
}

/*
ROTATE TRANSFORMATION

Use DrawScope.rotate to rotate your drawing operations around a pivot point
For example you can rotate a rectangle 45 degrees
*/

@Composable
fun RotateExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(degrees = 45F) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

/*
Rotate around center

This example rotates a rectangle around the center of the canvas
*/

@Composable
fun RotateAroundCenterExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        rotate(
            degrees = 30f,
            pivot = Offset(size.width / 2, size.height / 2)
        ) {
            drawRect(
                color = Color.Blue,
                topLeft = Offset(size.width / 4, size.height / 4),
                size = size / 2f
            )
        }
    }
}

/*
Rotate multiple shapes at different angles

This creates a pinwheel effect with multiple rotations
*/

@Composable
fun RotatePinwheelExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val center = Offset(size.width / 2, size.height / 2)

        for (i in 0 until 8) {
            rotate(degrees = i * 45f, pivot = center) {
                drawRect(
                    color = Color.Red,
                    topLeft = Offset(center.x, center.y - 80.dp.toPx()),
                    size = Size(40.dp.toPx(), 80.dp.toPx())
                )
            }
        }
    }
}

/*
INSET TRANSFORMATION

Use DrawScope.inset to adjust the default parameters of the current DrawScope
This changes the drawing boundaries and translates the drawings accordingly
This effectively adds padding to the drawing commands
*/

@Composable
fun InsetExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        inset(horizontal = 50f, vertical = 30f) {
            drawRect(color = Color.Green, size = canvasQuadrantSize)
        }
    }
}

/*
Inset with all sides specified

You can specify different insets for each side
*/

@Composable
fun InsetAllSidesExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        inset(left = 10f, top = 20f, right = 30f, bottom = 40f) {
            drawRect(color = Color.Cyan)
        }
    }
}

/*
Nested insets

You can nest inset operations to create multiple layers of padding
*/

@Composable
fun NestedInsetsExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        inset(20f, 20f, 20f, 20f) {
            drawRect(color = Color.Red)

            inset(20f, 20f, 20f, 20f) {
                drawRect(color = Color.Green)

                inset(20f, 20f, 20f, 20f) {
                    drawRect(color = Color.Blue)
                }
            }
        }
    }
}

/*
Display all transformation examples
*/

@Composable
fun AllTransformationExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Basic Transformations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Scale Transformations",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(text = "Non-uniform Scale", fontWeight = FontWeight.Medium)
        ScaleExample()

        Text(text = "Uniform Scale", fontWeight = FontWeight.Medium)
        UniformScaleExample()

        Text(text = "Scale with Pivot", fontWeight = FontWeight.Medium)
        ScaleWithPivotExample()

        Text(
            text = "Translate Transformations",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(text = "Basic Translate", fontWeight = FontWeight.Medium)
        TranslateExample()

        Text(text = "Translate to Center", fontWeight = FontWeight.Medium)
        TranslateToCenterExample()

        Text(text = "Multiple Translates", fontWeight = FontWeight.Medium)
        MultipleTranslateExample()

        Text(
            text = "Rotate Transformations",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(text = "Basic Rotate (45°)", fontWeight = FontWeight.Medium)
        RotateExample()

        Text(text = "Rotate Around Center", fontWeight = FontWeight.Medium)
        RotateAroundCenterExample()

        Text(text = "Rotate Pinwheel", fontWeight = FontWeight.Medium)
        RotatePinwheelExample()

        Text(
            text = "Inset Transformations",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(text = "Basic Inset", fontWeight = FontWeight.Medium)
        InsetExample()

        Text(text = "Inset All Sides", fontWeight = FontWeight.Medium)
        InsetAllSidesExample()

        Text(text = "Nested Insets", fontWeight = FontWeight.Medium)
        NestedInsetsExample()
    }
}
