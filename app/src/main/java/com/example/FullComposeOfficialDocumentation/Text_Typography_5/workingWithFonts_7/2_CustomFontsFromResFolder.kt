package com.example.FullComposeOfficialDocumentation.Text_Typography_5.workingWithFonts_7

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 ═══════════════════════════════════════════════════════════════════════════════
 CUSTOM FONTS FROM res/font FOLDER - REQUIRES FONT FILES
 ═══════════════════════════════════════════════════════════════════════════════

 ⚠ This file shows how to use custom fonts, but requires actual font files.

 To use custom fonts:
 1. Download font files (.ttf or .otf) - e.g., from Google Fonts
 2. Place them in app/src/main/res/font/ directory
 3. Name files in lowercase with underscores (e.g., firasans_bold.ttf)
 4. Create a FontFamily with Font() for each weight/style variant

 Example font files needed:
 - firasans_light.ttf
 - firasans_regular.ttf
 - firasans_italic.ttf
 - firasans_medium.ttf
 - firasans_bold.ttf

 Benefits:
 - Professional typography
 - Brand consistency
 - Better design control

 Note: Font files are bundled with the app, increasing APK size
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 EXAMPLE CODE (UNCOMMENT AFTER ADDING FONT FILES)
 ═══════════════════════════════════════════════════════════════════════════════

 import androidx.compose.ui.text.font.Font
 import androidx.compose.ui.text.font.FontStyle
 import com.example.FullComposeOfficialDocumentation.R

 Structure:
 FontFamily(
     Font(resourceId, weight, style)
 )

 FontWeight options: Thin, Light, Normal, Medium, SemiBold, Bold, ExtraBold, Black
 FontStyle options: Normal, Italic

 val firaSansFamily = FontFamily(
     Font(R.font.firasans_light, FontWeight.Light),
     Font(R.font.firasans_regular, FontWeight.Normal),
     Font(R.font.firasans_italic, FontWeight.Normal, FontStyle.Italic),
     Font(R.font.firasans_medium, FontWeight.Medium),
     Font(R.font.firasans_bold, FontWeight.Bold)
 )
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 TEMPORARY IMPLEMENTATION (Using system fonts as placeholder)
 ═══════════════════════════════════════════════════════════════════════════════
*/

// Placeholder using system fonts (replace with custom fonts when you add font files)
val firaSansFamily = FontFamily.SansSerif

@Composable
fun CustomFontExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "⚠️ Custom fonts not configured",
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "To use custom fonts:",
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp
        )

        Text(
            text = "1. Add .ttf files to res/font/ folder",
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp
        )

        Text(
            text = "2. Uncomment the code in this file",
            fontFamily = FontFamily.Monospace,
            fontSize = 11.sp
        )

        Text(
            text = "\nCurrent: Using system fonts as placeholder",
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Example with different weights (using system font)
        Text(
            text = "Light Text (system)",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.Light
        )

        Text(
            text = "Normal Text (system)",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.Normal
        )

        Text(
            text = "Medium Text (system)",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Bold Text (system)",
            fontFamily = firaSansFamily,
            fontWeight = FontWeight.Bold
        )
    }
}

/*
 ═══════════════════════════════════════════════════════════════════════════════
 HOW IT WORKS (WHEN USING CUSTOM FONTS)
 ═══════════════════════════════════════════════════════════════════════════════

 When you set fontWeight = FontWeight.Bold:
 1. Compose looks in firaSansFamily for a Font with FontWeight.Bold
 2. Finds Font(R.font.firasans_bold, FontWeight.Bold)
 3. Uses that font file to render the text

 If you request a weight not defined (e.g., FontWeight.Black):
 - Compose uses the closest available weight
 - Or synthesizes it (may look less professional)

 ═══════════════════════════════════════════════════════════════════════════════
 WHERE TO GET FONTS
 ═══════════════════════════════════════════════════════════════════════════════

 1. Google Fonts (free): https://fonts.google.com/
    - Download font family
    - Extract .ttf files
    - Rename to lowercase with underscores

 2. Font Squirrel (free): https://www.fontsquirrel.com/

 3. Adobe Fonts (subscription)

 4. Custom branded fonts from your design team

 Popular free fonts for apps:
 - Roboto (Android default)
 - Inter (modern UI)
 - Poppins (friendly, rounded)
 - Montserrat (geometric sans-serif)
 - Open Sans (readable body text)
*/
