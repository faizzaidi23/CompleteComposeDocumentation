package com.example.FullComposeOfficialDocumentation.Text_Typography_5.DisplayAndStyleText_1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Add Shadow to Text
 *
 * The style parameter lets you set an object of type TextStyle
 * to configure multiple parameters including shadow.
 *
 * Shadow receives:
 * - color: Color for the shadow
 * - offset: Where shadow is located in respect to the Text
 * - blurRadius: How blurry the shadow looks
 */

/**
 * Basic Text Shadow Example
 */
@Composable
fun TextShadow() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "Hello world!",
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue,
                offset = offset,
                blurRadius = 3f
            )
        )
    )
}

/**
 * Simple Shadow (small offset)
 */
@Composable
fun SimpleShadow() {
    Text(
        text = "Text with simple shadow",
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Gray,
                offset = Offset(2f, 2f),
                blurRadius = 1f
            )
        )
    )
}

/**
 * Dramatic Shadow (large offset)
 */
@Composable
fun DramaticShadow() {
    Text(
        text = "Dramatic Shadow",
        style = TextStyle(
            fontSize = 30.sp,
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(10f, 10f),
                blurRadius = 5f
            )
        )
    )
}

/**
 * Soft Glow Effect (high blur, no offset)
 */
@Composable
fun GlowEffect() {
    Text(
        text = "Glowing Text",
        style = TextStyle(
            fontSize = 28.sp,
            color = Color.White,
            shadow = Shadow(
                color = Color.Cyan,
                offset = Offset(0f, 0f),
                blurRadius = 20f
            )
        )
    )
}

/**
 * Colored Shadow Examples
 */
@Composable
fun ColoredShadows() {
    Column {
        // Red shadow
        Text(
            text = "Red Shadow",
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Red,
                    offset = Offset(4f, 4f),
                    blurRadius = 2f
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Blue shadow
        Text(
            text = "Blue Shadow",
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Blue,
                    offset = Offset(4f, 4f),
                    blurRadius = 2f
                )
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Green shadow
        Text(
            text = "Green Shadow",
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Green,
                    offset = Offset(4f, 4f),
                    blurRadius = 2f
                )
            )
        )
    }
}

/**
 * Multiple Shadows Effect (using semi-transparent shadows)
 */
@Composable
fun LayeredShadow() {
    Text(
        text = "Layered Effect",
        style = TextStyle(
            fontSize = 32.sp,
            color = Color.White,
            // Note: TextStyle only supports one shadow
            // For multiple shadows, layer multiple Text composables
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.5f),
                offset = Offset(8f, 8f),
                blurRadius = 10f
            )
        )
    )
}

/**
 * Shadow with Other Styles Combined
 */
@Composable
fun ShadowWithOtherStyles() {
    Text(
        text = "Styled with Shadow",
        style = TextStyle(
            fontSize = 28.sp,
            color = Color(0xFF6200EE),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            shadow = Shadow(
                color = Color.Gray,
                offset = Offset(3f, 3f),
                blurRadius = 2f
            )
        )
    )
}

/**
 * All Shadow Examples Together
 */
@Composable
fun AllShadowExamples() {
    Column {
        TextShadow()
        Spacer(modifier = Modifier.height(16.dp))

        SimpleShadow()
        Spacer(modifier = Modifier.height(16.dp))

        DramaticShadow()
        Spacer(modifier = Modifier.height(16.dp))

        GlowEffect()
        Spacer(modifier = Modifier.height(16.dp))

        ShadowWithOtherStyles()
    }
}

/**
 * Key Points:
 *
 * Shadow Parameters:
 * - color: The color of the shadow
 * - offset: Offset(x, y) - where shadow appears relative to text
 *   * Positive x = shadow to the right
 *   * Positive y = shadow downward
 * - blurRadius: How blurry/soft the shadow is
 *   * 0 = sharp shadow
 *   * Higher values = softer shadow
 *
 * Use Cases:
 * - Enhance text readability on images
 * - Create depth and elevation
 * - Add visual interest
 * - Create glow effects
 */
