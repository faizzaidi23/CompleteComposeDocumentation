package com.example.FullComposeOfficialDocumentation.Components_3.Badges_3

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
═══════════════════════════════════════════════════════════════════════════════
                          CONDITIONAL BADGES
═══════════════════════════════════════════════════════════════════════════════

Show or hide badges based on state/conditions

EXAMPLES IN THIS FILE:
1. Show badge only when count > 0
2. Interactive example with state management
3. Different conditions for showing badges

KEY CONCEPT:
Use if statements in the badge parameter to conditionally display badges

═══════════════════════════════════════════════════════════════════════════════
*/

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

KEY PATTERN:
BadgedBox(
    badge = {
        if (condition) {
            Badge { ... }    // Badge shows when condition is true
        }
        // No else needed - no badge when false
    }
) {
    Icon(...)
}

WHY THIS PATTERN:
- Don't show "0" in badge (confusing to users)
- Only indicate when action needed
- Clean UI when no notifications
- Better user experience

REAL-WORLD USAGE:
- Only show badge when there are notifications
- Hide badge when user has read all messages
- Display status badge only when relevant
*/

@Composable
fun MultipleBadgeConditionsExample() {
    var messageCount by remember { mutableStateOf(3) }
    var notificationCount by remember { mutableStateOf(0) }
    var hasNewFeature by remember { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Different Badge Conditions:", style = MaterialTheme.typography.titleMedium)

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    if (messageCount > 0) {
                        Badge { Text("$messageCount") }
                    }
                }
            ) {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = "Messages",
                    modifier = Modifier.size(32.dp)
                )
            }
            Text("Messages: $messageCount")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    if (notificationCount > 0) {
                        Badge { Text("$notificationCount") }
                    }
                }
            ) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier.size(32.dp)
                )
            }
            Text("Notifications: $notificationCount (no badge)")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    if (hasNewFeature) {
                        Badge { Text("NEW") }
                    }
                }
            ) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Features",
                    modifier = Modifier.size(32.dp)
                )
            }
            Text("New feature available: $hasNewFeature")
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { messageCount++ }) {
                Text("Add Message")
            }
            Button(onClick = { notificationCount++ }) {
                Text("Add Notification")
            }
            Button(onClick = { hasNewFeature = !hasNewFeature }) {
                Text("Toggle New Feature")
            }
        }
    }
}

/*
DIFFERENT CONDITION TYPES:

1. NUMERIC CONDITION (count > 0):
   if (messageCount > 0) {
       Badge { Text("$messageCount") }
   }
   - Shows count when greater than zero
   - Hides when count is zero

2. BOOLEAN CONDITION:
   if (hasNewFeature) {
       Badge { Text("NEW") }
   }
   - Shows badge when feature is new
   - Hides when user has seen it

3. COMPLEX CONDITION:
   if (unreadCount > 0 && !isMuted) {
       Badge { Text("$unreadCount") }
   }
   - Multiple conditions combined
   - Shows only when both conditions true

4. THRESHOLD CONDITION:
   if (errorCount >= 5) {
       Badge { Text("!") }
   }
   - Shows warning when threshold reached
   - Different from simple > 0 check
*/

@Composable
fun LargeNumberHandlingExample() {
    var count by remember { mutableStateOf(15) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(
            badge = {
                if (count > 0) {
                    Badge {
                        Text(
                            if (count > 99) "99+"
                            else "$count"
                        )
                    }
                }
            }
        ) {
            Icon(
                Icons.Filled.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier.size(48.dp)
            )
        }

        Text("Count: $count")
        Text(
            if (count > 99) "Showing as 99+"
            else "Showing actual count",
            style = MaterialTheme.typography.bodySmall
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { count += 10 }) {
                Text("+10")
            }
            Button(onClick = { count += 50 }) {
                Text("+50")
            }
            Button(
                onClick = { count = maxOf(0, count - 20) }
            ) {
                Text("-20")
            }
            Button(onClick = { count = 0 }) {
                Text("Clear")
            }
        }
    }
}

/*
LARGE NUMBER HANDLING:

Pattern for displaying large counts:
Badge {
    Text(
        if (count > 99) "99+"
        else "$count"
    )
}

WHY "99+"?
- Keeps badge compact and readable
- Prevents badge from becoming too wide
- User understands "many" notifications
- Common pattern in mobile apps (Gmail, WhatsApp, etc.)

VARIATIONS:
- "99+" for counts over 99
- "999+" for counts over 999
- "1k+" for counts over 1000
- Choose threshold based on your UI needs

COMPLETE PATTERN:
if (count > 0) {
    Badge {
        Text(
            when {
                count > 999 -> "999+"
                count > 99 -> "99+"
                else -> "$count"
            }
        )
    }
}
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          KEY CONCEPTS
═══════════════════════════════════════════════════════════════════════════════

1. CONDITIONAL RENDERING PATTERN:
   badge = {
       if (shouldShow) {
           Badge { ... }
       }
   }
   - No else needed
   - No badge shown when condition false
   - Clean and simple

2. WHEN TO HIDE BADGES:
   - Count is zero
   - User has dismissed notification
   - Feature is not new anymore
   - Item is muted/disabled

3. STATE MANAGEMENT:
   - Use remember { mutableStateOf() } for UI state
   - Update state based on user actions
   - Badge automatically updates when state changes

4. PERFORMANCE:
   - Conditional rendering is efficient
   - Only renders Badge when needed
   - No overhead when badge hidden

5. USER EXPERIENCE:
   - Never show "0" in badge
   - Use "99+" for large numbers
   - Hide badge when no action needed
   - Clear visual feedback

═══════════════════════════════════════════════════════════════════════════════
*/

