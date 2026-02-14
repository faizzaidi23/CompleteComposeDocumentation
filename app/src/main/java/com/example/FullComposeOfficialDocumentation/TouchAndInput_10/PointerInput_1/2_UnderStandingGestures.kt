package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

/*
UNDERSTANDING GESTURES IN JETPACK COMPOSE

There are several terms and concepts that are important to understand when working
on gesture handling in an application. This documentation explains the terms pointers,
pointer events, and gestures, and introduces the different abstraction levels for gestures.
It also dives deeper into event consumption and propagation.

KEY TOPICS COVERED:
1. Definitions (Pointers, Pointer Events, Gestures)
2. Different levels of abstraction (Component support, Modifiers, PointerInput)
3. Component support with built-in gesture handling
4. Adding gestures with modifiers
5. Custom gestures with pointerInput
6. Event dispatching and hit-testing
7. Event consumption and propagation
8. Testing gestures

ABSTRACTION LEVELS (from highest to lowest):
- Component Support: Use built-in composables like Button, LazyColumn
- Gesture Modifiers: Use clickable, draggable, scrollable modifiers
- PointerInput: Create completely custom gesture handlers

RULE OF THUMB:
Build on the highest level of abstraction that offers the functionality you need.
This way, you benefit from the best practices included in the layer.
For example, Button contains more semantic information (used for accessibility)
than clickable, which contains more information than a raw pointerInput implementation.
*/