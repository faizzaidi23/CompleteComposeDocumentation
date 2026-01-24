package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Button Migration from M2 to M3
 *
 * M2 and M3 share many button types:
 * - Button (filled)
 * - OutlinedButton
 * - TextButton
 * - IconButton
 * - FloatingActionButton
 * - ExtendedFloatingActionButton
 *
 * New in M3:
 * - FilledTonalButton (medium emphasis)
 * - ElevatedButton
 * - More FAB variants
 */

// M2 and M3 have similar APIs, but M3 has additional variants

@Composable
fun ButtonMigrationExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Button Migration",
            style = MaterialTheme.typography.headlineSmall
        )

        // Same in M2 and M3
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Filled Button (same in M2 & M3)")
        }

        // Same in M2 and M3
        OutlinedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Outlined Button (same in M2 & M3)")
        }

        // Same in M2 and M3
        TextButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Text Button (same in M2 & M3)")
        }

        // New in M3 - Filled Tonal Button
        FilledTonalButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Filled Tonal Button (New in M3)")
        }

        // Icon Button - same in M2 and M3
        IconButton(onClick = { }) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }

        // FAB - same in M2 and M3
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }

        // Extended FAB - same in M2 and M3
        ExtendedFloatingActionButton(
            onClick = { },
            icon = { Icon(Icons.Default.Add, contentDescription = null) },
            text = { Text("Extended FAB") }
        )
    }
}

