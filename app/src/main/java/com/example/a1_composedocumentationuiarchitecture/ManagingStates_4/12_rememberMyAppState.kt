package com.example.a1_composedocumentationuiarchitecture.ManagingStates_4

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

/*
This is a helper function that creates and remembers your state holder.
It is aa common convention to name it remember + the name of your state class
*/

@Composable
private fun rememberMyAppState(windowSizeClass:WindowSizeClass):MyAppState{
    return remember(windowSizeClass){
        MyAppState(windowSizeClass)
    }
}

/*
Stable is an annotation that tells compose compiler that this class is,
well stable. It is an optimization hint that helps compose know when it can safely skip recomposition
*/
@Stable
// This is your plain state holder class. It is not a viewModel
// its job is to hold UI state and UI logic
class MyAppState(
    // It holds a reference to the windowSizeClass it was created with.
    // Inside this class, you would have other 'mutableStateOf' variables
    // and functions that depend on the window size

    private val windowSizeClass:WindowSizeClass){
    /*
    For example you might have state like this inside:
    val shouldShowNavigationRail:Boolean=windowSizeClass.widthSizeClass>WindowWidthSizeClass.Compact
    var selectedItem by mutableStateOf(...)
    */
}



/*
In short, MyAppState is a custom helper class you create to organize all the UI-related state and logic for a specific screen.

Instead of having many remember variables scattered inside your main Composable, you group them all into this one class.

## When to Use It
You should use this concept when your Composable screen becomes complex.

Use it when you find yourself writing many remember or rememberSaveable lines at the top of your Composable function. If your screen has multiple states that are related to each other (like a search query, a list of filters, and a loading spinner), a state holder class is a great way to clean it up.

## How to Use It
Create a plain class to hold your UI state.

Create a remember... function that creates and remembers an instance of your class.

Use that instance in your main Composable.

Simple Example: A Search Screen
1. Create the State Holder Class (SearchScreenState)

This class holds the search text and the loading state.

Kotlin

class SearchScreenState {
    // Group related UI state here
    var searchQuery by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    // Group related UI logic here
    fun onQueryChange(newQuery: String) {
        searchQuery = newQuery
        // You could add search logic here
    }
}
2. Create the remember Function

This function creates and remembers the state holder.

Kotlin

@Composable
fun rememberSearchScreenState(): SearchScreenState {
    return remember {
        SearchScreenState()
    }
}
3. Use it in Your Screen

Your main Composable is now much cleaner. It just gets the state holder and uses it.

Kotlin

@Composable
fun SearchScreen() {
    // Get the instance of your state holder
    val state = rememberSearchScreenState()

    Column {
        TextField(
            value = state.searchQuery,
            onValueChange = { state.onQueryChange(it) }
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}
*/