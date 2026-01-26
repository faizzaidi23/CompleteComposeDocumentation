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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
ORIGIN - Transform Origin

A transformOrigin can be specified
It is then used as the point from which transformations take place

All the examples so far have used TransformOrigin.Center which is at (0.5f, 0.5f)
If you specify the origin at (0f, 0f) the transformations then start from the top-left corner of the composable

The transform origin is specified as fractions of the width and height
(0f, 0f) = top-left corner
(0.5f, 0.5f) = center (default)
(1f, 1f) = bottom-right corner
*/

@Composable
fun TransformOriginCenterExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin.Center
                this.rotationZ = 45f
            }
    )
}

/*
Transform origin at top-left

If you change the origin with a rotationZ transformation
you can see that the item rotates around the top left of the composable
*/

@Composable
fun TransformOriginTopLeftExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(0f, 0f)
                this.rotationX = 90f
                this.rotationY = 275f
                this.rotationZ = 180f
            }
    )
}

/*
Transform origin at top-right

Transformations happen from the top-right corner
*/

@Composable
fun TransformOriginTopRightExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(1f, 0f)
                this.rotationZ = 45f
            }
    )
}

/*
Transform origin at bottom-left

Transformations happen from the bottom-left corner
*/

@Composable
fun TransformOriginBottomLeftExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(0f, 1f)
                this.rotationZ = 45f
            }
    )
}

/*
Transform origin at bottom-right

Transformations happen from the bottom-right corner
*/

@Composable
fun TransformOriginBottomRightExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(1f, 1f)
                this.rotationZ = 45f
            }
    )
}

/*
Custom transform origin

You can specify any point as the transform origin using fractions
*/

@Composable
fun CustomTransformOriginExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(0.25f, 0.75f)
                this.rotationZ = 45f
            }
    )
}

/*
Transform origin with scale

Transform origin also affects scaling transformations
*/

@Composable
fun TransformOriginWithScaleExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.transformOrigin = TransformOrigin(0f, 0f)
                this.scaleX = 1.5f
                this.scaleY = 1.5f
            }
    )
}

/*
Display all transform origin examples
*/

@Composable
fun AllTransformOriginExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Transform Origin",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "transformOrigin sets the point from which transformations occur",
            fontSize = 14.sp
        )

        Text(text = "Center Origin (0.5, 0.5) - Default", fontWeight = FontWeight.Medium)
        TransformOriginCenterExample()

        Text(text = "Top-Left Origin (0, 0)", fontWeight = FontWeight.Medium)
        TransformOriginTopLeftExample()

        Text(text = "Top-Right Origin (1, 0)", fontWeight = FontWeight.Medium)
        TransformOriginTopRightExample()

        Text(text = "Bottom-Left Origin (0, 1)", fontWeight = FontWeight.Medium)
        TransformOriginBottomLeftExample()

        Text(text = "Bottom-Right Origin (1, 1)", fontWeight = FontWeight.Medium)
        TransformOriginBottomRightExample()

        Text(text = "Custom Origin (0.25, 0.75)", fontWeight = FontWeight.Medium)
        CustomTransformOriginExample()

        Text(text = "Origin with Scale", fontWeight = FontWeight.Medium)
        TransformOriginWithScaleExample()
    }
}

