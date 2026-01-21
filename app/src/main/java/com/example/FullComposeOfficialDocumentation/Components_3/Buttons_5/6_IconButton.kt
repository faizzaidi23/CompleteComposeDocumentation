package com.example.FullComposeOfficialDocumentation.Components_3.Buttons_5

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/*
ICON BUTTON

Display actions users can take
Must use icon with clear meaning
Represents common or frequently used actions

TWO TYPES:
1. Default: Opens other elements (menu, search)
2. Toggle: Binary actions that can be toggled (favorite, bookmark)

WHEN TO USE:
- Common actions with universally recognized icons
- Toolbar actions
- Toggle states (favorite, bookmark)
- Navigation actions
- Secondary actions in cards or lists

API SURFACE PARAMETERS:

1. onClick: () -> Unit (required)
   Function executed when user taps button

2. modifier: Modifier
   Standard modifier for positioning and size

3. enabled: Boolean (default = true)
   Controls whether button responds to input

4. colors: IconButtonColors
   Defines icon color for different states

5. interactionSource: MutableInteractionSource
   Tracks user interactions (press, hover, focus)

6. content: @Composable () -> Unit (required)
   Icon composable inside the button
*/

@Composable
fun IconButtonExample(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
    }
}

@Composable
fun IconButtonWithEnabledExample() {
    var isEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = { /* action */ },
                enabled = isEnabled
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete")
            }

            IconButton(
                onClick = { /* action */ },
                enabled = isEnabled
            ) {
                Icon(Icons.Filled.Share, contentDescription = "Share")
            }

            IconButton(
                onClick = { /* action */ },
                enabled = isEnabled
            ) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit")
            }
        }

        Button(onClick = { isEnabled = !isEnabled }) {
            Text(if (isEnabled) "Disable Icons" else "Enable Icons")
        }
    }
}

/*
enabled parameter:
When false, icon appears grayed out
Button doesn't respond to clicks
Useful for conditional availability
*/

@Composable
fun IconButtonColorsExample() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(onClick = { /* action */ }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Default",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        IconButton(onClick = { /* action */ }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Red",
                tint = Color.Red
            )
        }

        IconButton(onClick = { /* action */ }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Green",
                tint = Color(0xFF4CAF50)
            )
        }
    }
}

/*
Color customization:
Use Icon's tint parameter for color
Can use theme colors or custom colors
Ensure sufficient contrast with background
*/

@Composable
fun ToggleIconButtonExample() {
    var isToggled by rememberSaveable { mutableStateOf(false) }

    IconButton(onClick = { isToggled = !isToggled }) {
        Icon(
            imageVector = if (isToggled) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = if (isToggled) "Remove from favorites" else "Add to favorites",
            tint = if (isToggled) Color.Red else MaterialTheme.colorScheme.onSurface
        )
    }
}

/*
Toggle pattern:
rememberSaveable preserves state across configuration changes
Change icon based on state (filled vs outlined)
Update contentDescription for accessibility
Optional: Change color to indicate state
*/

@Composable
fun ToggleIconButtonCompleteExample() {
    var isFavorite by rememberSaveable { mutableStateOf(false) }
    var isBookmarked by rememberSaveable { mutableStateOf(false) }
    var isArchived by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { isFavorite = !isFavorite }) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (isFavorite) "Unfavorite" else "Favorite",
                tint = if (isFavorite) Color.Red else Color.Gray
            )
        }

        IconButton(onClick = { isBookmarked = !isBookmarked }) {
            Icon(
                imageVector = if (isBookmarked) Icons.Filled.Star else Icons.Filled.Star,
                contentDescription = if (isBookmarked) "Remove bookmark" else "Bookmark",
                tint = if (isBookmarked) Color(0xFFFFC107) else Color.Gray
            )
        }

        IconButton(onClick = { isArchived = !isArchived }) {
            Icon(
                imageVector = if (isArchived) Icons.Filled.Check else Icons.Filled.MailOutline,
                contentDescription = if (isArchived) "Unarchive" else "Archive",
                tint = if (isArchived) Color.Green else Color.Gray
            )
        }
    }
}

/*
Multiple toggle buttons:
Each maintains independent state
Different icons and colors for different purposes
Clear visual feedback for each state
*/

@Composable
fun MomentaryIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    stepDelay: Long = 100L,
    content: @Composable (isPressed: Boolean) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressedListener by rememberUpdatedState(onClick)

    LaunchedEffect(isPressed) {
        while (isPressed) {
            delay(stepDelay.coerceIn(1L, Long.MAX_VALUE))
            pressedListener()
        }
    }

    IconButton(
        modifier = modifier,
        onClick = onClick,
        interactionSource = interactionSource
    ) {
        content(isPressed)
    }
}

/*
Momentary (hold) button:
Continuously triggers action while pressed
Uses interactionSource to track press state
LaunchedEffect loops while button held
delay creates interval between actions
rememberUpdatedState ensures latest onClick is used
*/

@Composable
fun MomentaryIconButtonExample() {
    var count by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MomentaryIconButton(
            onClick = { count-- },
            stepDelay = 100L
        ) { isPressed ->
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Decrease",
                tint = if (isPressed) Color.Red else Color.Gray
            )
        }

        Text(
            "Count: $count",
            style = MaterialTheme.typography.headlineMedium
        )

        MomentaryIconButton(
            onClick = { count++ },
            stepDelay = 100L
        ) { isPressed ->
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Increase",
                tint = if (isPressed) Color.Green else Color.Gray
            )
        }
    }
}

/*
Real-world use case for momentary buttons:
Volume control
Counter increment/decrement
Seek forward/backward in media
Zoom in/out continuously
Scroll through items

stepDelay controls repeat speed:
100L = 10 times per second
50L = 20 times per second (faster)
200L = 5 times per second (slower)
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconButtonInToolbarExample() {
    TopAppBar(
        title = { Text("Icon Buttons") },
        actions = {
            IconButton(onClick = { /* search */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /* filter */ }) {
                Icon(Icons.Filled.Star, contentDescription = "Filter")
            }
            IconButton(onClick = { /* more */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More options")
            }
        }
    )
}

/*
Icon buttons in TopAppBar:
Common pattern for toolbar actions
Use for frequently accessed features
Keep to 3-4 icons maximum
Overflow menu for additional actions
*/

/*
BEST PRACTICES:

Use clear, universally recognized icons
Provide meaningful contentDescription
Keep icon buttons 48dp minimum touch target
Use toggle pattern for binary states
Limit to essential actions
Group related icon buttons
Consider filled variants for emphasis
Use colors meaningfully (red for delete, etc.)
Don't use text labels (use tooltips instead)
Test with accessibility tools
*/

