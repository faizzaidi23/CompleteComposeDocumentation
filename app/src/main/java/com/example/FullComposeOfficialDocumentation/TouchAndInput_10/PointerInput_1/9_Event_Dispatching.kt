package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

/*
EVENT DISPATCHING AND HIT-TESTING

Not every pointer event is sent to every pointerInput modifier. Understanding how
events are dispatched and which composables receive them is crucial for building
correct gesture handling.

═══════════════════════════════════════════════════════════════════════════════
HOW EVENT DISPATCHING WORKS
═══════════════════════════════════════════════════════════════════════════════

THE PROCESS:

1. INITIAL HIT-TESTING
   When a new pointer triggers its first event, the system performs hit-testing
   to determine which composables should receive events for this pointer.

2. ELIGIBILITY
   A composable is "eligible" when it has pointer input handling capabilities.
   Examples: Has pointerInput modifier, clickable, draggable, etc.

3. HIT-TEST FLOW
   Hit-testing flows from the TOP of the UI tree to the BOTTOM.
   A composable is "hit" when the pointer event occurred within its bounds.

4. HIT-TEST RESULT
   This process creates a CHAIN of composables that hit-test positively.
   All composables in this chain will receive events for this pointer.

5. SUBSEQUENT EVENTS
   Further events for the same pointer are dispatched to that SAME CHAIN.
   No more hit-testing is performed for this pointer.

6. EVENTS OUTSIDE BOUNDS
   Each composable in the chain receives ALL events for that pointer,
   even when those occur outside of the composable's bounds.

7. COMPOSABLES NOT IN CHAIN
   Composables not in the chain NEVER receive pointer events,
   even when the pointer moves inside their bounds.

═══════════════════════════════════════════════════════════════════════════════
VISUAL EXAMPLE
═══════════════════════════════════════════════════════════════════════════════

UI STRUCTURE:
┌─────────────────────────────────────────────────────────────────────────┐
│ Box A (pointerInput)                                                    │
│  ┌───────────────────────────────────────────────────────────────────┐  │
│  │ Box B (pointerInput)                                              │  │
│  │  ┌─────────────────────────────────────────────────────────────┐  │  │
│  │  │ Box C (pointerInput)                                        │  │  │
│  │  │                                                             │  │  │
│  │  └─────────────────────────────────────────────────────────────┘  │  │
│  └───────────────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────────────┘

SCENARIO 1: User taps on Box C
Hit-test result: A → B → C (all three are hit)
Event chain: A, B, and C all receive pointer events

SCENARIO 2: User drags from C outside of C but still in B
Hit-test was performed when pointer went down in C
Event chain: Still A, B, and C (even though pointer left C)
Result: C still receives events even outside its bounds

SCENARIO 3: User taps on Box A but outside of B
Hit-test result: Only A is hit
Event chain: Only A receives pointer events
Result: B and C never receive this pointer's events

═══════════════════════════════════════════════════════════════════════════════
KEY RULES
═══════════════════════════════════════════════════════════════════════════════

RULE 1: HIT-TESTING HAPPENS ONCE
Hit-testing is performed only when a pointer first goes down.
Subsequent events for that pointer are sent to the same chain.

RULE 2: EVENTS OUTSIDE BOUNDS STILL DELIVERED
A composable in the chain receives all events for that pointer,
even if the pointer moves outside the composable's bounds.

WHY? This allows you to detect when a user:
- Starts a drag inside a button but moves outside (cancel the click)
- Draws continuously even when pointer moves outside drawing area
- Performs gestures that start in one area but move to another

RULE 3: NOT IN CHAIN = NO EVENTS
If a composable wasn't hit during initial hit-testing, it will
never receive events for that pointer, even if the pointer later
moves into its bounds.

WHY? This prevents unexpected gesture handling when dragging
across the screen.

═══════════════════════════════════════════════════════════════════════════════
OVERLAPPING COMPOSABLES - Z-INDEX MATTERS
═══════════════════════════════════════════════════════════════════════════════

When multiple eligible composables are on the same level of the tree,
only the composable with the HIGHEST Z-INDEX is "hit".

EXAMPLE:
*/

