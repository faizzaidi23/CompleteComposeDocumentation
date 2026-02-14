package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.PointerInput_1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
COMPONENT SUPPORT - BUILT-IN GESTURE HANDLING

Many out-of-the-box components in Compose include some sort of internal gesture handling.
This gesture handling works automatically and requires minimal setup.

═══════════════════════════════════════════════════════════════════════════════
COMPONENTS WITH INTERNAL GESTURE HANDLING
═══════════════════════════════════════════════════════════════════════════════

┌─────────────────────────────────────────────────────────────────────────────┐
│ SCROLLING COMPONENTS                                                        │
├─────────────────────────────────────────────────────────────────────────────┤
│ - LazyColumn: Responds to vertical drag gestures by scrolling              │
│ - LazyRow: Responds to horizontal drag gestures by scrolling               │
│ - LazyVerticalGrid: Responds to vertical drag gestures                     │
│ - HorizontalPager: Responds to horizontal swipe gestures                   │
│                                                                             │
│ BEHAVIOR: Automatically scrolls content when user drags                    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ INTERACTIVE COMPONENTS                                                      │
├─────────────────────────────────────────────────────────────────────────────┤
│ - Button: Shows ripple when pressed, triggers onClick                      │
│ - IconButton: Shows ripple when pressed, triggers onClick                  │
│ - FloatingActionButton: Shows ripple and elevation change                  │
│ - Switch: Responds to taps and drags                                       │
│ - Checkbox: Responds to taps                                               │
│ - RadioButton: Responds to taps                                            │
│ - Slider: Responds to drags on the handle                                  │
│                                                                             │
│ BEHAVIOR: Visual feedback + trigger callback on interaction                │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│ DISMISSIBLE COMPONENTS                                                      │
├─────────────────────────────────────────────────────────────────────────────┤
│ - SwipeToDismiss: Contains built-in swiping logic to dismiss              │
│ - DismissibleNavigationDrawer: Swipe to open/close                        │
│ - ModalBottomSheet: Swipe down to dismiss                                 │
│                                                                             │
│ BEHAVIOR: Responds to swipe gestures with dismiss animation                │
└─────────────────────────────────────────────────────────────────────────────┘

═══════════════════════════════════════════════════════════════════════════════
TWO TYPES OF COMPONENT GESTURE HANDLING
═══════════════════════════════════════════════════════════════════════════════

TYPE 1: INTERNAL GESTURE HANDLING (Automatic)
Works automatically without any code from you.
Example: LazyColumn scrolls when user drags

TYPE 2: CALLER-HANDLED GESTURES (Requires callback)
Component detects the gesture, but YOU handle what happens.
Example: Button detects tap, but YOU provide the onClick action

═══════════════════════════════════════════════════════════════════════════════
EXAMPLES - CALLER-HANDLED GESTURES
═══════════════════════════════════════════════════════════════════════════════
*/

/*
Button automatically detects taps and triggers a click event.
You pass an onClick lambda to react to the gesture.
*/
@Composable
fun ButtonExample() {
    Button(onClick = {
        /*
        The Button handles:
        - Detecting the tap gesture
        - Showing ripple effect
        - Providing haptic feedback
        - Accessibility announcements

        You handle:
        - What happens when clicked
        */
        println("Button clicked!")
    }) {
        Text("Click me!")
    }
}

/*
Slider automatically handles dragging the slider handle.
You pass an onValueChange lambda to react to the gesture.
*/
@Composable
fun SliderExample() {
    var sliderValue = 0f

    androidx.compose.material3.Slider(
        value = sliderValue,
        onValueChange = { newValue ->
            /*
            The Slider handles:
            - Detecting drag gestures on the handle
            - Moving the handle smoothly
            - Restricting movement to the track
            - Showing visual feedback

            You handle:
            - Storing the new value
            - Reacting to value changes
            */
            sliderValue = newValue
        }
    )
}

