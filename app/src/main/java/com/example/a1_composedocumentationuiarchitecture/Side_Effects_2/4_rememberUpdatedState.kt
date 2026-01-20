package com.example.a1_composedocumentationuiarchitecture.Side_Effects_2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.delay

/*
In simple terms, rememberUpdatedState lets long running background task use the newest version
of a value of function without restarting the task itself
*/

/*
Think of LaunchedEffect as a worker you've given a long, important job,
like "wait for 5 seconds." rememberUpdatedState is like giving that worker
a special piece of paper. You can change what's written on the paper
at any time, and the worker will always read the latest version
without having to stop and restart their 5-second countdown.
*/


/*
The problem-->
A long running task with changing instructions
Imagine we have a splash screen  that waits for 3 seconds and then navigates away
*/

@Composable
fun LandingScreen(onTimeOut:()->Unit){
    // we want this to run once and then wait for 3 seconds

    LaunchedEffect(true){
        delay(3000)
        onTimeOut()  // This is the bug
    }
}

/*
The Bug: What if the parent composable redraws LandingScreen and passes
in a new version of the onTimeout function while the delay(3000)
is already running? The LaunchedEffect
is still running its original job and is holding onto the old,
original onTimeout function. When the 3 seconds are up,
it will call the outdated function, which can lead to crashes or
weird behavior.

You can't fix this by putting onTimeout in the key like
LaunchedEffect(onTimeout). If you did that, the timer would restart
every single time a new onTimeout function was passed in,
which we don't want.
*/


/*
Solution is-->
rememberUpdatedState

it always have the newest value
*/

@Composable
fun LandingPageCorrect(onTimeOut:()->Unit){
    val currentOnTimeout by rememberUpdatedState(onTimeOut)

    LaunchedEffect(true){
        delay(3000)
        currentOnTimeout()
    }
}

/*
Here's what happens:

We create currentOnTimeout. This isn't the onTimeout function itself, but a smart pointer that will always know where the latest version of onTimeout is.

LaunchedEffect(true) starts its 3-second timer. It doesn't watch onTimeout, so it will never restart.

During the 3-second wait, the parent UI might recompose and pass
a new onTimeout function to LandingScreen.
The currentOnTimeout pointer automatically
updates to point to this new function.

When the delay finishes, it calls currentOnTimeout().
Because it's using the smart pointer, it executes the newest,
most up-to-date version of the function, preventing the bug.
*/


/*
where can we use rememberUpdatedState concept


1. Timers and Delays
This is the classic use case, like the splash screen example.

Scenario: You have a "session timeout" feature. After 5 minutes of inactivity, you show a "Are you still there?" dialog. The function to show the dialog (onTimeout) might be passed from a parent that recomposes.

Why you need it: You start a delay(5_MINUTES) in a LaunchedEffect. You don't want this 5-minute timer to restart every time the parent recomposes. rememberUpdatedState ensures that when the timer finally finishes, it calls the newest version of the onTimeout function.

2. Continuous Animations or Effects
For animations or effects that run in an infinite loop.

Scenario: You have an animated background that continuously pulses or shifts colors. The speed or color of the animation might be configurable by the user and passed in as a parameter (animationSpeed).

Why you need it: The animation runs in a while(isActive) loop inside a LaunchedEffect. You don't want the entire animation to restart from the beginning just because the user tweaked the speed. You would wrap animationSpeed in rememberUpdatedState so the loop can smoothly start using the new speed value on its next iteration.

3. Handling Complex User Gestures
For long-running gestures like dragging.

Scenario: A user is dragging an item across the screen. While they are dragging, another piece of state changes—for example, the "snap-to-grid" setting is toggled on or off by another UI element.

Why you need it: The drag gesture is handled within a single, long-running coroutine. You don't want the entire drag to cancel and restart just because the snapToGrid boolean changed. You would use rememberUpdatedState on the snapToGrid value so the drag logic can instantly start using the new setting mid-drag.

4. Subscribing to Data Streams
When listening to a real-time data source like a WebSocket or a Firestore listener.

Scenario: You are subscribed to a live chat feed. The callback function that handles incoming messages (onNewMessage) might depend on other UI state (like whether a "profanity filter" is on).

Why you need it: The connection to the data stream is a long-lived process inside a LaunchedEffect. You don't want to disconnect and reconnect to the server every time the user toggles the profanity filter. By wrapping the onNewMessage callback in rememberUpdatedState, the listener can immediately use the new message-handling logic without interrupting the connection.
*/