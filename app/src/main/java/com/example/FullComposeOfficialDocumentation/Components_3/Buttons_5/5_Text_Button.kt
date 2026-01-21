package com.example.FullComposeOfficialDocumentation.Components_3.Buttons_5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
TEXT BUTTON

Displays text with no background or border
Lowest emphasis button for tertiary actions
Minimal visual weight, ideal for less critical actions

WHEN TO USE:
- Navigational links
- Tertiary actions
- Learn More, View Details, Skip
- Dialog dismiss actions
- Inline actions in dense layouts

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function called when button is pressed

2. modifier: Modifier
   Standard modifier for layout

3. enabled: Boolean (default = true)
   Controls interactive state

4. colors: ButtonColors
   Defines text color for different states

5. contentPadding: PaddingValues
   Internal padding (usually smaller than other buttons)
*/

@Composable
fun TextButtonExample(onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text("Text Button")
    }
}

@Composable
fun TextButtonWithColorsExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(onClick = { /* action */ }) {
            Text("Default Text Button")
        }

        TextButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Red
            )
        ) {
            Text("Red Text Button")
        }

        TextButton(
            onClick = { /* action */ },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color(0xFF1976D2)
            )
        ) {
            Text("Blue Link Style")
        }

        TextButton(
            onClick = { /* action */ },
            enabled = false
        ) {
            Text("Disabled Text Button")
        }
    }
}

/*
TextButton colors:
- contentColor: Text color (no container)
- Often uses primary color for links
- Disabled state appears faded
*/

@Composable
fun TextButtonNavigationExample() {
    var currentScreen by remember { mutableStateOf("Home") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Navigation Example:")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(onClick = { currentScreen = "Home" }) {
                Text("Home")
            }
            TextButton(onClick = { currentScreen = "About" }) {
                Text("About")
            }
            TextButton(onClick = { currentScreen = "Contact" }) {
                Text("Contact")
            }
        }

        Text("Current: $currentScreen")
    }
}

/*
Common use for navigation:
TextButtons work well for tab-like navigation
Minimal visual weight doesn't compete with content
Good for breadcrumbs and inline links
*/

@Composable
fun TextButtonDialogExample() {
    var showMessage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Dialog Pattern:")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { showMessage = false }) {
                Text("Cancel")
            }
            Spacer(Modifier.width(8.dp))
            androidx.compose.material3.Button(onClick = { showMessage = true }) {
                Text("OK")
            }
        }

        if (showMessage) {
            Text("Action confirmed!")
        }
    }
}

/*
Dialog action pattern:
- TextButton for dismiss/cancel (left or secondary)
- Filled/Tonal button for confirm (right or primary)
Aligns with Material Design dialog guidelines
*/

@Composable
fun TextButtonInlineExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Product Description")
        Text("This is a brief preview of the product details...")

        TextButton(
            onClick = { /* navigate to details */ },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("Read more")
        }

        Spacer(Modifier.height(8.dp))

        Text("Need help?")
        TextButton(
            onClick = { /* open support */ },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text("Contact support")
        }
    }
}

/*
contentPadding = PaddingValues(0.dp):
Removes default padding for inline text buttons
Makes them look like clickable text links
Useful for "Read more", "Learn more" patterns
*/

@Composable
fun TextButtonCompleteExample() {
    var agreedToTerms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Sign Up Form")

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            androidx.compose.material3.Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it }
            )
            Text("I agree to the")
            TextButton(
                onClick = { /* show terms */ },
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                Text("Terms of Service")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = { /* skip */ }) {
                Text("Skip")
            }

            androidx.compose.material3.Button(
                onClick = { /* sign up */ },
                enabled = agreedToTerms
            ) {
                Text("Sign Up")
            }
        }
    }
}

/*
HIERARCHY OF BUTTON EMPHASIS:

Highest → Filled Button
↓
High → Filled Tonal Button
↓
Medium → Elevated Button
↓
Medium-Low → Outlined Button
↓
Lowest → Text Button

WHEN TO USE TEXT BUTTON:

Appropriate:
- Navigation links
- Dialog dismiss
- "Learn more", "View details"
- Inline actions
- Skip options
- Less important actions

Not appropriate:
- Primary actions
- Important confirmations
- Destructive actions (use outlined instead)
- When action needs to stand out

BEST PRACTICES:

Use sparingly for low-priority actions
Good for navigation and links
Perfect for dialog cancel/dismiss
Remove padding for inline links
Keep text concise
Use color to indicate interactivity (primary color)
Don't use for critical actions
Pair with higher emphasis buttons
*/

