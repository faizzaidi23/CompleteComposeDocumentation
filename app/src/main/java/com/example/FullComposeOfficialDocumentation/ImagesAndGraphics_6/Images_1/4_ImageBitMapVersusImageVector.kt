package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.Images_1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.FullComposeOfficialDocumentation.R

/*
The two most common kinds of image formats are
raster images
vector images
*/

/*
A raster graphic format contains tiny individual squares that contain
a color made up of red green blue and alpha values

when placing a lot of pixels together a very detailed image can be formed such as a photograph
A raster graphic has fixed resolution i.e fixed number of pixels
This means when we increase the size of the image it loses the detail and pixelation
can occur.

eg are JPEG PNG and WEBP
*/

/*
vector images on the other hand are scalable mathematical representation of a visual element
on screen.
A vector is a set of commands describing how to draw the image on the screen for example
a line,point or fill.
When scaling a vector on screen it won't lose the quality as the mathematical formula maintains the relationship
between the different commands
*/

/*
ImageBitmap
In Compose a raster image often referred to as a Bitmap can be loaded up into an ImageBitmap instance
and a BitmapPainter is what is responsible for drawing the bitmap to screen.

For basic use cases painterResource() can be used to create an ImageBitmap and returns a Painter object
in this case a BitmapPainter
*/

@Composable
fun BasicImageBitmapExample() {
    /*
    painterResource automatically creates ImageBitmap and BitmapPainter for raster images
    This is the simplest way to load bitmap images like PNG, JPEG, WEBP
    */
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = stringResource(id = R.string.dog_content_description)
    )
}

/*
If we require further customization for example a custom painter implementation
and need access to the ImageBitmap itself we can load it in the following way
*/

@Composable
fun CustomImageBitmapExample() {
    /*
    ImageBitmap.imageResource loads the bitmap directly
    This gives us the actual ImageBitmap object for custom manipulation
    Use this when you need to process the bitmap or use a custom painter
    */
    val imageBitmap = ImageBitmap.imageResource(R.drawable.dog)

    /*
    Now we can use this imageBitmap for custom operations
    like applying filters, transformations, or using with custom painters
    */
}

/*
ImageVector
A VectorPainter is responsible for drawing an ImageVector to screen
ImageVector supports a subset of Scalable Vector Graphics SVG commands
Not all images can be represented as vectors for example the photos you take with your camera
cannot be transformed into a vector

We can create a custom ImageVector either by importing an existing vector drawable XML file
imported into Android Studio using the import tool or implementing the class and issuing path commands manually
*/

@Composable
fun BasicImageVectorExample() {
    /*
    painterResource works for ImageVectors in the same way it does for ImageBitmap
    It returns a VectorPainter as the result
    painterResource handles loading of VectorDrawables and BitmapDrawables into VectorPainter and BitmapPainter respectively
    Vector drawables are XML files that define paths and shapes
    */
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Shopping cart icon",
        modifier = Modifier.size(48.dp)
    )
}

/*
If we require further customization and need access to the ImageVector itself
we can load it in the following way
*/

@Composable
fun CustomImageVectorExample() {
    /*
    ImageVector.vectorResource loads the vector drawable directly
    This gives us the actual ImageVector object for custom manipulation
    Use this when you need to modify the vector programmatically or use a custom painter
    */

    /*
    Note: ImageVector.vectorResource only works with vector drawables (XML files)
    If you have a vector drawable, you would use it like this:
    val imageVector = ImageVector.vectorResource(id = R.drawable.your_vector_drawable)

    For demonstration purposes, this example shows the pattern
    In production, replace R.drawable.dog with an actual vector drawable resource
    */
}

/*
Complete example showing both ImageBitmap and ImageVector usage side by side
*/

@Composable
fun ImageBitmapVsImageVectorComparison() {
    Column {
        /*
        Raster image (Bitmap)
        Good for photos and complex graphics with gradients
        Fixed resolution, may pixelate when scaled up
        */
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = "Dog photo - raster image",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        /*
        Vector image
        Good for icons and simple graphics
        Scalable without quality loss
        Smaller file size for simple graphics

        Note: In production, replace this with an actual vector drawable
        Vector drawables are XML files in res/drawable folder
        */
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = "Icon example - vector image",
            modifier = Modifier.size(200.dp)
        )
    }
}

/*
When to use ImageBitmap
- Photos and camera images
- Complex graphics with many colors and gradients
- When exact pixel representation is needed
- File formats: PNG, JPEG, WEBP

When to use ImageVector
- Icons and logos
- Simple illustrations
- UI elements that need to scale to different sizes
- When you need crisp graphics at any size
- File format: XML vector drawables
*/
