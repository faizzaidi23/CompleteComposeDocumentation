package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
Window-like Components:

Window-like custom components, similar to ModalBottomSheet, need additional signals to
differentiate them from surrounding content.

For this, you can use paneTitle semantics, so that any relevant window or pane changes may be
represented appropriately by the accessibility services, along with its main semantic information.
*/

@Composable
fun ShareSheet(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp)
            .semantics { paneTitle = "New bottom sheet" }
    ) {
        Text(
            text = message,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

/*
Benefits of paneTitle:

- Helps accessibility services identify and announce new windows or panes
- Provides context when the UI changes to show a new layer
- Differentiates the new content from the underlying content
*/

@Composable
fun ShareSheetExample() {
    ShareSheet(
        message = "Choose how to share this photo",
        modifier = Modifier
            .fillMaxWidth()
            .semantics { paneTitle = "Share options" }
    )
}

/*
When to use paneTitle:

- Bottom sheets
- Dialog-like components
- Modal overlays
- Any component that acts as a separate "window" or "pane" over existing content

For reference, Material 3 uses paneTitle for its ModalBottomSheet and other window-like components.
*/

