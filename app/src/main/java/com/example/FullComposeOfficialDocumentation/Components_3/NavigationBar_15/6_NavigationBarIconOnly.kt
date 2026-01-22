package com.example.FullComposeOfficialDocumentation.Components_3.NavigationBar_15

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
fun NavigationBarIconOnlyExample() {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf("Home", "Search", "Settings")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Search,
        Icons.Filled.Settings
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
                // No label parameter - icon only
            )
        }
    }
}

