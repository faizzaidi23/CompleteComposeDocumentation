package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.CustomPainter_3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.FullComposeOfficialDocumentation.R

/*
In Compose a Painter object is used to represent something that can be drawn
It is a replacement to the Drawable APIs defined in Android
A Painter can influence measurement and layout of the corresponding composable that is using it

A BitmapPainter takes an ImageBitmap that can draw a Bitmap on screen
For most use cases using painterResource returns the correct painter for the asset
This could be a BitmapPainter for raster images or VectorPainter for vector images

A Painter is different from a DrawModifier
DrawModifier strictly draws within the bounds given to it and has no influence on measurement or layout
Painter can influence the size of the composable that contains it
*/

/*
Using painterResource to load images
This is the most common way to load images in Compose
The system automatically returns the appropriate painter type based on the image format
*/

@Composable
fun BasicPainterUsage(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

/*
Using BitmapPainter directly
You can create a BitmapPainter manually if you have an ImageBitmap
First load the image as an ImageBitmap then create a BitmapPainter from it
*/

@Composable
fun DirectBitmapPainterUsage(){
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.dog)
    val bitmapPainter = remember { BitmapPainter(imageBitmap) }

    Image(
        painter = bitmapPainter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

/*
Using ContentScale with Painter
Just like with painterResource you can use ContentScale to control how the image is scaled
*/

@Composable
fun PainterWithContentScale(){
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.dog)
    val bitmapPainter = remember { BitmapPainter(imageBitmap) }

    Image(
        painter = bitmapPainter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(200.dp)
    )
}

/*
Display all basic painter examples
This shows different ways to use Painter in Compose
*/

@Composable
fun AllBasicPainterExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Basic Painter Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Using painterResource", fontWeight = FontWeight.Medium)
        BasicPainterUsage()

        Text(text = "Using BitmapPainter directly", fontWeight = FontWeight.Medium)
        DirectBitmapPainterUsage()

        Text(text = "Painter with ContentScale.Crop", fontWeight = FontWeight.Medium)
        PainterWithContentScale()
    }
}
