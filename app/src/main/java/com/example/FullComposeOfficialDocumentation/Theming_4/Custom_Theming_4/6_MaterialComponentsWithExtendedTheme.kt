package com.example.FullComposeOfficialDocumentation.Theming_4.Custom_Theming_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Using Material Components with Extended Theme
 *
 * When extending MaterialTheme, you can still use Material components
 * You may want to create wrapper components that use your extended values
 *
 * This shows how to wrap Material components to use extended colors
 */

/**
 * Extended Button - Uses caution color from ExtendedTheme
 *
 * Wraps Material Button and sets custom colors
 * Exposes other parameters for flexibility
 */
@Composable
fun ExtendedCautionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = ExtendedTheme.colors.caution,
            contentColor = ExtendedTheme.colors.onCaution
            // Other colors use default Material values
        ),
        content = content
    )
}

/**
 * Extended Success Card - Uses success color
 */
@Composable
fun SuccessCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = ExtendedTheme.colors.success.copy(alpha = 0.1f),
            contentColor = ExtendedTheme.colors.success
        ),
        content = content
    )
}

/**
 * Example showing Material components with extended theme
 */
@Composable
fun MaterialComponentsWithExtendedTheme() {
    ExtendedTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Material Components + Extended Theme",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Standard Material components work as usual:",
                style = MaterialTheme.typography.bodyMedium
            )

            // Standard Material Button
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Standard Material Button")
            }

            Text(
                text = "Extended components use custom colors:",
                style = MaterialTheme.typography.bodyMedium
            )

            // Extended Button with caution color
            ExtendedCautionButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Caution Action")
            }

            // Extended Success Card
            SuccessCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Success!",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "This card uses the extended success color",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Text(
                text = "Approach: Wrap Material components in custom composables " +
                        "that apply your extended theme values",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

