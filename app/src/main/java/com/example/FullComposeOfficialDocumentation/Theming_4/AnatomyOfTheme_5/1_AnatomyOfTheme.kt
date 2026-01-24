package com.example.FullComposeOfficialDocumentation.Theming_4.AnatomyOfTheme_5

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

/**
 * Anatomy of a Theme in Compose - Overview
 *
 * Themes are built from lower-level constructs:
 * 1. Theme System Classes - Data classes holding theming values
 * 2. CompositionLocals - Implicit providers for theme systems
 * 3. Theme Function - Constructs and provides theme values
 * 4. Theme Object - Convenience accessor for theme values
 *
 * This pattern is used by MaterialTheme and can be applied to custom design systems
 */

/**
 * Step 1: Theme System Classes
 *
 * Classes group related theming values (colors, typography, shapes, etc.)
 * Must be annotated with @Stable or @Immutable for Compose optimization
 *
 * @Immutable tells Compose the values won't change after creation
 * This allows Compose to skip recomposition when values haven't changed
 */

// Color System - Groups color-related values
@Immutable
data class ColorSystem(
    val color: Color,
    val gradient: List<Color>
)

// Typography System - Groups text-related values
@Immutable
data class TypographySystem(
    val fontFamily: FontFamily,
    val textStyle: TextStyle
)

// Custom System - Any additional theming concept
@Immutable
data class CustomSystem(
    val value1: Int,
    val value2: String
)

/**
 * Why use data classes?
 * - Easy to define and use
 * - Automatic equality checks
 * - Copy functionality for variations
 * - Destructuring support
 *
 * Why @Immutable?
 * - Tells Compose values won't change
 * - Enables smart recomposition
 * - Better performance
 * - Type safety
 */

/**
 * Example Usage Scenario:
 *
 * val myColors = ColorSystem(
 *     color = Color(0xFF3DDC84),
 *     gradient = listOf(Color.White, Color(0xFFD7EFFF))
 * )
 *
 * // Create a variation
 * val darkColors = myColors.copy(
 *     color = Color(0xFF1B5E20)
 * )
 */
