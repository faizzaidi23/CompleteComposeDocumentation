package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Elevation in Material Design 2
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * M2 uses elevation to create depth and hierarchy
 * Elevation is measured in dp (density-independent pixels)
 *
 * How elevation works:
 * - Creates shadows under components
 * - In dark theme: adds white overlay (elevation overlay)
 * - Higher elevation = component appears closer to user
 *
 * Standard elevation levels:
 * - 0.dp: Flat on surface
 * - 1.dp: Cards, buttons
 * - 2.dp: Raised buttons
 * - 4.dp: Navigation drawer
 * - 6.dp: Floating action button
 * - 8.dp: App bar, bottom navigation
 * - 16.dp: Modal dialogs
 * - 24.dp: Navigation drawer overlay
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * @Composable
 * fun M2ElevationExample() {
 *     MaterialTheme {
 *         Column(modifier = Modifier.padding(16.dp)) {
 *             // No elevation
 *             Surface(elevation = 0.dp) {
 *                 Text("0.dp - Flat", modifier = Modifier.padding(16.dp))
 *             }
 *
 *             // Card elevation
 *             Card(elevation = 1.dp) {
 *                 Text("1.dp - Card", modifier = Modifier.padding(16.dp))
 *             }
 *
 *             // Raised elevation
 *             Card(elevation = 4.dp) {
 *                 Text("4.dp - Raised", modifier = Modifier.padding(16.dp))
 *             }
 *
 *             // High elevation
 *             Card(elevation = 8.dp) {
 *                 Text("8.dp - High", modifier = Modifier.padding(16.dp))
 *             }
 *
 *             // Dialog elevation
 *             Card(elevation = 16.dp) {
 *                 Text("16.dp - Dialog", modifier = Modifier.padding(16.dp))
 *             }
 *         }
 *     }
 * }
 *
 * M2 to M3 Migration:
 * - M2: elevation parameter only
 * - M3: shadowElevation and tonalElevation parameters
 * - M3 tonal elevation replaces M2 elevation overlays in dark theme
 */
