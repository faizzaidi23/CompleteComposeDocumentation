package com.example.FullComposeOfficialDocumentation.Components_3.RadioButton_20

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.HorizontalDivider
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
fun RadioButtonGroupsExample() {
    var sortBy by remember { mutableStateOf("Name") }
    var orderBy by remember { mutableStateOf("Ascending") }

    Column(Modifier.padding(16.dp)) {
        // First group - Sort By
        Text(
            "Sort By",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(Modifier.selectableGroup()) {
            listOf("Name", "Date", "Size").forEach { option ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (option == sortBy),
                            onClick = { sortBy = option },
                            role = Role.RadioButton
                        )
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == sortBy),
                        onClick = null
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        // Second group - Order By
        Text(
            "Order",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(Modifier.selectableGroup()) {
            listOf("Ascending", "Descending").forEach { option ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (option == orderBy),
                            onClick = { orderBy = option },
                            role = Role.RadioButton
                        )
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == orderBy),
                        onClick = null
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

