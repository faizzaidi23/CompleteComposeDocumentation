package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput

/*
EVENT PROPAGATION - THE THREE PASSES

As mentioned before, pointer changes are passed to each composable that it hits.
But if more than one such composable exists, in what order do the events propagate?

Understanding event propagation is crucial for building complex gesture interactions.

═══════════════════════════════════════════════════════════════════════════════
THE THREE PROPAGATION PASSES
═══════════════════════════════════════════════════════════════════════════════

Pointer events flow through each composable THREE times, during three "passes":

┌─────────────────────────────────────────────────────────────────────────────┐
│ 1. INITIAL PASS (Parent → Child)                                            │
├─────────────────────────────────────────────────────────────────────────────┤
│ FLOW: Top of UI tree → Bottom (leaf nodes)                                 │
│                                                                             │
│ PURPOSE: Allows parents to intercept events before children see them       │
│                                                                             │
│ USE CASE: Tooltips intercepting long-press before child handles it         │
│          Parents that need first chance to handle events                   │
│                                                                             │
│ EXAMPLE: ListItem receives event before Button inside it                   │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ 2. MAIN PASS (Child → Parent) [DEFAULT]                                    │
├─────────────────────────────────────────────────────────────────────────────┤
│ FLOW: Leaf nodes → Root of UI tree                                         │
│                                                                             │
│ PURPOSE: Normal gesture handling - children get priority over parents      │
│                                                                             │
│ USE CASE: This is where most gesture handling happens                      │
│          Children handle events first, parents handle if not consumed      │
│                                                                             │
│ EXAMPLE: Button receives event before ListItem                             │
│                                                                             │
│ NOTE: This is the DEFAULT pass when listening to events                    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ 3. FINAL PASS (Parent → Child)                                              │
├─────────────────────────────────────────────────────────────────────────────┤
│ FLOW: Top of UI tree → Bottom (leaf nodes)                                 │
│                                                                             │
│ PURPOSE: Allows parents to respond to event consumption by children        │
│                                                                             │
│ USE CASE: Button removing ripple when parent scroll starts dragging        │
│          Parents need to clean up after children consumed events           │
│                                                                             │
│ EXAMPLE: ListItem receives event after Button (can see if Button consumed) │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
VISUAL REPRESENTATION - UI TREE EXAMPLE
═══════════════════════════════════════════════════════════════════════════════

UI STRUCTURE:
┌─────────────────────────────────────────────────────────────────────────────┐
│ ListItem (1)                                                                │
│  ┌─────┐  ┌──────────┐  ┌──────────────────┐                               │
│  │Image│  │ Column   │  │ Button (4)       │                               │
│  │(2)  │  │  Text(3) │  │                  │                               │
│  │     │  │  Text(3) │  │                  │                               │
│  └─────┘  └──────────┘  └──────────────────┘                               │
└─────────────────────────────────────────────────────────────────────────────┘

Only ListItem (1) and Button (4) respond to pointer events.
Image (2) and Texts (3) are not interactive in this example.

SIMPLIFIED UI TREE (only interactive elements):
    ListItem (1)
        │
        └── Button (4)

EVENT FLOW FOR ALL THREE PASSES:

INITIAL PASS (Parent to Child):
    ListItem (1) ───→ Button (4)

MAIN PASS (Child to Parent):
    Button (4) ───→ ListItem (1)

FINAL PASS (Parent to Child):
    ListItem (1) ───→ Button (4)

═══════════════════════════════════════════════════════════════════════════════
COMPLETE EVENT FLOW VISUALIZATION
═══════════════════════════════════════════════════════════════════════════════

TIMELINE OF EVENT PROCESSING:

Time →

1. Initial Pass
   ┌─────────┐        ┌────────┐
   │ListItem │───────→│ Button │
   └─────────┘        └────────┘
   Parent sees event first

2. Main Pass (DEFAULT)
   ┌─────────┐        ┌────────┐
   │ListItem │←───────│ Button │
   └─────────┘        └────────┘
   Child handles first

3. Final Pass
   ┌─────────┐        ┌────────┐
   │ListItem │───────→│ Button │
   └─────────┘        └────────┘
   Parent sees consumption result

═══════════════════════════════════════════════════════════════════════════════
EVENT CONSUMPTION ACROSS PASSES
═══════════════════════════════════════════════════════════════════════════════

Once an input change is consumed, this information is passed forward:

SCENARIO: Button consumes event in Main Pass

INITIAL PASS:
   ListItem receives: isConsumed = false
   Button receives: isConsumed = false

MAIN PASS:
   Button receives: isConsumed = false
   Button handles and CONSUMES event
   ListItem receives: isConsumed = TRUE ← Now marked as consumed

FINAL PASS:
   ListItem receives: isConsumed = TRUE ← Still marked
   Button receives: isConsumed = TRUE

═══════════════════════════════════════════════════════════════════════════════
CODE EXAMPLE - SPECIFYING WHICH PASS TO USE
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun EventPassExample() {
    Box(
        Modifier.pointerInput(Unit) {
            awaitPointerEventScope {
                /*
                You can specify which pass you want to listen to
                */

                /*
                Initial Pass - Receive events before children
                */
                val eventOnInitialPass = awaitPointerEvent(PointerEventPass.Initial)

                /*
                Main Pass - Default, most common usage
                */
                val eventOnMainPass = awaitPointerEvent(PointerEventPass.Main)
                // or simply: awaitPointerEvent() // Main is default

                /*
                Final Pass - Receive events after children handled them
                */
                val eventOnFinalPass = awaitPointerEvent(PointerEventPass.Final)

                /*
                IMPORTANT: These are the SAME event, but consumption
                state may have changed between passes
                */
            }
        }
    )
}

