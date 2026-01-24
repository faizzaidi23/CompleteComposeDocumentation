package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Accessibility in Material Design 3
 *
 * M3 color system is designed for accessible contrast by default
 * Key principles:
 * - Use "on" colors with their corresponding base colors
 * - onPrimary on primary, onPrimaryContainer on primaryContainer
 * - Tonal palettes ensure proper contrast ratios (4.5:1 minimum)
 * - Avoid mismatched color combinations
 */

@Composable
fun AccessibleColorUsage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Accessible Color Usage",
            style = MaterialTheme.typography.headlineSmall
        )

        // Correct - Good contrast
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Good Contrast: Primary + OnPrimary")
        }

        // Correct - Good contrast
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text("Good Contrast: Container + OnContainer")
        }

        // Incorrect example - Poor contrast (for demonstration)
        Text(
            text = "Avoid This:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.error
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text("Poor Contrast: Mismatched Colors")
        }
    }
}

@Composable
fun TypographyAccessibility() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Typography Scale for Different Devices",
            style = MaterialTheme.typography.headlineSmall
        )

        // Display styles scale for large screens
        Text(
            text = "Display Small adapts to device context",
            style = MaterialTheme.typography.displaySmall
        )

        // Body text remains readable across devices
        Text(
            text = "Body text maintains readability with proper line height and letter spacing. " +
                    "M3 typography ensures accessible text sizes.",
            style = MaterialTheme.typography.bodyLarge
        )

        // Labels for interactive elements
        Text(
            text = "LABEL TEXT FOR BUTTONS",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

