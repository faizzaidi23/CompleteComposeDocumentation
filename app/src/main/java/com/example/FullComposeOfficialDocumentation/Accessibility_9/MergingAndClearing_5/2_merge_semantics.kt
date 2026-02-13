package com.example.FullComposeOfficialDocumentation.Accessibility_9.MergingAndClearing_5

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics

/*
MERGE SEMANTICS

When you apply a clickable modifier to a parent composable, Compose automatically
merges all children elements under it. Interactive Compose Material and Foundation
components use merging strategies by default.

WHEN TO USE MERGE SEMANTICS:
It's common for a component to consist of multiple composables. These composables
could form a logical group and each could contain important information, but you
still might want accessibility services to view them as one element.

EXAMPLE SCENARIO:
A composable that shows a user's avatar, their name, and some extra information.
Without merging, accessibility services would highlight each element separately,
forcing users to interact with each one individually.

HOW IT WORKS:
Use the mergeDescendants parameter in the semantics modifier. This way, accessibility
services treat the component as one entity, and all semantics properties of the
descendants are merged.

MERGING STRATEGY:
Each semantics property has a defined merging strategy:
- ContentDescription: Adds all descendant values to a list
- Text properties: Concatenate into a string
- Other properties: Check their mergePolicy implementation in SemanticsProperties.kt

Properties can:
- Take on the parent or child value
- Merge the values into a list or string
- Not allow merging at all (throw an exception)
- Use any other custom merging strategy
*/

/*
Example: Post metadata showing user info
Without merging, each element (avatar, name, date) would be separate
With merging, they form one logical unit
*/

data class Author(val name: String)
data class Metadata(
    val author: Author,
    val date: String,
    val readTimeMinutes: Int
)

@Composable
fun PostMetadata(metadata: Metadata) {
    /*
    Merge elements below for accessibility purposes
    This makes accessibility services treat the entire Row as one entity
    instead of focusing on each child individually
    */
    Row(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        /*
        Image is decorative, so contentDescription is null
        It will still be part of the merged semantic node
        */
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null
        )

        Column {
            /*
            Both text elements will be merged into the parent Row
            Accessibility services will read them together as one unit
            */
            Text(metadata.author.name)
            Text("${metadata.date} • ${metadata.readTimeMinutes} min read")
        }
    }
}

/*
RESULT:
Accessibility services now focus on the whole container at once and merge its contents.
Instead of navigating through avatar → name → date separately, users experience them
as one cohesive element.

ACCESSIBILITY ANNOUNCEMENT EXAMPLE (TalkBack):
"John Doe, February 13, 2026 • 5 min read"
(All information announced together in one go)
*/

