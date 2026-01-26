package com.example.FullComposeOfficialDocumentation.Graphics_7.Brush_3

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.Language

/*
ADVANCED EXAMPLE: CUSTOM BRUSH
AGSL RuntimeShader Brush

AGSL (Android Graphics Shading Language) offers a subset of GLSL Shader capabilities
Shaders can be written in AGSL and used with a Brush in Compose

This allows you to create completely custom visual effects using GPU-accelerated shaders

IMPORTANT: RuntimeShaders only work on Android 13+ (API 33+)
Wrap your Composable in an API if-else check and provide a suitable fallback

Note: This file demonstrates the concept of AGSL shaders.
RuntimeShader requires androidx.compose.ui.graphics.RuntimeShader which may need
additional dependencies or newer Compose versions.
*/

/*
Custom AGSL Shader String

This shader takes two input colors, calculates the distance from the bottom left
of the drawing area and does a mix between the two colors based on the distance
This produces a custom gradient effect
*/

@Language("AGSL")
val CUSTOM_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;

        float mixValue = distance(uv, vec2(0, 1));
        return mix(color, color2, mixValue);
    }
""".trimIndent()

/*
Custom Coral to Light Yellow Gradient

This uses the AGSL shader to create a custom gradient
from coral to light yellow based on distance calculation

NOTE: Uncomment and use when RuntimeShader is available in your project
*/

val Coral = Color(0xFFF3A397)
val LightYellow = Color(0xFFF8EE94)

/*
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ShaderBrushExample() {
    Box(
        modifier = Modifier
            .drawWithCache {
                val shader = RuntimeShader(CUSTOM_SHADER)
                val shaderBrush = ShaderBrush(shader)
                shader.setFloatUniform("resolution", size.width, size.height)
                onDrawBehind {
                    shader.setColorUniform(
                        "color",
                        android.graphics.Color.valueOf(
                            LightYellow.red, LightYellow.green,
                            LightYellow.blue,
                            LightYellow.alpha
                        )
                    )
                    shader.setColorUniform(
                        "color2",
                        android.graphics.Color.valueOf(
                            Coral.red,
                            Coral.green,
                            Coral.blue,
                            Coral.alpha
                        )
                    )
                    drawRect(shaderBrush)
                }
            }
            .fillMaxWidth()
            .height(200.dp)
    )
}
*/

/*
Circular Pattern Shader

A more complex shader that creates a circular pattern
*/

@Language("AGSL")
val CIRCULAR_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;
    uniform float time;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;
        float2 center = vec2(0.5, 0.5);
        
        float dist = distance(uv, center);
        float circle = smoothstep(0.3, 0.31, dist);
        
        return mix(color, color2, circle);
    }
""".trimIndent()

/*
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CircularShaderExample() {
    Box(
        modifier = Modifier
            .drawWithCache {
                val shader = RuntimeShader(CIRCULAR_SHADER)
                val shaderBrush = ShaderBrush(shader)
                shader.setFloatUniform("resolution", size.width, size.height)
                shader.setFloatUniform("time", 0f)

                onDrawBehind {
                    shader.setColorUniform(
                        "color",
                        android.graphics.Color.valueOf(
                            Color.Blue.red,
                            Color.Blue.green,
                            Color.Blue.blue,
                            Color.Blue.alpha
                        )
                    )
                    shader.setColorUniform(
                        "color2",
                        android.graphics.Color.valueOf(
                            Color.Red.red,
                            Color.Red.green,
                            Color.Red.blue,
                            Color.Red.alpha
                        )
                    )
                    drawRect(shaderBrush)
                }
            }
            .fillMaxWidth()
            .height(200.dp)
    )
}
*/

/*
Wave Pattern Shader

Creates a wave-like gradient effect
*/

@Language("AGSL")
val WAVE_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;
        
        float wave = sin(uv.x * 10.0) * 0.5 + 0.5;
        
        return mix(color, color2, wave);
    }
""".trimIndent()

/*
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WaveShaderExample() {
    Box(
        modifier = Modifier
            .drawWithCache {
                val shader = RuntimeShader(WAVE_SHADER)
                val shaderBrush = ShaderBrush(shader)
                shader.setFloatUniform("resolution", size.width, size.height)

                onDrawBehind {
                    shader.setColorUniform(
                        "color",
                        android.graphics.Color.valueOf(
                            Color(0xFF00BCD4).red,
                            Color(0xFF00BCD4).green,
                            Color(0xFF00BCD4).blue,
                            Color(0xFF00BCD4).alpha
                        )
                    )
                    shader.setColorUniform(
                        "color2",
                        android.graphics.Color.valueOf(
                            Color(0xFFE91E63).red,
                            Color(0xFFE91E63).green,
                            Color(0xFFE91E63).blue,
                            Color(0xFFE91E63).alpha
                        )
                    )
                    drawRect(shaderBrush)
                }
            }
            .fillMaxWidth()
            .height(200.dp)
    )
}
*/

/*
AGSL Shaders Information Display

Since RuntimeShader requires Android 13+ and specific Compose versions,
this provides information about AGSL shaders instead
*/

@Composable
fun AGSLShadersShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "AGSL RuntimeShader Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Requires Android 13+ (API 33+)",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            "RuntimeShader allows you to create custom GPU-accelerated visual effects using AGSL",
            fontSize = 14.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            "The shader code is defined as a string and can create:",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            "• Custom gradients\n• Wave patterns\n• Circular effects\n• Any math-based visual effect",
            fontSize = 14.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            "To use RuntimeShader, uncomment the example functions in this file and ensure you have the required dependencies.",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

/*
Important Notes:

1. RuntimeShaders only work on Android 13+ (API 33+)
2. Always wrap your shader composables in an API check
3. Provide a suitable fallback for older devices
4. AGSL offers a subset of GLSL capabilities
5. Shaders are GPU-accelerated and very performant
6. You can do much more than gradients - any math-based visual effect is possible

For more information, check out the AGSL documentation
*/
