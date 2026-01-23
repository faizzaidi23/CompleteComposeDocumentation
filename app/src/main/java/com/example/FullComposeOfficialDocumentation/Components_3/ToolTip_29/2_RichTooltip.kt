package com.example.FullComposeOfficialDocumentation.Components_3.ToolTip_29

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
Rich Tooltip - Provides Additional Context

Rich tooltips provide more detail than plain tooltips.
They support:
- Multiple lines of text
- Optional title
- Optional action buttons
- Optional links

Use cases:
- Describing features or functionality
- Providing helpful tips or hints
- Displaying additional information

Key differences from PlainTooltip:
- TooltipDefaults.rememberRichTooltipPositionProvider(): Different positioning strategy
- RichTooltip: Supports title parameter and more complex content
- Can display multi-line text
- Can include interactive actions
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RichTooltipExample(
    modifier: Modifier = Modifier,
    richTooltipSubheadText: String = "Rich Tooltip",
    richTooltipText: String = "Rich tooltips support multiple lines of informational text."
) {
    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) }
            ) {
                // Body text of the tooltip
                Text(richTooltipText)
            }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = { /* Icon button's click event */ }) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Show more information"
            )
        }
    }
}

