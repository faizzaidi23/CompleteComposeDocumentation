package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
Semantics Tree:

A composition describes the UI of your app and is produced by running composables. The composition
is a tree-structure that consists of the composables that describe your UI.

Next to the Composition, there exists a parallel tree, called the semantics tree. This tree
describes your UI in an alternative manner that is understandable for Accessibility services and
for the Testing framework.

Accessibility services use the tree to describe the app to users with a specific need. The testing
framework uses the tree to interact with your app and make assertions about it.

The Semantics tree does not contain the information on how to draw your composables, but it
contains information about the semantic meaning of your composables.
*/

/*
How it works:

If your app consists of composables and modifiers from the Compose foundation and material library,
the Semantics tree is automatically filled and generated for you.

However when you're adding custom low-level composables, you have to manually provide its semantics.
There might also be situations where your tree does not correctly or fully represent the meaning
of the elements on the screen, in which case you can adapt the tree.
*/

@Composable
fun SemanticsTreeExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "UI Hierarchy",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "The UI you see is represented as a composition tree",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Parallel to this, a semantics tree exists for accessibility services",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "This tree contains semantic meaning, not visual information",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/*
Custom composables example:

Consider a custom calendar composable. If the entire calendar is implemented as a single
low-level composable using the Layout composable and drawing directly to the Canvas, accessibility
services won't receive enough information about the content.

For example, if a user clicks on the day containing 17, the accessibility framework only receives
the description information for the whole calendar control. TalkBack would announce "Calendar" or
"April Calendar" and the user would be left to wonder what day was selected.

To make this composable more accessible, you need to add semantic information manually.
*/

/*
Key points about the Semantics Tree:

1. It's a parallel tree structure to the UI composition
2. It describes semantic meaning, not visual appearance
3. Used by accessibility services and testing framework
4. Automatically generated for standard Compose components
5. Needs manual implementation for custom low-level composables
6. Can be inspected and adapted when needed
*/

@Composable
fun CalendarExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Calendar",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "When implementing a custom calendar, you need to manually add semantics",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Without semantics, accessibility services only know it's a 'Calendar'",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "With proper semantics, they can announce the selected date",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

/*
Best practices:

- Use standard Compose components when possible (they have built-in semantics)
- For custom components, add appropriate semantics manually
- Test with accessibility services like TalkBack
- Use the Layout Inspector to visualize the semantics tree
- Ensure each focusable element has meaningful semantics
*/

