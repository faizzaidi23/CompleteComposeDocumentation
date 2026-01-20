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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


// Define the routes as constants. This is the best practice to avoid typos

object AppRoutes {
    const val HOME = "home"
    const val LOGIN = "login"
    const val GUEST_WELCOME = "guest_welcome"
    const val GUEST_DETAILS = "guest_details"
    const val CONFIRMATION = "confirmation"
}


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppRoutes.HOME
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // Home Screen
        composable(AppRoutes.HOME) {
            HomeScreen(
                onNavigateToLogin = {
                    // When going to Login, restore its state if it was saved before.
                    navController.navigate(AppRoutes.LOGIN) {
                        restoreState = true
                    }
                }
            )
        }

        // Login Screen
        composable(AppRoutes.LOGIN) {
            LoginScreen(
                onNavigateToGuestWelcome = { navController.navigate(AppRoutes.GUEST_WELCOME) },
                onNavigateToConfirmation = { navController.navigate(AppRoutes.CONFIRMATION) }
            )
        }

        // Guest Welcome Screen ---
        composable(AppRoutes.GUEST_WELCOME) {
            GuestWelcomeScreen(
                onNavigateToDetails = { navController.navigate(AppRoutes.GUEST_DETAILS) },
                onNavigateBack = { navController.popBackStack() } // Standard "back"
            )
        }

        // --- Guest Details Screen (Demonstrates popBackStack with parameters) ---
        composable(AppRoutes.GUEST_DETAILS) {
            GuestDetailsScreen(
                onGoBackToLoginAndSaveState = {
                    // This is the powerful popBackStack call.
                    // It goes back TO the LOGIN route,
                    // does NOT include it in the pop (so we land on it),
                    // and SAVES the state of the screens it pops (Guest Welcome & Guest Details).
                    navController.popBackStack(
                        route = AppRoutes.LOGIN,
                        inclusive = false,
                        saveState = true
                    )
                }
            )
        }

        // --- Confirmation Screen ---
        composable(AppRoutes.CONFIRMATION) {
            ConfirmationScreen(
                onFinish = {
                    // This popBackStack goes back to HOME and pops it too,
                    // effectively clearing the whole stack.
                    navController.popBackStack(AppRoutes.HOME, inclusive = true)
                    // Then we navigate to a fresh Home screen.
                    navController.navigate(AppRoutes.HOME)
                }
            )
        }
    }
}


// --- 3. Create your screens as Composables ---

@Composable
fun HomeScreen(onNavigateToLogin: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Home Screen!")
        Button(onClick = onNavigateToLogin) {
            Text("Start Purchase (Login)")
        }
    }
}

@Composable
fun LoginScreen(
    onNavigateToGuestWelcome: () -> Unit,
    onNavigateToConfirmation: () -> Unit
) {
    // Using rememberSaveable so its state can be saved by the NavController
    var name by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Screen")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name (State is saved)") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToGuestWelcome) {
            Text("Continue as Guest")
        }
        Button(onClick = onNavigateToConfirmation) {
            Text("Go to Confirmation")
        }
    }
}

@Composable
fun GuestWelcomeScreen(onNavigateToDetails: () -> Unit, onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome, Guest!")
        Button(onClick = onNavigateToDetails) {
            Text("Enter Details")
        }
        Button(onClick = onNavigateBack) {
            Text("Go Back (Simple Pop)")
        }
    }
}

@Composable
fun GuestDetailsScreen(onGoBackToLoginAndSaveState: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the Guest Details Screen.")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "This button will go back to Login and save state.")
        Button(onClick = onGoBackToLoginAndSaveState) {
            Text("Go Back to Login")
        }
    }
}

@Composable
fun ConfirmationScreen(onFinish: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Order Confirmed!")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "This button will clear the checkout history.")
        Button(onClick = onFinish) {
            Text("Finish")
        }
    }
}
