package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Content Alpha and Emphasis in M2
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * M2 uses ContentAlpha to create visual hierarchy and emphasis
 * Three alpha levels:
 * - ContentAlpha.high (1.0f or 0.87f): High emphasis
 * - ContentAlpha.medium (0.6f or 0.74f): Medium emphasis
 * - ContentAlpha.disabled (0.38f): Disabled state
 *
 * Applied using CompositionLocalProvider with LocalContentAlpha
 * This automatically applies alpha to all child content (text, icons)
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * @Composable
 * fun M2ContentAlphaExample() {
 *     MaterialTheme {
 *         Column {
 *             // High emphasis - default, most important content
 *             CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
 *                 Text("High Emphasis")
 *                 Icon(Icons.Default.Favorite, contentDescription = null)
 *             }
 *
 *             // Medium emphasis - secondary content
 *             CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
 *                 Text("Medium Emphasis")
 *                 Icon(Icons.Default.Favorite, contentDescription = null)
 *             }
 *
 *             // Disabled emphasis - disabled state
 *             CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
 *                 Text("Disabled")
 *                 Icon(Icons.Default.Favorite, contentDescription = null)
 *             }
 *         }
 *     }
 * }
 *
 * Practical example:
 * @Composable
 * fun ArticleItem() {
 *     Column {
 *         // Title - high emphasis
 *         CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
 *             Text("Article Title", style = MaterialTheme.typography.h6)
 *         }
 *
 *         // Date - medium emphasis
 *         CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
 *             Text("Published on Jan 24, 2026", style = MaterialTheme.typography.caption)
 *         }
 *     }
 * }
 *
 * M2 to M3 Migration:
 * - M2 ContentAlpha.high -> M3 onSurface color + FontWeight.Medium-Black
 * - M2 ContentAlpha.medium -> M3 onSurfaceVariant color + FontWeight.Thin-Normal
 * - M2 ContentAlpha.disabled -> M3 onSurface.copy(alpha = 0.38f)
 */
