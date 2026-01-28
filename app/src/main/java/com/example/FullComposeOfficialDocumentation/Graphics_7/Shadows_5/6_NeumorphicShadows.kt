package com.example.FullComposeOfficialDocumentation.Graphics_7.Shadows_5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.dropShadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/*
Combine shadows - Create neumorphic shadows-->

You can combine and layer the dropShadow() and innerShadow() modifiers to create a
variety of effects.

Neumorphic shadows are characterized by a soft appearance that emerges organically from
the background. To create neumorphic shadows, do the following:

1. Use an element that shares the same colors as its background
2. Apply two faint, opposing drop shadows: a light shadow to one corner, and a dark
   shadow to the opposite corner

The following snippet layers two dropShadow() modifiers to create the neumorphic effect

NOTE: The dropShadow() modifier is part of Material 3 Expressive/Experimental APIs and may not
be available in all Compose versions. This is a placeholder for future implementation.

COMMENTED OUT - API NOT YET AVAILABLE IN STANDARD COMPOSE
*/

/*
@Composable
fun NeumorphicRaisedButton(
    shape: RoundedCornerShape = RoundedCornerShape(30.dp)
) {
    val bgColor = Color(0xFFe0e0e0)
    val lightShadow = Color(0xFFFFFFFF)
    val darkShadow = Color(0xFFb1b1b1)
    val upperOffset = -10.dp
    val lowerOffset = 10.dp
    val radius = 15.dp
    val spread = 0.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .wrapContentSize(Alignment.Center)
            .size(240.dp)
            .dropShadow(
                shape,
                shadow = Shadow(
                    radius = radius,
                    color = lightShadow,
                    spread = spread,
                    offset = DpOffset(upperOffset, upperOffset)
                ),
            )
            .dropShadow(
                shape,
                shadow = Shadow(
                    radius = radius,
                    color = darkShadow,
                    spread = spread,
                    offset = DpOffset(lowerOffset, lowerOffset)
                ),

            )
            .background(bgColor, shape)
    )
}
*/

/*
Key points about the code:

- The element (Box) uses the same background color (bgColor) as the parent container,
  creating a unified appearance
- Two dropShadow() modifiers are chained together:
  * First shadow: Light colored (white), positioned at upper-left (-10.dp, -10.dp offset)
  * Second shadow: Dark colored (gray), positioned at lower-right (10.dp, 10.dp offset)
- This creates the characteristic neumorphic effect where the element appears to softly
  emerge from the background
- Both shadows use the same radius (15.dp) and spread (0.dp) for consistency
*/
