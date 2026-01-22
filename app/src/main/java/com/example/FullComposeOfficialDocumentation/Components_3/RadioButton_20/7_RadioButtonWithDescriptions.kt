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
fun RadioButtonWithDescriptionsExample() {
    var selectedPlan by remember { mutableStateOf("Basic") }

    data class Plan(val name: String, val description: String, val price: String)
    val plans = listOf(
        Plan("Basic", "Essential features for personal use", "Free"),
        Plan("Pro", "Advanced features for professionals", "$9.99/mo"),
        Plan("Enterprise", "Complete solution for teams", "$29.99/mo")
    )

    Column(Modifier.selectableGroup()) {
        plans.forEach { plan ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (plan.name == selectedPlan),
                        onClick = { selectedPlan = plan.name },
                        role = Role.RadioButton
                    )
                    .padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                RadioButton(
                    selected = (plan.name == selectedPlan),
                    onClick = null,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Column(Modifier.padding(start = 16.dp)) {
                    Text(
                        text = plan.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = plan.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = plan.price,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

