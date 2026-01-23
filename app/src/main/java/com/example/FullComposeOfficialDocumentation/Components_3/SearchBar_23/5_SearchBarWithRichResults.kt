package com.example.FullComposeOfficialDocumentation.Components_3.SearchBar_23

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

/*
Advanced SearchBar with rich list items showing additional content.
Demonstrates ListItem customization with supporting text and leading icons.

Key concepts:
- headlineContent: Main text of the list item
- supportingContent: Secondary text below the headline (e.g., description)
- leadingContent: Content before the text (e.g., icons, avatars)
- ListItemDefaults.colors: Customize list item colors
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithRichResults() {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    // Data class to hold search items with descriptions
    data class SearchItem(val title: String, val description: String)

    val searchItems = remember {
        listOf(
            SearchItem("Jetpack Compose", "Modern toolkit for native Android UI"),
            SearchItem("Material Design 3", "Latest design system from Google"),
            SearchItem("Kotlin Coroutines", "Asynchronous programming in Kotlin"),
            SearchItem("Room Database", "Persistence library for SQLite"),
            SearchItem("Navigation Component", "Handle app navigation easily"),
            SearchItem("ViewModel", "Store UI-related data lifecycle-aware")
        )
    }

    val filteredItems = remember(query) {
        if (query.isEmpty()) {
            searchItems
        } else {
            searchItems.filter {
                it.title.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
            }
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
                    placeholder = { Text("Search topics") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            LazyColumn {
                items(count = filteredItems.size) { index ->
                    val item = filteredItems[index]
                    ListItem(
                        headlineContent = { Text(item.title) },
                        // Supporting content shows additional information
                        supportingContent = { Text(item.description) },
                        // Leading content shows an icon
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .clickable {
                                query = item.title
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

