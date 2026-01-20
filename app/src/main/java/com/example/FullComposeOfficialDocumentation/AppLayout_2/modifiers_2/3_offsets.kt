package com.example.a2_composedocumentation_app_layout.Modifiers_2

import android.R.attr.x
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
An offset is a modifier that lets you shift a composable from its original measured position
on the screen, without affecting the layout of any other composable around it.


The original spot remains empty,and other components
are placed as if the offset component was still there.
*/

@Composable
fun OffsetExample(){
    Column(
        modifier=Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier=Modifier.size(100.dp).background(Color.Blue)
        )

        // The second box which is offset
        // Its original position is directly below the blue box
        Box(
            modifier=Modifier.size(100.dp)
                .offset(x=40.dp,y=20.dp) // Shifted 40dp to the right and 20 dp down
                .background(Color.Red)
        )

        // The third box
        // Notice it is placed directly below the original position of the
        //red box ignoring its offset. It overlaps with the red box's new position

        Box(
            modifier=Modifier.size(100.dp)
                .background(Color.Green)
        )



    }
}

//@Composable
//fun OffsetLambdaExampleScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Text(
//            text = "Offset (Lambda) Demo",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Text(
//            "The green box is positioned inside a larger grey box. We use the offset lambda to dynamically move it up and to the left by 50% of its own size.",
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // A container to provide context for the offset
//        Box(
//            modifier = Modifier
//                .size(250.dp)
//                .background(Color.LightGray),
//            contentAlignment = Alignment.Center
//        ) {
//            // A simple box to show the original position before offset
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color.Black.copy(alpha = 0.2f))
//            ) {
//                Text("Original\nPosition", color = Color.White, modifier = Modifier.align(Alignment.Center))
//            }
//
//            // The box that we will apply the offset to
//            Box(
//                modifier = Modifier
//                    .size(100.dp)
//                    .background(Color(0xFF4CAF50)) // Green
//                    // --- This is the lambda version of offset ---
//                    //.offset {
//                        // 'this' is a Density scope, so we can convert Dp to Px
//                        // 'size' is the measured size of this Box (100.dp x 100.dp) in pixels
//                       // val xOffset = -size.width / 2
//                       // val yOffset = -size.height / 2
//
//                        // The lambda must return an IntOffset
//                         //IntOffset(x = xOffset, y = yOffset)
//                    },
//                //contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "Offset Box",
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }
//    }
//}
//
