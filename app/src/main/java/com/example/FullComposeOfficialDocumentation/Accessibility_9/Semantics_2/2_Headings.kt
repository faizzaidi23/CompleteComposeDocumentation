package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
Headings:

Apps often contain screens with text-rich content, like long articles or news pages, which are
usually divided into different subsections with headings.

Users with accessibility needs can have difficulties navigating such a screen easily. To improve
the navigation experience, some accessibility services allow for easier navigation directly
between sections or headings.

To enable this, indicate that your component is a heading by defining its semantics property.
*/

@Composable
fun Subsection(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.semantics { heading() }
    )
}

/*
By marking text as a heading, accessibility services can:
- Jump directly between headings for faster navigation
- Announce the text as a heading to users
- Provide better context about the structure of the content
*/

@Composable
fun ArticleWithHeadings() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Article Title",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.semantics { heading() }
        )

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "First Section",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.semantics { heading() }
        )

        Text(
            text = "More article content here...",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "Second Section",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.semantics { heading() }
        )

        Text(
            text = "Additional content...",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

/*
This improves navigation for users with accessibility needs by allowing them to:
- Skip between sections easily
- Get a better understanding of the content structure
- Navigate long articles more efficiently
*/

