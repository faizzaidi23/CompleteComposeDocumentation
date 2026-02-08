package com.example.FullComposeOfficialDocumentation.Accessibility_9.ApiDefaults_1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/*
When you define an Image or Icon composable, there is no automatic way for the Android framework
to understand what the app is displaying. You need to pass a textual description of the graphic element.

The contentDescription parameter describes a graphic element. Use a localized string, as it is visible to the user.
*/

/*
ContentDescription helps visually impaired users understand what type of icon is displayed
and what will happen by clicking it
*/
@Composable
fun ShareButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "Share"
        )
    }
}

/*
Some graphic elements are purely decorative and you might not want to communicate them to the user.
When you set the contentDescription parameter to null, you indicate to the Android framework
that this element does not have associated actions or state
*/
@Composable
fun DecorativePostImage(imageRes: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

/*
contentDescription is mainly meant to be used for graphic elements, such as images.
Material components like Button or Text, and actionable behaviors like clickable or toggleable,
come with other predefined semantics that describe their intrinsic behavior
*/
