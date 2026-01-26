package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.ImageCustomization_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
To blur an image use Modifier.blur supplying the radiusX and radiusY
radiusX specifies the blur radius in the horizontal direction
radiusY specifies the blur radius in the vertical direction
Note the blur effect is only supported on Android 12 and later
Attempts to use this modifier on older Android versions are ignored
*/

/*
Basic blur with BlurredEdgeTreatment shape
When blurring images it is recommended to use BlurredEdgeTreatment with a shape
This keeps the edges of the image sharp while blurring the content inside
The shape parameter defines the outline of the blur area
*/

@Composable
fun ImageWithBasicBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            )
    )
}

/*
Light blur effect
A small blur radius creates a subtle soft focus effect
This is useful for creating a gentle blur without losing too much detail
*/

@Composable
fun ImageWithLightBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 3.dp,
                radiusY = 3.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            )
    )
}

/*
Heavy blur effect
A large blur radius creates a strong blur effect
This makes the image very indistinct and out of focus
Useful for background images or privacy obscuring
*/

@Composable
fun ImageWithHeavyBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 20.dp,
                radiusY = 20.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            )
    )
}

/*
Horizontal blur only
You can blur only in one direction by setting one radius to 0
This creates a directional motion blur effect
Here we blur only horizontally
*/

@Composable
fun ImageWithHorizontalBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 15.dp,
                radiusY = 0.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            )
    )
}

/*
Vertical blur only
Similar to horizontal blur but only blurs vertically
This creates a vertical motion blur effect
*/

@Composable
fun ImageWithVerticalBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 0.dp,
                radiusY = 15.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            )
    )
}

/*
Blur with Unbounded edge treatment
BlurredEdgeTreatment.Unbounded allows the blur to extend beyond the original bounds
This is used for blurring arbitrary renderings that render outside the bounds
For images this makes the edges appear blurred instead of sharp
Notice how the edges look fuzzy compared to the shaped version
*/

@Composable
fun ImageWithUnboundedBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment.Unbounded
            )
            .clip(RoundedCornerShape(8.dp))
    )
}

/*
Blur with Rectangle edge treatment
Using BlurredEdgeTreatment with RoundedCornerShape(0.dp) creates a sharp rectangle
This keeps the blur contained within a rectangle shape
*/

@Composable
fun ImageWithRectangleBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(0.dp))
            )
    )
}

/*
Blur with circular edge treatment
Using a large corner radius creates a circular blur area
This is useful for creating circular blurred profile pictures or avatars
*/

@Composable
fun ImageWithCircularBlur(){
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(75.dp))
            )
    )
}

/*
Display all blur examples together
This shows all the different blur effects in one screen for comparison
Note these examples will only work on Android 12 and above
On older versions the images will appear without blur
*/

@Composable
fun AllBlurExamples(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Blur Effect Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Note: Blur effects require Android 12+",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )

        Text(text = "Original Image", fontWeight = FontWeight.Medium)
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )

        Text(text = "Light Blur (3dp)", fontWeight = FontWeight.Medium)
        ImageWithLightBlur()

        Text(text = "Basic Blur (10dp)", fontWeight = FontWeight.Medium)
        ImageWithBasicBlur()

        Text(text = "Heavy Blur (20dp)", fontWeight = FontWeight.Medium)
        ImageWithHeavyBlur()

        Text(text = "Horizontal Blur Only", fontWeight = FontWeight.Medium)
        ImageWithHorizontalBlur()

        Text(text = "Vertical Blur Only", fontWeight = FontWeight.Medium)
        ImageWithVerticalBlur()

        Text(text = "Rectangle Blur", fontWeight = FontWeight.Medium)
        ImageWithRectangleBlur()

        Text(text = "Circular Blur", fontWeight = FontWeight.Medium)
        ImageWithCircularBlur()

        Text(text = "Unbounded Blur (Fuzzy Edges)", fontWeight = FontWeight.Medium)
        ImageWithUnboundedBlur()
    }
}

