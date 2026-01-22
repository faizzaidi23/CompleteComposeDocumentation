package com.example.FullComposeOfficialDocumentation.Components_3.NavigationRail_17

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
fun NavigationRailIconOnlyExample() {
    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf("Home", "Search", "Profile", "Settings")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Search,
        Icons.Filled.Person,
        Icons.Filled.Settings
    )

    Row(modifier = Modifier.fillMaxSize()) {
        // NavigationRail without labels - icon only
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
                    }
                    // No label parameter - cleaner look for icon-only navigation
                )
            }
        }

        Text(
            "Icon-only Navigation Rail\nSelected: ${items[selectedItem]}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

