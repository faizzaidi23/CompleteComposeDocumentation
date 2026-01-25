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
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 Understanding Preset vs Strategy

 LineBreak.Simple, Heading, Paragraph → Full ready-made configurations (presets)
 strategy = Balanced → One specific knob inside that configuration

 LineBreak is a class with internal properties:
 - strategy: How smart the algorithm is
 - strictness: How strictly it follows typographic rules
 - wordBreak: How aggressively words can be broken

 Presets fill these with reasonable defaults
 .copy() lets you tweak individual properties while keeping others

 Think of it like image compression modes:
 - Simple: Fast render
 - HighQuality: Best readability
 - Balanced: Best symmetry

 Presets choose the whole engine
 Strategy chooses the brain of the engine
*/

private const val SAMPLE_LONG_TEXT = "This is a sample long text that will demonstrate different line breaking strategies in Jetpack Compose"

@Composable
fun CustomLineBreakExample() {
    Column {

        /*
         Strategy.Balanced

         Goal: Make all lines similar in length, even if it slightly hurts natural word grouping

         Tries hard to avoid:
         - Very short last line
         - Very long first line
         - Ugly ragged right edge

         Best for: Narrow screens, smart watches, compact cards where symmetry matters

         Tradeoff: Readability may slightly drop because it prioritizes symmetry over natural flow
        */
        Text(
            text = "Balanced:",
            fontSize = 14.sp
        )

        /*
         LineBreak.Paragraph.copy(strategy = Balanced)

         This means:
         - Keep Paragraph defaults for strictness, wordBreak, hyphenation
         - Only swap the algorithm behavior to Balanced

         Why use .copy() instead of new object?
         - Presets already have good values for strictness, wordBreak, platform defaults
         - You only want to change one knob safely

         Pattern: Start with preset → tweak via copy()
        */
        val smallScreenAdaptedParagraph = LineBreak.Paragraph.copy(
            strategy = LineBreak.Strategy.Balanced
        )
        Text(
            text = SAMPLE_LONG_TEXT,
            modifier = Modifier
                .width(200.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(
                lineBreak = smallScreenAdaptedParagraph
            )
        )

        /*
         Default (no custom strategy)

         Uses system's default line breaking algorithm
         For comparison to see the difference
        */
        Text(
            text = "Default:",
            fontSize = 14.sp
        )
        Text(
            text = SAMPLE_LONG_TEXT,
            modifier = Modifier
                .width(200.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            style = TextStyle.Default
        )

        /*
         Strategy.HighQuality (default for Paragraph preset)

         Goal: Best readability through multi-line optimization

         What it does:
         - Better aesthetics
         - Avoids awkward ragged edges
         - Considers future lines when breaking
         - Uses more CPU

         Best for: Articles, reading screens, blogs, documentation, terms & conditions

         Note: At width = 200.dp with English text, Balanced and HighQuality may look similar
         You'll see bigger differences at narrower widths (120.dp) or with hyphenation enabled
        */
        Text(
            text = "HighQuality:",
            fontSize = 14.sp
        )
        Text(
            text = SAMPLE_LONG_TEXT,
            modifier = Modifier
                .width(200.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(
                /*
                 Configuration hierarchy:
                 TextStyle.Default
                   → copy(lineBreak = customLineBreak)
                         customLineBreak = LineBreak.Paragraph
                             → copy(strategy = HighQuality)

                 You're building configuration objects step-by-step
                */
                lineBreak = LineBreak.Paragraph.copy(
                    strategy = LineBreak.Strategy.HighQuality
                )
            )
        )
    }
}
