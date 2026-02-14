package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/*
ADD GESTURES WITH MODIFIERS

You can apply gesture modifiers to any arbitrary composable to make the composable
listen to gestures. For example, you can let a generic Box handle tap gestures by
making it clickable, or let a Column handle vertical scroll by applying verticalScroll.

═══════════════════════════════════════════════════════════════════════════════
AVAILABLE GESTURE MODIFIERS
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│ TAP AND PRESS MODIFIERS                                                     │
├─────────────────────────────────────────────────────────────────────────────┤
│ - clickable: Handle single taps/clicks                                     │
│ - combinedClickable: Handle taps, long-press, and double-tap               │
│ - selectable: Make element selectable (for radio button groups)            │
│ - toggleable: Make element toggleable (for checkboxes/switches)            │
│ - triStateToggleable: Toggleable with three states (checked/unchecked/     │
│   indeterminate)                                                            │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ SCROLL MODIFIERS                                                            │
├─────────────────────────────────────────────────────────────────────────────┤
│ - verticalScroll: Enable vertical scrolling                                │
│ - horizontalScroll: Enable horizontal scrolling                            │
│ - scrollable: Generic scrollable (more customizable)                       │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ DRAG MODIFIERS                                                              │
├─────────────────────────────────────────────────────────────────────────────┤
│ - draggable: Enable dragging in one direction                              │
│ - swipeable: Enable swipe gestures (deprecated, use AnchoredDraggable)     │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ MULTI-TOUCH MODIFIERS                                                       │
├─────────────────────────────────────────────────────────────────────────────┤
│ - transformable: Handle panning, rotating, and zooming                     │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
WHY PREFER GESTURE MODIFIERS OVER CUSTOM IMPLEMENTATION
═══════════════════════════════════════════════════════════════════════════════

As a rule, prefer out-of-the-box gesture modifiers over custom gesture handling.
The modifiers add more functionality on top of pure pointer event handling:

1. SEMANTIC INFORMATION
   Modifiers add semantic information for accessibility services.
   Example: clickable adds role information that tells accessibility services
   this is an actionable element.

2. VISUAL INDICATIONS
   Modifiers provide visual feedback on interactions:
   - Ripple effects on press
   - Indication when hovered (for mouse/trackpad)
   - Focus indicators when navigating with keyboard

3. HOVERING SUPPORT
   Modifiers automatically handle hover states for mouse and trackpad.
   The element can show different appearance when pointer is hovering over it.

4. FOCUS HANDLING
   Modifiers manage focus states:
   - Element can receive focus when navigating with keyboard
   - Shows focus indicator
   - Can be activated with Enter/Space keys

5. KEYBOARD SUPPORT
   Many modifiers add keyboard interaction:
   - clickable: Can be activated with Enter or Space
   - scrollable: Can scroll with arrow keys, Page Up/Down
   - draggable: Can be controlled with arrow keys

6. EDGE CASE HANDLING
   Modifiers handle many edge cases automatically:
   - What happens when user drags outside bounds?
   - How to handle gesture cancellation?
   - How to handle multi-window scenarios?
   - How to handle configuration changes?

═══════════════════════════════════════════════════════════════════════════════
EXAMPLES
═══════════════════════════════════════════════════════════════════════════════
*/

/*
EXAMPLE 1: Make a Box clickable
*/
@Composable
fun ClickableBoxExample() {
    Box(
        modifier = Modifier.clickable {
            /*
            The clickable modifier adds:
            - Tap gesture detection
            - Ripple effect on press
            - Semantic information for accessibility
            - Hover effect (for mouse/trackpad)
            - Focus handling
            - Keyboard support (Enter/Space to activate)
            */
            println("Box clicked!")
        }
    )
}

