package com.example.FullComposeOfficialDocumentation.Graphics_7.Shadows_5

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.dropShadow
import androidx.compose.ui.graphics.innerShadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Animate shadows on user interaction-->

To make your shadows respond to user interactions, you can integrate shadow properties
with Compose's animation APIs. When a user presses a button, for example, the shadow can
change to provide instantaneous visual feedback.

The following code creates a "pressed" effect with a shadow (the illusion that the surface
is being pushed down into the screen)

NOTE: This example uses dropShadow() and innerShadow() which are part of Material 3
Expressive/Experimental APIs and may not be available in all Compose versions.
This is a placeholder for future implementation.

COMMENTED OUT - API NOT YET AVAILABLE IN STANDARD COMPOSE
*/

/*
@Composable
fun AnimatedColoredShadows() {
    Box(Modifier.fillMaxSize()) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        // Create transition with pressed state
        val transition = updateTransition(
            targetState = isPressed,
            label = "button_press_transition"
        )

        fun <T> buttonPressAnimation() = tween<T>(
            durationMillis = 400,
            easing = EaseInOut
        )

        // Animate all properties using the transition
        val shadowAlpha by transition.animateFloat(
            label = "shadow_alpha",
            transitionSpec = { buttonPressAnimation() }
        ) { pressed ->
            if (pressed) 0f else 1f
        }

        val blueDropShadowColor = Color(0xFF64B5F6)
        val darkBlueDropShadowColor = Color(0xFF1976D2)
        val innerShadowColor1 = Color(0x66000000)
        val innerShadowColor2 = Color(0x33000000)

        val blueDropShadow by transition.animateColor(
            label = "shadow_color",
            transitionSpec = { buttonPressAnimation() }
        ) { pressed ->
            if (pressed) Color.Transparent else blueDropShadowColor
        }

        val darkBlueDropShadow by transition.animateColor(
            label = "dark_shadow_color",
            transitionSpec = { buttonPressAnimation() }
        ) { pressed ->
            if (pressed) Color.Transparent else darkBlueDropShadowColor
        }

        val innerShadowAlpha by transition.animateFloat(
            label = "inner_shadow_alpha",
            transitionSpec = { buttonPressAnimation() }
        ) { pressed ->
            if (pressed) 1f else 0f
        }

        Box(
            Modifier
                .clickable(
                    interactionSource, indication = null
                ) {
                    // Click action
                }
                .width(300.dp)
                .height(200.dp)
                .align(Alignment.Center)
                .dropShadow(
                    shape = RoundedCornerShape(70.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 0.dp,
                        color = blueDropShadow,
                        offset = DpOffset(x = 0.dp, y = (-2).dp),
                        alpha = shadowAlpha
                    )
                )
                .dropShadow(
                    shape = RoundedCornerShape(70.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 0.dp,
                        color = darkBlueDropShadow,
                        offset = DpOffset(x = 2.dp, 6.dp),
                        alpha = shadowAlpha
                    )
                )
                // note that the background needs to be defined before defining the inner shadow
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(70.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(70.dp),
                    shadow = Shadow(
                        radius = 8.dp,
                        spread = 4.dp,
                        color = innerShadowColor2,
                        offset = DpOffset(x = 4.dp, 0.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(70.dp),
                    shadow = Shadow(
                        radius = 20.dp,
                        spread = 4.dp,
                        color = innerShadowColor1,
                        offset = DpOffset(x = 4.dp, 0.dp),
                        alpha = innerShadowAlpha
                    )
                )

        ) {
            Text(
                "Animated Shadows",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp
            )
        }
    }
}
*/

/*
Key points about the code:

- Declares the start and end states for the parameters to animate upon press with
  transition.animateColor and transition.animateFloat

- Uses updateTransition and provides it with the chosen targetState (targetState = isPressed)
  to verify all animations are synchronized. Whenever isPressed changes, the transition object
  automatically manages the animation of all child properties from their current values to the
  new target values

- Defines the buttonPressAnimation specification, which controls the timing and easing of the
  transition. It specifies a tween (short for in-between) with a duration of 400 milliseconds
  and an EaseInOut curve, which means the animation starts slow, speeds up in the middle, and
  slows down at the end

- Defines a Box with a chain of modifier functions that apply all the animated properties to
  create the visual element, including the following:
  * .clickable(): A modifier that makes the Box interactive
  * .dropShadow(): Two outer drop shadows are applied first. Their color and alpha properties
    are linked to the animated values and create the initial raised appearance
  * .innerShadow(): Two inner shadows are drawn on top of the background. Their properties are
    linked to the other set of animated values and create the indented appearance
*/
