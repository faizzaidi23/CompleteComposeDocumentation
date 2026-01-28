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
import androidx.compose.ui.graphics.innerShadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Implement inner shadows-->

To create an inverse effect to dropShadow(), use Modifier.innerShadow(), which creates
the illusion that an element is recessed or pressed into the underlying surface.

Order is significant when creating inner shadows. The innerShadow() modifier draws on top
of the content. To make sure the shadow is visible, you typically perform the following steps:
1. Draw your background content
2. Apply the innerShadow() modifier to create the concave appearance

If the innerShadow() is placed before the background, the background is drawn over the
shadow, hiding it completely.

NOTE: The innerShadow() modifier is part of Material 3 Expressive/Experimental APIs and may not
be available in all Compose versions. This is a placeholder for future implementation.

COMMENTED OUT - API NOT YET AVAILABLE IN STANDARD COMPOSE
*/

/*
@Composable
fun SimpleInnerShadowUsage() {
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .width(300.dp)
                .height(200.dp)
                .align(Alignment.Center)
                // note that the background needs to be defined before defining the inner shadow
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(20.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 2.dp,
                        color = Color(0x40000000),
                        offset = DpOffset(x = 6.dp, 7.dp)
                    )
                )

        ) {
            Text(
                "Inner Shadow",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 32.sp
            )
        }
    }
}
*/
