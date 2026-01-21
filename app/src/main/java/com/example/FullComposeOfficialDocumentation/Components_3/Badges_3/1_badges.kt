package com.example.FullComposeOfficialDocumentation.Components_3.Badges_3

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
═══════════════════════════════════════════════════════════════════════════════
                                BADGES
═══════════════════════════════════════════════════════════════════════════════

Badges display small visual elements to denote status or numeric values on other composables

COMMON USE CASES:
- Notifications: Display count of unread notifications on app icon or bell
- Messages: Indicate new or unread messages in chat application
- Status updates: Show task status (complete, in progress, failed)
- Cart Quantity: Display number of items in shopping cart
- New content: Highlight new features or content available to user

═══════════════════════════════════════════════════════════════════════════════
                              API SURFACE
═══════════════════════════════════════════════════════════════════════════════

BADGEDBOX COMPOSABLE:
Container for implementing badges in your application

KEY PARAMETERS:
1. badge: (required) The composable badge that appears over the content
   - Usually the Badge() composable
   - Can contain text (numbers, labels) or be empty (just a dot)

2. content: (required) The composable that the badge overlaps
   - Typically an Icon
   - Can be any composable (Button, Image, etc.)

3. modifier: (optional) Modifier for the BadgedBox container


BADGE COMPOSABLE:
The actual badge that appears over content

KEY PARAMETERS:
1. content: (optional) What displays inside the badge
   - Empty = Small red dot (notification indicator)
   - Text = Number or label inside badge

2. containerColor: (optional) Background color of the badge
   - Default: MaterialTheme error color (red)

3. contentColor: (optional) Color of content inside badge
   - Default: Color that contrasts with containerColor

4. modifier: (optional) Modifier for the Badge

═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// BASIC BADGE - Small dot indicator (no content)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun BasicBadgeExample() {
    BadgedBox(
        badge = {
            Badge()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Notifications,
            contentDescription = "Notifications"
        )
    }
}

/*
EXPLANATION:
- BadgedBox: Container that positions badge over icon
- badge = { Badge() }: Creates empty badge (small red dot)
  - No content parameter = just a dot
  - Uses default red color
- content (the Icon): What the badge appears on top of
  - Mail icon in this case
  - Badge automatically positions at top-right corner
*/

// ─────────────────────────────────────────────────────────────────────────────
// BADGE WITH NUMBER - Shows count/quantity
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun BadgeWithNumberExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(
            badge = {
                Badge {
                    Text("3")
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Shopping cart"
            )
        }

        BadgedBox(
            badge = {
                Badge {
                    Text("99+")
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email"
            )
        }

        BadgedBox(
            badge = {
                Badge {
                    Text("12")
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications"
            )
        }
    }
}

/*
EXPLANATION:
- Badge { Text("3") }: Badge content parameter
  - Text composable inside shows the number
  - Badge automatically sizes to fit content
  - "99+" common pattern for large numbers

PARAMETERS USED:
- badge parameter: Contains Badge() composable
- Badge's content parameter: Contains Text() with number
- Result: Badge with number displays over icon
*/

// ─────────────────────────────────────────────────────────────────────────────
// CUSTOM COLORED BADGE - Using containerColor parameter
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun CustomColorBadgeExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ) {
                    Text("5")
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Messages"
            )
        }

        BadgedBox(
            badge = {
                Badge(
                    containerColor = Color.Green,
                    contentColor = Color.Black
                ) {
                    Text("NEW")
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Featured"
            )
        }

        BadgedBox(
            badge = {
                Badge(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            }
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Status"
            )
        }
    }
}

/*
EXPLANATION OF PARAMETERS:

Badge(
    containerColor = Color.Blue,     // Background color of badge
    contentColor = Color.White       // Color of text/content inside
) {
    Text("5")                        // Content shown in badge
}

PARAMETERS BREAKDOWN:
1. containerColor: Badge background color
   - Color.Blue, Color.Green, etc.
   - MaterialTheme.colorScheme.tertiary (theme-based)
   - Default is error color (red)

2. contentColor: Text/icon color inside badge
   - Must contrast with containerColor
   - Default automatically chosen for accessibility

3. Content block: What displays inside
   - Text with numbers
   - Text with labels ("NEW", "HOT")
   - Can be empty for dot badge
*/

