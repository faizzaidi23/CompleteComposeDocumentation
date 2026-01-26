package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.CustomPainter_3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
To create a custom painter extend the Painter class and implement the onDraw method
This allows access to the DrawScope to draw custom graphics
You can also override the intrinsicSize which will influence the Composable that contains it

The OverlayImagePainter shown here combines two images using a blend mode
It draws the first image normally then overlays the second image using BlendMode.Overlay
*/

/*
Custom Painter that overlays two images
This painter takes two ImageBitmap objects and draws them on top of each other
The second image is drawn with BlendMode.Overlay which blends the two images together
You can control the source offset and size for both images
*/

class OverlayImagePainter constructor(
    private val image: ImageBitmap,
    private val imageOverlay: ImageBitmap,
    private val srcOffset: IntOffset = IntOffset.Zero,
    private val srcSize: IntSize = IntSize(image.width, image.height),
    private val overlaySize: IntSize = IntSize(imageOverlay.width, imageOverlay.height)
) : Painter() {

    private val size: IntSize = validateSize(srcOffset, srcSize)

    /*
    The onDraw method is where you implement your custom drawing logic
    It provides a DrawScope which gives you access to drawing functions
    Here we draw the first image without any blend mode
    Then we draw the second image with Overlay blend mode to blend them together
    */
    override fun DrawScope.onDraw() {
        /* Draw the first image without any blend mode */
        drawImage(
            image,
            srcOffset,
            srcSize,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        /* Draw the second image with an Overlay blend mode to blend the two together */
        drawImage(
            imageOverlay,
            srcOffset,
            overlaySize,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.Overlay
        )
    }

    /*
    Return the dimension of the underlying ImageBitmap as its intrinsic width and height
    This influences the measurement and layout of the composable using this painter
    */
    override val intrinsicSize: Size get() = size.toSize()

    /*
    Validate that the source offset and size are within the bounds of the image
    This ensures we do not try to draw outside the image boundaries
    */
    private fun validateSize(srcOffset: IntOffset, srcSize: IntSize): IntSize {
        require(
            srcOffset.x >= 0 &&
                srcOffset.y >= 0 &&
                srcSize.width >= 0 &&
                srcSize.height >= 0 &&
                srcSize.width <= image.width &&
                srcSize.height <= image.height
        )
        return srcSize
    }
}

/*
Using the custom OverlayImagePainter
Load two ImageBitmap objects and create the custom painter
Then use it in an Image composable just like any other painter
Remember is used to cache the painter and avoid recreating it on every recomposition
Note: For this example we use the same image twice with different blend modes
In a real app you would use two different images
*/

@Composable
fun OverlayImageExample(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val customPainter = remember {
        OverlayImagePainter(dogImage, dogImage)
    }

    Image(
        painter = customPainter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.wrapContentSize()
    )
}

/*
Using custom painter with wrapContentSize
This lets the image size itself based on the intrinsicSize of the painter
*/

@Composable
fun OverlayImageWithWrapContent(){
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)

    val customPainter = remember {
        OverlayImagePainter(dogImage, dogImage)
    }

    Image(
        painter = customPainter,
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.wrapContentSize()
    )
}

/*
Display all overlay image examples
This shows the custom painter in action with different configurations
*/

@Composable
fun AllOverlayImageExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Custom Overlay Painter",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "This custom painter overlays two images using BlendMode.Overlay",
            fontSize = 14.sp
        )

        Text(text = "Dog with Rainbow Overlay", fontWeight = FontWeight.Medium)
        OverlayImageExample()

        Text(text = "With WrapContentSize", fontWeight = FontWeight.Medium)
        OverlayImageWithWrapContent()
    }
}
