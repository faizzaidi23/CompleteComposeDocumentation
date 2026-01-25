package com.example.FullComposeOfficialDocumentation.Text_Typography_5.ParagraphStyling_2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em

// Demonstrates lineHeight configuration and LineHeightStyle API
// LineHeightStyle controls alignment and trimming of extra space
@Composable
fun LineHeightAndPaddingExample() {
    val text = "This is sample text to demonstrate line height adjustments"

    Column {
        // Basic lineHeight configuration using em units
        Text(
            text = text,
            // LocalTextStyle.current - Gets the current TextStyle from Material theme
            // This is a CompositionLocal that provides the default text style
            // .current - Retrieves the value provided at this point in the composition tree
            // .merge() - Combines the current style with our custom style
            // Values we provide override the defaults, while keeping other properties
            style = LocalTextStyle.current.merge(
                TextStyle(
                    // lineHeight - Controls the vertical space between lines
                    // 2.5.em - Uses "em" units (relative to font size) for better scaling
                    lineHeight = 2.5.em,
                    // LineHeightStyle - Fine-tunes how lineHeight is applied
                    lineHeightStyle = LineHeightStyle(
                        // Alignment.Center - Vertically centers text within line height space
                        // Other options: Top, Bottom, Proportional
                        alignment = LineHeightStyle.Alignment.Center,
                        // Trim.None - Keeps extra padding at top of first line and bottom of last line
                        // Other options: Both, FirstLineTop, LastLineBottom
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )
        )
    }
}

// Shows different LineHeightStyle.Trim options
@Composable
fun LineHeightTrimExamples() {
    val text = "First line\nSecond line\nThird line"

    Column {
        // Trim.None - keeps all extra space
        Text(
            text = "Trim.None:\n$text",
            style = TextStyle(
                // 2.em - Line height is 2 times the font size
                lineHeight = 2.em,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    // Trim.None - Preserves all extra vertical space
                    // Useful when you need consistent spacing around text blocks
                    trim = LineHeightStyle.Trim.None
                )
            )
        )

        // Trim.Both - removes extra space from first and last lines
        Text(
            text = "Trim.Both:\n$text",
            style = TextStyle(
                lineHeight = 2.em,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    // Trim.Both - Removes padding from top of first line AND bottom of last line
                    // Makes text block more compact and aligned with other elements
                    trim = LineHeightStyle.Trim.Both
                )
            )
        )

        // Trim.FirstLineTop - removes extra space from first line only
        Text(
            text = "Trim.FirstLineTop:\n$text",
            style = TextStyle(
                lineHeight = 2.em,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    // Trim.FirstLineTop - Only removes padding from top of first line
                    // Useful when aligning first line with other UI elements
                    trim = LineHeightStyle.Trim.FirstLineTop
                )
            )
        )

        // Trim.LastLineBottom - removes extra space from last line only
        Text(
            text = "Trim.LastLineBottom:\n$text",
            style = TextStyle(
                lineHeight = 2.em,
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    // Trim.LastLineBottom - Only removes padding from bottom of last line
                    // Useful when you need tight spacing after text block
                    trim = LineHeightStyle.Trim.LastLineBottom
                )
            )
        )
    }
}
