package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content

/*
DETECTING SCALING GESTURES IN JETPACK COMPOSE

To support user-scalable content, you must first detect multi-touch gestures.
In Jetpack Compose, you can do this using the Modifier.transformable.

IMPORTANT: The transformable modifier is purely a gesture detector; it doesn't apply scaling.
You must use the data it provides to update your own state.

The transformable modifier is a high-level API that provides the zoomChange delta since
the last gesture event. This simplifies the state update logic to direct accumulation
(for example, scale *= zoomChange), making it ideal for the adaptive scaling strategies.

TYPICAL ACCESSIBILITY SCALE RANGES:
- Minimum scale: ~0.75x (allows slight zoom out)
- Maximum scale: ~3.5x (allows significant zoom in for low vision users)
*/

/*
Example of creating a TransformableState:

val transformableState = TransformableState { zoomChange, panChange, rotationChange ->
    // Handle the zoom change
    scaleFactor *= zoomChange

    // Optionally handle pan and rotation
    // offset += panChange
    // rotation += rotationChange
}

Then apply it to a modifier:
Modifier.transformable(state = transformableState)
*/
