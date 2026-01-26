package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.ImageCustomization_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
This file demonstrates combining multiple image customization techniques
You can stack multiple modifiers and effects to create sophisticated image styles
The order of modifiers matters and affects the final result
*/

/*
Profile picture with border and clip
A common pattern for user avatars is a circular image with a colored border
This combines ContentScale.Crop clip and border modifiers
*/

@Composable
fun ProfilePictureStyle(){
    val borderWidth = 4.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(120.dp)
            .border(
                BorderStroke(borderWidth, Color(0xFF2196F3)),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

/*
Instagram style circular profile with gradient border
This mimics the Instagram story ring with a gradient border around a circular image
*/

@Composable
fun InstagramStyleProfile(){
    val gradientBrush = remember {
        Brush.linearGradient(
            listOf(
                Color(0xFFE1306C),
                Color(0xFFFD1D1D),
                Color(0xFFF77737),
                Color(0xFFFCAF45)
            )
        )
    }
    val borderWidth = 3.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .border(
                BorderStroke(borderWidth, gradientBrush),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

/*
Vintage photo effect
Combines sepia tone with rounded corners and a subtle border
Creates an old photograph aesthetic
*/

@Composable
fun VintagePhotoStyle(){
    val sepiaMatrix = floatArrayOf(
        0.393f, 0.769f, 0.189f, 0f, 0f,
        0.349f, 0.686f, 0.168f, 0f, 0f,
        0.272f, 0.534f, 0.131f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(sepiaMatrix)),
        modifier = Modifier
            .size(200.dp)
            .border(BorderStroke(2.dp, Color(0xFF8D6E63)), RoundedCornerShape(8.dp))
            .padding(2.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

/*
Background blur effect
Creates a blurred background image useful for cards or hero sections
Combines blur with aspect ratio control
*/

@Composable
fun BlurredBackgroundStyle(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(250.dp)
            .blur(
                radiusX = 15.dp,
                radiusY = 15.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(16.dp))
            )
    )
}

/*
Black and white circular thumbnail
Useful for creating monochrome profile pictures or artistic effects
Combines grayscale filter with circular clipping
*/

@Composable
fun BlackAndWhiteCircularThumbnail(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
    )
}

/*
High contrast card image
Creates a dramatic high contrast image with rounded corners
Good for hero images or feature cards
*/

@Composable
fun HighContrastCardImage(){
    val contrast = 2.0f
    val brightness = -50f

    val colorMatrix = floatArrayOf(
        contrast, 0f, 0f, 0f, brightness,
        0f, contrast, 0f, 0f, brightness,
        0f, 0f, contrast, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

/*
Faded background image
Creates a desaturated blurred image perfect for backgrounds
Combines low saturation with blur effect
*/

@Composable
fun FadedBackgroundImage(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.3f) }),
        modifier = Modifier
            .size(250.dp)
            .blur(
                radiusX = 8.dp,
                radiusY = 8.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(12.dp))
            )
    )
}

/*
Neon border effect
Creates a vibrant colorful border effect with high saturation
Perfect for modern app designs
*/

@Composable
fun NeonBorderEffect(){
    val neonBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFFFF00FF),
                Color(0xFF00FFFF),
                Color(0xFFFFFF00),
                Color(0xFFFF00FF)
            )
        )
    }

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(1.5f) }),
        modifier = Modifier
            .size(150.dp)
            .border(BorderStroke(4.dp, neonBrush), RoundedCornerShape(20.dp))
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

/*
Soft focus portrait
Creates a soft dreamy look by combining light blur with increased brightness
Good for portrait photos
*/

@Composable
fun SoftFocusPortrait(){
    val brightness = 30f
    val colorMatrix = floatArrayOf(
        1f, 0f, 0f, 0f, brightness,
        0f, 1f, 0f, 0f, brightness,
        0f, 0f, 1f, 0f, brightness,
        0f, 0f, 0f, 1f, 0f
    )

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
        modifier = Modifier
            .size(180.dp)
            .blur(
                radiusX = 2.dp,
                radiusY = 2.dp,
                edgeTreatment = BlurredEdgeTreatment(CircleShape)
            )
    )
}

/*
Display all combined effect examples
This shows practical real-world styling patterns you can use in your apps
*/

@Composable
fun AllCombinedEffectsExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Combined Effects Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Profile Picture Style", fontWeight = FontWeight.Medium)
        ProfilePictureStyle()

        Text(text = "Instagram Story Style", fontWeight = FontWeight.Medium)
        InstagramStyleProfile()

        Text(text = "Vintage Photo Effect", fontWeight = FontWeight.Medium)
        VintagePhotoStyle()

        Text(text = "Blurred Background", fontWeight = FontWeight.Medium)
        BlurredBackgroundStyle()

        Text(text = "Black and White Circular", fontWeight = FontWeight.Medium)
        BlackAndWhiteCircularThumbnail()

        Text(text = "High Contrast Card", fontWeight = FontWeight.Medium)
        HighContrastCardImage()

        Text(text = "Faded Background", fontWeight = FontWeight.Medium)
        FadedBackgroundImage()

        Text(text = "Neon Border Effect", fontWeight = FontWeight.Medium)
        NeonBorderEffect()

        Text(text = "Soft Focus Portrait", fontWeight = FontWeight.Medium)
        SoftFocusPortrait()
    }
}