/*
EXAMPLE 2: Make a Column scrollable vertically
*/
@Composable
fun ScrollableColumnExample() {
    /*
    Remember scroll state to persist scroll position
    across recompositions
    */
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
        /*
        The verticalScroll modifier adds:
        - Vertical drag gesture detection
        - Smooth scrolling animation
        - Fling gesture support (fast scroll continues with momentum)
        - Overscroll effect
        - Keyboard support (arrow keys, Page Up/Down)
        - Mouse wheel support
        - Accessibility support (swipe gestures on TalkBack)
        */
    ) {
        // Long content that needs scrolling
        repeat(100) {
            androidx.compose.material3.Text("Item $it")
        }
    }
}

/*
EXAMPLE 3: Make an element draggable
*/
@Composable
fun DraggableBoxExample() {
    var offsetX by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    /*
                    The draggable modifier adds:
                    - Drag gesture detection in specified orientation
                    - Touch slop handling (minimum distance to start drag)
                    - Velocity tracking
                    - Fling support (continue movement after release)
                    - Keyboard support (arrow keys)
                    */
                    offsetX += delta
                }
            )
    )
}

/*
EXAMPLE 4: Combined clickable for multiple gesture types
*/
@Composable
fun CombinedClickableExample() {
    Box(
        modifier = Modifier.combinedClickable(
            onClick = {
                /*
                Handle single tap
                */
                println("Single tap")
            },
            onLongClick = {
                /*
                Handle long press
                Triggered after holding for ~500ms
                */
                println("Long press")
            },
            onDoubleClick = {
                /*
                Handle double tap
                Two taps in quick succession
                */
                println("Double tap")
            }
            /*
            The combinedClickable modifier adds all the benefits of
            clickable, plus detection for long-press and double-tap
            */
        )
    )
}

/*
EXAMPLE 5: Toggleable for checkbox-like behavior
*/
@Composable
fun ToggleableExample() {
    var checked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.toggleable(
            value = checked,
            onValueChange = { newValue ->
                /*
                The toggleable modifier adds:
                - Toggle gesture detection
                - Semantic role information (Role.Checkbox)
                - State description for accessibility
                - Visual indication
                - Keyboard support (Space to toggle)
                */
                checked = newValue
            }
        )
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
SOURCE CODE REFERENCE
═══════════════════════════════════════════════════════════════════════════════

You can check the source code of gesture modifiers to see how functionality
is being added. For example, the clickable modifier implementation shows:

1. How it adds semantic information using Modifier.semantics
2. How it adds visual indication using Modifier.indication
3. How it handles focus using Modifier.focusable
4. How it detects gestures using Modifier.pointerInput
5. How it adds keyboard support using Modifier.onKeyEvent

This is educational and can help you understand best practices when building
your own custom gesture handlers.

═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE GESTURE MODIFIERS
═══════════════════════════════════════════════════════════════════════════════

✓ USE GESTURE MODIFIERS WHEN:
- You need to add gesture support to a custom composable
- A built-in component doesn't match your visual needs
- You need one of the standard gesture types (tap, scroll, drag, transform)
- You want accessibility and keyboard support included

✗ USE POINTERINPUT WHEN:
- No gesture modifier exists for your specific gesture
- You need very custom gesture recognition logic
- You're combining gestures in non-standard ways
- Examples: drag-after-long-press, three-finger tap, custom swipe patterns

═══════════════════════════════════════════════════════════════════════════════
COMBINING MULTIPLE GESTURE MODIFIERS
═══════════════════════════════════════════════════════════════════════════════

You can apply multiple gesture modifiers to the same composable:
*/

@Composable
fun MultipleGesturesExample() {
    Box(
        modifier = Modifier
            .clickable { println("Clicked") }
            .verticalScroll(rememberScrollState())
            /*
            Note: Order matters! The clickable will consume tap events,
            preventing them from triggering scroll. For scrolling with
            clickable items, use LazyColumn instead.
            */
    )
}

/*
BEST PRACTICE:
When combining gestures, think carefully about which gestures should take
priority and in what order they should be applied.
*/
