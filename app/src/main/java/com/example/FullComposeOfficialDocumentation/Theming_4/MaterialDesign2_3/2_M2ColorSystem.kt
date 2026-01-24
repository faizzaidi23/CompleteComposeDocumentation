package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Material Design 2 Color System
 *
 * NOTE: This is a conceptual reference. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * M2 has 4 key colors:
 * - Primary: Main brand color
 * - PrimaryVariant: Darker/lighter variation of primary
 * - Secondary: Accent color (also called accent)
 * - SecondaryVariant: Variation of secondary
 *
 * Additional colors:
 * - Background: Behind scrollable content
 * - Surface: Cards, sheets, menus
 * - Error: Error states
 * - OnPrimary, OnSecondary, OnBackground, OnSurface, OnError: Text/icons on colored backgrounds
 *
 * Each Colors instance has an isLight boolean to check theme mode
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * // Light colors definition
 * private val M2LightColors = lightColors(
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
 * // Dark colors definition
 * private val M2DarkColors = darkColors(
 *     primary = Color(0xFFBB86FC),
 *     primaryVariant = Color(0xFF3700B3),
 *     secondary = Color(0xFF03DAC6),
 *     background = Color(0xFF121212),
 *     surface = Color(0xFF121212),
 *     onPrimary = Color(0xFF000000),
 *     onBackground = Color(0xFFFFFFFF)
 * )
 *
 * // Usage in MaterialTheme
 * @Composable
 * fun M2ColorExample(darkTheme: Boolean = false) {
 *     val colors = if (darkTheme) M2DarkColors else M2LightColors
 *
 *     MaterialTheme(colors = colors) {
 *         Box(
 *             modifier = Modifier.background(MaterialTheme.colors.primary),
 *             contentAlignment = Alignment.Center
 *         ) {
 *             Text(
 *                 text = "Primary Color",
 *                 color = MaterialTheme.colors.onPrimary
 *             )
 *         }
 *     }
 * }
 *
 * Key difference from M3:
 * - M2: 4 key colors (primary, primaryVariant, secondary, secondaryVariant)
 * - M3: 5 key colors (primary, secondary, tertiary, error, neutral)
 * - M2: Has isLight property on Colors
 * - M3: No isLight on ColorScheme
 */
