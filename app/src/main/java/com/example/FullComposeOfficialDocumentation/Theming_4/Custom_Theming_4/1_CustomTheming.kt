package com.example.FullComposeOfficialDocumentation.Theming_4.Custom_Theming_4

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Custom Design Systems in Compose - Overview
 *
 * While Material is recommended, you can create custom design systems:
 *
 * Three approaches:
 * 1. Extend MaterialTheme with additional values
 * 2. Replace some Material systems (Colors, Typography, Shapes)
 * 3. Implement a fully custom design system
 *
 * Material is built on public APIs, so any of these approaches is possible
 * You can also continue using Material components with custom design
 */

/**
 * Approach 1: Extend Material with Extension Properties
 *
 * Simple approach for adding a few custom values
 * Works well for values that are consistent across themes
 */

// Extension property for custom color
val ColorScheme.snackbarAction: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFEF9A9A) else Color(0xFFD32F2F)

// Extension property for custom text style
val Typography.textFieldInput: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.5.sp
    )

// Extension property for custom shape
val Shapes.card: Shape
    get() = RoundedCornerShape(size = 20.dp)

/**
 * Usage Example:
 *
 * @Composable
 * fun MyScreen() {
 *     MaterialTheme {
 *         // Access custom values
 *         val actionColor = MaterialTheme.colorScheme.snackbarAction
 *         val inputStyle = MaterialTheme.typography.textFieldInput
 *         val cardShape = MaterialTheme.shapes.card
 *
 *         // Use them in your UI
 *         Card(shape = cardShape) {
 *             Text("Custom themed card", style = inputStyle, color = actionColor)
 *         }
 *     }
 * }
 *
 * Pros:
 * - Simple and straightforward
 * - Consistent with MaterialTheme API
 * - Type-safe
 *
 * Cons:
 * - Only recommended for simple additions
 * - Not ideal for multiple themes with different values
 * - Extension properties are global
 */
