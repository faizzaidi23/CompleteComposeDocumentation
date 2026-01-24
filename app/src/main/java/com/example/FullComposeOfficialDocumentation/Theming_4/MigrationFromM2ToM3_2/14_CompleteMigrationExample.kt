package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Complete M2 to M3 Migration Example
 *
 * This demonstrates a full migration combining:
 * - Updated dependencies (material3 instead of material)
 * - Color scheme migration (Colors -> ColorScheme)
 * - Typography migration (h1-h6 -> display/headline/title/body/label)
 * - Shape migration (3 sizes -> 5 sizes)
 * - Component updates
 *
 * Migration checklist:
 * 1. ✓ Add M3 dependency
 * 2. ✓ Create M3 color scheme
 * 3. ✓ Create M3 typography
 * 4. ✓ Create M3 shapes
 * 5. ✓ Update components gradually
 * 6. ✓ Remove M2 dependency when complete
 */

// Complete M3 theme implementation
private val AppLightColorScheme = lightColorScheme(
    primary = Color(0xFF476810),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC7F089),
    onPrimaryContainer = Color(0xFF121F00),
    secondary = Color(0xFF596248),
    onSecondary = Color(0xFFFFFFFF),
    tertiary = Color(0xFF39665D),
    onTertiary = Color(0xFFFFFFFF)
)

private val AppDarkColorScheme = darkColorScheme(
    primary = Color(0xFFACD370),
    onPrimary = Color(0xFF213600),
    primaryContainer = Color(0xFF324F00),
    onPrimaryContainer = Color(0xFFC7F089),
    secondary = Color(0xFFC1CAAB),
    onSecondary = Color(0xFF2B331C)
)

private val AppTypography = Typography(
    displayLarge = TextStyle(fontSize = 57.sp, fontWeight = FontWeight.Normal),
    headlineMedium = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.SemiBold),
    titleLarge = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> AppDarkColorScheme
        else -> AppLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}

@Composable
fun CompleteMigrationExample() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Complete M3 App",
                style = MaterialTheme.typography.headlineMedium
            )

            Card {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Successfully Migrated!",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "This app now uses Material Design 3 with updated theming and components.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Button(onClick = { }) {
                Text("M3 Button")
            }
        }
    }
}

