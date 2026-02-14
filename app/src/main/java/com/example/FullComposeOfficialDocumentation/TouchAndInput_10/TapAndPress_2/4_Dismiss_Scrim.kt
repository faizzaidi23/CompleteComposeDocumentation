package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.TapAndPress_2

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import com.example.FullComposeOfficialDocumentation.R

/*
DISMISS A COMPOSABLE BY TAPPING A SCRIM

In some cases, clickable and combinedClickable add behavior that is not desirable.
For example, when implementing a scrim (semi-transparent background) that dismisses
a dialog or sheet when tapped.

═══════════════════════════════════════════════════════════════════════════════
THE PROBLEM WITH CLICKABLE FOR SCRIMS
═══════════════════════════════════════════════════════════════════════════════

A scrim should:
✗ NOT show visual indication (ripple) on tap
✗ NOT respond to hovering
✗ NOT be focusable with keyboard/joystick
✗ NOT participate in tab navigation
✓ SHOULD handle accessibility differently
✓ SHOULD respond to Escape key
✓ SHOULD be tappable to dismiss

The clickable modifier provides behaviors we DON'T want for a scrim!

SOLUTION:
Drop down to a lower abstraction level and use the pointerInput modifier
with detectTapGestures method. Then manually add the behaviors we DO want.

═══════════════════════════════════════════════════════════════════════════════
SCRIM IMPLEMENTATION
═══════════════════════════════════════════════════════════════════════════════

A scrim is a semi-transparent background that:
1. Appears behind dialogs, bottom sheets, or full-screen overlays
2. Dims the content underneath
3. Can be tapped to dismiss the overlay
4. Should not have ripple effects or hover states
*/

@Composable
fun Scrim(onClose: () -> Unit, modifier: Modifier = Modifier) {
    /*
    Get the localized "Close" string for accessibility
    */
    val strClose = stringResource(R.string.close)

    Box(
        modifier
            /*
            1. HANDLE POINTER INPUT (Touch/Mouse taps)
            Use pointerInput instead of clickable to avoid unwanted behavior
            Pass onClose as key to re-execute lambda if callback changes
            */
            .pointerInput(onClose) {
                detectTapGestures {
                    onClose()
                }
            }

            /*
            2. HANDLE ACCESSIBILITY SERVICES
            Manually set semantics since we're not using clickable
            This ensures screen readers can interact with the scrim
            */
            .semantics(mergeDescendants = true) {
                /*
                Describe what this element is to screen readers
                TalkBack will announce: "Close, Double tap to activate"
                */
                contentDescription = strClose

                /*
                Define the action that happens on click
                Return true to indicate the action was handled
                */
                onClick {
                    onClose()
                    true
                }
            }

            /*
            3. HANDLE PHYSICAL KEYBOARD INPUT
            Allow users to press Escape key to close
            */
            .onKeyEvent {
                if (it.key == Key.Escape) {
                    onClose()
                    true  // Event was handled
                } else {
                    false  // Event not handled, pass to next handler
                }
            }

            /*
            4. DRAW THE SCRIM
            Semi-transparent dark gray background
            */
            .background(Color.DarkGray.copy(alpha = 0.75f))
    )
}

