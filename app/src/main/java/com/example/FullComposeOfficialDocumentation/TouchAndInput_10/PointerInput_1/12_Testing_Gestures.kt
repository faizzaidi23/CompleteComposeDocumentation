package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

/*
TESTING GESTURES

In your test methods, you can manually send pointer events using the performTouchInput
method. This lets you perform either higher-level full gestures (such as pinch or
long click) or low-level gestures (such as moving the cursor by a certain amount of pixels).

═══════════════════════════════════════════════════════════════════════════════
BASIC TESTING SETUP
═══════════════════════════════════════════════════════════════════════════════

To test gestures in Compose, you need:
1. ComposeTestRule to set up the Compose test environment
2. A way to find the composable you want to test
3. performTouchInput to simulate gestures

BASIC STRUCTURE:

class GestureTestExample {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGesture() {
        // 1. Set up your composable
        composeTestRule.setContent {
            YourComposable()
        }

        // 2. Find the composable using a matcher
        composeTestRule
            .onNodeWithTag("MyElement")

            // 3. Perform touch input
            .performTouchInput {
                // Gesture actions here
            }

        // 4. Verify the result
        composeTestRule
            .onNodeWithText("Expected Result")
            .assertExists()
    }
}

═══════════════════════════════════════════════════════════════════════════════
HIGH-LEVEL GESTURE METHODS
═══════════════════════════════════════════════════════════════════════════════

These methods simulate complete, common gestures:

┌─────────────────────────────────────────────────────────────────────────────┐
│ SWIPE GESTURES                                                              │
├─────────────────────────────────────────────────────────────────────────────┤
│ swipeUp()           - Swipe from bottom to top                             │
│ swipeDown()         - Swipe from top to bottom                             │
│ swipeLeft()         - Swipe from right to left                             │
│ swipeRight()        - Swipe from left to right                             │
│                                                                             │
│ Parameters:                                                                 │
│   startX, startY    - Starting position (default: center)                  │
│   endX, endY        - Ending position (default: edge)                      │
│   durationMillis    - Duration of swipe (default: 200ms)                   │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ CLICK/TAP GESTURES                                                          │
├─────────────────────────────────────────────────────────────────────────────┤
│ click()             - Single click at center                               │
│ click(position)     - Single click at specific position                    │
│ doubleClick()       - Double click at center                               │
│ longClick()         - Long press at center                                 │
│                                                                             │
│ Parameters:                                                                 │
│   position          - Offset where to click (default: center)              │
│   durationMillis    - How long to press (for longClick)                    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ PINCH GESTURES                                                              │
├─────────────────────────────────────────────────────────────────────────────┤
│ pinch()             - Pinch gesture (zoom in/out)                          │
│                                                                             │
│ Parameters:                                                                 │
│   start0, end0      - First finger start/end positions                     │
│   start1, end1      - Second finger start/end positions                    │
│   durationMillis    - Duration of pinch                                    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ ROTATION GESTURES                                                           │
├─────────────────────────────────────────────────────────────────────────────┤
│ rotate()            - Rotation gesture                                     │
│                                                                             │
│ Parameters:                                                                 │
│   angle             - Angle to rotate in degrees                           │
│   pivot             - Center point of rotation                             │
│   durationMillis    - Duration of rotation                                 │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
TESTING EXAMPLES
═══════════════════════════════════════════════════════════════════════════════

EXAMPLE 1: Testing swipe gestures

@Test
fun testSwipeGestures() {
    composeTestRule.setContent {
        MyScrollableList(modifier = Modifier.testTag("MyList"))
    }

    composeTestRule.onNodeWithTag("MyList").performTouchInput {
        swipeUp()  // Scroll down the list
        swipeDown()  // Scroll up the list

        // Custom swipe with specific positions
        swipeUp(
            startY = bottom,
            endY = top,
            durationMillis = 300
        )
    }
}

EXAMPLE 2: Testing click gestures

@Test
fun testClickGestures() {
    var clickCount = 0
    var longPressTriggered = false

    composeTestRule.setContent {
        Box(
            Modifier
                .testTag("ClickableBox")
                .combinedClickable(
                    onClick = { clickCount++ },
                    onLongClick = { longPressTriggered = true }
                )
        )
    }

    composeTestRule.onNodeWithTag("ClickableBox").performTouchInput {
        click()  // Single click
        click(Offset(x = 50f, y = 50f))  // Click at specific position
        doubleClick()  // Double click
        longClick()  // Long press
    }

    assert(clickCount == 2)
    assert(longPressTriggered)
}

═══════════════════════════════════════════════════════════════════════════════
LOW-LEVEL GESTURE METHODS
═══════════════════════════════════════════════════════════════════════════════

For more control, use low-level methods to build custom gestures:

┌─────────────────────────────────────────────────────────────────────────────┐
│ POINTER CONTROL                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│ down(pointerId, position)     - Put pointer down at position               │
│ moveTo(pointerId, position)   - Move pointer to position                   │
│ moveBy(pointerId, delta)      - Move pointer by delta amount               │
│ up(pointerId)                 - Lift pointer                               │
│ cancel()                      - Cancel current gesture                     │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ COORDINATES AND TIMING                                                      │
├─────────────────────────────────────────────────────────────────────────────┤
│ center               - Center position of the composable                   │
│ centerX, centerY     - Center coordinates                                  │
│ top, bottom          - Top/bottom edge coordinates                         │
│ left, right          - Left/right edge coordinates                         │
│ width, height        - Dimensions of the composable                        │
│                                                                             │
│ advanceEventTime(millis) - Advance time between events                     │
└─────────────────────────────────────────────────────────────────────────────┘

EXAMPLE: Low-level custom drag

@Test
fun testCustomDrag() {
    composeTestRule.setContent {
        DraggableBox(Modifier.testTag("DraggableBox"))
    }

    composeTestRule.onNodeWithTag("DraggableBox").performTouchInput {
        // Put pointer down
        down(0, Offset(centerX, centerY))

        // Move pointer in steps
        moveTo(0, Offset(centerX + 50f, centerY))
        advanceEventTime(16)  // 16ms frame time

        moveTo(0, Offset(centerX + 100f, centerY))
        advanceEventTime(16)

        // Lift pointer
        up(0)
    }
}

═══════════════════════════════════════════════════════════════════════════════
BEST PRACTICES FOR GESTURE TESTING
═══════════════════════════════════════════════════════════════════════════════

1. USE TEST TAGS
   Always add testTag to composables you want to test
   More reliable than finding by text or content description

2. TEST BEHAVIOR, NOT IMPLEMENTATION
   Test what the user experiences, not internal details
   Example: Test that list scrolled, not that offset changed

3. USE HIGH-LEVEL METHODS WHEN POSSIBLE
   Prefer swipeUp() over manual down/moveTo/up sequence
   Easier to read and maintain

4. USE LOW-LEVEL FOR CUSTOM GESTURES
   When testing unique gestures, use down/moveTo/up for precision

5. VERIFY RESULTS
   Always assert that the gesture had the expected effect
   Check UI state, text, visibility, etc.

6. TEST EDGE CASES
   - What happens when gesture is cancelled?
   - What happens with very fast/slow gestures?
   - What happens at boundaries?

═══════════════════════════════════════════════════════════════════════════════
SUMMARY
═══════════════════════════════════════════════════════════════════════════════

TESTING GESTURES IN COMPOSE:
1. Set up ComposeTestRule
2. Set content with testTag on composables
3. Find composable with onNodeWithTag
4. Use performTouchInput to simulate gestures
5. Verify results with assertions

GESTURE METHODS:
- High-level: swipeUp/Down/Left/Right, click, longClick, pinch, rotate
- Low-level: down, moveTo, moveBy, up, cancel

CHOOSE:
- High-level for common gestures (easier, more readable)
- Low-level for custom gestures (more control, more complex)

BEST PRACTICE:
Test user-visible behavior, use test tags for reliability,
and verify that gestures produce expected results.

NOTE: This file contains conceptual examples. For actual testing,
you would need to:
1. Add the test dependencies in build.gradle
2. Place test files in the androidTest or test directory
3. Use proper test annotations (@Test, @get:Rule)
4. Import testing libraries (junit, compose-ui-test)
*/
