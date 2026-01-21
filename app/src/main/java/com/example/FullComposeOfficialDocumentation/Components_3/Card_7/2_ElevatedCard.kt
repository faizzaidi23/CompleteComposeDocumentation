package com.example.FullComposeOfficialDocumentation.Components_3.Card_7

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
ELEVATED CARD

Material Design elevated container for UI content
Uses dedicated ElevatedCard composable
Appears elevated above the background with shadow
Default elevation creates visual hierarchy

COMMON USE CASES:
- Featured content items
- Interactive elements needing emphasis
- Floating action containers
- Important notifications
- Highlighted product cards

API SURFACE PARAMETERS:

1. modifier: Modifier
   Standard modifier for size, padding, positioning

2. shape: Shape
   Corner shape of the card (default is RoundedCornerShape)

3. colors: CardColors
   Container and content colors

4. elevation: CardElevation
   Shadow depth in different states (key parameter for elevated cards)

5. content: @Composable ColumnScope.() -> Unit
   Card content (automatically in Column layout)
*/

@Composable
fun ElevatedCardBasicExample() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = "Elevated",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ElevatedCardWithElevationExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Low Elevation (2dp)",
                modifier = Modifier.padding(16.dp)
            )
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Medium Elevation (6dp)",
                modifier = Modifier.padding(16.dp)
            )
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "High Elevation (12dp)",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

/*
CardElevation type:
- defaultElevation: Elevation in default state
- pressedElevation: Elevation when pressed (for clickable cards)
- focusedElevation: Elevation when focused
- hoveredElevation: Elevation when hovered
- draggedElevation: Elevation when dragged
- disabledElevation: Elevation when disabled
*/

@Composable
fun ElevatedCardWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Default Surface Color",
                modifier = Modifier.padding(16.dp)
            )
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Primary Container Color",
                modifier = Modifier.padding(16.dp)
            )
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF3E0),
                contentColor = Color(0xFFE65100)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Custom Orange Container",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ElevatedCardWithShapeExample() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier.size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Default\nShape", textAlign = TextAlign.Center)
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
            modifier = Modifier.size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Rounded\n24dp", textAlign = TextAlign.Center)
            }
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            shape = androidx.compose.foundation.shape.CircleShape,
            modifier = Modifier.size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Circle", textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun ElevatedCardClickableExample() {
    var clickCount by remember { mutableStateOf(0) }

    ElevatedCard(
        onClick = { clickCount++ },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Clickable Elevated Card",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Clicked $clickCount times")
            Text(
                "Elevation changes on press",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/*
Clickable elevated cards can have different elevation states:
- defaultElevation: Normal state
- pressedElevation: When user presses (typically lower)
- Creates interactive feedback through elevation change
*/

@Composable
fun ElevatedCardWithEnabledExample() {
    var isEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedCard(
            onClick = { /* action */ },
            enabled = isEnabled,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isEnabled) "Enabled Card" else "Disabled Card",
                modifier = Modifier.padding(16.dp)
            )
        }

        Button(onClick = { isEnabled = !isEnabled }) {
            Text(if (isEnabled) "Disable Card" else "Enable Card")
        }
    }
}

@Composable
fun ElevatedCardCompleteExample() {
    var isSelected by remember { mutableStateOf(false) }

    ElevatedCard(
        onClick = { isSelected = !isSelected },
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 12.dp else 6.dp,
            pressedElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            },
            contentColor = if (isSelected) {
                MaterialTheme.colorScheme.onPrimaryContainer
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Featured Product",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                "Click to select this item",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                if (isSelected) "Selected - Higher elevation" else "Not selected",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

/*
BEST PRACTICES:

Use elevation to establish visual hierarchy
Higher elevation = more important or active
Keep elevation values consistent across app
Use pressed elevation for tactile feedback
Default elevations: 1dp, 2dp, 4dp, 6dp, 8dp, 12dp, 16dp, 24dp
Avoid excessive elevation (creates heavy shadows)
Consider accessibility in low contrast environments
Elevation creates depth perception in UI
*/

