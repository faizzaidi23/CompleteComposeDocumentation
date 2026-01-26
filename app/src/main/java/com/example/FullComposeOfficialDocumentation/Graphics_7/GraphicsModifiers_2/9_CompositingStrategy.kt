package com.example.FullComposeOfficialDocumentation.Graphics_7.GraphicsModifiers_2

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
COMPOSITING STRATEGY

Working with alpha and transparency might not be as simple as changing a single alpha value
In addition to changing an alpha there is also the option to set a CompositingStrategy on a graphicsLayer

A CompositingStrategy determines how the content of the composable is composited (put together)
with the other content already drawn on screen

Note: CompositingStrategy was introduced in Compose 1.4.0-alpha02

The different strategies are:
- Auto (default)
- Offscreen
- ModulateAlpha
*/

/*
Auto - default compositing strategy

The compositing strategy is determined by the rest of the graphicsLayer parameters
It renders the layer into an offscreen buffer if alpha is less than 1.0f or a RenderEffect is set

Whenever the alpha is less than 1f a compositing layer is created automatically
to render the contents and then draw this offscreen buffer to the destination with the corresponding alpha

Setting a RenderEffect or overscroll always renders content into an offscreen buffer
regardless of the CompositingStrategy set
*/

/*
Offscreen compositing strategy

The contents of the composable are always rasterized to an offscreen texture or bitmap before rendering to the destination
This is useful for applying BlendMode operations to mask content
and for performance when rendering complex sets of drawing instructions

An example of using CompositingStrategy.Offscreen is with BlendModes
If you want to remove parts of an Image composable by issuing a draw command that uses BlendMode.Clear
you need to set the compositingStrategy to CompositingStrategy.Offscreen

If you do not set it the BlendMode interacts with all the contents below it
*/

@Composable
fun CompositingStrategyOffscreenExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(120.dp)
            .aspectRatio(1f)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFFC5E1A5),
                        Color(0xFF80DEEA)
                    )
                )
            )
            .padding(8.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                val path = Path()
                path.addOval(
                    Rect(
                        topLeft = Offset.Zero,
                        bottomRight = Offset(size.width, size.height)
                    )
                )
                onDrawWithContent {
                    clipPath(path) {
                        /* This draws the actual image - if you do not call drawContent it will not render anything */
                        this@onDrawWithContent.drawContent()
                    }
                    val dotSize = size.width / 8f

                    /* Clip a white border for the content */
                    drawCircle(
                        Color.Black,
                        radius = dotSize,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        ),
                        blendMode = BlendMode.Clear
                    )

                    /* Draw the red circle indication */
                    drawCircle(
                        Color(0xFFEF5350), radius = dotSize * 0.8f,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        )
                    )
                }
            }
    )
}

/*
Without Offscreen compositing strategy

If you did not use CompositingStrategy.Offscreen
the results of applying BlendMode.Clear clears all the pixels in the destination
regardless of what was already set
leaving the window rendering buffer (black) visible

Many of the BlendModes that involve alpha will not work as expected without an offscreen buffer
*/

@Composable
fun CompositingStrategyNoOffscreenExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(120.dp)
            .aspectRatio(1f)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFFC5E1A5),
                        Color(0xFF80DEEA)
                    )
                )
            )
            .padding(8.dp)
            .drawWithCache {
                val path = Path()
                path.addOval(
                    Rect(
                        topLeft = Offset.Zero,
                        bottomRight = Offset(size.width, size.height)
                    )
                )
                onDrawWithContent {
                    clipPath(path) {
                        this@onDrawWithContent.drawContent()
                    }
                    val dotSize = size.width / 8f

                    drawCircle(
                        Color.Black,
                        radius = dotSize,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        ),
                        blendMode = BlendMode.Clear
                    )

                    drawCircle(
                        Color(0xFFEF5350), radius = dotSize * 0.8f,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        )
                    )
                }
            }
    )
}

/*
CompositingStrategy.Auto vs CompositingStrategy.Offscreen

When using CompositingStrategy.Offscreen an offscreen texture that is the size of the drawing area is created
and rendered back on screen

Any drawing commands that are done with this strategy are by default clipped to this region
*/

@Composable
fun CompositingStrategyComparison() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text("Auto - does not clip content", fontWeight = FontWeight.Medium)

        /* Does not clip content even with a graphics layer usage here */
        Canvas(
            modifier = Modifier
                .graphicsLayer()
                .size(100.dp)
                .border(2.dp, color = Color.Blue)
        ) {
            /* Drawing a size of 200 dp here outside the bounds */
            drawRect(color = Color.Magenta, size = Size(200.dp.toPx(), 200.dp.toPx()))
        }

        Spacer(modifier = Modifier.size(50.dp))

        Text("Offscreen - clips content", fontWeight = FontWeight.Medium)

        /* Clips content as offscreen buffer is created */
        Canvas(
            modifier = Modifier
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .size(100.dp)
                .border(2.dp, color = Color.Blue)
        ) {
            /* Drawing a size of 200 dp but content gets clipped */
            drawRect(color = Color.Red, size = Size(200.dp.toPx(), 200.dp.toPx()))
        }
    }
}

/*
Display all compositing strategy examples
*/

@Composable
fun AllCompositingStrategyExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Compositing Strategy",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Controls how content is composited with existing content",
            fontSize = 14.sp
        )

        Text(text = "With Offscreen Strategy (BlendMode works)", fontWeight = FontWeight.Medium)
        CompositingStrategyOffscreenExample()

        Text(text = "Without Offscreen (BlendMode issues)", fontWeight = FontWeight.Medium)
        CompositingStrategyNoOffscreenExample()

        Text(text = "Auto vs Offscreen Clipping", fontWeight = FontWeight.Medium)
        Box(modifier = Modifier.size(300.dp)) {
            CompositingStrategyComparison()
        }
    }
}

