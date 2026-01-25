package com.example.FullComposeOfficialDocumentation.Text_Typography_5.EnableUserInteractions_6

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink

/*
To listen for clicks on the text we can add the clickable modifier


However if we want to attach extra information to a certain part of the text value like a URL attached to a certain
word to be opened in a browser.
In cases like this we need to use a LinkAnnotation which is an annotation that represents a clickable part of the text


With linkAnnotation we can attach  a URL to a part of the text composable that automatically
opens once clicked
*/


@Composable

fun AnnotatedStringWithLinkSample(){
    Text(
        buildAnnotatedString {
            append("Go to the ")
            withLink(
                LinkAnnotation.Url(
                    "https://developer.android.com/",
                    TextLinkStyles(style= SpanStyle(color=Color.Blue))
                )
            ){
                append("Android developers")
            }
            append("Website, and check out the ")
            withLink(
                LinkAnnotation.Url(
                    "https://developer.android.com/jetpack/compose",
                    TextLinkStyles(style=SpanStyle(color=Color.Green))
                )
            ){
                append("Compose Guidance")
            }
            append(".")
        }
    )
}