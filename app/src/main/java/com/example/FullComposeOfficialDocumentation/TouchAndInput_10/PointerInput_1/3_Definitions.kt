package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerType

/*
DEFINITIONS - KEY TERMINOLOGY FOR GESTURE HANDLING

To understand the various concepts in gesture handling, you need to understand
some of the terminology used:

1. POINTER

DEFINITION:
A physical object you can use to interact with your application.

COMMON POINTER TYPES:

┌─────────────────────────────────────────────────────────────────────────────┐
│ MOBILE DEVICES                                                              │
├─────────────────────────────────────────────────────────────────────────────┤
│ - Finger: Most common pointer for touchscreen interaction                  │
│ - Stylus: Alternative to finger, provides more precision                   │
│                                                                             │
│ LARGE SCREENS / DESKTOPS                                                    │
├─────────────────────────────────────────────────────────────────────────────┤
│ - Mouse: Indirect interaction with display                                 │
│ - Trackpad: Indirect interaction with display                              │
└─────────────────────────────────────────────────────────────────────────────┘

REQUIREMENTS:
An input device must be able to "point" at a coordinate to be considered a pointer.
For example, a keyboard CANNOT be considered a pointer because it doesn't point
at specific coordinates.

IN COMPOSE:
The pointer type is included in pointer changes using PointerType:
- PointerType.Touch (finger or stylus on touchscreen)
- PointerType.Mouse
- PointerType.Stylus (on devices that distinguish stylus from touch)
- PointerType.Eraser (stylus with eraser function)
- PointerType.Unknown

EXAMPLE USAGE:
```
when (pointerEvent.changes.first().type) {
    PointerType.Touch -> handleTouchInput()
    PointerType.Mouse -> handleMouseInput()
    PointerType.Stylus -> handleStylusInput()
    else -> handleGenericInput()
}
```

═══════════════════════════════════════════════════════════════════════════════
2. POINTER EVENT
═══════════════════════════════════════════════════════════════════════════════

DEFINITION:
Describes a low-level interaction of one or more pointers with the application
at a given time.

WHAT TRIGGERS A POINTER EVENT:
- Putting a finger on the screen (down event)
- Moving a finger across the screen (move event)
- Lifting a finger from the screen (up event)
- Dragging a mouse (move event)
- Clicking a mouse button (down/up events)
- Hovering with mouse or stylus (hover event)

IN COMPOSE:
All relevant information for such an event is contained in the PointerEvent class.

POINTER EVENT CONTAINS:
- List of PointerInputChange objects (one for each active pointer)
- Type of event (Press, Release, Move, Enter, Exit)
- Timestamp of the event
- Button information (for mouse events)

EXAMPLE STRUCTURE:
```
PointerEvent {
    changes: List<PointerInputChange> // Info about each pointer
    type: PointerEventType           // Press, Release, Move, etc.
}

PointerInputChange {
    id: PointerId                    // Unique identifier for this pointer
    position: Offset                 // Current position
    previousPosition: Offset         // Position in previous event
    pressed: Boolean                 // Is pointer currently down?
    type: PointerType               // Touch, Mouse, Stylus, etc.
    // ... and more
}
```

MULTI-TOUCH EXAMPLE:
When a user puts two fingers on the screen, the PointerEvent contains TWO
PointerInputChange objects - one for each finger. Each has its own ID, position,
and state.

═══════════════════════════════════════════════════════════════════════════════
3. GESTURE
═══════════════════════════════════════════════════════════════════════════════

DEFINITION:
A sequence of pointer events that can be interpreted as a single action.

EXAMPLES OF GESTURES:

┌─────────────────────────────────────────────────────────────────────────────┐
│ SIMPLE GESTURES                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│ - Tap: Down event → Up event at same location                              │
│ - Double Tap: Two tap gestures in quick succession                         │
│ - Long Press: Down event → Wait → Still down after threshold               │
│ - Drag: Down event → Move events → Up event                                │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ COMPLEX GESTURES (Multi-touch)                                              │
├─────────────────────────────────────────────────────────────────────────────┤
│ - Pinch to Zoom: Two pointers moving closer/farther apart                  │
│ - Rotation: Two pointers rotating around a central point                   │
│ - Pan: Multiple pointers moving together in same direction                 │
│ - Transform: Combination of pan, zoom, and rotation                        │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ CUSTOM GESTURES                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│ - Drag after long-press: Long press → Drag movement                        │
│ - Control-click: Mouse click while holding Ctrl key                        │
│ - Three-finger tap: Three pointers down and up simultaneously              │
│ - Swipe to dismiss: Fast drag gesture in specific direction                │
└─────────────────────────────────────────────────────────────────────────────┘

GESTURE LIFECYCLE:
1. Gesture starts: First pointer goes down
2. Gesture continues: Pointer moves, additional pointers may join
3. Gesture ends: All pointers lift OR gesture is cancelled

COMMON GESTURES IN COMPOSE:
Compose provides built-in detection for many common gestures:
- Tap, double-tap, long-press (via detectTapGestures)
- Horizontal/vertical drag (via detectDragGestures)
- Multi-touch transform (via detectTransformGestures)

CUSTOM GESTURES:
When built-in gestures don't fit your needs, you can create custom gestures
using the pointerInput modifier to access raw pointer events.

═══════════════════════════════════════════════════════════════════════════════
RELATIONSHIP BETWEEN CONCEPTS
═══════════════════════════════════════════════════════════════════════════════

FLOW:
Pointer (physical object)
    ↓
Pointer Events (low-level interactions)
    ↓
Gesture (interpreted sequence of events)
    ↓
User Action (semantic meaning)

EXAMPLE - TAP GESTURE:
1. Pointer: User's finger
2. Pointer Events:
   - Event 1: Finger down at (100, 200)
   - Event 2: Finger up at (102, 198)
3. Gesture: Tap gesture detected (down + up at similar location)
4. User Action: Button clicked, item selected, etc.

EXAMPLE - PINCH ZOOM GESTURE:
1. Pointers: User's two fingers
2. Pointer Events:
   - Event 1: Finger 1 down at (100, 100)
   - Event 2: Finger 2 down at (200, 200)
   - Events 3-10: Both fingers move (distance changes)
   - Event 11: Finger 1 up
   - Event 12: Finger 2 up
3. Gesture: Pinch-to-zoom detected (two pointers with changing distance)
4. User Action: Image zoomed in or out

═══════════════════════════════════════════════════════════════════════════════
KEY TAKEAWAYS
═══════════════════════════════════════════════════════════════════════════════

1. Pointers are the physical input devices (finger, mouse, stylus)
2. Pointer Events are the raw, low-level data about interactions
3. Gestures are meaningful patterns recognized from pointer events
4. Compose provides multiple levels of abstraction for handling these concepts
5. Use the highest level of abstraction that meets your needs
*/

