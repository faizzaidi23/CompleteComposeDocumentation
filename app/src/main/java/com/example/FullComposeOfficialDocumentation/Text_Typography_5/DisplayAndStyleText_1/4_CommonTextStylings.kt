package com.example.FullComposeOfficialDocumentation.Text_Typography_5.DisplayAndStyleText_1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Common Text Stylings
 *
 * The Text composable has multiple optional parameters to style its content.
 * When you set these parameters, you're applying the style to the whole text value.
 *
 * For multiple styles within the same line or paragraphs, use AnnotatedString.
 */

/**
 * 1. Change Text Color
 *
 * Use the color parameter to change text color
 */
@Composable
fun BlueText() {
    Text("Hello World", color = Color.Blue)
}

@Composable
fun RedText() {
    Text("Hello World", color = Color.Red)
}

@Composable
fun CustomColorText() {
    Text("Hello World", color = Color(0xFF6200EE))
}

/**
 * 2. Change Text Size
 *
 * Use the fontSize parameter to change text size
 * Size is specified in sp (scaleable pixels)
 */
@Composable
fun BigText() {
    Text("Hello World", fontSize = 30.sp)
}

@Composable
fun SmallText() {
    Text("Hello World", fontSize = 12.sp)
}

@Composable
fun VariousSizes() {
    Column {
        Text("Size 14", fontSize = 14.sp)
        Text("Size 18", fontSize = 18.sp)
        Text("Size 24", fontSize = 24.sp)
        Text("Size 30", fontSize = 30.sp)
        Text("Size 40", fontSize = 40.sp)
    }
}

/**
 * 3. Make Text Italic
 *
 * Use the fontStyle parameter to italicize text
 * Options: FontStyle.Normal, FontStyle.Italic
 */
@Composable
fun ItalicText() {
    Text("Hello World", fontStyle = FontStyle.Italic)
}

@Composable
fun NormalText() {
    Text("Hello World", fontStyle = FontStyle.Normal)
}

/**
 * 4. Make Text Bold
 *
 * Use the fontWeight parameter to bold text
 * Options: FontWeight.Thin, FontWeight.Light, FontWeight.Normal,
 *          FontWeight.Medium, FontWeight.Bold, FontWeight.Black
 *          or numeric values from 100-900
 */
@Composable
fun BoldText() {
    Text("Hello World", fontWeight = FontWeight.Bold)
}

@Composable
fun VariousFontWeights() {
    Column {
        Text("Thin (100)", fontWeight = FontWeight.Thin)
        Text("Light (300)", fontWeight = FontWeight.Light)
        Text("Normal (400)", fontWeight = FontWeight.Normal)
        Text("Medium (500)", fontWeight = FontWeight.Medium)
        Text("SemiBold (600)", fontWeight = FontWeight.SemiBold)
        Text("Bold (700)", fontWeight = FontWeight.Bold)
        Text("ExtraBold (800)", fontWeight = FontWeight.ExtraBold)
        Text("Black (900)", fontWeight = FontWeight.Black)
    }
}

/**
 * 5. Combining Multiple Styles
 *
 * You can combine multiple style parameters together
 */
@Composable
fun CombinedStyles() {
    Text(
        text = "Hello World",
        color = Color.Blue,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    )
}

/**
 * Example: All Common Stylings Together
 */
@Composable
fun CommonTextStylingExamples() {
    Column {
        Text("Default Text")
        Spacer(modifier = Modifier.height(8.dp))

        BlueText()
        Spacer(modifier = Modifier.height(8.dp))

        BigText()
        Spacer(modifier = Modifier.height(8.dp))

        ItalicText()
        Spacer(modifier = Modifier.height(8.dp))

        BoldText()
        Spacer(modifier = Modifier.height(8.dp))

        CombinedStyles()
    }
}

/**
 * Key Points:
 *
 * - color: Changes the text color
 * - fontSize: Changes the text size (use .sp units)
 * - fontStyle: Normal or Italic
 * - fontWeight: Controls boldness (Thin to Black)
 * - These apply to the ENTIRE text value
 * - Can be combined together
 * - For partial styling, use AnnotatedString
 */

