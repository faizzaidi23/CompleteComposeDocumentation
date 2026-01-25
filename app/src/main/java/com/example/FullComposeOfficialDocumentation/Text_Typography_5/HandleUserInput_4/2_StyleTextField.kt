package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/*
 Styling TextField with textStyle and other parameters

 Common customization parameters:
 - textStyle: Controls font, color, weight, size
 - placeholder: Shows hint when empty
 - label: Material Design label
 - singleLine vs maxLines: Control line behavior
 - modifier: Standard Compose modifiers
*/

/*
 Styled TextField with custom text style

 textStyle applies to the user input:
 - color: Text color
 - fontWeight: Bold, Normal, Light, etc
 - fontSize, fontFamily, etc
*/
@Composable
fun StyledTextField() {
    var text by remember { mutableStateOf("Hello\nWorld") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Enter text here") },
        textStyle = TextStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        ),
        label = { Text("Enter text") },
        modifier = Modifier.padding(20.dp),
        maxLines = 3
    )
}

/*
 SingleLine TextField

 singleLine = true restricts to one line
 Text scrolls horizontally when it exceeds width
*/
@Composable
fun SingleLineTextField() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Single line input") },
        label = { Text("Username") },
        singleLine = true
    )
}

/*
 MultiLine TextField

 maxLines controls maximum visible lines
 minLines controls minimum lines to show
*/
@Composable
fun MultiLineTextField() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Enter your message") },
        label = { Text("Message") },
        maxLines = 5,
        minLines = 3
    )
}

