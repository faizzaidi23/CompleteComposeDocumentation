package com.example.FullComposeOfficialDocumentation.Components_3.NavigationDrawer_16

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerWithSelectionExample() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Track selected item index
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    // Define navigation items with labels and icons
    val items = listOf(
        Pair("Home", Icons.Filled.Home),
        Pair("Profile", Icons.Filled.AccountCircle),
        Pair("Settings", Icons.Filled.Settings)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("My App", modifier = Modifier.padding(16.dp))
                HorizontalDivider()

                // Iterate through items and create drawer items
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(item.first) },
                        selected = selectedItemIndex == index,
                        icon = { Icon(item.second, contentDescription = item.first) },
                        onClick = {
                            // Update selected item
                            selectedItemIndex = index
                            // Close drawer after selection
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        }
    ) {
        // Screen content shows selected item
        Text(
            "Selected: ${items[selectedItemIndex].first}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

