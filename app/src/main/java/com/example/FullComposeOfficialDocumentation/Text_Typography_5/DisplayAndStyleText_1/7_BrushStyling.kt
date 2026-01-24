package com.example.FullComposeOfficialDocumentation.Text_Typography_5.DisplayAndStyleText_1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Advanced Styling with Brush API
 *
 * Enable more advanced text styling using the Brush API
 * with TextStyle and SpanStyle.
 *
 * You can use Brush anywhere you would typically use TextStyle or SpanStyle.
 *
 * Note: The Brush API in TextStyle is experimental and may change in the future.
 */

// Define some color palettes for gradients
val gradientColors = listOf(
    Color.Cyan,
    Color(0xFF64B5F6),
    Color(0xFF9C27B0)
)

val rainbowColors = listOf(
    Color(0xFFFF0000), // Red
    Color(0xFFFF7F00), // Orange
    Color(0xFFFFFF00), // Yellow
    Color(0xFF00FF00), // Green
    Color(0xFF0000FF), // Blue
    Color(0xFF4B0082), // Indigo
    Color(0xFF9400D3)  // Violet
)

/**
 * Example 1: Basic Linear Gradient
 *
 * Use a built-in brush within TextStyle
 * Configure linearGradient brush to your text
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun LinearGradientText() {
    Text(
        text = "Gradient Text",
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = gradientColors
            ),
            fontSize = 30.sp
        )
    )
}

/**
 * Example 2: Horizontal Linear Gradient
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun HorizontalGradientText() {
    Text(
        text = "Horizontal Gradient",
        style = TextStyle(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF00C853),
                    Color(0xFF64DD17)
                )
            ),
            fontSize = 28.sp
        )
    )
}

/**
 * Example 3: Vertical Linear Gradient
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun VerticalGradientText() {
    Text(
        text = "Vertical Gradient",
        style = TextStyle(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFD50000),
                    Color(0xFFFF6E40)
                )
            ),
            fontSize = 28.sp
        )
    )
}

/**
 * Example 4: Rainbow Gradient
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun RainbowGradientText() {
    Text(
        text = "Rainbow Colors",
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = rainbowColors
            ),
            fontSize = 32.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    )
}

/**
 * Example 5: Radial Gradient
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun RadialGradientText() {
    Text(
        text = "Radial Gradient",
        style = TextStyle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFFFEB3B),
                    Color(0xFFF57C00),
                    Color(0xFFD84315)
                )
            ),
            fontSize = 30.sp
        )
    )
}

/**
 * Example 6: Apply Brush to Span of Text
 *
 * Use buildAnnotatedString and SpanStyle API
 * along with your brush and gradient of choice
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun GradientSpanText() {
    Text(
        text = buildAnnotatedString {
            append("Do not allow people to dim your shine\n")
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = rainbowColors
                    )
                )
            ) {
                append("because they are blinded.")
            }
            append("\nTell them to put some sunglasses on.")
        },
        fontSize = 18.sp
    )
}

/**
 * Example 7: Multiple Gradient Spans
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun MultipleGradientSpans() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Yellow)
                    ),
                    fontSize = 24.sp
                )
            ) {
                append("First ")
            }

            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Green, Color.Cyan)
                    ),
                    fontSize = 24.sp
                )
            ) {
                append("Second ")
            }

            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Blue, Color.Magenta)
                    ),
                    fontSize = 24.sp
                )
            ) {
                append("Third")
            }
        }
    )
}

/**
 * Example 8: Opacity in Span of Text
 *
 * Adjust the opacity of a particular span using SpanStyle's alpha parameter
 * Use the same brush for both parts, change alpha in corresponding span
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun OpacityGradientText() {
    val brush = Brush.linearGradient(colors = rainbowColors)

    Text(
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    brush = brush,
                    alpha = 0.5f
                )
            ) {
                append("Text in ")
            }
            withStyle(
                SpanStyle(
                    brush = brush,
                    alpha = 1f
                )
            ) {
                append("Compose ❤️")
            }
        },
        fontSize = 24.sp
    )
}

/**
 * Example 9: Sweeping Gradient
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun SweepGradientText() {
    Text(
        text = "Sweep Gradient",
        style = TextStyle(
            brush = Brush.sweepGradient(
                colors = listOf(
                    Color.Red,
                    Color.Yellow,
                    Color.Green,
                    Color.Cyan,
                    Color.Blue,
                    Color.Magenta,
                    Color.Red
                )
            ),
            fontSize = 36.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    )
}

/**
 * Example 10: Solid Color Brush
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun SolidColorBrushText() {
    Text(
        text = "Solid Brush Color",
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(Color.Blue, Color.Blue) // Same color = solid
            ),
            fontSize = 26.sp
        )
    )
}

/**
 * All Brush Examples Together
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun AllBrushExamples() {
    Column {
        LinearGradientText()
        Spacer(modifier = Modifier.height(12.dp))

        HorizontalGradientText()
        Spacer(modifier = Modifier.height(12.dp))

        VerticalGradientText()
        Spacer(modifier = Modifier.height(12.dp))

        RainbowGradientText()
        Spacer(modifier = Modifier.height(12.dp))

        RadialGradientText()
        Spacer(modifier = Modifier.height(12.dp))

        SweepGradientText()
        Spacer(modifier = Modifier.height(12.dp))

        GradientSpanText()
        Spacer(modifier = Modifier.height(12.dp))

        OpacityGradientText()
    }
}

/**
 * Key Points:
 *
 * Brush Types:
 * - linearGradient(): Gradient in any direction
 * - horizontalGradient(): Left to right gradient
 * - verticalGradient(): Top to bottom gradient
 * - radialGradient(): Circular gradient from center
 * - sweepGradient(): Sweeping/angular gradient
 *
 * Usage:
 * - In TextStyle: Applies to entire text
 * - In SpanStyle: Applies to specific spans
 * - Can combine with alpha for opacity control
 *
 * Integration:
 * - Works with TextField
 * - Works with buildAnnotatedString
 * - Seamless with existing text APIs
 *
 * Note:
 * - API is experimental (@OptIn required)
 * - May change in future versions
 * - Provides advanced visual effects
 */

