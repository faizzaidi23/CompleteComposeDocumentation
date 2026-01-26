package com.example.FullComposeOfficialDocumentation.Graphics_7.Overview_1

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
ACCESSING CANVAS OBJECT

With DrawScope you do not have direct access to a Canvas object
You can use DrawScope.drawIntoCanvas to get access to the Canvas object itself
that you can call functions on

For example if you have a custom Drawable that you would like to draw onto the canvas
you can access the canvas and call Drawable.draw passing in the Canvas object
*/

/*
Drawing a ShapeDrawable on Canvas

This example shows how to use drawIntoCanvas to access the native canvas
and draw an Android Drawable on it

ShapeDrawable is a view-based drawable that we can use in Compose
by accessing the native canvas
*/

@Composable
fun DrawShapeDrawableExample() {
    val drawable = ShapeDrawable(OvalShape())

    Spacer(
        modifier = Modifier
            .size(200.dp)
            .drawWithContent {
                drawIntoCanvas { canvas ->
                    drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
                    drawable.draw(canvas.nativeCanvas)
                }
            }
    )
}

/*
Drawing a custom Android Drawable

This shows how to draw any Android Drawable
The drawable is positioned and sized using setBounds
then drawn on the native canvas
*/

@Composable
fun DrawCustomDrawableExample() {
    val drawable = ShapeDrawable(OvalShape()).apply {
        paint.color = Color.BLUE
    }

    Canvas(modifier = Modifier.size(200.dp)) {
        drawIntoCanvas { canvas ->
            /* Set the bounds for the drawable */
            drawable.setBounds(
                20.dp.toPx().toInt(),
                20.dp.toPx().toInt(),
                (size.width - 20.dp.toPx()).toInt(),
                (size.height - 20.dp.toPx()).toInt()
            )

            /* Draw it on the native canvas */
            drawable.draw(canvas.nativeCanvas)
        }
    }
}

/*
Accessing native canvas for custom painting

Sometimes you might need to use native Canvas methods
that are not exposed through DrawScope

drawIntoCanvas gives you access to the underlying Android Canvas
where you can use all the traditional Canvas drawing methods
*/

@Composable
fun NativeCanvasExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawIntoCanvas { canvas ->
            val nativeCanvas = canvas.nativeCanvas

            /* Use native Canvas methods */
            val paint = Paint().apply {
                color = Color.RED
                strokeWidth = 5f
                style = Paint.Style.STROKE
            }

            nativeCanvas.drawCircle(
                size.width / 2,
                size.height / 2,
                80.dp.toPx(),
                paint
            )
        }
    }
}

/*
Combining DrawScope and native Canvas

You can mix DrawScope drawing methods with native Canvas methods
*/

@Composable
fun MixedDrawingExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* Use DrawScope methods */
        drawCircle(
            color = androidx.compose.ui.graphics.Color.Yellow,
            radius = 90.dp.toPx()
        )

        /* Use native Canvas methods */
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                color = Color.BLUE
                textSize = 40f
                textAlign = Paint.Align.CENTER
            }

            canvas.nativeCanvas.drawText(
                "Mixed",
                size.width / 2,
                size.height / 2,
                paint
            )
        }
    }
}

/*
Drawing multiple drawables

This example draws multiple ShapeDrawables at different positions
*/

@Composable
fun MultipleDrawablesExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawIntoCanvas { canvas ->
            /* Create different colored drawables */
            val redDrawable = ShapeDrawable(OvalShape()).apply {
                paint.color = Color.RED
            }

            val greenDrawable = ShapeDrawable(OvalShape()).apply {
                paint.color = Color.GREEN
            }

            val blueDrawable = ShapeDrawable(OvalShape()).apply {
                paint.color = Color.BLUE
            }

            /* Draw them at different positions */
            redDrawable.setBounds(0, 0, 80.dp.toPx().toInt(), 80.dp.toPx().toInt())
            redDrawable.draw(canvas.nativeCanvas)

            greenDrawable.setBounds(
                60.dp.toPx().toInt(),
                60.dp.toPx().toInt(),
                140.dp.toPx().toInt(),
                140.dp.toPx().toInt()
            )
            greenDrawable.draw(canvas.nativeCanvas)

            blueDrawable.setBounds(
                120.dp.toPx().toInt(),
                120.dp.toPx().toInt(),
                200.dp.toPx().toInt(),
                200.dp.toPx().toInt()
            )
            blueDrawable.draw(canvas.nativeCanvas)
        }
    }
}

/*
Using native Paint for gradient effects

This shows how to use native Paint with gradients
*/

@Composable
fun NativeGradientExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                Paint.setShader = LinearGradient(
                    0f, 0f,
                    size.width, size.height,
                    Color.MAGENTA,
                    Color.CYAN,
                    Shader.TileMode.CLAMP
                )
            }

            canvas.nativeCanvas.drawRect(
                0f, 0f,
                size.width, size.height,
                paint
            )
        }
    }
}

/*
When to use drawIntoCanvas

Use drawIntoCanvas when you need to:
- Draw Android Drawables
- Use native Canvas methods not available in DrawScope
- Integrate with existing Android drawing code
- Use native Paint features like shaders or path effects

For most Compose drawing use DrawScope methods instead
as they are more efficient and better integrated with Compose
*/

/*
Display all canvas access examples
*/

@Composable
fun AllCanvasAccessExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Accessing Canvas Object",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Use drawIntoCanvas to access native Canvas for Android Drawables",
            fontSize = 14.sp
        )

        Text(text = "Draw ShapeDrawable", fontWeight = FontWeight.Medium)
        DrawShapeDrawableExample()

        Text(text = "Custom Drawable with Bounds", fontWeight = FontWeight.Medium)
        DrawCustomDrawableExample()

        Text(text = "Native Canvas Methods", fontWeight = FontWeight.Medium)
        NativeCanvasExample()

        Text(text = "Mixed DrawScope and Native", fontWeight = FontWeight.Medium)
        MixedDrawingExample()

        Text(text = "Multiple Drawables", fontWeight = FontWeight.Medium)
        MultipleDrawablesExample()

        Text(text = "Native Gradient", fontWeight = FontWeight.Medium)
        NativeGradientExample()
    }
}

