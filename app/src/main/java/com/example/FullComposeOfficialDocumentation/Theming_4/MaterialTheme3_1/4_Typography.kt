package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialTheme3_1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Material Design 3 Typography
 *
 * M3 provides a type scale with simplified naming:
 * - Display (Large, Medium, Small): Largest text, for short important text
 * - Headline (Large, Medium, Small): High-emphasis text, shorter than display
 * - Title (Large, Medium, Small): Medium-emphasis text for section headers
 * - Body (Large, Medium, Small): Main content text
 * - Label (Large, Medium, Small): Small text for labels and captions
 *
 * Total: 15 text styles (5 categories × 3 sizes)
 */

// Custom typography definition
val customTypography = Typography(
    // Display styles - largest text
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),

    // Headline styles
    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),

    // Title styles
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    // Body styles - main content
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    // Label styles - small text
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

@Composable
fun TypographyExample() {
    MaterialTheme(typography = customTypography) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Display
            Text(
                text = "Display Large",
                style = MaterialTheme.typography.displayLarge
            )

            // Headline
            Text(
                text = "Headline Medium",
                style = MaterialTheme.typography.headlineMedium
            )

            // Title
            Text(
                text = "Title Large - Section Header",
                style = MaterialTheme.typography.titleLarge
            )

            // Body
            Text(
                text = "Body Large - This is the main content text that users will read. " +
                        "It has comfortable spacing and readability.",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Body Medium - Slightly smaller for secondary content.",
                style = MaterialTheme.typography.bodyMedium
            )

            // Label
            Text(
                text = "LABEL MEDIUM",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun CustomFontTypography() {
    // Typography with custom font family
    val customFontTypography = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp
        )
    )

    MaterialTheme(typography = customFontTypography) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Title with Serif Font",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Body with Sans Serif Font",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

