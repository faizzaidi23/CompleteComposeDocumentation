package com.example.FullComposeOfficialDocumentation.Graphics_7.GraphicsModifiers_2

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
MODULATEALPHA COMPOSITING STRATEGY

This composition strategy modulates the alpha for each of the drawing instructions
recorded within the graphicsLayer

It will not create an offscreen buffer for alpha below 1.0f unless a RenderEffect is set
so it can be more efficient for alpha rendering

However it can provide different results for overlapping content

For use cases where it is known in advance that content is not overlapping
this can provide better performance than CompositingStrategy.Auto with alpha values less than 1
*/

/*
Helper function to draw overlapping squares

This draws three overlapping colored squares
*/

private fun DrawScope.drawSquares() {
    val size = Size(100.dp.toPx(), 100.dp.toPx())

    drawRect(color = Color(0xFFEF5350), size = size)

    drawRect(
        color = Color(0xFF7E57C2), size = size,
        topLeft = androidx.compose.ui.geometry.Offset(size.width / 4f, size.height / 4f)
    )

    drawRect(
        color = Color(0xFFFFCA28), size = size,
        topLeft = androidx.compose.ui.geometry.Offset(size.width / 4f * 2f, size.height / 4f * 2f)
    )
}

/*
Base drawing with no alpha applied

This shows the original overlapping squares without any transparency
*/

@Composable
fun ModulateAlphaBaseExample() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("No Alpha Applied", fontWeight = FontWeight.Medium)

        Canvas(modifier = Modifier.size(200.dp)) {
            drawSquares()
        }
    }
}

/*
Alpha applied to whole composable using Auto strategy

When alpha 0.5f is applied to the whole composable with Auto strategy
all the squares become semi-transparent together
The entire layer is rendered to an offscreen buffer then composited with the alpha
*/

@Composable
fun ModulateAlphaAutoExample() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Alpha 0.5 with Auto Strategy", fontWeight = FontWeight.Medium)

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    alpha = 0.5f
                }
        ) {
            drawSquares()
        }
    }
}

/*
Alpha applied with ModulateAlpha strategy

When using ModulateAlpha the alpha is applied to each individual draw call
This means overlapping areas get the alpha applied multiple times
resulting in a different visual appearance
*/

@Composable
fun ModulateAlphaStrategyExample() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Alpha 0.75 with ModulateAlpha", fontWeight = FontWeight.Medium)

        Canvas(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.ModulateAlpha
                    alpha = 0.75f
                }
        ) {
            drawSquares()
        }
    }
}

/*
When to use ModulateAlpha

Use ModulateAlpha when:
- Content is not overlapping
- You want better performance than Auto with alpha less than 1
- You do not need a RenderEffect

Do not use ModulateAlpha when:
- Content overlaps and you need consistent alpha across all content
- You are applying BlendModes that require an offscreen buffer
*/

/*
Display all ModulateAlpha examples
*/

@Composable
fun AllModulateAlphaExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "ModulateAlpha Strategy",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Modulates alpha for each drawing instruction",
            fontSize = 14.sp
        )

        ModulateAlphaBaseExample()

        Spacer(modifier = Modifier.size(16.dp))

        ModulateAlphaAutoExample()

        Spacer(modifier = Modifier.size(16.dp))

        ModulateAlphaStrategyExample()

        Text(
            text = "Notice how ModulateAlpha applies alpha to each square individually, while Auto applies it to the entire layer",
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

