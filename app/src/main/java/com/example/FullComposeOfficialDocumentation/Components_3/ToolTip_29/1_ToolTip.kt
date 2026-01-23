package com.example.FullComposeOfficialDocumentation.Components_3.ToolTip_29

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
Tooltips add context to buttons or other UI elements.

Two types of tooltips:
1. Plain tooltips: Describe elements or actions of icon buttons (simple, short text)
2. Rich tooltips: Provide more detail with optional title, links, and buttons

API Surface:

TooltipBox - Main composable for implementing tooltips
Key parameters:
- positionProvider: Places tooltip relative to anchor content
  Use TooltipDefaults.rememberPlainTooltipPositionProvider() or rememberRichTooltipPositionProvider()

- tooltip: Composable containing tooltip content
  Use PlainTooltip or RichTooltip composables

- state: State holder for tooltip UI logic and element state
  Created with rememberTooltipState()

- content: The composable that tooltip is anchored to (button, icon, etc.)

How tooltips are triggered:
- Desktop: Hovering over the element with cursor
- Mobile: Long-pressing the element
*/

/*
Plain Tooltip - Simple Example

Plain tooltips briefly describe UI elements.
Use for icon buttons or simple actions.

Key points:
- TooltipDefaults.rememberPlainTooltipPositionProvider(): Default positioning for plain tooltips
- PlainTooltip: Contains the tooltip text
- rememberTooltipState(): Controls tooltip visibility state
- Shows on hover (desktop) or long-press (mobile)
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlainTooltipExample(
    modifier: Modifier = Modifier,
    plainTooltipText: String = "Add to favorites"
) {
    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip { Text(plainTooltipText) }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = { /* Do something... */ }) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Add to favorites"
            )
        }
    }
}
