package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import com.example.FullComposeOfficialDocumentation.R

/*
EVENT CONSUMPTION AND PROPAGATION

When more than one composable has a gesture handler assigned to it, those handlers
shouldn't conflict. Understanding event consumption and propagation is crucial for
building correct gesture interactions.

═══════════════════════════════════════════════════════════════════════════════
THE PROBLEM - CONFLICTING GESTURE HANDLERS
═══════════════════════════════════════════════════════════════════════════════

EXAMPLE SCENARIO:
List item with:
- Parent Row (clickable - opens article)
- Image thumbnail
- Text content
- Bookmark button (clickable - bookmarks article)

┌─────────────────────────────────────────────────────────────────────────────┐
│ Row (clickable - opens article)                                             │
│  ┌─────┐  ┌──────────────┐  ┌──────────────────────────────────────┐       │
│  │Image│  │Article Title │  │              [Bookmark Button]       │       │
│  │     │  │Subtitle text │  │                                      │       │
│  └─────┘  └──────────────┘  └──────────────────────────────────────┘       │
└─────────────────────────────────────────────────────────────────────────────┘

THE QUESTION:
When user taps the bookmark button, what should happen?
- Should ONLY the button's onClick be called? (Bookmark the article)
- Or should BOTH button AND row onClick be called? (Bookmark AND open article)

THE ANSWER:
Only the button should respond. The Row should NOT also handle the tap.

HOW? The button must "consume" the event to tell the Row not to react to it.

═══════════════════════════════════════════════════════════════════════════════
EVENT CONSUMPTION
═══════════════════════════════════════════════════════════════════════════════

WHAT IS EVENT CONSUMPTION?
Marking an event as "consumed" to signal that it has been handled.
Other composables should check if an event is consumed before handling it.

BUILT-IN CONSUMPTION:
Gestures included in out-of-the-box components and common gesture modifiers
include consumption behavior automatically.

EXAMPLES:
- Button automatically consumes tap events
- clickable modifier automatically consumes tap events
- draggable modifier automatically consumes drag events
*/

@Composable
fun ListItemExample() {
    /*
    This example shows how consumption prevents conflicts
    */
    Row(
        modifier = Modifier.clickable {
            /*
            This Row is clickable - it opens the article
            */
            openArticle()
        }
    ) {
        /*
        Image and text are part of the Row's clickable area
        */
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "Article thumbnail"
        )
        Text("Article Title")

        /*
        The bookmark button consumes tap events.
        When tapped, the button's onClick runs, and the event is consumed.
        The Row's clickable doesn't trigger because the event was consumed.
        */
        IconButton(
            onClick = { bookmarkArticle() }
        ) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Bookmark"
            )
        }
    }
}

/*
RESULT:
- Tap on Image or Text → Row's clickable triggers → Opens article
- Tap on Bookmark button → Button's onClick triggers → Bookmarks article
                         → Event is consumed
                         → Row's clickable does NOT trigger

═══════════════════════════════════════════════════════════════════════════════
MANUAL EVENT CONSUMPTION
═══════════════════════════════════════════════════════════════════════════════

When writing custom gestures with pointerInput, you must consume events manually.
Use the PointerInputChange.consume() method.
*/

@Composable
fun ManualConsumptionExample() {
    Box(
        Modifier.pointerInput(Unit) {
            awaitEachGesture {
                while (true) {
                    val event = awaitPointerEvent()

                    /*
                    Process the event
                    */
                    handleEvent(event)

                    /*
                    Consume all changes to prevent parent from handling them
                    */
                    event.changes.forEach { change ->
                        change.consume()
                    }
                }
            }
        }
    )
}

/*
WHAT consume() DOES:
Marks the PointerInputChange as consumed. Other composables can check
the isConsumed property to see if they should ignore this event.

═══════════════════════════════════════════════════════════════════════════════
CONSUMPTION DOES NOT STOP PROPAGATION
═══════════════════════════════════════════════════════════════════════════════

IMPORTANT: Consuming an event does NOT stop the event propagation to other
composables. All composables in the hit-test chain still receive the event.

WHAT CONSUMPTION DOES:
It marks the event as consumed, so other composables can CHOOSE to ignore it.

WHAT COMPOSABLES MUST DO:
Explicitly check if an event was consumed and decide whether to handle it.
*/

