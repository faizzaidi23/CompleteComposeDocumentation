package com.example.FullComposeOfficialDocumentation.Text_Typography_5.workingWithFonts_7

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import com.example.FullComposeOfficialDocumentation.R

/*
 ═══════════════════════════════════════════════════════════════════════════════
 DOWNLOADABLE FONTS (GOOGLE FONTS) - REQUIRES ADDITIONAL DEPENDENCY
 ═══════════════════════════════════════════════════════════════════════════════

 ⚠️ IMPORTANT: This file is currently COMMENTED OUT because it requires adding:

 In build.gradle.kts (Module: app), add to dependencies:
 implementation("androidx.compose.ui:ui-text-google-fonts:1.7.6")

 Also create res/values/font_certs.xml with Google Fonts certificates.
 See full certificate XML in comments below.

 Once dependency is added, uncomment the code sections marked with "UNCOMMENT AFTER ADDING DEPENDENCY"

 ═══════════════════════════════════════════════════════════════════════════════

 Starting in Compose 1.2.0, you can download Google Fonts asynchronously.

 Advantages:
 - Reduces APK size (fonts downloaded on-demand)
 - Access to 1000+ Google Fonts
 - Fonts cached by system (shared across apps)

 Limitations:
 - Requires internet connection for first download
 - Only Google Fonts supported (no custom providers yet)
 - Requires proper certificate configuration

 Dependencies needed:
 implementation("androidx.compose.ui:ui-text-google-fonts:1.7.6")
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 CERTIFICATE CONFIGURATION
 ═══════════════════════════════════════════════════════════════════════════════

 Create a file: app/src/main/res/values/font_certs.xml

 Content:

 <?xml version="1.0" encoding="utf-8"?>
 <resources>
     <array name="com_google_android_gms_fonts_certs">
         <item>@array/com_google_android_gms_fonts_certs_dev</item>
         <item>@array/com_google_android_gms_fonts_certs_prod</item>
     </array>
     <string-array name="com_google_android_gms_fonts_certs_dev">
         <item>
             MIIEqDCCA5CgAwIBAgIJANWFuGx90071MA0GCSqGSIb3DQEBBAUAMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTAeFw0wODA0MTUyMzM2NTZaFw0zNTA5MDEyMzM2NTZaMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTCCASAwDQYJKoZIhvcNAQEBBQADggENADCCAQgCggEBANbOLggKv+IxTdGNs8/TGFy0PTP6DHThvbbR24kT9ixcOd9W+EaBPWW+wPPKQmsHxajtWjmQwWfna8mZuSeJS48LIgAZlKkpFeVyxW0qMBujb8X8ETrWy550NaFtI6t9+u7hZeTfHwqNvacKhp1RbE6dBRGWynwMVX8XW8N1+UjFaq6GCJukT4qmpN2afb8sCjUigq0GuMwYXrFVee74bQgLHWGJwPmvmLHC69EH6kWr22ijx4OKXlSIx2xT1AsSHee70w5iDBiK4aph27yH3TxkXy9V89TDdexAcKk/cVHYNnDBapcavl7y0RiQ4biu8ymM8Ga/nmzhRKya6G0cGw8CAQOjgfwwgfkwHQYDVR0OBBYEFI0cxb6VTEM8YYY6FbBMvAPyT+CyMIHJBgNVHSMEgcEwgb6AFI0cxb6VTEM8YYY6FbBMvAPyT+CyoYGapIGXMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbYIJANWFuGx90071MAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEEBQADggEBABnTDPEF+3iSP0wNfdIjIz1AlnrPzgAIHVvXxunW7SBrDhEglQZBbKJEk5kT0mtKoOD1JMrSu1xuTKEBahWRbqHsXclaXjoBADb0kkjVEJu/Lh5hgYZnOjvlba8Ld7HCKePCVePoTJBdI4fvugnL8TsgK05aIskyY0hKI9L8KfqfGTl1lzOv2KoWD0KWwtAWPoGChZxmQ+nBli+gwYMzM1vAkP+aayLe0a1EQimlOalO762r0GXO0ks+UeXde2Z4e+8S/pf7pITEI/tP+MxJTALw9QUWEv9lKTk+jkbqxbsh8nfBUapfKqYn0eem1E9VDhAIMtTnJniJSE40yHPUU4w=
         </item>
     </string-array>
     <string-array name="com_google_android_gms_fonts_certs_prod">
         <item>
             MIIEQzCCAyugAwIBAgIJAMLgh0ZkSjCNMA0GCSqGSIb3DQEBBAUAMHQxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtHb29nbGUgSW5jLjEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDAeFw0wODA4MjEyMzEzMzRaFw0zNjAxMDcyMzEzMzRaMHQxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtHb29nbGUgSW5jLjEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDCCASAwDQYJKoZIhvcNAQEBBQADggENADCCAQgCggEBAKtWLgDYO6IIrgqWbxJOKdoR8qtW0I9Y4sypEwPpt1TTcvZApxsdyxMJZ2JORland2qSGT2y5b+3JKkedxiLDmpHpDsz2WCbdxgxRczfey5YZnTJ4VZbH0xqWVW/8lGmPav5xVwnIiJS6HXk+BVKZF+JcWjAsb/GEuq/eFdpuzSqeYTcfi6idkyugwfYwXFU1+5fZKUaRKYCwkkFQVfcAs1fXA5V+++FGfvjJ/CxURaSxaBvGdGDhfXE28LWuT9ozCl5xw4Yq5OGazvV24mZVSoOO0yZ31j7kYvtwYK6NeADwbSxDdJEqO4k//0zOHKrUiGYXtqw/A0LFFtqoZKFjnkCAQOjgdkwgdYwHQYDVR0OBBYEFMd9jMIhF1Ylmn/Tgt9r45jk14alMIGmBgNVHSMEgZ4wgZuAFMd9jMIhF1Ylmn/Tgt9r45jk14aloXikdjB0MQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEUMBIGA1UEChMLR29vZ2xlIEluYy4xEDAOBgNVBAsTB0FuZHJvaWQxEDAOBgNVBAMTB0FuZHJvaWSCCQDC4IdGZEowjTAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBAUAA4IBAQBt0lLO74UwLDYKqs6Tm8/yzKkEu116FmH4rkaymUIE0P9KaMftGlMexFlaYjzmB2OxZyl6euNXEsQH8gjwyxCUKRJNexBiGcCEyj6z+a1fuHHvkiaai+KL8W1EyNmgjmyy8AW7P+LLlkR+ho5zEHatRbM/YAnqGcFh5iZBqpknHf1SKMXFh4dd239FJ1khLT7h1DA3b952i6vr1jOY01dJ2MKh3fIwitiT6kWKbCQL0eUQaBvJRc4YqJy6OAcgNNYFzkRqO41vJ8P59SHohWWjhGpfpYXgtyNw18N1knOKPKOcwWcGX/5e+JWpRltsU3OwLWBvEP1w8y9w0cxJR+YQ=
         </item>
     </string-array>
 </resources>
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 EXAMPLE CODE (COMMENTED OUT - UNCOMMENT AFTER ADDING DEPENDENCY)
 ═══════════════════════════════════════════════════════════════════════════════

 // STEP 1: Import the necessary classes
 // import androidx.compose.ui.text.ExperimentalTextApi
 // import androidx.compose.ui.text.googlefonts.GoogleFont
 // import androidx.compose.ui.text.googlefonts.Font
 // import androidx.compose.ui.text.font.Font as LocalFont
 // import com.example.FullComposeOfficialDocumentation.R

 // STEP 2: Configure Google Font Provider
 // @OptIn(ExperimentalTextApi::class)
 // val provider = GoogleFont.Provider(
 //     providerAuthority = "com.google.android.gms.fonts",
 //     providerPackage = "com.google.android.gms",
 //     certificates = R.array.com_google_android_gms_fonts_certs
 // )

 // STEP 3: Define Google Fonts
 // @OptIn(ExperimentalTextApi::class)
 // val lobsterFontName = GoogleFont("Lobster Two")

 // @OptIn(ExperimentalTextApi::class)
 // val lobsterFontFamily = FontFamily(
 //     Font(googleFont = lobsterFontName, fontProvider = provider)
 // )

 // STEP 4: Font with specific weight and style
 // @OptIn(ExperimentalTextApi::class)
 // val lobsterBoldItalicFamily = FontFamily(
 //     Font(
 //         googleFont = lobsterFontName,
 //         fontProvider = provider,
 //         weight = FontWeight.Bold,
 //         style = FontStyle.Italic
 //     )
 // )

 // STEP 5: Multiple weights for complete font family
 // @OptIn(ExperimentalTextApi::class)
 // val robotoFontName = GoogleFont("Roboto")

 // @OptIn(ExperimentalTextApi::class)
 // val robotoFontFamily = FontFamily(
 //     Font(googleFont = robotoFontName, fontProvider = provider, weight = FontWeight.Light),
 //     Font(googleFont = robotoFontName, fontProvider = provider, weight = FontWeight.Normal),
 //     Font(googleFont = robotoFontName, fontProvider = provider, weight = FontWeight.Medium),
 //     Font(googleFont = robotoFontName, fontProvider = provider, weight = FontWeight.Bold)
 // )

 // STEP 6: Use in composables
 // @OptIn(ExperimentalTextApi::class)
 // @Composable
 // fun DownloadableFontExample() {
 //     Column(modifier = Modifier.padding(16.dp)) {
 //         Text(
 //             text = "Hello World with Lobster Two!",
 //             fontFamily = lobsterFontFamily,
 //             fontSize = 24.sp
 //         )
 //
 //         Text(
 //             text = "Bold Italic Lobster",
 //             fontFamily = lobsterBoldItalicFamily,
 //             fontSize = 20.sp
 //         )
 //     }
 // }

 // STEP 7: Fallback fonts (hybrid approach - downloads online, falls back to local)
 // @OptIn(ExperimentalTextApi::class)
 // val hybridFontFamily = FontFamily(
 //     // Try downloading first
 //     Font(googleFont = lobsterFontName, fontProvider = provider),
 //     // Fallback to local font if download fails
 //     LocalFont(resId = R.font.firasans_regular),
 //
 //     // Same for bold weight
 //     Font(googleFont = lobsterFontName, fontProvider = provider, weight = FontWeight.Bold),
 //     LocalFont(resId = R.font.firasans_bold, weight = FontWeight.Bold)
 // )

 // STEP 8: Debugging with error handler
 // @OptIn(ExperimentalTextApi::class)
 // @Composable
 // fun DownloadableFontWithDebugging() {
 //     val handler = CoroutineExceptionHandler { _, throwable ->
 //         Log.e("FontDownload", "Font loading failed: ", throwable)
 //     }
 //
 //     val context = LocalContext.current
 //
 //     LaunchedEffect(Unit) {
 //         if (provider.isAvailableOnDevice(context)) {
 //             Log.d("FontDownload", "Provider configured correctly!")
 //         } else {
 //             Log.e("FontDownload", "Provider not available or misconfigured")
 //         }
 //     }
 //
 //     CompositionLocalProvider(
 //         LocalFontFamilyResolver provides createFontFamilyResolver(context, handler)
 //     ) {
 //         Column(modifier = Modifier.padding(16.dp)) {
 //             Text(
 //                 text = "Debugging downloadable fonts",
 //                 style = MaterialTheme.typography.bodyMedium,
 //                 fontFamily = lobsterFontFamily
 //             )
 //         }
 //     }
 // }
*/

