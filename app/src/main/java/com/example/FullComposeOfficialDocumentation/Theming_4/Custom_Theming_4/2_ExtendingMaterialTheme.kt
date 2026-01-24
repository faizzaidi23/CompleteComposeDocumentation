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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Extending Material Theme with Additional Colors
 *
 * This approach wraps MaterialTheme and adds custom color values
 * Supports multiple themes and provides type-safe access
 *
 * Use case: Adding colors like "caution", "success" that aren't in Material
 */

// Step 1: Define custom color data class
@Immutable
data class ExtendedColors(
    val caution: Color,
    val onCaution: Color,
    val success: Color,
    val onSuccess: Color
)

// Step 2: Create CompositionLocal for custom colors
val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        caution = Color.Unspecified,
        onCaution = Color.Unspecified,
        success = Color.Unspecified,
        onSuccess = Color.Unspecified
    )
}

// Step 3: Create extended theme composable
@Composable
fun ExtendedTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    // Define extended colors based on theme
    val extendedColors = if (darkTheme) {
        ExtendedColors(
            caution = Color(0xFFFFCC02),
            onCaution = Color(0xFF2C2D30),
            success = Color(0xFF4CAF50),
            onSuccess = Color(0xFF000000)
        )
    } else {
        ExtendedColors(
            caution = Color(0xFFFFB300),
            onCaution = Color(0xFF000000),
            success = Color(0xFF2E7D32),
            onSuccess = Color(0xFFFFFFFF)
        )
    }

    // Provide extended colors and wrap MaterialTheme
    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = MaterialTheme.typography,
            shapes = MaterialTheme.shapes,
            content = content
        )
    }
}

// Step 4: Create accessor object for easy access
object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}

/**
 * Custom button using extended colors
 */
@Composable
fun CautionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = ExtendedTheme.colors.caution,
            contentColor = ExtendedTheme.colors.onCaution
        ),
        content = content
    )
}

@Composable
fun SuccessButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = ExtendedTheme.colors.success,
            contentColor = ExtendedTheme.colors.onSuccess
        ),
        content = content
    )
}

/**
 * Example usage
 */
@Composable
fun ExtendedThemeExample() {
    ExtendedTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Extended Theme Example",
                style = MaterialTheme.typography.headlineSmall
            )

            // Using standard Material colors
            Button(onClick = { }) {
                Text("Standard Button")
            }

            // Using custom extended colors
            CautionButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Caution Button")
            }

            SuccessButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Success Button")
            }

            // Using extended colors in Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    containerColor = ExtendedTheme.colors.caution.copy(alpha = 0.1f)
                )
            ) {
                Text(
                    text = "This card uses extended caution color",
                    modifier = Modifier.padding(16.dp),
                    color = ExtendedTheme.colors.caution
                )
            }
        }
    }
}

