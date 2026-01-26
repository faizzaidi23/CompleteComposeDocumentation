package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.CustomPainter_3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.FullComposeOfficialDocumentation.R
import kotlin.math.roundToInt

/*
This file demonstrates creating custom painters with different BlendModes
BlendMode determines how two images are combined together
You can create different visual effects by changing the blend mode
*/

/*
Custom painter with Multiply blend mode
Multiply blend mode multiplies the colors of the two images
This creates a darker combined image
Useful for creating shadow or darkening effects
*/

class MultiplyBlendPainter(
    private val baseImage: ImageBitmap,
    private val overlayImage: ImageBitmap
) : Painter() {

    override fun DrawScope.onDraw() {
        /* Draw the base image first */
        drawImage(
            baseImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        /* Draw overlay with Multiply blend mode */
        drawImage(
            overlayImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.Multiply
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(baseImage.width, baseImage.height).toSize()
}

/*
Custom painter with Screen blend mode
Screen blend mode creates a lighter combined image
It is the opposite of Multiply
Useful for creating light glow or brightening effects
*/

class ScreenBlendPainter(
    private val baseImage: ImageBitmap,
    private val overlayImage: ImageBitmap
) : Painter() {

    override fun DrawScope.onDraw() {
        drawImage(
            baseImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        drawImage(
            overlayImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.Screen
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(baseImage.width, baseImage.height).toSize()
}

/*
Custom painter with Darken blend mode
Darken blend mode selects the darker of the two colors for each pixel
Creates a darker composite image
*/

class DarkenBlendPainter(
    private val baseImage: ImageBitmap,
    private val overlayImage: ImageBitmap
) : Painter() {

    override fun DrawScope.onDraw() {
        drawImage(
            baseImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        drawImage(
            overlayImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.Darken
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(baseImage.width, baseImage.height).toSize()
}

/*
Custom painter with Lighten blend mode
Lighten blend mode selects the lighter of the two colors for each pixel
Creates a lighter composite image
*/

class LightenBlendPainter(
    private val baseImage: ImageBitmap,
    private val overlayImage: ImageBitmap
) : Painter() {

    override fun DrawScope.onDraw() {
        drawImage(
            baseImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        drawImage(
            overlayImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.Lighten
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(baseImage.width, baseImage.height).toSize()
}

/*
Custom painter with ColorBurn blend mode
ColorBurn darkens the base color to reflect the blend color
Creates high contrast dark effects
*/

class ColorBurnBlendPainter(
    private val baseImage: ImageBitmap,
    private val overlayImage: ImageBitmap
) : Painter() {

    override fun DrawScope.onDraw() {
        drawImage(
            baseImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        drawImage(
            overlayImage,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.ColorBurn
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(baseImage.width, baseImage.height).toSize()
}

/*
Using different blend mode painters
*/

@Composable
fun MultiplyBlendExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember { MultiplyBlendPainter(dogImage, dogImage) }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun ScreenBlendExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember { ScreenBlendPainter(dogImage, dogImage) }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun DarkenBlendExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember { DarkenBlendPainter(dogImage, dogImage) }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun LightenBlendExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember { LightenBlendPainter(dogImage, dogImage) }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun ColorBurnBlendExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember { ColorBurnBlendPainter(dogImage, dogImage) }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(200.dp)
    )
}

/*
Display all blend mode examples together
*/

@Composable
fun AllBlendModePainterExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Custom Painters with Different BlendModes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Multiply BlendMode", fontWeight = FontWeight.Medium)
        MultiplyBlendExample()

        Text(text = "Screen BlendMode", fontWeight = FontWeight.Medium)
        ScreenBlendExample()

        Text(text = "Darken BlendMode", fontWeight = FontWeight.Medium)
        DarkenBlendExample()

        Text(text = "Lighten BlendMode", fontWeight = FontWeight.Medium)
        LightenBlendExample()

        Text(text = "ColorBurn BlendMode", fontWeight = FontWeight.Medium)
        ColorBurnBlendExample()
    }
}
