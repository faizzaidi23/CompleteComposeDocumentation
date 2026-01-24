package com.example.FullComposeOfficialDocumentation.Theming_4.AnatomyOfTheme_5

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

/**
 * Theme Function - Entry Point and Primary API
 *
 * The theme function:
 * 1. Constructs instances of theme systems
 * 2. Provides them to the composition tree
 * 3. Wraps content to make theme available to children
 *
 * This is what users call to apply theming to their UI
 */

/**
 * Step 3: Creating the Theme Function
 *
 * The theme function is a composable that:
 * - Creates theme system instances with real values
 * - Uses CompositionLocalProvider to provide values
 * - Accepts content lambda for nested composables
 */

@Composable
fun Theme(
    // Optional parameters for theme customization
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    // Construct ColorSystem with actual values
    val colorSystem = ColorSystem(
        color = Color(0xFF3DDC84),  // Android green
        gradient = listOf(Color.White, Color(0xFFD7EFFF))
    )

    // Construct TypographySystem with actual values
    val typographySystem = TypographySystem(
        fontFamily = FontFamily.Monospace,
        textStyle = TextStyle(fontSize = 18.sp)
    )

    // Construct CustomSystem with actual values
    val customSystem = CustomSystem(
        value1 = 1000,
        value2 = "Custom system"
    )

    // Provide all theme systems to composition tree
    CompositionLocalProvider(
        LocalColorSystem provides colorSystem,
        LocalTypographySystem provides typographySystem,
        LocalCustomSystem provides customSystem,
        content = content  // Children can now access theme values
    )
}

/**
 * Theme Function with Dark Mode Support
 *
 * More realistic example with theme variations
 */
@Composable
fun ThemeWithVariations(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    // Create different color systems based on theme
    val colorSystem = if (darkTheme) {
        ColorSystem(
            color = Color(0xFF4CAF50),  // Lighter green for dark mode
            gradient = listOf(Color(0xFF1A1A1A), Color(0xFF2C2C2C))
        )
    } else {
        ColorSystem(
            color = Color(0xFF2E7D32),  // Darker green for light mode
            gradient = listOf(Color.White, Color(0xFFE8F5E9))
        )
    }

    // Typography can also vary by theme
    val typographySystem = TypographySystem(
        fontFamily = FontFamily.SansSerif,
        textStyle = TextStyle(
            fontSize = if (darkTheme) 16.sp else 18.sp
        )
    )

    val customSystem = CustomSystem(
        value1 = if (darkTheme) 800 else 1000,
        value2 = if (darkTheme) "Dark theme" else "Light theme"
    )

    CompositionLocalProvider(
        LocalColorSystem provides colorSystem,
        LocalTypographySystem provides typographySystem,
        LocalCustomSystem provides customSystem,
        content = content
    )
}

/**
 * How the Theme Function Works:
 *
 * 1. User calls: Theme { /* app content */ }
 * 2. Theme function creates system instances
 * 3. CompositionLocalProvider makes values available
 * 4. All composables in content lambda can access theme
 * 5. Changes to theme trigger recomposition
 *
 * Example usage:
 *
 * @Composable
 * fun MyApp() {
 *     Theme {
 *         // All children can access theme values
 *         Screen1()
 *         Screen2()
 *     }
 * }
 *
 * @Composable
 * fun Screen1() {
 *     // Can access theme without passing parameters
 *     val color = Theme.colorSystem.color
 *     Box(modifier = Modifier.background(color))
 * }
 */

/**
 * Key Points:
 *
 * - Theme function is the main entry point
 * - Creates actual theme values (not defaults)
 * - Uses CompositionLocalProvider to distribute values
 * - content parameter allows nesting
 * - Can include logic for variations (dark mode, etc.)
 * - All theme systems provided in one place
 */

