package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Typography Migration from M2 to M3
 *
 * M2 Typography -> M3 Typography mapping:
 * - h1 -> displayLarge
 * - h2 -> displayMedium
 * - h3 -> displaySmall
 * - h4 -> headlineMedium
 * - h5 -> headlineSmall
 * - h6 -> titleLarge
 * - subtitle1 -> titleMedium
 * - subtitle2 -> titleSmall
 * - body1 -> bodyLarge
 * - body2 -> bodyMedium
 * - caption -> bodySmall
 * - button -> labelLarge
 * - overline -> labelSmall
 *
 * New in M3: headlineLarge, labelMedium
 * Note: M3 Typography doesn't have defaultFontFamily parameter
 */

// M2 approach (old):
// val M2Typography = Typography(
//     h1 = TextStyle(...),
//     h2 = TextStyle(...),
//     body1 = TextStyle(...)
// )

// M3 approach (new):
private val M3Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)

@Composable
fun TypographyMigrationExample() {
    MaterialTheme(typography = M3Typography) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // M2: h1 -> M3: displayLarge
            Text(
                text = "Display Large (was h1)",
                style = MaterialTheme.typography.displayLarge
            )

            // M2: h4 -> M3: headlineMedium
            Text(
                text = "Headline Medium (was h4)",
                style = MaterialTheme.typography.headlineMedium
            )

            // M2: h6 -> M3: titleLarge
            Text(
                text = "Title Large (was h6)",
                style = MaterialTheme.typography.titleLarge
            )

            // M2: body1 -> M3: bodyLarge
            Text(
                text = "Body Large (was body1) - Main content text",
                style = MaterialTheme.typography.bodyLarge
            )

            // M2: caption -> M3: bodySmall
            Text(
                text = "Body Small (was caption)",
                style = MaterialTheme.typography.bodySmall
            )

            // M2: button -> M3: labelLarge
            Text(
                text = "LABEL LARGE (WAS BUTTON)",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

