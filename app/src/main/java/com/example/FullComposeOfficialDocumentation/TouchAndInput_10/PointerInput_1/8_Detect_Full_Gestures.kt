package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/*
DETECT FULL GESTURES - UTILITY METHODS

Instead of handling raw pointer events, you can listen for specific gestures
to occur and respond appropriately. The AwaitPointerEventScope provides methods
for listening to complete gestures.

═══════════════════════════════════════════════════════════════════════════════
AVAILABLE GESTURE DETECTION METHODS
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│ PRESS, TAP, AND LONG-PRESS                                                  │
├─────────────────────────────────────────────────────────────────────────────┤
│ detectTapGestures {                                                         │
│     onPress: (Offset) -> Unit                                               │
│     onTap: (Offset) -> Unit                                                 │
│     onDoubleTap: (Offset) -> Unit                                           │
│     onLongPress: (Offset) -> Unit                                           │
│ }                                                                           │
│                                                                             │
│ DETECTS: Single tap, double tap, long press, and press events              │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ DRAG GESTURES                                                               │
├─────────────────────────────────────────────────────────────────────────────┤
│ detectDragGestures { change, dragAmount ->                                  │
│     // Handle drag in any direction                                        │
│ }                                                                           │
│                                                                             │
│ detectHorizontalDragGestures { change, dragAmount ->                        │
│     // Handle horizontal drag only                                         │
│ }                                                                           │
│                                                                             │
│ detectVerticalDragGestures { change, dragAmount ->                          │
│     // Handle vertical drag only                                           │
│ }                                                                           │
│                                                                             │
│ detectDragGesturesAfterLongPress { change, dragAmount ->                    │
│     // Handle drag that starts after long press                            │
│ }                                                                           │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ TRANSFORM GESTURES (Multi-touch)                                            │
��─────────────────────────────────────────────────────────────────────────────┤
│ detectTransformGestures { centroid, pan, zoom, rotation ->                 │
│     // Handle pinch-to-zoom, pan, and rotation                             │
│ }                                                                           │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
EXAMPLE 1: DETECT TAP GESTURES
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun TapGesturesExample() {
    var log by remember { mutableStateOf("Waiting for gesture...") }

    Column {
        Text(log)

        Box(
            Modifier
                .size(100.dp)
                .background(Color.Blue)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { offset ->
                            /*
                            Called when pointer goes down.
                            offset: Position where press occurred
                            */
                            log = "Pressed at $offset"
                        },
                        onTap = { offset ->
                            /*
                            Called for a quick tap (down then up quickly)
                            offset: Position where tap occurred
                            */
                            log = "Tapped at $offset"
                        },
                        onDoubleTap = { offset ->
                            /*
                            Called for double tap (two taps in quick succession)
                            offset: Position where double tap occurred
                            */
                            log = "Double tapped at $offset"
                        },
                        onLongPress = { offset ->
                            /*
                            Called when pointer stays down for extended time (~500ms)
                            offset: Position where long press occurred
                            */
                            log = "Long pressed at $offset"
                        }
                    )
                }
        )
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
EXAMPLE 2: DETECT DRAG GESTURES
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun DragGesturesExample() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .size(100.dp)
            .background(Color.Green)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    /*
                    change: PointerInputChange containing event details
                    dragAmount: Offset representing drag distance since last event

                    This is called continuously during drag.
                    dragAmount is the delta (change) since last callback.
                    */
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y

                    /*
                    Consume the event so parent doesn't also handle it
                    */
                    change.consume()
                }
            }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
EXAMPLE 3: HORIZONTAL DRAG ONLY
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun HorizontalDragExample() {
    var offsetX by remember { mutableStateOf(0f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    /*
                    Only detects horizontal dragging.
                    Vertical movement is ignored.

                    dragAmount: Float representing horizontal drag distance
                    */
                    offsetX += dragAmount
                    change.consume()
                }
            }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
EXAMPLE 4: DRAG AFTER LONG PRESS
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun DragAfterLongPressExample() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .size(100.dp)
            .background(if (isDragging) Color.Yellow else Color.Magenta)
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDragStart = {
                        /*
                        Called when drag starts (after long press completes)
                        */
                        isDragging = true
                    },
                    onDragEnd = {
                        /*
                        Called when user lifts pointer
                        */
                        isDragging = false
                    },
                    onDragCancel = {
                        /*
                        Called if drag is cancelled
                        */
                        isDragging = false
                    }
                ) { change, dragAmount ->
                    /*
                    User must long-press first, THEN drag.
                    Regular drag without long-press won't trigger this.
                    */
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                    change.consume()
                }
            }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
