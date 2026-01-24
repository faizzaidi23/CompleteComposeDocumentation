package com.example.FullComposeOfficialDocumentation.Theming_4.Custom_Theming_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Using Material Components with Replacement Theme
 *
 * When replacing Material subsystems (Typography/Shapes),
 * Material components won't automatically use your custom values
 *
 * Solution: Wrap components and manually set custom values
 * Use ProvideTextStyle for text styles
 */

/**
 * Replacement Button - Uses custom shapes and typography
 *
 * Sets shape directly and uses ProvideTextStyle for typography
 */
@Composable
fun ReplacementThemedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = ReplacementTheme.shapes.component, // Custom shape
        content = {
            // Apply custom typography to button content
            ProvideTextStyle(value = ReplacementTheme.typography.body) {
                content()
            }
        }
    )
}

/**
 * Example usage with replacement theme
 */
@Composable
fun MaterialComponentsWithReplacementTheme() {
    ReplacementTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Material Components + Replacement Theme",
                style = ReplacementTheme.typography.title
            )

            Text(
                text = "When you replace Material systems, standard components " +
                        "won't use your custom values automatically.",
                style = ReplacementTheme.typography.body
            )

            Text(
                text = "Standard Button (uses Material defaults):",
                style = ReplacementTheme.typography.caption
            )

            // Standard Material Button - won't use custom theme
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Standard Button")
            }

            Text(
                text = "Custom Wrapped Button (uses replacement theme):",
                style = ReplacementTheme.typography.caption
            )

            // Custom button using replacement theme
            ReplacementThemedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Replacement Themed Button")
            }

            Text(
                text = "Key Points:",
                style = ReplacementTheme.typography.title
            )

            Text(
                text = "• Create wrapper composables for components you use\n" +
                        "• Set shape, typography directly in wrappers\n" +
                        "• Use ProvideTextStyle for text styling\n" +
                        "• Material colors still work if not replaced",
                style = ReplacementTheme.typography.body
            )
        }
    }
}

