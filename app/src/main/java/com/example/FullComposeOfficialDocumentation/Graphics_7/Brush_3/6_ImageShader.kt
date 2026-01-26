package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
USE AN IMAGE AS A BRUSH

To use an ImageBitmap as a Brush, load up the image as an ImageBitmap,
and create an ImageShader brush

The ImageShader can be used anywhere a regular brush can be used:
- Modifier.background()
- TextStyle for text rendering
- DrawScope draw calls (like drawCircle)

This allows you to "paint" with an image instead of solid colors or gradients
*/

/*
Basic ImageShader Brush

Load an image and create a shader brush from it
*/

@Composable
fun BasicImageShaderExample() {
    // Load the image as ImageBitmap
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_foreground)

    // Create an ImageShader Brush
    val imageBrush = ShaderBrush(ImageShader(imageBitmap))

    // Use with background
    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(imageBrush)
    )
}

/*
ImageShader with Text

Use an image to render the text characters
The image is mapped onto the text glyphs
*/

@Composable
fun ImageShaderTextExample() {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_foreground)
    val imageBrush = ShaderBrush(ImageShader(imageBitmap))

    Text(
        text = "Hello Android!",
        style = TextStyle(
            brush = imageBrush,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp
        )
    )
}

/*
ImageShader with Canvas

Use an ImageShader to draw shapes in Canvas
*/

@Composable
fun ImageShaderCanvasExample() {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_foreground)
    val imageBrush = ShaderBrush(ImageShader(imageBitmap))

    Canvas(
        onDraw = {
            drawCircle(imageBrush)
        },
        modifier = Modifier.size(200.dp)
    )
}

/*
ImageShader with TileMode

You can control how the image tiles using TileMode
This is useful when the image is smaller than the drawing area
*/

@Composable
fun ImageShaderTileModeExample() {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_foreground)

    // TileMode.Repeated tiles the image
    val imageBrush = ShaderBrush(
        ImageShader(
            imageBitmap,
            tileModeX = TileMode.Repeated,
            tileModeY = TileMode.Repeated
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(imageBrush)
    )
}

/*
ImageShader with Mirror TileMode

Creates a mirrored tiling effect
*/

@Composable
fun ImageShaderMirrorExample() {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_foreground)

    val imageBrush = ShaderBrush(
        ImageShader(
            imageBitmap,
            tileModeX = TileMode.Mirror,
            tileModeY = TileMode.Mirror
        )
    )

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(imageBrush)
    )
}

/*
All ImageShader Usage Examples

This demonstrates all the different ways to use ImageShader Brush
*/

@Composable
fun AllImageShaderUsageShowcase() {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_foreground)
    val imageBrush = ShaderBrush(ImageShader(imageBitmap))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "ImageShader Brush Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Use ImageShader Brush with background
        Text("With Background", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(imageBrush)
        )

        // Use ImageShader Brush with TextStyle
        Text("With Text", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text(
            text = "Hello Android!",
            style = TextStyle(
                brush = imageBrush,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            )
        )

        // Use ImageShader Brush with DrawScope#drawCircle()
        Text("With Canvas (Circle)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Canvas(
            onDraw = {
                drawCircle(imageBrush)
            },
            modifier = Modifier.size(200.dp)
        )

        // Repeated TileMode
        Text("Repeated TileMode", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    ShaderBrush(
                        ImageShader(
                            imageBitmap,
                            tileModeX = TileMode.Repeated,
                            tileModeY = TileMode.Repeated
                        )
                    )
                )
        )
    }
}

