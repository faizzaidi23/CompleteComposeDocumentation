package com.example.FullComposeOfficialDocumentation.ImagesAndGraphics_6.Images_1

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage

/*
To load an image from the internet there are several third party libraries available to help
in handling the process. Image loading libraries do a lot of heavy lifting for us.
They handle both caching so we do not download the image multiple times and networking logic to download the image
and display it on the screen

Popular libraries:
- Coil (recommended for Compose) - Modern, Kotlin-first image loading library
- Glide - Well established, feature rich
- Fresco - Advanced features for large images

For Coil, add this dependency in build.gradle.kts:
implementation("io.coil-kt:coil-compose:2.5.0")

Also add internet permission in AndroidManifest.xml:
<uses-permission android:name="android.permission.INTERNET" />
*/

@Composable
fun LoadImageFromInternet(){
    /*
    AsyncImage is from Coil library
    It automatically handles downloading, caching, and displaying images from URLs
    The model parameter takes the URL string
    contentDescription is for accessibility
    */
    AsyncImage(
        model = "https://example.com/image.jpg",
        contentDescription = "Translated description of what the image about"
    )

}