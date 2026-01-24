package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Complete Material Design 2 Theme Example
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * This demonstrates a full M2 theme implementation combining:
 * - Custom color palette (light and dark)
 * - Custom typography scale
 * - Custom shapes
 * - Theme composable that switches based on dark mode
 *
 * This is the typical pattern for M2 apps
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * // Define app colors
 * private val AppLightColors = lightColors(
 *     primary = Color(0xFF6200EE),
 *     primaryVariant = Color(0xFF3700B3),
 *     secondary = Color(0xFF03DAC6),
 *     secondaryVariant = Color(0xFF018786),
 *     background = Color(0xFFFFFFFF),
 *     surface = Color(0xFFFFFFFF),
 *     error = Color(0xFFB00020),
 *     onPrimary = Color(0xFFFFFFFF),
 *     onSecondary = Color(0xFF000000),
 *     onBackground = Color(0xFF000000),
 *     onSurface = Color(0xFF000000),
 *     onError = Color(0xFFFFFFFF)
 * )
 *
 * private val AppDarkColors = darkColors(
 *     primary = Color(0xFFBB86FC),
 *     primaryVariant = Color(0xFF3700B3),
 *     secondary = Color(0xFF03DAC6),
 *     background = Color(0xFF121212),
 *     surface = Color(0xFF121212),
 *     error = Color(0xFFCF6679),
 *     onPrimary = Color(0xFF000000),
 *     onSecondary = Color(0xFF000000),
 *     onBackground = Color(0xFFFFFFFF),
 *     onSurface = Color(0xFFFFFFFF)
 * )
 *
 * // Define app typography
 * private val AppTypography = Typography(
 *     h4 = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.Normal),
 *     h5 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Normal),
 *     h6 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
 *     body1 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
 *     body2 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
 *     button = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
 * )
 *
 * // Define app shapes
 * private val AppShapes = Shapes(
 *     small = RoundedCornerShape(4.dp),
 *     medium = RoundedCornerShape(8.dp),
 *     large = RoundedCornerShape(16.dp)
 * )
 *
 * // App theme composable
 * @Composable
 * fun MyM2AppTheme(
 *     darkTheme: Boolean = isSystemInDarkTheme(),
 *     content: @Composable () -> Unit
 * ) {
 *     val colors = if (darkTheme) AppDarkColors else AppLightColors
 *
 *     MaterialTheme(
 *         colors = colors,
 *         typography = AppTypography,
 *         shapes = AppShapes,
 *         content = content
 *     )
 * }
 *
 * // Example app using the theme
 * @Composable
 * fun CompleteM2ThemeExample() {
 *     MyM2AppTheme {
 *         Column(modifier = Modifier.padding(16.dp)) {
 *             Text("My M2 App", style = MaterialTheme.typography.h4)
 *
 *             Card(elevation = 4.dp) {
 *                 Column(modifier = Modifier.padding(16.dp)) {
 *                     Text("Card Title", style = MaterialTheme.typography.h6)
 *                     Text("Custom themed content", style = MaterialTheme.typography.body1)
 *                 }
 *             }
 *
 *             Button(onClick = { }) {
 *                 Text("Themed Button")
 *             }
 *         }
 *     }
 * }
 *
 * M2 to M3 Migration Summary:
 * - Replace lightColors/darkColors with lightColorScheme/darkColorScheme
 * - Update Typography parameter names (h1-h6 to display/headline/title/body/label)
 * - Add extraSmall and extraLarge to Shapes
 * - Change MaterialTheme parameters: colors -> colorScheme
 * - Update component imports from androidx.compose.material to material3
 */
