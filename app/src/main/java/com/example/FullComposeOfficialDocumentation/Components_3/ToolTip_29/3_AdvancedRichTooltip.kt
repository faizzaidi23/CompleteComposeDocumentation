package com.example.FullComposeOfficialDocumentation.Components_3.ToolTip_29

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/*
Advanced Rich Tooltip with Custom Actions

This demonstrates a fully customizable rich tooltip with:
- Title
- Multi-line body text
- Action buttons (like Dismiss)
- Manual control over showing/dismissing

Key concepts:

1. tooltipState with CoroutineScope:
   - rememberTooltipState(): Creates state to control tooltip
   - rememberCoroutineScope(): Needed for calling suspending functions
   - tooltipState.show(): Manually show the tooltip
   - tooltipState.dismiss(): Manually hide the tooltip

2. action parameter in RichTooltip:
   - Adds interactive elements (buttons, links) to the tooltip
   - Common actions: Dismiss, Learn More, Got It, etc.
   - Actions are launched in coroutines to avoid blocking UI

3. Why use coroutines:
   - show() and dismiss() are suspending functions
   - They may involve animations or delays
   - Coroutines ensure smooth, non-blocking execution

When to use:
- Need dismiss action for long tooltips
- Want to show tooltip on button click (not just hover)
- Need to track whether user acknowledged the tooltip
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedRichTooltipExample(
    modifier: Modifier = Modifier,
    richTooltipSubheadText: String = "Custom Rich Tooltip",
    richTooltipText: String = "Rich tooltips support multiple lines of informational text.",
    richTooltipActionText: String = "Dismiss"
) {
    val tooltipState = rememberTooltipState()
    val coroutineScope = rememberCoroutineScope()

    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) },
                action = {
                    Row {
                        TextButton(onClick = {
                            // Launch coroutine to dismiss tooltip
                            coroutineScope.launch {
                                tooltipState.dismiss()
                            }
                        }) {
                            Text(richTooltipActionText)
                        }
                    }
                },
            ) {
                Text(richTooltipText)
            }
        },
        state = tooltipState
    ) {
        IconButton(onClick = {
            // Manually show tooltip when icon is clicked
            coroutineScope.launch {
                tooltipState.show()
            }
        }) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Open camera"
            )
        }
    }
}
