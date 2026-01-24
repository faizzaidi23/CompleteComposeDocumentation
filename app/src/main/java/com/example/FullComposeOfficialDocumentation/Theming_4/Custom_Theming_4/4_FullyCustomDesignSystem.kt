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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Fully Custom Design System
 *
 * This replaces MaterialTheme entirely with a custom design system
 * Introduces new concepts like gradients and custom elevation
 *
 * Custom systems:
 * - Colors: Including gradients (List<Color>)
 * - Typography: Custom text styles
 * - Elevation: Custom elevation values
 */

// Step 1: Define custom color system with gradients
@Immutable
data class CustomColors(
    val content: Color,
    val component: Color,
    val background: List<Color> // Gradient support
)

// Step 2: Define custom typography
@Immutable
data class CustomTypography(
    val body: TextStyle,
    val title: TextStyle
)

// Step 3: Define custom elevation system
@Immutable
data class CustomElevation(
    val default: Dp,
    val pressed: Dp
)

// Step 4: Create CompositionLocals
val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        content = Color.Unspecified,
        component = Color.Unspecified,
        background = emptyList()
    )
}

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        body = TextStyle.Default,
        title = TextStyle.Default
    )
}

val LocalCustomElevation = staticCompositionLocalOf {
    CustomElevation(
        default = Dp.Unspecified,
        pressed = Dp.Unspecified
    )
}

// Step 5: Create custom theme composable
@Composable
fun CustomTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val customColors = if (darkTheme) {
        CustomColors(
            content = Color(0xFFFFFFFF),
            component = Color(0xFF2196F3),
            background = listOf(Color(0xFF1A237E), Color(0xFF0D47A1))
        )
    } else {
        CustomColors(
            content = Color(0xFFDD0D3C),
            component = Color(0xFFC20029),
            background = listOf(Color.White, Color(0xFFF8BBD0))
        )
    }

    val customTypography = CustomTypography(
        body = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        title = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    )

    val customElevation = CustomElevation(
        default = 4.dp,
        pressed = 8.dp
    )

    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        LocalCustomTypography provides customTypography,
        LocalCustomElevation provides customElevation,
        content = content
    )
}

// Step 6: Create accessor object
object CustomTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current

    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current

    val elevation: CustomElevation
        @Composable
        get() = LocalCustomElevation.current
}

/**
 * Custom button using fully custom design system
 */
@Composable
fun CustomButton(
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
            disabledContainerColor = CustomTheme.colors.content
                .copy(alpha = 0.12f),
            disabledContentColor = CustomTheme.colors.content
                .copy(alpha = 0.38f)
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
 * Example usage of fully custom theme
 */
@Composable
fun CustomThemeExample() {
    CustomTheme {
        // Background with gradient
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
                // Title with custom typography
                Text(
                    text = "Custom Design System",
                    style = CustomTheme.typography.title,
                    color = CustomTheme.colors.content
                )

                // Body text
                Text(
                    text = "This uses a fully custom design system with gradients, " +
                            "custom typography, and custom elevation.",
                    style = CustomTheme.typography.body,
                    color = CustomTheme.colors.content
                )

                // Custom button
                CustomButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Custom Themed Button")
                }

                // Info text
                Text(
                    text = "Notice the gradient background - a custom feature " +
                            "not in Material Theme",
                    style = CustomTheme.typography.body,
                    color = CustomTheme.colors.content.copy(alpha = 0.7f)
                )
            }
        }
    }
}
