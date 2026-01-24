package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Material Design 3 Components
 *
 * M3 provides components with different emphasis levels:
 * - FAB/Extended FAB: Highest emphasis action
 * - Filled Button: High emphasis
 * - Filled Tonal Button: Medium emphasis
 * - Outlined Button: Medium-low emphasis
 * - Text Button: Low emphasis
 */

@Composable
fun ComponentEmphasisExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Button Emphasis Hierarchy",
            style = MaterialTheme.typography.headlineSmall
        )

        // Highest emphasis - Extended FAB
        ExtendedFloatingActionButton(
            onClick = { },
            icon = { Icon(Icons.Default.Add, contentDescription = null) },
            text = { Text("Highest Emphasis") }
        )

        // High emphasis - Filled Button
        Button(onClick = { }) {
            Text("High Emphasis")
        }

        // Medium emphasis - Filled Tonal Button
        FilledTonalButton(onClick = { }) {
            Text("Medium Emphasis")
        }

        // Medium-low emphasis - Outlined Button
        OutlinedButton(onClick = { }) {
            Text("Medium-Low Emphasis")
        }

        // Low emphasis - Text Button
        TextButton(onClick = { }) {
            Text("Low Emphasis")
        }
    }
}

