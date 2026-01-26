package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.ImageCustomization_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
To transform an image into a custom aspect ratio use Modifier.aspectRatio
The aspect ratio is the proportional relationship between width and height
For example 16:9 means the width is 16 units and height is 9 units
*/

/*
Aspect ratio 16:9 example
This is commonly used for widescreen videos and modern displays
The image will be wider than it is tall
16 divided by 9 equals approximately 1.78
*/

@Composable
fun ImageWith16By9AspectRatio(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    )
}

/*
Aspect ratio 4:3 example
This is the traditional television and monitor aspect ratio
The image will be slightly wider than it is tall
4 divided by 3 equals approximately 1.33
*/

@Composable
fun ImageWith4By3AspectRatio(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4f / 3f)
    )
}

/*
Square aspect ratio 1:1 example
This creates a perfect square image
The width and height are equal
1 divided by 1 equals 1
*/

@Composable
fun ImageWithSquareAspectRatio(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}

/*
Portrait aspect ratio 9:16 example
This is the reverse of 16:9 used for portrait mode videos and stories
The image will be taller than it is wide
9 divided by 16 equals 0.5625
*/

@Composable
fun ImageWith9By16AspectRatio(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(9f / 16f)
    )
}

/*
Aspect ratio 21:9 example
This is an ultra-wide aspect ratio used for cinematic content
The image will be very wide compared to its height
21 divided by 9 equals approximately 2.33
*/

@Composable
fun ImageWith21By9AspectRatio(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(21f / 9f)
    )
}

/*
Aspect ratio 3:2 example
This is commonly used in photography and print media
The image will be moderately wider than it is tall
3 divided by 2 equals 1.5
*/

@Composable
fun ImageWith3By2AspectRatio(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 2f)
    )
}

/*
Custom aspect ratio example
You can use any custom ratio you want
Here we use 2.5:1 for an extra wide banner style image
*/

@Composable
fun ImageWithCustomAspectRatio(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.5f)
    )
}

/*
Display all aspect ratio examples together
This shows all the different aspect ratios in one screen for comparison
Notice how the same image looks different with each aspect ratio
*/

@Composable
fun AllAspectRatioExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Aspect Ratio Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "16:9 (Widescreen)", fontWeight = FontWeight.Medium)
        ImageWith16By9AspectRatio()

        Text(text = "4:3 (Traditional)", fontWeight = FontWeight.Medium)
        ImageWith4By3AspectRatio()

        Text(text = "1:1 (Square)", fontWeight = FontWeight.Medium)
        ImageWithSquareAspectRatio()

        Text(text = "3:2 (Photography)", fontWeight = FontWeight.Medium)
        ImageWith3By2AspectRatio()

        Text(text = "21:9 (Ultra-wide)", fontWeight = FontWeight.Medium)
        ImageWith21By9AspectRatio()

        Text(text = "2.5:1 (Custom Banner)", fontWeight = FontWeight.Medium)
        ImageWithCustomAspectRatio()
    }
}

