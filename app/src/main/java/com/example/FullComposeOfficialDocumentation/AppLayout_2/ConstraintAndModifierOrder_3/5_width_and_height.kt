package com.example.a2_composedocumentation_app_layout.Constraint_And_Modifier_Order_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WidthHeightModifierScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "width & height Modifiers",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "This Text composable has a fixed width of 200.dp. Its height is flexible and grows to fit the content as the text wraps.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // This Text has its width fixed by the .width() modifier.
        // The height is determined by how many lines the wrapping text occupies.
        Text(
            text = "This is a long piece of text that demonstrates how the width modifier forces content to wrap to new lines.",
            color = Color.White,
            modifier = Modifier
                .width(200.dp) // Sets an exact width
                // The height is not specified, so it will wrap its content.
                .background(Color(0xFF0D47A1)) // Dark Blue
                .padding(16.dp)
        )
    }
}