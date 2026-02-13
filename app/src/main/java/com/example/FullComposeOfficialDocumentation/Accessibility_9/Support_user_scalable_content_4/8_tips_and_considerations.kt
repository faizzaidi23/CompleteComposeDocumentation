package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content_4

/*
TIPS AND CONSIDERATIONS FOR USER-SCALABLE CONTENT

To create a more polished and accessible experience, consider the following recommendations:

1. OFFER NON-GESTURE SCALE CONTROLS
   - Some users may have difficulty with pinch-to-zoom gestures
   - Provide alternative controls like buttons or sliders to adjust scale
   - Include a "Reset to default" option
   - Consider adding preset zoom levels (100%, 125%, 150%, 200%)

   Example controls:
   - Plus/minus buttons
   - Slider with scale percentage
   - Dropdown menu with preset sizes
   - Double-tap to reset

2. BUILD FOR ALL SCALES
   - Test your UI against both in-app scaling and system-wide font/display settings
   - Check that layouts adapt correctly without breaking, overlapping, or hiding content
   - Test edge cases:
     * Minimum scale (0.75x)
     * Maximum scale (3.5x)
     * Combined with system font scaling
     * Combined with display size settings

   Common issues to watch for:
   - Text overlapping other elements
   - Buttons becoming too small to tap
   - Content flowing off-screen
   - Horizontal scrolling issues
   - Clipped or truncated text

3. PERSIST USER PREFERENCES
   - Save the user's chosen scale factor using DataStore or SharedPreferences
   - Restore the scale when the user returns to the screen
   - Apply the saved scale immediately on app launch (don't force users to rescale each time)

   Benefits:
   - Better user experience
   - Respects accessibility preferences
   - Reduces repetitive actions

4. PROVIDE VISUAL FEEDBACK
   - Show the current scale percentage (e.g., "125%")
   - Provide haptic feedback during scaling
   - Animate scale changes smoothly
   - Indicate when min/max scale is reached

5. TEST WITH REAL USERS
   - Test with users who have low vision
   - Test with users who have motor impairments
   - Verify with TalkBack and other accessibility services
   - Get feedback on the scaling experience

6. COMBINE WITH OTHER ACCESSIBILITY FEATURES
   - Support dynamic color/dark mode
   - Ensure sufficient contrast ratios at all scales
   - Support screen readers
   - Provide text alternatives for images
   - Ensure touch targets are large enough (48dp minimum)

7. HANDLE EDGE CASES
   - What happens when users scale while scrolling?
   - What happens when orientation changes?
   - What happens when the keyboard appears?
   - Does scaling work correctly in dialogs/bottom sheets?

8. PERFORMANCE CONSIDERATIONS
   - Scaling shouldn't cause jank or lag
   - Recomposition should be efficient
   - Test on lower-end devices
   - Consider lazy loading for large content

9. DOCUMENTATION AND ONBOARDING
   - Tell users that scaling is available
   - Show a tutorial on first launch
   - Include scaling info in accessibility settings
   - Provide in-app help documentation
*/

/*
EXAMPLE: Alternative Scale Controls

You can provide buttons for users who have difficulty with gestures:

@Composable
fun ScalableContentWithControls() {
    val density = LocalDensity.current
    val scalingState = remember { DensityScalingState(currentDensity = density) }

    Column {
        // Scale controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                scalingState.scaleFactor.floatValue =
                    (scalingState.scaleFactor.floatValue - 0.1f).coerceAtLeast(0.75f)
            }) {
                Text("−")
            }

            Text("${(scalingState.scaleFactor.floatValue * 100).toInt()}%")

            Button(onClick = {
                scalingState.scaleFactor.floatValue =
                    (scalingState.scaleFactor.floatValue + 0.1f).coerceAtMost(3.5f)
            }) {
                Text("+")
            }

            Button(onClick = { scalingState.scaleFactor.floatValue = 1f }) {
                Text("Reset")
            }
        }

        // Scalable content
        CompositionLocalProvider(LocalDensity provides scalingState.scaledDensity()) {
            Box(
                modifier = Modifier
                    .transformable(state = scalingState.transformableState)
                    .fillMaxSize()
            ) {
                DemoCard()
            }
        }
    }
}
*/

