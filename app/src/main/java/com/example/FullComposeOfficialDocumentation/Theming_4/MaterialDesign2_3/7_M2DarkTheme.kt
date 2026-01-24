package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Dark Theme in Material Design 2
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * M2 provides built-in dark theme support through darkColors()
 *
 * Key differences in dark theme:
 * - Background and surface colors are dark
 * - Primary colors adjusted for dark backgrounds
 * - Elevation overlays: white tint added based on elevation
 * - Text colors inverted (onPrimary, onSurface, etc.)
 *
 * Elevation overlay calculation:
 * - 1.dp = 5% white overlay
 * - 4.dp = 9% white overlay
 * - 8.dp = 12% white overlay
 * - Higher elevation = more white tint
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * private val LightThemeColors = lightColors(
 *     primary = Color(0xFF6200EE),
 *     primaryVariant = Color(0xFF3700B3),
 *     secondary = Color(0xFF03DAC6),
 *     background = Color(0xFFFFFFFF),
 *     surface = Color(0xFFFFFFFF),
 *     onPrimary = Color(0xFFFFFFFF),
 *     onBackground = Color(0xFF000000)
 * )
 *
 * private val DarkThemeColors = darkColors(
 *     primary = Color(0xFFBB86FC),
 *     primaryVariant = Color(0xFF3700B3),
 *     secondary = Color(0xFF03DAC6),
 *     background = Color(0xFF121212),
 *     surface = Color(0xFF121212),
 *     onPrimary = Color(0xFF000000),
 *     onBackground = Color(0xFFFFFFFF)
 * )
 *
 * @Composable
 * fun M2DarkThemeExample(darkTheme: Boolean = isSystemInDarkTheme()) {
 *     val colors = if (darkTheme) DarkThemeColors else LightThemeColors
 *
 *     MaterialTheme(colors = colors) {
 *         Column {
 *             Text("Current theme: ${if (darkTheme) "Dark" else "Light"}")
 *             Text("isLight: ${colors.isLight}")
 *
 *             Card(elevation = 1.dp) {
 *                 Text("1.dp - Notice white tint in dark theme")
 *             }
 *             Card(elevation = 8.dp) {
 *                 Text("8.dp - More white tint")
 *             }
 *         }
 *     }
 * }
 *
 * M2 to M3 Migration:
 * - M2 elevation overlays -> M3 tonal elevation
 * - M2 Colors.isLight -> M3 track in theme composable
 * - M2 ElevationOverlay -> M3 not needed (tonal elevation handles it)
 */
