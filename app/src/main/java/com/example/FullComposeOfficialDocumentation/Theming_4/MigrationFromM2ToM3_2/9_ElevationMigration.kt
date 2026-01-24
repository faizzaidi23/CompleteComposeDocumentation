package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Elevation Migration from M2 to M3
 *
 * M3 introduces two types of elevation:
 * 1. Shadow Elevation: Same as M2 elevation (casts shadow)
 * 2. Tonal Elevation: New in M3 (color overlay based on elevation)
 *
 * M2: elevation parameter only
 * M3: shadowElevation and tonalElevation parameters
 *
 * Tonal elevation replaces elevation overlays from M2 dark theme
 */

// M2 approach (old):
// Surface(elevation = 4.dp) { ... }

// M3 approach (new):
@Composable
fun ElevationMigrationExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Elevation Migration",
            style = MaterialTheme.typography.headlineSmall
        )

        // M2 style - only shadow (set tonalElevation to 0.dp)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 4.dp,
            tonalElevation = 0.dp
        ) {
            Text(
                text = "Shadow Only (M2 style)",
                modifier = Modifier.padding(16.dp)
            )
        }

        // M3 style - both shadow and tonal
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shadowElevation = 4.dp,
            tonalElevation = 4.dp  // Adds color tint
        ) {
            Text(
                text = "Shadow + Tonal (M3 style)",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Card example
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp  // Applies both shadow and tonal
            )
        ) {
            Text(
                text = "Card with M3 elevation",
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(
            text = "Note: Tonal elevation uses primary color by default. " +
                    "Override with surfaceTint in ColorScheme.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

