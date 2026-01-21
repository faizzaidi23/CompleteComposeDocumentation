package com.example.FullComposeOfficialDocumentation.Components_3.Buttons_5

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

/*
FILLED BUTTON

Solid background with contrasting text
High-emphasis button for primary actions (submit, save, confirm)
Most prominent button type - use sparingly for main action

WHEN TO USE:
- Primary action in a form or dialog
- Main call-to-action on a screen
- Actions that complete a flow (Save, Submit, Confirm)
- Only one per screen recommended

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. modifier: Modifier
   Standard modifier for size, padding, etc.

3. enabled: Boolean (default = true)
   When false, button appears disabled and non-interactive

4. colors: ButtonColors
   Defines container and content colors for different states

5. contentPadding: PaddingValues
   Internal padding around the content
*/

@Composable
fun FilledButtonExample(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Filled Button")
    }
}

@Composable
fun FilledButtonWithEnabledExample() {
    var enabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { /* action */ },
            enabled = enabled
        ) {
            Text(if (enabled) "Enabled Button" else "Disabled Button")
        }

        Button(onClick = { enabled = !enabled }) {
            Text("Toggle State")
        }
    }
}

/*
enabled parameter:
- true: Button is clickable and fully colored
- false: Button appears grayed out and ignores clicks
- Useful for form validation or conditional actions
*/

@Composable
fun FilledButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { /* action */ }) {
            Text("Default Colors")
        }

        Button(
            onClick = { /* action */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text("Custom Red")
        }

        Button(
            onClick = { /* action */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            enabled = false
        ) {
            Text("Disabled Custom")
        }
    }
}

/*
colors parameter uses ButtonColors which defines:
- containerColor: Background color when enabled
- contentColor: Text/icon color when enabled
- disabledContainerColor: Background when disabled
- disabledContentColor: Text/icon color when disabled
*/

@Composable
fun FilledButtonWithContentPaddingExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { /* action */ }) {
            Text("Default Padding")
        }

        Button(
            onClick = { /* action */ },
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text("Large Padding")
        }

        Button(
            onClick = { /* action */ },
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text("Small Padding")
        }
    }
}

/*
contentPadding parameter:
Controls spacing inside the button around the content
Default is ButtonDefaults.ContentPadding
Useful for making buttons larger or more compact

Note: Different from modifier padding which affects outside spacing
*/

@Composable
fun FilledButtonCompleteExample() {
    var isProcessing by remember { mutableStateOf(false) }

    Button(
        onClick = {
            isProcessing = true
            // Simulate processing
        },
        enabled = !isProcessing,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE),
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(if (isProcessing) "Processing..." else "Submit Form")
    }
}

/*
BEST PRACTICES:

Use for primary actions only
One filled button per screen
Pair with outlined/text buttons for secondary actions
Make onClick handlers clear and concise
Use enabled state for validation
Keep button text short and action-oriented
*/
