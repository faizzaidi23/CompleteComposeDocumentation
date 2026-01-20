package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.ManagingStates_4

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.android.parcel.Parcelize

/*
Why do we need this Parcelize annotation




The core problem is this: rememberSaveable knows how to save simple things like text (String) and numbers (Int) automatically. But it has no idea how to save a custom object you created, like a City class.

@Parcelize is a special instruction you give to your custom class that essentially says: "Hey, I'm going to teach you how to pack yourself into a suitcase so Android can save you."
*/


@Parcelize
data class City(val name:String,val country:String) : Parcelable

@Composable
fun CityScreen(){
    var selectedCity by rememberSaveable{mutableStateOf(City("Madrid","Spain"))}
}



/*
Where do we have to use this parcelize concept



## 1. Passing Data Between Activities or Fragments
This is the most common use case.

Scenario: You have a list of products in ProductListActivity. When the user taps a product, you want to open ProductDetailActivity and show the details for that specific product.

Why you need @Parcelize: You need to send the entire Product object (with its name, price, description, etc.) from the first screen to the second. The standard way to do this is by putting it in the Intent's "extras". The Intent extras can only hold basic types or objects that are Parcelable.

Without it: You would have to send each piece of data separately (intent.putExtra("PRODUCT_NAME", product.name), intent.putExtra("PRODUCT_PRICE", product.price)), which is messy and easy to break.

With it: You just put the whole object in one go: intent.putExtra("PRODUCT_OBJECT", product).

## 2. Saving UI State with rememberSaveable in Compose
This is the use case we've been discussing.

Scenario: A user is filling out a multi-step registration form. The current state of the form is held in a custom RegistrationData object. The user rotates their phone.

Why you need @Parcelize: rememberSaveable needs to save the RegistrationData object to survive the screen rotation. It uses Android's standard "saved instance state" mechanism, which, again, requires the object to be Parcelable.

## 3. Caching Data in a ViewModel to Survive Process Death
This is a more advanced but critical use case for robust apps.

Scenario: Your app is in the background. The Android system is low on memory and decides to kill your app's process completely to free up resources. When the user re-opens your app, they expect to be right where they left off.

Why you need @Parcelize: The ViewModel's SavedStateHandle is a special tool designed to survive this exact scenario (process death). To save your entire UserProfile or ScreenData object in the SavedStateHandle, it must be Parcelable. This ensures your app can restore its state even after being completely shut down by the OS.

## 4. Passing Data to Background Services or Broadcast Receivers
Scenario: Your app downloads a file and needs to tell a background Service to process it. You want to send a DownloadedFile object containing the file's name, path, and size to the service.

Why you need @Parcelize: Communication with background components like Services and BroadcastReceivers also happens via Intents. To pass your custom DownloadedFile object within that Intent, it must be Parcelable.

In short, any time your custom object needs to cross a boundary—from one screen to another, from your app to the Android system for saving, or from your app to a background component—you need to make it Parcelable so it can be safely transported.
*/