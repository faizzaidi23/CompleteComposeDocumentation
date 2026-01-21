package com.example.FullComposeOfficialDocumentation.Components_3.AppBars_2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/*
═══════════════════════════════════════════════════════════════════════════════
                              APP BARS
═══════════════════════════════════════════════════════════════════════════════

App bars are containers that provide access to key features and navigation items.

TWO TYPES:
1. TOP APP BAR - Across the top of the screen
   - Provides access to key tasks and information
   - Hosts title, core action items, and navigation items

2. BOTTOM APP BAR - Across the bottom of the screen
   - Includes core navigation items
   - May provide access to key actions (with FAB)

IMPORTANT: App bars are generally passed to the Scaffold composable
           Scaffold has specific parameters to receive them

═══════════════════════════════════════════════════════════════════════════════
                          TOP APP BARS
═══════════════════════════════════════════════════════════════════════════════

FOUR TYPES OF TOP APP BARS:

1. SMALL - For screens that don't require a lot of navigation/actions
2. CENTER ALIGNED - For screens with a single, primary action
3. MEDIUM - For screens requiring moderate navigation and actions
4. LARGE - For screens requiring lots of navigation and actions

KEY PARAMETERS (common across all types):
- title: Text appearing across the app bar
- navigationIcon: Primary icon for navigation (left side)
- actions: Icons for key actions (right side)
- scrollBehavior: How app bar responds to scrolling
- colors: App bar appearance

═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// SCROLL BEHAVIOR - Controls app bar response to scrolling
// ─────────────────────────────────────────────────────────────────────────────

/*
THREE TYPES OF TopAppBarScrollBehavior:

1. enterAlwaysScrollBehavior()
   - App bar collapses when user scrolls up
   - Expands when user scrolls down

2. exitUntilCollapsedScrollBehavior()
   - Similar to enterAlways
   - Additionally expands when reaching end of content

3. pinnedScrollBehavior()
   - App bar remains in place
   - Does not react to scrolling
*/

