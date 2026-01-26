package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.ImageCustomization_2

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
To make an image fit into a shape use the built-in clip modifier
Clipping allows you to cut your image into different shapes like circles squares with rounded corners or custom shapes
*/

/*
Clip an image into a circle shape
Use Modifier.clip(CircleShape) to crop your image into a perfect circle
This is commonly used for profile pictures or avatars
Remember to use ContentScale.Crop so the image fills the entire circle without empty spaces
*/

@Composable
fun ClipImageToCircle(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
}

/*
Clip an image with rounded corners
Use Modifier.clip(RoundedCornerShape(16.dp)) to create an image with rounded corners
You can change the 16.dp value to make the corners more or less rounded
Smaller values make sharper corners and larger values make more rounded corners
*/

@Composable
fun ClipImageToRoundedCorners(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

/*
Different corner radius examples
You can also specify different corner sizes for each corner individually
For example you can make only the top corners rounded
*/

@Composable
fun ClipImageWithDifferentCorners(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
    )
}

/*
Create a custom clipping shape
You can create your own custom shapes by extending the Shape interface
and providing a Path that defines the outline of your shape
Here we create a SquashedOval shape that makes an oval that is narrower than a circle
*/

class SquashedOval : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            /*
            We create an Oval that starts at 1/4 of the width and ends at 3/4 of the width of the container
            This makes the oval appear squashed horizontally
            */
            addOval(
                Rect(
                    left = size.width / 4f,
                    top = 0f,
                    right = size.width * 3 / 4f,
                    bottom = size.height
                )
            )
        }
        return Outline.Generic(path = path)
    }
}

/*
Use the custom shape to clip an image
Once you have created a custom shape you can use it just like any built-in shape
Simply pass your custom shape to the clip modifier
*/

@Composable
fun ClipImageToCustomShape(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(SquashedOval())
    )
}

/*
Another custom shape example - Triangle
This creates a triangle shape by drawing a path with three points
*/

class TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            /*
            Move to the top center point
            Draw a line to the bottom left corner
            Draw a line to the bottom right corner
            Close the path to complete the triangle
            */
            moveTo(size.width / 2f, 0f)
            lineTo(0f, size.height)
            lineTo(size.width, size.height)
            close()
        }
        return Outline.Generic(path = path)
    }
}

@Composable
fun ClipImageToTriangle(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(TriangleShape())
    )
}

/*
Display all clipping examples together
This shows all the different clipping shapes in one screen for comparison
*/

@Composable
fun AllClipShapeExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Image Clipping Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Circle Shape", fontWeight = FontWeight.Medium)
        ClipImageToCircle()

        Text(text = "Rounded Corners (16.dp)", fontWeight = FontWeight.Medium)
        ClipImageToRoundedCorners()

        Text(text = "Top Corners Only", fontWeight = FontWeight.Medium)
        ClipImageWithDifferentCorners()

        Text(text = "Custom Squashed Oval", fontWeight = FontWeight.Medium)
        ClipImageToCustomShape()

        Text(text = "Custom Triangle", fontWeight = FontWeight.Medium)
        ClipImageToTriangle()
    }
}

