package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Material Design 3 Color Scheme
 *
 * Color scheme is built from 5 key colors that generate 13 tonal palettes
 * Each palette contains variations from 0 (white) to 100 (black)
 *
 * Key color roles:
 * - Primary: Main brand color, used for prominent components
 * - Secondary: Less prominent components, expands color expression
 * - Tertiary: Contrasting accents, balances primary and secondary
 * - Error: Error states and alerts
 * - Background/Surface: Container colors
 */

// Custom light color scheme
private val LightColors = lightColorScheme(
    primary = Color(0xFF476810),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC7F089),
    onPrimaryContainer = Color(0xFF121F00),

    secondary = Color(0xFF596248),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDDE6C6),
    onSecondaryContainer = Color(0xFF161E0A),

    tertiary = Color(0xFF39665D),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFBCECDF),
    onTertiaryContainer = Color(0xFF00201B),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),

    background = Color(0xFFFEFCF5),
    onBackground = Color(0xFF1B1C18),

    surface = Color(0xFFFEFCF5),
    onSurface = Color(0xFF1B1C18),
    surfaceVariant = Color(0xFFE2E4D4),
    onSurfaceVariant = Color(0xFF45483D)
)

// Custom dark color scheme
private val DarkColors = darkColorScheme(
    primary = Color(0xFFACD370),
    onPrimary = Color(0xFF213600),
    primaryContainer = Color(0xFF324F00),
    onPrimaryContainer = Color(0xFFC7F089),

    secondary = Color(0xFFC1CAAB),
    onSecondary = Color(0xFF2B331C),
    secondaryContainer = Color(0xFF424A31),
    onSecondaryContainer = Color(0xFFDDE6C6),

    tertiary = Color(0xFFA0D0C4),
    onTertiary = Color(0xFF00382F),
    tertiaryContainer = Color(0xFF1F4E45),
    onTertiaryContainer = Color(0xFFBCECDF),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),

    background = Color(0xFF1B1C18),
    onBackground = Color(0xFFE4E3DB),

    surface = Color(0xFF1B1C18),
    onSurface = Color(0xFFE4E3DB),
    surfaceVariant = Color(0xFF45483D),
    onSurfaceVariant = Color(0xFFC5C8B8)
)

@Composable
fun ColorSchemeExample() {
    // Using custom color scheme
    MaterialTheme(colorScheme = LightColors) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Primary color usage
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Primary Container",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // Secondary color usage
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Secondary Container",
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            // Tertiary color usage
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tertiary Container",
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