/*
In the example above, the SAME identical event is returned by each await call,
although the data about consumption might have changed.

═══════════════════════════════════════════════════════════════════════════════
USE CASES FOR EACH PASS
═══════════════════════════════════════════════════════════════════════════════

INITIAL PASS USE CASES:

1. PARENT INTERCEPTS BEFORE CHILD
   A parent needs to handle event before child sees it

   Example: Tooltip that intercepts long-press
   - Tooltip (parent) listens on Initial pass
   - Intercepts long-press before child button handles it
   - Shows tooltip, consumes event
   - Child button never sees the long-press

2. GESTURE DISAMBIGUATION
   Parent needs to determine gesture type before children handle it

   Example: Detecting if gesture is drag or tap
   - Parent checks initial movement direction
   - If horizontal drag: Parent handles (scrolling)
   - If vertical: Let children handle


MAIN PASS USE CASES (Most Common):

1. NORMAL GESTURE HANDLING
   Default behavior - children get priority

   Example: Button in a list
   - Button handles tap on Main pass
   - If Button doesn't consume, list handles it

2. LEAF NODE INTERACTIONS
   Elements that don't have interactive children

   Example: Custom drawing canvas
   - Canvas handles all pointer events
   - No children to consider


FINAL PASS USE CASES:

1. CLEANUP AFTER CONSUMPTION
   Parent needs to react to child consuming event

   Example: Button in scrollable list
   - User starts press on Button (shows ripple)
   - User drags (scrolls the list)
   - On Final pass, Button sees drag was consumed by scroll
   - Button removes ripple effect

2. COORDINATED GESTURES
   Parent and child need to coordinate behavior

   Example: Nested scrolling
   - Child scroll consumes event when scrolling
   - Parent sees consumption on Final pass
   - Parent adjusts its own scroll state accordingly

═══════════════════════════════════════════════════════════════════════════════
PRACTICAL EXAMPLE - BUTTON WITH RIPPLE IN SCROLLABLE
═══════════════════════════════════════════════════════════════════════════════

SCENARIO: User presses button in scrollable list, then drags to scroll

TIMELINE:

1. User presses button
   Main Pass: Button receives press → Shows ripple

2. User starts dragging
   Initial Pass: List checks if this is a drag gesture
   Main Pass: List consumes drag event → Scrolls
   Final Pass: Button sees event was consumed → Removes ripple

RESULT: Button shows initial ripple, but removes it when scroll starts.
This creates the correct UX where ripple doesn't persist during scroll.

═══════════════════════════════════════════════════════════════════════════════
WHY THREE PASSES?
═══════════════════════════════════════════════════════════════════════════════

THREE PASSES ENABLE:

1. PARENT INTERCEPTION (Initial)
   Parents can intercept events before children

2. CHILD PRIORITY (Main)
   Children get first chance to handle events normally

3. PARENT RESPONSE (Final)
   Parents can respond to child consumption

This design enables complex gesture coordination while maintaining
predictable event flow.

═══════════════════════════════════════════════════════════════════════════════
PASS SELECTION GUIDELINE
═══════════════════════════════════════════════════════════════════════════════

✓ USE MAIN PASS (Default):
  - 95% of gesture handling
  - When you just need to handle gestures normally
  - When no special parent/child coordination needed

✓ USE INITIAL PASS:
  - Parent needs to intercept before child
  - Parent needs to make decisions before children
  - Rare, special cases only

✓ USE FINAL PASS:
  - Parent needs to clean up after child consumption
  - Parent needs to coordinate with child behavior
  - Rare, special cases only

IF IN DOUBT: Use Main pass (or don't specify a pass, it's the default)

═══════════════════════════════════════════════════════════════════════════════
DEBUGGING EVENT PROPAGATION
═══════════════════════════════════════════════════════════════════════════════

To debug event propagation issues:

1. Log events in different passes to see the flow
2. Check consumption status in each pass
3. Verify parent/child hierarchy is correct
4. Use Layout Inspector to visualize composable tree
5. Remember: Same event flows through all three passes

EXAMPLE DEBUG CODE:
*/

@Composable
fun DebugEventPropagation() {
    Box(
        Modifier.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val initial = awaitPointerEvent(PointerEventPass.Initial)
                    println("Initial: consumed=${initial.changes.first().isConsumed}")

                    val main = awaitPointerEvent(PointerEventPass.Main)
                    println("Main: consumed=${main.changes.first().isConsumed}")

                    val final = awaitPointerEvent(PointerEventPass.Final)
                    println("Final: consumed=${final.changes.first().isConsumed}")
                }
            }
        }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
SUMMARY
═══════════════════════════════════════════════════════════════════════════════

EVENT PROPAGATION FLOW:

1. INITIAL PASS: Parent → Child
   Purpose: Parent interception

2. MAIN PASS: Child → Parent (DEFAULT)
   Purpose: Normal gesture handling

3. FINAL PASS: Parent → Child
   Purpose: Parent response to consumption

KEY INSIGHTS:
- Same event flows through all three passes
- Consumption state can change between passes
- Main pass is default and most commonly used
- Initial and Final passes enable advanced parent/child coordination
- Understanding passes is crucial for complex gesture interactions

BEST PRACTICE:
Use Main pass for normal gesture handling.
Only use Initial/Final passes when you specifically need
parent/child coordination.
*/

