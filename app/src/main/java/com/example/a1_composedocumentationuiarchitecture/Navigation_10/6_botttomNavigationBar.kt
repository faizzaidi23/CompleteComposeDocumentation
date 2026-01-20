package com.example.a1_composedocumentationuiarchitecture.Navigation_10

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class BottomNavScreen(
    val route:String,
    val title:String,
    val icon: ImageVector
){
    object Home: BottomNavScreen("home","Home",Icons.Default.Home)
    object Profile: BottomNavScreen("profile","Profile",Icons.Default.Person)
    object Settings: BottomNavScreen("settings","Settings",Icons.Default.Settings)
}

@Composable
fun MainAppScreen(){
    val navController= rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val currentDestination = navBackStackEntry?.destination

                val screens=listOf(BottomNavScreen.Home, BottomNavScreen.Profile, BottomNavScreen.Settings)

                screens.forEach{screen->
                    NavigationBarItem(
                        label={Text(screen.title)},
                        icon={Icon(screen.icon, contentDescription = null)},
                        selected = currentDestination?.hierarchy?.any{it.route==screen.route}==true,/*highlights the correct icon on your bottom navigation bar.*/
                        onClick = {
                            navController.navigate(screen.route){
                                /*
                                pop up to the start destination of the graph to
                                avoid building upa large stack of destinations
                                on the back stack as users select items
                                */
                                popUpTo(
                                    navController.graph.findStartDestination().id
                                ){
                                    saveState=true
                                }
                                //avoid multiple copies of the same destination when reselecting the same item
                                launchSingleTop=true
                                //Restore state when reselecting a previously selected item
                                restoreState=true
                            }
                        }
                    )
                }
            }
        }
    ){innerPadding->
        AppNavHost(
            navController = navController,
            modifier=Modifier.padding(innerPadding)
        )

    }
}

//Setting up the navHost for the different screens
@Composable
fun AppNavHost(
    navController: NavHostController, modifier:Modifier= Modifier
){
    NavHost(
        navController = navController,
        startDestination = BottomNavScreen.Home.route,
        modifier=modifier
    ){
      composable(BottomNavScreen.Home.route) {
          GenericScreen(title = "Home")
      }
      composable(BottomNavScreen.Profile.route){
          GenericScreen(title="Profile")
      }
      composable(BottomNavScreen.Settings.route){
          GenericScreen(title="Settings")
      }
    }
}

// Generic Screen
@Composable
fun GenericScreen(title:String){

    Box(
        modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text="$title Screen",fontSize=24.sp)
    }

}
