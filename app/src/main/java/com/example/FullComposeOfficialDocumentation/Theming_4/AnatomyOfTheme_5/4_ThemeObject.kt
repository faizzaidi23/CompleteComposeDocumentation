package com.example.FullComposeOfficialDocumentation.Theming_4.AnatomyOfTheme_5

import androidx.compose.runtime.Composable

/**
 * Theme Object - Convenience Accessor
 *
 * The theme object provides a clean API for accessing theme values
 * Named the same as the theme function for consistency
 *
 * Pattern: Theme.systemName.property
 */

/**
 * Step 4: Creating the Theme Object
 *
 * Object with properties that access CompositionLocal.current
 * Provides type-safe, convenient access to theme values
 */

// Use with: Theme.colorSystem.color
object Theme {
    val colorSystem: ColorSystem
        @Composable
        get() = LocalColorSystem.current

    val typographySystem: TypographySystem
        @Composable
        get() = LocalTypographySystem.current

    val customSystem: CustomSystem
        @Composable
        get() = LocalCustomSystem.current
}

/**
 * Why use an object?
 *
 * Benefits:
 * - Clean, readable syntax: Theme.colorSystem.color
 * - Type-safe access
 * - IDE autocomplete support
 * - Consistent with MaterialTheme pattern
 * - Centralized access point
 *
 * Alternative (without object):
 * val color = LocalColorSystem.current.color  // More verbose
 *
 * With object:
 * val color = Theme.colorSystem.color  // Cleaner
 */

/**
 * Usage Examples:
 *
 * @Composable
 * fun MyComponent() {
 *     // Access color system
 *     val color = Theme.colorSystem.color
 *     val gradient = Theme.colorSystem.gradient
 *
 *     // Access typography system
 *     val fontFamily = Theme.typographySystem.fontFamily
 *     val textStyle = Theme.typographySystem.textStyle
 *
 *     // Access custom system
 *     val value1 = Theme.customSystem.value1
 *     val value2 = Theme.customSystem.value2
 *
 *     // Use in components
 *     Text(
 *         text = value2,
 *         style = textStyle,
 *         color = color
 *     )
 * }
 */

/**
 * Important Notes:
 *
 * 1. Properties must be @Composable
 *    - Because .current is a composable property
 *    - Allows Compose to track when values change
 *
 * 2. Object name matches theme function
 *    - Theme() function
 *    - Theme object
 *    - Consistent naming convention
 *
 * 3. Each property returns a system
 *    - Not individual values
 *    - Access specific values through the system
 *    - Example: Theme.colorSystem.color (not Theme.color)
 */

/**
 * Comparison with MaterialTheme:
 *
 * MaterialTheme follows this exact pattern:
 *
 * MaterialTheme() - Theme function
 * MaterialTheme.colorScheme - Accessor object
 * MaterialTheme.typography - Accessor object
 * MaterialTheme.shapes - Accessor object
 *
 * Your custom theme:
 *
 * Theme() - Theme function
 * Theme.colorSystem - Accessor object
 * Theme.typographySystem - Accessor object
 * Theme.customSystem - Accessor object
 */

