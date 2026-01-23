package com.example.FullComposeOfficialDocumentation.Components_3.Switch_26

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/*
Custom thumb with icon

We can pass any composable for the thumbContent parameter to create a custom thumb.
The thumbContent accepts a lambda that returns a composable.

Key concepts:
- thumbContent: A lambda that provides custom content for the switch thumb
- Conditional rendering: Show icon when checked, null when unchecked
- SwitchDefaults.IconSize: Predefined size for icons in switches
- The icon appears inside the circular thumb of the switch

Common use cases:
- Show checkmark when enabled
- Show different icons for different states
- Add visual feedback for the switch state
*/

@Composable
fun SwitchWithIconExample() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize)
                )
            }
        } else {
            null
        }
    )
}