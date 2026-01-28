package com.example.FullComposeOfficialDocumentation.Graphics_7.Shadows_5

/*
Combine shadows - Create realistic shadows-->

Realistic shadows mimic shadows in the physical world—they appear lit by a primary light
source, resulting in both a direct shadow and a more diffuse shadow. You can stack multiple
dropShadow() and innerShadow() instances with different properties to recreate realistic
shadow effects.

NOTE: The dropShadow() and innerShadow() modifiers are part of Material 3
Expressive/Experimental APIs and may not be available in all Compose versions.
This is a placeholder for future implementation.

COMMENTED OUT - API NOT YET AVAILABLE IN STANDARD COMPOSE
*/

/*
@Composable
fun RealisticShadows() {
    Box(Modifier.fillMaxSize()) {
        val dropShadowColor1 = Color(0xB3000000)
        val dropShadowColor2 = Color(0x66000000)

        val innerShadowColor1 = Color(0xCC000000)
        val innerShadowColor2 = Color(0xFF050505)
        val innerShadowColor3 = Color(0x40FFFFFF)
        val innerShadowColor4 = Color(0x1A050505)

        Box(
            Modifier
                .width(300.dp)
                .height(200.dp)
                .align(Alignment.Center)
                .dropShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 40.dp,
                        spread = 0.dp,
                        color = dropShadowColor1,
                        offset = DpOffset(x = 2.dp, 8.dp)
                    )
                )
                .dropShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 4.dp,
                        spread = 0.dp,
                        color = dropShadowColor2,
                        offset = DpOffset(x = 0.dp, 4.dp)
                    )
                )
                // note that the background needs to be defined before defining the inner shadow
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(100.dp)
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 12.dp,
                        spread = 3.dp,
                        color = innerShadowColor1,
                        offset = DpOffset(x = 6.dp, 6.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 4.dp,
                        spread = 1.dp,
                        color = Color.White,
                        offset = DpOffset(x = 5.dp, 5.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 12.dp,
                        spread = 5.dp,
                        color = innerShadowColor2,
                        offset = DpOffset(x = (-3).dp, (-12).dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 3.dp,
                        spread = 10.dp,
                        color = innerShadowColor3,
                        offset = DpOffset(x = 0.dp, 0.dp)
                    )
                )
                .innerShadow(
                    shape = RoundedCornerShape(100.dp),
                    shadow = Shadow(
                        radius = 3.dp,
                        spread = 9.dp,
                        color = innerShadowColor4,
                        offset = DpOffset(x = 1.dp, 1.dp)
                    )
                )

        ) {
            Text(
                "Realistic Shadows",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}
*/

/*
Key points about the code:

- Two chained dropShadow() modifiers with distinct properties are applied, followed by
  a background() modifier:
  * First drop shadow: Large blur radius (40.dp) for diffuse, ambient shadow
  * Second drop shadow: Small blur radius (4.dp) for sharp, direct shadow

- Chained innerShadow() modifiers are applied to forge the metallic rim effect around
  the component's edge:
  * Multiple layers with varying radii, spreads, colors, and offsets
  * Some shadows use positive offsets (lower-right), others negative (upper-left)
  * White shadow creates highlights, dark shadows create depth
  * This layering mimics how light interacts with curved, reflective surfaces in reality

- The result is a sophisticated, three-dimensional appearance that resembles real-world
  lighting and material properties
*/
