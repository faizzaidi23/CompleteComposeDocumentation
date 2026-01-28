package com.example.FullComposeOfficialDocumentation.Graphics_7.Shadows_5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

/*
Shadows visually elevate the UI, indicating interactivity to users, and provide
immediate feedback on user actions.
Compose provides various several ways to incorporate shadows in the app
*/

/*
Modifier.shadow()--->Creates an elevation based shadow behind a composable that conforms to Material Design guidelines
Modifier.dropShadow()--->Creates a customizable shadow that appears behind a composable, making it appear elevated
Modifier.innerShadow()--->Creates a shadow inside the borders of a composable making it appear pressed into the surface behind it
*/

/*
Creating basic shadows-->

Modifier.shadow() creates a basic shadow following the guidelines that simulates a light source
from above. The shadow depth is based on an elevation value, and the case shadow to the shape of the composable
*/

@Composable
fun ElevationBasedShadow(){
    Box(
        modifier=Modifier.aspectRatio(1f).fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Box(Modifier.size(100.dp,100.dp)
            .shadow(10.dp, RectangleShape)
            .background(Color.White)
        )
    }
}