package com.example.FullComposeOfficialDocumentation.Text_Typography_5.TextLayout_3

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

/*
when limiting a long text we may want to indicate a TextOverflow which is only
shown if the displayed text is truncated. To do so, we need to set the textOverflow parameter
*/
/*
ellipsis means showing ...(three dots) when the text is cut off.
*/
@Composable
fun OverflowedText(){
    Text("Hello Compose".repeat(50),maxLines=2,overflow= TextOverflow.Ellipsis)
}