package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

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
 * Material Design 3 Overview
 * MaterialTheme is the foundation of M3 theming system
 * It contains three main subsystems: ColorScheme, Typography, and Shapes
 */

@Composable
fun MaterialTheme3Basics() {
    // MaterialTheme wraps your app content and provides theming
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Access theme colors
            Text(
                text = "Primary Color",
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Secondary Color",
                color = MaterialTheme.colorScheme.secondary
            )

            // Access theme typography
            Text(
                text = "Display Large",
                style = MaterialTheme.typography.displayLarge
            )

            Text(
                text = "Body Medium",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

