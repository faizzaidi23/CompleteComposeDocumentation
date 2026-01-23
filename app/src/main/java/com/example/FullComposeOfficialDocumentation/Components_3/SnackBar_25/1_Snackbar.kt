package com.example.FullComposeOfficialDocumentation.Components_3.SnackBar_25

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/*
Snackbar is a brief notification that appears at the bottom of the screen.
It provides feedback about an operation without interrupting the user experience.

Common use cases:
- Action confirmation (e.g., "Email deleted" with Undo)
- Network status (e.g., "You are offline")
- Data submission (e.g., "Settings saved successfully")

Key components:
1. SnackbarHost: Container that displays the snackbar
2. SnackbarHostState: Manages snackbar state and provides showSnackbar() function
3. CoroutineScope: Required to call the suspending showSnackbar() function

How it works:
- Snackbars disappear automatically after a few seconds
- Can be dismissed manually by user
- Only one snackbar shows at a time
*/

@Composable
fun BasicSnackbar() {
    // Create a CoroutineScope tied to this composable's lifecycle
    val scope = rememberCoroutineScope()

    // Create and remember the snackbar state
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        // SnackbarHost displays the snackbar at the bottom
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                onClick = {
                    // Launch a coroutine to show the snackbar
                    scope.launch {
                        // Display a simple snackbar with just a message
                        snackbarHostState.showSnackbar("This is a basic snackbar")
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
            Text("Click the button to show snackbar")
        }
    }
}
