package com.example.a1_composedocumentationuiarchitecture.Side_Effects_2

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive



/*LAUNCHED EFFECT*/

/*
In simple terms, LaunchedEffect is a way to run a task in the background when your UI first appears on screen.

Think of it like hiring a worker for a specific job 🧑‍🔧.

When your UI shows up, you hire the worker (LaunchedEffect starts) and give them a to-do list (the code inside).

The worker does the job in the background, so your UI stays smooth and responsive. This job could be fetching data, starting an animation, or showing a notification.

When your UI goes away, the worker is automatically fired (LaunchedEffect is canceled), so they don't keep working and wasting battery.

It also has a special feature called a "key".
If the key's value changes, LaunchedEffect will fire the old worker and
hire a new one with the updated instructions.
If the key doesn't change, the original worker just keeps doing their job,
even if the UI redraws (recomposes).
*/

/*
How the while Loop Runs
The while loop doesn't need pulseRateMs to change for it to run. It's an infinite loop because while(isActive) is always true.

Here is the sequence of events:

LaunchedEffect starts.

The while loop begins.

It pauses for 3 seconds.

It animates the alpha value to 0f.

It animates the alpha value to 1f.

The loop repeats from step 3.

This creates a continuous "pulsing" animation that
fades out and fades in every 3 seconds.
The value of pulseRateMs is only read by the delay function;
it is not changed by the loop itself.
*/

@Composable
fun ExampleCode(){
    val pulseRateMs by remember{mutableStateOf(3000L)}
    val alpha=remember{Animatable(1f)}

    LaunchedEffect(pulseRateMs){
        while(isActive){
            delay(pulseRateMs)
            alpha.animateTo(0f)
            alpha.animateTo(1f)
        }
    }
}

/*
Here isActive is the key it will be active as long as the Example code composable is on the sceeen
i.e if it is not on the screen this launchedEffect will be stopped
just like we navigate to the other screen
*/


/*
Where and Why We Use LaunchedEffect in Real Apps
You use LaunchedEffect for tasks that should happen because a
 piece of UI is on the screen. It's for running suspend functions
 (like delay, network calls, or database access) in a way that is safe and
 tied to the UI lifecycle.
*/

/*

1. Showing a Snack bar or Toast
When an operation finishes, you might want to show a message. You only want to show it once when the state changes.

Scenario: After a user successfully updates their profile, a ViewModel sets a state showSuccessMessage = true.

Kotlin

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    // This will run ONLY when uiState.showSuccessMessage changes to true
    LaunchedEffect(uiState.showSuccessMessage) {  It means: "Run this code again whenever the value of showSuccessMessage changes."
        if (uiState.showSuccessMessage) { // This if block will run only when  it will be true
            scaffoldState.snack barHostState.showSnack bar("Profile Updated!")
            viewModel.onSuccessMessageShown() // Reset the state
        }
    }
    // ...
}

*/



/*
2-->Fetching data for a specific screen

when an user navigates to a detail screen you need to load

 the data for the specific item


 like a user taps on a product with productId=123 to see its details



 @Composable
fun ProductDetailScreen(productId: String, viewModel: ProductViewModel) {

    // This runs when the screen first appears.
    // If productId changes, it CANCELS the old fetch and starts a NEW one.
    LaunchedEffect(productId) {
        viewModel.fetchProductDetails(productId)
    }

    val product = viewModel.productState
    // ... display product details or a loading spinner
}



Why LaunchedEffect? This is its killer feature. If the user is on the screen for product 123 and
then gets a notification that takes them to product 456 without
leaving the screen, LaunchedEffect sees the key productId has changed.
It automatically cancels the network request for 123 and launches a new one for 456.
This prevents bugs and wasted network calls.

*/


/*
3. Requesting Permissions
You want to ask for a permission as soon as the user enters a feature that needs it.

Scenario: A user opens a camera feature within your app.

Kotlin

@Composable
fun CameraFeature() {
    val permissionLauncher = rememberLauncherForActivityResult(...)

    // Runs once when CameraFeature first appears on screen
    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }
    // ...
}
*/


/*
In summary, the core principle is always the same:
if you need to run a suspend function or a long-running task
in a way that respects the UI's lifecycle (starting, stopping,
and restarting when its inputs change), LaunchedEffect is your go-to tool.
*/

