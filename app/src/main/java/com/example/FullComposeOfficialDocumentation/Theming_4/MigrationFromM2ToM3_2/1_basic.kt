package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Material 2 to Material 3 Migration - Basic Overview
 *
 * M3 is the evolution of M2 with updated theming, components, and Material You features
 *
 * Key differences:
 * - Separate package: androidx.compose.material3
 * - Updated color system (5 key colors vs 4 in M2)
 * - Revised typography scale
 * - Expanded shape system
 * - New components and variations
 *
 * Migration approach:
 * 1. Add M3 dependency alongside M2
 * 2. Create M3 theme alongside M2 theme
 * 3. Migrate modules/screens gradually
 * 4. Remove M2 theme and dependency
 */

// M2 approach (old)
// import androidx.compose.material.MaterialTheme
// MaterialTheme(colors = ..., typography = ..., shapes = ...) { }

// M3 approach (new)
@Composable
fun M3BasicExample() {
    // MaterialTheme now uses M3 with different parameters
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,  // Was 'colors' in M2
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Material 3 Migration",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "This uses M3 components and theming",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
