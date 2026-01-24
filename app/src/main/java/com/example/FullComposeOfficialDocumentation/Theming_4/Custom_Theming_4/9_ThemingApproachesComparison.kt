package com.example.FullComposeOfficialDocumentation.Theming_4.Custom_Theming_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Comparison of Custom Theming Approaches
 *
 * This file summarizes the three approaches and when to use each
 */

/**
 * APPROACH 1: Extension Properties
 *
 * Usage:
 * val ColorScheme.customColor: Color
 *     @Composable get() = Color.Red
 *
 * Use when:
 * - Adding a few simple values to Material
 * - Values are consistent across all themes
 * - You want minimal code changes
 *
 * Pros:
 * - Simplest approach
 * - Works directly with MaterialTheme
 * - Type-safe
 * - No wrapper composables needed
 *
 * Cons:
 * - Not suitable for multiple themes with different values
 * - Extension properties are global
 * - Limited to simple additions
 *
 * Example:
 * MaterialTheme {
 *     Text(color = MaterialTheme.colorScheme.snackbarAction)
 * }
 */

/**
 * APPROACH 2: Extended Theme (Wrapping MaterialTheme)
 *
 * Usage:
 * @Immutable
 * data class ExtendedColors(val caution: Color)
 *
 * @Composable
 * fun ExtendedTheme(content: @Composable () -> Unit) {
 *     CompositionLocalProvider(LocalExtendedColors provides ...) {
 *         MaterialTheme { content() }
 *     }
 * }
 *
 * Use when:
 * - Need to add custom values to Material
 * - Have multiple themes (light/dark, brand variations)
 * - Want to keep Material systems (colors, typography, shapes)
 * - Material components fit your design
 *
 * Pros:
 * - Supports multiple themes
 * - Type-safe access via object
 * - Works well with Material components
 * - Can wrap Material components easily
 *
 * Cons:
 * - More boilerplate than extension properties
 * - Need to create wrapper components
 * - Still tied to Material
 *
 * Example:
 * ExtendedTheme {
 *     Button(colors = ButtonDefaults.buttonColors(
 *         containerColor = ExtendedTheme.colors.caution
 *     )) { }
 * }
 */

/**
 * APPROACH 3: Replacement Theme
 *
 * Usage:
 * @Immutable
 * data class CustomTypography(val body: TextStyle)
 *
 * @Composable
 * fun ReplacementTheme(content: @Composable () -> Unit) {
 *     CompositionLocalProvider(LocalCustomTypography provides ...) {
 *         MaterialTheme(/* keep colors only */) { content() }
 *     }
 * }
 *
 * Use when:
 * - Want custom typography or shapes
 * - Keep some Material systems (e.g., colors)
 * - Material's type/shape system doesn't fit
 * - Still want to use some Material components
 *
 * Pros:
 * - Mix custom and Material systems
 * - Full control over replaced systems
 * - Can reuse Material components with customization
 *
 * Cons:
 * - Material components need wrapping
 * - Must manually set all relevant values
 * - More complex than extending
 * - Need ProvideTextStyle for text styling
 *
 * Example:
 * ReplacementTheme {
 *     Button(shape = ReplacementTheme.shapes.component) {
 *         ProvideTextStyle(ReplacementTheme.typography.body) {
 *             Text("Button")
 *         }
 *     }
 * }
 */

/**
 * APPROACH 4: Fully Custom Design System
 *
 * Usage:
 * @Immutable
 * data class CustomColors(
 *     val content: Color,
 *     val background: List<Color> // Gradient!
 * )
 *
 * @Composable
 * fun CustomTheme(content: @Composable () -> Unit) {
 *     CompositionLocalProvider(
 *         LocalCustomColors provides ...,
 *         LocalCustomTypography provides ...
 *     ) {
 *         content()
 *     }
 * }
 *
 * Use when:
 * - Complete design system from scratch
 * - Introducing new concepts (gradients, custom elevation)
 * - Material doesn't fit your design language
 * - Need full control over everything
 *
 * Pros:
 * - Complete freedom
 * - Can introduce new types and concepts
 * - No Material constraints
 * - Fully custom design language
 *
 * Cons:
 * - Most work required
 * - Must build/wrap all components
 * - No Material defaults
 * - Need to handle all theming aspects
 *
 * Example:
 * CustomTheme {
 *     Box(modifier = Modifier.background(
 *         brush = Brush.gradient(CustomTheme.colors.background)
 *     )) {
 *         CustomButton(onClick = {}) { Text("Button") }
 *     }
 * }
 */

@Composable
fun ThemingApproachesComparison() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Custom Theming Approaches",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "1. Extension Properties",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Simple additions, consistent across themes",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "2. Extended Theme",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Add custom values, keep Material systems",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "3. Replacement Theme",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Replace some Material systems (typography, shapes)",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "4. Fully Custom System",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Complete custom design, new concepts",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Choose based on:\n" +
                        "• How much you need to customize\n" +
                        "• Whether Material fits your design\n" +
                        "• How much control you need\n" +
                        "• Development effort you can invest",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

