package com.example.FullComposeOfficialDocumentation.Components_3.RadioButton_20

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonEnabledDisabledExample() {
    var selectedOption by remember { mutableStateOf("Available") }

    // Some options are enabled, some disabled
    data class RadioOption(val label: String, val enabled: Boolean)
    val options = listOf(
        RadioOption("Available", true),
        RadioOption("Unavailable", false),
        RadioOption("Coming Soon", false)
    )

    Column(Modifier.selectableGroup()) {
        options.forEach { option ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option.label == selectedOption),
                        onClick = { selectedOption = option.label },
                        enabled = option.enabled,
                        role = Role.RadioButton
                    )
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // enabled parameter controls if user can interact with radio button
                RadioButton(
                    selected = (option.label == selectedOption),
                    onClick = null,
                    enabled = option.enabled
                )
                Text(
                    text = option.label,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp),
                    // Visually indicate disabled state
                    color = if (option.enabled) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    }
                )
            }
        }
    }
}

