package com.example.FullComposeOfficialDocumentation.Text_Typography_5.workingWithFonts_7

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 ═══════════════════════════════════════════════════════════════════════════════
 VARIABLE FONTS - REQUIRES FONT FILES
 ═══════════════════════════════════════════════════════════════════════════════

 ⚠️ This file shows how to use variable fonts, but requires actual variable font files.

 Variable fonts allow ONE font file to contain MULTIPLE styles.
 Instead of separate files for Light, Regular, Bold, etc., you adjust axes.

 Benefits:
 - Smaller file size (one file vs many)
 - Infinite variations (not limited to predefined weights)
 - Smooth animations between weights/widths

 Limitations:
 - Only supported on Android O (API 26) and above
 - Not available via downloadable fonts yet
 - Must be local files in res/font/

 Standard Axes:
 - weight (wght) - How thick/thin the font is
 - width (wdth)  - How compressed/expanded horizontally
 - slant         - How much the font leans
 - italic (ital) - Switch between upright and italic

 Custom Axes (font-specific):
 - Roboto Flex has YTAS (ascender height), XTRA (counter width), etc.
 - Check fonts.google.com/variablefonts for each font's axes

 TO USE VARIABLE FONTS:
 1. Download a variable font (e.g., Roboto Flex from fonts.google.com/variablefonts)
 2. Add .ttf file to res/font/ folder
 3. Name it lowercase with underscores (e.g., robotoflex_variable.ttf)
 4. Also add a static fallback font (e.g., robotoflex_static_regular.ttf)
 5. Uncomment the code sections marked below
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 CONFIGURATION OBJECTS FOR VARIABLE FONT SETTINGS
 ═══════════════════════════════════════════════════════════════════════════════

 Extract values into objects for:
 - Easy reuse across different text styles
 - Centralized configuration
 - Better maintainability
*/

object DisplayLargeVFConfig {
    const val WEIGHT = 950
    const val WIDTH = 30f
    const val SLANT = -6f
    const val ASCENDER_HEIGHT = 800f
    const val COUNTER_WIDTH = 500
}

object BodyTextVFConfig {
    const val WEIGHT = 400
    const val WIDTH = 100f
    const val SLANT = 0f
}

object HeadlineVFConfig {
    const val WEIGHT = 600
    const val WIDTH = 90f
    const val SLANT = 0f
}

/*
 ═══════════════════════════════════════════════════════════════════════════════
 CUSTOM AXIS HELPER FUNCTIONS
 ═══════════════════════════════════════════════════════════════════════════════

 For custom axes not in the standard FontVariation API.
 These functions:
 1. Validate the value is within the font's supported range
 2. Return a FontVariation.Setting with the correct axis name
*/

// Roboto Flex custom axis: Ascender Height (YTAS)
// Range: 649f to 854f
fun ascenderHeight(value: Float): FontVariation.Setting {
    require(value in 649f..854f) {
        "Ascender Height must be between 649f and 854f, got $value"
    }
    return FontVariation.Setting("YTAS", value)
}

// Roboto Flex custom axis: Counter Width (XTRA)
// Range: 323 to 603
fun counterWidth(value: Int): FontVariation.Setting {
    require(value in 323..603) {
        "Counter Width must be between 323 and 603, got $value"
    }
    return FontVariation.Setting("XTRA", value.toFloat())
}

/*
 ═══════════════════════════════════════════════════════════════════════════════
 VARIABLE FONT FAMILIES WITH STANDARD AXES
 ═══════════════════════════════════════════════════════════════════════════════
*/

// Default fallback for older Android versions
// val defaultFontFamily = FontFamily(
//     Font(R.font.robotoflex_static_regular)
// )

// Variable font for display text (large, attention-grabbing)
// @OptIn(ExperimentalTextApi::class)
// val displayLargeFontFamily = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//     FontFamily(
//         Font(
//             R.font.robotoflex_variable,
//             variationSettings = FontVariation.Settings(
//                 FontVariation.weight(DisplayLargeVFConfig.WEIGHT),
//                 FontVariation.width(DisplayLargeVFConfig.WIDTH),
//                 FontVariation.slant(DisplayLargeVFConfig.SLANT),
//             )
//         )
//     )
// } else {
//     defaultFontFamily
// }

// Variable font for body text (readable, medium weight)
// @OptIn(ExperimentalTextApi::class)
// val bodyFontFamily = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//     FontFamily(
//         Font(
//             R.font.robotoflex_variable,
//             variationSettings = FontVariation.Settings(
//                 FontVariation.weight(BodyTextVFConfig.WEIGHT),
//                 FontVariation.width(BodyTextVFConfig.WIDTH),
//                 FontVariation.slant(BodyTextVFConfig.SLANT),
//             )
//         )
//     )
// } else {
//     defaultFontFamily
// }

