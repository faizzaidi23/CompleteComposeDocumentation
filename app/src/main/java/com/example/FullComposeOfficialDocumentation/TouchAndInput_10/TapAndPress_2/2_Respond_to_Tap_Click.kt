package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.TapAndPress_2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
RESPOND TO TAP OR CLICK

The clickable modifier is a commonly used modifier that makes a composable react
to taps or clicks. This modifier adds additional features beyond just click detection.

═══════════════════════════════════════════════════════════════════════════════
WHAT IS THE CLICKABLE MODIFIER?
═══════════════════════════════════════════════════════════════════════════════

The clickable modifier responds to "clicks" in the widest sense of the word:
- Mouse clicks
- Finger taps on touchscreen
- Stylus taps
- Keyboard input (Enter key)
- Accessibility service actions (double-tap)

It's not just a gesture detector - it's a complete interaction handler.

═══════════════════════════════════════════════════════════════════════════════
ADDITIONAL FEATURES OF CLICKABLE
═══════════════════════════════════════════════════════════════════════════════

The clickable modifier adds these behaviors automatically:

1. VISUAL INDICATION
   - Draws a ripple effect by default when tapped
   - Can be customized with interactionSource and indication parameters
   - Learn more on the "Handling user interactions" page

2. ACCESSIBILITY SUPPORT
   - Sets semantic information so accessibility services can interact
   - Announces element as clickable/tappable
   - Enables double-tap gesture for TalkBack users

3. KEYBOARD & FOCUS SUPPORT
   - Element can receive focus when navigating with keyboard/joystick
   - Pressing Enter or d-pad center triggers the click
   - Supports tab navigation

4. HOVERING SUPPORT
   - Responds to mouse or stylus hovering over the element
   - Can show hover state (visual feedback)
   - Important for desktop and tablet experiences

5. CONTEXT MENU SUPPORT
   - Long-press can show contextual menu (on supported platforms)
   - Platform-appropriate behavior

═══════════════════════════════════════════════════════════════════════════════
BASIC EXAMPLE - CLICKABLE SURFACE
═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun ClickableSurfaceExample() {
    /*
    Many composables have built-in onClick support
    */
    Surface(
        onClick = {
            /* handle click */
            println("Surface clicked!")
        }
    ) {
        Text(
            "Click me!",
            Modifier.padding(24.dp)
        )
    }
}

/*
This Surface automatically includes:
- Ripple effect on click
- Accessibility support
- Keyboard support (Enter to click)
- Focus handling
- Hover effects

═══════════════════════════════════════════════════════════════════════════════
PRACTICAL EXAMPLE - IMAGE GRID WITH CLICK HANDLING
═══════════════════════════════════════════════════════════════════════════════

Imagine a grid of images, where an image shows full-screen when a user clicks on it.
*/

data class Photo(val id: Int, val url: String, val highResUrl: String)

@Composable
fun ImageGrid(photos: List<Photo>) {
    /*
    Track which photo is currently shown full-screen
    Using rememberSaveable to survive configuration changes
    */
    var activePhotoId by rememberSaveable { mutableStateOf<Int?>(null) }

    /*
    Display photos in a grid
    */
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(photos, key = { it.id }) { photo ->
            ImageItem(
                photo,
                /*
                Add clickable modifier to each grid item
                When clicked, set this photo as the active one
                */
                Modifier.clickable {
                    activePhotoId = photo.id
                }
            )
        }
    }

    /*
    Show full-screen image if one is selected
    */
    if (activePhotoId != null) {
        FullScreenImage(
            photo = photos.first { it.id == activePhotoId },
            onDismiss = {
                /*
                Clear the active photo to dismiss full-screen view
                */
                activePhotoId = null
            }
        )
    }
}