EXAMPLE 5: TRANSFORM GESTURES (Pinch, Pan, Rotate)
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun TransformGesturesExample() {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation
            )
            .size(100.dp)
            .background(Color.Cyan)
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotationDelta ->
                    /*
                    centroid: Center point of all pointers (Offset)
                    pan: Translation/movement amount (Offset)
                    zoom: Scale factor change (Float, 1.0 = no change)
                    rotationDelta: Rotation change in degrees (Float)

                    This detects multi-touch gestures:
                    - Two fingers moving apart/together = zoom
                    - Two fingers moving together = pan
                    - Two fingers rotating around center = rotation
                    */

                    scale *= zoom
                    rotation += rotationDelta
                    offsetX += pan.x
                    offsetY += pan.y
                }
            }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
IMPORTANT: TOP-LEVEL DETECTORS - ONLY ONE PER POINTERINPUT
═══════════════════════════════════════════════════════════════════════════════

These are top-level detectors, so you can't add multiple detectors within
one pointerInput modifier.

❌ THIS DOESN'T WORK - Only taps are detected:
*/

@Composable
fun WrongMultipleDetectorsExample() {
    var log by remember { mutableStateOf("") }

    Box(
        Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectTapGestures {
                    log = "Tap!"
                }

                /*
                Never reached!
                detectTapGestures blocks the coroutine,
                so this line never executes.
                */
                detectDragGestures { _, _ ->
                    log = "Dragging"
                }
            }
    )
}

/*
✅ THIS WORKS - Use separate pointerInput modifiers:
*/

@Composable
fun CorrectMultipleDetectorsExample() {
    var log by remember { mutableStateOf("") }

    Box(
        Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectTapGestures {
                    log = "Tap!"
                }
            }
            .pointerInput(Unit) {
                /*
                This drag detector works correctly because it's
                in a separate pointerInput modifier.
                */
                detectDragGestures { _, _ ->
                    log = "Dragging"
                }
            }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
WHY DETECTORS BLOCK
═══════════════════════════════════════════════════════════════════════════════

Internally, the detect functions block the coroutine by continuously
waiting for events in a loop. Example simplified implementation:

suspend fun detectTapGestures(onTap: (Offset) -> Unit) {
    awaitEachGesture {
        // This loop continues forever, handling each gesture
        while (true) {
            val event = awaitPointerEvent()
            // Process tap logic...
        }
    }
}

Since this never returns, any code after it never executes.

SOLUTION:
Use multiple pointerInput modifiers, each with its own detector.
Each runs in its own coroutine, so they don't block each other.

══════════════════════════════════════════════════════════════════════���════════
WHEN TO USE DETECT METHODS VS RAW EVENTS
═══════════════════════════════════════════════════════════════════════════════

✓ USE DETECT METHODS (detectTapGestures, etc.) WHEN:
- Your gesture matches one of the available detectors
- You want simplified gesture recognition
- You don't need access to every pointer event
- This covers 90% of use cases

✓ USE RAW awaitPointerEvent() WHEN:
- No detector matches your specific gesture
- You need complete control over event processing
- You're implementing a very unique interaction
- You need access to every single pointer event

═══════════════════════════════════════════════════════════════════════════════
AVAILABLE DETECTOR SUMMARY
═══════════════════════════════════════════════════════════════════════════════

detectTapGestures                    → Tap, double-tap, long-press
detectDragGestures                   → Drag in any direction
detectHorizontalDragGestures         → Drag horizontally only
detectVerticalDragGestures           → Drag vertically only
detectDragGesturesAfterLongPress     → Drag that starts after long-press
detectTransformGestures              → Pinch, pan, rotate (multi-touch)

For even more fine-grained control, use lower-level utilities like
awaitFirstDown(), awaitDragOrCancellation(), etc. (covered in next section)
*/
