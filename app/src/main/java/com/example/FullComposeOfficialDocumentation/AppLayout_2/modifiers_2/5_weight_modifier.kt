package com.example.a2_composedocumentation_app_layout.Modifiers_2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
weight modifier is a fundaments concept for creating flexible and responsive
layouts in jetpack compose

*/

/*
How weight Works
Calculate Total Weight: The layout first adds up all the weights of its direct children.

Determine Available Space: It figures out how much space it has to distribute (e.g., the full width of the screen for a Row with fillMaxWidth()).

Distribute Space: It divides the available space among the weighted children based on their individual weight relative to the total weight.

A child with weight(2f) in a Row with a total weight of 3f will get 2/3 of the available width. A child with weight(1f) will get the remaining 1/3.
*/

@Composable
fun WeightExampleScreen(){
    Column(
        modifier=Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text="Weight modifier demo",
            fontSize=24.sp,
            fontWeight= FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Row with weights 2f and 1f",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
            /*
            Modifier.align() positions the entire Text component within its parent (Column).

            textAlign aligns the text inside the Text component's own box.
            */
        )
        Text(
            "The first box gets 2/3 of the width, and the second gets 1/3.",
            modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // This Box gets 2 parts of the space
            WeightedBox(
                text = "Weight = 2",
                color = Color(0xFFF44336), // Red
                modifier = Modifier.weight(2f)
            )

            // This Box gets 1 part of the space
            WeightedBox(
                text = "Weight = 1",
                color = Color(0xFF2196F3), // Blue
                modifier = Modifier.weight(1f)
            )

    }
}
}

// A simple composable to display a colored box that fills its assigned space

@Composable
fun RowScope.WeightedBox(text:String,color:Color,modifier:Modifier= Modifier){

    Box(
        modifier = modifier
            .fillMaxHeight() // Fill the height of the Row
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun ColumnScope.WeightedBox(text: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth() // Fill the width of the Column
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
