package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content_4

import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.unit.Density

/*
FONT SCALING STRATEGY

This strategy is more targeted, modifying only the fontScale factor. The result is that
only text elements grow or shrink, while all other layout components—such as containers,
padding, and icons—remain a fixed size.

WHAT SCALES:
- Text size only
- Typography elements

WHAT STAYS THE SAME:
- Component sizes (buttons, icons)
- Padding and margins
- Spacing between elements
- Images and other visual elements

WHEN TO USE:
- Well-suited for improving text legibility in reading-intensive apps
- When you want to maintain consistent layout but allow text customization
- For users who only need larger text without scaling the entire UI

IMPORTANT NOTE:
This approach leverages Android's underlying font scaling system, which applies a
non-linear scale for better readability. Therefore, a zoomChange of 2.0 might not
result in text that appears exactly twice as large, but it creates scaling behavior
that is consistent with the user's system-wide accessibility settings.
*/

class FontScaleState(
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
    Creates a new Density object with the scaled fontScale value.
    This new density will affect only text rendering when used in a CompositionLocalProvider.
    Note: The base density remains unchanged, so layout measurements stay the same.
    */
    fun scaledFont(): Density {
        return Density(
            currentDensity.density,
            currentDensity.fontScale * scaleFactor.floatValue
        )
    }
}

/*
USAGE EXAMPLE:

@Composable
fun ScalableTextContent() {
    val density = LocalDensity.current
    val fontScaleState = remember { FontScaleState(currentDensity = density) }

    CompositionLocalProvider(LocalDensity provides fontScaleState.scaledFont()) {
        Box(
            modifier = Modifier
                .transformable(state = fontScaleState.transformableState)
                .fillMaxSize()
        ) {
            // Your content here - only text will scale
            YourTextContentComposable()
        }
    }
}
*/

