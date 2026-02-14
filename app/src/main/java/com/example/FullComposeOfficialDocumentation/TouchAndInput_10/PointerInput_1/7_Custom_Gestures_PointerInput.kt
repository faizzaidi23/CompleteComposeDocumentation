package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/*
CUSTOM GESTURES WITH POINTERINPUT MODIFIER

Not every gesture is implemented with an out-of-the-box gesture modifier.
For example, you cannot use a modifier to react to a drag after long-press,
a control-click, or a three-finger tap.

Instead, you can write your own gesture handler to identify these custom gestures
using the pointerInput modifier, which gives you access to raw pointer events.

═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE POINTERINPUT
═══════════════════════════════════════════════════════════════════════════════

✓ USE POINTERINPUT WHEN:
- No built-in gesture modifier exists for your gesture
- You need very specific custom gesture recognition
- You're building game-like touch controls
- You need to handle raw pointer data

EXAMPLES OF CUSTOM GESTURES:
- Drag after long-press
- Control-click (Ctrl + click)
- Three-finger tap
- Custom swipe patterns
- Drawing/painting gestures
- Multi-touch game controls

═══════════════════════════════════════════════════════════════════════════════
BASIC POINTERINPUT STRUCTURE
═══════════════════════════════════════════════════════════════════════════════

The pointerInput modifier has this basic structure:

Modifier.pointerInput(key1, key2, ...) {
    // Suspend lambda - runs in a coroutine
    awaitPointerEventScope {
        // Suspend until pointer events occur
        while (true) {
            val event = awaitPointerEvent()
            // Handle the event
        }
    }
}

KEY COMPONENTS:
1. pointerInput(keys): The modifier, with restart keys
2. awaitPointerEventScope: Creates coroutine scope for pointer events
3. awaitPointerEvent(): Suspends until next pointer event
4. while(true): Continuous loop to handle all events

═══════════════════════════════════════════════════════════════════════════════
EXAMPLE: LISTENING TO RAW POINTER EVENTS
═══════════════════════════════════════════════════════════════════════════════
*/

/*
This example logs all pointer events (or filters by type)
*/
@Composable
fun LogPointerEvents(filter: PointerEventType? = null) {
    var log by remember { mutableStateOf("") }

    Column {
        Text(log)

        Box(
            Modifier
                .size(100.dp)
                .background(Color.Red)
                .pointerInput(filter) {
                    /*
                    The pointerInput modifier takes keys as parameters.
                    When any key changes, the lambda is re-executed.
                    Here, we pass 'filter' so that changing the filter
                    restarts the pointer event handler.
                    */

                    awaitPointerEventScope {
                        /*
                        Creates a coroutine scope that can wait for pointer events.
                        This scope has access to pointer event utilities.
                        */

                        while (true) {
                            /*
                            Infinite loop to continuously handle events.
                            The loop suspends at awaitPointerEvent() until
                            a new event occurs.
                            */

                            val event = awaitPointerEvent()
                            /*
                            Suspends the coroutine until a next pointer event occurs.
                            Returns a PointerEvent containing all pointer changes.
                            */

                            /*
                            Handle the pointer event
                            Check if we should log this event based on filter
                            */
                            if (filter == null || event.type == filter) {
                                log = "${event.type}, ${event.changes.first().position}"
                            }
                        }
                    }
                }
        )
    }
}

/*
BREAKDOWN OF THE EXAMPLE:

1. pointerInput(filter):
   - The modifier that enables pointer event handling
   - Takes 'filter' as a key - when filter changes, the handler restarts
   - This is important for remembering the current filter value

2. awaitPointerEventScope:
   - Creates a special coroutine scope for pointer events
   - Provides access to awaitPointerEvent() and other utilities
   - This scope is cancelled when the modifier is removed

3. while (true):
   - Infinite loop to handle all pointer events
   - The loop doesn't actually run infinitely - it suspends at awaitPointerEvent()
   - When an event occurs, it processes it, then waits for the next one

4. awaitPointerEvent():
   - Suspends the coroutine until a pointer event happens
   - Returns a PointerEvent object with all the event data
   - Types: Press, Release, Move, Enter, Exit

5. event.type and event.changes:
   - event.type: The type of pointer event (Press, Release, Move, etc.)
   - event.changes: List of PointerInputChange (one per active pointer)
   - event.changes.first().position: Position of the first pointer

═══════════════════════════════════════════════════════════════════════════════
POINTER INPUT IS JUST A DETECTOR
═══════════════════════════════════════════════════════════════════════════════

IMPORTANT: The pointerInput modifier is purely a gesture detector.
It doesn't apply scaling, rotation, or offset changes.
You must use the data it provides to update your own state.

Example - What pointerInput DOESN'T do:
*/

