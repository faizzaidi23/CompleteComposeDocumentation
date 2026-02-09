package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
Custom Actions:

Custom actions can be used for more complex touchscreen gestures, like swipe to dismiss or
drag and drop, as these can be challenging for users with motor impairments or other
disabilities to interact with.

To make gestures more accessible, you can link them to custom actions, passing the action
and label there.
*/

@Composable
fun SwipeToDismissExample(
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "Remove article from list",
                        action = {
                            onDismiss()
                            true
                        }
                    )
                )
            }
    ) {
        Text(
            text = "Article Title",
            modifier = Modifier.padding(16.dp)
        )
    }
}

/*
How it works:

An accessibility service like TalkBack highlights the component, and hints that there are more
actions available in its menu, representing the swipe to dismiss action there.

This makes complex gestures accessible through the accessibility service's action menu.
*/

@Composable
fun ArticleListItemWithActions(
    onOpen: () -> Unit,
    onBookmark: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "Open article",
                        action = {
                            onOpen()
                            true
                        }
                    ),
                    CustomAccessibilityAction(
                        label = "Add to bookmarks",
                        action = {
                            onBookmark()
                            true
                        }
                    )
                )
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Article Title")
            Text(text = "Article summary...")
        }
    }
}

/*
Benefits for long lists:

Another use case for custom actions are long lists with items that have more available actions,
as it might be tedious for users to iterate through each action for every single item individually.

To improve the navigation experience, which is especially helpful for interaction-based assistive
technologies like Switch Access or Voice Access, you can use custom actions on the container to
move actions out of individual traversal, and into a separate action menu.
*/

@Composable
fun EmailListItem(
    emailTitle: String,
    onOpen: () -> Unit,
    onArchive: () -> Unit,
    onDelete: () -> Unit,
    onMarkUnread: () -> Unit
) {
    var isRead by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "Open email",
                        action = {
                            isRead = true
                            onOpen()
                            true
                        }
                    ),
                    CustomAccessibilityAction(
                        label = "Archive",
                        action = {
                            onArchive()
                            true
                        }
                    ),
                    CustomAccessibilityAction(
                        label = "Delete",
                        action = {
                            onDelete()
                            true
                        }
                    ),
                    CustomAccessibilityAction(
                        label = "Mark as unread",
                        action = {
                            onMarkUnread()
                            true
                        }
                    )
                )
            }
    ) {
        Text(
            text = emailTitle,
            modifier = Modifier.padding(16.dp)
        )
    }
}

/*
Important notes:

- When using custom actions, make sure to clear the original children's semantics with the
  clearAndSetSemantics modifier if you're moving click actions into custom actions

- Custom actions appear in the accessibility service's action menu

- This improves navigation for Switch Access and Voice Access users

- Return 'true' from the action to indicate successful execution
*/

@Composable
fun MediaControlExample(
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "Play",
                        action = {
                            onPlay()
                            true
                        }
                    ),
                    CustomAccessibilityAction(
                        label = "Pause",
                        action = {
                            onPause()
                            true
                        }
                    ),
                    CustomAccessibilityAction(
                        label = "Next track",
                        action = {
                            onNext()
                            true
                        }
                    ),
                    CustomAccessibilityAction(
                        label = "Previous track",
                        action = {
                            onPrevious()
                            true
                        }
                    )
                )
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Now Playing")
            Text(text = "Song Title - Artist Name")
        }
    }
}

/*
Use cases:

- Swipe to dismiss gestures
- Drag and drop operations
- Long lists with multiple actions per item
- Media controls
- Any complex gesture that needs an accessible alternative
*/

