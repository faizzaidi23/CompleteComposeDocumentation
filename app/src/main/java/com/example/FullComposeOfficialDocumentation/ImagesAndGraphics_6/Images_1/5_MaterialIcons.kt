package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.Images_1

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.FullComposeOfficialDocumentation.R

/*
The Icon composable is a convenient way to draw a single color icon
on screen that follows Material Design Guidelines. To use Icon include the Compose Material library

for example if we had a vector drawable that you wanted to load up with Material defaults we can use the Icon composable as follows
*/


@Composable
fun IconExample(){
    Icon(
        painter= painterResource(R.drawable.dog),
        contentDescription = stringResource(id=R.string.dog_content_description)
    )
}

/*
By default, the Icon composable in tinted with LocalContentColor.current and is 24.dp in size
It also exposes a tint color parameter which leverages the same meachanism for tinting as described in the image tint section

The icon composable is intended for use for small icon elements. we should use the Image composable for more customization options
*/