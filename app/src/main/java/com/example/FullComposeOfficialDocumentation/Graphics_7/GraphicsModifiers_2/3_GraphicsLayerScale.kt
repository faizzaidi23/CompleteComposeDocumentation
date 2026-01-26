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
MODIFIER.GRAPHICSLAYER - Apply transformations to composables

Modifier.graphicsLayer is a modifier that makes the content of the composable draw into a draw layer
A layer provides a few different functions such as:

Isolation for its drawing instructions similar to RenderNode
Drawing instructions captured as part of a layer can be re-issued efficiently by the rendering pipeline
without re-executing application code

Transformations that apply to all the drawing instructions contained within a layer

Rasterization for composition capabilities
When a layer is rasterized its drawing instructions are executed and the output is captured
into an offscreen buffer
Compositing such a buffer for subsequent frames is faster than executing the individual instructions
but it will behave as a bitmap when transforms like scaling or rotation are applied
*/

/*
TRANSFORMATIONS

Modifier.graphicsLayer provides isolation for its drawing instructions
Various transformations can be applied using Modifier.graphicsLayer
These can be animated or modified without needing to re-execute the drawing lambda

Modifier.graphicsLayer does not change the measured size or placement of your composable
as it only affects the draw phase
This means your composable might overlap others if it ends up drawing outside of its layout bounds

Note: You should prefer the lambda version of this modifier when performing animations
or using a State object to update a graphicsLayer property
*/

/*
SCALE - Increase size

scaleX and scaleY enlarges or shrinks content in the horizontal or vertical direction respectively
A value of 1.0f indicates no change in scale
A value of 0.5f means half of the dimension
*/

@Composable
fun ScaleTransformExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.scaleX = 1.2f
                this.scaleY = 0.8f
            }
    )
}

/*
Uniform scale example

Scaling equally in both directions
*/

@Composable
fun UniformScaleExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.scaleX = 1.5f
                this.scaleY = 1.5f
            }
    )
}

/*
Small scale example

Making content smaller
*/

@Composable
fun SmallScaleExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.scaleX = 0.5f
                this.scaleY = 0.5f
            }
    )
}

/*
Display all scale transformation examples
*/

@Composable
fun AllScaleTransformExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Scale Transformations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "scaleX and scaleY control horizontal and vertical scaling",
            fontSize = 14.sp
        )

        Text(text = "Non-uniform Scale (scaleX=1.2, scaleY=0.8)", fontWeight = FontWeight.Medium)
        ScaleTransformExample()

        Text(text = "Uniform Scale (1.5x)", fontWeight = FontWeight.Medium)
        UniformScaleExample()

        Text(text = "Small Scale (0.5x)", fontWeight = FontWeight.Medium)
        SmallScaleExample()
    }
}

