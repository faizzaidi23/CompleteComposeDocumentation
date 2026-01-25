package com.example.FullComposeOfficialDocumentation.Text_Typography_5.ParagraphStyling_2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

/*
 * Demonstrates using ParagraphStyle to apply styles to a paragraph
 *
 * Key concepts:
 * - ParagraphStyle affects the entire paragraph (text separated by \n)
 * - SpanStyle affects only specific portions of text
 * - ParagraphStyle includes: lineHeight, textAlign, textDirection, lineHeightStyle, textIndent
 * - Once text is marked with ParagraphStyle, it's treated as a separate paragraph
 *   (as if it has line feeds at the beginning and end)
 */
@Composable
fun ParagraphStyleExample() {
    Text(
        /*
         * buildAnnotatedString - Type-safe builder for creating styled text
         * Allows mixing different text styles within a single Text composable
         * More efficient than using multiple Text composables
         */
        buildAnnotatedString {
            /*
             * withStyle(ParagraphStyle) - Applies paragraph-level styling
             * Everything inside this block is treated as one paragraph
             * The lineHeight of 30.sp applies to all lines in this paragraph
             */
            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                /*
                 * withStyle(SpanStyle) - Applies character-level styling
                 * SpanStyle can change: color, fontSize, fontWeight, fontStyle, etc.
                 * This only affects "Hello\n" - making it blue
                 */
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("Hello\n")
                }
                /*
                 * Another SpanStyle for different text
                 * This makes "World\n" bold and red
                 * Both SpanStyles share the same ParagraphStyle (lineHeight = 30.sp)
                 */
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                ) {
                    append("World\n")
                }
                /*
                 * Text without explicit SpanStyle
                 * Uses default styling but still inherits the ParagraphStyle
                 * So "Compose" also has lineHeight = 30.sp
                 */
                append("Compose")
            }
        }
    )
}
