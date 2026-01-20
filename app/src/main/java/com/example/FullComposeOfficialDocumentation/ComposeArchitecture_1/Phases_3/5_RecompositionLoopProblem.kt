package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Phases_3


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import com.example.FullComposeOfficialDocumentation.R

/*
The recomposition loop problem

This is a common pitfall where you accidentally create a dependency that goes backward
across frames, causing UI jank
*/

@Composable
fun problematic(){
    Box{
        var imageHeightPx by remember{mutableStateOf(0)}
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription ="background image",
            modifier=Modifier.fillMaxWidth().onSizeChanged{
                size->
                // This updates the state, scheduling a NEW frame.
                imageHeightPx=size.height
            }
        )

        Text(
            text="I am below the image",
            modifier=Modifier.padding(top=with(LocalDensity.current){imageHeightPx.toDp()})
        )
    }
}

/*
That piece of code is problematic because it creates a recomposition loop, causing the UI to flicker or "jank" for one frame before settling into the correct layout.

The core issue is that you are trying to use information from a later phase (Layout) to influence an earlier phase (Composition) in the next frame, which is an inefficient, backward flow of data.
*/

/*
Frame 1: The Incorrect Frame 🖼️
Composition Phase: The code runs. The state variable imageHeightPx is 0. The Text is created with a top padding of 0.dp.

Layout Phase: The Image is measured, and its actual height is calculated (e.g., 150 pixels). The onSizeChanged modifier is triggered, and it updates the state: imageHeightPx = 150.

Drawing Phase: It's too late for this frame! The Text is drawn with the original padding of 0, causing it to overlap with the Image. The user sees this incorrect layout for a split second.

Because a state was changed, Compose schedules a new frame to be drawn.

Frame 2: The Correct Frame 🖼️
Composition Phase: The code re-runs (recomposes). This time, imageHeightPx is 150. The Text is correctly created with the right amount of top padding.

Layout & Drawing Phases: The UI is now drawn correctly, with the Text positioned perfectly below the Image.

The final result is correct, but it took an extra,
inefficient frame to get there, producing a visible flicker.
The proper solution is to use a layout like Column,
which is designed to measure and place its children
sequentially in a single frame without this kind of loop.
*/



/*
Correct Version



Use of Column

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ImageWithTextBelow() {
    // Use a Column to place items vertically, one after the other.
    Column {
        Image(
            painter = painterResource(id = R.drawable.your_image_here), // Replace with your image
            contentDescription = "I'm above the text",
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "I'm below the image"
            // No special padding is needed. The Column handles the placement.
        )
    }
}
*/