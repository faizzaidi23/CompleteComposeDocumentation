package com.example.FullComposeOfficialDocumentation.Text_Typography_5.ParagraphStyling_2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

/*
To set different styling on the paragraph we use annotated strings which can be annotated
with styles of arbitrary annotations.
Once a portion of text is marked with a ParagraphStyle that portion is separated from the remaining text as if it had line feeds at the beginning and end
*/


/*
what ParagraphStyle actually does-->
ParagraphStyle affects how the text is laid out, not how the individual letters look
*/

@Composable
fun ParagraphStyle(){
    Text(
        buildAnnotatedString {
            withStyle(style=ParagraphStyle(lineHeight=30.sp)){
                withStyle(style=SpanStyle(Color.Blue)){
                    append("Hello\n")
                }
                withStyle(
                    style=SpanStyle(fontWeight = FontWeight.Bold,color=Color.Red)
                ){
                    append("World\n")
                }
                append("Compose")
            }
        }
    )
}