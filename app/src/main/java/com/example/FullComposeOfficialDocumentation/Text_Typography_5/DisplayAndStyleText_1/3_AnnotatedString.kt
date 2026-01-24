package com.example.FullComposeOfficialDocumentation.Text_Typography_5.DisplayAndStyleText_1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

/*
To add multiple styles to the text

we use annotated strings to serve this purpose


AnnotatedString is a data class containing

A text value
A list of SpanStyleRange equivalent to inline styling with position range within the text value


we use SpanStyle and ParagraphStyle to use in AnnotatedString
*/



@Composable
fun MultipleStylesInText(){
    Text(
        buildAnnotatedString {
            withStyle(style=SpanStyle(color=Color.Blue, fontSize = 24.sp)){
                append("H")
            }
            append("ello")
            withStyle(style=SpanStyle(fontWeight = FontWeight.Bold)){
                append("W")
            }
            append("orld")
        }
    )
}