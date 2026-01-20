package com.example.a1_composedocumentationuiarchitecture.Navigation_10

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

object NewAppRoutes{
    // Routes for the main graph
    const val HOME="home"
    const val PROFILE="profile"

    //route for the nested authentication graph
    const val AUTH_GRAPH="auth_graph"

    //routes within the authentication graph
    const val LOGIN="login"
    const val FORGOT_PASSWORD="forgot_password"
}

@Composable
fun NewAppNavHost(
    modifier:Modifier=Modifier,
    navController: NavHostController= rememberNavController(),
    startDestination:String= NewAppRoutes.HOME
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){

        composable(NewAppRoutes.HOME){
            HomeScreen(
                onNavigateToProfile = {navController.navigate(NewAppRoutes.PROFILE)},
                onNavigateToAuth = {navController.navigate(NewAppRoutes.AUTH_GRAPH)}
            )
        }
        composable(NewAppRoutes.PROFILE){
            ProfileScreen(onNavigateBack = {navController.popBackStack()})
        }

        authGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavHostController){
    //This navigation builder creates a nested graph
    navigation(
        startDestination = NewAppRoutes.LOGIN,
        route= NewAppRoutes.AUTH_GRAPH
    ){
        //composable within the auth graph

        composable(NewAppRoutes.LOGIN){
            LogiinScreen(
                onLoginSuccess = {
                    navController.navigate(NewAppRoutes.HOME){
                        popUpTo(NewAppRoutes.AUTH_GRAPH){
                            inclusive=true
                        }
                    }
                },
                onForgotPasswordClick = {navController.navigate(NewAppRoutes.FORGOT_PASSWORD)}
            )
        }
        composable(NewAppRoutes.FORGOT_PASSWORD){
            ForgotPasswordScreen(onNavigateBack = {navController.popBackStack()})
        }
    }
}

@Composable
fun HomeScreen(onNavigateToProfile:()->Unit,onNavigateToAuth:()->Unit){
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text("Home Screen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onNavigateToProfile) { Text("Go to Profile") }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onNavigateToAuth) { Text("Logout / Go to Login") }

    }
}
@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen", fontSize = 24.sp)
        Button(onClick = onNavigateBack) { Text("Go Back") }
    }
}

@Composable
fun LogiinScreen(onLoginSuccess: () -> Unit, onForgotPasswordClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onLoginSuccess) { Text("Log In") }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onForgotPasswordClick) { Text("Forgot Password?") }
    }
}

@Composable
fun ForgotPasswordScreen(onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Forgot Password Screen", fontSize = 24.sp)
        Button(onClick = onNavigateBack) { Text("Back to Login") }
    }
}