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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
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
Advanced custom painter techniques
This file demonstrates more complex custom painters with multiple features
You can combine images text shapes and effects in a single painter
*/

/*
Custom painter that adds a watermark to an image
This overlays text or a small logo on top of an image
Useful for protecting images or adding branding
*/

class WatermarkImagePainter(
    private val image: ImageBitmap,
    private val watermarkImage: ImageBitmap,
    private val position: WatermarkPosition = WatermarkPosition.BOTTOM_RIGHT,
    private val padding: Float = 16f
) : Painter() {

    enum class WatermarkPosition {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, CENTER
    }

    override fun DrawScope.onDraw() {
        /* Draw the main image */
        drawImage(
            image,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        /* Calculate watermark position */
        val watermarkWidth = watermarkImage.width * 0.2f
        val watermarkHeight = watermarkImage.height * 0.2f

        val offset = when (position) {
            WatermarkPosition.TOP_LEFT -> Offset(padding, padding)
            WatermarkPosition.TOP_RIGHT -> Offset(
                size.width - watermarkWidth - padding,
                padding
            )
            WatermarkPosition.BOTTOM_LEFT -> Offset(
                padding,
                size.height - watermarkHeight - padding
            )
            WatermarkPosition.BOTTOM_RIGHT -> Offset(
                size.width - watermarkWidth - padding,
                size.height - watermarkHeight - padding
            )
            WatermarkPosition.CENTER -> Offset(
                (size.width - watermarkWidth) / 2f,
                (size.height - watermarkHeight) / 2f
            )
        }

        /* Draw the watermark */
        drawImage(
            watermarkImage,
            dstOffset = IntOffset(offset.x.roundToInt(), offset.y.roundToInt()),
            dstSize = IntSize(watermarkWidth.roundToInt(), watermarkHeight.roundToInt()),
            alpha = 0.5f
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(image.width, image.height).toSize()
}

/*
Custom painter that creates a framed image effect
Draws an image with a decorative border frame
*/

class FramedImagePainter(
    private val image: ImageBitmap,
    private val frameColor: Color,
    private val frameWidth: Float = 20f,
    private val innerFrameColor: Color = Color.White,
    private val innerFrameWidth: Float = 10f
) : Painter() {

    override fun DrawScope.onDraw() {
        /* Draw outer frame */
        drawRect(
            color = frameColor,
            size = size
        )

        /* Draw inner frame */
        drawRect(
            color = innerFrameColor,
            topLeft = Offset(frameWidth, frameWidth),
            size = Size(
                size.width - frameWidth * 2,
                size.height - frameWidth * 2
            )
        )

        /* Draw the image */
        val imageOffset = frameWidth + innerFrameWidth
        val imageSize = Size(
            size.width - (frameWidth + innerFrameWidth) * 2,
            size.height - (frameWidth + innerFrameWidth) * 2
        )

        drawImage(
            image,
            dstOffset = IntOffset(imageOffset.roundToInt(), imageOffset.roundToInt()),
            dstSize = IntSize(imageSize.width.roundToInt(), imageSize.height.roundToInt())
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(image.width + 60, image.height + 60).toSize()
}

/*
Custom painter that creates a vignette effect
Darkens the edges of an image to draw focus to the center
*/

class VignetteImagePainter(
    private val image: ImageBitmap,
    private val vignetteStrength: Float = 0.7f
) : Painter() {

    override fun DrawScope.onDraw() {
        /* Draw the image */
        drawImage(
            image,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        /* Draw vignette overlay - darker circles from outside to inside */
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val maxRadius = maxOf(size.width, size.height) / 1.5f

        for (i in 0..20) {
            val radius = maxRadius + (i * maxRadius / 20f)
            val alpha = (i / 20f) * vignetteStrength

            drawCircle(
                color = Color.Black.copy(alpha = alpha),
                radius = radius,
                center = Offset(centerX, centerY)
            )
        }
    }

    override val intrinsicSize: Size
        get() = IntSize(image.width, image.height).toSize()
}

/*
Custom painter that creates a polaroid style image
Adds a white border with extra space at bottom like a polaroid photo
*/

class PolaroidImagePainter(
    private val image: ImageBitmap,
    private val backgroundColor: Color = Color.White,
    private val borderWidth: Float = 20f,
    private val bottomExtraSpace: Float = 40f
) : Painter() {

    override fun DrawScope.onDraw() {
        /* Draw white background */
        drawRect(
            color = backgroundColor,
            size = size
        )

        /* Draw the image with border */
        drawImage(
            image,
            dstOffset = IntOffset(borderWidth.roundToInt(), borderWidth.roundToInt()),
            dstSize = IntSize(
                (size.width - borderWidth * 2).roundToInt(),
                (size.height - borderWidth * 2 - bottomExtraSpace).roundToInt()
            )
        )
    }

    override val intrinsicSize: Size
        get() = IntSize(
            image.width + (borderWidth * 2).toInt(),
            image.height + (borderWidth * 2 + bottomExtraSpace).toInt()
        ).toSize()
}

/*
Examples using advanced custom painters
*/

@Composable
fun WatermarkImageExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)
    /* Using the same image as watermark for demonstration */

    val painter = remember {
        WatermarkImagePainter(
            image = dogImage,
            watermarkImage = dogImage,
            position = WatermarkImagePainter.WatermarkPosition.BOTTOM_RIGHT
        )
    }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun FramedImageExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember {
        FramedImagePainter(
            image = dogImage,
            frameColor = Color(0xFF8B4513),
            frameWidth = 15f,
            innerFrameColor = Color(0xFFF5DEB3),
            innerFrameWidth = 8f
        )
    }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(220.dp)
    )
}

@Composable
fun VignetteImageExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember {
        VignetteImagePainter(
            image = dogImage,
            vignetteStrength = 0.6f
        )
    }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun PolaroidImageExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val painter = remember {
        PolaroidImagePainter(
            image = dogImage,
            backgroundColor = Color(0xFFFAFAFA),
            borderWidth = 20f,
            bottomExtraSpace = 50f
        )
    }

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(220.dp)
    )
}

/*
Display all advanced painter examples
*/

@Composable
fun AllAdvancedPainterExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Advanced Custom Painters",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Complex painters with multiple features",
            fontSize = 14.sp
        )

        Text(text = "Image with Watermark", fontWeight = FontWeight.Medium)
        WatermarkImageExample()

        Text(text = "Framed Image", fontWeight = FontWeight.Medium)
        FramedImageExample()

        Text(text = "Vignette Effect", fontWeight = FontWeight.Medium)
        VignetteImageExample()

        Text(text = "Polaroid Style", fontWeight = FontWeight.Medium)
        PolaroidImageExample()
    }
}
