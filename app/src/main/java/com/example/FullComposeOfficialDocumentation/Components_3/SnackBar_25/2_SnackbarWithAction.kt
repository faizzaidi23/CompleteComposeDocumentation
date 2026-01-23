package com.example.FullComposeOfficialDocumentation.Components_3.SnackBar_25

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/*
Snackbar with Action Button demonstrates how to add an interactive action
to the snackbar and handle user responses.

Key concepts:

1. actionLabel: Text for the action button (e.g., "Undo", "Retry", "Dismiss")
   - Appears on the right side of the snackbar
   - Optional parameter

2. SnackbarResult: Return value from showSnackbar() indicating what happened
   - SnackbarResult.ActionPerformed: User clicked the action button
   - SnackbarResult.Dismissed: Snackbar dismissed without action
     * Auto-dismissed after timeout
     * User swiped it away
     * Another snackbar appeared

3. when (result): Handle different outcomes
   - Execute specific logic based on user interaction
   - Example: Restore deleted item on "Undo" click

Common patterns:
- Delete with Undo: "Email deleted" with "Undo" action
- Network retry: "Connection failed" with "Retry" action
- Navigation: "Settings updated" with "View" action
*/

@Composable
fun SnackbarWithAction() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Delete item") },
                icon = { Icon(Icons.Filled.Delete, contentDescription = null) },
                onClick = {
                    scope.launch {
                        // Show snackbar with action button and capture result
                        val result = snackbarHostState.showSnackbar(
                            message = "Item deleted",
                            actionLabel = "Undo"
                        )

                        // Handle the result
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                // User clicked "Undo" button
                                // Restore the deleted item here
                                println("Undo action performed")
                            }
                            SnackbarResult.Dismissed -> {
                                // Snackbar was dismissed without action
                                // Confirm deletion is permanent
                                println("Snackbar dismissed")
                            }
                        }
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Click delete to see snackbar with action")
        }
    }
}

