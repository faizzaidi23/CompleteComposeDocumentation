package com.example.FullComposeOfficialDocumentation.Components_3.NavigationBar_15

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun NavigationBarFiveDestinationsExample() {
    var selectedItem by remember { mutableIntStateOf(0) }

    // Maximum of 5 destinations for navigation bar
    val items = listOf("Home", "Favorites", "Profile", "Starred", "More")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Favorite,
        Icons.Filled.Person,
        Icons.Filled.Star,
        Icons.Filled.Home
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
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
}

