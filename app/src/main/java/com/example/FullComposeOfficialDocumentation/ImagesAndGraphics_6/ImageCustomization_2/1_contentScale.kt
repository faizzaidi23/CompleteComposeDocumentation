package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.ImageCustomization_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.FullComposeOfficialDocumentation.R

/*
Specify a contentScale option to crop or change how an image is scaled inside its bounds
By default if we do not specify a contentScale option, ContentScale.Fit will be used

*/

/*
contentScale=ContentScale.Fit means---->

Scale the image uniformly (keeping the aspect ratio)
so that the entire image fits indie the given bounds without
cropping anything


But it may leave some empty space around the image


here the image container is 150*150
The dog will be having its own aspect ratio maybe wide or tall or something
with ContentScale.Fit

Compose will resize the image so the whole dog is visible
If the dog image is rectangular we will see yellow space on the top and the bottom sides


*/
@Composable
fun ContentScaleExample(){


    val imageModifier=Modifier.size(150.dp)
        .border(BorderStroke(1.dp,Color.Black))
        .background(Color.Yellow)

    Image(
        painter= painterResource(id=R.drawable.dog),
        contentDescription = stringResource(id=R.string.dog_content_description),
        contentScale=ContentScale.Fit,
        modifier=imageModifier
    )
}