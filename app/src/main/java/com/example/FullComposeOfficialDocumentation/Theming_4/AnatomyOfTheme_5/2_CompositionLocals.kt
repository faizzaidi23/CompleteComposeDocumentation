package com.example.FullComposeOfficialDocumentation.Theming_4.AnatomyOfTheme_5

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

/**
 * CompositionLocals for Theme Systems
 *
 * CompositionLocals allow theme values to be implicitly passed down the composition tree
 * This means composables can access theme values without explicit parameter passing
 *
 * Two types:
 * - compositionLocalOf: Recomposes all consumers when value changes
 * - staticCompositionLocalOf: Better performance, use when values rarely change
 */

/**
 * Step 2: Creating CompositionLocals
 *
 * Each theme system needs a CompositionLocal
 * Use staticCompositionLocalOf for theme values (they rarely change)
 * Provide default/fallback values in the lambda
 */

// CompositionLocal for ColorSystem
val LocalColorSystem = staticCompositionLocalOf {
    ColorSystem(
        color = Color.Unspecified,  // Safe default
        gradient = emptyList()
    )
}

// CompositionLocal for TypographySystem
val LocalTypographySystem = staticCompositionLocalOf {
    TypographySystem(
        fontFamily = FontFamily.Default,  // Safe default
        textStyle = TextStyle.Default
    )
}

// CompositionLocal for CustomSystem
val LocalCustomSystem = staticCompositionLocalOf {
    CustomSystem(
        value1 = 0,  // Safe default
        value2 = ""
    )
}

/**
 * Why use default values?
 *
 * Default values act as fallbacks when:
 * - Theme is not provided
 * - Composable is used outside theme scope
 * - During preview/testing
 *
 * Best practices for defaults:
 * - Color.Unspecified for colors
 * - TextStyle.Default for text styles
 * - FontFamily.Default for fonts
 * - Empty collections for lists
 * - 0 for numeric values
 * - "" for strings
 */

/**
 * staticCompositionLocalOf vs compositionLocalOf
 *
 * Use staticCompositionLocalOf when:
 * - Values rarely change (like theme values)
 * - Better performance needed
 * - Theme is set once at app start
 *
 * Use compositionLocalOf when:
 * - Values change frequently
 * - Need to minimize recomposition scope
 * - Dynamic theme switching
 */

/**
 * How it works:
 *
 * 1. CompositionLocal is created with default value
 * 2. Theme function provides actual value with CompositionLocalProvider
 * 3. Composables access current value with .current
 * 4. Value is automatically available to all children in composition tree
 *
 * Example flow:
 * Theme() provides LocalColorSystem
 *   ↓
 * Screen() can access LocalColorSystem.current
 *   ↓
 * Component() can also access LocalColorSystem.current
 *   ↓
 * All children have access without passing parameters
 */

