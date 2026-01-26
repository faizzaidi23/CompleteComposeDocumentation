package com.example.FullComposeOfficialDocumentation.Graphics_7.GraphicsModifiers_2

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
DRAWING MODIFIERS

In addition to the Canvas composable Compose has several useful graphics Modifiers
which aid in drawing custom content
These modifiers are useful because they can be applied to any composable

There are three main drawing modifiers in Compose:
- drawWithContent
- drawBehind
- drawWithCache
*/

/*
The base modifier for drawing is drawWithContent
where you can decide the drawing order of your Composable and the drawing commands issued inside the modifier

drawBehind is a convenient wrapper around drawWithContent
which has the drawing order set to behind the content of the composable

drawWithCache calls either onDrawBehind or onDrawWithContent inside of it
and provides a mechanism for caching the objects created in them
*/

/*
MODIFIER.DRAWWITHCONTENT - Choose drawing order

Modifier.drawWithContent lets you execute DrawScope operations before or after the content of the composable
Be sure to call drawContent to then render the actual content of the composable

With this modifier you can decide the order of operations
if you want your content to be drawn before or after your custom drawing operations
*/

/*
Drawing before content example

This draws a background circle before the text content is rendered
*/

@Composable
fun DrawBeforeContentExample() {
    Text(
        text = "Before",
        modifier = Modifier
            .drawWithContent {
                /* Draw background circle before content */
                drawCircle(
                    color = Color.Yellow,
                    radius = 50.dp.toPx()
                )

                /* Then draw the actual content (the Text) */
                drawContent()
            }
            .padding(16.dp),
        fontSize = 20.sp
    )
}

/*
Drawing after content example

This draws a semi-transparent overlay after the content
*/

@Composable
fun DrawAfterContentExample() {
    Text(
        text = "After",
        modifier = Modifier
            .drawWithContent {
                /* Draw the content first */
                drawContent()

                /* Then draw overlay on top */
                drawCircle(
                    color = Color.Red.copy(alpha = 0.3f),
                    radius = 40.dp.toPx()
                )
            }
            .padding(16.dp),
        fontSize = 20.sp
    )
}

/*
Flashlight keyhole effect

This example renders a radial gradient on top of content to create a flashlight keyhole effect on the UI
The gradient has transparent center and black edges
creating a spotlight effect that follows pointer movement
*/

@Composable
fun FlashlightKeyholeEffect() {
    var pointerOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput("dragging") {
                detectDragGestures { change, dragAmount ->
                    pointerOffset += dragAmount
                }
            }
            .onSizeChanged {
                pointerOffset = Offset(it.width / 2f, it.height / 2f)
            }
            .drawWithContent {
                drawContent()

                /* Draws a fully black area with a small keyhole at pointerOffset that shows part of the UI */
                drawRect(
                    Brush.radialGradient(
                        listOf(Color.Transparent, Color.Black),
                        center = pointerOffset,
                        radius = 100.dp.toPx(),
                    )
                )
            }
            .background(Color.White)
    ) {
        Text(
            "Drag to reveal content",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        repeat(20) {
            Text(
                "Line ${it + 1}",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

/*
Drawing before and after content

This example shows how to draw both before and after content in one modifier
*/

@Composable
fun DrawBeforeAndAfterExample() {
    Text(
        text = "Both",
        modifier = Modifier
            .drawWithContent {
                /* Draw yellow circle behind */
                drawCircle(
                    color = Color.Yellow,
                    radius = 60.dp.toPx()
                )

                /* Draw content */
                drawContent()

                /* Draw red circle on top */
                drawCircle(
                    color = Color.Red.copy(alpha = 0.5f),
                    radius = 30.dp.toPx(),
                    center = Offset(size.width, 0f)
                )
            }
            .padding(16.dp),
        fontSize = 20.sp
    )
}

/*
MODIFIER.DRAWBEHIND - Drawing behind a composable

Modifier.drawBehind lets you perform DrawScope operations behind the composable content
that is drawn on screen

If you take a look at the implementation of Canvas
you might notice that it is just a convenient wrapper around Modifier.drawBehind
*/

/*
Drawing rounded rectangle behind Text

This draws a colored rounded rectangle behind text
*/

@Composable
fun DrawBehindRoundedRectExample() {
    Text(
        "Hello Compose!",
        modifier = Modifier
            .drawBehind {
                drawRoundRect(
                    Color(0xFFBBAAEE),
                    cornerRadius = CornerRadius(10.dp.toPx())
                )
            }
            .padding(4.dp)
    )
}

/*
Drawing circle behind text

Simple circle background using drawBehind
*/

@Composable
fun DrawBehindCircleExample() {
    Text(
        "Circle",
        modifier = Modifier
            .drawBehind {
                drawCircle(
                    color = Color(0xFF4CAF50),
                    radius = 50.dp.toPx()
                )
            }
            .padding(16.dp),
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

/*
Multiple layers with drawBehind

You can chain multiple drawBehind modifiers to create layers
*/

@Composable
fun MultipleDrawBehindLayers() {
    Text(
        "Layers",
        modifier = Modifier
            .drawBehind {
                /* Outermost layer - large blue circle */
                drawCircle(
                    color = Color.Blue,
                    radius = 80.dp.toPx()
                )
            }
            .drawBehind {
                /* Middle layer - medium yellow circle */
                drawCircle(
                    color = Color.Yellow,
                    radius = 60.dp.toPx()
                )
            }
            .drawBehind {
                /* Innermost layer - small red circle */
                drawCircle(
                    color = Color.Red,
                    radius = 40.dp.toPx()
                )
            }
            .padding(16.dp),
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

/*
Display all drawing modifier examples
*/

@Composable
fun AllDrawingModifierExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Drawing Modifiers",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "drawWithContent Examples",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        DrawBeforeContentExample()
        DrawAfterContentExample()
        DrawBeforeAndAfterExample()

        Text(
            text = "drawBehind Examples",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        DrawBehindRoundedRectExample()
        DrawBehindCircleExample()
        MultipleDrawBehindLayers()

        Text(
            text = "Flashlight Effect (drag to reveal)",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Box(modifier = Modifier.size(300.dp)) {
            FlashlightKeyholeEffect()
        }
    }
}
