package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Navigation_10

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController




/*
Pressing the back button would just take you to the previous tab you were on, which feels broken. You want the back button to exit the app from any of the main tabs.

popUpTo solves this by clearing the back stack before navigating.



The flow is now a simple Home -> Settings navigation.

When navigating from Home to Settings, it uses popUpTo to ensure the back stack doesn't grow.

It uses saveState and restoreState so that any text you type in the Home screen's text field will still be there when you navigate to Settings and come back.
*/

object AppRoute{
    const val HOME="home"
    const val SETTING="settings"
}

@Composable
fun ApppNavHost(
    modifier:Modifier=Modifier,
    navController: NavHostController= rememberNavController(),
    startDestination:String= AppRoute.HOME
){
    NavHost(
        modifier=modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(AppRoute.HOME){
            HomeScreens(
                onNavigateToSettings = {navController.navigate(AppRoute.SETTING){
                    popUpTo(AppRoute.HOME){
                     /*This tells the NavController, "Before you go to the new screen, clear the back stack until you see the HOME screen." This prevents the back stack from infinitely growing as the user taps between tabs. For example, it turns a messy stack like [Home, Settings, Home, Profile] into just [Home].
*/

                        saveState=true/*saveState = true: This is a crucial addition. It says, "As you are clearing the back stack, make sure to save the UI state (like text in a field or scroll position) of the HOME screen you are popping up to."*/
                    }
                    launchSingleTop=true /*This line prevents creating multiple copies of the same screen on top of each other
                    like tapping the home screen icon on staying on the home screen already if we do not use this it will make another home screen on top of the home screen which is already there in the stack of the screens
                    */
                    restoreState=true
                } }
            )
        }
        composable(AppRoute.SETTING){
            SettingsScreen(onNavigateHome = {navController.navigate(AppRoute.HOME){
                popUpTo(AppRoute.SETTING){
                    saveState=true
                }
                launchSingleTop=true
                restoreState=true
            } },
                onSimpleBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}

@Composable
fun HomeScreens(onNavigateToSettings:()->Unit){
    var name by rememberSaveable{mutableStateOf("")}

    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text="Home Screen")
        Spacer(modifier=Modifier.height(10.dp))
        OutlinedTextField(
            value=name,
            onValueChange = {name=it},
            label={Text("Enter the name")}
        )
        Spacer(modifier=Modifier.height(10.dp))
        Button(
            onClick = onNavigateToSettings
        ){
            Text(text="Go to the Settings")
        }

    }

}
@Composable
fun SettingsScreen(onNavigateHome: () -> Unit, onSimpleBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Settings Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Go back and your text on the Home screen will be restored.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateHome) {
            Text("Go to Home (using popUpTo)")
        }
        Button(onClick = onSimpleBack) {
            Text("Go Back (using popBackStack)")
        }
    }
}

/*
popBackStack()
Purpose: To navigate backward by removing screens from the top of the back stack.

What it is: A direct function call. You tell the NavController, "Go back."

When it's used: For "Back" buttons, "Cancel" actions, or any time you want to dismiss the current screen and return to the previous one.

Analogy: It's literally the Back button on your phone. You press it, and you go back one step.

Kotlin

// This is a direct command to go back immediately.
navController.popBackStack()
## popUpTo()
Purpose: To clean up the back stack as part of a forward navigation.

What it is: An option or a configuration you provide inside a navigate() call. You're saying, "Before you go to this new screen, clear out some of the old screens first."

When it's used: To prevent an infinite back stack, especially in tab-based navigation (like a bottom navigation bar). It ensures that repeatedly tapping between tabs doesn't just pile them on top of each other.

Analogy: It's like clearing your desk before starting a new task. You're not going back; you're tidying up your history before moving forward.
*/


/*
Feature	popBackStack	popUpTo
Trigger	A direct, immediate command.	An option inside a navigate command.
Direction	Destroys screens backward from the current one.	Destroys screens forward from a target in the stack.
Purpose	To go back in history.	To clean up history before adding to it.
Analogy	Undo	Delete and Replace

*/