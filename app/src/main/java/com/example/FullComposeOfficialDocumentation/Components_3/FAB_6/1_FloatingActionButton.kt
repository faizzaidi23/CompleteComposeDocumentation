package com.example.FullComposeOfficialDocumentation.Components_3.FAB_6

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
FLOATING ACTION BUTTON (FAB)

High-emphasis button for primary actions in an application
Promotes a single, focused action
Typically anchored to bottom right of screen

COMMON USE CASES:
- Create new item (note, document, post)
- Add new contact or conversation
- Center map on current location
- Quick access to most common action

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when FAB is pressed

2. modifier: Modifier
   Standard modifier for positioning and size

3. containerColor: Color
   Background color of the FAB

4. contentColor: Color
   Color of the icon inside FAB

5. shape: Shape
   Shape of the FAB (default is rounded square)

6. elevation: FloatingActionButtonElevation
   Shadow depth in different states
*/

@Composable
fun FloatingActionButtonExample(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun FloatingActionButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FloatingActionButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Add, contentDescription = "Default colors")
        }

        FloatingActionButton(
            onClick = { /* action */ },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Secondary colors")
        }

        FloatingActionButton(
            onClick = { /* action */ },
            containerColor = Color(0xFFE91E63),
            contentColor = Color.White
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Custom pink")
        }
    }
}

/*
containerColor and contentColor:
- containerColor: FAB background
- contentColor: Icon color
- Should have good contrast
- Default uses primary color scheme
*/

@Composable
fun FloatingActionButtonWithElevationExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FloatingActionButton(
            onClick = { /* action */ },
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 6.dp
            )
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Default elevation")
        }

        FloatingActionButton(
            onClick = { /* action */ },
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 12.dp,
                pressedElevation = 16.dp
            )
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Higher elevation")
        }
    }
}

/*
elevation parameter controls shadow:
- defaultElevation: Normal state shadow depth
- pressedElevation: When FAB is pressed
- hoveredElevation: When mouse hovers (desktop)
- focusedElevation: When FAB has focus

FABs have higher default elevation than regular buttons
*/

@Composable
fun FloatingActionButtonInScaffoldExample() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* create new */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Create new")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Content area", modifier = Modifier.padding(16.dp))
        }
    }
}

/*
FAB in Scaffold:
- Scaffold has dedicated floatingActionButton parameter
- Automatically positions FAB at bottom right
- Handles padding and layout
- Most common and recommended pattern
*/

@Composable
fun FloatingActionButtonCompleteExample() {
    var itemCount by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { itemCount++ },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add item")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                "Items created: $itemCount",
                style = MaterialTheme.typography.titleLarge
            )
            Text("Tap the FAB to create new items")
        }
    }
}

/*
BEST PRACTICES:

One FAB per screen maximum
Use for most common action only
Position at bottom right (Scaffold handles this)
Make action obvious with appropriate icon
Don't use for destructive actions
Keep icon simple and recognizable
Ensure good color contrast
Provides clear visual affordance
*/

