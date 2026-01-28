package com.example.FullComposeOfficialDocumentation.Graphics_7.Shadows_5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.dropShadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Implement drop shadows-->

Use the dropShadow() modifier to draw an accurate shadow behind your content, which makes
the element appear elevated.

You can control the following key aspects through its Shadow parameter:
- radius: Defines the softness and diffusion of your blur
- color: Defines the color of the tint
- offset: Positions the shadow's geometry along the x and y axes
- spread: Controls the expansion or contraction of the shadow's geometry

Additionally, the shape parameter defines the shadow's overall shape. It can use any geometry
from the androidx.compose.foundation.shape package, as well as the Material Expressive shapes.

NOTE: The dropShadow() modifier is part of Material 3 Expressive/Experimental APIs and may not
be available in all Compose versions. This is a placeholder for future implementation.
*/

/*
To implement a basic drop shadow, add the dropShadow() modifier onto your composable chain,
providing the radius, color, and spread. Note that the background that appears on top of the
shadow is drawn after the dropShadow() modifier

COMMENTED OUT - API NOT YET AVAILABLE IN STANDARD COMPOSE
*/

/*
@Composable
fun SimpleDropShadowUsage() {
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .width(300.dp)
                .height(300.dp)
                .dropShadow(
                    shape = RoundedCornerShape(20.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 6.dp,
                        color = Color(0x40000000),
                        offset = DpOffset(x = 4.dp, 4.dp)
                    )
                )
                .align(Alignment.Center)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Text(
                "Drop Shadow",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 32.sp
            )
        }
    }
}
*/

/*
Key points about the code:

- The dropShadow() modifier is applied to the inner Box. The shadow has the following characteristics:
  * A rounded rectangle shape (RoundedCornerShape(20.dp))
  * A blur radius of 10.dp, making the edges soft and diffused
  * A spread of 6.dp, which expands the shadow's size and makes it larger than the box casting it
  * An alpha of 0.5f, making the shadow semi-transparent

- After the shadow is defined, the .background() modifier is applied
  * The Box is filled with a white color
  * The background is clipped to the same rounded rectangle shape as the shadow
*/
