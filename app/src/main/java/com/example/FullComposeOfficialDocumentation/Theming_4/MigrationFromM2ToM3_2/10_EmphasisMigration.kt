package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Emphasis and Content Alpha Migration from M2 to M3
 *
 * M2 used ContentAlpha with alpha values for emphasis:
 * - ContentAlpha.high
 * - ContentAlpha.medium
 * - ContentAlpha.disabled
 *
 * M3 uses different approaches:
 * 1. OnColor variants (onSurface, onSurfaceVariant)
 * 2. Different font weights for text
 * 3. Alpha only for disabled states
 *
 * M2 -> M3 mapping:
 * - onSurface with ContentAlpha.high -> onSurface (FontWeight.Medium - Black)
 * - onSurface with ContentAlpha.medium -> onSurfaceVariant (FontWeight.Thin - Normal)
 * - onSurface with ContentAlpha.disabled -> onSurface.copy(alpha = 0.38f)
 */

// M2 approach (old):
// CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
//     Icon(...)
// }

// M3 approach (new):
@Composable
fun EmphasisMigrationExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Emphasis Migration",
            style = MaterialTheme.typography.headlineSmall
        )

        // Icon emphasis in M3
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Icon Emphasis:")

            // High emphasis - use onSurface
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "High emphasis",
                tint = MaterialTheme.colorScheme.onSurface
            )

            // Medium emphasis - use onSurfaceVariant
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Medium emphasis",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Disabled emphasis - use alpha
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Disabled",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        }

        // Text emphasis in M3
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Text Emphasis:")

            // High emphasis - bold weight + onSurface
            Text(
                text = "High emphasis text",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Medium emphasis - normal weight + onSurfaceVariant
            Text(
                text = "Medium emphasis text",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Disabled - alpha
            Text(
                text = "Disabled text",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        }
    }
}

