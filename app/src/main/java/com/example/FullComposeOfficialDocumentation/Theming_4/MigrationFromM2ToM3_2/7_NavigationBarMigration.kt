package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp

/**
 * Bottom Navigation Migration from M2 to M3
 *
 * M2: BottomNavigation + BottomNavigationItem
 * M3: NavigationBar + NavigationBarItem
 *
 * The component is renamed but functionality remains similar
 */

// M2 approach (old):
// BottomNavigation {
//     BottomNavigationItem(
//         icon = { Icon(...) },
//         label = { Text("Home") },
//         selected = selectedItem == 0,
//         onClick = { selectedItem = 0 }
//     )
// }

// M3 approach (new):
@Composable
fun NavigationBarMigrationExample() {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val items = listOf("Home", "Search", "Favorites")
    val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Favorite)

    Scaffold(
        bottomBar = {
            NavigationBar {  // Was BottomNavigation in M2
                items.forEachIndexed { index, item ->
                    NavigationBarItem(  // Was BottomNavigationItem in M2
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Navigation Bar (M3)",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "BottomNavigation renamed to NavigationBar in M3",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Selected item: ${items[selectedItem]}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