/*
HOW IT WORKS:

POINTER INPUT (Touch/Mouse):
- Uses detectTapGestures to detect taps
- Calls onClose() when tapped
- No ripple effect (unlike clickable)
- No hover indication

ACCESSIBILITY:
- Sets contentDescription so screen readers announce "Close"
- Sets onClick action so TalkBack users can double-tap to activate
- Merges descendants so child content doesn't interfere

KEYBOARD:
- onKeyEvent listens for key presses
- Escape key triggers onClose()
- Returns true if handled, false otherwise

VISUAL:
- Semi-transparent dark gray (75% opacity)
- No ripple or indication on interaction

═══════════════════════════════════════════════════════════════════════════════
WHY NOT USE CLICKABLE?
═══════════════════════════════════════════════════════════════════════════════

IF WE USED CLICKABLE:
Box(
    Modifier
        .clickable { onClose() }
        .background(Color.DarkGray.copy(alpha = 0.75f))
)

PROBLEMS:
✗ Ripple effect appears when tapped (looks wrong for scrim)
✗ Hover effect on mouse-over (not desired for backdrop)
✗ Element is focusable (can tab to it, not desired)
✗ No Escape key support by default
✗ Visual indication doesn't match design intent

WITH POINTERINPUT:
✓ No ripple effect (clean dismiss)
✓ No hover effects
✓ Not focusable via tab navigation
✓ Custom keyboard handling (Escape key)
✓ Full control over behavior

═══════════════════════════════════════════════════════════════════════════════
POINTERINPUT KEY PARAMETER
═══════════════════════════════════════════════════════════════════════════════

IMPORTANT: Pass onClose as key to pointerInput

WHY?
The key parameter tells Compose when to re-execute the pointerInput lambda.
If onClose callback changes, we need to restart the gesture detection with
the new callback.

WRONG:
.pointerInput(Unit) {
    detectTapGestures { onClose() }  // Uses stale onClose!
}

RIGHT:
.pointerInput(onClose) {
    detectTapGestures { onClose() }  // Always uses current onClose!
}

Without the key, if the onClose callback changes (for example, it captures
different state), the old callback would still be used!

═══════════════════════════════════════════════════════════════════════════════
MANUAL ACCESSIBILITY IMPLEMENTATION
═══════════════════════════════════════════════════════════════════════════════

When you don't use clickable, you must manually provide accessibility support:

1. SET CONTENT DESCRIPTION
   contentDescription = "Close"
   Tells screen readers what this element is

2. DEFINE ONCLICK ACTION
   onClick { onClose(); true }
   Tells screen readers what happens on double-tap

3. MERGE DESCENDANTS (optional)
   semantics(mergeDescendants = true) { ... }
   Combines child semantics into parent

ACCESSIBILITY RESULT:
- TalkBack announces: "Close, Double tap to activate"
- User can double-tap anywhere on scrim to dismiss
- Screen reader users have same experience as visual users

═══════════════════════════════════════════════════════════════════════════════
KEYBOARD HANDLING
═══════════════════════════════════════════════════════════════════════════════

onKeyEvent modifier allows responding to keyboard input:

.onKeyEvent { keyEvent ->
    when (keyEvent.key) {
        Key.Escape -> {
            onClose()
            true  // Consumed the event
        }
        else -> false  // Didn't handle, pass to next handler
    }
}

RETURN VALUES:
- true: Event was handled, stop propagation
- false: Event not handled, pass to parent

COMMON KEYS:
- Key.Escape: Close dialogs/overlays
- Key.Enter: Confirm/submit
- Key.Backspace: Delete/go back
- Key.DirectionUp/Down/Left/Right: Navigate

═══════════════════════════════════════════════════════════════════════════════
USAGE EXAMPLE
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun FullScreenImageWithScrim(onDismiss: () -> Unit) {
    Box {
        /*
        Scrim fills entire screen behind the image
        */
        Scrim(
            onClose = onDismiss,
            modifier = Modifier.fillMaxSize()
        )

        /*
        Image appears on top of scrim
        */
        ImageContent()
    }
}

/*
USER EXPERIENCE:
- User sees image on semi-transparent dark background
- Tapping the dark background dismisses the image
- No ripple effect on tap (clean, minimal)
- Pressing Escape key also dismisses
- Screen reader announces "Close, Double tap to activate"

═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE POINTERINPUT INSTEAD OF CLICKABLE
═══════════════════════════════════════════════════════════════════════════════

USE POINTERINPUT WHEN:
✓ You need to avoid ripple/indication effects (scrims, overlays)
✓ You need to avoid hover effects
✓ Element should not be focusable
✓ You need custom keyboard handling
✓ You need tap position (covered in next section)
✓ You're building custom gesture behavior

USE CLICKABLE WHEN:
✓ You want standard button/interactive behavior
✓ You need ripple effects
✓ You want focus and keyboard support automatically
✓ You want hover effects
✓ Standard interactive element (button, list item, card)

═══════════════════════════════════════════════════════════════════════════════
BEST PRACTICES FOR SCRIMS
═══════════════════════════════════════════════════════════════════════════════

1. ALWAYS PROVIDE ACCESSIBILITY
   Don't forget semantics when using pointerInput

2. SUPPORT KEYBOARD DISMISS
   Add Escape key handling for better UX

3. USE APPROPRIATE OPACITY
   0.5 to 0.8 alpha is typical for scrims

4. PASS CALLBACK AS KEY
   .pointerInput(onClose) ensures correct callback is used

5. CONSIDER BACK BUTTON
   On Android, handle back button to dismiss as well

6. TEST WITH SCREEN READERS
   Verify TalkBack/accessibility services work correctly

7. DON'T BLOCK UNDERLYING CONTENT
   Scrim should be at appropriate z-level
*/

// Placeholder composables
@Composable
fun ImageContent() {
    Text("Image content")
}

@Composable
fun Modifier.fillMaxSize(): Modifier = this

