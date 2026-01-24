package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Customizing Component Theming
 *
 * Most M3 components have default colors but expose APIs for customization
 * Components provide Default objects (e.g., CardDefaults, ButtonDefaults)
 * These allow customization of:
 * - Colors for different states
 * - Elevation levels
 * - Other component-specific properties
 */

@Composable
fun CustomComponentTheming() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Custom Component Theming",
            style = MaterialTheme.typography.headlineSmall
        )

        // Custom card colors and elevation
        val customCardColors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )

        val customCardElevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 2.dp,
            focusedElevation = 4.dp,
            hoveredElevation = 6.dp
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = customCardColors,
            elevation = customCardElevation
        ) {
            Text(
                text = "Custom Themed Card",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Custom button colors
        val customButtonColors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Button(
            onClick = { },
            colors = customButtonColors
        ) {
            Text("Custom Colored Button")
        }
    }
}