/*
HOW IT WORKS:

1. INITIAL STATE
   - activePhotoId is null
   - Grid of thumbnail images is shown

2. USER CLICKS IMAGE
   - clickable modifier detects the tap
   - activePhotoId is set to the clicked photo's id
   - Recomposition occurs

3. SHOW FULL-SCREEN
   - activePhotoId is not null, so FullScreenImage is shown
   - Grid is still in the composition (behind the full-screen view)

4. USER DISMISSES
   - onDismiss callback sets activePhotoId back to null
   - FullScreenImage is removed from composition
   - Grid is visible again

═══════════════════════════════════════════════════════════════════════════════
WHY USE CLICKABLE INSTEAD OF POINTERINPUT?
═══════════════════════════════════════════════════════════════════════════════

CLICKABLE PROVIDES:
✓ Ripple effect automatically
✓ Accessibility support (TalkBack announces "Double tap to activate")
✓ Keyboard navigation (Tab to focus, Enter to click)
✓ Focus indicators
✓ Hover effects for mouse/stylus
✓ Platform-appropriate behavior

POINTERINPUT PROVIDES:
- Raw pointer events only
- You must add accessibility manually
- You must add keyboard support manually
- You must add focus handling manually
- You must add visual feedback manually

USE CLICKABLE WHEN:
- You just need to detect clicks/taps
- You want accessibility support
- You want platform-standard behavior
- You don't need tap position

USE POINTERINPUT WHEN:
- You need tap position (for zoom, etc.)
- You need custom visual behavior
- You're building a custom scrim/overlay
- You need very specific gesture handling

═══════════════════════════════════════════════════════════════════════════════
CLICKABLE PARAMETERS
═══════════════════════════════════════════════════════════════════════════════

Modifier.clickable(
    enabled: Boolean = true,              // Can be clicked?
    onClickLabel: String? = null,         // Accessibility label for action
    role: Role? = null,                   // Semantic role (Button, etc)
    onClick: () -> Unit                   // Click callback
)

ADVANCED PARAMETERS:
    interactionSource: MutableInteractionSource, // Track interaction state
    indication: Indication?                       // Visual feedback (ripple)

EXAMPLE WITH PARAMETERS:
*/

@Composable
fun ClickableWithParametersExample() {
    var enabled by remember { mutableStateOf(true) }

    Box(
        Modifier.clickable(
            enabled = enabled,
            onClickLabel = "Open photo gallery",
            role = androidx.compose.ui.semantics.Role.Button,
            onClick = {
                /* handle click */
                enabled = false  // Disable after first click
            }
        )
    ) {
        Text(if (enabled) "Click me" else "Clicked!")
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
BEST PRACTICES
═══════════════════════════════════════════════════════════════════════════════

1. USE CLICKABLE FOR STANDARD INTERACTIONS
   Prefer clickable over custom pointer handling when possible

2. PROVIDE CLICK LABELS FOR ACCESSIBILITY
   Use onClickLabel to help accessibility service users understand the action

3. SET APPROPRIATE ROLES
   Use role parameter to indicate semantic meaning (Button, Checkbox, etc)

4. REMEMBER STATE APPROPRIATELY
   Use rememberSaveable for UI state that should survive configuration changes

5. USE KEYS IN LISTS
   Always provide keys when using clickable in lazy lists (items(list, key = {...}))

6. CONSIDER ENABLED STATE
   Disable clickable elements when action is not available

═══════════════════════════════════════════════════════════════════════════════
COMMON PATTERNS
═══════════════════════════════════════════════════════════════════════════════

PATTERN 1: Toggle state on click
var expanded by remember { mutableStateOf(false) }
Box(Modifier.clickable { expanded = !expanded })

PATTERN 2: Navigate on click
val navController = rememberNavController()
Box(Modifier.clickable { navController.navigate("details") })

PATTERN 3: Show/hide content on click
var visible by remember { mutableStateOf(false) }
Box(Modifier.clickable { visible = true })
if (visible) { DetailContent() }

PATTERN 4: Conditional clickable
Box(
    Modifier.clickable(enabled = isValid) {
        submitForm()
    }
)
*/

// Placeholder composables
@Composable
fun ImageItem(photo: Photo, modifier: Modifier = Modifier) {
    Box(modifier) { Text("Image ${photo.id}") }
}

@Composable
fun FullScreenImage(photo: Photo, onDismiss: () -> Unit) {
    Box(Modifier.clickable { onDismiss() }) {
        Text("Full screen: ${photo.id}")
    }
}