@Composable
fun PointerInputDoesntApplyChanges() {
    Box(
        Modifier
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        /*
                        This ONLY detects the event.
                        It doesn't move, scale, or rotate the Box.
                        You must apply changes yourself using state and modifiers.
                        */
                    }
                }
            }
    )
}

/*
Example - What you MUST do:
*/

@Composable
fun PointerInputWithStateUpdates() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            /*
            Apply the offset to actually move the Box
            */
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        /*
                        Detect the event and update state
                        */
                        event.changes.forEach { change ->
                            offsetX += change.position.x - change.previousPosition.x
                            offsetY += change.position.y - change.previousPosition.y
                        }
                    }
                }
            }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
RESTART KEYS - WHEN TO RE-EXECUTE THE HANDLER
═══════════════════════════════════════════════════════════════════════════════

The pointerInput modifier takes one or more keys.
When the value of any key changes, the modifier content lambda is re-executed.

WHY USE KEYS?
To ensure the gesture handler has access to the latest values.

EXAMPLE - Without proper key:
*/

@Composable
fun WrongKeyExample() {
    var onClick by remember { mutableStateOf<() -> Unit>({}) }

    Box(
        Modifier.pointerInput(Unit) {
            /*
            Problem: This captures 'onClick' when first created.
            If 'onClick' changes later, this handler still uses the old value.
            */
            detectTapGestures {
                onClick() // Uses stale onClick!
            }
        }
    )
}

/*
EXAMPLE - With proper key:
*/

@Composable
fun CorrectKeyExample() {
    var onClick by remember { mutableStateOf<() -> Unit>({}) }

    Box(
        Modifier.pointerInput(onClick) {
            /*
            Solution: Pass 'onClick' as a key.
            When 'onClick' changes, the handler is recreated with the new value.
            */
            detectTapGestures {
                onClick() // Always uses current onClick!
            }
        }
    )
}

/*
COMMON KEYS TO USE:
- Unit: When handler never needs to restart
- Callback functions: When you want to capture latest callback
- State values: When handler logic depends on state
- Multiple keys: When handler depends on multiple values

═══════════════════════════════════════════════════════════════════════════════
LISTENING TO RAW INPUT IS POWERFUL BUT COMPLEX
═══════════════════════════════════════════════════════════════════════════════

Although listening to raw input events is powerful, it is also complex to write
a custom gesture based on this raw data.

CHALLENGES:
- Must handle all edge cases manually
- Must track pointer IDs across events
- Must calculate distances, velocities, etc.
- Must determine when gesture starts and ends
- Must handle multi-touch scenarios
- Must handle gesture cancellation

To simplify the creation of custom gestures, many utility methods are available.
These are covered in the next section: "Detect Full Gestures".

═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE RAW EVENTS VS UTILITY METHODS
═══════════════════════════════════════════════════════════════════════════════

USE RAW awaitPointerEvent() WHEN:
- You need complete control over event processing
- You're implementing a very unique gesture
- You need access to every single pointer event
- You're building something like a drawing app

USE UTILITY METHODS (detectTapGestures, etc.) WHEN:
- Your gesture matches a common pattern
- You want simplified gesture detection
- You don't need access to every single event
- Most use cases fall into this category

RULE OF THUMB:
Start with utility methods (covered next).
Only use raw awaitPointerEvent() if utilities don't meet your needs.
*/

