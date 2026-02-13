package com.example.FullComposeOfficialDocumentation.Accessibility_9.Support_user_scalable_content_4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
DEMO CARD COMPOSABLE

This is a shared demo UI component used to demonstrate the different scaling behaviors.
It contains various UI elements to showcase how scaling affects different components:
- Text with different typography styles (headline, body)
- Interactive components (Switch)
- Icons with fixed sizes
- Colored boxes to show layout spacing
- Multi-line text to show reflow behavior

This card is used by both density scaling and font scaling examples to highlight
the differences between the two approaches:

DENSITY SCALING:
- Everything scales: text, switch, icon, boxes, padding
- The entire card grows/shrinks proportionally

FONT SCALING:
- Only text scales: headline and body text get larger/smaller
- Components stay the same size: switch, icon, boxes, padding remain unchanged
*/

@Composable
fun DemoCard() {
    Card(
        modifier = Modifier
            .width(360.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            /*
            Headline text - demonstrates text scaling
            */
            Text(
                "Demo Card",
                style = MaterialTheme.typography.headlineMedium
            )

            /*
            Switch row - demonstrates component scaling (or lack thereof in font scaling)
            */
            var isChecked by remember { mutableStateOf(true) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Demo Switch",
                    Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge
                )
                Switch(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it }
                )
            }

            /*
            Icon row - demonstrates icon and spacing scaling
            */
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Icon",
                    Modifier.size(32.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Demo Icon",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            /*
            Colored boxes - demonstrates layout spacing and sizing
            These boxes help visualize how padding and spacing scale
            */
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    Modifier
                        .width(100.dp)
                        .weight(1f)
                        .height(80.dp)
                        .background(Color.Blue)
                )
                Box(
                    Modifier
                        .width(100.dp)
                        .weight(1f)
                        .height(80.dp)
                        .background(Color.Red)
                )
            }

            /*
            Multi-line text - demonstrates text reflow behavior
            This is especially important for accessibility as it shows how
            content adapts without requiring horizontal panning
            */
            Text(
                "Demo Text: Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                    " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }
    }
}

