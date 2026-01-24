package com.example.FullComposeOfficialDocumentation.Theming_4.Custom_Theming_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Using Extension Properties with MaterialTheme
 *
 * This demonstrates practical usage of extension properties
 * defined in 1_CustomTheming.kt
 *
 * Extension properties provide a simple way to add custom values
 * without creating a full custom theme
 */

@Composable
fun ExtensionPropertiesUsageExample() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Extension Properties Example",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Standard Material Typography",
                style = MaterialTheme.typography.bodyLarge
            )

            // Using custom extension property for typography
            Text(
                text = "Custom TextField Input Style",
                style = MaterialTheme.typography.textFieldInput
            )

            // Using custom extension property for shape
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.card // Custom rounded shape
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Card with Custom Shape",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "This card uses the custom 'card' shape (20.dp rounded)",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    // Using custom extension property for color
                    Text(
                        text = "Action Text",
                        color = MaterialTheme.colorScheme.snackbarAction,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Text(
                text = "Benefits of Extension Properties:",
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = "• Simple to implement\n" +
                        "• Type-safe access\n" +
                        "• Works with existing MaterialTheme\n" +
                        "• No wrapper composables needed",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

