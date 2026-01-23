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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
Fully customizable SearchBar demonstrating all available customization options.
This example shows how to create a production-ready search experience with
filtering, custom icons, rich results, and callback handling.

Key concepts:
- Callback functions: onSearch, onQueryChange, onResultClick for parent communication
- Customization parameters: placeholder, leadingIcon, trailingIcon
- supportingContent lambda: Provides dynamic content for each result
- leadingContent lambda: Provides leading icons for results
- State hoisting: Query state managed by parent composable
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizableSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    onResultClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit = { Text("Search") },
    leadingIcon: @Composable (() -> Unit)? = {
        Icon(Icons.Default.Search, contentDescription = "Search")
    },
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingContent: (@Composable (String) -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
) {
    // Track expanded state of search bar
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                // Customizable input field implementation
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = {
                        onSearch(query)
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = placeholder,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            // Show search results in a lazy column for better performance
            LazyColumn {
                items(count = searchResults.size) { index ->
                    val resultText = searchResults[index]
                    ListItem(
                        headlineContent = { Text(resultText) },
                        supportingContent = supportingContent?.let { { it(resultText) } },
                        leadingContent = leadingContent,
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .clickable {
                                onResultClick(resultText)
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

/*
Example usage of CustomizableSearchBar demonstrating state hoisting
and custom callback handling.
*/
@Composable
fun CustomizableSearchBarExample() {
    var query by rememberSaveable { mutableStateOf("") }

    val allResults = remember {
        listOf(
            "Android Development",
            "Android Studio",
            "Jetpack Compose",
            "Material Design",
            "Kotlin Programming",
            "App Architecture",
            "Navigation Component",
            "Room Database"
        )
    }

    val filteredResults = remember(query) {
        if (query.isEmpty()) {
            allResults
        } else {
            allResults.filter { it.contains(query, ignoreCase = true) }
        }
    }

    CustomizableSearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = { searchQuery ->
            // Handle search action
            println("Searching for: $searchQuery")
        },
        searchResults = filteredResults,
        onResultClick = { result ->
            // Handle result selection
            query = result
            println("Selected: $result")
        },
        placeholder = { Text("Search topics") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        },
        trailingIcon = {
            IconButton(onClick = { /* Show filter options */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }
        },
        supportingContent = { result ->
            Text("Click to select: $result")
        },
        leadingContent = {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    )
}
