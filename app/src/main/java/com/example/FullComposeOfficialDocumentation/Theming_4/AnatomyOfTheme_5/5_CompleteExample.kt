package com.example.FullComposeOfficialDocumentation.Theming_4.AnatomyOfTheme_5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

/**
 * Complete Example - All Components Working Together
 *
 * This file demonstrates how all the anatomy pieces work together:
 * 1. Theme System Classes (1_AnatomyOfTheme.kt)
 * 2. CompositionLocals (2_CompositionLocals.kt)
 * 3. Theme Function (3_ThemeFunction.kt)
 * 4. Theme Object (4_ThemeObject.kt)
 *
 * Real-world usage examples showing the complete theme system in action
 */

/**
 * Example 1: Basic App Structure
 *
 * Shows how to wrap your app with the theme and access values
 */
@Composable
fun MyApp() {
    // Wrap entire app in theme
    Theme {
        // All children can access theme values
        AppContent()
    }
}

@Composable
fun AppContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = Theme.colorSystem.gradient  // Access via Theme object
                )
            )
            .padding(16.dp)
    ) {
        WelcomeText()
        Spacer(modifier = Modifier.height(16.dp))
        InfoSection()
        Spacer(modifier = Modifier.height(16.dp))
        StyledText()
    }
}

/**
 * Example 2: Accessing Color System
 */
@Composable
fun WelcomeText() {
    // Access color through Theme object
    val color = Theme.colorSystem.color

    Text(
        text = "Welcome to Custom Theme",
        color = color
    )
}

/**
 * Example 3: Accessing Typography System
 */
@Composable
fun StyledText() {
    // Access typography through Theme object
    val textStyle = Theme.typographySystem.textStyle
    val fontFamily = Theme.typographySystem.fontFamily

    Text(
        text = "Styled with custom typography",
        style = textStyle,
        fontFamily = fontFamily
    )
}

/**
 * Example 4: Accessing Custom System
 */
@Composable
fun InfoSection() {
    // Access custom system values
    val value1 = Theme.customSystem.value1
    val value2 = Theme.customSystem.value2

    Column {
        Text(
            text = "Custom Value 1: $value1",
            style = Theme.typographySystem.textStyle
        )
        Text(
            text = "Custom Value 2: $value2",
            style = Theme.typographySystem.textStyle,
            color = Theme.colorSystem.color
        )
    }
}

/**
 * Example 5: Multiple Theme Accesses in One Component
 */
@Composable
fun ComplexComponent() {
    // Can access multiple theme systems
    val color = Theme.colorSystem.color
    val gradient = Theme.colorSystem.gradient
    val textStyle = Theme.typographySystem.textStyle
    val customValue = Theme.customSystem.value2

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = Brush.horizontalGradient(gradient))
            .padding(16.dp)
    ) {
        Text(
            text = customValue,
            style = textStyle,
            color = color
        )
    }
}

/**
 * Example 6: Theme with Dark Mode Support
 */
@Composable
fun ThemedAppWithDarkMode(darkTheme: Boolean) {
    ThemeWithVariations(darkTheme = darkTheme) {
        // Theme automatically switches based on darkTheme parameter
        Column {
            Text(
                text = Theme.customSystem.value2,  // Will show "Dark theme" or "Light theme"
                color = Theme.colorSystem.color     // Color adapts to theme
            )
        }
    }
}

/**
 * How It All Works Together:
 *
 * 1. Theme System Classes (Step 1)
 *    └─> Define ColorSystem, TypographySystem, CustomSystem
 *
 * 2. CompositionLocals (Step 2)
 *    └─> Create LocalColorSystem, LocalTypographySystem, LocalCustomSystem
 *
 * 3. Theme Function (Step 3)
 *    └─> Constructs systems and provides them via CompositionLocalProvider
 *
 * 4. Theme Object (Step 4)
 *    └─> Provides clean API: Theme.colorSystem.color
 *
 * 5. Usage (This file)
 *    └─> Access values anywhere in composition tree
 */

/**
 * Comparison with MaterialTheme:
 *
 * MaterialTheme Structure:
 * MaterialTheme {              // Theme function
 *     Text(
 *         color = MaterialTheme.colorScheme.primary,  // Theme object
 *         style = MaterialTheme.typography.bodyLarge
 *     )
 * }
 *
 * Your Custom Theme Structure:
 * Theme {                      // Theme function
 *     Text(
 *         color = Theme.colorSystem.color,            // Theme object
 *         style = Theme.typographySystem.textStyle
 *     )
 * }
 *
 * Same pattern, different implementation!
 */

/**
 * Key Benefits:
 *
 * 1. No Parameter Drilling
 *    - Don't need to pass theme values through every function
 *    - Available anywhere in composition tree
 *
 * 2. Type Safety
 *    - All values are strongly typed
 *    - IDE provides autocomplete
 *    - Compile-time error checking
 *
 * 3. Centralized Management
 *    - All theme logic in one place
 *    - Easy to modify and extend
 *    - Consistent across app
 *
 * 4. Recomposition Optimization
 *    - @Immutable classes help Compose skip unnecessary recomposition
 *    - staticCompositionLocalOf reduces recomposition scope
 *
 * 5. Flexibility
 *    - Easy to add new theme systems
 *    - Support for theme variations (dark mode, etc.)
 *    - Can override at different levels
 */

/**
 * Advanced: Nested Theme Overrides
 *
 * You can override theme values for specific parts of the UI
 */
@Composable
fun NestedThemeExample() {
    Theme {
        Text("Uses main theme", color = Theme.colorSystem.color)

        // Override theme for this section
        CompositionLocalProvider(
            LocalColorSystem provides ColorSystem(
                color = androidx.compose.ui.graphics.Color.Red,
                gradient = emptyList()
            )
        ) {
            Text("Uses red color", color = Theme.colorSystem.color)
        }

        Text("Back to main theme", color = Theme.colorSystem.color)
    }
}

/**
 * Summary:
 *
 * The anatomy of a theme consists of:
 * - System Classes: Hold theming values
 * - CompositionLocals: Provide implicit access
 * - Theme Function: Entry point that provides values
 * - Theme Object: Convenient accessor
 *
 * This pattern is:
 * - Used by MaterialTheme
 * - Applicable to custom design systems
 * - Scalable and maintainable
 * - Optimized for Compose
 */

