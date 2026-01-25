package com.example.FullComposeOfficialDocumentation.Text_Typography_5.ParagraphStyling_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * Sample text with long words to demonstrate hyphenation
 * "extraordinarily" is a good example word that benefits from hyphenation
 */
private const val SAMPLE_LONG_TEXT = "This is a sample long text with extraordinarily long words that demonstrates hyphenation functionality"

/*
 * Demonstrates hyphenation support using Hyphens API
 *
 * Hyphenation = inserting a dash (-) to split a word across two lines
 * Example: "extra-
 *           ordinarily"
 *
 * By default, hyphenation is disabled (Hyphens.None)
 * Use Hyphens.Auto to enable automatic hyphenation
 *
 * Hyphenation only works when:
 * 1. A word doesn't fit on a line
 * 2. The appropriate locale is set on your device
 * 3. System has hyphenation dictionaries for that locale
 */
@Composable
fun HyphenationExample() {
    Column {
        /*
         * Hyphens.None (Default)
         * - Hyphenation is disabled
         * - Words wrap to the next line without breaking
         * - May result in uneven line lengths
         * - Faster rendering performance
         * - Use when hyphenation is not needed or desired
         */
        Text(
            text = "Hyphens.None:",
            fontSize = 14.sp
        )
        Text(
            text = SAMPLE_LONG_TEXT,
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(
                /*
                 * LineBreak.Paragraph provides high-quality line breaking
                 * Even without hyphenation, it optimizes line breaks
                 */
                lineBreak = LineBreak.Paragraph,
                /*
                 * Hyphens.None explicitly disables hyphenation
                 * This is the default, but shown here for clarity
                 */
                hyphens = Hyphens.None
            )
        )

        /*
         * Hyphens.Auto (Enabled)
         * - Automatically hyphenates words when appropriate
         * - Inserts hyphens between syllables at valid break points
         * - Creates more balanced line lengths
         * - Improves text density in narrow columns
         * - Requires device locale to have hyphenation dictionaries
         * - Slightly slower than Hyphens.None
         */
        Text(
            text = "Hyphens.Auto:",
            fontSize = 14.sp
        )
        Text(
            text = SAMPLE_LONG_TEXT,
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(
                lineBreak = LineBreak.Paragraph,
                /*
                 * Hyphens.Auto enables automatic hyphenation
                 * Words like "extraordinarily" may appear as:
                 * "extraordi-
                 *  narily"
                 */
                hyphens = Hyphens.Auto
            )
        )
    }
}

/*
 * Demonstrates how hyphenation behaves with different LineBreak strategies
 *
 * Important: Hyphenation behavior varies based on the line breaking strategy
 * - Simple strategy: Only hyphenates if a word doesn't fit on entire line
 * - Paragraph strategy: Uses hyphenation to optimize overall readability
 */
@Composable
fun HyphenationWithDifferentStrategies() {
    /*
     * Text with long words to demonstrate hyphenation
     * "Internationalization" is 20 characters - perfect for testing
     */
    val longWord = "Internationalization and localization are important"

    Column {
        /*
         * LineBreak.Simple + Hyphens.Auto
         *
         * Simple strategy only hyphenates when absolutely necessary:
         * - Only if the word is longer than the entire line width
         * - No optimization for balanced line lengths
         * - Minimal performance impact
         *
         * Example: If "Internationalization" doesn't fit on one line,
         * it will be hyphenated. But shorter words won't be.
         */
        Text(
            text = "Simple + Hyphens.Auto:",
            fontSize = 14.sp
        )
        Text(
            text = longWord,
            modifier = Modifier.width(120.dp),
            fontSize = 14.sp,
            style = TextStyle(
                lineBreak = LineBreak.Simple,
                hyphens = Hyphens.Auto
            )
        )

        /*
         * LineBreak.Paragraph + Hyphens.Auto
         *
         * Paragraph strategy uses hyphenation strategically:
         * - Considers overall paragraph appearance
         * - May hyphenate even shorter words if it improves readability
         * - Creates more balanced line lengths
         * - Better visual aesthetics
         * - Higher quality results but more processing
         *
         * Example: May hyphenate "localization" as "localiza-tion"
         * even though it could fit on the next line, if it creates
         * better overall balance in the paragraph.
         */
        Text(
            text = "Paragraph + Hyphens.Auto:",
            fontSize = 14.sp
        )
        Text(
            text = longWord,
            modifier = Modifier.width(120.dp),
            fontSize = 14.sp,
            style = TextStyle(
                /*
                 * Paragraph strategy + Auto hyphens = best readability
                 * This combination produces the most balanced, readable text
                 * Recommended for body text in narrow columns
                 */
                lineBreak = LineBreak.Paragraph,
                hyphens = Hyphens.Auto
            )
        )
    }
}
