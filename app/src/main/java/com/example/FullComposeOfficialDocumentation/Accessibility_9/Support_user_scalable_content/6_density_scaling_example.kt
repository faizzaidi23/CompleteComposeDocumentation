package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content

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
COMPLETE DENSITY SCALING EXAMPLE

This example demonstrates how to implement density scaling in a complete composable.
When the user pinches to zoom, EVERYTHING scales proportionally:
- Text sizes
- Component sizes (switches, icons)
- Padding and margins
- Spacing between elements
- Layout dimensions

HOW IT WORKS:
1. Get the current density from LocalDensity
2. Create a DensityScalingState with the current density
3. Wrap content in CompositionLocalProvider to override LocalDensity
4. Apply the transformable modifier to detect pinch gestures
5. All child composables automatically use the scaled density

ACCESSIBILITY BENEFITS:
- Users can zoom the entire UI to their preferred size
- Content reflows to fit the screen width (no horizontal panning needed)
- Visual relationships between elements are preserved
- Works intuitively with standard pinch-to-zoom gestures
*/

@Composable
fun DensityScalingExample() {
    /*
    Get the current system density
    */
    val density = LocalDensity.current

    /*
    Create and remember the scaling state
    This state manages the scale factor and gesture detection
    */
    val scalingState = remember {
        DensityScalingState(currentDensity = density)
    }

    /*
    Provide the scaled density to all child composables
    This overrides the default LocalDensity for everything inside
    */
    CompositionLocalProvider(LocalDensity provides scalingState.scaledDensity()) {
        Box(
            modifier = Modifier
                .transformable(state = scalingState.transformableState)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            /*
            All content inside this Box will scale proportionally
            when the user performs a pinch-to-zoom gesture
            */
            DemoCard()
        }
    }
}

/*
TIP: To enhance user experience, consider persisting the scale factor using Jetpack DataStore.
This way, when users return to the screen or relaunch the app, their preferred scale is already applied.

Example of what to persist:
- scalingState.scaleFactor.floatValue

Then restore it when creating the DensityScalingState:
- DensityScalingState(initialScale = savedScale, currentDensity = density)
*/
