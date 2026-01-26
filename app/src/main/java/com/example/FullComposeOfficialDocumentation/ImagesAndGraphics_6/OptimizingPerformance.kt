package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
OPTIMIZING PERFORMANCE FOR IMAGES

Working with images can quickly introduce performance issues if we are not careful
We can quite easily run into an OutOfMemoryError when working with large bitmaps
Follow these best practices to ensure the app performs at its best
*/

/*
TIP 1: Only load the size of the bitmap you need

Most smartphones have high resolution cameras that produce large image files
If we are showing an image on screen we must either reduce the resolution of the image or only load the image
up to the size of the image container

The constant loading of larger than needed images can exhaust GPU caches leading to less performant UI rendering
*/

/*
To manage image sizes:

Scale down the image files to be as small as possible without affecting output image quality

Consider converting the images to WEBP format instead of JPEG or PNG
WEBP provides better compression and smaller file sizes

Supply smaller images for different screen resolutions
This helps reduce memory usage on lower resolution devices

Use an image loading library which scales down the image to fit the size of your view on screen
Libraries like Coil or Glide automatically handle this
This can help improve the loading performance of the screen

CAUTION: Using painterResource will NOT scale your image to the size of the Composable that is visible on screen
If you have a large image in a small Composable be sure to use an image loading library
which scales the image down for you to fit the bounds
*/

/*
TIP 2: Use vectors over bitmaps wherever possible

When representing something visually on screen we need to decide if it can be
represented as a vector or not

Prefer vector images over bitmaps as they do not pixelate when we scale them to different sizes
Vectors are resolution independent and typically have smaller file sizes

However not everything can be represented as a vector
Images taken with a camera cannot be converted into a vector
Photos and complex images must remain as bitmaps
*/

/*
TIP 3: Supply alternative resources for different screen sizes

If we are shipping images with the app consider supplying different sized assets for different device resolutions

This can help reduce the download size of the app on devices
It improves performance as it will load up a lower resolution image on a lower resolution device

For example provide drawable-mdpi drawable-hdpi drawable-xhdpi versions
The system will automatically pick the appropriate resolution

For more information on providing alternative bitmaps for different device sizes
check out the alternative bitmap documentation
*/

/*
TIP 4: When using ImageBitmap call prepareToDraw before drawing

When using ImageBitmap to start the process of uploading the texture to the GPU
call ImageBitmap.prepareToDraw before actually drawing it

This helps the GPU prepare the texture and improve the performance of showing a visual on screen

Most image loading libraries already do this optimization
But if you are working with the ImageBitmap class yourself it is something to keep in mind
*/

/*
TIP 5: Prefer passing an Int DrawableRes or URL as parameters into your composable instead of Painter

Due to the complexities of dealing with images
for example writing an equals function for Bitmaps would be computationally expensive
the Painter API is explicitly not marked as a Stable class

Unstable classes can lead to unnecessary recompositions
because the compiler cannot easily infer if the data has changed

Therefore it is preferable to pass a URL or drawable resource ID as parameters to your composable
instead of passing a Painter as a parameter
*/

/*
Example of what to prefer
*/
@Composable
fun MyImageGoodExample(drawableRes: Int) {
    /* Pass resource ID instead of Painter */
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = "Good example",
        modifier = Modifier.size(100.dp)
    )
}

@Composable
fun MyImageWithUrl(url: String) {
    /* Pass URL string instead of Painter */
    /* In real app you would use Coil or Glide to load from URL */
    Text("Load image from: $url")
}

/*
Example of what to avoid
*/
@Composable
fun MyImageBadExample(painter: Painter) {
    /* Avoid passing Painter directly as it is not Stable */
    /* This can cause unnecessary recompositions */
    Image(
        painter = painter,
        contentDescription = "Bad example",
        modifier = Modifier.size(100.dp)
    )
}

/*
TIP 6: Do not store a bitmap in memory longer than you need it

The more bitmaps you load into memory the more likely it is that you could run out of memory on the device

For instance if loading a large list of Image composables on screen
use LazyColumn or LazyRow to ensure that memory is freed up when scrolling a large list

LazyColumn and LazyRow only compose and layout items that are visible on screen
Items that scroll off screen are removed from memory
*/

/*
Example of good practice with large image lists
*/
@Composable
fun LargeImageListGoodExample() {
    val imageList = remember { List(100) { R.drawable.dog } }

    /* LazyColumn only keeps visible items in memory */
    LazyColumn {
        items(imageList) { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "List item",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )
        }
    }
}

/*
Example of bad practice with large image lists
*/
@Composable
fun LargeImageListBadExample() {
    val imageList = remember { List(100) { R.drawable.dog } }

    /* Regular Column keeps ALL items in memory at once */
    /* This can cause OutOfMemoryError with many images */
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        imageList.forEach { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "List item",
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )
        }
    }
}

/*
TIP 7: Do not package large images with your AAB or APK file

One of the top causes for large app download size is due to graphics that are packaged inside the AAB or APK file

Use the APK analyzer tool to ensure that you are not packaging larger than required image files

Reduce the sizes of images or consider placing the images on a server and only downloading them when required

For images that are not essential for the initial app experience download them on demand
This reduces the initial app download size and improves installation time
*/

/*
Example of using ImageBitmap.prepareToDraw
*/
@Composable
fun PrepareToDrawExample() {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.dog)

    /* Call prepareToDraw to upload texture to GPU before drawing */
    remember(imageBitmap) {
        imageBitmap.prepareToDraw()
        imageBitmap
    }

    Image(
        bitmap = imageBitmap,
        contentDescription = "Image with prepareToDraw",
        modifier = Modifier.size(200.dp)
    )
}

/*
SUMMARY OF BEST PRACTICES:

1. Only load bitmap sizes you need
   Use image loading libraries that scale images automatically

2. Use vectors over bitmaps when possible
   Vectors do not pixelate and have smaller file sizes

3. Supply alternative resources for different screen sizes
   Use drawable-mdpi drawable-hdpi drawable-xhdpi etc

4. Call prepareToDraw before drawing ImageBitmap
   This optimizes GPU texture upload

5. Pass Int or String parameters instead of Painter
   Painter is not Stable and can cause unnecessary recompositions

6. Do not store bitmaps longer than needed
   Use LazyColumn or LazyRow for large lists of images

7. Do not package large images in AAB or APK
   Use APK analyzer and consider server-side images

Following these practices will help prevent OutOfMemoryError
and ensure smooth performant image rendering in your Compose app
*/

/*
Complete example demonstrating all best practices
*/
@Composable
fun ImagePerformanceBestPracticesDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Image Performance Best Practices",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Good: Pass resource ID",
            fontWeight = FontWeight.Medium
        )
        MyImageGoodExample(drawableRes = R.drawable.dog)

        Text(
            text = "Good: Use LazyColumn for lists",
            fontWeight = FontWeight.Medium
        )
        /* LazyColumn shown in separate example */

        Text(
            text = "Good: Use prepareToDraw",
            fontWeight = FontWeight.Medium
        )
        PrepareToDrawExample()
    }
}
