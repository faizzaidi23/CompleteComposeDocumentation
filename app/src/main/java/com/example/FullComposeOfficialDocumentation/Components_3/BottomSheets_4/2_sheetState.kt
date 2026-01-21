package com.example.FullComposeOfficialDocumentation.Components_3.BottomSheets_4

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
═══════════════════════════════════════════════════════════════════════════════
                          SHEET STATE EXPLAINED
═══════════════════════════════════════════════════════════════════════════════

What exactly is sheetState?

val sheetState = rememberModalBottomSheetState()

This creates and remembers a STATE OBJECT that controls the bottom sheet's behavior.

THINK OF IT AS: The remote control for the bottom sheet

═══════════════════════════════════════════════════════════════════════════════
*/

/*
WHAT SHEETSTATE HOLDS:

1. Visibility State
   - Whether the sheet is visible or hidden
   - Current visibility: sheetState.isVisible

2. Animation Progress
   - How far along the show/hide animation is
   - Values between 0.0 (hidden) and 1.0 (fully visible)

3. Expansion State
   - Whether sheet is fully expanded or partially expanded
   - Depends on configuration

4. Gesture State
   - Current drag position
   - Swipe velocity
   - Whether user is currently dragging

FUNCTIONS IT PROVIDES:

- sheetState.show() - Show the sheet with animation
- sheetState.hide() - Hide the sheet with animation
- sheetState.expand() - Fully expand the sheet
- sheetState.partialExpand() - Partially expand (if configured)

All these are SUSPEND FUNCTIONS (must be called in coroutine)
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                    WHY NOT JUST USE showBottomSheet ALONE?
═══════════════════════════════════════════════════════════════════════════════

TWO STATE VARIABLES SERVE DIFFERENT PURPOSES:

┌──────────────────────┬────────────────────────────────────────────────┐
│ showBottomSheet      │ sheetState                                     │
├──────────────────────┼────────────────────────────────────────────────┤
│ Boolean (true/false) │ Complex state object                           │
├──────────────────────┼────────────────────────────────────────────────┤
│ Controls composition │ Controls animation & gestures                  │
├──────────────────────┼────────────────────────────────────────────────┤
│ "Should it exist?"   │ "How should it behave?"                        │
├──────────────────────┼────────────────────────────────────────────────┤
│ On/Off switch        │ Animation controller                           │
└──────────────────────┴────────────────────────────────────────────────┘

WHAT showBottomSheet DOES:
- Decides whether ModalBottomSheet composable exists in UI tree
- Simple boolean flag
- Does NOT control animation

WHAT sheetState DOES:
- Controls smooth animations
- Handles swipe gestures
- Tracks drag physics
- Manages transition states
- Provides show()/hide() functions

WITHOUT sheetState, the sheet would:
 Appear/disappear abruptly (no animation)
 Have no drag gestures (can't swipe to dismiss)
 No smooth transitions
 No Material Design behavior

WITH sheetState:
 Smooth slide-in/out animations
 User can swipe down to dismiss
 Proper Material Design motion
 Programmatic control over animations
*/

/*

                          MENTAL MODEL


REAL-WORLD ANALOGY:

Think of a physical garage door:

showBottomSheet = Whether the garage door EXISTS
sheetState = The motor and remote control that operates it

Without the door (showBottomSheet = false):
- No door to operate

With the door but no motor (no sheetState):
- Door exists but opens/closes instantly
- No smooth motion
- No remote control

With both:
- Door exists AND operates smoothly
- Can be controlled remotely
- Smooth opening/closing
- Can stop mid-way if needed

═══════════════════════════════════════════════════════════════════════════════
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetStateExplanationExample() {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Sheet State Properties:", style = MaterialTheme.typography.titleLarge)

        Button(onClick = { showBottomSheet = true }) {
            Text("Open Sheet")
        }

        if (showBottomSheet) {
            Text("showBottomSheet = $showBottomSheet")
            Text("sheetState.isVisible = ${sheetState.isVisible}")
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Sheet State Demo", style = MaterialTheme.typography.titleLarge)

                Text("Current State:")
                Text("• isVisible: ${sheetState.isVisible}")

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Text("Try swiping down to dismiss!")
                Text("Notice the smooth animation - that's sheetState working")
            }
        }
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                    COMPARISON: WITH vs WITHOUT sheetState
═══════════════════════════════════════════════════════════════════════════════

APPROACH 1: Without sheetState (WRONG - Won't compile)
─────────────────────────────────────────────────────────

var showBottomSheet by remember { mutableStateOf(false) }

if (showBottomSheet) {
    ModalBottomSheet(
        onDismissRequest = { showBottomSheet = false }
        //  ERROR: Missing required parameter 'sheetState'
    ) {
        // Content
    }
}

WHY THIS FAILS:
- ModalBottomSheet REQUIRES sheetState parameter
- Without it, Compose can't handle animations and gestures
- The composable needs internal state management


APPROACH 2: With sheetState (CORRECT)
──────────────────────────────────────────

val sheetState = rememberModalBottomSheetState()
var showBottomSheet by remember { mutableStateOf(false) }

if (showBottomSheet) {
    ModalBottomSheet(
        onDismissRequest = { showBottomSheet = false },
        sheetState = sheetState  //  Provides animation & gesture control
    ) {
        // Content
    }
}

WHY THIS WORKS:
- sheetState handles all animation logic
- User can swipe to dismiss
- Smooth transitions
- Proper Material behavior

═══════════════════════════════════════════════════════════════════════════════
*/

/*
KEY CONCEPT: TWO-LAYER STATE MANAGEMENT

Layer 1: Composition State (showBottomSheet)
┌────────────────────────────────────────────┐
│ if (showBottomSheet) {                     │
│     ModalBottomSheet(...)                  │
│ }                                          │
└────────────────────────────────────────────┘
Controls: Whether composable exists in UI tree

Layer 2: Animation State (sheetState)
┌────────────────────────────────────────────┐
│ sheetState = rememberModalBottomSheetState()│
│ • Tracks animation progress               │
│ • Handles gestures                         │
│ • Manages transitions                      │
└────────────────────────────────────────────┘
Controls: How composable behaves when it exists

BOTH ARE NECESSARY:
- showBottomSheet: Lifecycle management
- sheetState: Behavior & animation management

WORKFLOW:
1. showBottomSheet = true → Composable enters composition
2. sheetState.show() → Animates sheet sliding up
3. User swipes down → sheetState detects gesture
4. sheetState.hide() → Animates sheet sliding down
5. showBottomSheet = false → Composable leaves composition
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          REMEMBER PATTERN
═══════════════════════════════════════════════════════════════════════════════

val sheetState = rememberModalBottomSheetState()

WHY "remember"?

- Creates state ONCE and remembers it across recompositions
- Without remember: New state created every recomposition (loses animation)
- With remember: Same state instance persists

WHAT GETS REMEMBERED:
- Current visibility state
- Animation progress
- Gesture tracking data
- Expansion state

LIFECYCLE:
- Created: First composition
- Persists: Through recompositions
- Destroyed: When composable leaves composition permanently

═══════════════════════════════════════════════════════════════════════════════
*/

/*
SUMMARY:

sheetState is the ANIMATION + GESTURE + VISIBILITY CONTROLLER

It's NOT optional - it's REQUIRED for ModalBottomSheet to work properly

It provides:
 Smooth animations
 Swipe gestures
 Programmatic control (show/hide)
 Material Design behavior

Without it:
 No compilation (required parameter)
 No animations
 No gestures
 Poor user experience
*/

