package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Dynamic Color - Material You
 *
 * Dynamic color derives custom colors from user's wallpaper (Android 12+)
 * The system generates a color palette and applies it to apps and system UI
 *
 * Steps:
 * 1. Check if dynamic color is available (Android 12+)
 * 2. Use dynamicLightColorScheme() or dynamicDarkColorScheme()
 * 3. Fall back to custom colors for older Android versions
 */

@Composable
fun DynamicColorExample(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColorEnabled: Boolean = true
) {
    // Check if dynamic color is available
    val dynamicColor = dynamicColorEnabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        // Dynamic color schemes for Android 12+
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)

        // Fallback to custom color schemes
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    MaterialTheme(colorScheme = colorScheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (dynamicColor) "Dynamic Color Enabled" else "Using Default Colors",
                style = MaterialTheme.typography.headlineSmall
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = "This card uses primary container color",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Button(onClick = { }) {
                Text("Dynamic themed button")
            }
        }
    }
}

