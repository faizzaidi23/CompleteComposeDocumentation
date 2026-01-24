package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Material Design 2 Shapes
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * M2 has 3 shape categories:
 * - Small: For buttons, chips, text fields
 * - Medium: For cards, dialogs
 * - Large: For navigation drawers, bottom sheets
 *
 * Default shapes are all RoundedCornerShape(4.dp)
 * You can customize with:
 * - RoundedCornerShape: Rounded corners
 * - CutCornerShape: Cut corners
 * - RectangleShape: No shaping
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * // Shapes definition
 * private val M2CustomShapes = Shapes(
 *     small = RoundedCornerShape(4.dp),
 *     medium = RoundedCornerShape(8.dp),
 *     large = RoundedCornerShape(16.dp)
 * )
 *
 * // Usage
 * @Composable
 * fun M2ShapesExample() {
 *     MaterialTheme(shapes = M2CustomShapes) {
 *         Column {
 *             Card(shape = MaterialTheme.shapes.small) {
 *                 Text("Small shape (4.dp)")
 *             }
 *             Card(shape = MaterialTheme.shapes.medium) {
 *                 Text("Medium shape (8.dp)")
 *             }
 *             Card(shape = MaterialTheme.shapes.large) {
 *                 Text("Large shape (16.dp)")
 *             }
 *             Card(shape = CutCornerShape(8.dp)) {
 *                 Text("Custom cut corner")
 *             }
 *         }
 *     }
 * }
 *
 * M2 to M3 Shape Mapping:
 * small -> small (same name)
 * medium -> medium (same name)
 * large -> large (same name)
 * N/A -> extraSmall (new in M3)
 * N/A -> extraLarge (new in M3)
 */
