package com.example.FullComposeOfficialDocumentation.Components_3.NavigationBar_15

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationBarWithNavHostExample(modifier: Modifier = Modifier) {
    // Create NavController to manage navigation
    val navController = rememberNavController()
    val startDestination = Destination.SONGS

    // Track selected destination
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                // Iterate through all destinations
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            // Navigate to destination and update selection
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        // NavHost handles navigation between screens
        NavHost(
            navController = navController,
            startDestination = startDestination.route,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(Destination.SONGS.route) {
                Text("Songs Screen", modifier = Modifier.padding(contentPadding))
            }
            composable(Destination.ALBUMS.route) {
                Text("Albums Screen", modifier = Modifier.padding(contentPadding))
            }
            composable(Destination.ARTISTS.route) {
                Text("Artists Screen", modifier = Modifier.padding(contentPadding))
            }
        }
    }
}

