package com.example.FullComposeOfficialDocumentation.Components_3.Badges_3

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
═══════════════════════════════════════════════════════════════════════════════
                          BASIC BADGES
═══════════════════════════════════════════════════════════════════════════════

Simple badge implementations showing dot indicator and numbered badges

EXAMPLES IN THIS FILE:
1. Empty badge (dot indicator)
2. Numbered badges with counts

═══════════════════════════════════════════════════════════════════════════════
*/

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
  - Notification icon in this case
  - Badge automatically positions at top-right corner

WHEN TO USE:
- Generic notification indicator
- "You have updates" flag
- Status indicator without specific count
*/

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
BadgedBox(
    badge = {                    // Lambda containing Badge composable
        Badge {                  // Badge composable
            Text("3")           // Content shown inside badge
        }
    }
) {
    Icon(...)                   // Content badge appears over
}

COMMON PATTERNS:
- Single digit: "3", "5", "9"
- Double digit: "12", "25", "50"
- Large numbers: "99+" (keeps badge compact)

WHEN TO USE:
- Shopping cart item count
- Unread message count
- Notification count
- Any numeric indicator
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          KEY CONCEPTS
═══════════════════════════════════════════════════════════════════════════════

1. EMPTY BADGE vs NUMBERED BADGE:
   - Badge() = Dot (no content)
   - Badge { Text("5") } = Number badge

2. BADGE SIZING:
   - Automatically sizes to fit content
   - Dot badge is small and circular
   - Text badge expands to fit text width

3. DEFAULT APPEARANCE:
   - Red background (error color from theme)
   - White text (auto-contrasted)
   - Top-right positioning over content

4. CONTENT PARAMETER:
   Badge {
       // This is the content parameter
       Text("Label or number here")
   }

═══════════════════════════════════════════════════════════════════════════════
*/

