package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.unit.Density

/*
DENSITY SCALING STRATEGY

This approach scales the base density of a UI area. As a result, all layout-based measurements—
including padding, spacing, and component sizes—are scaled, as if the screen size or resolution
had changed.

WHAT SCALES:
- Text size (relies on density)
- Component sizes (buttons, icons)
- Padding and margins
- Spacing between elements
- Images and other visual elements

WHEN TO USE:
- Effective when you want to uniformly enlarge all elements within a specific area
- Maintains the overall visual rhythm and proportions of your UI
- Ideal for reading-intensive layouts like news articles or messaging apps
- Best for visually structured layouts like app stores or social media feeds

BENEFITS:
- Preserves visual relationships between all elements
- Reflowing nature avoids horizontal panning
- Works well with nested scrolling elements
*/

class DensityScalingState(
    /*
    For accessibility, typical min/max values are:
    - minScale: ~0.75x (allows slight reduction)
    - maxScale: ~3.5x (allows significant enlargement for users with low vision)
    */
    private val minScale: Float = 0.75f,
    private val maxScale: Float = 3.5f,
    private val currentDensity: Density
) {
    /*
    TransformableState handles the pinch-to-zoom gesture.
    zoomChange represents the delta since the last gesture event.
    We multiply the current scaleFactor by zoomChange and clamp it within our min/max range.
    */
    val transformableState = TransformableState { zoomChange, _, _ ->
        scaleFactor.floatValue =
            (scaleFactor.floatValue * zoomChange).coerceIn(minScale, maxScale)
    }

    /*
    Mutable state that holds the current scale factor.
    Starts at 1.0 (100% - no scaling)
    */
    val scaleFactor = mutableFloatStateOf(1f)

    /*
    Creates a new Density object with the scaled density value.
    This new density will affect all dp-based measurements when used in a CompositionLocalProvider.
    Note: fontScale remains unchanged here (it could also be scaled if desired)
    */
    fun scaledDensity(): Density {
        return Density(
            currentDensity.density * scaleFactor.floatValue,
            currentDensity.fontScale
        )
    }
}

/*
USAGE EXAMPLE:

@Composable
fun ScalableContent() {
    val density = LocalDensity.current
    val scalingState = remember { DensityScalingState(currentDensity = density) }

    CompositionLocalProvider(LocalDensity provides scalingState.scaledDensity()) {
        Box(
            modifier = Modifier
                .transformable(state = scalingState.transformableState)
                .fillMaxSize()
        ) {
            // Your content here - everything will scale proportionally
            YourContentComposable()
        }
    }
}
*/
