package com.example.FullComposeOfficialDocumentation.Components_3.SearchBar_23

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
Customizable SearchBar with leading and trailing icons.
Demonstrates how to add visual elements to enhance the search experience.

Key concepts:
- leadingIcon: Icon at the start of the search field (typically a search icon)
- trailingIcon: Icon at the end (typically for clearing text or additional actions)
- IconButton: Makes icons clickable for actions like clearing the query
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithIcons() {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    val searchItems = remember {
        listOf(
            "Compose UI",
            "Compose Animation",
            "Compose Navigation",
            "Compose Theming",
            "Compose State",
            "Compose Effects"
        )
    }

    val filteredItems = remember(query) {
        if (query.isEmpty()) {
            searchItems
        } else {
            searchItems.filter { it.contains(query, ignoreCase = true) }
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
                    placeholder = { Text("Search") },
                    // Leading icon - search icon at the start
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search icon"
                        )
                    },
                    // Trailing icon - clear button at the end
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = {
                                query = ""
                                expanded = false
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear search"
                                )
                            }
                        }
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

