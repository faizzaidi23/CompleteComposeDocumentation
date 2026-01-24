package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Color Migration from M2 to M3
 *
 * M2 had 4 key colors: primary, primaryVariant, secondary, secondaryVariant
 * M3 has 5 key colors: primary, secondary, tertiary, error, neutral
 *
 * Each generates 13-tone palettes with container variants
 *
 * M2 -> Material Theme Builder mapping:
 * - M2 primary -> M3 Primary
 * - M2 primaryVariant -> M3 Secondary
 * - M2 secondary -> M3 Tertiary
 * - M2 surface/background -> M3 Neutral
 */

// M2 approach (old):
// val M2LightColors = lightColors(
//     primary = Color(0xFF6200EE),
//     primaryVariant = Color(0xFF3700B3),
//     secondary = Color(0xFF03DAC6)
// )

// M3 approach (new):
private val M3LightColors = lightColorScheme(
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
    onTertiaryContainer = Color(0xFF00201B)
)

private val M3DarkColors = darkColorScheme(
    primary = Color(0xFFACD370),
    onPrimary = Color(0xFF213600),
    primaryContainer = Color(0xFF324F00),
    onPrimaryContainer = Color(0xFFC7F089),

    secondary = Color(0xFFC1CAAB),
    onSecondary = Color(0xFF2B331C),
    secondaryContainer = Color(0xFF424A31),
    onSecondaryContainer = Color(0xFFDDE6C6)
)

@Composable
fun ColorMigrationExample(darkTheme: Boolean = false) {
    val colorScheme = if (darkTheme) M3DarkColors else M3LightColors

    MaterialTheme(colorScheme = colorScheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "M3 Color System",
                style = MaterialTheme.typography.headlineSmall
            )

            // Primary color usage
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = "Primary Container",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            // Secondary color usage
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text(
                    text = "Secondary Container",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            // Tertiary color usage (new in M3)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Text(
                    text = "Tertiary Container (New in M3)",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

