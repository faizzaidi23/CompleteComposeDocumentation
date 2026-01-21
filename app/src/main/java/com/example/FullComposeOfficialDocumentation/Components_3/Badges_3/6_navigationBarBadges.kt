package com.example.FullComposeOfficialDocumentation.Components_3.Badges_3

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
═══════════════════════════════════════════════════════════════════════════════
                    NAVIGATION BAR WITH BADGES
═══════════════════════════════════════════════════════════════════════════════

Real-world pattern showing badges in navigation components

EXAMPLES IN THIS FILE:
1. Bottom NavigationBar with badges
2. NavigationRail with badges
3. NavigationDrawer with badges
4. Best practices for navigation badges

This is the most common real-world use case for badges (Gmail, WhatsApp, etc.)

═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun NavigationBarWithBadgesExample() {
    var selectedItem by remember { mutableStateOf(0) }
    val notificationCount = 12
    val messageCount = 3

    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            },
            label = { Text("Home") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
        )

        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        if (messageCount > 0) {
                            Badge { Text("$messageCount") }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Email, contentDescription = "Messages")
                }
            },
            label = { Text("Messages") },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 }
        )

        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        if (notificationCount > 0) {
                            Badge {
                                Text(
                                    if (notificationCount > 99) "99+"
                                    else "$notificationCount"
                                )
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
            },
            label = { Text("Alerts") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )

        NavigationBarItem(
            icon = {
                Icon(Icons.Filled.Person, contentDescription = "Profile")
            },
            label = { Text("Profile") },
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 }
        )
    }
}

/*
EXPLANATION - REAL-WORLD NAVIGATION PATTERN:

NavigationBarItem with badges:
- Messages tab: Badge with count "3"
- Notifications tab: Badge with count "12"
- Home and Profile: No badges

STRUCTURE:
NavigationBarItem(
    icon = {                           // Icon parameter
        BadgedBox(                     // Container for icon + badge
            badge = {                  // Badge parameter
                if (count > 0) {       // Conditional display
                    Badge {            // Badge composable
                        Text("$count") // Badge content
                    }
                }
            }
        ) {
            Icon(...)                  // Icon content
        }
    },
    label = { Text("Label") },
    selected = ...,
    onClick = { ... }
)

KEY TECHNIQUES:
1. Conditional display: if (count > 0)
   - Don't show badge when count is zero
   - Clean UI when no notifications

2. Large number handling: "99+" for counts over 99
   - Keeps badge compact
   - Common pattern in mobile apps

3. Badge integrates seamlessly with NavigationBarItem
   - Wraps Icon inside BadgedBox
   - Badge appears in correct position
   - Works with selected state

EXAMPLES IN APPS:
- Gmail: Unread email count on Mail tab
- WhatsApp: Unread message count on Chats tab
- Instagram: Notification count on Alerts tab
- Twitter: Mention count on Notifications tab
*/

@Composable
fun NavigationBarWithDifferentBadgeTypesExample() {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
        )

        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge(containerColor = Color.Blue) {
                            Text("5")
                        }
                    }
                ) {
                    Icon(Icons.Filled.Email, contentDescription = "Messages")
                }
            },
            label = { Text("Messages") },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 }
        )

        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge(containerColor = Color.Red)
                    }
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Alerts")
                }
            },
            label = { Text("Alerts") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )

        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge(
                            containerColor = Color.Green,
                            contentColor = Color.White
                        ) {
                            Text("NEW")
                        }
                    }
                ) {
                    Icon(Icons.Filled.Star, contentDescription = "Features")
                }
            },
            label = { Text("Features") },
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 }
        )
    }
}

/*
DIFFERENT BADGE TYPES IN NAVIGATION:

1. NUMBERED BADGE (Blue - Messages):
   Badge(containerColor = Color.Blue) { Text("5") }
   - Shows count of messages
   - Blue = informational

2. DOT BADGE (Red - Alerts):
   Badge(containerColor = Color.Red)
   - Generic "you have alerts" indicator
   - No specific count
   - Red = urgent attention needed

3. LABELED BADGE (Green - New Features):
   Badge(containerColor = Color.Green) { Text("NEW") }
   - Highlights new content
   - Green = positive/new
   - Text label instead of number

COLOR MEANINGS IN NAVIGATION:
- Red: Urgent notifications, errors
- Blue: Messages, informational
- Green: New features, success
- Default (theme error): Standard notifications
*/

