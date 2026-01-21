package com.example.FullComposeOfficialDocumentation.Components_3.FAB_6

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
LARGE FLOATING ACTION BUTTON

Larger version of standard FAB
Maximum visual emphasis for primary action
Makes action unmissable

WHEN TO USE:
- Primary action needs maximum emphasis
- Large screens or tablets
- Accessibility requirements (larger touch target)
- Action is critical to user flow

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. modifier: Modifier
   Standard modifier for positioning

3. shape: Shape
   Shape of the FAB (can use CircleShape for round button)

4. containerColor: Color
   Background color of the large FAB

5. contentColor: Color
   Icon color inside the large FAB

6. elevation: FloatingActionButtonElevation
   Shadow depth in different states
*/

@Composable
fun LargeFloatingActionButtonExample(onClick: () -> Unit) {
    LargeFloatingActionButton(onClick = onClick) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Add",
            modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
        )
    }
}

/*
Icon size for Large FAB:
Use FloatingActionButtonDefaults.LargeIconSize for proper scaling
Larger icon fits the larger button
Maintains proper proportions
*/

@Composable
fun LargeFloatingActionButtonWithShapeExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LargeFloatingActionButton(
            onClick = { /* action */ }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Default shape",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
            )
        }

        LargeFloatingActionButton(
            onClick = { /* action */ },
            shape = CircleShape
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Circle shape",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
            )
        }
    }
}

/*
shape parameter:
- Default: Rounded square (MaterialTheme.shapes.large)
- CircleShape: Perfect circle
- Can use any custom Shape
- Circle shape creates distinct look
*/

@Composable
fun LargeFloatingActionButtonSizeComparisonExample() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            SmallFloatingActionButton(onClick = { /* action */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Small")
            }
            Text("Small", style = MaterialTheme.typography.labelSmall)
        }

        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            FloatingActionButton(onClick = { /* action */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Standard")
            }
            Text("Standard", style = MaterialTheme.typography.labelSmall)
        }

        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            LargeFloatingActionButton(onClick = { /* action */ }) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Large",
                    modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
                )
            }
            Text("Large", style = MaterialTheme.typography.labelSmall)
        }
    }
}

/*
All three FAB sizes shown together
Clear visual hierarchy
Choose based on emphasis needed
*/

@Composable
fun LargeFloatingActionButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LargeFloatingActionButton(
            onClick = { /* action */ },
            shape = CircleShape
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Default",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
            )
        }

        LargeFloatingActionButton(
            onClick = { /* action */ },
            shape = CircleShape,
            containerColor = Color(0xFFE91E63),
            contentColor = Color.White
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Custom pink",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
            )
        }
    }
}

/*
Large FAB with custom colors:
More prominent due to size
Color choice more impactful
Use for strong brand presence
*/

@Composable
fun LargeFloatingActionButtonInScaffoldExample() {
    Scaffold(
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { /* create */ },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Create",
                    modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Large FAB for maximum emphasis", modifier = Modifier.padding(16.dp))
        }
    }
}

/*
Large FAB in Scaffold:
Commands attention
Best for critical primary actions
Ensure it doesn't overwhelm UI
*/

@Composable
fun LargeFloatingActionButtonCompleteExample() {
    var photoCount by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { photoCount++ },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Take photo",
                    modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
                )
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
                "Camera App",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(16.dp))
            Text("Photos taken: $photoCount")
            Text("Tap the large FAB to capture")
        }
    }
}

/*
WHEN TO USE EACH SIZE:

Small FAB:
- Limited space
- Less emphasis needed
- Secondary actions

Standard FAB:
- Default choice
- Most common
- Good balance

Large FAB:
- Maximum emphasis
- Critical actions
- Accessibility needs
- Large screens

BEST PRACTICES:

Use for most important action only
Circle shape creates distinct appearance
Ensure icon scales properly (use LargeIconSize)
Don't overpower the interface
Good for camera, create, primary actions
Consider accessibility benefits
May be too large for small screens
Works well on tablets
*/

