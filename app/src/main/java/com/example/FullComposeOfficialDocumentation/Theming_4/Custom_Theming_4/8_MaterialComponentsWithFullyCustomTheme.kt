package com.example.FullComposeOfficialDocumentation.Theming_4.Custom_Theming_4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

/**
 * Using Material Components with Fully Custom Theme
 *
 * With a fully custom design system, Material components need significant customization
 * You may need to implement components from scratch for custom types (like gradients)
 *
 * Options:
 * 1. Wrap Material components with heavy customization
 * 2. Build custom components from scratch
 */

/**
 * Custom-themed button for fully custom design system
 *
 * Sets all necessary values manually since MaterialTheme isn't used
 */
@Composable
fun FullyCustomThemedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomTheme.colors.component,
            contentColor = CustomTheme.colors.content,
            disabledContainerColor = CustomTheme.colors.content.copy(alpha = 0.12f),
            disabledContentColor = CustomTheme.colors.content.copy(alpha = 0.38f)
        ),
        shape = RoundedCornerShape(percent = 50),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = CustomTheme.elevation.default,
            pressedElevation = CustomTheme.elevation.pressed
        ),
        content = {
            ProvideTextStyle(value = CustomTheme.typography.body) {
                content()
            }
        }
    )
}

/**
 * Custom gradient card - built from scratch
 *
 * Uses Box with gradient background since Card doesn't support gradients
 */
@Composable
fun GradientCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = CustomTheme.colors.background
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        content()
    }
}

/**
 * Example usage
 */
@Composable
fun MaterialComponentsWithFullyCustomTheme() {
    CustomTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = CustomTheme.colors.background
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Fully Custom Design System",
                    style = CustomTheme.typography.title,
                    color = CustomTheme.colors.content
                )

                Text(
                    text = "With a fully custom design system, you have two options:",
                    style = CustomTheme.typography.body,
                    color = CustomTheme.colors.content
                )

                // Wrapped Material component
                FullyCustomThemedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Wrapped Material Button")
                }

                Text(
                    text = "Option 1: Wrap Material components (like above button)",
                    style = CustomTheme.typography.body,
                    color = CustomTheme.colors.content.copy(alpha = 0.8f)
                )

                // Custom component built from scratch
                GradientCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Custom Gradient Card",
                            style = CustomTheme.typography.title,
                            color = CustomTheme.colors.content
                        )
                        Text(
                            text = "This is built from scratch to support gradient backgrounds",
                            style = CustomTheme.typography.body,
                            color = CustomTheme.colors.content
                        )
                    }
                }

                Text(
                    text = "Option 2: Build components from scratch (like above card)",
                    style = CustomTheme.typography.body,
                    color = CustomTheme.colors.content.copy(alpha = 0.8f)
                )

                Text(
                    text = "When to build from scratch:\n" +
                            "• Your design uses unique features (gradients, special shapes)\n" +
                            "• Material components don't fit your design language\n" +
                            "• You need full control over appearance and behavior",
                    style = CustomTheme.typography.body,
                    color = CustomTheme.colors.content.copy(alpha = 0.7f)
                )
            }
        }
    }
}