// ─────────────────────────────────────────────────────────────────────────────
// 1. SMALL TOP APP BAR - Basic implementation
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarExample() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Small Top App Bar") }
            )
        },
    ) { innerPadding ->
        Text(
            "Content here",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 2. CENTER ALIGNED TOP APP BAR - Title centered with navigation & actions
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Text(
            "Content with centered app bar",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 3. MEDIUM TOP APP BAR - Title beneath icons
// Uses enterAlwaysScrollBehavior - collapses on scroll up, expands on scroll down
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Medium Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(50) { index ->
                Text(
                    "Item $index",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// 4. LARGE TOP APP BAR - More space, greater padding
// Uses exitUntilCollapsedScrollBehavior - expands when reaching end of content
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Large Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(100) { index ->
                Text(
                    "Item $index",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                          BOTTOM APP BAR
═══════════════════════════════════════════════════════════════════════════════

KEY PARAMETERS:
- actions: Series of icons on the left side (key actions or navigation)
- floatingActionButton: FAB on the right side

NOTE: You can also use BottomAppBar without actions and FAB to create
      a custom bottom app bar with any content you want
*/

// ─────────────────────────────────────────────────────────────────────────────
// BOTTOM APP BAR - Actions on left, FAB on right
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun BottomAppBarExample() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "Check")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Mic, contentDescription = "Mic")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Image, contentDescription = "Image")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Scaffold with bottom app bar"
        )
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                    TOP APP BAR WITH NAVIGATION
═══════════════════════════════════════════════════════════════════════════════

To make the navigation icon functional:
1. Define a parameter of type () -> Unit (e.g., navigateBack)
2. Pass it to the navigationIcon's onClick
3. When using the composable, pass NavController.popBackStack()

Example usage in Navigation Graph:
    NavHost(navController, startDestination = "home") {
        composable("screen") {
            TopBarNavigationExample { navController.popBackStack() }
        }
    }

You can also implement different actions:
- Home icon → navController.navigate("home")
- Settings icon → navController.navigate("settings")
*/

// ─────────────────────────────────────────────────────────────────────────────
// NAVIGATION EXAMPLE - Functional back button
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigationExample(
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Navigation example") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Text(
            "Click the back button to pop from the back stack.",
            modifier = Modifier.padding(innerPadding),
        )
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                    DYNAMIC TOP APP BAR
═══════════════════════════════════════════════════════════════════════════════

Changes title and actions based on selection state.

USE CASE: When user selects items from a list
- Default: Shows list title
- Selected: Shows "Selected X items" with action icons (share, delete, etc.)

KEY CONCEPTS:
- selectedItems: Set of selected item indexes
- hasSelection: Boolean check if any items selected
- Conditional rendering of title and actions
*/

// ─────────────────────────────────────────────────────────────────────────────
// DYNAMIC APP BAR - Changes based on selection
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarSelectionActions(
    selectedItems: Set<Int>,
    modifier: Modifier = Modifier,
) {
    val hasSelection = selectedItems.isNotEmpty()
    val topBarText = if (hasSelection) {
        "Selected ${selectedItems.size} items"
    } else {
        "List of items"
    }

    TopAppBar(
        title = { Text(topBarText) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            if (hasSelection) {
                IconButton(onClick = { /* share action */ }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share items"
                    )
                }
                IconButton(onClick = { /* delete action */ }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete items"
                    )
                }
            }
        },
        modifier = modifier
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// SELECTABLE LIST WITH DYNAMIC APP BAR
// Long press to select items, app bar updates accordingly
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppBarMultiSelectionExample(
    modifier: Modifier = Modifier,
) {
    val listItems by remember { mutableStateOf(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) }
    var selectedItems by rememberSaveable { mutableStateOf(setOf<Int>()) }

    Scaffold(
        modifier = modifier,
        topBar = { AppBarSelectionActions(selectedItems) }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            itemsIndexed(listItems) { index, item ->
                val isItemSelected = selectedItems.contains(index)

                ListItem(
                    headlineContent = { Text("Item $item") },
                    leadingContent = {
                        if (isItemSelected) {
                            Icon(Icons.Filled.Check, contentDescription = "Selected")
                        } else {
                            Icon(Icons.Filled.Person, contentDescription = null)
                        }
                    },
                    modifier = Modifier.combinedClickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { /* regular click action */ },
                        onLongClick = {
                            selectedItems = if (isItemSelected) {
                                selectedItems - index
                            } else {
                                selectedItems + index
                            }
                        }
                    )
                )
            }
        }
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                          IMPORTANT CONCEPTS
═══════════════════════════════════════════════════════════════════════════════

1. APP BAR HIERARCHY
   Small < Center < Medium < Large (in terms of height and prominence)

2. WHEN TO USE WHICH APP BAR
   - Small: Simple screens, minimal actions
   - Center: Focus on single main action
   - Medium: Moderate complexity
   - Large: Complex screens with lots of content

3. SCROLL BEHAVIOR TYPES
   - pinnedScrollBehavior(): Stays fixed
   - enterAlwaysScrollBehavior(): Responsive to scroll direction
   - exitUntilCollapsedScrollBehavior(): Expands at content end

4. NESTED SCROLL CONNECTION
   Must use Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
   to enable scroll behavior

5. BOTTOM APP BAR vs NAVIGATION BAR
   - BottomAppBar: For actions + FAB
   - NavigationBar: For primary navigation between destinations

6. DYNAMIC APP BAR PATTERN
   Common in Gmail, Files, Photos apps
   - Long press to enter selection mode
   - App bar shows selection count and actions
   - Click outside or back to exit selection mode

7. STATE MANAGEMENT
   - Use rememberSaveable for selection state (survives configuration changes)
   - Pass state up to app bar from list
   - Actions in app bar operate on selected items

8. ACCESSIBILITY
   - Always provide contentDescription for icons
   - Use semantic colors (primaryContainer, primary, etc.)
   - Ensure touch targets are 48dp minimum

9. INTEGRATION WITH SCAFFOLD
   - topBar parameter accepts app bar composable
   - bottomBar parameter accepts bottom app bar
   - innerPadding accounts for app bar height automatically

10. COMBINING WITH NAVIGATION
    - NavigationIcon typically for back/up navigation
    - Pass NavController functions as parameters
    - Actions for screen-specific functionality

═══════════════════════════════════════════════════════════════════════════════
*/
