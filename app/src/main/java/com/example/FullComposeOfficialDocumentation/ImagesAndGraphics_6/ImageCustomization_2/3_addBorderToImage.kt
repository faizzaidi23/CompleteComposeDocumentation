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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
A common operation is to combine the Modifier.border with Modifier.clip to create a border around an image
The order of modifiers matters here
First apply the border then add padding then clip the image
*/

/*
Simple border around a circular image
This example shows how to add a solid color border around a circular image
The borderWidth controls how thick the border is
The padding ensures the border is visible and not cut off by the clip
*/

@Composable
fun ImageWithCircleBorder(){
    val borderWidth = 4.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, Color.Yellow),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

/*
Border around a rounded rectangle image
You can also add borders to images with rounded corners
Just use RoundedCornerShape instead of CircleShape
*/

@Composable
fun ImageWithRoundedBorder(){
    val borderWidth = 4.dp
    val cornerRadius = 16.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, Color.Blue),
                RoundedCornerShape(cornerRadius)
            )
            .padding(borderWidth)
            .clip(RoundedCornerShape(cornerRadius))
    )
}

/*
Gradient border around an image
To create a gradient border you can use the Brush API
This creates a rainbow gradient that sweeps around the circle
The remember function ensures the gradient is only created once and not recreated on every recomposition
*/

@Composable
fun ImageWithGradientBorder(){
    /*
    Create a rainbow gradient brush using sweepGradient
    This creates a circular gradient that goes around the center point
    The colors transition smoothly from purple through red orange yellow green and blue
    */
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }

    val borderWidth = 4.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, rainbowColorsBrush),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

/*
Linear gradient border
This creates a border with a linear gradient that goes from top to bottom
You can adjust the angle and colors to create different effects
*/

@Composable
fun ImageWithLinearGradientBorder(){
    val gradientBrush = remember {
        Brush.verticalGradient(
            listOf(
                Color(0xFFFF6B6B),
                Color(0xFF4ECDC4),
                Color(0xFF45B7D1)
            )
        )
    }

    val borderWidth = 6.dp
    val cornerRadius = 24.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, gradientBrush),
                RoundedCornerShape(cornerRadius)
            )
            .padding(borderWidth)
            .clip(RoundedCornerShape(cornerRadius))
    )
}

/*
Thick border example
This shows how changing the border width affects the appearance
A thicker border can create a bold frame effect
*/

@Composable
fun ImageWithThickBorder(){
    val borderWidth = 8.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, Color.Red),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

/*
Radial gradient border
This creates a border with a radial gradient that emanates from the center
*/

@Composable
fun ImageWithRadialGradientBorder(){
    val radialBrush = remember {
        Brush.radialGradient(
            listOf(
                Color(0xFFFFD700),
                Color(0xFFFF8C00),
                Color(0xFFFF4500)
            )
        )
    }

    val borderWidth = 5.dp

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(
                BorderStroke(borderWidth, radialBrush),
                CircleShape
            )
            .padding(borderWidth)
            .clip(CircleShape)
    )
}

/*
Display all border examples together
This shows all the different border styles in one screen for comparison
*/

@Composable
fun AllBorderExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Image Border Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Yellow Circle Border", fontWeight = FontWeight.Medium)
        ImageWithCircleBorder()

        Text(text = "Blue Rounded Rectangle Border", fontWeight = FontWeight.Medium)
        ImageWithRoundedBorder()

        Text(text = "Rainbow Gradient Border", fontWeight = FontWeight.Medium)
        ImageWithGradientBorder()

        Text(text = "Linear Gradient Border", fontWeight = FontWeight.Medium)
        ImageWithLinearGradientBorder()

        Text(text = "Thick Red Border", fontWeight = FontWeight.Medium)
        ImageWithThickBorder()

        Text(text = "Radial Gradient Border", fontWeight = FontWeight.Medium)
        ImageWithRadialGradientBorder()
    }
}

