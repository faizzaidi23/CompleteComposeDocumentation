package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
Inspect the Tree:

The semantics tree is in fact two different trees:

1. Merged Semantics Tree:
   - Merges descendant nodes when mergeDescendants is set to true
   - Used by the testing framework by default

2. Unmerged Semantics Tree:
   - Does not apply the merging, keeps every node intact
   - Used by accessibility services (they apply their own merging algorithms)
   - Takes into consideration the mergeDescendants property
*/

/*
Inspecting with Testing Framework:

You can inspect both trees with the printToLog() method.
By default, the merged tree is logged.

Example for merged tree:
composeTestRule.onRoot().printToLog("MY TAG")

Example for unmerged tree:
composeTestRule.onRoot(useUnmergedTree = true).printToLog("MY TAG")

To print the unmerged tree, set the useUnmergedTree parameter of the onRoot() matcher to true.
*/

/*
Inspecting with Layout Inspector:

The Layout Inspector lets you display both the merged and the unmerged Semantics tree, by
selecting the preferred one in the view filter.

For each node in your tree, the Layout Inspector shows:
- Merged Semantics: The semantics after merging is applied
- Semantics: The semantics set on that specific node

You can find this in the properties panel when inspecting a composable.
*/

@Composable
fun InspectableComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Inspecting the Semantics Tree",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Use Layout Inspector to view semantics properties",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Use printToLog() in tests to debug semantics",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { /* Handle click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Example Button")
        }
    }
}

/*
Testing Framework Matchers:

By default, matchers in the Testing Framework use the merged Semantics tree.
That's why you can interact with a Button by matching the text shown inside it:

Example:
composeTestRule.onNodeWithText("Like").performClick()

This works because the Button merges its descendants' semantics, including the text.
*/

/*
Override default behavior:

You can override this behavior by setting the useUnmergedTree parameter of the matchers to true:

Example with merged tree (default):
composeTestRule.onNodeWithText("Example").assertExists()

Example with unmerged tree:
composeTestRule.onNode(
    hasText("Example"),
    useUnmergedTree = true
).assertExists()
*/

@Composable
fun TestableButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Click Me")
    }
}

/*
Best practices for inspection:

1. Use Layout Inspector during development:
   - View both merged and unmerged trees
   - Check semantics properties for each node
   - Verify that accessibility information is complete

2. Use printToLog() in tests:
   - Debug test failures related to semantics
   - Understand how your UI is represented
   - Verify merging behavior

3. Test with both trees when needed:
   - Use merged tree for most test cases (default)
   - Use unmerged tree when testing specific semantics properties
   - Switch between them to understand different behaviors
*/

/*
Example test code patterns:

// Test using merged tree (default)
@Test
fun testButton_merged() {
    composeTestRule.setContent {
        TestableButton(onClick = {})
    }

    // This works because Button merges its children
    composeTestRule.onNodeWithText("Click Me").performClick()

    // Print merged tree for debugging
    composeTestRule.onRoot().printToLog("MERGED_TREE")
}

// Test using unmerged tree
@Test
fun testButton_unmerged() {
    composeTestRule.setContent {
        TestableButton(onClick = {})
    }

    // Print unmerged tree for debugging
    composeTestRule.onRoot(useUnmergedTree = true).printToLog("UNMERGED_TREE")
}
*/

/*
What you'll see in Layout Inspector:

For a Button with Text inside:

Merged Semantics:
- Text: "Click Me"
- Role: Button
- Clickable: true
- (All child semantics combined into one)

Semantics (on Button node):
- Role: Button
- Clickable: true

Semantics (on Text node):
- Text: "Click Me"

The merged view combines everything, while the individual view shows each node's semantics.
*/

@Composable
fun ComplexInspectableComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Complex Component",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "This has multiple children",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(onClick = { /* Handle click */ }) {
            Text("Action Button")
        }
    }
}

/*
Summary:

- Two trees exist: merged and unmerged
- Layout Inspector shows both in the properties panel
- printToLog() helps debug in tests
- Use useUnmergedTree parameter to switch between them
- Merged tree is default for testing
- Unmerged tree is used by accessibility services
- Both are useful for different purposes
*/

