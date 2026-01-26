package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
TILE MODE - REPEATING PATTERNS

Each gradient brush has the option to set a TileMode on it
A TileMode will only tile the gradient if the size of the area is bigger than the Brush size

You may not notice the TileMode if you haven't set a start and end for the gradient
as it'll default to fill the whole area

TileMode is useful when you want to repeat a gradient pattern across a larger area
*/

/*
TileMode.Repeated

Edge is repeated from last color to first
The gradient pattern repeats seamlessly across the entire area
*/

@Composable
fun TileModeRepeatedExample() {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val tileSize = with(LocalDensity.current) {
        50.dp.toPx()
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listColors,
                    endX = tileSize,
                    tileMode = TileMode.Repeated
                )
            )
    )
}

/*
TileMode.Mirror

Edge is mirrored from last color to first
Each repetition is a mirror image of the previous one
Creates a symmetric pattern
*/

@Composable
fun TileModeMirrorExample() {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val tileSize = with(LocalDensity.current) {
        50.dp.toPx()
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listColors,
                    endX = tileSize,
                    tileMode = TileMode.Mirror
                )
            )
    )
}

/*
TileMode.Clamp (Default)

Edge is clamped to the final color
It'll then paint the closest color for the rest of the region
This is the default behavior when no TileMode is specified
*/

@Composable
fun TileModeClampExample() {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val tileSize = with(LocalDensity.current) {
        50.dp.toPx()
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listColors,
                    endX = tileSize,
                    tileMode = TileMode.Clamp
                )
            )
    )
}

/*
TileMode.Decal

Render only up to the size of the bounds
TileMode.Decal leverages transparent black to sample content outside the original bounds
whereas TileMode.Clamp samples the edge color

Note: TileMode.Decal is only available on API 31+
Use TileMode.isSupported() to determine if a TileMode is supported on a device
If a TileMode that is not supported is used, the default of TileMode.Clamp is applied
*/

@Composable
fun TileModeDecalExample() {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val tileSize = with(LocalDensity.current) {
        50.dp.toPx()
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.horizontalGradient(
                    listColors,
                    endX = tileSize,
                    tileMode = TileMode.Decal
                )
            )
    )
}

/*
Vertical Gradient with TileMode

TileMode works in a similar way for other directional gradients
The difference being the direction that the repetition occurs
*/

@Composable
fun VerticalTileModeExample() {
    val listColors = listOf(Color.Cyan, Color.Magenta, Color.Yellow)
    val tileSize = with(LocalDensity.current) {
        40.dp.toPx()
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(
                Brush.verticalGradient(
                    listColors,
                    endY = tileSize,
                    tileMode = TileMode.Mirror
                )
            )
    )
}

/*
All TileModes Comparison

This composable displays all tile modes side by side for comparison
*/

@Composable
fun AllTileModesShowcase() {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val tileSize = with(LocalDensity.current) {
        50.dp.toPx()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "TileMode Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Each gradient is 50dp, repeated in 200dp area",
            fontSize = 14.sp,
            color = Color.Gray
        )

        // TileMode.Repeated
        Text("TileMode.Repeated", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        listColors,
                        endX = tileSize,
                        tileMode = TileMode.Repeated
                    )
                )
        )

        // TileMode.Mirror
        Text("TileMode.Mirror", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        listColors,
                        endX = tileSize,
                        tileMode = TileMode.Mirror
                    )
                )
        )

        // TileMode.Clamp
        Text("TileMode.Clamp (Default)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        listColors,
                        endX = tileSize,
                        tileMode = TileMode.Clamp
                    )
                )
        )

        // TileMode.Decal
        Text("TileMode.Decal (API 31+)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    Brush.horizontalGradient(
                        listColors,
                        endX = tileSize,
                        tileMode = TileMode.Decal
                    )
                )
        )
    }
}

