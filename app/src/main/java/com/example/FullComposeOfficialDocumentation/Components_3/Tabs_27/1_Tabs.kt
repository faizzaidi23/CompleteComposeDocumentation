package com.example.FullComposeOfficialDocumentation.Components_3.Tabs_27

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.rememberNavController

/*
Tabs allow us to organize groups of related content.
There are two types of tabs

Primary tabs-->Placed at the top of the content pane under a top app bar. They display the main content destinations and should be used when just one set of tabs are needed

Secondary tabs-->Used within a content area to further separate related content and establish
hierarchy they are necessary when a screen requires more than one level of tabs
*/

/*
API surface-->
Use the Tab,PrimaryTabRow and the SecondaryTabRow composable to implement tabs.
The Tab composable represents an individual tab within the row and is typically used inside of a
PrimaryTabRow for primary indicator tabs or SecondaryTabRow for secondary indicator tabs

Tab includes the following key parameters

* selected-->Determines whether the current tab is visually highlighted or not

* onClick()-->A required function that defines the action to be performed when the user
clicks on the tab
this is where we typically handle the navigation events, update the selected tab state or load corresponding content

*text-->Displays the text within the tab. This is optional
*icon-->Displays an icon within the tab. This is optional too
*enabled-->Controls whether the tab is enabled and can be interacted with
if set to false, the tab appears in a disabled state and won't be able to be responded by the  clicks
*/

/*
Key concepts in the implementation:

1. selected = selectedDestination == index
   - Compares current tab's index with the selected index
   - Returns true if they match (this tab is selected)
   - Returns false if they don't match (this tab is not selected)
   - This highlights the active tab visually

2. selectedTabIndex parameter in PrimaryTabRow
   - Tells the TabRow which tab should show the indicator line underneath
   - Keeps the UI in sync with the selected state

3. rememberSaveable with mutableIntStateOf
   - Stores the selected tab index (0, 1, 2, etc.)
   - Survives configuration changes (like screen rotation)
   - ordinal converts enum to index number

4. forEachIndexed on Destination.entries
   - Loops through all destinations
   - index: Position in the list (0, 1, 2...)
   - destination: The actual destination object

5. Why we need both navigation and state update:
   - navController.navigate(): Changes the screen content
   - selectedDestination = index: Updates which tab appears selected
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTabExample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.SONGS

    // Stores which tab is currently selected (0, 1, 2, etc.)
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(modifier = modifier) { contentPadding ->
        PrimaryTabRow(
            selectedTabIndex = selectedDestination,
            modifier = Modifier.padding(contentPadding)
        ) {
            // Loop through each destination and create a Tab for it
            Destination.entries.forEachIndexed { index, destination ->
                Tab(
                    // Check if THIS tab is the selected one
                    // If index=0 and selectedDestination=0, then selected=true
                    selected = selectedDestination == index,
                    onClick = {
                        // Navigate to the destination screen
                        navController.navigate(route = destination.route)
                        // Update selected tab to THIS tab's index
                        selectedDestination = index
                    },
                    text = {
                        Text(
                            text = destination.label,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
        AppNavHost(navController, startDestination)
    }
}
