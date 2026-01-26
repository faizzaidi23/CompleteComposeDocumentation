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
ALPHA - Opacity

Modifier.graphicsLayer can be used to set an alpha (opacity) for the whole layer
1.0f is fully opaque and 0.0f is invisible

Note: When an alpha is set to less than 1.0f
the entire contents of the layer are drawn to an offscreen buffer
if the CompositingStrategy is not set to ModulateAlpha
See the CompositingStrategy section for more information
*/

@Composable
fun AlphaFullyOpaqueExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.alpha = 1.0f
            }
    )
}

/*
Alpha 50 percent

Half transparent image
*/

@Composable
fun AlphaHalfExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.alpha = 0.5f
            }
    )
}

/*
Alpha 25 percent

Mostly transparent image
*/

@Composable
fun AlphaQuarterExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.alpha = 0.25f
            }
    )
}

/*
Alpha 75 percent

Slightly transparent image
*/

@Composable
fun AlphaThreeQuartersExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.alpha = 0.75f
            }
    )
}

/*
Alpha 10 percent

Very transparent almost invisible
*/

@Composable
fun AlphaVeryLowExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.alpha = 0.1f
            }
    )
}

/*
Alpha 90 percent

Barely transparent
*/

@Composable
fun AlphaVeryHighExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.alpha = 0.9f
            }
    )
}

/*
Display all alpha examples
*/

@Composable
fun AllAlphaExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Alpha (Opacity)",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Alpha controls opacity: 1.0f = opaque, 0.0f = invisible",
            fontSize = 14.sp
        )

        Text(text = "Alpha 1.0 (Fully Opaque)", fontWeight = FontWeight.Medium)
        AlphaFullyOpaqueExample()

        Text(text = "Alpha 0.9 (90%)", fontWeight = FontWeight.Medium)
        AlphaVeryHighExample()

        Text(text = "Alpha 0.75 (75%)", fontWeight = FontWeight.Medium)
        AlphaThreeQuartersExample()

        Text(text = "Alpha 0.5 (50%)", fontWeight = FontWeight.Medium)
        AlphaHalfExample()

        Text(text = "Alpha 0.25 (25%)", fontWeight = FontWeight.Medium)
        AlphaQuarterExample()

        Text(text = "Alpha 0.1 (10%)", fontWeight = FontWeight.Medium)
        AlphaVeryLowExample()
    }
}

