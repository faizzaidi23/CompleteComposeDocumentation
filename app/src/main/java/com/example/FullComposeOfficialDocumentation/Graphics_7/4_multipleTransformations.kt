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
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
MULTIPLE TRANSFORMATIONS

To apply multiple transformations to your drawings use the DrawScope.withTransform function
This creates and applies a single transformation that combines all your desired changes

Using withTransform is more efficient than making nested calls to individual transformations
because all the transformations are performed together in a single operation
instead of Compose needing to calculate and save each of the nested transformations
*/

/*
Combining translation and rotation with withTransform

For example you can apply both a translation and a rotation to a rectangle
This is more efficient than nesting translate and rotate calls
*/

@Composable
fun WithTransformExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        withTransform({
            translate(left = size.width / 5F)
            rotate(degrees = 45F)
        }) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

/*
Combining scale rotation and translation

This example combines three transformations in a single withTransform block
*/

@Composable
fun MultipleTransformationsExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        withTransform({
            translate(left = size.width / 2, top = size.height / 2)
            rotate(degrees = 30f)
            scale(scaleX = 1.5f, scaleY = 1.5f)
        }) {
            drawRect(
                color = Color.Blue,
                topLeft = Offset(-50.dp.toPx(), -50.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(100.dp.toPx(), 100.dp.toPx())
            )
        }
    }
}

/*
Comparison nested transformations vs withTransform

This shows the difference between nested calls and withTransform
Both produce the same result but withTransform is more efficient
*/

@Composable
fun NestedTransformationsExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* Less efficient nested approach */
        translate(left = 50f) {
            scale(scale = 1.2f) {
                drawCircle(
                    color = Color.Red,
                    radius = 30.dp.toPx()
                )
            }
        }

        /* More efficient withTransform approach - same result */
        withTransform({
            translate(left = 150f)
            scale(scale = 1.2f)
        }) {
            drawCircle(
                color = Color.Green,
                radius = 30.dp.toPx()
            )
        }
    }
}

/*
Creating complex shapes with withTransform

This example creates a pattern using multiple withTransform blocks
*/

@Composable
fun ComplexPatternExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val center = Offset(size.width / 2, size.height / 2)

        for (i in 0 until 12) {
            withTransform({
                translate(left = center.x, top = center.y)
                rotate(degrees = i * 30f)
                translate(left = 0f, top = -60f)
            }) {
                drawCircle(
                    color = Color.Cyan,
                    radius = 10.dp.toPx()
                )
            }
        }
    }
}

/*
Animating with transformations

This example shows how transformations can be used for creating visual effects
Here we create a spiral pattern
*/

@Composable
fun SpiralPatternExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val center = Offset(size.width / 2, size.height / 2)

        for (i in 0 until 20) {
            withTransform({
                translate(left = center.x, top = center.y)
                rotate(degrees = i * 18f)
                translate(left = i * 3f, top = 0f)
                scale(scale = 1f + i * 0.05f)
            }) {
                drawCircle(
                    color = Color.Blue.copy(alpha = 1f - i * 0.04f),
                    radius = 5.dp.toPx()
                )
            }
        }
    }
}

/*
Combining transformations with inset

You can also combine inset with other transformations
*/

@Composable
fun TransformWithInsetExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        withTransform({
            inset(20f, 20f, 20f, 20f)
            rotate(degrees = 45f, pivot = Offset(size.width / 2, size.height / 2))
        }) {
            drawRect(
                color = Color.Magenta
            )
        }
    }
}

/*
Creating a flower pattern with transformations

This demonstrates how powerful withTransform can be for creating complex graphics
*/

@Composable
fun FlowerPatternExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        val center = Offset(size.width / 2, size.height / 2)
        val petalCount = 8

        for (i in 0 until petalCount) {
            withTransform({
                translate(left = center.x, top = center.y)
                rotate(degrees = i * (360f / petalCount))
            }) {
                drawOval(
                    color = Color(0xFFFF6B9D),
                    topLeft = Offset(-20.dp.toPx(), -60.dp.toPx()),
                    size = androidx.compose.ui.geometry.Size(40.dp.toPx(), 80.dp.toPx())
                )
            }
        }

        /* Draw center circle */
        drawCircle(
            color = Color(0xFFFFD700),
            radius = 20.dp.toPx(),
            center = center
        )
    }
}

/*
Display all multiple transformation examples
*/

@Composable
fun AllMultipleTransformationExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Multiple Transformations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Use withTransform for better performance when combining transformations",
            fontSize = 14.sp
        )

        Text(text = "Translation + Rotation", fontWeight = FontWeight.Medium)
        WithTransformExample()

        Text(text = "Scale + Rotation + Translation", fontWeight = FontWeight.Medium)
        MultipleTransformationsExample()

        Text(text = "Nested vs withTransform", fontWeight = FontWeight.Medium)
        NestedTransformationsExample()

        Text(text = "Complex Circular Pattern", fontWeight = FontWeight.Medium)
        ComplexPatternExample()

        Text(text = "Spiral Pattern", fontWeight = FontWeight.Medium)
        SpiralPatternExample()

        Text(text = "Transform with Inset", fontWeight = FontWeight.Medium)
        TransformWithInsetExample()

        Text(text = "Flower Pattern", fontWeight = FontWeight.Medium)
        FlowerPatternExample()
    }
}
