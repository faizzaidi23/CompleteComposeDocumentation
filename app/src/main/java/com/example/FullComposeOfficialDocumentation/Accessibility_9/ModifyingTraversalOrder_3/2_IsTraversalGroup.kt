package com.example.FullComposeOfficialDocumentation.Accessibility_9.ModifyingTraversalOrder_3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics

/*
Group Elements for Traversal:

isTraversalGroup is a boolean property that defines whether a semantics node is a traversal group.
This type of node is one whose function is to serve as a boundary or border in organizing the
node's children.

Setting isTraversalGroup = true on a node means that all children of that node are visited before
moving to other elements. You can set isTraversalGroup on non-screen reader focusable nodes, such
as columns, rows, or boxes.
*/

/*
Problem Example - Without isTraversalGroup:

The following example emits four text elements. The left two elements belong to one CardBox element,
while the right two elements belong to another CardBox element.
*/

@Composable
fun CardBox(
    topSampleText: String,
    bottomSampleText: String,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Column {
            Text(topSampleText)
            Text(bottomSampleText)
        }
    }
}

@Composable
fun TraversalGroupDemo() {
    val topSampleText1 = "This sentence is in "
    val bottomSampleText1 = "the left column."
    val topSampleText2 = "This sentence is "
    val bottomSampleText2 = "on the right."

    Row {
        CardBox(
            topSampleText1,
            bottomSampleText1
        )
        CardBox(
            topSampleText2,
            bottomSampleText2
        )
    }
}

/*
The Problem:

Because no semantics have been set, the default behavior of the screen reader is to traverse
elements from left to right and top to bottom.

TalkBack reads the sentence fragments in the WRONG order:
"This sentence is in" → "This sentence is" → "the left column." → "on the right."

This creates a confusing experience for users with accessibility needs.
*/

/*
Solution - With isTraversalGroup:

To order the fragments correctly, modify the snippet to set isTraversalGroup to true on each CardBox.
*/

@Composable
fun TraversalGroupDemo2() {
    val topSampleText1 = "This sentence is in "
    val bottomSampleText1 = "the left column."
    val topSampleText2 = "This sentence is"
    val bottomSampleText2 = "on the right."

    Row {
        CardBox(
            topSampleText1,
            bottomSampleText1,
            Modifier.semantics { isTraversalGroup = true }
        )
        CardBox(
            topSampleText2,
            bottomSampleText2,
            Modifier.semantics { isTraversalGroup = true }
        )
    }
}

/*
How it works:

Because isTraversalGroup is set specifically on each CardBox, the CardBox boundaries apply when
sorting their elements. In this case, the left CardBox is read first, followed by the right CardBox.

Now, TalkBack reads out the sentence fragments in the CORRECT order:
"This sentence is in" → "the left column." → "This sentence is" → "on the right."
*/

/*
Important Notes:

1. isTraversalGroup is TRUE by default on:
   - Scroll containers (like LazyColumn)
   - Material surfaces

2. You can disable this default behavior:
   Modifier.semantics { isTraversalGroup = false }

   Setting isTraversalGroup to false reinstates the default traversal order.

3. Use on non-focusable containers:
   - Columns
   - Rows
   - Boxes
   - Custom layout composables
*/

@Composable
fun MultiColumnExample() {
    Row {
        Column(
            modifier = Modifier
                .weight(1f)
                .semantics { isTraversalGroup = true }
        ) {
            Text("Column 1 - Item 1")
            Text("Column 1 - Item 2")
            Text("Column 1 - Item 3")
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .semantics { isTraversalGroup = true }
        ) {
            Text("Column 2 - Item 1")
            Text("Column 2 - Item 2")
            Text("Column 2 - Item 3")
        }
    }
}

/*
With isTraversalGroup = true on each Column:
TalkBack reads all items in Column 1 first, then all items in Column 2.

Without isTraversalGroup:
TalkBack would alternate: "Column 1 - Item 1", "Column 2 - Item 1", "Column 1 - Item 2", etc.
*/

