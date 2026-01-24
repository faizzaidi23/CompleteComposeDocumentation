package com.example.FullComposeOfficialDocumentation.Text_Typography_5.DisplayAndStyleText_1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//Common styling

@Composable
fun BlueText(){
    Text("Hello world",color=Color.Blue,fontWeight= FontWeight.Bold,fontSize=36.sp)
}