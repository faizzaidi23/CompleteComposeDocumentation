package com.example.FullComposeOfficialDocumentation.Components_3.Buttons_5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
OUTLINED BUTTON

Features a border with no fill
Medium-emphasis button for secondary actions
Pairs well with filled buttons to show alternatives

WHEN TO USE:
- Secondary actions alongside primary button
- Cancel, Back, Skip actions
- Alternative choices in dialogs
- Less important but still relevant actions

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. modifier: Modifier
   Standard modifier for layout and styling

3. enabled: Boolean (default = true)
   Controls interactive state

4. border: BorderStroke
   Defines outline appearance (width and color)

5. colors: ButtonColors
   Defines colors for content and background

6. contentPadding: PaddingValues
   Internal padding around content
*/

@Composable
fun OutlinedButtonExample(onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Text("Outlined Button")
    }
}

@Composable
fun OutlinedButtonWithBorderExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(onClick = { /* action */ }) {
            Text("Default Border")
        }

        OutlinedButton(
            onClick = { /* action */ },
            border = androidx.compose.foundation.BorderStroke(2.dp, Color.Red)
        ) {
            Text("Thick Red Border")
        }

        OutlinedButton(
            onClick = { /* action */ },
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF6200EE))
        ) {
            Text("Custom Purple Border")
        }
    }
}

/*
border parameter controls outline appearance:
- BorderStroke(width, color)
- width: Thickness of border (typically 1-2dp)
- color: Border color (matches content color usually)
*/

@Composable
fun OutlinedButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(onClick = { /* action */ }) {
            Text("Default Outlined")
        }

        OutlinedButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Red
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red)
        ) {
            Text("Red Outlined")
        }

        OutlinedButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF2E7D32)
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF2E7D32))
        ) {
            Text("Green Outlined")
        }
    }
}

/*
OutlinedButton colors:
- containerColor: Usually transparent
- contentColor: Text and border color
Border and content color should match for consistency
*/

@Composable
fun OutlinedButtonPairedWithFilledExample() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Common Pattern: Primary + Secondary Actions")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }

            androidx.compose.material3.Button(
                onClick = { showDialog = true },
                modifier = Modifier.weight(1f)
            ) {
                Text("Submit")
            }
        }

        if (showDialog) {
            Text("Form submitted!", color = Color(0xFF4CAF50))
        }
    }
}

/*
Common pairing pattern:
- OutlinedButton for Cancel/Back (left position)
- Filled Button for primary action (right position)
This creates clear visual hierarchy
*/

@Composable
fun OutlinedButtonCompleteExample() {
    var selectedFilter by remember { mutableStateOf<String?>(null) }
    val filters = listOf("All", "Active", "Completed")

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Filter options using outlined buttons:")

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { filter ->
                OutlinedButton(
                    onClick = {
                        selectedFilter = if (selectedFilter == filter) null else filter
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (selectedFilter == filter) {
                            Color(0xFFE8DEF8)
                        } else {
                            Color.Transparent
                        },
                        contentColor = if (selectedFilter == filter) {
                            Color(0xFF6750A4)
                        } else {
                            Color.Gray
                        }
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        width = if (selectedFilter == filter) 2.dp else 1.dp,
                        color = if (selectedFilter == filter) {
                            Color(0xFF6750A4)
                        } else {
                            Color.Gray
                        }
                    )
                ) {
                    Text(filter)
                }
            }
        }

        selectedFilter?.let {
            Text("Showing: $it items")
        }
    }
}

/*
COMPARISON WITH OTHER BUTTONS:

Filled Button:
- Highest emphasis
- Primary actions
- One per screen

Outlined Button:
- Medium emphasis
- Secondary actions
- Multiple allowed
- Clear but not dominant

Text Button:
- Lowest emphasis
- Tertiary actions
- Navigation

BEST PRACTICES:

Always pair with higher emphasis button
Use for cancel/back/skip actions
Good for filter chips and toggles
Border should match content color
Keep consistent stroke width (1-2dp)
Don't use alone as primary action
Works well for destructive secondary actions (Cancel, Delete)
*/

