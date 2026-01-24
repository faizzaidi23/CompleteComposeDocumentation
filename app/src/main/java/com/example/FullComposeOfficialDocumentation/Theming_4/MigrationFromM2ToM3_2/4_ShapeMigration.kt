package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

/**
 * Shape Migration from M2 to M3
 *
 * M2 had 3 shape parameters: small, medium, large
 * M3 has 5 shape parameters: extraSmall, small, medium, large, extraLarge
 *
 * M2 -> M3 mapping:
 * - small -> small
 * - medium -> medium
 * - large -> large
 * - (new) -> extraSmall
 * - (new) -> extraLarge
 *
 * Special shapes (constant, not in Shapes class):
 * - none -> RectangleShape
 * - full -> CircleShape
 */

// M2 approach (old):
// val M2Shapes = Shapes(
//     small = RoundedCornerShape(4.dp),
//     medium = RoundedCornerShape(4.dp),
//     large = RoundedCornerShape(0.dp)
// )

// M3 approach (new):
private val M3Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun ShapeMigrationExample() {
    MaterialTheme(shapes = M3Shapes) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "M3 Shape System",
                style = MaterialTheme.typography.headlineSmall
            )

            // Extra Small (new in M3)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Text(
                    text = "Extra Small (4.dp) - New in M3",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Small (exists in M2)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Small (8.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Medium (exists in M2)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Medium (12.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Large (exists in M2)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = "Large (16.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Extra Large (new in M3)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text(
                    text = "Extra Large (28.dp) - New in M3",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Special shapes
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape  // M2: none
            ) {
                Text(
                    text = "Rectangle Shape (no radius)",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

