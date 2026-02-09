package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
Merged and Unmerged Tree:

As mentioned before, each composable in the UI tree might have zero or more semantics properties set.
When a composable has no semantics properties set, it isn't included as part of the Semantics tree.

The Semantics tree contains only the nodes that actually contain semantic meaning. However,
sometimes to convey the correct meaning of what is shown on the screen, it is also useful to
merge certain sub-trees of nodes and treat them as one.

That way you can reason about a set of nodes as a whole, instead of dealing with each descendant
node individually. As a rule of thumb, each node in this tree represents a focusable element when
using Accessibility services.
*/

/*
Example: Button with merged semantics

You can reason about a button as a single element, even though it may contain multiple child nodes.
*/

@Composable
fun LikeButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Like")
    }
}

/*
In the Semantics tree, the properties of the button's descendants are merged, and the button is
presented as a single leaf node in the tree.

Composables and modifiers can indicate that they want to merge their descendants' semantics
properties by calling:
Modifier.semantics(mergeDescendants = true) {}

Setting this property to true indicates that the semantics properties should be merged.
*/

@Composable
fun MergedSemanticsExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
            .clickable { /* Handle click */ }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null
        )
        Spacer(Modifier.size(8.dp))
        Text("Favorite Article")
    }
}

/*
Automatic merging:

Several modifiers and composables in the Foundation and Material Compose libraries have this
property set automatically:

- clickable modifier
- toggleable modifier
- Button composable
- ListItem composable
- And many others
*/

/*
Two types of trees:

1. Merged Semantics Tree:
   - Merges descendant nodes when mergeDescendants is set to true
   - Used by the testing framework by default
   - Treats groups of elements as single focusable units

2. Unmerged Semantics Tree:
   - Does not apply merging, keeps every node intact
   - Used by accessibility services (they apply their own merging algorithms)
   - Shows the full structure with all individual nodes
*/

@Composable
fun UnmergedSemanticsExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Unmerged semantics show all individual nodes")
        Text(text = "Merged semantics group related nodes together")
        Text(text = "Accessibility services use the unmerged tree")
        Text(text = "Testing framework uses the merged tree by default")
    }
}

/*
Key differences:

Merged Tree:
- Groups related elements
- Used for testing by default
- Simpler structure
- Represents focusable elements

Unmerged Tree:
- Shows all individual elements
- Used by accessibility services
- More detailed structure
- Accessibility services apply their own merging
*/

/*
When to use mergeDescendants = true:

- When you have a group of elements that should be treated as one focusable unit
- For custom clickable containers with multiple children
- When you want to combine text from multiple Text composables
- For custom interactive components

When NOT to use it:

- When each child should be individually focusable
- When you want fine-grained accessibility control
- For decorative containers with independent children
*/

@Composable
fun ArticleCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(text = title)
        Text(text = subtitle)
    }
}

/*
The ArticleCard above will be announced as a single element by TalkBack, combining both
title and subtitle into one announcement, followed by "double tap to activate".

Without mergeDescendants = true, each Text would be announced separately, which may not
provide the best user experience.
*/

