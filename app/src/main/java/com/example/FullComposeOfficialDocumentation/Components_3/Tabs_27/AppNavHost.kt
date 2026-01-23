package com.example.FullComposeOfficialDocumentation.Components_3.Tabs_27

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/*
AppNavHost manages navigation between different tab destinations.
It defines which screen to show for each route.
*/

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        composable(Destination.SONGS.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Songs Screen",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        composable(Destination.ARTISTS.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Artists Screen",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        composable(Destination.ALBUMS.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Albums Screen",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

