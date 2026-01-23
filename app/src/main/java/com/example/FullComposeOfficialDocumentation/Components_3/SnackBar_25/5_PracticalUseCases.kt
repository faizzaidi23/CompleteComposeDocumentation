package com.example.FullComposeOfficialDocumentation.Components_3.SnackBar_25

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
Practical Snackbar Use Cases demonstrating real-world scenarios.

This example shows three common patterns you'll encounter in production apps:

1. Action Confirmation (Delete with Undo)
   - User deletes an item
   - Snackbar confirms deletion
   - "Undo" button allows reversal
   - Common in email apps, todo lists, file managers

2. Network Status (Offline Mode)
   - App detects lost connection
   - Snackbar stays visible (Indefinite duration)
   - "Retry" button attempts reconnection
   - Dismisses when connection restored

3. Success Notification (Settings Saved)
   - User saves preferences
   - Brief confirmation appears
   - Auto-dismisses (Short duration)
   - No action needed from user

These patterns cover most snackbar use cases:
- Undo actions: Indefinite with action
- Error messages: Indefinite with retry
- Success messages: Short without action
- Informational: Long duration
*/

@Composable
fun PracticalSnackbarUseCases() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Use Case 1: Delete with Undo
                Button(onClick = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Email deleted",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Long
                        )

                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                // Restore deleted email
                                println("Email restored")
                            }
                            SnackbarResult.Dismissed -> {
                                // Permanently delete email
                                println("Email permanently deleted")
                            }
                        }
                    }
                }) {
                    Text("Delete Email (with Undo)")
                }

                // Use Case 2: Network Error with Retry
                Button(onClick = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "No internet connection",
                            actionLabel = "Retry",
                            duration = SnackbarDuration.Indefinite
                        )

                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                // Attempt to reconnect
                                println("Retrying connection...")
                            }
                            SnackbarResult.Dismissed -> {
                                // User dismissed, continue offline
                                println("Continue in offline mode")
                            }
                        }
                    }
                }) {
                    Text("Simulate Network Error")
                }

                // Use Case 3: Success Notification
                Button(onClick = {
                    scope.launch {
                        // No action needed, just confirmation
                        snackbarHostState.showSnackbar(
                            message = "Settings saved successfully",
                            duration = SnackbarDuration.Short
                        )
                        // Result ignored for simple confirmations
                    }
                }) {
                    Text("Save Settings")
                }
            }
        }
    }
}
