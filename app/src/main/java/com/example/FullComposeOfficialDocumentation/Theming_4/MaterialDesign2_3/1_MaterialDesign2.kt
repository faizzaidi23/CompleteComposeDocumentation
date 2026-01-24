package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Material Design 2 Overview
 *
 * NOTE: This is a conceptual reference. Material Design 2 requires the dependency:
 * implementation "androidx.compose.material:material:$version"
 *
 * This project uses Material 3, so M2 code won't compile.
 * These files serve as documentation for M2 concepts.
 *
 * Material Design 2 (M2) is the previous generation of Material Design
 * Package: androidx.compose.material
 *
 * Core theming subsystems:
 * - Colors: Primary, secondary, background, surface, error colors
 * - Typography: Text styles from h1-h6, body1, body2, etc.
 * - Shapes: Small, medium, large shapes
 *
 * Key features:
 * - ContentAlpha for emphasis
 * - Elevation overlays in dark theme
 * - Ripple effects
 * - Component states
 *
 * Example M2 code structure:
 *
 * @Composable
 * fun MaterialDesign2Basics() {
 *     MaterialTheme(
 *         colors = lightColors(...),
 *         typography = Typography(...),
 *         shapes = Shapes(...)
 *     ) {
 *         Column {
 *             Text("Material Design 2", style = MaterialTheme.typography.h4)
 *             Text("This uses M2 theming", style = MaterialTheme.typography.body1)
 *         }
 *     }
 * }
 *
 * To use M2 in your project:
 * 1. Add dependency: implementation "androidx.compose.material:material:1.6.0"
 * 2. Import from androidx.compose.material (not material3)
 * 3. Use MaterialTheme with colors, typography, shapes parameters
 */
