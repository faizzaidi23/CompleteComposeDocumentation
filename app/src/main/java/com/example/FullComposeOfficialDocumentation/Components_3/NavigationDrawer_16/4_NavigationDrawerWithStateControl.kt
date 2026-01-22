    // CoroutineScope for launching suspending functions
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        // Pass drawer state to control behavior
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer Content")
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = {
                        // Close drawer when item is clicked
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
    ) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        // Toggle drawer open/close using coroutine
                        scope.launch {
                            drawerState.apply {
                                // Check current state and toggle
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            // Screen content with padding
            Text("Main content", modifier = androidx.compose.ui.Modifier.padding(contentPadding))
        }
    }
}
package com.example.FullComposeOfficialDocumentation.Components_3.NavigationDrawer_16

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawerWithStateControlExample() {
    // Create drawer state to control open/close behavior
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


