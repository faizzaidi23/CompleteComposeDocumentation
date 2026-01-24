package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Surface and Cards in Material Design 2
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * Surface:
 * - Base container for Material components
 * - Supports elevation and background color
 * - Clips content to shape
 * - Most M2 components are built on Surface
 *
 * Card:
 * - Specialized Surface for content containers
 * - Default elevation of 1.dp
 * - Default shape is medium from theme
 * - Used for grouping related content
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * @Composable
 * fun M2SurfaceAndCardExample() {
 *     MaterialTheme {
 *         Column(modifier = Modifier.padding(16.dp)) {
 *             // Surface with custom properties
 *             Surface(
 *                 elevation = 4.dp,
 *                 color = MaterialTheme.colors.primary,
 *                 shape = MaterialTheme.shapes.medium
 *             ) {
 *                 Text(
 *                     "Custom Surface",
 *                     modifier = Modifier.padding(16.dp),
 *                     color = MaterialTheme.colors.onPrimary
 *                 )
 *             }
 *
 *             // Card - convenience wrapper around Surface
 *             Card(elevation = 2.dp) {
 *                 Column(modifier = Modifier.padding(16.dp)) {
 *                     Text("Card Title", style = MaterialTheme.typography.h6)
 *                     Text("Card content", style = MaterialTheme.typography.body2)
 *                 }
 *             }
 *
 *             // Card with custom background
 *             Card(
 *                 backgroundColor = MaterialTheme.colors.secondary,
 *                 contentColor = MaterialTheme.colors.onSecondary,
 *                 elevation = 4.dp
 *             ) {
 *                 Text("Custom colored card", modifier = Modifier.padding(16.dp))
 *             }
 *         }
 *     }
 * }
 *
 * M2 to M3 Migration:
 * - Surface: Similar in M3, but with shadowElevation and tonalElevation
 * - Card: Same in M3, but backgroundColor -> containerColor
 */
