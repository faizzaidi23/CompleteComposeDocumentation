package com.example.FullComposeOfficialDocumentation.Graphics_7.Shadows_5

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.dropShadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/*
Combine shadows - Create neobrutalist shadows-->

The neobrutalist style showcases high-contrast, blocky layouts, vivid colors, and thick
borders. To create this effect, use a dropShadow() with zero blur and a distinct offset.

NOTE: The dropShadow() modifier is part of Material 3 Expressive/Experimental APIs and may not
be available in all Compose versions. This is a placeholder for future implementation.

COMMENTED OUT - API NOT YET AVAILABLE IN STANDARD COMPOSE
*/

/*
@Composable
fun NeoBrutalShadows() {
    val dropShadowColor = Color(0xFF007AFF)
    val borderColor = Color(0xFFFF2D55)

    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .width(300.dp)
                .height(200.dp)
                .align(Alignment.Center)
                .dropShadow(
                    shape = RoundedCornerShape(0.dp),
                    shadow = Shadow(
                        radius = 0.dp,
                        spread = 0.dp,
                        color = dropShadowColor,
                        offset = DpOffset(x = 8.dp, 8.dp)
                    )
                )
                .border(
                    8.dp, borderColor
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(0.dp)
                )
        ) {
            Text(
                "Neobrutal Shadows",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
*/

/*
Key points about the code:

- The shadow uses zero blur (radius = 0.dp), creating a sharp, solid shadow with no
  diffusion or softness
- The shadow has a clear offset (8.dp, 8.dp), making it distinct and blocky
- A thick border (8.dp) is applied with a contrasting color (red)
- The shadow color (blue) contrasts strongly with the white background
- No rounded corners (RoundedCornerShape(0.dp)), maintaining the blocky aesthetic
- This creates the characteristic neobrutalist look: bold, high-contrast, and unapologetically
  geometric
*/
