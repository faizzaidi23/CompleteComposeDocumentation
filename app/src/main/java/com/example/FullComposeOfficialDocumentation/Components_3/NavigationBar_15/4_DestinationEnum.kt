package com.example.FullComposeOfficialDocumentation.Components_3.NavigationBar_15

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

// Enum representing app destinations
enum class Destination(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val contentDescription: String
) {
    SONGS(
        route = "songs",
        icon = Icons.Filled.List,
        label = "Songs",
        contentDescription = "Songs screen"
    ),
    ALBUMS(
        route = "albums",
        icon = Icons.Filled.Favorite,
        label = "Albums",
        contentDescription = "Albums screen"
    ),
    ARTISTS(
        route = "artists",
        icon = Icons.Filled.Person,
        label = "Artists",
        contentDescription = "Artists screen"
    )
}

