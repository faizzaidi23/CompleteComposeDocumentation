package com.example.FullComposeOfficialDocumentation.TouchAndInput_10.TapAndPress_2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.FullComposeOfficialDocumentation.R

/*
COMBINED CLICKABLE - MULTIPLE GESTURE TYPES

The combinedClickable modifier lets you add double tap or long-press behavior
in addition to normal click behavior.

═══════════════════════════════════════════════════════════════════════════════
WHAT IS COMBINEDCLICKABLE?
═══════════════════════════════════════════════════════════════════════════════

combinedClickable extends the functionality of clickable by adding support for:
- onClick: Normal single tap/click
- onDoubleClick: Two quick taps in succession
- onLongClick: Press and hold for ~500ms

All the benefits of clickable are included:
✓ Accessibility support
✓ Keyboard navigation
✓ Focus handling
✓ Hover effects
✓ Ripple indication

═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE COMBINEDCLICKABLE
═══════════════════════════════════════════════════════════════════════════════

USE CASES:
- Context menus: Long-press to show additional actions
- Quick actions: Double-tap for secondary action
- Multi-modal interaction: Different actions for different gesture types

EXAMPLES:
- Long-press image to show context menu (share, delete, info)
- Double-tap message to react with emoji
- Long-press list item to enter selection mode
- Double-tap to zoom, single-tap to view details

═══════════════════════════════════════════════════════════════════════════════
EXAMPLE: CONTEXT MENU ON LONG-PRESS
═══════════════════════════════════════════════════════════════════════════════

Show a context menu when a user touches and holds a grid image.
*/

data class Phooto(val id: Int, val url: String, val highResUrl: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageGridWithContextMenu(photos: List<Photo>) {
    /*
    Track active photo for full-screen view
    */
    var activePhotoId by rememberSaveable { mutableStateOf<Int?>(null) }

    /*
    Track photo for context menu
    */
    var contextMenuPhotoId by rememberSaveable { mutableStateOf<Int?>(null) }

    /*
    Get haptic feedback system service
    Used to provide tactile feedback on long-press
    */
    val haptics = LocalHapticFeedback.current

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(photos, key = { it.id }) { photo ->
            ImageItem(
                photo,
                Modifier.combinedClickable(
                    /*
                    Normal click: Show full-screen image
                    */
                    onClick = {
                        activePhotoId = photo.id
                    },

                    /*
                    Long-press: Show context menu with actions
                    */
                    onLongClick = {
                        /*
                        BEST PRACTICE: Provide haptic feedback on long-press
                        This gives users tactile confirmation that the
                        long-press was registered
                        */
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)

                        contextMenuPhotoId = photo.id
                    },

                    /*
                    ACCESSIBILITY: Provide label for long-press action
                    This helps screen reader users understand what
                    long-press does
                    */
                    onLongClickLabel = stringResource(R.string.open_context_menu)
                )
            )
        }
    }

    /*
    Show full-screen image if selected
    */
    if (activePhotoId != null) {
        FullScreenImage(
            photo = photos.first { it.id == activePhotoId },
            onDismiss = { activePhotoId = null }
        )
    }

    /*
    Show context menu (bottom sheet) if triggered
    */
    if (contextMenuPhotoId != null) {
        PhotoActionsSheet(
            photo = photos.first { it.id == contextMenuPhotoId },
            onDismissSheet = { contextMenuPhotoId = null }
        )
    }
}

