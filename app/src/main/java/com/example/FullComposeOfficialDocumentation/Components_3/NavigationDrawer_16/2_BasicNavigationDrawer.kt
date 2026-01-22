package com.example.FullComposeOfficialDocumentation.Components_3.NavigationDrawer_16

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BasicNavigationDrawerExample() {
    // Basic navigation drawer with minimal setup
    ModalNavigationDrawer(
        drawerContent = {
            // ModalDrawerSheet provides Material Design styling
            ModalDrawerSheet {
                // Drawer title
                Text("Drawer title", modifier = Modifier.padding(16.dp))

                // Divider to separate title from items
                HorizontalDivider()

                // Navigation drawer items
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /* Handle item click */ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Second Item") },
                    selected = false,
                    onClick = { /* Handle item click */ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Third Item") },
                    selected = true, // This item is selected
                    onClick = { /* Handle item click */ }
                )
            }
        }
    ) {
        // Screen content goes here
        Text("Main content", modifier = Modifier.padding(16.dp))
    }
}

