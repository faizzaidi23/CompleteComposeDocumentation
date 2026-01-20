package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Side_Effects_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/*
The Key Difference: When the Work Starts
LaunchedEffect: The work starts automatically
when the UI appears or a state value changes.
It's tied to the UI's state.

rememberCoroutineScope: The work starts only when you explicitly tell it to,
usually inside an event handler like onClick. It's tied to user actions.
*/

@Composable
fun MovieScreen(snackbarHostState: SnackbarHostState){
    val scope= rememberCoroutineScope()
    Scaffold(){
        contentPadding->
        Column(modifier=Modifier.padding(contentPadding)){

            Button(
                onClick ={
                    scope.launch{
                        snackbarHostState.showSnackbar("something happened")
                    }
                }
            ){
                Text("Press me")
            }

        }
    }

}


/*
Why Not Use LaunchedEffect Here?
If you used LaunchedEffect, the snack bar would appear as soon as the screen
 loaded, not when the user clicked the button.
 rememberCoroutineScope gives you the control to trigger the
 background task precisely when you need it based on a user interaction.
*/

/*
The reason is that showing a snackbar is not an instant action.
 It involves animations and delays, which are suspend functions.
 You cannot call a suspend function from a regular function,
 and the onClick lambda is a regular function.
*/

/*
LAUNCH---->


launch is a coroutine builder.
Its job is to "launch" a new, independent background task (a coroutine)
without blocking the current thread.
You give it a block of code, and it runs that code concurrently
*/

/*
use of remember here is that

we need to remember the state of the composition here and  of the CoroutineScope
*/



/*
Where do we need to use this rememberCoroutineScope in real world android development





You use rememberCoroutineScope any time you need to launch a background task from a callback or event handler that is not itself a composable suspend function. It's the bridge to the "pausable world" of coroutines from the "normal world" of user events.

Here is a comprehensive list of all the cases where this is necessary.

1---->    User-Initiated Actions


This is the most common category. The task should only run when the user physically interacts with the UI.

Button Clicks: For any onClick, onLongClick, or onDoubleClick event that needs to perform a suspend action like a network request or database write.

Toggling Controls: When flipping a Switch or checking a Checkbox (onCheckedChange) needs to trigger a background save operation.

Gestures: When a custom gesture like a swipe or drag (detectTapGestures, draggable) should trigger a data fetch or a complex animation.

Keyboard Actions: When the user presses a keyboard action button (like "Done" or "Search") in a TextField's KeyboardActions.

Dropdown Menu Selections: When selecting an item from a DropdownMenu needs to fetch new data based on the selection.

Programmatic UI Control
This is for when you need to manually control the state of a UI element from an event handler.

Scrolling Lists: Manually scrolling a LazyColumn or LazyRow to a specific item or position (using LazyListState.animateScrollToItem()). For example, a "Scroll to Top" button.

Controlling Sheets and Drawers: Programmatically opening or closing a BottomSheetScaffold or a ModalNavigationDrawer in response to a button click (using BottomSheetScaffoldState.expand() or DrawerState.open()).

Managing Focus: Manually moving the focus to a specific TextField using a FocusRequester when a button is clicked.

Controlling Pagers: Swiping a HorizontalPager to a specific page programmatically using PagerState.animateScrollToPage().

Manual Lifecycle Control
This is for when you need more direct control over a long-running process than LaunchedEffect provides.

Starting/Stopping Timers: Creating a stopwatch where "Start" and "Stop" buttons in the UI launch and cancel the counting coroutine.

Controlling Media: Building custom media player controls where onClick events on "Play," "Pause," and "Stop" buttons control a media playback coroutine.

Manually Triggering Animations: Starting a complex, multi-stage animation when a user clicks a button, rather than when a state value changes.

Interacting with External Systems
This is for bridging the gap between Compose and other parts of your app or external libraries.

Handling Callbacks: When you receive a callback from a non-Compose system (like a sensor manager, a Bluetooth library, or a broadcast receiver) and need to launch a coroutine to process the result within your composable's lifecycle.

Controlling Legacy Views: If you have an old Android View embedded in your Compose UI, you might use a scope to call methods on it from a button in your Compose code.
*/



