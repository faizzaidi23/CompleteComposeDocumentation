package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content_4

import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity

/*
COMPLETE FONT SCALING EXAMPLE

This example demonstrates how to implement font scaling in a complete composable.
When the user pinches to zoom, ONLY TEXT scales:
- Text sizes increase/decrease
- All other components stay the same size (switches, icons, boxes)
- Padding, margins, and spacing remain unchanged

HOW IT WORKS:
1. Get the current density from LocalDensity
2. Create a FontScaleState with the current density
3. Wrap content in CompositionLocalProvider to override the fontScale portion of LocalDensity
4. Apply the transformable modifier to detect pinch gestures
5. Only text elements use the scaled fontScale value

ACCESSIBILITY BENEFITS:
- Users can increase text size for better readability
- Layout structure remains stable (components don't shift around)
- Ideal for reading-intensive content like articles or messages
- Consistent with Android's system-wide font scaling behavior

IMPORTANT:
This uses Android's non-linear font scaling system. A scale factor of 2.0 won't
make text exactly twice as large, but creates more readable scaling that matches
the user's system accessibility settings.
*/

@Composable
fun FontScalingExample() {
    /*
    Get the current system density
    */
    val density = LocalDensity.current

    /*
    Create and remember the font scaling state
    This state manages the font scale factor and gesture detection
    */
    val fontScaleState = remember {
        FontScaleState(currentDensity = density)
    }

    /*
    Provide the scaled font density to all child composables
    This overrides only the fontScale portion of LocalDensity
    The base density (used for layout) remains unchanged
    */
    CompositionLocalProvider(LocalDensity provides fontScaleState.scaledFont()) {
        Box(
            modifier = Modifier
                .transformable(state = fontScaleState.transformableState)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            /*
            Only text inside this Box will scale when the user
            performs a pinch-to-zoom gesture.
            All other UI elements (icons, spacing, components) stay the same size.
            */
            DemoCard()
        }
    }
}

/*
COMPARISON WITH DENSITY SCALING:
- Font Scaling: Only text grows/shrinks, layout stays stable
- Density Scaling: Everything grows/shrinks proportionally

WHEN TO CHOOSE FONT SCALING:
- Reading apps (news, books, messages)
- When you want predictable layout behavior
- When text legibility is the primary concern
- When you want to match system font scaling behavior
*/

