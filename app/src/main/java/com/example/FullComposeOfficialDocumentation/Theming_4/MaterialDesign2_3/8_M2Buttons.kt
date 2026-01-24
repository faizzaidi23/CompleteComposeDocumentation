package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Material Design 2 Buttons
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * M2 provides several button types:
 * - Button: Filled button with primary color (high emphasis)
 * - OutlinedButton: Button with border (medium emphasis)
 * - TextButton: Flat button without background (low emphasis)
 * - IconButton: Button with only an icon
 * - FloatingActionButton (FAB): Circular button for primary action
 * - ExtendedFloatingActionButton: FAB with text label
 *
 * Button hierarchy (high to low emphasis):
 * FAB > Button > OutlinedButton > TextButton
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * @Composable
 * fun M2ButtonsExample() {
 *     MaterialTheme {
 *         Column(modifier = Modifier.padding(16.dp)) {
 *             // Filled button - high emphasis
 *             Button(onClick = { }) {
 *                 Text("Filled Button")
 *             }
 *
 *             // Outlined button - medium emphasis
 *             OutlinedButton(onClick = { }) {
 *                 Text("Outlined Button")
 *             }
 *
 *             // Text button - low emphasis
 *             TextButton(onClick = { }) {
 *                 Text("Text Button")
 *             }
 *
 *             // Icon button
 *             IconButton(onClick = { }) {
 *                 Icon(Icons.Default.Favorite, contentDescription = "Favorite")
 *             }
 *
 *             // Floating action button
 *             FloatingActionButton(onClick = { }) {
 *                 Icon(Icons.Default.Add, contentDescription = "Add")
 *             }
 *
 *             // Extended FAB
 *             ExtendedFloatingActionButton(
 *                 text = { Text("Extended FAB") },
 *                 icon = { Icon(Icons.Default.Add, contentDescription = null) },
 *                 onClick = { }
 *             )
 *         }
 *     }
 * }
 *
 * M2 to M3 Migration:
 * - Most button types have the same names in M3
 * - M3 adds FilledTonalButton for medium emphasis
 * - M3 adds ElevatedButton variant
 * - APIs are similar, just different package imports
 */
