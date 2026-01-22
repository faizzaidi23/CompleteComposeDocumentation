package com.example.FullComposeOfficialDocumentation.Components_3.NavigationDrawer_16

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawerWithGroupsExample() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))

                    // Main drawer title
                    Text(
                        "Navigation Menu",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()

                    // Group 1: Main Navigation
                    Text(
                        "Main",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Home") },
                        selected = true,
                        icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Favorites") },
                        selected = false,
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        onClick = { /* Handle click */ }
                    )

                    // Divider between groups
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    // Group 2: User Section
                    Text(
                        "User",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Profile") },
                        selected = false,
                        icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Achievements") },
                        selected = false,
                        icon = { Icon(Icons.Filled.Star, contentDescription = null) },
                        badge = { Text("5") }, // Badge showing count
                        onClick = { /* Handle click */ }
                    )

                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    ) {
        // Screen content
        Text("Main content with grouped navigation", modifier = Modifier.padding(16.dp))
    }
}