/*
Box {
    Button(
        onClick = { println("Button 1") },
        modifier = Modifier.size(100.dp)
    ) { Text("Button 1") }

    // This button is drawn on top (higher z-index)
    Button(
        onClick = { println("Button 2") },
        modifier = Modifier.size(100.dp)
    ) { Text("Button 2") }
}

RESULT:
Only Button 2 receives pointer events in the overlapping area.
Button 1 is underneath and doesn't receive events.

TO CONTROL Z-INDEX:
Use Modifier.zIndex(value) to explicitly set z-index.
Higher values are drawn on top and receive events first.
*/

/*
═══════════════════════════════════════════════════════════════════════════════
ADVANCED: SHARING POINTER INPUT WITH SIBLINGS
═══════════════════════════════════════════════════════════════════════════════

By default, only the top-most composable receives events.
You can theoretically override this behavior by creating your own
PointerInputModifierNode implementation and setting
sharePointerInputWithSiblings to true.

This is an advanced technique rarely needed in typical apps.

═══════════════════════════════════════════════════════════════════════════════
EXCEPTION: HOVER EVENTS
═══════════════════════════════════════════════════════════════════════════════

Hover events (triggered by mouse or stylus hovering) are an EXCEPTION
to the rules defined above.

HOVER EVENT RULES:
1. Hover events are sent to ANY composable that they hit
2. When pointer hovers from one composable to another, events are
   sent to the NEW composable (not the original one)
3. No chain is created for hover events
4. Each hover event performs fresh hit-testing

EXAMPLE SCENARIO:
User hovers mouse over Button A, then moves to Button B:
- While over A: A receives hover events
- While over B: B receives hover events (A stops receiving them)

This is different from down/move/up events where the chain
is established at the first down event.

WHY THE DIFFERENCE?
Hover is about which element is currently under the cursor.
It makes sense to send events to whichever element is currently
being hovered over.

Down/move/up events are about gesture tracking.
Once a gesture starts, it should be tracked by the same composables
throughout its lifetime.

═══════════════════════════════════════════════════════════════════════════════
PRACTICAL IMPLICATIONS
═══════════════════════════════════════════════════════════════════════════════

IMPLICATION 1: DRAG OUTSIDE BOUNDS WORKS
You can start dragging inside a small button and continue the drag
even after moving outside the button. The button continues to receive
drag events.

IMPLICATION 2: CAN'T "STEAL" ONGOING GESTURE
Once a pointer is assigned to a chain of composables, other composables
can't intercept it, even if the pointer moves into their bounds.

IMPLICATION 3: MULTI-POINTER SCENARIOS
Each pointer has its own independent chain. In multi-touch gestures,
different pointers can have different chains.

Example: Two-finger pinch gesture
- Finger 1 might create chain: Root → Container → ImageA
- Finger 2 might create chain: Root → Container → ImageB
- Both fingers' events go to Container (common ancestor)

IMPLICATION 4: NESTED SCROLLING
This is why nested scrolling works correctly. The inner scrollable
establishes its chain first, and the outer scrollable only receives
events if the inner one doesn't consume them.

═══════════════════════════════════════════════════════════════════════════════
DEBUGGING HIT-TESTING
═══════════════════════════════════════════════════════════════════════════════

If gestures aren't working as expected, check:

1. Is the composable eligible? (Has pointerInput/clickable/etc?)
2. Is the pointer actually hitting the composable's bounds?
3. Is another composable with higher z-index blocking it?
4. Is the pointer chain already established to different composables?
5. Are events being consumed by a parent or child?

Use the Layout Inspector to visualize composable bounds and hierarchy.

═══════════════════════════════════════════════════════════════════════════════
SUMMARY
═══════════════════════════════════════════════════════════════════════════════

EVENT FLOW:
1. Pointer down → Hit-testing from top to bottom of UI tree
2. Create chain of hit composables
3. All subsequent events for this pointer → Same chain
4. Events delivered even if pointer moves outside bounds
5. Composables not in chain never receive events for this pointer

EXCEPTIONS:
- Hover events: Fresh hit-testing for each event
- SharePointerInputWithSiblings: Advanced custom behavior

KEY INSIGHT:
The hit-test chain is established at pointer DOWN and remains
constant for the lifetime of that pointer. This enables consistent
gesture tracking throughout a gesture's lifecycle.
*/

