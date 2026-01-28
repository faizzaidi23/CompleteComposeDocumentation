package com.example.FullComposeOfficialDocumentation.Graphics_7.Shadows_5

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.dropShadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/*
Create gradient shadows-->

Shadows are not limited to solid colors. The shadow API accepts a Brush, which lets you
create gradient shadows.

You can use a brush as a shadow to create a gradient dropShadow() with a "breathing" animation

NOTE: The dropShadow() modifier with Brush support is part of Material 3
Expressive/Experimental APIs and may not be available in all Compose versions.
This is a placeholder for future implementation.

COMMENTED OUT - API NOT YET AVAILABLE IN STANDARD COMPOSE
*/

/*
@Composable
fun GradientShadowExample() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Infinite transition for breathing animation
        val infiniteTransition = rememberInfiniteTransition(label = "breathing")

        val animatedSpread by infiniteTransition.animateFloat(
            initialValue = 10f,
            targetValue = 20f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            ),
            label = "spread"
        )

        val animatedAlpha by infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            ),
            label = "alpha"
        )

        // Gradient colors for the shadow
        val colors = listOf(
            Color(0xFFFF6B6B),
            Color(0xFFFFD93D),
            Color(0xFF6BCF7F),
            Color(0xFF4D96FF),
            Color(0xFFB06AFF),
            Color(0xFFFF6B6B)
        )

        Box(
            modifier = Modifier
                .width(240.dp)
                .height(200.dp)
                .dropShadow(
                    shape = RoundedCornerShape(70.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = animatedSpread.dp,
                        brush = Brush.sweepGradient(colors),
                        offset = DpOffset(x = 0.dp, y = 0.dp),
                        alpha = animatedAlpha
                    )
                )
                .clip(RoundedCornerShape(70.dp))
                .background(Color(0xEDFFFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Gradient Shadow",
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
*/

/*
Key points about the code:

- dropShadow() adds a shadow behind the box
- brush = Brush.sweepGradient(colors) colors the shadow with a gradient that rotates
  through a list of predefined colors, creating a rainbow-like effect
- The animatedSpread and animatedAlpha values create a "breathing" effect where the
  shadow expands/contracts and fades in/out
*/
