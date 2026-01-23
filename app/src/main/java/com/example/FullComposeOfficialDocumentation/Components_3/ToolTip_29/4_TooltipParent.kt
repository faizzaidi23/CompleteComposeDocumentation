package com.example.FullComposeOfficialDocumentation.Components_3.ToolTip_29

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
Parent Composable demonstrating all tooltip types.

This shows:
1. PlainTooltip - Simple text on hover/long-press
2. RichTooltip - With title and multi-line text
3. AdvancedRichTooltip - With title, text, and dismiss action

User interaction:
- Desktop: Hover over icons to see tooltips
- Mobile: Long-press icons to see tooltips
- Advanced tooltip: Click camera icon OR long-press to show
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipExamplesParent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = "Tooltip Examples",
            fontSize = 24.sp
        )

        Text(text = "Hover or long-press icons to see tooltips")

        HorizontalDivider()

        // Plain Tooltip Example
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Plain Tooltip", fontSize = 18.sp)
            Text(text = "(Hover/long-press the heart icon)")
            PlainTooltipExample()
        }

        HorizontalDivider()

        // Rich Tooltip Example
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Rich Tooltip with Title", fontSize = 18.sp)
            Text(text = "(Hover/long-press the info icon)")
            RichTooltipExample()
        }

        HorizontalDivider()

        // Advanced Rich Tooltip Example
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Advanced Tooltip with Action", fontSize = 18.sp)
            Text(text = "(Click or long-press the camera icon)")
            AdvancedRichTooltipExample()
        }
    }
}