@Composable
fun NavigationRailWithBadgesExample() {
    var selectedItem by remember { mutableStateOf(0) }
    val messageCount = 7

    NavigationRail {
        NavigationRailItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
        )

        NavigationRailItem(
            icon = {
                BadgedBox(
                    badge = {
                        if (messageCount > 0) {
                            Badge { Text("$messageCount") }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Email, contentDescription = "Messages")
                }
            },
            label = { Text("Messages") },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 }
        )

        NavigationRailItem(
            icon = {
                BadgedBox(
                    badge = { Badge() }
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
            },
            label = { Text("Alerts") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )

        NavigationRailItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 }
        )
    }
}

/*
NAVIGATION RAIL WITH BADGES:

NavigationRail is used for:
- Tablet layouts
- Mid-size screens
- Side navigation

Badge usage is IDENTICAL to NavigationBar:
- Same BadgedBox pattern
- Same icon wrapping
- Same conditional logic
- Just different navigation component

The pattern works across ALL navigation types:
- NavigationBar (bottom navigation - phones)
- NavigationRail (side navigation - tablets)
- NavigationDrawer (drawer navigation - large screens)
*/

@Composable
fun CompleteNavigationWithBadgesExample() {
    var selectedItem by remember { mutableStateOf(0) }
    var cartCount by remember { mutableStateOf(2) }
    var messageCount by remember { mutableStateOf(5) }
    var notificationCount by remember { mutableStateOf(8) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    },
                    label = { Text("Home") },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 }
                )

                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (cartCount > 0) {
                                    Badge { Text("$cartCount") }
                                }
                            }
                        ) {
                            Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
                        }
                    },
                    label = { Text("Cart") },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 }
                )

                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (messageCount > 0) {
                                    Badge(containerColor = Color.Blue) {
                                        Text("$messageCount")
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Email, contentDescription = "Messages")
                        }
                    },
                    label = { Text("Messages") },
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 }
                )

                NavigationBarItem(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (notificationCount > 0) {
                                    Badge {
                                        Text(
                                            if (notificationCount > 99) "99+"
                                            else "$notificationCount"
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                        }
                    },
                    label = { Text("Alerts") },
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Complete Navigation Example", style = MaterialTheme.typography.titleLarge)

            Button(onClick = { cartCount++ }) {
                Text("Add to Cart")
            }
            Button(onClick = { messageCount++ }) {
                Text("New Message")
            }
            Button(onClick = { notificationCount++ }) {
                Text("New Notification")
            }
            Button(onClick = {
                cartCount = 0
                messageCount = 0
                notificationCount = 0
            }) {
                Text("Clear All Badges")
            }
        }
    }
}

/*
COMPLETE REAL-WORLD EXAMPLE:

This shows a typical e-commerce or social app with:
- Shopping cart count
- Unread message count
- Notification count

INTERACTIVE FEATURES:
- Buttons to increment counts
- Badges update automatically
- "Clear All" to reset all badges

STATE MANAGEMENT:
- Each badge has its own state
- State updates trigger recomposition
- Badges conditionally show/hide

BEST PRACTICES DEMONSTRATED:
✓ Conditional display (if count > 0)
✓ Large number handling (99+)
✓ Different colors for different types
✓ Clean UI when no notifications
✓ Interactive state management
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          BEST PRACTICES FOR NAVIGATION BADGES
═══════════════════════════════════════════════════════════════════════════════

1. WHEN TO USE BADGES IN NAVIGATION:
   ✓ Message/email counts
   ✓ Notification counts
   ✓ Shopping cart items
   ✓ Pending actions
   ✓ New content indicators

   ✗ Don't overuse - too many badges = cluttered
   ✗ Maximum 2-3 badged items in navigation

2. BADGE DISPLAY RULES:
   - Show badge only when count > 0
   - Use "99+" for large numbers
   - Hide badge after user views content
   - Update in real-time when count changes

3. COLOR CODING:
   - Red: Urgent/important (default)
   - Blue: Informational messages
   - Green: New/positive updates
   - Consistent colors = better UX

4. ACCESSIBILITY:
   - Provide contentDescription for icons
   - Don't rely solely on badge color
   - Consider screen readers
   - Ensure sufficient contrast

5. STATE MANAGEMENT:
   - Track counts in ViewModel (not in composable)
   - Update from data layer (Room, API, etc.)
   - Use StateFlow or LiveData for reactive updates
   - Persist important counts (survive app restart)

6. USER EXPERIENCE:
   - Clear badges when user views content
   - Provide way to "mark all read"
   - Don't show "0" - hide badge instead
   - Keep badge text short (max 3-4 chars)

7. PERFORMANCE:
   - Update only when count actually changes
   - Use remember to avoid unnecessary recomposition
   - Conditional rendering is efficient
   - Don't animate badge changes (distracting)

═══════════════════════════════════════════════════════════════════════════════
*/

