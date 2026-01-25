package com.example.FullComposeOfficialDocumentation.Text_Typography_5.workingWithFonts_7

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*
 ═══════════════════════════════════════════════════════════════════════════════
 TYPOGRAPHY - APP-WIDE FONT CONFIGURATION
 ═══════════════════════════════════════════════════════════════════════════════

 Typography in Material Design defines a type scale with different text styles.
 Instead of setting fontFamily on every Text composable, define it once in Typography.

 Material 3 Typography includes 15 text styles:
 - Display: Large, Medium, Small (largest text on screen)
 - Headline: Large, Medium, Small (high-emphasis headings)
 - Title: Large, Medium, Small (medium-emphasis headings)
 - Body: Large, Medium, Small (main content text)
 - Label: Large, Medium, Small (buttons, tabs, smaller text)

 Benefits:
 - Consistent typography across entire app
 - Easy to change font for all text at once
 - Follows Material Design guidelines
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 DEFINE CUSTOM TYPOGRAPHY WITH CUSTOM FONTS
 ═══════════════════════════════════════════════════════════════════════════════
*/

// Using the custom font family from file 2_CustomFontsFromResFolder.kt
val MyAppTypography = Typography(
    // Display styles - for large, short text
    displayLarge = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    // Headline styles - for high-emphasis text
    headlineLarge = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // Title styles - for medium-emphasis text
    titleLarge = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // Body styles - for main content
    bodyLarge = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // Label styles - for buttons, captions, smaller text
    labelLarge = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = firaSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

/*
 ═══════════════════════════════════════════════════════════════════════════════
 TYPOGRAPHY WITH DOWNLOADABLE FONTS
 ═══════════════════════════════════════════════════════════════════════════════
*/

// Using downloadable font from file 3_DownloadableFontsGoogleFonts.kt
val DownloadableFontTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    labelLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    )
)

/*
 ═══════════════════════════════════════════════════════════════════════════════
 TYPOGRAPHY WITH VARIABLE FONTS
 ═══════════════════════════════════════════════════════════════════════════════
*/

// Using variable fonts from file 4_VariableFonts.kt
val VariableFontTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = displayLargeFontFamily,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = headlineFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = bodyFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = bodyFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )
)

/*
 ═══════════════════════════════════════════════════════════════════════════════
 HOW TO USE TYPOGRAPHY IN YOUR APP
 ═══════════════════════════════════════════════════════════════════════════════

 Step 1: Define your Typography (as shown above)

 Step 2: Pass it to MaterialTheme in your app's theme composable:

 @Composable
 fun MyAppTheme(
     content: @Composable () -> Unit
 ) {
     MaterialTheme(
         typography = MyAppTypography,
         colorScheme = ...,
         content = content
     )
 }

 Step 3: Use it in your composables:

 @Composable
 fun MyScreen() {
     MyAppTheme {
         Column {
             Text(
                 text = "Title",
                 style = MaterialTheme.typography.headlineLarge
             )
             Text(
                 text = "Body text",
                 style = MaterialTheme.typography.bodyMedium
             )
         }
     }
 }

 Benefits:
 - Change font for entire app by updating Typography once
 - Consistent text styles across all screens
 - Easy to switch between different fonts
 - Follows Material Design guidelines
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 WHEN TO USE EACH TEXT STYLE
 ═══════════════════════════════════════════════════════════════════════════════

 displayLarge/Medium/Small:
 - Largest text on screen
 - Short, important text (e.g., onboarding screens, hero sections)
 - Use sparingly (1-2 per screen max)

 headlineLarge/Medium/Small:
 - Section titles
 - Screen titles in app bar
 - Card headings

 titleLarge/Medium/Small:
 - List item titles
 - Subsection headings
 - Dialog titles

 bodyLarge/Medium/Small:
 - Main content text
 - Paragraphs
 - Descriptions
 - bodyLarge for primary content
 - bodyMedium for secondary content
 - bodySmall for tertiary content

 labelLarge/Medium/Small:
 - Button text
 - Tab labels
 - Input field labels
 - Chips
 - Captions
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 EXAMPLE: COMPLETE SCREEN WITH TYPOGRAPHY
 ═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun TypographyExampleScreen() {
    MaterialTheme(typography = MyAppTypography) {
        Column {
            // Screen title
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.displayMedium
            )

            // Section heading
            Text(
                text = "Features",
                style = MaterialTheme.typography.headlineMedium
            )

            // Subsection
            Text(
                text = "Easy to Use",
                style = MaterialTheme.typography.titleLarge
            )

            // Main content
            Text(
                text = "This app makes everything simple and intuitive.",
                style = MaterialTheme.typography.bodyLarge
            )

            // Supporting text
            Text(
                text = "Last updated: January 2026",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
