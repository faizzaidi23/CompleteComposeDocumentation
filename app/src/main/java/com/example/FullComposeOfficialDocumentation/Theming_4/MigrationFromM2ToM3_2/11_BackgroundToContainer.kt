package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Background to Container Migration
 *
 * M2 used "background" naming
 * M3 uses "container" naming
 *
 * Common replacements:
 * - backgroundColor -> containerColor
 * - backgroundContentColor -> contentColor
 * - Similar pattern across all components
 */

// M2 approach (old):
// Card(backgroundColor = MaterialTheme.colors.surface) { }
// Button(backgroundColor = MaterialTheme.colors.primary) { }

// M3 approach (new):
@Composable
fun BackgroundToContainerMigration() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Background → Container",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "M3 renames 'background' parameters to 'container'",
            style = MaterialTheme.typography.bodyMedium
        )

        // Card with container color
        Card(
            modifier = Modifier.fillMaxWidth(),
            // M2: backgroundColor = ...
            // M3: containerColor through colors parameter
        ) {
            Text(
                text = "Card uses containerColor (was backgroundColor)",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Button with container color
        Button(
            onClick = { },
            // M2: backgroundColor = ...
            // M3: containerColor through colors parameter
        ) {
            Text("Button containerColor")
        }

        Text(
            text = "Replace background* with container* when migrating",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

