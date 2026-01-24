package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
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
 * Complete Material Design 3 Theme Implementation
 *
 * This demonstrates how to create a full custom theme combining:
 * - Custom color schemes (light and dark)
 * - Custom typography
 * - Custom shapes
 * - Dynamic color support
 */

// Define custom light color scheme
private val CustomLightColors = lightColorScheme(
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
    background = Color(0xFFFEFCF5),
    onBackground = Color(0xFF1B1C18),
    surface = Color(0xFFFEFCF5),
    onSurface = Color(0xFF1B1C18)
)

// Define custom dark color scheme
private val CustomDarkColors = darkColorScheme(
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
    background = Color(0xFF1B1C18),
    onBackground = Color(0xFFE4E3DB),
    surface = Color(0xFF1B1C18),
    onSurface = Color(0xFFE4E3DB)
)

// Define custom typography
private val CustomTypography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)

// Define custom shapes
private val CustomShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

/**
 * Custom App Theme
 * Combines all theming elements with dynamic color support
 */
@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Determine color scheme based on settings
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> CustomDarkColors
        else -> CustomLightColors
    }

    // Apply theme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = CustomTypography,
        shapes = CustomShapes,
        content = content
    )
}

/**
 * Example app using the custom theme
 */
@Composable
fun CompleteThemedApp() {
    MyAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title using custom typography
            Text(
                text = "My Themed App",
                style = MaterialTheme.typography.headlineLarge
            )

            // Card with custom colors and shapes
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Card Title",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "This card uses the custom theme with custom colors, typography, and shapes.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            // Button with theme colors
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Themed Button")
            }

            // FAB with theme elements
            ExtendedFloatingActionButton(
                onClick = { },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Add Item") }
            )
        }
    }
}

