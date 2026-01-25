package com.example.FullComposeOfficialDocumentation.Text_Typography_5.TextLayout_3

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/*
Using textLayout we can configure the text layout with parameters like maxLines and
overflow
*/

//Limit visible lines in text composable, set the maxLines parameter

@Composable
fun LongText(){
    Text("Hello".repeat(50),maxLines=2)
}