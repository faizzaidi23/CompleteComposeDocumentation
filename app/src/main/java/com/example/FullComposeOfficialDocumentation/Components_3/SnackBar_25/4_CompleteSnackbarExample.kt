package com.example.FullComposeOfficialDocumentation.Components_3.SnackBar_25

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
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
import kotlinx.coroutines.launch

/*
Complete Snackbar Example demonstrating all key features together:
- Custom message
- Action button with label
- Custom duration
- Result handling

This example simulates a real-world scenario:
User submits a form, sees confirmation with option to view the submitted data.

Key parameters combined:

1. message: The main text displayed in the snackbar
   - Keep it concise (1-2 lines)
   - Clear and informative

2. actionLabel: Text for the action button
   - Use action verbs (Undo, Retry, View, Dismiss)
   - Should be short (one word is best)

3. duration: How long the snackbar shows
   - Short: Quick feedback
   - Long: More complex messages
   - Indefinite: Requires user action

4. Result handling: Different responses based on user action
   - ActionPerformed: Execute the action
   - Dismissed: Clean up or log

Real-world example workflow:
1. User clicks "Submit Form"
2. Snackbar shows "Form submitted successfully"
3. User can click "View" to see submission
4. Or let it auto-dismiss after timeout
*/

@Composable
fun CompleteSnackbarExample() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Submit Form") },
                icon = { Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null) },
                onClick = {
                    scope.launch {
                        // Show snackbar with all options
                        val result = snackbarHostState.showSnackbar(
                            message = "Form submitted successfully",
                            actionLabel = "View",
                            duration = SnackbarDuration.Long
                        )

                        // Handle user response
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                // User clicked "View" button
                                // Navigate to submission details or show dialog
                                println("Navigating to form details...")
                            }
                            SnackbarResult.Dismissed -> {
                                // User dismissed or timeout occurred
                                // Form submitted, no further action
                                println("Form submission acknowledged")
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
            Text("Click submit to see complete snackbar example")
        }
    }
}
