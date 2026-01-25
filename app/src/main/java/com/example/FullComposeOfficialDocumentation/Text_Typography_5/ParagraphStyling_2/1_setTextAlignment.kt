package com.example.FullComposeOfficialDocumentation.Text_Typography_5.ParagraphStyling_2

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
The textAlign parameter lets us set the horizontal alignment of the text within the text composable surface area

By default the text will select the natural text alignment depending on its content value
*/

@Composable
fun CenterText(){
    Text(
        "Hello world",textAlign=TextAlign.Center,modifier=Modifier.width(150.dp)
    )
}