package com.example.FullComposeOfficialDocumentation.Text_Typography_5.ParagraphStyling_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
 * Demonstrates horizontal text alignment within a Text composable
 * TextAlign controls how text is positioned within its container width
 */
@Composable
fun CenterText() {
    Text(
        "Hello World",
        /*
         * TextAlign.Center - Horizontally centers the text
         * The text will be centered within the 150.dp width
         */
        textAlign = TextAlign.Center,
        modifier = Modifier.width(150.dp)
    )
}

/*
 * Shows different text alignment options
 * Prefer TextAlign.Start/End over Left/Right for proper RTL support
 *
 * RTL (Right-to-Left) languages like Arabic and Hebrew need special handling
 * Start/End automatically adjust based on the text direction
 */
@Composable
fun TextAlignmentExamples() {
    Column {
        /*
         * TextAlign.Left
         * - Always aligns text to the left edge
         * - Does NOT adapt to RTL languages
         * - Use only when you specifically need left alignment regardless of language
         */
        Text(
            "Left aligned text",
            textAlign = TextAlign.Left,
            modifier = Modifier.width(200.dp)
        )

        /*
         * TextAlign.Center
         * - Centers text horizontally
         * - Works the same for both LTR and RTL languages
         * - Good for titles and centered content
         */
        Text(
            "Center aligned text",
            textAlign = TextAlign.Center,
            modifier = Modifier.width(200.dp)
        )

        /*
         * TextAlign.Right
         * - Always aligns text to the right edge
         * - Does NOT adapt to RTL languages
         * - Use only when you specifically need right alignment regardless of language
         */
        Text(
            "Right aligned text",
            textAlign = TextAlign.Right,
            modifier = Modifier.width(200.dp)
        )

        /*
         * TextAlign.Start (RECOMMENDED)
         * - Aligns to the start of text direction
         * - For LTR (English, French): aligns left
         * - For RTL (Arabic, Hebrew): aligns right
         * - Automatically adapts based on locale
         * - This is the recommended approach for most text
         */
        Text(
            "Start aligned (recommended)",
            textAlign = TextAlign.Start,
            modifier = Modifier.width(200.dp)
        )

        /*
         * TextAlign.End (RECOMMENDED)
         * - Aligns to the end of text direction
         * - For LTR (English, French): aligns right
         * - For RTL (Arabic, Hebrew): aligns left
         * - Automatically adapts based on locale
         * - Use this instead of TextAlign.Right for proper internationalization
         */
        Text(
            "End aligned (recommended)",
            textAlign = TextAlign.End,
            modifier = Modifier.width(200.dp)
        )
    }
}
