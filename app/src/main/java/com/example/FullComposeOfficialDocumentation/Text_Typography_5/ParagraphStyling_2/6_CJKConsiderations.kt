package com.example.FullComposeOfficialDocumentation.Text_Typography_5.ParagraphStyling_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * Demonstrates CJK-specific line breaking options
 *
 * CJK = Chinese, Japanese, Korean languages
 * These languages have different line breaking rules than Latin alphabets
 *
 * Two main parameters for CJK customization:
 * 1. Strictness - How strict the line breaking rules are
 * 2. WordBreak - How to break within words (Default vs Phrase-based)
 *
 * Note: These settings are designed for CJK languages
 * You may not see the intended effect when using non-CJK text
 * Line-breaking rules are locale-based
 */
@Composable
fun CJKLineBreakExample() {
    /*
     * Complete LineBreak configuration for Japanese text
     * This example shows all three parameters working together:
     *
     * strategy: LineBreak.Strategy.HighQuality
     * - Uses the best algorithm for readable text
     *
     * strictness: LineBreak.Strictness.Strict
     * - Most stringent line breaking rules
     * - Follows the strictest typographic conventions
     *
     * wordBreak: LineBreak.WordBreak.Phrase
     * - Breaks lines based on phrases rather than just words
     * - Helps maintain meaning and readability in CJK text
     *
     * Japanese text: "あなたに寄り添う最先端のテクノロジー。"
     * Translation: "Technology that stays close to you."
     */
    val customTitleLineBreak = LineBreak(
        strategy = LineBreak.Strategy.HighQuality,
        strictness = LineBreak.Strictness.Strict,
        wordBreak = LineBreak.WordBreak.Phrase
    )

    /*
     * To properly test this example:
     * 1. Go to device Settings
     * 2. Navigate to System > Languages & Input > Languages
     * 3. Add Japanese locale
     * The line breaking behavior will adapt based on the locale
     */
    Text(
        text = "あなたに寄り添う最先端のテクノロジー。",
        modifier = Modifier.width(250.dp),
        fontSize = 14.sp,
        style = TextStyle.Default.copy(
            lineBreak = customTitleLineBreak
        )
    )
}

/*
 * Shows different Strictness levels for CJK text
 *
 * Strictness controls how strictly line breaking rules are applied
 * The default recommended value varies based on locale
 */
@Composable
fun StrictnessExamples() {
    /*
     * Japanese sample text
     * Translation: "This is a Japanese text sample."
     */
    val japaneseText = "これは日本語のテキストサンプルです。"

    Column {
        /*
         * LineBreak.Strictness.Loose
         * - The least restrictive rules
         * - Allows more flexibility in where lines can break
         * - Suitable for short lines where space is limited
         * - May break at points that are less typographically ideal
         * - Good for responsive layouts with narrow containers
         */
        Text(
            text = "Loose: $japaneseText",
            modifier = Modifier.width(150.dp),
            style = TextStyle(
                lineBreak = LineBreak.Paragraph.copy(
                    strictness = LineBreak.Strictness.Loose
                )
            )
        )

        /*
         * LineBreak.Strictness.Normal
         * - The most common rules for line breaking
         * - Balanced between flexibility and typographic quality
         * - Default for most situations
         * - Good for general body text
         */
        Text(
            text = "Normal: $japaneseText",
            modifier = Modifier.width(150.dp),
            style = TextStyle(
                lineBreak = LineBreak.Paragraph.copy(
                    strictness = LineBreak.Strictness.Normal
                )
            )
        )

        /*
         * LineBreak.Strictness.Strict
         * - The most stringent rules for line breaking
         * - Follows the strictest typographic conventions
         * - May require more horizontal space
         * - Best for high-quality typography
         * - Use for titles, headers, or premium content
         */
        Text(
            text = "Strict: $japaneseText",
            modifier = Modifier.width(150.dp),
            style = TextStyle(
                lineBreak = LineBreak.Paragraph.copy(
                    strictness = LineBreak.Strictness.Strict
                )
            )
        )
    }
}
