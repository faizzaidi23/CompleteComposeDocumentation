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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
Snackbar Duration controls how long the snackbar stays visible on screen.

SnackbarDuration options:

1. SnackbarDuration.Short (Default)
   - Displays for ~4 seconds
   - Best for simple confirmations
   - Example: "Message sent"

2. SnackbarDuration.Long
   - Displays for ~10 seconds
   - Use for messages that need more reading time
   - Example: "Your account settings have been updated successfully"

3. SnackbarDuration.Indefinite
   - Stays visible until user dismisses it or action is clicked
   - Use for critical messages requiring user response
   - Examples:
     * "No internet connection" (remains until connection restored)
     * "Error uploading file" (user must choose retry or cancel)
     * Important warnings requiring acknowledgment

When to use each:
- Short: Quick confirmations, simple messages
- Long: Longer messages, multiple pieces of information
- Indefinite: Errors, warnings, messages requiring action
*/

@Composable
fun SnackbarWithDuration() {
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
                // Short duration snackbar
                Button(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Short duration (4 seconds)",
                            duration = SnackbarDuration.Short
                        )
                    }
                }) {
                    Text("Show Short Snackbar")
                }

                // Long duration snackbar
                Button(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Long duration (10 seconds)",
                            duration = SnackbarDuration.Long
                        )
                    }
                }) {
                    Text("Show Long Snackbar")
                }

                // Indefinite duration snackbar with action
                Button(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Indefinite - stays until dismissed",
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                }) {
                    Text("Show Indefinite Snackbar")
                }
            }
        }
    }
}

