package com.example.FullComposeOfficialDocumentation.Text_Typography_5.MigrateToStateBased_9

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

/*
 Complex State Update - Markdown Editor Example

 Scenario: Button adds **bold** markers around selected text
 Also maintains selection position after insertion

 Old approach: Calculate new TextFieldValue with text splicing and selection adjustment
 New approach: Use TextFieldState.edit with TextFieldBuffer

 This shows the power of edit {} for complex text manipulations
*/

/*
 VALUE-BASED - Markdown bold button (Current approach)

 Uses TextFieldValue instead of String to track selection
 Manually calculates:
 - Where to insert ** markers
 - How to splice the text
 - New selection position
*/
@Composable
fun OldTextFieldAddMarkdownEmphasis() {
    var markdownState by remember { mutableStateOf(TextFieldValue()) }

    Button(onClick = {
        // Add ** decorations around current selection
        // Also preserve the selection position
        markdownState = with(markdownState) {
            copy(
                text = buildString {
                    // Text before selection
                    append(text.take(selection.min))
                    // Opening **
                    append("**")
                    // Selected text
                    append(text.substring(selection.min, selection.max))
                    // Closing **
                    append("**")
                    // Text after selection
                    append(text.drop(selection.max))
                },
                // Adjust selection to account for the ** we inserted
                // Move both start and end forward by 2 positions
                selection = TextRange(selection.min + 2, selection.max + 2)
            )
        }
    }) {
        Text("Bold")
    }

    TextField(
        value = markdownState,
        onValueChange = { markdownState = it },
        maxLines = 10
    )
}

/*
 STATE-BASED - Markdown bold button (Future approach)

 Migration steps:
 1. Replace TextFieldValue with rememberTextFieldState()
 2. Replace maxLines with lineLimits
 3. Use edit {} with TextFieldBuffer for manipulation

 Example (requires newer Compose version):

 @Composable
 fun NewTextFieldAddMarkdownEmphasis() {
     val markdownState = rememberTextFieldState()

     Button(onClick = {
         markdownState.edit {
             // TextFieldBuffer provides natural editing API

             // Insert ** before selection
             insert(selection.min, "**")

             // Insert ** after selection
             // selection.max is now +2 because we inserted **
             insert(selection.max + 2, "**")

             // Adjust selection
             // Move both start and end forward by 2
             selectCharsIn(
                 TextRange(selection.min + 2, selection.max + 2)
             )
         }
     }) {
         Text("Bold")
     }

     TextField(
         state = markdownState,
         lineLimits = TextFieldLineLimits.MultiLine(maxHeightInLines = 10)
     )
 }

 Benefits of edit {}:
 - More natural API (insert, delete, replace)
 - Don't need to manually splice strings
 - TextFieldBuffer tracks changes automatically
 - Less error-prone
 - Easier to read and maintain
*/

/*
 More complex editing examples (value-based, current approach)
*/

/*
 Insert prefix to current line
 Example: Adding "- " for list items
*/
@Composable
fun OldInsertListMarker() {
    var text by remember { mutableStateOf(TextFieldValue()) }

    Button(onClick = {
        text = with(text) {
            // Find start of current line
            val lineStart = this.text.lastIndexOf('\n', selection.min - 1) + 1

            copy(
                text = buildString {
                    append(this@with.text.take(lineStart))
                    append("- ")
                    append(this@with.text.drop(lineStart))
                },
                selection = TextRange(
                    selection.min + 2,
                    selection.max + 2
                )
            )
        }
    }) {
        Text("List Item")
    }

    TextField(
        value = text,
        onValueChange = { text = it }
    )
}

/*
 Wrap selection with tags
 Example: HTML/XML tags
*/
@Composable
fun OldWrapWithTags() {
    var text by remember { mutableStateOf(TextFieldValue()) }

    Button(onClick = {
        text = with(text) {
            copy(
                text = buildString {
                    append(this@with.text.take(selection.min))
                    append("<tag>")
                    append(this@with.text.substring(selection.min, selection.max))
                    append("</tag>")
                    append(this@with.text.drop(selection.max))
                },
                selection = TextRange(
                    selection.min + 5,
                    selection.max + 5
                )
            )
        }
    }) {
        Text("Wrap in Tag")
    }

    TextField(
        value = text,
        onValueChange = { text = it }
    )
}

/*
 STATE-BASED equivalents (Future approach)

 Insert list marker:

 @Composable
 fun NewInsertListMarker() {
     val textState = rememberTextFieldState()

     Button(onClick = {
         textState.edit {
             val lineStart = text.lastIndexOf('\n', selection.min - 1) + 1
             insert(lineStart, "- ")
             // Selection automatically adjusted
         }
     }) {
         Text("List Item")
     }

     TextField(state = textState)
 }

 Wrap with tags:

 @Composable
 fun NewWrapWithTags() {
     val textState = rememberTextFieldState()

     Button(onClick = {
         textState.edit {
             insert(selection.min, "<tag>")
             insert(selection.max + 5, "</tag>")
             // Adjust selection if needed
             selectCharsIn(TextRange(selection.min + 5, selection.max + 5))
         }
     }) {
         Text("Wrap in Tag")
     }

     TextField(state = textState)
 }

 Much cleaner and easier to understand!
*/