// Variable font for headlines (semi-bold, slightly condensed)
// @OptIn(ExperimentalTextApi::class)
// val headlineFontFamily = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//     FontFamily(
//         Font(
//             R.font.robotoflex_variable,
//             variationSettings = FontVariation.Settings(
//                 FontVariation.weight(HeadlineVFConfig.WEIGHT),
//                 FontVariation.width(HeadlineVFConfig.WIDTH),
//                 FontVariation.slant(HeadlineVFConfig.SLANT),
//             )
//         )
//     )
// } else {
//     defaultFontFamily
// }

// Variable font with custom axes
// @OptIn(ExperimentalTextApi::class)
// val displayWithCustomAxes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//     FontFamily(
//         Font(
//             R.font.robotoflex_variable,
//             variationSettings = FontVariation.Settings(
//                 // Standard axes
//                 FontVariation.weight(DisplayLargeVFConfig.WEIGHT),
//                 FontVariation.width(DisplayLargeVFConfig.WIDTH),
//                 FontVariation.slant(DisplayLargeVFConfig.SLANT),
//                 // Custom axes using helper functions
//                 ascenderHeight(DisplayLargeVFConfig.ASCENDER_HEIGHT),
//                 counterWidth(DisplayLargeVFConfig.COUNTER_WIDTH)
//             )
//         )
//     )
// } else {
//     defaultFontFamily
// }

/*
 ═══════════════════════════════════════════════════════════════════════════════
 TEMPORARY PLACEHOLDERS (Using system fonts until you add variable fonts)
 ═══════════════════════════════════════════════════════════════════════════════
*/

val defaultFontFamily = FontFamily.SansSerif
val displayLargeFontFamily = FontFamily.SansSerif
val bodyFontFamily = FontFamily.SansSerif
val headlineFontFamily = FontFamily.SansSerif
val displayWithCustomAxes = FontFamily.SansSerif

/*
 ═══════════════════════════════════════════════════════════════════════════════
 USING VARIABLE FONTS IN COMPOSABLES
 ═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun VariableFontBasicExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "⚠️ Variable fonts not configured",
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "To use variable fonts:",
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp
        )

        Text(
            text = "1. Download variable font from fonts.google.com/variablefonts",
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp
        )

        Text(
            text = "2. Add .ttf to res/font/ folder",
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp
        )

        Text(
            text = "3. Uncomment code in this file",
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp
        )

        Text(
            text = "\nCurrent: Using system fonts as placeholder",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun VariableFontWithCustomAxesExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Custom Axes",
            fontFamily = displayWithCustomAxes,
            fontSize = 48.sp
        )

        Text(
            text = "This text uses custom ascender height and counter width settings",
            fontFamily = displayWithCustomAxes,
            fontSize = 20.sp
        )
    }
}

/*
 ═══════════════════════════════════════════════════════════════════════════════
 COMPLETE EXAMPLE: VARIABLE FONTS WITH MATERIAL DESIGN 3
 ═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun VariableFontCardExample() {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display large - heavy, condensed, slanted
            Text(
                text = "Compose",
                fontFamily = displayLargeFontFamily,
                fontSize = 50.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                maxLines = 1
            )

            // Headline - semi-bold, slightly condensed
            Text(
                text = "Beautiful UIs on Android",
                fontFamily = headlineFontFamily,
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                maxLines = 2
            )

            // Body - regular weight, normal width
            Text(
                text = "Jetpack Compose is Android's recommended modern toolkit for building native UI. It simplifies and accelerates UI development.",
                fontFamily = bodyFontFamily,
                fontSize = 16.sp,
                maxLines = 3
            )
        }
    }
}

/*
 ═══════════════════════════════════════════════════════════════════════════════
 KEY CONCEPTS
 ═══════════════════════════════════════════════════════════════════════════════

 1. ONE FILE, MULTIPLE STYLES
    Instead of:
    - roboto_light.ttf
    - roboto_regular.ttf
    - roboto_medium.ttf
    - roboto_bold.ttf

    You have:
    - robotoflex_variable.ttf (with weight axis 100-1000)

 2. AXIS RANGES
    Each variable font defines ranges for its axes:
    - FontVariation.weight(): Usually 100-900 or 100-1000
    - FontVariation.width(): Usually 75-125 (percentage)
    - Custom axes: Check font documentation

 3. VERSION CHECK REQUIRED
    Always check Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    Provide fallback for older devices

 4. FINDING CUSTOM AXES
    Visit fonts.google.com/variablefonts
    Click on a font → See all available axes with their ranges
    Example: Roboto Flex has GRAD, XTRA, XOPQ, YOPQ, YTLC, YTUC, YTAS, etc.

 5. PERFORMANCE
    Variable fonts are efficient:
    - Single file reduces app size
    - System handles interpolation (fast)
    - Better than synthesized bold/italic

 6. WHERE TO GET VARIABLE FONTS
    - fonts.google.com/variablefonts (free)
    - Download → Select variable font → Download family
    - Extract the variable .ttf file
    - Rename to lowercase with underscores
*/
