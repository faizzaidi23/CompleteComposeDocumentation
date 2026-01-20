package com.example.a1_composedocumentationuiarchitecture.ManagingStates_4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable

data class Cityy(val name:String,val country:String)

val citySaver=run{
    val nameKey="Name"
    val countryKey="Country"

    mapSaver(
        save={mapOf(nameKey to it.name,countryKey to it.country)},
        restore = {Cityy(it[nameKey] as String, it[countryKey] as String)}
    )
}

@Composable
fun CityScreens(){
    val selectedCity by rememberSaveable(stateSaver = citySaver){ /*stateSaver is an optional parameter of the rememberSaveable function.

Think of a function's parameters as its settings or options. Most of the time, rememberSaveable uses its default, automatic settings to save state.

However, the stateSaver parameter lets you override that default behavior and provide your own custom instructions.*/
        mutableStateOf(Cityy("Madrid","Spain"))
    }
}



/*
You want to use rememberSaveable on a custom class, but you cannot modify the source code of that class to add @Parcelize.

This happens most often when the class comes from an external library or a legacy part of your project that you can't change.

## How It Works in Code
rememberSaveable knows how to save a Map<String, Any> automatically. mapSaver is a tool that lets you manually convert your custom object into that savable Map and then convert it back.

It requires you to provide two pieces of logic:

save = { ... }: A function that takes your object (e.g., City) and deconstructs it into a Map.

Input: City(name = "Madrid", country = "Spain")

Output: mapOf("Name" to "Madrid", "Country" to "Spain")

restore = { ... }: A function that takes the saved Map and reconstructs your original object.

Input: mapOf("Name" to "Madrid", "Country" to "Spain")

Output: City(name = "Madrid", country = "Spain")

You are essentially writing the save/load instructions yourself because the class doesn't have them built-in with @Parcelize.

## Code Explanation Line-by-Line
Here is the code again, with a focus on the coding mechanics.

Kotlin

// A plain class. You can't add @Parcelize to it.
data class City(val name: String, val country: String)

// This is a variable that holds your custom saving logic.
val CitySaver = run {
    // Keys for the map.
    val nameKey = "Name"
    val countryKey = "Country"

    // The function that creates your saver.
    mapSaver(
        // The 'save' lambda:
        // It receives a 'City' object (named 'it' by default).
        // It returns a Map where the City's properties are the values.
        save = { mapOf(nameKey to it.name, countryKey to it.country) },

        // The 'restore' lambda:
        // It receives the saved Map (named 'it' by default).
        // It returns a new City object by reading the values from the map.
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
}

@Composable
fun CityScreen() {
    // Here, you tell rememberSaveable to use your custom logic.
    // Instead of trying to save the City object directly, it will:
    // 1. Call your 'CitySaver.save' lambda to get a Map.
    // 2. Save that Map.
    // 3. On restore, it will call your 'CitySaver.restore' lambda with the saved Map.
    var selectedCity by rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(City("Madrid", "Spain"))
    }
}
*/