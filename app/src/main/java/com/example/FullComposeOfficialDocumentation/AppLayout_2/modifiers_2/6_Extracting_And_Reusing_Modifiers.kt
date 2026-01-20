package com.example.a2_composedocumentation_app_layout.Modifiers_2

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




/*
Recomposition is the process of Compose re-running your composable
 functions to update the UI when state changes.
  During animations or when scrolling, this can happen many
   times per second. Creating new objects
   on every recomposition is called re-allocation,
   and it's what we want to avoid for performance.
*/

// below is an example of unscoped modifier that means we can easily extract the unscoped modifiers out so they can be used where ever needed
private val reusableModifier=Modifier.size(150.dp)
    .background(Color(0xFF4CAF50))
    .padding(16.dp)

@Composable
fun PerformanceComparisonScreen() {
    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Reusing Modifiers Demo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text("Recomposition count: $counter")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { counter++ }) {
            Text("Trigger Recomposition")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- BAD PRACTICE ---
        BadPracticeExample(counter)

        Spacer(modifier = Modifier.height(32.dp))

        // --- GOOD PRACTICE ---
        GoodPracticeExample(counter)
    }
}

@Composable
fun BadPracticeExample(counter: Int) {
    Text("Inefficient Version", fontWeight = FontWeight.SemiBold)
    Text("Modifier re-allocated $counter times", fontSize = 12.sp)

    // This entire modifier chain is re-created every time the button is clicked.
    // This is inefficient and wastes resources.
    Column(
        modifier = Modifier
            .size(150.dp)
            .background(Color(0xFFF44336)) // Red
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Recomposing...", color = Color.White)
    }
}

@Composable
fun GoodPracticeExample(counter: Int) {
    Text("Efficient Version", fontWeight = FontWeight.SemiBold)
    Text("Modifier created ONCE", fontSize = 12.sp)

    // We just pass the reference to the pre-built 'reusableModifier'.
    // No new objects are created during recomposition. This is very fast.
    Column(
        modifier = reusableModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Recomposing...", color = Color.White)
    }
}

//val reusablleModifier = Modifier
//    .padding(12.dp)
//    .background(Color.Gray)
//
//@Composable
//fun LoadingWheelAnimation() {
//    val animatedState = animateFloatAsState(/*...*/)
//
//    LoadingWheel(
//        // No allocation, as we're just reusing the same instance
//        modifier = reusablleModifier,
//        animatedState = animatedState
//    )
//}

/*
Extracting and using scoped modifiers-->

When dealing with modifiers that are scoped to certain composable we can extract t hem to  the highest possible level and reuse where appropriate



Column(/*...*/) {
    val reusableItemModifier = Modifier
        .padding(bottom = 12.dp)
        // Align Modifier.Element requires a ColumnScope
        .align(Alignment.CenterHorizontally)
        .weight(1f)
    Text1(
        modifier = reusableItemModifier,
        // ...
    )
    Text2(
        modifier = reusableItemModifier
        // ...
    )
    // ...

*/