/*
HOW IT WORKS:

1. USER SINGLE-TAPS IMAGE
   - onClick is called
   - activePhotoId is set
   - Full-screen image is shown

2. USER LONG-PRESSES IMAGE
   - onLongClick is called
   - Haptic feedback is triggered (device vibrates briefly)
   - contextMenuPhotoId is set
   - Context menu sheet appears with actions (share, delete, info, etc.)

3. USER ACTS ON CONTEXT MENU
   - User selects an action or dismisses
   - onDismissSheet is called
   - contextMenuPhotoId is set to null
   - Context menu disappears

═══════════════════════════════════════════════════════════════════════════════
COMBINEDCLICKABLE PARAMETERS
═══════════════════════════════════════════════════════════════════════════════

Modifier.combinedClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onLongClickLabel: String? = null,     // NEW: Label for long-press
    onLongClick: (() -> Unit)? = null,    // NEW: Long-press callback
    onDoubleClick: (() -> Unit)? = null,  // NEW: Double-tap callback
    onClick: () -> Unit
)

ADVANCED PARAMETERS:
    interactionSource: MutableInteractionSource,
    indication: Indication?

═══════════════════════════════════════════════════════════════════════════════
HAPTIC FEEDBACK BEST PRACTICE
═══════════════════════════════════════════════════════════════════════════════

ALWAYS include haptic feedback when user long-presses elements.

WHY?
- Provides tactile confirmation that the action was registered
- Helps users understand the UI responded to their gesture
- Standard platform behavior users expect

HOW?
1. Get the haptic feedback service:
   val haptics = LocalHapticFeedback.current

2. Trigger feedback in onLongClick:
   haptics.performHapticFeedback(HapticFeedbackType.LongPress)

HAPTIC FEEDBACK TYPES:
- HapticFeedbackType.LongPress: For long-press gestures
- HapticFeedbackType.TextHandleMove: For text selection
- HapticFeedbackType.KeyboardPress: For virtual keyboard
- HapticFeedbackType.KeyboardRelease: For virtual keyboard

═══════════════════════════════════════════════════════════════════════════════
ACCESSIBILITY WITH COMBINEDCLICKABLE
═══════════════════════════════════════════════════════════════════════════════

IMPORTANT: Always provide onLongClickLabel

Without label:
TalkBack announces: "Double tap to activate. Actions available."

With label:
TalkBack announces: "Double tap to activate. Double tap and hold to open context menu."

The label tells users what the long-press action does!

EXAMPLE:
*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccessibleCombinedClickableExample() {
    Box(
        Modifier.combinedClickable(
            onClick = { /* open item */ },
            onLongClick = { /* show menu */ },
            onClickLabel = "Open item",
            onLongClickLabel = "Show actions menu"  // This is important!
        )
    )
}

/*
═══════════════════════════════════════════════════════════════════════════════
DOUBLE-TAP EXAMPLE
═══════════════════════════════════════════════════════════════════════════════

Use case: Double-tap to favorite/like, single-tap to open
*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DoubleTapToFavoriteExample() {
    var isFavorite by remember { mutableStateOf(false) }
    val haptics = LocalHapticFeedback.current

    Box(
        Modifier.combinedClickable(
            onClick = {
                /* Single tap: Open details */
                println("Opening details...")
            },
            onDoubleClick = {
                /* Double tap: Toggle favorite */
                isFavorite = !isFavorite
                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
            }
        )
    ) {
        Text(if (isFavorite) "❤️ Favorited" else "🤍 Not favorited")
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
WHEN TO USE EACH GESTURE TYPE
═══════════════════════════════════════════════════════════════════════════════

SINGLE TAP (onClick):
- Primary action
- Most common/expected action
- Example: Open item, select, navigate

DOUBLE TAP (onDoubleClick):
- Quick secondary action
- Should be discoverable but not required
- Example: Like/favorite, zoom

LONG-PRESS (onLongClick):
- Show additional options
- Context menu
- Enter special mode (selection mode)
- Example: Show menu, start drag, show details

DESIGN GUIDELINE:
- Single tap should always be the primary action
- Double tap and long-press should be secondary/optional
- Don't rely solely on double-tap or long-press for critical actions
- Always provide alternative ways to access long-press actions

═══════════════════════════════════════════════════════════════════════════════
COMMON PATTERNS
═══════════════════════════════════════════════════════════════════════════════

PATTERN 1: Context Menu
Modifier.combinedClickable(
    onClick = { openItem() },
    onLongClick = {
        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
        showContextMenu()
    },
    onLongClickLabel = "Show menu"
)

PATTERN 2: Quick Favorite
Modifier.combinedClickable(
    onClick = { viewDetails() },
    onDoubleClick = { toggleFavorite() }
)

PATTERN 3: Selection Mode
Modifier.combinedClickable(
    onClick = { if (selectionMode) toggleSelection() else openItem() },
    onLongClick = {
        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
        enterSelectionMode()
    }
)

═══════════════════════════════════════════════════════════════════════════════
DEBUGGING TIP
═══════════════════════════════════════════════════════════════════════════════

If gestures aren't working as expected:
1. Check if enabled = true
2. Verify callbacks are set correctly
3. Check for conflicting gesture handlers in parent/child
4. Use Layout Inspector to verify clickable modifiers are applied
5. Test with different input methods (touch, mouse, keyboard)
*/

// Placeholder composables for the examples
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageeItem(photo: Photo, modifier: Modifier = Modifier) {
    Box(modifier) { Text("Image ${photo.id}") }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FulllScreenImage(photo: Photo, onDismiss: () -> Unit) {
    Box(Modifier.combinedClickable(onClick = onDismiss)) {
        Text("Full screen: ${photo.id}")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoActionsSheet(photo: Photo, onDismissSheet: () -> Unit) {
    Box(Modifier.combinedClickable(onClick = onDismissSheet)) {
        Text("Actions for photo ${photo.id}")
    }
}
