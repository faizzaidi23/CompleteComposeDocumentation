package com.example.FullComposeOfficialDocumentation.Components_3.Buttons_5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
ELEVATED BUTTON

Stands out by having a shadow
Similar purpose to tonal button but uses elevation instead of color
Shadow effect provides visual hierarchy

WHEN TO USE:
- Medium emphasis actions on light backgrounds
- When tonal button doesn't provide enough contrast
- Actions that need to stand out without bold colors
- Alternatives to tonal buttons in your design system

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. modifier: Modifier
   Standard modifier for size, padding, positioning

3. enabled: Boolean (default = true)
   Controls interactive state

4. elevation: ButtonElevation
   Controls shadow depth in different states

5. colors: ButtonColors
   Defines colors for different button states

6. contentPadding: PaddingValues
   Internal padding around content
*/

@Composable
fun ElevatedButtonExample(onClick: () -> Unit) {
    ElevatedButton(onClick = onClick) {
        Text("Elevated Button")
    }
}

@Composable
fun ElevatedButtonWithElevationExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ElevatedButton(onClick = { /* action */ }) {
            Text("Default Elevation")
        }

        ElevatedButton(
            onClick = { /* action */ },
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 12.dp,
                hoveredElevation = 10.dp
            )
        ) {
            Text("Higher Elevation")
        }

        ElevatedButton(
            onClick = { /* action */ },
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 2.dp
            )
        ) {
            Text("Lower Elevation")
        }
    }
}

/*
elevation parameter controls shadow depth:
- defaultElevation: Normal state shadow
- pressedElevation: When button is pressed
- hoveredElevation: When mouse hovers (desktop)
- focusedElevation: When button has focus
- disabledElevation: When button is disabled

Higher elevation = more prominent shadow
*/

@Composable
fun ElevatedButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedButton(onClick = { /* action */ }) {
            Text("Default Colors")
        }

        ElevatedButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF6200EE)
            )
        ) {
            Text("Custom White")
        }

        ElevatedButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFFF5F5F5),
                contentColor = Color.Black
            )
        ) {
            Text("Custom Gray")
        }
    }
}

/*
ElevatedButton typically uses surface colors with elevation
Container color usually light or neutral
Content color provides contrast
Shadow does the visual work instead of bold colors
*/

@Composable
fun ElevatedButtonWithStateExample() {
    var isSelected by remember { mutableStateOf(false) }

    ElevatedButton(
        onClick = { isSelected = !isSelected },
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = if (isSelected) Color(0xFFE1BEE7) else Color.White,
            contentColor = if (isSelected) Color(0xFF6A1B9A) else Color.Black
        )
    ) {
        Text(if (isSelected) "Selected" else "Select")
    }
}

/*
Dynamic elevation useful for:
- Showing selected state
- Hover effects
- Focus indicators
- Progressive disclosure
*/

@Composable
fun ElevatedButtonCompleteExample() {
    var selectedOption by remember { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Select an option:", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)

        options.forEach { option ->
            ElevatedButton(
                onClick = { selectedOption = option },
                modifier = Modifier.fillMaxWidth(),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = if (selectedOption == option) 6.dp else 2.dp
                ),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = if (selectedOption == option) {
                        Color(0xFFE8DEF8)
                    } else {
                        Color.White
                    }
                )
            ) {
                Text(option)
            }
        }
    }
}

/*
COMPARISON WITH OTHER BUTTONS:

Filled Button:
- Bold solid colors
- Highest emphasis
- No shadow by default

Filled Tonal Button:
- Tonal colors
- No shadow
- Medium-high emphasis

Elevated Button:
- Uses shadow for emphasis
- Lighter colors
- Medium emphasis
- Best on light backgrounds

WHEN TO CHOOSE ELEVATED:
- Light background where tonal doesn't work
- Need visual separation without bold colors
- Subtle emphasis through shadow
- Clean, minimal design aesthetic

BEST PRACTICES:

Works best on light backgrounds
Shadow provides the emphasis
Use higher elevation for more importance
Can combine with color changes for states
Good for card actions
Avoid on dark backgrounds (shadow not visible)
Don't overuse - can look dated if too many
*/