/*
Switch automatically handles taps and drags.
You pass an onCheckedChange lambda to react to the gesture.
*/
@Composable
fun SwitchExample() {
    var checked = false

    androidx.compose.material3.Switch(
        checked = checked,
        onCheckedChange = { newChecked ->
            /*
            The Switch handles:
            - Detecting tap gestures
            - Detecting drag gestures on the thumb
            - Animating the switch
            - Showing visual feedback

            You handle:
            - Storing the new checked state
            - Reacting to state changes
            */
            checked = newChecked
        }
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
BENEFITS OF USING COMPONENT SUPPORT
═══════════════════════════════════════════════════════════════════════════════

1. ACCESSIBILITY SUPPORT (Most Important!)
   Components include proper semantic information for accessibility services.
*/

@Composable
fun AccessibilityComparison() {
    Column {
        /*
        Button provides rich accessibility information:
        TalkBack announces: "Click me!, Button, double tap to activate"

        The word "Button" tells users this is an actionable button element,
        which is more informative than just "clickable"
        */
        Button(onClick = { /* TODO */ }) {
            Text("Click me!")
        }

        /*
        Box with clickable has less semantic information:
        TalkBack announces: "Click me!, double tap to activate"

        It's clickable, but users don't know it's specifically a button
        */
        Box(
            Modifier.clickable { /* TODO */ }
        ) {
            Text("Click me!")
        }
    }
}

/*
2. FOCUS AND KEYBOARD SUPPORT
   Components automatically handle keyboard navigation and focus indicators.
   - Button can be activated with Enter or Space key
   - Shows focus indicator when navigating with keyboard
   - Manages focus states correctly

3. VISUAL FEEDBACK
   Components provide appropriate visual feedback for interactions:
   - Ripple effects on press
   - Elevation changes
   - Color changes for different states (pressed, hovered, focused, disabled)
   - Smooth animations

4. WELL-TESTED
   Built-in components are thoroughly tested across:
   - Different screen sizes
   - Different input methods (touch, mouse, keyboard)
   - Different accessibility services
   - Edge cases and error conditions

5. PLATFORM CONSISTENCY
   Components follow platform conventions, making your app feel native:
   - Consistent touch target sizes
   - Standard visual feedback
   - Expected behavior patterns

═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE COMPONENT SUPPORT
═══════════════════════════════════════════════════════════════════════════════

✓ PREFER COMPONENTS WHEN:
- A built-in component matches your use case
- You need accessibility support (almost always!)
- You want consistent platform behavior
- You want to minimize custom code
- You need keyboard and focus support

✗ USE MODIFIERS OR POINTERINPUT WHEN:
- No built-in component fits your needs
- You need completely custom visual appearance
- You're building a specialized interaction pattern
- Built-in components are too opinionated for your use case

═══════════════════════════════════════════════════════════════════════════════
BEST PRACTICE
═══════════════════════════════════════════════════════════════════════════════

When creating custom clickable elements, consider whether you can build on top
of existing components rather than starting from scratch.

EXAMPLE - Building a custom button:

❌ DON'T:
Box(Modifier.pointerInput(Unit) {
    detectTapGestures { /* custom logic */ }
}) {
    // Custom button UI
}

❌ BETTER, BUT STILL NOT IDEAL:
Box(Modifier.clickable { /* custom logic */ }) {
    // Custom button UI
}

✅ BEST:
Button(
    onClick = { /* custom logic */ },
    // Customize appearance with parameters
    colors = ButtonDefaults.buttonColors(...),
    shape = RoundedCornerShape(...)
) {
    // Custom button content
}

By using Button, you automatically get accessibility, focus, keyboard support,
and all the built-in best practices!

═══════════════════════════════════════════════════════════════════════════════
LEARN MORE
═══════════════════════════════════════════════════════════════════════════════

To learn more about accessibility in Compose, see:
- Accessibility in Compose documentation
- Semantic properties documentation
- Material 3 components documentation
*/

