package com.example.FullComposeOfficialDocumentation.Components_3.SearchBar_23

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

/*
We use a search bar to implement the search functionality
A search bar is a persistent search field that lets the users enter a keyword
or phrase to display relevant results within the app and is recommended when the search is the primary focus of the app
*/


/**
 API Surface-->


 Use the searchBar composable to implement the search bars.
 Key parameters include-->


 inputField-->Defines the input field of the search bar.
 This basically utilizes the SearchBarDefaults.InputField which allows the customization of :
 query-->The query text to be shown in the input field
 onQueryChange-->Lambda to handle the changes in the query string


 expanded-->A boolean indicating whether the search bar is expanded to show the suggestions or filtered results
 onExpandedChange-->Lambda to handle the changes in the dropdown's expanded state
 content-->The content of this search bar to display search results below the inputField

 **/

/*
SearchBar is a persistent search field that lets users enter a keyword or phrase
to display relevant results within your app.

Key parameters:
- inputField: Defines the input field of the search bar
- expanded: Boolean indicating if the search bar shows suggestions
- onExpandedChange: Lambda to handle expansion state changes
- content: Content to display below the input field (search results)
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicSearchBar() {
    // State to store the search query
    var query by rememberSaveable { mutableStateOf("") }
    // State to control whether search suggestions are visible
    var expanded by rememberSaveable { mutableStateOf(false) }

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
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            // Empty content - basic search bar without suggestions
            Text(
                text = "Search results will appear here",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
