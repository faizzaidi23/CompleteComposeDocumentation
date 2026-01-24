package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Material Design 2 Typography
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * M2 Typography scale includes 13 text styles:
 * - h1 to h6: Headlines (largest to smallest)
 * - subtitle1, subtitle2: Subtitles
 * - body1, body2: Body text
 * - button: Button text
 * - caption: Small text
 * - overline: Overline text
 *
 * Features:
 * - defaultFontFamily parameter to set font for all styles
 * - Each style is a TextStyle with font, size, weight, etc.
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * // Typography definition
 * private val M2CustomTypography = Typography(
 *     h1 = TextStyle(fontWeight = FontWeight.Light, fontSize = 96.sp),
 *     h2 = TextStyle(fontWeight = FontWeight.Light, fontSize = 60.sp),
 *     h3 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 48.sp),
 *     h4 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 34.sp),
 *     h5 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 24.sp),
 *     h6 = TextStyle(fontWeight = FontWeight.Medium, fontSize = 20.sp),
 *     subtitle1 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
 *     subtitle2 = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp),
 *     body1 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
 *     body2 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
 *     button = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp),
 *     caption = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp),
 *     overline = TextStyle(fontWeight = FontWeight.Normal, fontSize = 10.sp)
 * )
 *
 * // Usage
 * @Composable
 * fun M2TypographyExample() {
 *     MaterialTheme(typography = M2CustomTypography) {
 *         Column {
 *             Text("H1 Headline", style = MaterialTheme.typography.h1)
 *             Text("H4 Headline", style = MaterialTheme.typography.h4)
 *             Text("Body 1 text", style = MaterialTheme.typography.body1)
 *             Text("Caption text", style = MaterialTheme.typography.caption)
 *         }
 *     }
 * }
 *
 * M2 to M3 Typography Mapping:
 * h1 -> displayLarge
 * h2 -> displayMedium
 * h3 -> displaySmall
 * h4 -> headlineMedium
 * h5 -> headlineSmall
 * h6 -> titleLarge
 * subtitle1 -> titleMedium
 * subtitle2 -> titleSmall
 * body1 -> bodyLarge
 * body2 -> bodyMedium
 * caption -> bodySmall
 * button -> labelLarge
 * overline -> labelSmall
 */
