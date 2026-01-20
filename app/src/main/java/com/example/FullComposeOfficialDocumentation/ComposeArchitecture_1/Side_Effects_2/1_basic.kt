package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Side_Effects_2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/*
Think of a composable function as a pure function in math:

Pure function → given the same input, it always gives the same output and doesn’t change anything outside itself.

Side-effect → when a function does something outside its own scope, like:

Updating a database

Showing a snackbar

Navigating to another screen

Logging to console

Reading a file

 In Jetpack Compose, UI functions should ideally be pure — they should only describe what the UI looks like for a given state.
But sometimes we need side effects.




3. Why this matters for side-effects
Because recomposition can happen:

Without you explicitly causing it.

Multiple times in quick succession.

Or be aborted before the UI is updated.

If your side-effect is inside the composable body without special handling,
it might run too often or at the wrong time.


that means recompostion sometimes may happen by itself although we have not triggered that by ourselves

now for this
*/


@Composable
fun MyScreen(){
    println("Composing MyScreen") // log to console
    Text("Welcome")
}

/*
Run this and:

Rotate the device

Switch dark mode

Trigger a parent state change

Let Compose run its internal optimizations

You’ll see "Composing MyScreen" printed multiple times, even though "Welcome" looks unchanged.
*/

@Composable
fun MyScreen2(){
    // ShowToast("Hello world")
    Text("Welcome ")
}

/*
If Compose decides to recompose this screen
5 times in a second (for any reason),
your toast shows 5 times.
*/

/*
3. Why special handling solves this
Special handling = Effect APIs like LaunchedEffect, SideEffect, DisposableEffect.

They let you say:

“Run this side effect only when X changes”
or
“Run this once when the composable enters the UI, then clean up when it leaves.”
*/
