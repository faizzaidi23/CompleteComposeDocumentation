package com.example.FullComposeOfficialDocumentation.Graphics_7

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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Many apps need to be able to precisely control exactly what is drawn on the screen
This might be as small as putting a box or a circle on the screen in just the right place
or it might be an elaborate arrangement of graphic elements in many different styles

The core way to draw something custom in Compose is with modifiers
such as Modifier.drawWithContent Modifier.drawBehind and Modifier.drawWithCache
*/

/*
Basic drawing with modifiers and DrawScope

The core way to draw something custom in Compose is with modifiers
All drawing modifiers expose a DrawScope which is a scoped drawing environment
that maintains its own state

DrawScope lets you set the parameters for a group of graphical elements
DrawScope provides several useful fields like size which is a Size object
specifying the current dimensions of the DrawScope
*/

/*
Using Modifier.drawBehind

To draw something behind your composable use the drawBehind modifier
This is useful for drawing backgrounds or decorative elements behind your content
Inside drawBehind you have access to DrawScope
*/

@Composable
fun DrawBehindExample() {
    Spacer(
        modifier = Modifier
            .size(200.dp)
            .drawBehind {
                /* this = DrawScope */
                /* We can use any DrawScope drawing functions here */
                drawRect(
                    color = Color.Blue,
                    size = size
                )
            }
    )
}

/*
Using Canvas composable

If all you need is a composable that draws you can use the Canvas composable
Canvas is a convenient wrapper around Modifier.drawBehind
You place the Canvas in your layout the same way you would with any other Compose UI element

Within the Canvas you can draw elements with precise control over their style and location
*/

@Composable
fun BasicCanvasExample() {
    Canvas(modifier = Modifier.size(200.dp)) {
        /* this = DrawScope */
        drawCircle(
            color = Color.Red,
            radius = 100.dp.toPx()
        )
    }
}

/*
Under the hood Compose relies on the view-based UI Canvas and other associated objects
However Compose simplifies many of the more confusing aspects of Canvas

For example most of the view-based graphics elements rely on a Paint helper object
You need to know which configuration options you set in the Paint and which you set in the method call
You also need to be careful to create your Paint objects in a way that will not harm performance

Compose takes care of those details for you
*/

/*
Drawing a rectangle with Canvas

All drawing modifiers expose a DrawScope
DrawScope provides several useful fields like size
which is a Size object specifying the current dimensions of the DrawScope

To draw something you can use one of the many draw functions on DrawScope
For example drawRect to draw a rectangle
*/

@Composable
fun DrawRectangleExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2F
        drawRect(
            color = Color.Magenta,
            size = canvasQuadrantSize
        )
    }
}

/*
Using Modifier.drawWithContent

drawWithContent allows you to draw before and after the content of the composable
This is useful when you want to add decorations around existing content
*/

@Composable
fun DrawWithContentExample() {
    Text(
        text = "Hello World",
        modifier = Modifier
            .drawWithContent {
                /* Draw background before content */
                drawCircle(
                    color = Color.Yellow,
                    radius = 100.dp.toPx()
                )

                /* Draw the actual content (the Text) */
                drawContent()

                /* Draw foreground after content */
                drawCircle(
                    color = Color.Red.copy(alpha = 0.3f),
                    radius = 50.dp.toPx()
                )
            }
            .padding(16.dp),
        fontSize = 20.sp
    )
}

/*
Multiple drawing modifiers can be chained

You can chain multiple drawing modifiers together
Each modifier adds its own drawing layer
*/

@Composable
fun MultipleDrawModifiersExample() {
    Spacer(
        modifier = Modifier
            .size(200.dp)
            .drawBehind {
                drawRect(color = Color.Blue)
            }
            .padding(20.dp)
            .drawBehind {
                drawRect(color = Color.Green)
            }
            .padding(20.dp)
            .drawBehind {
                drawRect(color = Color.Red)
            }
    )
}

/*
Display all basic drawing examples
*/

@Composable
fun AllBasicDrawingExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Basic Drawing with Modifiers",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "drawBehind Example", fontWeight = FontWeight.Medium)
        DrawBehindExample()

        Text(text = "Canvas Example", fontWeight = FontWeight.Medium)
        BasicCanvasExample()

        Text(text = "drawRect Example", fontWeight = FontWeight.Medium)
        DrawRectangleExample()

        Text(text = "drawWithContent Example", fontWeight = FontWeight.Medium)
        DrawWithContentExample()

        Text(text = "Multiple Draw Modifiers", fontWeight = FontWeight.Medium)
        MultipleDrawModifiersExample()
    }
}

