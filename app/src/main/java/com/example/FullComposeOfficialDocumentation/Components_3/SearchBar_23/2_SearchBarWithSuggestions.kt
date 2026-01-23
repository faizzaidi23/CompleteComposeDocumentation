package com.example.FullComposeOfficialDocumentation.Components_3.SearchBar_23

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

/*
SearchBar with suggestions displays a list of search suggestions below the input field.
When users type in the search field, relevant suggestions appear.

Key concepts:
- rememberSaveable: Preserves state across configuration changes
- semantics modifier: Controls TalkBack traversal order for accessibility
- isTraversalGroup: Groups child composables for screen reader navigation
- traversalIndex: Specifies reading order (negative values read first)
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithSuggestions() {
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }

    // Sample search suggestions
    val suggestions = remember {
        listOf(
            "Android Development",
            "Android Studio",
            "Jetpack Compose",
            "Material Design",
            "Kotlin Programming",
            "App Architecture"
        )
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
                    onQueryChange = { query = it },
                    onSearch = {
                        // Perform search action
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            // Display search suggestions in a scrollable column
            Column(Modifier.verticalScroll(rememberScrollState())) {
                suggestions.forEach { suggestion ->
                    ListItem(
                        headlineContent = { Text(suggestion) },
                        modifier = Modifier
                            .clickable {
                                // Fill search field with selected suggestion
                                query = suggestion
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

