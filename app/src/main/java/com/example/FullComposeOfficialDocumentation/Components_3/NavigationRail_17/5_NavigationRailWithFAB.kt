package com.example.FullComposeOfficialDocumentation.Components_3.NavigationRail_17

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationRailWithFABExample() {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf("Home", "Favorites", "Profile", "Settings")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Favorite,
        Icons.Filled.Person,
        Icons.Filled.Settings
    )

    Row(modifier = Modifier.fillMaxSize()) {
        // NavigationRail with header slot for FAB
        NavigationRail(
            modifier = Modifier.fillMaxHeight(),
            header = {
                // FloatingActionButton at the top of the rail
                FloatingActionButton(
                    onClick = { /* Handle FAB click */ },
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
                // Spacer between FAB and rail items
                Spacer(modifier = Modifier.height(16.dp))
            }
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    icon = {
                        Icon(
                            icons[index],
                            contentDescription = item
                        )
                    },
                    label = { Text(item) }
                )
            }
        }

        Text(
            "Navigation Rail with FAB\nSelected: ${items[selectedItem]}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

