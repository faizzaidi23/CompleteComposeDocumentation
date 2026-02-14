package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.TapAndPress_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.unit.toSize

/*
DOUBLE TAP TO ZOOM

Sometimes clickable and combinedClickable do not include enough information to
respond to the interaction in the correct way. For example, composables might
need access to the position within the composable's bounds where the interaction
took place.

═══════════════════════════════════════════════════════════════════════════════
THE PROBLEM - POSITION-AWARE GESTURES
═══════════════════════════════════════════════════════════════════════════════

LIMITATION OF CLICKABLE/COMBINEDCLICKABLE:
- They tell you THAT a gesture occurred
- They don't tell you WHERE the gesture occurred
- No access to tap position (Offset)

WHEN YOU NEED TAP POSITION:
✓ Zoom in around tap location (zoom to where user tapped)
✓ Place marker at tap location
✓ Select element at specific coordinates
✓ Draw at tap position
✓ Custom interactive graphics

SOLUTION:
Use pointerInput + detectTapGestures to access tap position.

═══════════════════════════════════════════════════════════════════════════════
USE CASE: DOUBLE-TAP TO ZOOM
═══════════════════════════════════════════════════════════════════════════════

BEST PRACTICE:
When showing full-screen images, allow users to zoom in by double-tapping.
The zoom should center around the tap position for a natural experience.

EXAMPLE BEHAVIOR:
- User double-taps left side of image → Zooms into left side
- User double-taps right side of image → Zooms into right side
- User double-taps center → Zooms into center
- User double-taps again → Zooms out

This creates a much better experience than always zooming to the center!

═══════════════════════════════════════════════════════════════════════════════
IMPLEMENTATION
═══════════════════════════════════════════════════════════════════════════════
*/

// Note: Photo data class is defined in 3_CombinedClickable.kt to avoid duplication

@Composable
fun ZoomableImage(photo: Photo, modifier: Modifier = Modifier) {
    /*
    Track whether image is currently zoomed
    */
    var zoomed by remember { mutableStateOf(false) }

    /*
    Track the offset for zoom translation
    This determines where the zoom centers on
    */
    var zoomOffset by remember { mutableStateOf(Offset.Zero) }

    Image(
        painter = rememberAsyncImagePainter(model = photo.highResUrl),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            /*
            DETECT DOUBLE-TAP GESTURE
            Use pointerInput to access tap position
            */
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { tapOffset ->
                        /*
                        tapOffset: Offset - Position where user double-tapped
                        size: Size - Size of the composable

                        If already zoomed: Zoom out (reset offset to zero)
                        If not zoomed: Calculate zoom offset and zoom in
                        */
                        zoomOffset = if (zoomed) {
                            Offset.Zero
                        } else {
                            calculateOffset(tapOffset, size.toSize())
                        }

                        /*
                        Toggle zoom state
                        */
                        zoomed = !zoomed
                    }
                )
            }
            /*
            APPLY ZOOM TRANSFORMATION
            Use graphicsLayer to scale and translate the image
            */
            .graphicsLayer {
                /*
                Scale: 2x when zoomed, 1x when normal
                */
                scaleX = if (zoomed) 2f else 1f
                scaleY = if (zoomed) 2f else 1f

                /*
                Translation: Shift image so tap point stays in place
                */
                translationX = zoomOffset.x
                translationY = zoomOffset.y
            }
    )
}

/*
HOW IT WORKS:

1. USER DOUBLE-TAPS IMAGE
   - detectTapGestures detects the double-tap
   - tapOffset contains the exact position where user tapped
   - Example: tapOffset = Offset(100f, 200f) pixels from top-left

2. CALCULATE ZOOM OFFSET
   - calculateOffset determines how to translate the image
   - Goal: Keep the tap point in the same visual position after zoom
   - If tapping left side, image shifts right to keep tap point visible
   - If tapping right side, image shifts left

3. APPLY TRANSFORMATION
   - graphicsLayer scales image to 2x
   - graphicsLayer translates image by calculated offset
   - Result: Zoomed in around tap point

4. DOUBLE-TAP AGAIN TO ZOOM OUT
   - Offset is reset to Offset.Zero
   - Scale returns to 1x
   - Image returns to original position

═══════════════════════════════════════════════════════════════════════════════
CALCULATING ZOOM OFFSET
═══════════════════════════════════════════════════════════════════════════════

The offset calculation ensures the tap point stays in the same screen position
after zooming.

GOAL:
Before zoom: Tap point at screen position P
After zoom: Same point still at screen position P

MATH:
When we scale by 2x, everything moves away from the origin (top-left).
To keep a point in place, we need to translate the image back.
*/

fun calculateOffset(tapOffset: Offset, size: Size): Offset {
    /*
    Calculate the center of the composable
    */
    val centerX = size.width / 2
    val centerY = size.height / 2

    /*
    Calculate how far the tap is from center
    */
    val offsetX = tapOffset.x - centerX
    val offsetY = tapOffset.y - centerY

    /*
    When we scale by 2x, the tap point moves away from center by 2x.
    We need to translate back by 1x the distance to keep it in place.

    Example:
    - Tap at 300px from center
    - After 2x scale: Now 600px from center
    - Translate back -300px to return to original screen position

    For zoom factor of 2x, we translate by -(offsetFromCenter * (scale - 1))
    = -(offset * 1.0)
    = -offset
    */
    return Offset(
        x = -offsetX,
        y = -offsetY
    )
}

