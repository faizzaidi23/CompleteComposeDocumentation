package com.example.a2_composedocumentation_app_layout.Constraint_And_Modifier_Order_3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
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
sizeIn

This Modifier is like setting a boundary for a composable size. you give it
a minimum and or maximum width and size


The composable will first try to be just big enough to fit its content like the text
however it will never be smaller than the minimum  you set
and it will never be larger than the maximums you set


2--> fillMaxSize().size()


Theory: The First Modifier Wins
When you chain modifiers, the order is critical. Each modifier passes a new set of rules (constraints) to the next one in the chain.

fillMaxSize(): This modifier's job is to be exactly as big as the parent. It receives the parent's size (e.g., 300x200) and creates a very strict rule: "The component inside me must be exactly 300x200." It sets both the minimum and maximum constraints to this size.

.size(50.dp): This modifier receives the strict "must be 300x200" rule. The size modifier is a suggestion that has to obey the rules it's given. Since the rule says the minimum size is 300x200, the suggestion to be 50dp is impossible to follow.

Result: The .size(50.dp) modifier is effectively ignored, and the component remains at the full parent size.


3--> Chained Modifiers: fillMaxSize().wrapContentSize().size()

fillMaxSize(): Same as before, it sets a strict "must be 300x200" rule.

.wrapContentSize(): This modifier acts like a "reset button." It takes the strict rule it received but tells the next modifier, "You don't have to be 300x200 anymore. Your minimum size can be 0 again." It effectively relaxes the minimum constraint.

.size(50.dp): This modifier now receives a relaxed rule ("be between 0 and 300x200"). Its suggestion to be 50dp is now valid and is applied.

Result: The component becomes 50x50. As a final step, the wrapContentSize modifier takes this smaller component and centers it within the large area that fillMaxSize originally reserved.



You put a tiny photo (50x50) on a big cardboard (300x200).

Normally, fillMaxSize() would stretch the photo to cover the whole cardboard.

But wrapContentSize() says: “No need to stretch, keep the photo small.”

Instead, it just centers the small photo on the big cardboard.

*/


@Composable
fun ModifiersExplainedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- 1. sizeIn Modifier ---
        SectionTitle("1. sizeIn Modifier")
        Text("Sets a min/max size range. The box grows to the min size, but won't exceed the max.")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Short text, so the box is forced to the minimum size.
            Text(
                text = "Short",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .sizeIn(minWidth = 100.dp, minHeight = 50.dp, maxWidth = 200.dp)
                    .background(Color.Yellow)
                    .padding(4.dp)
            )
            // Long text, so the box is capped at the maximum size.
            Text(
                text = "This text is very long and will be capped",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .sizeIn(minWidth = 100.dp, minHeight = 50.dp, maxWidth = 150.dp)
                    .background(Color.Yellow)
                    .padding(4.dp)
            )
        }

        // --- 2. fillMaxSize().size() ---
        SectionTitle("2. fillMaxSize().size(50.dp)")
        Text("The .size() is ignored because .fillMaxSize() sets a strict minimum size.")
        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 100.dp)
                .border(2.dp, Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .size(50.dp) // This modifier is ignored.
                    .background(Color.Red.copy(alpha = 0.8f))
            )
        }

        // --- 3. fillMaxSize().wrapContentSize().size() ---
        SectionTitle("3. fillMaxSize().wrapContentSize().size(50.dp)")
        Text(".wrapContentSize() resets the minimums, allowing .size() to work. It then centers the result.")
        Box(
            modifier = Modifier
                .size(width = 300.dp, height = 100.dp)
                .border(2.dp, Color.DarkGray),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize() // This "resets" the constraints.
                    .size(50.dp)       // This now works as expected.
                    .background(Color.Green.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

