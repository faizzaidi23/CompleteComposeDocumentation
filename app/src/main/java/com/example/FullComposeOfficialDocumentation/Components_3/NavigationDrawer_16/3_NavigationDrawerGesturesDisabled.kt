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
fun NavigationDrawerWithGesturesDisabledExample() {
    // Navigation drawer with gestures disabled
    // Users can only open it via button tap, not by swiping
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer with gestures disabled", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Item 1") },
                    selected = false,
                    onClick = { /* Handle click */ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Item 2") },
                    selected = false,
                    onClick = { /* Handle click */ }
                )
            }
        },
        // Disable swipe gestures to open drawer
        gesturesEnabled = false
    ) {
        // Screen content
        Text("Swipe gestures disabled. Use menu button to open drawer.",
            modifier = Modifier.padding(16.dp))
    }
}

