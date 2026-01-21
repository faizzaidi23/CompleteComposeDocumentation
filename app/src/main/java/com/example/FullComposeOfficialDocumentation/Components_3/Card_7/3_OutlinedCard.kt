package com.example.FullComposeOfficialDocumentation.Components_3.Card_7

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
OUTLINED CARD

Material Design outlined container for UI content
Uses dedicated OutlinedCard composable
Features a border stroke with no elevation by default
Provides subtle container differentiation

COMMON USE CASES:
- Secondary content items
- Selection options in forms
- Less prominent information cards
- Grouped content with minimal emphasis
- Alternative to elevated cards for flatter design

API SURFACE PARAMETERS:

1. modifier: Modifier
   Standard modifier for size, padding, positioning

2. shape: Shape
   Corner shape of the card (default is RoundedCornerShape)

3. colors: CardColors
   Container and content colors

4. border: BorderStroke
   Border width and color around the card (key parameter for outlined cards)

5. content: @Composable ColumnScope.() -> Unit
   Card content (automatically in Column layout)
*/

@Composable
fun OutlinedCardBasicExample() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = "Outlined",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun OutlinedCardWithBordersExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedCard(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Thin Border (1dp) - Theme Outline",
                modifier = Modifier.padding(16.dp)
            )
        }

        OutlinedCard(
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Medium Border (2dp) - Primary Color",
                modifier = Modifier.padding(16.dp)
            )
        }

        OutlinedCard(
            border = BorderStroke(4.dp, Color(0xFF4CAF50)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Thick Border (4dp) - Custom Green",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

/*
BorderStroke type:
- width: Dp value for border thickness
- brush: Brush for border color (can be solid or gradient)

Common border widths:
- 1.dp: Standard subtle outline
- 2.dp: More prominent outline
- 3-4.dp: Heavy emphasis outline
*/

@Composable
fun OutlinedCardWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Default Surface Color",
                modifier = Modifier.padding(16.dp)
            )
        }

        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Secondary Container with Matching Border",
                modifier = Modifier.padding(16.dp)
            )
        }

        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFFDE7),
                contentColor = Color(0xFFF57F17)
            ),
            border = BorderStroke(2.dp, Color(0xFFFBC02D)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Custom Yellow Theme",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun OutlinedCardWithShapeExample() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedCard(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
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

        OutlinedCard(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
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

        OutlinedCard(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
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

/*
Shape applies to both the container and border
Border follows the same shape as card
Rounded corners are measured from the edge
*/

@Composable
fun OutlinedCardClickableExample() {
    var clickCount by remember { mutableStateOf(0) }

    OutlinedCard(
        onClick = { clickCount++ },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Clickable Outlined Card",
                style = MaterialTheme.typography.titleMedium
            )
            Text("Clicked $clickCount times")
            Text(
                "Ripple effect on click",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun OutlinedCardWithEnabledExample() {
    var isEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedCard(
            onClick = { /* action */ },
            enabled = isEnabled,
            border = BorderStroke(
                width = 1.dp,
                color = if (isEnabled) {
                    MaterialTheme.colorScheme.outline
                } else {
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.38f)
                }
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
fun OutlinedCardSelectionExample() {
    var selectedOption by remember { mutableStateOf(0) }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "Select an option:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        options.forEachIndexed { index, option ->
            OutlinedCard(
                onClick = { selectedOption = index },
                border = BorderStroke(
                    width = if (selectedOption == index) 2.dp else 1.dp,
                    color = if (selectedOption == index) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outline
                    }
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedOption == index) {
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = option,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun OutlinedCardCompleteExample() {
    var isSelected by remember { mutableStateOf(false) }

    OutlinedCard(
        onClick = { isSelected = !isSelected },
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
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
                "Selection Card",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                "Click to toggle selection",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                if (isSelected) "Selected - Border thickens" else "Not selected",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

/*
BEST PRACTICES:

Use for less prominent content than elevated cards
Ideal for selection interfaces and forms
Keep border widths subtle (1-2dp typically)
Match border color to theme or content purpose
Outlined cards work well in flat design patterns
Good alternative when avoiding shadows
Maintain adequate contrast between border and background
Works well for grouped content of equal importance
Consider outlined cards for accessibility (no shadow needed)
Use thicker borders to indicate selection or focus
*/

