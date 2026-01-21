package com.example.FullComposeOfficialDocumentation.Components_3.FAB_6

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
SMALL FLOATING ACTION BUTTON

Smaller version of standard FAB
Use when screen space is limited or FAB needs less emphasis
Still maintains high visibility

WHEN TO USE:
- Limited screen space
- Less prominent action
- Secondary floating action alongside standard FAB
- Dense interfaces with multiple actions

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. modifier: Modifier
   Standard modifier for positioning

3. containerColor: Color
   Background color of the small FAB

4. contentColor: Color
   Icon color inside the small FAB

5. shape: Shape
   Shape of the small FAB

6. elevation: FloatingActionButtonElevation
   Shadow depth in different states
*/

@Composable
fun SmallFloatingActionButtonExample(onClick: () -> Unit) {
    SmallFloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun SmallFloatingActionButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SmallFloatingActionButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Add, contentDescription = "Default")
        }

        SmallFloatingActionButton(
            onClick = { /* action */ },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Edit, contentDescription = "Secondary")
        }

        SmallFloatingActionButton(
            onClick = { /* action */ },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.tertiary
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Tertiary")
        }
    }
}

/*
Color customization same as standard FAB
Use theme colors for consistency
Secondary/tertiary colors work well for small FABs
*/

@Composable
fun SmallFloatingActionButtonSizeComparisonExample() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            SmallFloatingActionButton(onClick = { /* action */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Small FAB")
            }
            Text("Small", style = MaterialTheme.typography.labelSmall)
        }

        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            FloatingActionButton(onClick = { /* action */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Standard FAB")
            }
            Text("Standard", style = MaterialTheme.typography.labelSmall)
        }
    }
}

/*
Size comparison helps decide which to use
Small FAB is noticeably more compact
Use small when standard FAB feels too large
*/

@Composable
fun SmallFloatingActionButtonInScaffoldExample() {
    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { /* quick action */ },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(Icons.Filled.Edit, contentDescription = "Quick edit")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Small FAB takes less space", modifier = Modifier.padding(16.dp))
        }
    }
}

/*
Small FAB in Scaffold:
Works same as standard FAB
Takes less screen real estate
Better for apps with limited space
Good for secondary actions
*/

@Composable
fun SmallFloatingActionButtonCompleteExample() {
    var notes by remember { mutableStateOf(listOf("Note 1", "Note 2")) }
    var editMode by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = { editMode = !editMode },
                containerColor = if (editMode) {
                    MaterialTheme.colorScheme.tertiaryContainer
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                }
            ) {
                Icon(
                    if (editMode) Icons.Filled.Add else Icons.Filled.Edit,
                    contentDescription = if (editMode) "Add note" else "Edit mode"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                if (editMode) "Edit Mode" else "View Mode",
                style = MaterialTheme.typography.titleLarge
            )
            notes.forEach { note ->
                Text(note)
            }
        }
    }
}

/*
COMPARISON: Small vs Standard FAB

Small FAB:
- More compact
- Less visual weight
- Good for secondary actions
- Better for dense layouts
- Multiple can coexist

Standard FAB:
- More prominent
- Stronger visual emphasis
- Primary action only
- One per screen
- Better discoverability

BEST PRACTICES:

Use when standard FAB is too large
Good for less critical quick actions
Maintains FAB benefits in smaller size
Don't use multiple small FABs (confusing)
Still one floating action per screen
Keep icon clear despite smaller size
Ensure adequate touch target size
*/

