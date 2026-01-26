package com.example.FullComposeOfficialDocumentation.Graphics_7.GraphicsModifiers_2

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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
ROTATION

Set rotationX to rotate horizontally
Set rotationY to rotate vertically
Set rotationZ to rotate on the Z axis which is standard rotation
This value is specified in degrees from 0 to 360
*/

@Composable
fun RotationXYZExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.rotationX = 90f
                this.rotationY = 275f
                this.rotationZ = 180f
            }
    )
}

/*
RotationZ only - standard 2D rotation

This is the most common type of rotation
Rotates the content around its center in the plane of the screen
*/

@Composable
fun RotationZExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.rotationZ = 45f
            }
    )
}

/*
RotationX - horizontal flip effect

Rotating around the X axis creates a horizontal flip effect
Like flipping a card horizontally
*/

@Composable
fun RotationXExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.rotationX = 45f
            }
    )
}

/*
RotationY - vertical flip effect

Rotating around the Y axis creates a vertical flip effect
Like flipping a card vertically
*/

@Composable
fun RotationYExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.rotationY = 45f
            }
    )
}

/*
90 degree rotation

Quarter turn rotation
*/

@Composable
fun Rotation90Example() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.rotationZ = 90f
            }
    )
}

/*
180 degree rotation

Half turn rotation - upside down
*/

@Composable
fun Rotation180Example() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.rotationZ = 180f
            }
    )
}

/*
270 degree rotation

Three quarter turn rotation
*/

@Composable
fun Rotation270Example() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.rotationZ = 270f
            }
    )
}

/*
Display all rotation examples
*/

@Composable
fun AllRotationExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Rotation Transformations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "rotationX, rotationY, rotationZ for 3D rotations (0-360 degrees)",
            fontSize = 14.sp
        )

        Text(text = "All Rotations Combined (X=90, Y=275, Z=180)", fontWeight = FontWeight.Medium)
        RotationXYZExample()

        Text(text = "RotationZ 45° (Standard 2D)", fontWeight = FontWeight.Medium)
        RotationZExample()

        Text(text = "RotationX 45° (Horizontal Flip)", fontWeight = FontWeight.Medium)
        RotationXExample()

        Text(text = "RotationY 45° (Vertical Flip)", fontWeight = FontWeight.Medium)
        RotationYExample()

        Text(text = "90° Rotation", fontWeight = FontWeight.Medium)
        Rotation90Example()

        Text(text = "180° Rotation", fontWeight = FontWeight.Medium)
        Rotation180Example()

        Text(text = "270° Rotation", fontWeight = FontWeight.Medium)
        Rotation270Example()
    }
}

