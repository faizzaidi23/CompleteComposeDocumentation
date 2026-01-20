package com.example.a2_composedocumentation_app_layout.Modifiers_2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
if you want to make a child as big as the parent Box without
affecting the Box size, use the matchParentSize modifier.
matchParentSize is only available in BoxScope. Therefore,
it can only be used on a child within a Box parent.
*/


@Composable
fun MatchParentSizeExampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "matchParentSize Demo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "The Box below determines its size from its largest child (the green box). The blue border uses matchParentSize to wrap perfectly around it.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // --- Example Usage of matchParentSize ---
        Box(
            // This Box will "wrap content" by default. Its size will be
            // determined by the largest non-matching child.
            contentAlignment = Alignment.Center
        ) {
            // This is the main content. The parent Box will size itself
            // to be large enough to hold this 150x100 dp box.
            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF4CAF50)) // Green
            ) {
                Text(
                    "Main Content",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }

            // This border uses matchParentSize. It will be measured *after*
            // the green box. It will then expand to match the size that the
            // parent Box has decided on (150x100 dp).
            Box(
                modifier = Modifier
                    .matchParentSize() // The key modifier!
                    .border(
                        width = 4.dp,
                        color = Color(0xFF2196F3), // Blue
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }
    }



}

/*
Here’s how the parent Box thinks:

Step 1: "How big do I need to be?"

The parent Box first looks at all its children that have a defined size (like the green box). It ignores any children with matchParentSize for now.

It sees the green box is 150dp by 100dp.

It says, "Okay, to hold my content, I need to make myself 150dp by 100dp." So, the parent Box sets its own size.

Step 2: "Now, size up the rest."

The parent Box now goes to the children it ignored—the ones with matchParentSize (our blue border box).

It tells the blue box, "My final size is 150dp by 100dp. Your job is to match me."
*/