package com.example.FullComposeOfficialDocumentation.Components_3.NavigationRail_17

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
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
fun NavigationRailSevenDestinationsExample() {
    var selectedItem by remember { mutableIntStateOf(0) }

    // NavigationRail supports 3-7 destinations
    val items = listOf("Home", "Search", "Favorites", "Profile", "Settings", "Starred", "More")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Search,
        Icons.Filled.Favorite,
        Icons.Filled.Person,
        Icons.Filled.Settings,
        Icons.Filled.Star,
        Icons.Filled.Home
    )

    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            modifier = Modifier.fillMaxHeight()
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
            "7 Destinations Example\nSelected: ${items[selectedItem]}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

