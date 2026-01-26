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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

/*
DRAW TEXT

To draw text in Compose you can typically use the Text composable
However if you are in a DrawScope or you want to draw your text manually with customization
you can use the DrawScope.drawText method

To draw text create a TextMeasurer using rememberTextMeasurer
and call drawText with the measurer
*/

/*
Basic text drawing with Canvas

This is the simplest way to draw text on a canvas
*/

@Composable
fun BasicDrawTextExample() {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier.size(200.dp)) {
        drawText(textMeasurer, "Hello")
    }
}

/*
Drawing text with custom style

You can customize the text appearance using TextStyle
*/

@Composable
fun StyledDrawTextExample() {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier.size(200.dp)) {
        drawText(
            textMeasurer = textMeasurer,
            text = "Styled Text",
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

/*
MEASURE TEXT

Drawing text works a bit differently from other drawing commands
Normally you give the drawing command the size (width and height) to draw the shape or image
With text there are a few parameters that control the size of the rendered text
such as font size font ligatures and letter spacing

With Compose you can use a TextMeasurer to get access to the measured size of text
depending on the above factors

If you want to draw a background behind the text
you can use the measured information to get the size of the area that the text takes up
*/

/*
Drawing text with background

This example measures the text and draws a background rectangle behind it
*/

@Composable
fun TextWithBackgroundExample() {
    val textMeasurer = rememberTextMeasurer()
    val pinkColor = Color(0xFFFF6B9D)

    Spacer(
        modifier = Modifier
            .size(300.dp)
            .drawWithCache {
                val measuredText = textMeasurer.measure(
                    AnnotatedString("Hello World"),
                    style = TextStyle(fontSize = 24.sp)
                )

                onDrawBehind {
                    drawRect(pinkColor, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
    )
}

/*
Multi-line text with background

This example shows how to measure and draw multi-line text
The text takes up 2/3 of the width and has a pink background
*/

@Composable
fun MultiLineTextExample() {
    val textMeasurer = rememberTextMeasurer()
    val pinkColor = Color(0xFFFFB6C1)
    val longTextSample = "This is a long text sample that will wrap to multiple lines when constrained to a specific width. It demonstrates how text measurement works in Compose."

    Spacer(
        modifier = Modifier
            .size(300.dp)
            .drawWithCache {
                val measuredText = textMeasurer.measure(
                    AnnotatedString(longTextSample),
                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                    style = TextStyle(fontSize = 18.sp)
                )

                onDrawBehind {
                    drawRect(pinkColor, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
    )
}

/*
Text with ellipsis overflow

Adjusting the constraints font size or any property that affects measured size
results in a new size reported

You can set a fixed size for both the width and height
and the text then follows the set TextOverflow

This example renders text in 1/3 of the height and 1/3 of the width of the composable area
and sets the TextOverflow to TextOverflow.Ellipsis
*/

@Composable
fun TextWithEllipsisExample() {
    val textMeasurer = rememberTextMeasurer()
    val pinkColor = Color(0xFFFF6B9D)
    val longTextSample = "This is a very long text that will be cut off with an ellipsis when it exceeds the specified constraints."

    Spacer(
        modifier = Modifier
            .size(300.dp)
            .drawWithCache {
                val measuredText = textMeasurer.measure(
                    AnnotatedString(longTextSample),
                    constraints = Constraints.fixed(
                        width = (size.width / 3f).toInt(),
                        height = (size.height / 3f).toInt()
                    ),
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 18.sp)
                )

                onDrawBehind {
                    drawRect(pinkColor, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
    )
}

/*
Note: The above examples use Modifier.drawWithCache
since drawing text is an expensive operation

Using drawWithCache helps cache the created objects
until the size of the drawing area changes

For more information see the Modifier.drawWithCache documentation
*/

/*
Drawing multiple text elements

You can draw multiple pieces of text at different positions
*/

@Composable
fun MultipleTextsExample() {
    val textMeasurer = rememberTextMeasurer()

    Canvas(modifier = Modifier.size(300.dp)) {
        drawText(
            textMeasurer = textMeasurer,
            text = "Top Left",
            topLeft = Offset(0f, 0f),
            style = TextStyle(fontSize = 16.sp, color = Color.Red)
        )

        drawText(
            textMeasurer = textMeasurer,
            text = "Center",
            topLeft = Offset(
                size.width / 2 - 30.dp.toPx(),
                size.height / 2
            ),
            style = TextStyle(fontSize = 20.sp, color = Color.Blue)
        )

        drawText(
            textMeasurer = textMeasurer,
            text = "Bottom Right",
            topLeft = Offset(
                size.width - 100.dp.toPx(),
                size.height - 40.dp.toPx()
            ),
            style = TextStyle(fontSize = 14.sp, color = Color.Green)
        )
    }
}

/*
Text with different fonts and weights

This shows various text styling options
*/

@Composable
fun TextStylingExample() {
    val textMeasurer = rememberTextMeasurer()

    Spacer(
        modifier = Modifier
            .size(300.dp)
            .drawWithCache {
                val normalText = textMeasurer.measure(
                    AnnotatedString("Normal Text"),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal)
                )

                val boldText = textMeasurer.measure(
                    AnnotatedString("Bold Text"),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )

                val largeText = textMeasurer.measure(
                    AnnotatedString("Large Text"),
                    style = TextStyle(fontSize = 32.sp, color = Color.Magenta)
                )

                onDrawBehind {
                    drawText(normalText, topLeft = Offset(0f, 0f))
                    drawText(boldText, topLeft = Offset(0f, 50.dp.toPx()))
                    drawText(largeText, topLeft = Offset(0f, 100.dp.toPx()))
                }
            }
    )
}

/*
Display all draw text examples
*/

@Composable
fun AllDrawTextExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Draw Text Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Use TextMeasurer and drawText to draw custom text",
            fontSize = 14.sp
        )

        Text(text = "Basic Draw Text", fontWeight = FontWeight.Medium)
        BasicDrawTextExample()

        Text(text = "Styled Text", fontWeight = FontWeight.Medium)
        StyledDrawTextExample()

        Text(text = "Text with Background", fontWeight = FontWeight.Medium)
        TextWithBackgroundExample()

        Text(text = "Multi-line Text", fontWeight = FontWeight.Medium)
        MultiLineTextExample()

        Text(text = "Text with Ellipsis", fontWeight = FontWeight.Medium)
        TextWithEllipsisExample()

        Text(text = "Multiple Texts", fontWeight = FontWeight.Medium)
        MultipleTextsExample()

        Text(text = "Text Styling Variations", fontWeight = FontWeight.Medium)
        TextStylingExample()
    }
}

