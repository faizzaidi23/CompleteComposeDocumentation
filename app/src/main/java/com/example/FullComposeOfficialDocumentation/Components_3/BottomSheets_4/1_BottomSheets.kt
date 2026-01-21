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
                          BOTTOM SHEETS - OVERVIEW
═══════════════════════════════════════════════════════════════════════════════

Bottom sheets are surfaces containing supplementary content anchored to the bottom of the screen

USE CASES:
- Additional information or options
- Forms and input fields
- Action menus
- Filtering options
- Detailed content preview

TYPES:
1. Modal Bottom Sheet - Blocks interaction with rest of screen
2. Standard Bottom Sheet - Allows interaction with background (not covered here)

═══════════════════════════════════════════════════════════════════════════════
                              KEY COMPONENTS
═══════════════════════════════════════════════════════════════════════════════

To implement bottom sheets, use the ModalBottomSheet composable

IMPORTANT PARAMETERS:
1. sheetState: Controls animation, visibility, and gestures
2. onDismissRequest: Called when user dismisses sheet (swipe/tap outside)
3. content: Sheet content (uses ColumnScope - layouts in vertical column)


*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleModalBottomSheetExample() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Hide the bottom sheet")
                    }
                }
            }
        }
    }
}

/*
BASIC FLOW:

1. User clicks FAB
   → showBottomSheet = true

2. Bottom sheet appears with animation
   → Controlled by sheetState

3. User clicks "Hide" button
   → scope.launch { sheetState.hide() }
   → Animation runs
   → invokeOnCompletion sets showBottomSheet = false

4. Sheet disappears smoothly

See other files for deep dive into each concept!
*/
