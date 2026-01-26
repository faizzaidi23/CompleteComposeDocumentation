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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
CHANGING BRUSH SIZE

If you know the size of the area in which your brush will be drawn, you can set the tile endX
as we've seen in the TileMode section

If you are in a DrawScope, you can use its size property to get the size of the area

If you don't know the size of your drawing area (for example if the Brush is assigned to Text),
you can extend ShaderBrush and utilize the size of the drawing area in the createShader function
*/

/*
Custom Shader Brush - Repeating Pattern

In this example, divide the size by 4 to repeat the pattern 4 times
This approach works even when the final size is unknown at creation time
*/

@Composable
fun CustomShaderBrushExample() {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)

    val customBrush = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                return LinearGradientShader(
                    colors = listColors,
                    from = Offset.Zero,
                    to = Offset(size.width / 4f, 0f),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(customBrush)
    )
}

/*
Custom Radial Gradient - Better Sizing

When the radial gradient is changed to set the radius size to the max dimension,
it produces a better radial gradient effect that fills the entire area

Without custom sizing, the gradient would only fill based on the smaller dimension
*/

@Composable
fun LargeRadialGradientExample() {
    val largeRadialGradient = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val biggerDimension = maxOf(size.height, size.width)
                return RadialGradientShader(
                    colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                    center = size.center,
                    radius = biggerDimension / 2f,
                    colorStops = listOf(0f, 0.95f)
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
    )
}

/*
Default Radial Gradient (for comparison)

This shows the default radial gradient without custom sizing
The gradient's center appears as the center of the smaller dimension
*/

@Composable
fun DefaultRadialGradientExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    listOf(Color(0xFF2be4dc), Color(0xFF243484))
                )
            )
    )
}

/*
Custom Shader with Dynamic Sizing

This demonstrates a custom shader that adapts to different sizes
The pattern repeats based on the actual drawing area size
*/

@Composable
fun DynamicSizeShaderExample() {
    val customBrush = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                // Create a gradient that repeats every 30dp worth of pixels
                return LinearGradientShader(
                    colors = listOf(
                        Color(0xFFFF6B6B),
                        Color(0xFF4ECDC4),
                        Color(0xFF45B7D1)
                    ),
                    from = Offset.Zero,
                    to = Offset(size.width / 6f, 0f),
                    tileMode = TileMode.Repeated
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(customBrush)
    )
}

/*
Vertical Custom Shader

Custom shader brushes work with vertical gradients too
*/

@Composable
fun VerticalCustomShaderExample() {
    val customBrush = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                return LinearGradientShader(
                    colors = listOf(Color.Magenta, Color.Cyan, Color.Yellow),
                    from = Offset.Zero,
                    to = Offset(0f, size.height / 3f),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .requiredSize(200.dp)
            .background(customBrush)
    )
}

/*
Important Note about Brush Size Allocation

It is worth noting that the actual size that is passed into the creation of the shader
is determined from where it is invoked

By default, Brush will reallocate its Shader internally if:
- The size is different from the last creation of the Brush
- A state object used in creation of the shader has changed

This means the shader is recreated when needed, ensuring it always fits the drawing area
*/

@Composable
fun BrushSizeShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Custom Brush Size Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Pattern repeated 4 times
        Text("Pattern Repeated 4x", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    remember {
                        object : ShaderBrush() {
                            override fun createShader(size: Size): Shader {
                                return LinearGradientShader(
                                    colors = listOf(Color.Yellow, Color.Red, Color.Blue),
                                    from = Offset.Zero,
                                    to = Offset(size.width / 4f, 0f),
                                    tileMode = TileMode.Mirror
                                )
                            }
                        }
                    }
                )
        )

        // Large radial gradient
        Text("Large Radial Gradient", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    remember {
                        object : ShaderBrush() {
                            override fun createShader(size: Size): Shader {
                                val biggerDimension = maxOf(size.height, size.width)
                                return RadialGradientShader(
                                    colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                                    center = size.center,
                                    radius = biggerDimension / 2f,
                                    colorStops = listOf(0f, 0.95f)
                                )
                            }
                        }
                    }
                )
        )

        // Pattern repeated 6 times
        Text("Pattern Repeated 6x", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(
                    remember {
                        object : ShaderBrush() {
                            override fun createShader(size: Size): Shader {
                                return LinearGradientShader(
                                    colors = listOf(
                                        Color(0xFFFF6B6B),
                                        Color(0xFF4ECDC4),
                                        Color(0xFF45B7D1)
                                    ),
                                    from = Offset.Zero,
                                    to = Offset(size.width / 6f, 0f),
                                    tileMode = TileMode.Repeated
                                )
                            }
                        }
                    }
                )
        )
    }
}
