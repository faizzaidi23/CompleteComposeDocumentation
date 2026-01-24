package com.example.FullComposeOfficialDocumentation.Theming_4.Custom_Theming_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Replacing Material Subsystems
 *
 * Replace one or more Material systems (Typography, Shapes)
 * while keeping others (like Colors)
 *
 * This example replaces Typography and Shapes but keeps Material Colors
 */

// Step 1: Define custom Typography system
@Immutable
data class ReplacementTypography(
    val body: TextStyle,
    val title: TextStyle,
    val caption: TextStyle
)

// Step 2: Define custom Shapes system
@Immutable
data class ReplacementShapes(
    val component: Shape,
    val surface: Shape
)

// Step 3: Create CompositionLocals
val LocalReplacementTypography = staticCompositionLocalOf {
    ReplacementTypography(
        body = TextStyle.Default,
        title = TextStyle.Default,
        caption = TextStyle.Default
    )
}

val LocalReplacementShapes = staticCompositionLocalOf {
    ReplacementShapes(
        component = RoundedCornerShape(0.dp),
        surface = RoundedCornerShape(0.dp)
    )
}

// Step 4: Create replacement theme composable
@Composable
fun ReplacementTheme(
    content: @Composable () -> Unit
) {
    // Define custom typography
    val replacementTypography = ReplacementTypography(
        body = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.5.sp
        ),
        title = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        ),
        caption = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.4.sp
        )
    )

    // Define custom shapes
    val replacementShapes = ReplacementShapes(
        component = RoundedCornerShape(percent = 50), // Fully rounded
        surface = RoundedCornerShape(size = 40.dp)
    )

    // Provide custom systems and wrap MaterialTheme for colors
    CompositionLocalProvider(
        LocalReplacementTypography provides replacementTypography,
        LocalReplacementShapes provides replacementShapes
    ) {
        MaterialTheme(
            // Use Material colors, but custom typography and shapes
            colorScheme = MaterialTheme.colorScheme,
            content = content
        )
    }
}

// Step 5: Create accessor object
object ReplacementTheme {
    val typography: ReplacementTypography
        @Composable
        get() = LocalReplacementTypography.current

    val shapes: ReplacementShapes
        @Composable
        get() = LocalReplacementShapes.current
}

/**
 * Custom button using replacement typography and shapes
 */
@Composable
fun ReplacementButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = ReplacementTheme.shapes.component, // Custom shape
        content = {
            // Provide custom text style to button content
            ProvideTextStyle(value = ReplacementTheme.typography.body) {
                content()
            }
        }
    )
}

/**
 * Example usage
 */
@Composable
fun ReplacementThemeExample() {
    ReplacementTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Using custom typography
            Text(
                text = "Custom Title",
                style = ReplacementTheme.typography.title
            )

            Text(
                text = "This is body text using custom typography system",
                style = ReplacementTheme.typography.body
            )

            Text(
                text = "Caption text with custom styling",
                style = ReplacementTheme.typography.caption
            )

            // Using custom button with replacement shapes and typography
            ReplacementButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fully Rounded Button")
            }

            // Standard button for comparison (will use Material defaults)
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Standard Button")
            }

            Text(
                text = "Notice: Colors come from MaterialTheme, " +
                        "but typography and shapes are custom",
                style = ReplacementTheme.typography.caption,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