/*
 ═══════════════════════════════════════════════════════════════════════════════
 TEMPORARY PLACEHOLDER (Until Google Fonts dependency is added)
 ═══════════════════════════════════════════════════════════════════════════════
*/

// Placeholder font family using system fonts
val robotoFontFamily = FontFamily.SansSerif

@Composable
fun DownloadableFontExample() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "⚠️ Google Fonts not configured",
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )
        Text(
            text = "Add dependency: androidx.compose.ui:ui-text-google-fonts:1.7.6",
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "See comments in this file for setup instructions",
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp
        )
    }
}

/*
 ═══════════════════════════════════════════════════════════════════════════════
 SETUP INSTRUCTIONS
 ═══════════════════════════════════════════════════════════════════════════════

 1. Add dependency to build.gradle.kts (Module: app):
    implementation("androidx.compose.ui:ui-text-google-fonts:1.7.6")

 2. Create res/values/font_certs.xml with the certificate XML above

 3. Sync Gradle

 4. Uncomment the example code in this file

 5. Replace robotoFontFamily placeholder with actual Google Font implementation

 Common Issues:
 - Font fails to load: New fonts take months to reach Android from fonts.google.com
 - Provider not available: Check certificates in font_certs.xml
 - Import conflicts: Use aliases like 'import androidx.compose.ui.text.googlefonts.Font as GoogleFont'
*/
