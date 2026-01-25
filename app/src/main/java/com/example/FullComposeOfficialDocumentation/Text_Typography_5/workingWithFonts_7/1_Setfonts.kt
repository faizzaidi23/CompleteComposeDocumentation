package com.example.FullComposeOfficialDocumentation.Text_Typography_5.workingWithFonts_7

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

/*
 ═══════════════════════════════════════════════════════════════════════════════
 DEFAULT FONT FAMILIES IN COMPOSE
 ═══════════════════════════════════════════════════════════════════════════════

 Text has a fontFamily parameter to set which font is used.

 By default, Compose includes 4 font families:
 1. FontFamily.Serif      - For traditional, formal text
 2. FontFamily.SansSerif  - Modern, clean text (default for most UIs)
 3. FontFamily.Monospace  - Fixed-width (for code, tables)
 4. FontFamily.Cursive    - Handwriting-style text

 These are system fonts and work on all Android devices.
*/

@Composable
fun DifferentFonts() {
    Column {
        Text("Serif Font", fontFamily = FontFamily.Serif)
        Text("SansSerif Font", fontFamily = FontFamily.SansSerif)
        Text("Monospace Font", fontFamily = FontFamily.Monospace)
        Text("Cursive Font", fontFamily = FontFamily.Cursive)

        // Default (if not specified, SansSerif is used)
        Text("Default Font")
    }
}