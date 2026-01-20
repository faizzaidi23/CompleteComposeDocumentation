package com.example.a2_composedocumentation_app_layout.Constraint_And_Modifier_Order_3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



/*
requiredSize modifier is a command
it breaks the rules

Here's what it does:

It Ignores Parent's Rules: It completely disregards the minimum and maximum size constraints given by its parent layout.

It Sets an Exact Size: It forces the composable to be the exact width and height you specify, no matter what.

It Can Overflow: Because it ignores the parent's rules, a composable with requiredSize can easily draw outside the bounds of its parent, creating a visual overflow.

In short, use size when you want a composable to be a specific size if possible
, and use requiredSize when you need it to be a specific size,
no matter what.
*/

@Composable
fun RequiredSizeModifierScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "requiredSize Modifier Example",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "The gray parent Box is 100.dp. The red child Box is required to be 150.dp, so it overflows the parent's bounds.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // The parent Box provides the initial constraints. It is 100x100 dp.
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(2.dp, Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            // This child Box uses requiredSize. It ignores the 100.dp constraint
            // from the parent and forces its size to be 150x150 dp.
            Box(
                modifier = Modifier
                    .requiredSize(150.dp)
                    .background(Color.Red.copy(alpha = 0.7f))
            ) {
                Text(
                    "150.dp",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}