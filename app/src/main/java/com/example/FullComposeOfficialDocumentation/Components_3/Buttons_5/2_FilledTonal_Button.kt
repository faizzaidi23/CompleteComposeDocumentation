package com.example.FullComposeOfficialDocumentation.Components_3.Buttons_5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
FILLED TONAL BUTTON

Background color varies to match the surface
Primary or significant actions with softer emphasis than filled button
More visual weight than outlined/text buttons

WHEN TO USE:
- Primary actions that need less emphasis than filled button
- Add to cart, Sign in, Continue
- When you need multiple prominent buttons on screen
- Actions that are important but not the absolute main focus

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. modifier: Modifier
   Standard modifier for size, padding, etc.

3. enabled: Boolean (default = true)
   Controls whether button is interactive

4. colors: ButtonColors
   Defines tonal colors for different states

5. contentPadding: PaddingValues
   Internal padding around button content
*/

@Composable
fun FilledTonalButtonExample(onClick: () -> Unit) {
    FilledTonalButton(onClick = onClick) {
        Text("Tonal Button")
    }
}

@Composable
fun FilledTonalButtonWithEnabledExample() {
    var acceptedTerms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton(
            onClick = { /* proceed */ },
            enabled = acceptedTerms
        ) {
            Text("Continue")
        }

        FilledTonalButton(onClick = { acceptedTerms = !acceptedTerms }) {
            Text(if (acceptedTerms) "Terms Accepted" else "Accept Terms")
        }
    }
}

/*
enabled state often tied to form validation or prerequisites
Tonal button appears softer when disabled compared to filled button
*/

@Composable
fun FilledTonalButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton(onClick = { /* action */ }) {
            Text("Default Tonal")
        }

        FilledTonalButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(0xFFE8DEF8),
                contentColor = Color(0xFF6750A4)
            )
        ) {
            Text("Custom Tonal Purple")
        }

        FilledTonalButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(0xFFFFE082),
                contentColor = Color(0xFF6D4C00)
            )
        ) {
            Text("Custom Tonal Amber")
        }
    }
}

/*
FilledTonalButtonColors provides tonal color scheme:
Uses lighter background with darker content for softer appearance
containerColor typically uses secondary or tertiary container color
contentColor matches the content on that container
*/

@Composable
fun FilledTonalButtonWithPaddingExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton(
            onClick = { /* action */ },
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 20.dp)
        ) {
            Text("Large Tonal Button")
        }

        FilledTonalButton(
            onClick = { /* action */ },
            contentPadding = PaddingValues(12.dp)
        ) {
            Text("Compact")
        }
    }
}

/*
contentPadding useful for:
Making buttons more prominent with larger touch targets
Creating compact buttons for dense layouts
Matching design system specifications
*/

@Composable
fun FilledTonalButtonCompleteExample() {
    var itemsInCart by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton(
            onClick = { itemsInCart++ },
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text("Add to Cart ($itemsInCart)")
        }

        FilledTonalButton(
            onClick = { itemsInCart = 0 },
            enabled = itemsInCart > 0,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(0xFFFFF8E1),
                contentColor = Color(0xFFF57F17)
            )
        ) {
            Text("Clear")
        }
    }
}

/*
COMPARISON WITH FILLED BUTTON:

Filled Button:
- Solid, bold colors
- Highest visual emphasis
- One per screen recommended
- Primary action only

Filled Tonal Button:
- Softer, tonal colors
- Medium-high emphasis
- Can use multiple on screen
- Primary and important secondary actions
- Better for multiple prominent actions

BEST PRACTICES:

Use when you need multiple important buttons
Pair with filled button (tonal for secondary primary action)
Good for e-commerce (Add to Cart, Buy Now)
Works well in card actions
Softer alternative when filled button too strong
Keep text concise and action-oriented
*/

