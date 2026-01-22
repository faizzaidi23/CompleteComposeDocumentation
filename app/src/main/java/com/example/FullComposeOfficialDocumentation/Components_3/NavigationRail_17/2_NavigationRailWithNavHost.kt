package com.example.FullComposeOfficialDocumentation.Components_3.NavigationRail_17

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
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
import com.example.FullComposeOfficialDocumentation.Components_3.NavigationBar_15.Destination

@Composable
fun NavigationRailExample(modifier: Modifier = Modifier) {
    // Create NavController for navigation management
    val navController = rememberNavController()
    val startDestination = Destination.SONGS

    // Track selected destination using saveable state
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(modifier = modifier) { contentPadding ->
        // NavigationRail provides vertical navigation on the left side
        NavigationRail(modifier = Modifier.padding(contentPadding)) {
            // Iterate through all destinations
            Destination.entries.forEachIndexed { index, destination ->
                NavigationRailItem(
                    // Highlight selected item
                    selected = selectedDestination == index,
                    onClick = {
                        // Navigate to destination route
                        navController.navigate(route = destination.route)
                        // Update selected state
                        selectedDestination = index
                    },
                    icon = {
                        Icon(
                            destination.icon,
                            contentDescription = destination.contentDescription
                        )
                    },
                    // Label appears below icon
                    label = { Text(destination.label) }
                )
            }
        }

        // NavHost manages screen content
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

