package com.example.FullComposeOfficialDocumentation.Components_3.FAB_6

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
EXTENDED FLOATING ACTION BUTTON

FAB with both icon and text
More descriptive than icon-only FAB
Provides context for the action

WHEN TO USE:
- Action needs text explanation
- Icon alone isn't clear enough
- First-time user guidance
- Important action that benefits from labeling

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. icon: @Composable () -> Unit (required for extended FAB)
   Icon displayed in the FAB

3. text: @Composable () -> Unit (required for extended FAB)
   Text label displayed alongside icon

4. modifier: Modifier
   Standard modifier for positioning

5. expanded: Boolean
   Controls whether text is shown or hidden

6. containerColor: Color
   Background color of the extended FAB

7. contentColor: Color
   Color of icon and text
*/

@Composable
fun ExtendedFloatingActionButtonExample(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        icon = { Icon(Icons.Filled.Edit, contentDescription = null) },
        text = { Text("Extended FAB") }
    )
}

/*
Basic extended FAB:
icon parameter provides the icon
text parameter provides the label
Both are required for extended FAB
*/

@Composable
fun ExtendedFloatingActionButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExtendedFloatingActionButton(
            onClick = { /* action */ },
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            text = { Text("Create") }
        )

        ExtendedFloatingActionButton(
            onClick = { /* action */ },
            icon = { Icon(Icons.Filled.Edit, contentDescription = null) },
            text = { Text("Edit Note") },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

        ExtendedFloatingActionButton(
            onClick = { /* action */ },
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            text = { Text("Add Item") },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}

/*
Color customization:
containerColor: Background
contentColor: Icon and text color
Use theme colors for consistency
*/

@Composable
fun ExtendedFloatingActionButtonExpandedExample() {
    var isExpanded by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExtendedFloatingActionButton(
            onClick = { /* action */ },
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            text = { Text("Create New") },
            expanded = isExpanded
        )

        Button(onClick = { isExpanded = !isExpanded }) {
            Text(if (isExpanded) "Collapse FAB" else "Expand FAB")
        }
    }
}

/*
expanded parameter:
- true: Shows icon and text
- false: Shows only icon (collapses to standard FAB size)
Useful for scroll-based expansion/collapse
*/

@Composable
fun ExtendedFloatingActionButtonScrollBehaviorExample() {
    val listState = rememberLazyListState()
    val isExpanded by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* create */ },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("Create") },
                expanded = isExpanded
            )
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(50) { index ->
                ListItem(
                    headlineContent = { Text("Item $index") }
                )
            }
        }
    }
}

/*
Scroll-based expansion:
- Expanded when at top of list
- Collapses when scrolling down
- Saves space during scroll
- Common pattern in Material Design apps
*/

@Composable
fun ExtendedFloatingActionButtonAnimatedExample() {
    var showFab by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimatedVisibility(
            visible = showFab,
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            ExtendedFloatingActionButton(
                onClick = { /* action */ },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("Animated FAB") }
            )
        }

        Button(onClick = { showFab = !showFab }) {
            Text(if (showFab) "Hide FAB" else "Show FAB")
        }
    }
}

/*
Animated visibility:
Extended FABs work well with animations
Can animate in/out based on context
Provides smooth user experience
*/

@Composable
fun ExtendedFloatingActionButtonCompleteExample() {
    var noteCount by remember { mutableStateOf(0) }
    val listState = rememberLazyListState()
    val isExpanded by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { noteCount++ },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("New Note") },
                expanded = isExpanded,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    "Notes ($noteCount)",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(noteCount) { index ->
                ListItem(
                    headlineContent = { Text("Note ${index + 1}") },
                    supportingContent = { Text("Note content here") }
                )
            }
            if (noteCount == 0) {
                item {
                    Text(
                        "No notes yet. Tap the FAB to create one!",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

/*
COMPARISON: Extended vs Standard FAB

Standard FAB:
- Icon only
- More compact
- Universal language
- Takes less space

Extended FAB:
- Icon + text
- More descriptive
- Clearer purpose
- Takes more space
- Better for first-time users

WHEN TO USE EXTENDED:

Use Extended FAB when:
- Action isn't obvious from icon alone
- Onboarding new users
- Important action needs emphasis
- Text adds valuable context

Use Standard FAB when:
- Icon is universally understood (+ for add)
- Screen space is limited
- Minimalist design preferred
- Action is obvious

BEST PRACTICES:

Keep text short (1-2 words ideal)
Use expanded parameter for scroll behavior
Collapse when scrolling to save space
Ensure icon and text match in meaning
Don't use if icon alone is sufficient
Good for primary creation actions
Position at bottom right via Scaffold
Animate expansion/collapse smoothly
Use theme colors for consistency
*/

