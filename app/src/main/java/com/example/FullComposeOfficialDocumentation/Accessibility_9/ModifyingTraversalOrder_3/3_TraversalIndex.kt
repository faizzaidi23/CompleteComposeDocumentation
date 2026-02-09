package com.example.FullComposeOfficialDocumentation.Accessibility_9.ModifyingTraversalOrder_3

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

/*
Customize Traversal Order:

traversalIndex is a float property that lets you customize TalkBack traversal order.

If grouping elements together with isTraversalGroup is not enough for TalkBack to work correctly,
use traversalIndex in conjunction with isTraversalGroup to further customize screen reader ordering.
*/

/*
traversalIndex Characteristics:

1. Elements with LOWER traversalIndex values are prioritized first
2. Can be positive or negative
3. The default value is 0f
4. Must be set on a component that will be selectable and focusable by accessibility services
   (such as on-screen elements like text or buttons)
5. Setting only traversalIndex on a Column would have no effect, unless the column has
   isTraversalGroup set on it as well
*/

/*
Common Use Case - Clock Face:

A clock face is a common scenario where standard traversal ordering does not work. The example
below is a time picker, where a user can traverse through the numbers on a clock face and select
digits for the hour and minute slots.

In a circular layout, 12 numbers are drawn starting with 12 and moving clockwise around the circle.
*/

@Composable
fun CircularLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content()
    }
}

/*
Problem - Without traversalIndex:

In the following simplified snippet, there is a CircularLayout in which 12 numbers are drawn,
starting with 12 and moving clockwise around the circle.
*/

@Composable
fun ClockFaceDemo() {
    CircularLayout {
        repeat(12) { hour ->
            ClockText(hour)
        }
    }
}

@Composable
fun ClockText(value: Int) {
    Box(modifier = Modifier) {
        Text((if (value == 0) 12 else value).toString())
    }
}

/*
The Problem:

Because the clock face is not read logically with the default left-to-right and top-to-bottom
ordering, TalkBack reads the numbers OUT OF ORDER.

For example, it might read: "12", "3", "6", "9", "1", "4", "7", "10", "2", "5", "8", "11"
instead of the correct sequential order: "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
*/

/*
Solution - With traversalIndex:

To rectify this, use the incrementing counter value as the traversalIndex.
*/

@Composable
fun ClockFaceDemoFixed() {
    CircularLayout(
        Modifier.semantics { isTraversalGroup = true }
    ) {
        repeat(12) { hour ->
            ClockTextWithIndex(hour)
        }
    }
}

@Composable
fun ClockTextWithIndex(value: Int) {
    Box(
        modifier = Modifier.semantics {
            this.traversalIndex = value.toFloat()
        }
    ) {
        Text((if (value == 0) 12 else value).toString())
    }
}

/*
How it works:

1. First, make the CircularLayout a traversal group: isTraversalGroup = true

2. Then, as each clock text is drawn onto the layout, set its corresponding traversalIndex
   to the counter value

3. Because the counter value continually increases, each clock value's traversalIndex is larger
   as numbers are added to the screen:
   - Clock value 0 has traversalIndex = 0
   - Clock value 1 has traversalIndex = 1
   - Clock value 2 has traversalIndex = 2
   - And so on...

4. In this way, the order that TalkBack reads them in is set correctly.

Now, the numbers inside the CircularLayout are read in the EXPECTED ORDER:
12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
*/

/*
Important Notes:

1. Relative Indexing:
   The traversalIndexes that have been set are only relative to other indexes within the same
   grouping. The rest of the screen ordering has been preserved.

   In other words, the semantic changes only modify the ordering within the clock face that has
   isTraversalGroup = true set.

2. Without isTraversalGroup:
   If you don't set CircularLayout's semantics to isTraversalGroup = true, the traversalIndex
   changes still apply. However, without the CircularLayout to bind them, the twelve digits of
   the clock face are read LAST, after all other elements on the screen have been visited.

   This occurs because all other elements have a default traversalIndex of 0f, and the clock
   text elements are read after all other 0f elements.
*/

@Composable
fun CustomOrderExample() {
    Box(modifier = Modifier.semantics { isTraversalGroup = true }) {
        Text(
            "Third item",
            modifier = Modifier.semantics { traversalIndex = 3f }
        )
        Text(
            "First item",
            modifier = Modifier.semantics { traversalIndex = 1f }
        )
        Text(
            "Second item",
            modifier = Modifier.semantics { traversalIndex = 2f }
        )
    }
}

/*
The above example will be read in order: "First item", "Second item", "Third item"
even though they are defined in a different order in the code.
*/

@Composable
fun NegativeIndexExample() {
    Box(modifier = Modifier.semantics { isTraversalGroup = true }) {
        Text(
            "Read second (default 0f)",
            modifier = Modifier
        )
        Text(
            "Read first (negative index)",
            modifier = Modifier.semantics { traversalIndex = -1f }
        )
        Text(
            "Read third (positive index)",
            modifier = Modifier.semantics { traversalIndex = 1f }
        )
    }
}

/*
Negative values allow you to prioritize certain elements to be read before others with default 0f values.
*/

