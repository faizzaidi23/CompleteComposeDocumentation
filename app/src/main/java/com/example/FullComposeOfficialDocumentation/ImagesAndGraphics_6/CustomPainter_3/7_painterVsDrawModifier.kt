package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.CustomPainter_3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Understanding the difference between Painter and DrawModifier

Painter can influence measurement and layout of the composable
It has an intrinsicSize property that affects how the composable sizes itself
Use Painter when you need to control the size of the composable

DrawModifier strictly draws within the bounds given to it
It has no influence on measurement or layout
Use DrawModifier when you only need to render within existing bounds
*/

/*
Example custom painter that influences size
This painter has an intrinsicSize of 200x200
When used in an Image with wrapContentSize the Image will be 200x200
*/

class SizeInfluencingPainter(
    private val color: Color,
    private val width: Float = 200f,
    private val height: Float = 200f
) : Painter() {

    override fun DrawScope.onDraw() {
        drawRect(
            color = color,
            size = size
        )

        /* Draw a circle in the center */
        drawCircle(
            color = Color.White,
            radius = size.minDimension / 4f,
            center = Offset(size.width / 2f, size.height / 2f)
        )
    }

    /*
    This intrinsicSize influences the measurement of the composable
    If you use this painter in an Image without specifying size
    the Image will try to be 200x200
    */
    override val intrinsicSize: Size
        get() = Size(width, height)
}

/*
Using a Painter that influences size
Without specifying a size modifier the Image uses the intrinsicSize from the painter
*/

@Composable
fun PainterInfluencingSizeExample(){
    val painter = remember {
        SizeInfluencingPainter(
            color = Color(0xFF2196F3),
            width = 150f,
            height = 150f
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Image uses painter's intrinsicSize (150x150)")

        /* No size modifier - uses intrinsicSize from painter */
        Image(
            painter = painter,
            contentDescription = "Size from painter"
        )
    }
}

/*
Using DrawModifier with drawBehind
DrawModifier does not influence size
It only draws within the bounds that are given to it
The Box size is determined by its content and modifiers not by the drawing
*/

@Composable
fun DrawModifierExample(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Box with drawBehind - size is 100x100 from modifier")

        Box(
            modifier = Modifier
                .size(100.dp)
                .drawBehind {
                    /* This drawing happens within the 100x100 bounds */
                    drawRect(
                        color = Color(0xFFFF5722),
                        size = size
                    )

                    drawCircle(
                        color = Color.White,
                        radius = size.minDimension / 4f,
                        center = Offset(size.width / 2f, size.height / 2f)
                    )
                }
        )
    }
}

/*
Comparing Painter vs DrawModifier side by side
This shows how Painter can influence size while DrawModifier cannot
*/

@Composable
fun PainterVsDrawModifierComparison(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Painter (intrinsicSize 180x180)")

        val painter = remember {
            SizeInfluencingPainter(
                color = Color(0xFF4CAF50),
                width = 180f,
                height = 180f
            )
        }

        Image(
            painter = painter,
            contentDescription = "Painter example"
        )

        Text("DrawModifier (must specify size)")

        Box(
            modifier = Modifier
                .size(180.dp)
                .drawBehind {
                    drawRect(
                        color = Color(0xFF4CAF50),
                        size = size
                    )

                    drawCircle(
                        color = Color.White,
                        radius = size.minDimension / 4f,
                        center = Offset(size.width / 2f, size.height / 2f)
                    )
                }
        )
    }
}

/*
When to use Painter
Use Painter when you need to influence the measurement or layout of a composable
Examples loading images creating custom drawable content that has inherent dimensions
*/

@Composable
fun WhenToUsePainterExample(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Use Painter for content with inherent size")

        val painter = remember {
            SizeInfluencingPainter(
                color = Color(0xFF9C27B0),
                width = 120f,
                height = 80f
            )
        }

        /* The Image will be 120x80 based on the painter */
        Image(
            painter = painter,
            contentDescription = "Painter with aspect ratio"
        )
    }
}

/*
When to use DrawModifier
Use DrawModifier when you only need to render within the bounds you are given
Examples decorating existing composables adding backgrounds drawing effects
*/

@Composable
fun WhenToUseDrawModifierExample(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Use DrawModifier for decorating composables")

        Text(
            text = "Text with custom background",
            modifier = Modifier
                .padding(16.dp)
                .drawBehind {
                    /* Draw a rounded background behind the text */
                    drawRoundRect(
                        color = Color(0xFFFFEB3B),
                        size = size
                    )
                },
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/*
Using Canvas composable
Canvas is another way to draw custom graphics
It takes a Modifier and draws within the given bounds
Similar to DrawModifier it does not influence size
*/

@Composable
fun CanvasExample(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Canvas composable (size from modifier)")

        Canvas(
            modifier = Modifier
                .size(150.dp)
                .background(Color.LightGray)
        ) {
            /* Draw within the 150x150 bounds */
            drawCircle(
                color = Color(0xFFE91E63),
                radius = size.minDimension / 3f,
                center = Offset(size.width / 2f, size.height / 2f)
            )
        }
    }
}

/*
Display all comparison examples
*/

@Composable
fun AllPainterVsDrawModifierExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Painter vs DrawModifier",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Understanding when to use each approach",
            fontSize = 14.sp
        )

        PainterInfluencingSizeExample()

        DrawModifierExample()

        Text(
            text = "Side by Side Comparison",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 16.dp)
        )

        PainterVsDrawModifierComparison()

        Text(
            text = "When to Use Each",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 16.dp)
        )

        WhenToUsePainterExample()

        WhenToUseDrawModifierExample()

        CanvasExample()
    }
}

