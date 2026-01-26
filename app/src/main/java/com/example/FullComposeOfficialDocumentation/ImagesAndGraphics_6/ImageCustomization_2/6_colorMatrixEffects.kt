package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.ImageCustomization_2

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
Transform your image using the color matrix ColorFilter option
A ColorMatrix is a 4x5 matrix that can transform the colors of an image
You can use it to create various effects like black and white contrast adjustment brightness adjustment and more
*/

/*
Black and white filter using saturation
To apply a black and white filter set the saturation to 0
Saturation of 0 means no color only shades of gray
Saturation of 1 is the default with full colors
*/

@Composable
fun ImageWithBlackAndWhiteFilter(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
        modifier = Modifier.size(200.dp)
    )
}

/*
Low saturation filter
Setting saturation to a value between 0 and 1 creates a desaturated washed out look
This is useful for creating a vintage or faded effect
*/

@Composable
fun ImageWithLowSaturation(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.3f) }),
        modifier = Modifier.size(200.dp)
    )
}

/*
High saturation filter
Setting saturation above 1 creates more vibrant and intense colors
This makes the image look more vivid and colorful
*/

@Composable
fun ImageWithHighSaturation(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(2f) }),
        modifier = Modifier.size(200.dp)
    )
}

/*
Adjust contrast and brightness
To change the contrast and brightness you can use a custom ColorMatrix
Contrast controls the difference between light and dark areas
Brightness controls the overall lightness or darkness of the image

The color matrix is a 4x5 matrix where each row represents RGBA channels
The format is: contrast 0 0 0 brightness
*/

@Composable
fun ImageWithIncreasedContrastAndBrightness(){
    val contrast = 2f // 0f to 10f where 1 is default
    val brightness = -180f // -255f to 255f where 0 is default

    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier.size(200.dp)
    )
}

/*
Decrease brightness example
Negative brightness values make the image darker
This can be useful for creating night mode effects or dimming images
*/

@Composable
fun ImageWithDecreasedBrightness(){
    val contrast = 1f
    val brightness = -80f

    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier.size(200.dp)
    )
}

/*
Increase brightness example
Positive brightness values make the image brighter
This can be useful for making dark images more visible
*/

@Composable
fun ImageWithIncreasedBrightness(){
    val contrast = 1f
    val brightness = 50f

    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier.size(200.dp)
    )
}

/*
Low contrast example
Setting contrast below 1 reduces the difference between light and dark areas
This creates a flatter washed out look
*/

@Composable
fun ImageWithLowContrast(){
    val contrast = 0.5f
    val brightness = 0f

    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier.size(200.dp)
    )
}

/*
High contrast example
Setting contrast above 1 increases the difference between light and dark areas
This makes the image more dramatic and vivid
*/

@Composable
fun ImageWithHighContrast(){
    val contrast = 2.5f
    val brightness = 0f

    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier.size(200.dp)
    )
}

/*
Invert colors of an image
To invert the colors set the ColorMatrix to multiply each color by -1 and add 255
This creates a negative image effect where light becomes dark and dark becomes light
Colors are also inverted to their opposites on the color wheel
*/

@Composable
fun ImageWithInvertedColors(){
    val colorMatrix = floatArrayOf(
        -1f, 0f, 0f, 0f, 255f,
        0f, -1f, 0f, 0f, 255f,
        0f, 0f, -1f, 0f, 255f,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier.size(200.dp)
    )
}

/*
Sepia tone filter
Sepia creates a warm brownish tone that gives images a vintage old photo look
This is achieved by adjusting the red green and blue channels with specific values
*/

@Composable
fun ImageWithSepiaTone(){
    val colorMatrix = floatArrayOf(
        0.393f, 0.769f, 0.189f, 0f, 0f,
        0.349f, 0.686f, 0.168f, 0f, 0f,
        0.272f, 0.534f, 0.131f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier.size(200.dp)
    )
}

/*
Display all color matrix examples together
This shows all the different color matrix effects in one screen for comparison
*/

@Composable
fun AllColorMatrixExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Color Matrix Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Original Image", fontWeight = FontWeight.Medium)
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            modifier = Modifier.size(200.dp)
        )

        Text(text = "Black and White (Saturation 0)", fontWeight = FontWeight.Medium)
        ImageWithBlackAndWhiteFilter()

        Text(text = "Low Saturation (0.3)", fontWeight = FontWeight.Medium)
        ImageWithLowSaturation()

        Text(text = "High Saturation (2.0)", fontWeight = FontWeight.Medium)
        ImageWithHighSaturation()

        Text(text = "High Contrast", fontWeight = FontWeight.Medium)
        ImageWithHighContrast()

        Text(text = "Low Contrast", fontWeight = FontWeight.Medium)
        ImageWithLowContrast()

        Text(text = "Increased Brightness", fontWeight = FontWeight.Medium)
        ImageWithIncreasedBrightness()

        Text(text = "Decreased Brightness", fontWeight = FontWeight.Medium)
        ImageWithDecreasedBrightness()

        Text(text = "Contrast and Brightness Adjusted", fontWeight = FontWeight.Medium)
        ImageWithIncreasedContrastAndBrightness()

        Text(text = "Inverted Colors", fontWeight = FontWeight.Medium)
        ImageWithInvertedColors()

        Text(text = "Sepia Tone", fontWeight = FontWeight.Medium)
        ImageWithSepiaTone()
    }
}

