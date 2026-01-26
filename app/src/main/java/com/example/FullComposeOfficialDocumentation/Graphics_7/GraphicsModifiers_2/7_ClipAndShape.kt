package com.example.FullComposeOfficialDocumentation.Graphics_7.GraphicsModifiers_2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
CLIP AND SHAPE

Shape specifies the outline that the content clips to when clip = true
You can set boxes to have different clips using graphicsLayer clip variable
or use the convenient wrapper Modifier.clip
*/

@Composable
fun ClipWithGraphicsLayerExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                clip = true
                shape = CircleShape
            }
            .background(Color(0xFFF06292))
    ) {
        Text(
            "Hello Compose",
            style = TextStyle(color = Color.Black, fontSize = 46.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Clip using Modifier.clip

This is a convenient wrapper that does the same thing
*/

@Composable
fun ClipWithModifierExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .background(Color(0xFF4DB6AC))
    ) {
        Text(
            "Clipped",
            style = TextStyle(color = Color.White, fontSize = 32.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Clip with translation

If you apply a translationY to the circle
you see that the bounds of the Composable are still the same
but the circle draws underneath other content and outside of its bounds
*/

@Composable
fun ClipWithTranslationExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .border(2.dp, Color.Black)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                    translationY = 50.dp.toPx()
                }
                .background(Color(0xFFF06292))
        ) {
            Text(
                "Hello Compose",
                style = TextStyle(color = Color.Black, fontSize = 46.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(500.dp))
                .background(Color(0xFF4DB6AC))
        )
    }
}

/*
Clip to rectangle shape to constrain content

To clip the composable to the region it is drawn in
you can add another Modifier.clip(RectangleShape) at the start of the modifier chain
The content then remains inside of the original bounds
*/

@Composable
fun ClipToRectangleExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .size(200.dp)
                .border(2.dp, Color.Black)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                    translationY = 50.dp.toPx()
                }
                .background(Color(0xFFF06292))
        ) {
            Text(
                "Hello Compose",
                style = TextStyle(color = Color.Black, fontSize = 46.sp),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(500.dp))
                .background(Color(0xFF4DB6AC))
        )
    }
}

/*
Clip with rounded corners

Using RoundedCornerShape to create rounded rectangle clips
*/

@Composable
fun ClipRoundedCornersExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFFFFB74D))
    ) {
        Text(
            "Rounded",
            style = TextStyle(color = Color.White, fontSize = 28.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Different corner radii

You can specify different radii for each corner
*/

@Composable
fun ClipDifferentCornersExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 48.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 48.dp
                )
            )
            .background(Color(0xFF9C27B0))
    ) {
        Text(
            "Custom Corners",
            style = TextStyle(color = Color.White, fontSize = 24.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Display all clip and shape examples
*/

@Composable
fun AllClipAndShapeExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Clip and Shape",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Use clip to constrain content to a shape",
            fontSize = 14.sp
        )

        Text(text = "Clip with graphicsLayer", fontWeight = FontWeight.Medium)
        ClipWithGraphicsLayerExample()

        Text(text = "Clip with Modifier.clip", fontWeight = FontWeight.Medium)
        ClipWithModifierExample()

        Text(text = "Clip with Translation", fontWeight = FontWeight.Medium)
        ClipWithTranslationExample()

        Text(text = "Clip to Rectangle Bounds", fontWeight = FontWeight.Medium)
        ClipToRectangleExample()

        Text(text = "Rounded Corners", fontWeight = FontWeight.Medium)
        ClipRoundedCornersExample()

        Text(text = "Different Corner Radii", fontWeight = FontWeight.Medium)
        ClipDifferentCornersExample()
    }
}

