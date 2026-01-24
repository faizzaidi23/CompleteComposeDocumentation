package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Chip Migration from M2 to M3
 *
 * M2 had a single Chip component
 * M3 has multiple specific chip variants:
 * - AssistChip: Represents smart actions
 * - FilterChip: Filters content
 * - InputChip: User-generated content
 * - SuggestionChip: Helps narrow user's intent
 *
 * Choose the appropriate variant based on use case
 */

// M2 approach (old):
// Chip(onClick = { }) {
//     Text("Chip")
// }

// M3 approach (new):
@Composable
fun ChipMigrationExample() {
    var filterSelected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Chip Migration",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "M3 has specific chip types instead of generic Chip",
            style = MaterialTheme.typography.bodyMedium
        )

        // AssistChip - for actions
        AssistChip(
            onClick = { },
            label = { Text("Assist Chip") },
            modifier = Modifier.fillMaxWidth()
        )

        // FilterChip - for filtering
        FilterChip(
            selected = filterSelected,
            onClick = { filterSelected = !filterSelected },
            label = { Text("Filter Chip") },
            modifier = Modifier.fillMaxWidth()
        )

        // SuggestionChip - for suggestions
        SuggestionChip(
            onClick = { },
            label = { Text("Suggestion Chip") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Replace M2 Chip with appropriate M3 chip variant",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

