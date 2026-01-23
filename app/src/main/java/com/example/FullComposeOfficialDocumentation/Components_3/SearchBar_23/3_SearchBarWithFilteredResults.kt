package com.example.FullComposeOfficialDocumentation.Components_3.SearchBar_23

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

/*
SearchBar with filtered results displays only items that match the user's query.
As the user types, the list dynamically filters to show relevant matches.

Key concepts:
- Dynamic filtering: Results update in real-time as query changes
- LazyColumn: Efficiently handles large lists by rendering only visible items
- Case-insensitive search: Uses contains() with ignoreCase for better UX
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithFilteredResults() {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    // Sample data to search through
    val allItems = remember {
        listOf(
            "Android Development",
            "Android Studio",
            "Jetpack Compose",
            "Material Design",
            "Kotlin Programming",
            "Kotlin Coroutines",
            "App Architecture",
            "Navigation Component",
            "Room Database",
            "Retrofit Library"
        )
    }

    // Filter results based on query
    val filteredItems = remember(query) {
        if (query.isEmpty()) {
            allItems
        } else {
            allItems.filter { it.contains(query, ignoreCase = true) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = {
                        query = it
                        expanded = it.isNotEmpty()
                    },
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search items") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            // Use LazyColumn for better performance with large lists
            LazyColumn {
                items(count = filteredItems.size) { index ->
                    val item = filteredItems[index]
                    ListItem(
                        headlineContent = { Text(item) },
                        modifier = Modifier
                            .clickable {
                                query = item
                                expanded = false
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