@Composable
fun CheckConsumptionExample() {
    Box(
        Modifier.pointerInput(Unit) {
            awaitEachGesture {
                while (true) {
                    val event = awaitPointerEvent()

                    /*
                    Check if any pointer change was already consumed
                    */
                    if (event.changes.any { it.isConsumed }) {
                        /*
                        A pointer was consumed by another gesture handler.
                        We should probably ignore this event.
                        */
                        continue
                    } else {
                        /*
                        Event is not consumed - we can handle it
                        */
                        handleUnconsumedEvent(event)

                        /*
                        Now consume it so others don't also handle it
                        */
                        event.changes.forEach { it.consume() }
                    }
                }
            }
        }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
CONSUMPTION BEST PRACTICES
═══════════════════════════════════════════════════════════════════════════════

1. USE BUILT-IN COMPONENTS WHEN POSSIBLE
   They handle consumption correctly automatically.

2. CONSUME EVENTS IN CUSTOM GESTURES
   When using pointerInput, consume events after handling them.

3. CHECK isConsumed BEFORE HANDLING
   Respect when other handlers have already consumed the event.

4. CONSUME ONLY WHAT YOU HANDLE
   Don't consume events you're not actually handling.

5. BE AWARE OF PROPAGATION
   Events propagate through all phases even when consumed.
   Consumption is just a flag for others to check.

═══════════════════════════════════════════════════════════════════════════════
PRACTICAL EXAMPLE - NESTED SCROLLING
═══════════════════════════════════════════════════════════════════════════════

Nested scrolling is a perfect example of consumption:

┌─────────────────────────────────────────────────────────────────────────────┐
│ Outer ScrollColumn                                                          │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │ Inner ScrollColumn                                                    │  │
│  │  Item 1                                                               │  │
│  │  Item 2                                                               │  │
│  │  Item 3                                                               │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘

BEHAVIOR:
- When inner scroll can scroll: Inner consumes events, outer doesn't scroll
- When inner scroll reaches end: Inner doesn't consume, outer starts scrolling

HOW IT WORKS:
1. Inner scrollable checks if it can scroll in drag direction
2. If yes: Consume the event and scroll
3. If no: Don't consume the event
4. Outer scrollable receives event
5. Outer checks if event is consumed
6. If not consumed: Outer scrolls

═══════════════════════════════════════════════════════════════════════════════
CONSUMPTION EXAMPLE - BUTTON IN SCROLLABLE LIST
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun ButtonInScrollableList() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        repeat(50) { index ->
            Row {
                Text("Item $index")

                Button(onClick = {
                    /*
                    The Button consumes tap events.
                    This prevents the list from scrolling when
                    you tap the button.

                    However, if you drag on the button (not just tap),
                    the button doesn't consume drag events, so the
                    list scrolls normally.
                    */
                    handleButtonClick(index)
                }) {
                    Text("Action")
                }
            }
        }
    }
}

/*
RESULT:
- Tap button → Button onClick triggers, list doesn't scroll
- Drag on button → List scrolls, button doesn't click
- Drag on text → List scrolls

═══════════════════════════════════════════════════════════════════════════════
CONSUMPTION IN DIFFERENT PROPAGATION PASSES
═══════════════════════════════════════════════════════════════════════════════

Events propagate through three passes (covered in next section):
1. Initial pass (parent to child)
2. Main pass (child to parent)
3. Final pass (parent to child)

Consumption works in all passes. An event consumed in the Initial pass
is marked as consumed for Main and Final passes too.

═══════════════════════════════════════════════════════════════════════════════
SUMMARY
═══════════════════════════════════════════════════════════════════════════════

KEY POINTS:
1. Event consumption prevents gesture conflicts
2. Built-in components consume events automatically
3. Custom gestures must consume events manually with consume()
4. Consuming does NOT stop propagation - it just sets a flag
5. Other handlers should check isConsumed before handling
6. Consumption flows through all propagation passes

CONSUMPTION FLOW:
Handle event → Decide if you handled it → If yes: consume()
Receive event → Check isConsumed → If consumed: maybe ignore

BEST PRACTICE:
Use built-in components and modifiers when possible. They handle
consumption correctly. Only manage consumption manually when using
raw pointerInput for custom gestures.
*/

fun openArticle() {}
fun bookmarkArticle() {}
fun handleEvent(event: Any) { /* Process event */ }
fun handleUnconsumedEvent(event: Any) { /* Process unconsumed event */ }
fun handleButtonClick(index: Int) { /* Handle button click for item */ }
