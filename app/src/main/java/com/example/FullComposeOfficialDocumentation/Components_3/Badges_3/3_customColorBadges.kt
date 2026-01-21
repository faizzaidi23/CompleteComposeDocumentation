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
                          CUSTOM COLORED BADGES
═══════════════════════════════════════════════════════════════════════════════

Demonstrates using containerColor and contentColor parameters to customize badge appearance

EXAMPLES IN THIS FILE:
1. Custom background colors (containerColor)
2. Custom text colors (contentColor)
3. Theme-based colors

═══════════════════════════════════════════════════════════════════════════════
*/

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
   - Color.Blue, Color.Green, Color.Red, etc.
   - MaterialTheme.colorScheme.tertiary (theme-based)
   - MaterialTheme.colorScheme.primary
   - Default is MaterialTheme.colorScheme.error (red)

2. contentColor: Text/icon color inside badge
   - Must contrast with containerColor for readability
   - Color.White, Color.Black, etc.
   - Default automatically chosen for accessibility
   - If not specified, automatically contrasts with containerColor

3. Content block: What displays inside
   - Text with numbers: "5", "10"
   - Text with labels: "NEW", "HOT", "SALE"
   - Can be empty for dot badge

WHEN TO USE CUSTOM COLORS:
- Different priority levels (red=urgent, yellow=warning, green=success)
- Category distinction (blue=messages, purple=updates)
- Brand colors for specific features
- Status indicators (green=online, yellow=away, red=busy)
*/

@Composable
fun ColorMeaningExample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Color Meanings:", style = MaterialTheme.typography.titleMedium)

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text("3")
                    }
                }
            ) {
                Icon(Icons.Filled.Warning, contentDescription = "Urgent")
            }
            Text("Urgent/Error")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color(0xFFFFA500),
                        contentColor = Color.Black
                    ) {
                        Text("2")
                    }
                }
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "Warning")
            }
            Text("Warning")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ) {
                        Text("7")
                    }
                }
            ) {
                Icon(Icons.Filled.Info, contentDescription = "Info")
            }
            Text("Informational")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            BadgedBox(
                badge = {
                    Badge(
                        containerColor = Color.Green,
                        contentColor = Color.White
                    ) {
                        Text("OK")
                    }
                }
            ) {
                Icon(Icons.Filled.CheckCircle, contentDescription = "Success")
            }
            Text("Success/Complete")
        }
    }
}

/*
COLOR GUIDELINES:

Red (Color.Red):
- Urgent notifications
- Errors requiring attention
- Critical alerts
- Default badge color

Orange/Yellow (Color(0xFFFFA500)):
- Warnings
- Medium priority items
- Pending actions

Blue (Color.Blue):
- Informational messages
- General notifications
- Non-urgent updates

Green (Color.Green):
- Success states
- Completed tasks
- Positive confirmations
- "Online" status

Purple/Tertiary:
- Special features
- Premium content
- New additions
*/

@Composable
fun ThemeBasedColorExample() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Text("5")
                }
            }
        ) {
            Icon(Icons.Filled.Star, contentDescription = "Primary")
        }

        BadgedBox(
            badge = {
                Badge(
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Text("3")
                }
            }
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Secondary")
        }

        BadgedBox(
            badge = {
                Badge(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ) {
                    Text("NEW")
                }
            }
        ) {
            Icon(Icons.Filled.Email, contentDescription = "Tertiary")
        }
    }
}

/*
THEME-BASED COLORS:

Using MaterialTheme.colorScheme ensures:
- Consistent with app's design system
- Automatically adapts to light/dark mode
- Respects user's Material You color preferences

Available theme colors:
- colorScheme.primary: Main brand color
- colorScheme.secondary: Accent color
- colorScheme.tertiary: Third accent color
- colorScheme.error: Error/urgent (default badge)
- colorScheme.surface: Background surfaces
- colorScheme.onPrimary: Text on primary color

AUTOMATIC CONTENT COLOR:
When you specify containerColor but not contentColor,
Compose automatically chooses a contrasting color for text.

Badge(
    containerColor = MaterialTheme.colorScheme.primary
    // contentColor automatically chosen for good contrast
) {
    Text("5")
}
*/

/*
═══════════════════════════════════════════════════════════════════════════════
                          KEY CONCEPTS
═══════════════════════════════════════════════════════════════════════════════

1. COLOR CONTRAST:
   - Always ensure contentColor contrasts with containerColor
   - Light background → Dark text
   - Dark background → Light text
   - Compose auto-calculates if contentColor not specified

2. ACCESSIBILITY:
   - Minimum contrast ratio: 4.5:1 for text
   - Don't rely solely on color to convey meaning
   - Provide contentDescription for icons
   - Consider colorblind users

3. CONSISTENCY:
   - Use same colors for same meanings throughout app
   - Red always means urgent
   - Green always means success
   - Blue always means info

4. THEME INTEGRATION:
   - Prefer MaterialTheme.colorScheme over hardcoded colors
   - Automatically supports light/dark mode
   - Respects Material You dynamic colors

═══════════════════════════════════════════════════════════════════════════════
*/