// ─────────────────────────────────────────────────────────────────────────────
// CONDITIONAL BADGE - Show/hide based on state
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ConditionalBadgeExample() {
    var unreadCount by remember { mutableStateOf(5) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(
            badge = {
                if (unreadCount > 0) {
                    Badge {
                        Text("$unreadCount")
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email",
                modifier = Modifier.size(48.dp)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { unreadCount++ }) {
                Text("Add")
            }
            Button(
                onClick = { if (unreadCount > 0) unreadCount-- },
                enabled = unreadCount > 0
            ) {
                Text("Remove")
            }
            Button(
                onClick = { unreadCount = 0 },
                enabled = unreadCount > 0
            ) {
                Text("Clear All")
            }
        }

        Text("Unread messages: $unreadCount")
    }
}

/*
EXPLANATION:
- Conditional badge display using if statement
- When unreadCount > 0: Badge appears with count
- When unreadCount = 0: No badge (if block doesn't execute)

KEY CONCEPT:
badge = {
    if (condition) {
        Badge { ... }    // Badge shows when condition is true
    }
    // No else needed - no badge when false
}

REAL-WORLD USAGE:
- Only show badge when there are notifications
- Hide badge when user has read all messages
- Display status badge only when relevant
*/

// ─────────────────────────────────────────────────────────────────────────────
// BADGES ON DIFFERENT COMPOSABLES - Not just icons
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun BadgeOnDifferentComposablesExample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(
            badge = {
                Badge { Text("3") }
            }
        ) {
            Button(onClick = { /* action */ }) {
                Text("Shopping Cart")
            }
        }

        BadgedBox(
            badge = {
                Badge(containerColor = Color.Red)
            }
        ) {
            Card(
                modifier = Modifier.size(80.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Item")
                }
            }
        }

        BadgedBox(
            badge = {
                Badge { Text("NEW") }
            }
        ) {
            Text(
                "Feature",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

/*
EXPLANATION:
BadgedBox works with ANY composable, not just icons:

1. Badge on Button
   - Shows cart item count on button

2. Badge on Card
   - Indicates special status on card

3. Badge on Text
   - Highlights new feature

KEY POINT:
The 'content' parameter of BadgedBox can be:
- Icon (most common)
- Button
- Card
- Text
- Image
- Any other composable

Badge automatically positions at top-right corner of content
*/

// ─────────────────────────────────────────────────────────────────────────────
// NAVIGATION BAR WITH BADGES - Common real-world pattern
// ─────────────────────────────────────────────────────────────────────────────

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
EXPLANATION - REAL-WORLD USAGE PATTERN:

NavigationBarItem with badges shows:
1. Messages tab: Badge with count "3"
2. Notifications tab: Badge with count "12"
3. Home and Profile: No badges

KEY TECHNIQUES:
- Conditional display: if (count > 0)
- Large number handling: "99+" for counts over 99
- Badge integrates seamlessly with NavigationBarItem

PARAMETERS RECAP:
NavigationBarItem(
    icon = {                           // Icon parameter
        BadgedBox(                     // Container for icon + badge
            badge = {                  // Badge parameter
                Badge {                // Badge composable
                    Text("$count")     // Badge content
                }
            }
        ) {
            Icon(...)                  // Icon content
        }
    }
)
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          PARAMETER SUMMARY
═══════════════════════════════════════════════════════════════════════════════

BADGEDBOX PARAMETERS:
┌─────────────────┬──────────────┬────────────────────────────────────────┐
│ Parameter       │ Type         │ Description                            │
├─────────────────┼──────────────┼────────────────────────────────────────┤
│ badge           │ @Composable  │ The badge composable (required)        │
│                 │              │ Usually Badge() with or without content│
├─────────────────┼──────────────┼────────────────────────────────────────┤
│ content         │ @Composable  │ What the badge appears on (required)   │
│                 │              │ Icon, Button, Text, any composable     │
├─────────────────┼──────────────┼────────────────────────────────────────┤
│ modifier        │ Modifier     │ Styling for BadgedBox container        │
└─────────────────┴──────────────┴────────────────────────────────────────┘

BADGE PARAMETERS:
┌─────────────────┬──────────────┬────────────────────────────────────────┐
│ Parameter       │ Type         │ Description                            │
├─────────────────┼──────────────┼────────────────────────────────────────┤
│ content         │ @Composable  │ What shows inside badge (optional)     │
│                 │              │ Empty = dot, Text = number/label       │
├─────────────────┼──────────────┼────────────────────────────────────────┤
│ containerColor  │ Color        │ Background color of badge              │
│                 │              │ Default: MaterialTheme error color     │
├─────────────────┼──────────────┼────────────────────────────────────────┤
│ contentColor    │ Color        │ Color of text/content inside badge     │
│                 │              │ Default: contrasts with containerColor │
├─────────────────┼──────────────┼────────────────────────────────────────┤
│ modifier        │ Modifier     │ Styling for Badge                      │
└─────────────────┴──────────────┴────────────────────────────────────────┘

USAGE PATTERNS:

1. EMPTY BADGE (Dot):
   Badge()
   - No parameters = small red dot
   - Use for: status indicator, "has updates" flag

2. NUMBERED BADGE:
   Badge { Text("5") }
   - Shows count inside badge
   - Use for: unread count, cart quantity

3. LABELED BADGE:
   Badge { Text("NEW") }
   - Shows label inside badge
   - Use for: new feature, special status

4. CUSTOM COLOR BADGE:
   Badge(
       containerColor = Color.Blue,
       contentColor = Color.White
   ) { Text("3") }
   - Custom colors for different meanings
   - Use for: different priority levels, categories

5. CONDITIONAL BADGE:
   badge = {
       if (count > 0) {
           Badge { Text("$count") }
       }
   }
   - Shows badge only when needed
   - Use for: hide when no notifications

                          BEST PRACTICES


1. NUMBERS
   - Use "99+" for counts over 99 to keep badge compact
   - Don't show "0" - hide badge instead

2. COLORS
   - Red: urgent/errors (default)
   - Blue: informational
   - Green: success/complete
   - Use theme colors for consistency

3. POSITIONING
   - Badge automatically positions at top-right
   - Works with any content size
   - Overlaps content by design

4. ACCESSIBILITY
   - Provide contentDescription for icons
   - Use sufficient color contrast
   - Don't rely on color alone for meaning

5. PERFORMANCE
   - Use conditional rendering (if statement)
   - Update badge state efficiently
   - Avoid unnecessary recomposition


*/
