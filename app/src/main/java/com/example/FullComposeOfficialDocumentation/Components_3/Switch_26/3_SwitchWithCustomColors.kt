package com.example.FullComposeOfficialDocumentation.Components_3.Switch_26

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/*
Switch with Custom Colors

The Switch composable can be customized with different colors for various states
using the colors parameter with SwitchDefaults.colors().

Key color parameters:

1. checkedThumbColor: Color of the circular thumb when switch is ON
   - The draggable circular part that slides

2. checkedTrackColor: Color of the track background when switch is ON
   - The background rail that the thumb slides on

3. uncheckedThumbColor: Color of the thumb when switch is OFF
   - Usually a neutral or secondary color

4. uncheckedTrackColor: Color of the track when switch is OFF
   - Usually a lighter or muted color

MaterialTheme.colorScheme provides colors that automatically adapt to light/dark themes:
- primary: Main brand color
- primaryContainer: Lighter variant of primary
- secondary: Secondary accent color
- secondaryContainer: Lighter variant of secondary
- surface, surfaceVariant, etc.

Why customize colors:
- Match your app's branding
- Indicate different switch types (e.g., critical vs normal settings)
- Improve visual hierarchy
- Better accessibility with high contrast colors
*/

@Composable
fun SwitchWithCustomColors() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        )
    )
}

