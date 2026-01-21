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
                    BADGES ON DIFFERENT COMPOSABLES
═══════════════════════════════════════════════════════════════════════════════

BadgedBox works with ANY composable, not just icons

EXAMPLES IN THIS FILE:
1. Badge on Button
2. Badge on Card
3. Badge on Text
4. Badge on custom composables

KEY CONCEPT:
The 'content' parameter of BadgedBox accepts any @Composable, giving you flexibility
to add badges to various UI elements

═══════════════════════════════════════════════════════════════════════════════
*/

@Composable
fun BadgeOnButtonExample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Badge on Button:", style = MaterialTheme.typography.titleMedium)

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
                Badge(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ) {
                    Text("NEW")
                }
            }
        ) {
            ElevatedButton(onClick = { /* action */ }) {
                Text("Premium Features")
            }
        }
    }
}

/*
EXPLANATION:
Badge on Button shows:
- Cart item count directly on shopping cart button
- "NEW" label on button for new features
- Badge positioned at top-right of button

WHEN TO USE:
- Shopping cart with item count
- Notification settings button with unread count
- Feature button with "NEW" or "BETA" label
- Action button with pending items count

ADVANTAGES:
- User sees count without separate icon
- Clear call-to-action with context
- Saves screen space
*/

@Composable
fun BadgeOnCardExample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Badge on Card:", style = MaterialTheme.typography.titleMedium)

        BadgedBox(
            badge = {
                Badge(containerColor = Color.Red)
            }
        ) {
            Card(
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Product")
                }
            }
        }

        BadgedBox(
            badge = {
                Badge(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ) {
                    Text("SALE")
                }
            }
        ) {
            ElevatedCard(
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Email, contentDescription = null)
                        Text("Item")
                    }
                }
            }
        }

        BadgedBox(
            badge = {
                Badge { Text("50% OFF") }
            }
        ) {
            OutlinedCard(
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp),
                onClick = { /* action */ }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Special Offer")
                }
            }
        }
    }
}

/*
EXPLANATION:
Badge on Card shows:
- Status indicator (red dot)
- Promotional labels ("SALE", "50% OFF")
- Special markings on product cards

WHEN TO USE:
- E-commerce product cards with sale badges
- Content cards with "NEW" or "UPDATED" labels
- Status indicators (available, sold out, etc.)
- Featured items in lists

REAL-WORLD EXAMPLES:
- Shopping apps: "Sale" badge on product cards
- News apps: "Breaking" badge on article cards
- Social media: "Live" badge on video cards
- Task apps: Priority badges on task cards
*/

@Composable
fun BadgeOnTextExample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Badge on Text:", style = MaterialTheme.typography.titleMedium)

        BadgedBox(
            badge = {
                Badge { Text("NEW") }
            }
        ) {
            Text(
                "New Feature Available",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        BadgedBox(
            badge = {
                Badge(containerColor = Color.Blue) { Text("BETA") }
            }
        ) {
            Text(
                "Experimental Settings",
                style = MaterialTheme.typography.titleMedium
            )
        }

        BadgedBox(
            badge = {
                Badge(containerColor = Color.Green)
            }
        ) {
            Text("Online Status", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/*
EXPLANATION:
Badge on Text shows:
- "NEW" badge next to feature names
- "BETA" badge for experimental features
- Status dots next to text labels

WHEN TO USE:
- Menu items with new feature indicators
- Settings options with beta labels
- User status text with online/offline dots
- List items with special markers

ADVANTAGE:
- Draws attention to specific text
- Indicates special status
- Minimal visual clutter
*/

@Composable
fun BadgeOnMixedComposablesExample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Various Composables with Badges:", style = MaterialTheme.typography.titleMedium)

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    Badge { Text("5") }
                }
            ) {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = "Messages",
                    modifier = Modifier.size(32.dp)
                )
            }

            BadgedBox(
                badge = {
                    Badge { Text("NEW") }
                }
            ) {
                TextButton(onClick = { /* action */ }) {
                    Text("Check Updates")
                }
            }

            BadgedBox(
                badge = {
                    Badge(containerColor = Color.Red)
                }
            ) {
                IconButton(onClick = { /* action */ }) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Alerts")
                }
            }
        }

        BadgedBox(
            badge = {
                Badge(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ) {
                    Text("PRO")
                }
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(Icons.Filled.Star, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Premium Feature")
            }
        }

        BadgedBox(
            badge = {
                Badge { Text("3") }
            }
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = "Cart",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

/*
EXPLANATION:
BadgedBox is a container that can wrap ANY composable:

WORKS WITH:
✓ Icon - Most common use case
✓ Button, TextButton, IconButton - Action elements
✓ Card, ElevatedCard, OutlinedCard - Content containers
✓ Text - Labels and titles
✓ Box, Row, Column - Layout containers
✓ Custom composables - Any @Composable function

HOW IT WORKS:
BadgedBox(
    badge = { Badge { ... } }    // What appears as badge
) {
    AnyComposable()              // What badge appears on top of
}

POSITIONING:
- Badge always appears at top-right corner
- Automatically adjusts to content size
- Overlaps content by design (intentional)
- Works with any content dimensions
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          KEY CONCEPTS
═══════════════════════════════════════════════════════════════════════════════

1. UNIVERSAL CONTAINER:
   BadgedBox works with any composable:
   - Icons (most common)
   - Buttons
   - Cards
   - Text
   - Custom composables
   - Layout containers

2. AUTOMATIC POSITIONING:
   - Badge always top-right
   - Works with any content size
   - No manual positioning needed
   - Overlaps content intentionally

3. USE CASES BY COMPOSABLE TYPE:

   Icons:
   - Notification counts
   - Status indicators
   - Message counts

   Buttons:
   - Shopping cart item count
   - Pending actions
   - New feature labels

   Cards:
   - Sale/promotion badges
   - Status labels
   - Priority indicators

   Text:
   - "NEW" feature markers
   - "BETA" labels
   - Status dots

4. FLEXIBILITY:
   - Mix different badge types on same screen
   - Different colors for different meanings
   - Numbered or labeled badges
   - Conditional display

5. DESIGN CONSIDERATIONS:
   - Badge should enhance, not overwhelm
   - Keep badge content short
   - Use consistent colors/meanings
   - Consider spacing around badged elements

═══════════════════════════════════════════════════════════════════════════════
*/

