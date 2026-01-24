package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

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
 * Material Design 3 Elevation
 *
 * M3 uses two types of elevation:
 * 1. Tonal Elevation: Changes container color based on elevation level
 *    - Uses tonal color overlays from primary color
 *    - Higher elevation = more prominent tone
 *
 * 2. Shadow Elevation: Creates shadow depth
 *    - Traditional shadow under the component
 *
 * Both can be used together to create depth and hierarchy
 */

@Composable
fun ElevationExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Elevation Examples",
            style = MaterialTheme.typography.headlineSmall
        )

        // Level 0: No elevation
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Text(
                text = "Level 0 - No Elevation",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Level 1: Subtle elevation
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 1.dp,
            shadowElevation = 1.dp
        ) {
            Text(
                text = "Level 1 - Tonal: 1.dp, Shadow: 1.dp",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Level 3: Medium elevation
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 3.dp,
            shadowElevation = 3.dp
        ) {
            Text(
                text = "Level 3 - Tonal: 3.dp, Shadow: 3.dp",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Level 6: High elevation
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 6.dp,
            shadowElevation = 6.dp
        ) {
            Text(
                text = "Level 6 - Tonal: 6.dp, Shadow: 6.dp",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun TonalVsShadowElevation() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Only tonal elevation - color changes, no shadow
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Text(
                text = "Tonal Elevation Only (6.dp)\nNotice the tint color change",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Only shadow elevation - shadow appears, color stays same
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 0.dp,
            shadowElevation = 8.dp
        ) {
            Text(
                text = "Shadow Elevation Only (8.dp)\nNotice the shadow",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Both elevations combined
        Surface(
            modifier = Modifier.fillMaxWidth(),
            tonalElevation = 4.dp,
            shadowElevation = 8.dp
        ) {
            Text(
                text = "Both Elevations\nTonal: 4.dp, Shadow: 8.dp",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

