package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

/**
 * Material Design 3 Shapes
 *
 * Shapes define the style of container corners
 * M3 provides 5 shape sizes:
 * - Extra Small: 4.dp default
 * - Small: 8.dp default
 * - Medium: 12.dp default
 * - Large: 16.dp default
 * - Extra Large: 28.dp default
 *
 * Additional shapes: RectangleShape (no radius), CircleShape (fully circular)
 */

// Custom shapes definition
val customShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun ShapesExample() {
    MaterialTheme(shapes = customShapes) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Shape Examples",
                style = MaterialTheme.typography.headlineSmall
            )

            // Extra Small shape
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Text(
                    text = "Extra Small Shape (4.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Small shape
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Small Shape (8.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Medium shape
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Medium Shape (12.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Large shape
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = "Large Shape (16.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Extra Large shape
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text(
                    text = "Extra Large Shape (24.dp)",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun SpecialShapesExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Rectangle shape - no rounded corners
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape
        ) {
            Text(
                text = "Rectangle Shape (No Radius)",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Circle shape - fully circular
        FloatingActionButton(
            onClick = { },
            shape = CircleShape,
            modifier = Modifier.size(56.dp)
        ) {
            Text("FAB")
        }

        // Custom rounded corner for specific sides
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )
        ) {
            Text(
                text = "Custom Shape - Rounded Top Only",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