/*
VISUAL EXPLANATION:

SCENARIO 1: Tap on left side
┌─────────────────────────────────┐
│ [TAP]         IMAGE             │  Before zoom
│   ↑                             │
│   └─ User taps here             │
└─────────────────────────────────┘

After 2x zoom without offset correction:
┌─────────────────────────────────┐
│ [TAP would be here, off-screen] │
│                                 │
│            IMAGE (2x)           │  Wrong! Tap point moved off-screen
│                                 │
└─────────────────────────────────┘

After 2x zoom WITH offset correction:
┌─────────────────────────────────┐
│ [TAP]                           │
│   ↑    IMAGE (2x, shifted right)│  Correct! Tap point stayed in place
│   └─ Still here                 │
└─────────────────────────────────┘

SCENARIO 2: Tap on right side
The image shifts LEFT to keep the tap point in the same screen position.

═══════════════════════════════════════════════════════════════════════════════
DETECTTAPGESTURES CALLBACKS
═══════════════════════════════════════════════════════════════════════════════

detectTapGestures provides these callbacks:

onTap: (Offset) -> Unit
    Called for single tap
    Receives tap position

onDoubleTap: (Offset) -> Unit
    Called for double tap
    Receives tap position

onLongPress: (Offset) -> Unit
    Called for long press
    Receives press position

onPress: suspend (Offset) -> Unit
    Called when pointer goes down
    Receives press position
    This is a suspend function!

ALL CALLBACKS RECEIVE POSITION!
This is the key difference from clickable/combinedClickable.

═══════════════════════════════════════════════════════════════════════════════
COMPLETE EXAMPLE WITH MULTIPLE GESTURES
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun FullyInteractiveImage(photo: Photo, modifier: Modifier = Modifier) {
    var zoomed by remember { mutableStateOf(false) }
    var zoomOffset by remember { mutableStateOf(Offset.Zero) }
    var showInfo by remember { mutableStateOf(false) }

    Image(
        painter = rememberAsyncImagePainter(model = photo.highResUrl),
        contentDescription = "Photo ${photo.id}",
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    /*
                    Single tap: Toggle info overlay
                    */
                    onTap = { tapOffset ->
                        showInfo = !showInfo
                    },

                    /*
                    Double tap: Zoom in/out around tap point
                    */
                    onDoubleTap = { tapOffset ->
                        zoomOffset = if (zoomed) {
                            Offset.Zero
                        } else {
                            calculateOffset(tapOffset, size.toSize())
                        }
                        zoomed = !zoomed
                    },

                    /*
                    Long press: Could show context menu
                    */
                    onLongPress = { pressOffset ->
                        // Show menu at press location
                    }
                )
            }
            .graphicsLayer {
                scaleX = if (zoomed) 2f else 1f
                scaleY = if (zoomed) 2f else 1f
                translationX = zoomOffset.x
                translationY = zoomOffset.y
            }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
GRAPHICS LAYER TRANSFORMATIONS
═══════════════════════════════════════════════════════════════════════════════

graphicsLayer is used to apply visual transformations without affecting layout:

.graphicsLayer {
    scaleX = 2f              // Horizontal scale
    scaleY = 2f              // Vertical scale
    translationX = -100f     // Move left/right (pixels)
    translationY = -50f      // Move up/down (pixels)
    rotationZ = 45f          // Rotate (degrees)
    alpha = 0.8f             // Transparency
}

BENEFITS OF GRAPHICSLAYER:
✓ Hardware accelerated (GPU)
✓ Doesn't affect layout (no recomposition)
✓ Smooth animations
✓ Efficient for visual-only changes

ALTERNATIVES:
- Modifier.offset: For layout-based positioning
- Modifier.scale: For scaling (but triggers layout)
- graphicsLayer is better for visual transformations

═══════════════════════════════════════════════════════════════════════════════
BEST PRACTICES
═══════════════════════════════════════════════════════════════════════════════

1. ALWAYS PROVIDE CONTENT DESCRIPTION FOR IMAGES
   Even for zoomable images, set contentDescription for accessibility

2. USE GRAPHICSLAYER FOR VISUAL TRANSFORMATIONS
   More efficient than triggering layout changes

3. CONSIDER ANIMATION
   Add animateFloatAsState for smooth zoom transitions

4. LIMIT ZOOM LEVELS
   Don't allow infinite zoom - set reasonable min/max

5. SHOW VISUAL FEEDBACK
   Consider showing zoom level indicator

6. TEST ON DIFFERENT DEVICES
   Touch targets and offsets may need adjustment

7. COMBINE WITH PANNING
   For better UX, allow dragging when zoomed

═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE POSITION-AWARE GESTURES
═══════════════════════════════════════════════════════════════════════════════

USE detectTapGestures (with position) WHEN:
✓ Zoom to tap location
✓ Place markers on tap
✓ Draw at tap position
✓ Select objects at coordinates
✓ Custom interactive graphics
✓ Position-dependent actions

USE clickable/combinedClickable WHEN:
✓ Position doesn't matter
✓ Just need to know gesture occurred
✓ Want standard accessibility behavior
✓ Need ripple/indication effects

═══════════════════════════════════════════════════════════════════════════════
SUMMARY
═══════════════════════════════════════════════════════════════════════════════

DOUBLE-TAP TO ZOOM PATTERN:
1. Use pointerInput + detectTapGestures to get tap position
2. Calculate offset to keep tap point in same screen position
3. Apply scale and translation with graphicsLayer
4. Toggle zoom state on double-tap

KEY INSIGHT:
Zooming around tap position creates much better UX than always
zooming to center. Users can efficiently zoom into specific parts
of an image.

REMEMBER:
- Use clickable for simple gestures
- Use detectTapGestures when you need position
- Graphics transformations are efficient and smooth
- Always consider accessibility

