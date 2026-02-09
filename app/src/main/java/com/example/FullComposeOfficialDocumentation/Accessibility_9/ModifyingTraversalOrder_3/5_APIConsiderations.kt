package com.example.FullComposeOfficialDocumentation.Accessibility_9.ModifyingTraversalOrder_3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

/*
API Considerations:

Consider the following when using the traversal APIs to ensure they work correctly and as expected.
*/

/*
1. Parent vs Child Placement:

- isTraversalGroup = true should be set on the PARENT containing the grouped elements
- traversalIndex should be set on a CHILD component that contains semantics and will be
  selected by accessibility services
*/

@Composable
fun CorrectAPIPlacement() {
    Column(
        modifier = Modifier.semantics { isTraversalGroup = true }
    ) {
        Text(
            "Item 1",
            modifier = Modifier.semantics { traversalIndex = 1f }
        )
        Text(
            "Item 2",
            modifier = Modifier.semantics { traversalIndex = 2f }
        )
    }
}

/*
CORRECT: isTraversalGroup on Column (parent), traversalIndex on Text elements (children)
*/

/*
2. ZIndex Considerations:

Ensure that all the elements you're investigating are at the same zIndex level, as that also
affects semantics and traversal order.

Elements with different zIndex values may not traverse in the expected order even with
traversalIndex set correctly.
*/

@Composable
fun ZIndexExample() {
    Box(
        modifier = Modifier.semantics { isTraversalGroup = true }
    ) {
        Text(
            "Background Text",
            modifier = Modifier.semantics { traversalIndex = 1f }
        )
    }
}

/*
Note: If elements have different zIndex values (one is layered above another), the zIndex
will take precedence over traversalIndex in determining order.
*/

/*
3. Semantics Merging Considerations:

Ensure that no semantics are unnecessarily merged, as this may affect which components
traversal indices are applied to.

If parent semantics merge children, the children's individual traversalIndex values may be ignored.
*/

@Composable
fun AvoidUnintendedMerging() {
    Column(
        modifier = Modifier.semantics { isTraversalGroup = true }
    ) {
        Row(
            modifier = Modifier.semantics(mergeDescendants = false) { }
        ) {
            Text(
                "Text 1",
                modifier = Modifier.semantics { traversalIndex = 2f }
            )
            Text(
                "Text 2",
                modifier = Modifier.semantics { traversalIndex = 1f }
            )
        }
    }
}

/*
If the Row had mergeDescendants = true (default for some composables), the individual Text
elements' traversalIndex values might not work as expected.
*/

/*
4. Focusable Elements Only:

traversalIndex only works on elements that are focusable by accessibility services.

Setting traversalIndex on a non-focusable container (like a plain Box or Column) has NO EFFECT
unless that container also has isTraversalGroup = true set on it.
*/

@Composable
fun FocusableElementExample() {
    Column(
        modifier = Modifier.semantics { isTraversalGroup = true }
    ) {
        Box(
            modifier = Modifier.semantics { traversalIndex = 1f }
        ) {
            Text("This won't work - Box is not focusable")
        }

        Text(
            "This works - Text is focusable",
            modifier = Modifier.semantics { traversalIndex = 2f }
        )
    }
}

/*
The Box's traversalIndex is ignored because Box is not a focusable element.
Only the Text element's traversalIndex is applied.
*/

/*
5. Default Scroll Containers:

Remember that isTraversalGroup is TRUE by default on scroll containers like:
- LazyColumn
- LazyRow
- LazyVerticalGrid
- Material Surfaces

You may need to explicitly set isTraversalGroup = false if you don't want this behavior.
*/

@Composable
fun ScrollContainerExample() {
    Column(
        modifier = Modifier.semantics { isTraversalGroup = false }
    ) {
        Text("Item 1")
        Text("Item 2")
        Text("Item 3")
    }
}

/*
Setting isTraversalGroup = false removes the grouping behavior and allows elements to be
traversed with the normal default order.
*/

/*
6. Testing is Essential:

Always test your traversal order with TalkBack or other accessibility services to ensure it
works as expected.

What seems logical in code may not always work as expected in practice due to:
- ZIndex conflicts
- Semantics merging
- Layout rendering order
- Other Compose internals
*/

/*
Summary of Best Practices:

1. ✅ Set isTraversalGroup = true on parent containers
2. ✅ Set traversalIndex on focusable child elements (Text, Button, etc.)
3. ✅ Ensure all elements are at the same zIndex level
4. ✅ Avoid unintended semantics merging
5. ✅ Remember scroll containers have isTraversalGroup = true by default
6. ✅ Always test with actual accessibility services
7. ✅ Use lower index values for higher priority elements
8. ✅ Keep indices relative within the same group

Common Mistakes to Avoid:

1. ❌ Setting traversalIndex on non-focusable elements
2. ❌ Forgetting to set isTraversalGroup on the parent
3. ❌ Mixing elements with different zIndex values
4. ❌ Not testing with real accessibility services
5. ❌ Unnecessarily merging semantics
6. ❌ Setting isTraversalGroup on the wrong level
*/

