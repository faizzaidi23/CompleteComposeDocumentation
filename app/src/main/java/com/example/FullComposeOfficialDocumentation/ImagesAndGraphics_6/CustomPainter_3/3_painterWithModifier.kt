package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.CustomPainter_3

import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
A custom painter can also be used with Modifier.paint to draw content to any composable
This is different from using it in an Image composable
With Modifier.paint you can apply the painter to any composable like Box Column etc
The painter draws its content within the bounds of the composable
*/

/*
Using custom painter with Modifier.paint on Box
This example shows how to use the OverlayImagePainter with the paint modifier
The Box has background colors and padding to show how the painter interacts with other modifiers
The painter is drawn on top of the background
Note: Using the same image twice for demonstration purposes
*/

@Composable
fun CustomPainterWithPaintModifier(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val customPainter = remember {
        OverlayImagePainter(dogImage, dogImage)
    }

    Box(
        modifier = Modifier
            .background(color = Color.Gray)
            .padding(30.dp)
            .background(color = Color.Yellow)
            .paint(customPainter)
    ) {
        /* Intentionally empty - the painter draws the content */
    }
}

/*
Paint modifier with different sizes
You can control the size of the Box to see how the painter scales
The painter will draw within the bounds of the Box
*/

@Composable
fun CustomPainterWithSizedBox(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val customPainter = remember {
        OverlayImagePainter(dogImage, dogImage)
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = Color.LightGray)
            .padding(16.dp)
            .paint(customPainter)
    ) {
        /* Intentionally empty */
    }
}

/*
Multiple paint modifiers can be stacked
You can apply multiple painters to create layered effects
Each paint modifier adds another layer of drawing
*/

@Composable
fun MultiplePaintModifiers(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val customPainter = remember {
        OverlayImagePainter(dogImage, dogImage)
    }

    Box(
        modifier = Modifier
            .size(250.dp)
            .background(color = Color(0xFFE0E0E0))
            .padding(20.dp)
            .background(color = Color.White)
            .paint(customPainter)
    ) {
        /* Intentionally empty */
    }
}

/*
Paint modifier with content inside
You can also have child composables inside the Box
The painter draws as a background and the child content appears on top
*/

@Composable
fun PaintModifierWithContent(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val customPainter = remember {
        OverlayImagePainter(dogImage, dogImage)
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .paint(customPainter),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Text on top",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/*
Display all paint modifier examples
This shows different ways to use the paint modifier with custom painters
*/

@Composable
fun AllPaintModifierExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Custom Painter with Modifier.paint",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Using paint modifier to apply painter to any composable",
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "Basic Paint Modifier",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 16.dp)
        )
        CustomPainterWithPaintModifier()

        Text(
            text = "Paint on Sized Box",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 24.dp)
        )
        CustomPainterWithSizedBox()

        Text(
            text = "With Padding and Background",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 24.dp)
        )
        MultiplePaintModifiers()

        Text(
            text = "Paint with Content on Top",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 24.dp)
        )
        PaintModifierWithContent()
    }
}
