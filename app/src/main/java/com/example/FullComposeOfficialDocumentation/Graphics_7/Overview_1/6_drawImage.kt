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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
DRAW IMAGE

To draw an ImageBitmap with DrawScope
load up the image using ImageBitmap.imageResource
and then call drawImage

Note: See the Image customization documentation for more information
on how to apply filters to your images
*/

/*
Basic image drawing

This is the simplest way to draw an image on canvas
*/

@Composable
fun BasicDrawImageExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        drawImage(dogImage)
    })
}

/*
Drawing image with specific size

You can specify the destination size to scale the image
*/

@Composable
fun DrawImageWithSizeExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.size(300.dp)) {
        drawImage(
            image = dogImage,
            dstSize = IntSize(200.dp.toPx().toInt(), 200.dp.toPx().toInt())
        )
    }
}

/*
Drawing image at specific position

You can position the image anywhere on the canvas using dstOffset
*/

@Composable
fun DrawImageAtPositionExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.size(300.dp)) {
        drawImage(
            image = dogImage,
            dstOffset = IntOffset(50.dp.toPx().toInt(), 50.dp.toPx().toInt()),
            dstSize = IntSize(150.dp.toPx().toInt(), 150.dp.toPx().toInt())
        )
    }
}

/*
Drawing image with alpha transparency

You can adjust the opacity of the image using the alpha parameter
*/

@Composable
fun DrawImageWithAlphaExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.size(300.dp)) {
        drawImage(
            image = dogImage,
            alpha = 0.5f
        )
    }
}

/*
Drawing multiple images

You can draw multiple images at different positions and sizes
*/

@Composable
fun MultipleImagesExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.size(300.dp)) {
        /* Large image in background */
        drawImage(
            image = dogImage,
            dstSize = IntSize(size.width.toInt(), size.height.toInt()),
            alpha = 0.3f
        )

        /* Small image in top-left */
        drawImage(
            image = dogImage,
            dstOffset = IntOffset(10.dp.toPx().toInt(), 10.dp.toPx().toInt()),
            dstSize = IntSize(80.dp.toPx().toInt(), 80.dp.toPx().toInt())
        )

        /* Small image in bottom-right */
        drawImage(
            image = dogImage,
            dstOffset = IntOffset(
                (size.width - 90.dp.toPx()).toInt(),
                (size.height - 90.dp.toPx()).toInt()
            ),
            dstSize = IntSize(80.dp.toPx().toInt(), 80.dp.toPx().toInt())
        )
    }
}

/*
Drawing part of an image (source rectangle)

You can draw only a portion of the source image using srcOffset and srcSize
This is useful for sprite sheets or extracting specific parts of an image
*/

@Composable
fun DrawImagePartExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.size(300.dp)) {
        /* Draw only the center portion of the image */
        drawImage(
            image = dogImage,
            srcOffset = IntOffset(
                dogImage.width / 4,
                dogImage.height / 4
            ),
            srcSize = IntSize(
                dogImage.width / 2,
                dogImage.height / 2
            ),
            dstSize = IntSize(
                200.dp.toPx().toInt(),
                200.dp.toPx().toInt()
            )
        )
    }
}

/*
Image tiled pattern

This creates a tiled pattern by drawing the image multiple times
*/

@Composable
fun ImageTiledPatternExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.size(300.dp)) {
        val tileSize = 75.dp.toPx().toInt()
        val tilesX = (size.width / tileSize).toInt()
        val tilesY = (size.height / tileSize).toInt()

        for (y in 0 until tilesY) {
            for (x in 0 until tilesX) {
                drawImage(
                    image = dogImage,
                    dstOffset = IntOffset(x * tileSize, y * tileSize),
                    dstSize = IntSize(tileSize, tileSize),
                    alpha = 0.6f
                )
            }
        }
    }
}

/*
Image with transformation

You can combine image drawing with transformations
*/

@Composable
fun ImageWithTransformExample() {
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    Canvas(modifier = Modifier.size(300.dp)) {
        withTransform({
            rotate(degrees = 45f, pivot = Offset(size.width / 2, size.height / 2))
        }) {
            drawImage(
                image = dogImage,
                dstOffset = IntOffset(
                    (size.width / 4).toInt(),
                    (size.height / 4).toInt()
                ),
                dstSize = IntSize(
                    (size.width / 2).toInt(),
                    (size.height / 2).toInt()
                )
            )
        }
    }
}

/*
Display all draw image examples
*/

@Composable
fun AllDrawImageExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Draw Image Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Use ImageBitmap.imageResource and drawImage",
            fontSize = 14.sp
        )

        Text(text = "Basic Draw Image", fontWeight = FontWeight.Medium)
        BasicDrawImageExample()

        Text(text = "Image with Specific Size", fontWeight = FontWeight.Medium)
        DrawImageWithSizeExample()

        Text(text = "Image at Position", fontWeight = FontWeight.Medium)
        DrawImageAtPositionExample()

        Text(text = "Image with Alpha", fontWeight = FontWeight.Medium)
        DrawImageWithAlphaExample()

        Text(text = "Multiple Images", fontWeight = FontWeight.Medium)
        MultipleImagesExample()

        Text(text = "Draw Part of Image", fontWeight = FontWeight.Medium)
        DrawImagePartExample()

        Text(text = "Tiled Pattern", fontWeight = FontWeight.Medium)
        ImageTiledPatternExample()

        Text(text = "Image with Rotation", fontWeight = FontWeight.Medium)
        ImageWithTransformExample()
    }
}
