package com.example.a2_composedocumentation_app_layout.Constraint_And_Modifier_Order_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//1-->size modifier--> declares the preferred size of the content


/*
Here are the key principles:

It Obeys the Parent: A composable can never be larger than the maximum size its parent allows, or smaller than the minimum size its parent requires.

It Clamps to the Bounds:

If you request a size that is within the parent's allowed range, the composable will be exactly that size.

If you request a size that is larger than the parent allows, the composable's size will be reduced to the parent's maximum allowed size.

If you request a size that is smaller than the parent requires, the composable's size will be increased to the parent's minimum required size.

Chaining Doesn't Work: When you use Modifier.size(100.dp),
it sets both the minimum and maximum constraints to exactly 100.dp.
If you chain another size modifier after it, like .size(50.dp),
that second modifier still has to obey the exact 100.dp
 rule it just received. Therefore, the second size modifier is ignored.
*/

@Composable
fun SizeModifier(){
    Column(
        modifier=Modifier.fillMaxSize()
            .padding(16.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ){

        Text("size Modifier Examples", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        val parentModifier=Modifier.sizeIn(minWidth = 50.dp, minHeight = 50.dp, maxWidth = 150.dp, maxHeight =150.dp )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("1. Size Fits (100.dp)", fontWeight = FontWeight.SemiBold)
            Box(
                modifier = parentModifier,
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp) // This is between 50dp and 150dp, so it's allowed.
                        .background(Color.Green)
                )
            }
        }

        // --- Case 2: The suggested size is larger than the constraints ---

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("2. Size Too Big (200.dp)", fontWeight = FontWeight.SemiBold)
            Box(
                modifier = parentModifier,
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(200.dp) // This is > 150dp, so it's clamped to 150dp.
                        .background(Color.Red)
                )
            }
        }


        // --- Case 3: The suggested size is smaller than the constraints ---
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("3. Size Too Small (30.dp)", fontWeight = FontWeight.SemiBold)
            Box(
                modifier = parentModifier,
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp) // This is < 50dp, so it's forced to be 50dp.
                        .background(Color.Yellow)
                )
            }
        }

        // --- Case 4: Chaining size modifiers ---
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("4. Chained Size (100.dp then 50.dp)", fontWeight = FontWeight.SemiBold)
            Box(
                modifier = parentModifier,
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp) // This sets the exact size to 100.dp.
                        .size(50.dp)  // This is ignored because it must obey the 100.dp constraint.
                        .background(Color.Cyan)
                )
            }
        }


    }
}