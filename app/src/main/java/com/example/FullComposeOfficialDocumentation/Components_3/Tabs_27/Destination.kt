package com.example.FullComposeOfficialDocumentation.Components_3.Tabs_27

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/*
Destination enum defines all available tab destinations.

Each destination has:
- route: Navigation route identifier
- label: Text displayed on the tab
- icon: Icon displayed on the tab
- contentDescription: Accessibility description
*/

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    SONGS(
        route = "songs",
        label = "Songs",
        icon = Icons.Default.Home,
        contentDescription = "Songs tab"
    ),
    ARTISTS(
        route = "artists",
        label = "Artists",
        icon = Icons.Default.Person,
        contentDescription = "Artists tab"
    ),
    ALBUMS(
        route = "albums",
        label = "Albums",
        icon = Icons.Default.Settings,
        contentDescription = "Albums tab"
    )
}

