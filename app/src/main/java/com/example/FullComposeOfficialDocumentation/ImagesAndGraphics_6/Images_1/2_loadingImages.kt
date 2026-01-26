package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.Images_1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a1_composedocumentationuiarchitecture.R

/*
To ensure that the app is accessible supply a contentDescription for visual elements
on screen. TalkBack reads out the content description, so we must ensure that the text
is meaningful if read out loud and translated.
Here in this example stringResource() is used to load up the translated content description from the strings.xml file.

If our visual element on screen is purely for visual decoration we set the contentDescription to null for the screen reader to ignore it.
*/

/*
painterResource API loads images from disk without needing to know the asset type.
It automatically handles different drawable formats.
Just use painterResource with your image reference in Image composable or paint modifiers.
*/

@Composable
fun LoadingImage(){
    Image(
        painter = painterResource(R.drawable.dog),
        contentDescription = stringResource(id=R.string.dog_content_description)
    )
}

/*
painterResource supports multiple drawable types.
Below are examples for each supported drawable type using the same image.
*/

@Composable
fun AllDrawableTypesExample() {
    Column {
        BitmapDrawableExample()
        Spacer(modifier = Modifier.height(16.dp))
        VectorDrawableExample()
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVectorDrawableExample()
        Spacer(modifier = Modifier.height(16.dp))
        ColorDrawableExample()
    }
}

/*
BitmapDrawable - Loads raster images like PNG, JPEG, WEBP
These are pixel-based images stored in drawable folders.
Common for photos and complex graphics with gradients.
*/
@Composable
fun BitmapDrawableExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

/*
VectorDrawable - Loads XML vector graphics
Scalable without quality loss, defined in XML format.
Ideal for icons and simple illustrations.
Smaller file size compared to bitmaps.
*/
@Composable
fun VectorDrawableExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

/*
AnimatedVectorDrawable - Loads animated vector graphics
XML-based animations for vector drawables.
Can animate properties like rotation, translation, color, path morphing.
Smooth animations that scale to any size.
*/
@Composable
fun AnimatedVectorDrawableExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

/*
ColorDrawable - Simple solid color drawable
Defined in XML or programmatically as a single color.
Useful for backgrounds, dividers, or placeholder graphics.
Very lightweight, just stores a color value.
*/
@Composable
fun ColorDrawableExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description),
        modifier = Modifier.size(200.dp)
    )
}

/*
For lower-level ImageBitmap specific functionality use ImageBitmap.imageResource() to load a Bitmap.
This gives more control over bitmap manipulation and pixel-level operations.
*/
