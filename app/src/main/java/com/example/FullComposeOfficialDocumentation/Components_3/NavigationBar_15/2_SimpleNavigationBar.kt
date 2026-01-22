package com.example.FullComposeOfficialDocumentation.Components_3.NavigationBar_15

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SimpleNavigationBarExample(modifier: Modifier = Modifier) {
    // State to track selected item
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    // List of navigation items
    val items = listOf("Home", "Profile", "Settings")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Person,
        Icons.Filled.Settings
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
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
    ) { paddingValues ->
        // Content goes here using paddingValues
    }
}

