package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.TapAndPress_2

/*
TAP AND PRESS GESTURES IN JETPACK COMPOSE

Many composables have built-in support for taps or clicks and include an onClick lambda.
For example, you can create a clickable Surface that includes all Material Design behavior
appropriate for interaction with surfaces.

BASIC EXAMPLE:
Surface(onClick = { /* handle click */ }) {
    Text("Click me!", Modifier.padding(24.dp))
}

BEYOND CLICKS:
Clicks are not the only way a user can interact with composables. This documentation
focuses on gestures that involve a single pointer, where the position of that pointer
is not significant for the handling of that event.

═══════════════════════════════════════════════════════════════════════════════
SINGLE-POINTER GESTURE TYPES
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│ Gesture        │ Description                                                │
├────────────────┼────────────────────────────────────────────────────────────┤
│ Tap (Click)    │ Pointer goes down and then up                              │
│ Double Tap     │ Pointer goes down, up, down, up (in quick succession)      │
│ Long-Press     │ Pointer goes down and is held for a longer time (~500ms)  │
│ Press          │ Pointer goes down (regardless of what happens after)       │
└─────────────────────────────────────────────────────────────────────────────┘

KEY TOPICS COVERED:
1. Responding to tap or click with clickable modifier
2. Adding multiple gesture types with combinedClickable
3. Dismissing composables by tapping a scrim
4. Implementing double-tap to zoom functionality
5. Accessing tap position for advanced interactions

MODIFIER ABSTRACTION LEVELS:
- High-level: clickable, combinedClickable (includes accessibility, keyboard, focus)
- Low-level: pointerInput + detectTapGestures (for custom behavior)

RULE OF THUMB:
Use high-level modifiers (clickable, combinedClickable) when possible.
Only drop to pointerInput when you need custom behavior that high-level
modifiers don't provide (like tap position, custom scrim behavior, etc.)
*/