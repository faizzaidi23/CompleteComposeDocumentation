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
 LineBreak controls WHERE the line breaks happen when text is too long

 Think of it like packing boxes:
 - Simple: Fill until full, move to next
 - Heading: Avoid awkward single items
 - Paragraph: Rearrange for balanced appearance

 This is layout intelligence, not visual styling
 width(130.dp) forces line breaks so you can see the difference
*/

private const val SAMPLE_LONG_TEXT = "This is a sample long text that will demonstrate different line breaking strategies in Jetpack Compose"

@Composable
fun LineBreakPresetsExample() {
    Column {

        /*
         LineBreak.Simple

         Decision: "Does this word fit? No → break here"

         Greedy algorithm - no lookahead, no optimization
         Fastest performance but may create uneven lines

         Use for: TextField, live typing, LazyColumn with text

         Like filling water bottles on a conveyor - when full, move to next
        */
        Text(
            text = "Simple:\n$SAMPLE_LONG_TEXT",
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(
                lineBreak = LineBreak.Simple
            )
        )

        /*
         LineBreak.Heading

         Decision: "Try to avoid single-word orphan lines"

         Looks slightly ahead to prevent awkward breaks
         Optimized for 1-3 lines of text
         Good balance between speed and appearance

         Use for: Titles, card headings, AppBar titles

         Like arranging shelf items - glance ahead to avoid one item alone
        */
        Text(
            text = "Heading:\n$SAMPLE_LONG_TEXT",
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            style = TextStyle.Default.copy(
                lineBreak = LineBreak.Heading
            )
        )

        /*
         LineBreak.Paragraph

         Decision: "What will next 2-3 lines look like? Can I balance them?"

         Analyzes multiple lines together for balanced appearance
         Slowest but produces professional typography quality
         May trigger hyphenation if enabled

         Use for: Articles, blog content, long readable text

         Like a book designer - rearranges across lines for best result
        */
        Text(
            text = "Paragraph:\n$SAMPLE_LONG_TEXT",
            modifier = Modifier
                .width(130.dp)
                .border(BorderStroke(1.dp, Color.Gray)),
            fontSize = 14.sp,
            /*
            below creates a new TextStyle object
            same as default for everything
            except lineBreak which we override
            */
            style = TextStyle.Default.copy( // because TextStyle is immutable we cannot directly modify it, we  have to copy the things like we are doing below
                lineBreak = LineBreak.Paragraph
            )
        )
    }
}
