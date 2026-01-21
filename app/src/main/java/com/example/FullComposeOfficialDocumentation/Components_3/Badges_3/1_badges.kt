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
                                BADGES - OVERVIEW
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

═══════════════════════════════════════════════════════════════════════════════
                          BEST PRACTICES
═══════════════════════════════════════════════════════════════════════════════

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

═══════════════════════════════════════════════════════════════════════════════

See other files for specific examples:
- 2_basicBadges.kt - Simple dot and numbered badges
- 3_customColorBadges.kt - Custom colors for badges
- 4_conditionalBadges.kt - Show/hide badges based on state
- 5_badgesOnComposables.kt - Badges on different UI elements
- 6_navigationBarBadges.kt - Real-world navigation pattern

═══════════════════════════════════════════════════════════════════════════════
*/
