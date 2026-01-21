package com.example.FullComposeOfficialDocumentation.Components_3.OverView_1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
═══════════════════════════════════════════════════════════════════════════════
                    MATERIAL COMPONENTS IN COMPOSE
═══════════════════════════════════════════════════════════════════════════════

Jetpack Compose offers an implementation of Material Design 3 (Material You)
- Comprehensive design system for creating digital interfaces
- Uses composable functions to implement Material components
- Consistent UI/UX across Android apps

Categories:
1. Actions - Buttons, FABs, Icon buttons
2. Communication - Badges, Progress indicators, Snackbars, Tooltips
3. Containment - Bottom sheets, Cards, Dialogs, Lists, Scaffold
4. Navigation - App bars, Navigation components, Tabs
5. Selection - Checkboxes, Chips, Date/Time pickers, Switches
6. Text inputs - Search, Text fields

═══════════════════════════════════════════════════════════════════════════════
                            1. ACTIONS
═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// BUTTONS - Prompt most actions in a UI
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ButtonExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Filled Button - High emphasis, primary action
        Button(onClick = { /* action */ }) {
            Text("Filled Button")
        }

        // Filled Tonal Button - Medium emphasis
        FilledTonalButton(onClick = { /* action */ }) {
            Text("Tonal Button")
        }

        // Outlined Button - Medium emphasis, secondary action
        OutlinedButton(onClick = { /* action */ }) {
            Text("Outlined Button")
        }

        // Text Button - Low emphasis, tertiary action
        TextButton(onClick = { /* action */ }) {
            Text("Text Button")
        }

        // Elevated Button - With shadow elevation
        ElevatedButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Add, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Elevated Button")
        }

        // Button with icon
        Button(onClick = { /* action */ }) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Add to Cart")
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// FLOATING ACTION BUTTON (FAB) - Primary actions
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun FABExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Regular FAB
        FloatingActionButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }

        // Small FAB
        SmallFloatingActionButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Edit, contentDescription = "Edit")
        }

        // Large FAB
        LargeFloatingActionButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        }

        // Extended FAB - with text
        ExtendedFloatingActionButton(
            onClick = { /* action */ },
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            text = { Text("Create New") }
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// ICON BUTTONS - Minor actions with one tap
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun IconButtonExamples() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Standard Icon Button
        IconButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Like")
        }

        // Filled Icon Button
        FilledIconButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }

        // Filled Tonal Icon Button
        FilledTonalIconButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings")
        }

        // Outlined Icon Button
        OutlinedIconButton(onClick = { /* action */ }) {
            Icon(Icons.Filled.Share, contentDescription = "Share")
        }

        // Icon Toggle Button - can be checked/unchecked
        var checked by remember { mutableStateOf(false) }
        IconToggleButton(
            checked = checked,
            onCheckedChange = { checked = it }
        ) {
            Icon(
                imageVector = if (checked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Toggle favorite"
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SEGMENTED BUTTONS - Select options, switch views, or sort
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedButtonExample() {
    var selectedIndex by remember { mutableStateOf(0) }
    val options = listOf("Day", "Week", "Month")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(label)
            }
        }
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                            2. COMMUNICATION
═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// BADGES - Show notifications, counts, or status
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun BadgeExamples() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        // Simple badge (dot)
        BadgedBox(badge = { Badge() }) {
            Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
        }

        // Badge with count
        BadgedBox(badge = { Badge { Text("99+") } }) {
            Icon(Icons.Filled.Email, contentDescription = "Messages")
        }

        // Badge with single digit
        BadgedBox(badge = { Badge { Text("3") } }) {
            Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PROGRESS INDICATORS - Show wait time or process duration
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ProgressIndicatorExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Indeterminate Circular - unknown duration
        CircularProgressIndicator()

        // Determinate Circular - known progress
        CircularProgressIndicator(progress = { 0.75f })

        // Indeterminate Linear
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        // Determinate Linear
        LinearProgressIndicator(
            progress = { 0.5f },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SNACKBAR - Short updates at bottom of screen
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun SnackbarExample() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            kotlinx.coroutines.launch {
                val result = snackbarHostState.showSnackbar(
                    message = "Item deleted",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Short
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> { /* Undo action */ }
                    SnackbarResult.Dismissed -> { /* Dismissed */ }
                }
            }
        }) {
            Text("Show Snackbar")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TOOLTIPS - Display brief labels or messages
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipExample() {
    // Plain Tooltip - appears on long press
    PlainTooltip {
        Text("Add item to favorites")
    }

    // Rich Tooltip - with title and action
    RichTooltip(
        title = { Text("Feature Info") },
        action = {
            TextButton(onClick = { /* action */ }) {
                Text("Learn More")
            }
        }
    ) {
        Text("This feature helps you organize your content efficiently.")
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                            3. CONTAINMENT
═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// CARDS - Display content and actions about a single subject
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun CardExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Elevated Card - default with shadow
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Elevated Card", style = MaterialTheme.typography.titleMedium)
                Text("Content goes here", style = MaterialTheme.typography.bodyMedium)
            }
        }

        // Filled Card - with background color
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Filled Card", style = MaterialTheme.typography.titleMedium)
                Text("With filled background", style = MaterialTheme.typography.bodyMedium)
            }
        }

        // Outlined Card - with border
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* clickable card */ }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Outlined Card", style = MaterialTheme.typography.titleMedium)
                Text("Clickable with border", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DIALOGS - Important prompts in user flow
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun DialogExamples() {
    var showBasicDialog by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }

    // Basic Dialog
    if (showBasicDialog) {
        BasicAlertDialog(
            onDismissRequest = { showBasicDialog = false }
        ) {
            Card {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Custom Dialog", style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(16.dp))
                    Text("This is a custom dialog with any content you want")
                    Spacer(Modifier.height(24.dp))
                    TextButton(
                        onClick = { showBasicDialog = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }

    // Alert Dialog - Standard Material Design
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            icon = { Icon(Icons.Filled.Warning, contentDescription = null) },
            title = { Text("Delete Item?") },
            text = { Text("This action cannot be undone.") },
            confirmButton = {
                TextButton(onClick = { showAlertDialog = false }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAlertDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DIVIDERS - Thin lines to group content
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun DividerExample() {
    Column {
        Text("Item 1", modifier = Modifier.padding(16.dp))
        HorizontalDivider() // Horizontal divider line
        Text("Item 2", modifier = Modifier.padding(16.dp))
        HorizontalDivider(thickness = 2.dp) // Thicker divider
        Text("Item 3", modifier = Modifier.padding(16.dp))
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// LISTS - Continuous, vertical indexes of text and images
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun ListExamples() {
    LazyColumn {
        items(10) { index ->
            // List Item with icon, text, and trailing content
            ListItem(
                headlineContent = { Text("Item $index") },
                supportingContent = { Text("Supporting text") },
                leadingContent = {
                    Icon(Icons.Filled.Person, contentDescription = null)
                },
                trailingContent = {
                    IconButton(onClick = { /* action */ }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "More")
                    }
                }
            )
            if (index < 9) HorizontalDivider()
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCAFFOLD - Holds together different parts of UI
// Purpose: Provides slots for app bar, FAB, snackbar, drawer, bottom bar
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                navigationIcon = {
                    IconButton(onClick = { /* open drawer */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { /* search */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* action */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* navigate */ },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /* navigate */ },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                    label = { Text("Favorites") }
                )
            }
        }
    ) { paddingValues ->
        // Main content with padding to avoid overlaps
        Box(modifier = Modifier.padding(paddingValues)) {
            Text("Content goes here")
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// BOTTOM SHEETS - Secondary content anchored to bottom
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExample() {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Bottom Sheet Title", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(16.dp))
                Text("This is the content of the bottom sheet")
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = { showBottomSheet = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Close")
                }
            }
        }
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                            4. NAVIGATION
═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// APP BARS - Top of screen for navigation
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarExamples() {
    Column {
        // Top App Bar - Standard
        TopAppBar(
            title = { Text("Title") },
            navigationIcon = {
                IconButton(onClick = { /* back */ }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { /* action */ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
                }
            }
        )

        // Center Aligned Top App Bar
        CenterAlignedTopAppBar(
            title = { Text("Centered Title") },
            navigationIcon = {
                IconButton(onClick = { /* menu */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            }
        )

        // Medium Top App Bar - with collapsing effect
        MediumTopAppBar(
            title = { Text("Medium Title") },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        )

        // Large Top App Bar - larger with collapsing
        LargeTopAppBar(
            title = { Text("Large Title") },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// NAVIGATION BAR - Bottom navigation for small devices
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun NavigationBarExample() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Search", "Profile")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Person)

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// NAVIGATION RAIL - Side navigation for mid-size devices (tablets)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun NavigationRailExample() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Search", "Settings")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings)

    NavigationRail {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// NAVIGATION DRAWER - Drawer for larger devices
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun NavigationDrawerExample() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Inbox", "Sent", "Drafts", "Trash")
    val icons = listOf(Icons.Filled.Email, Icons.Filled.Send, Icons.Filled.Edit, Icons.Filled.Delete)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        icon = { Icon(icons[index], contentDescription = null) },
                        label = { Text(item) },
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
                            // Close drawer after selection
                        }
                    )
                }
            }
        }
    ) {
        // Main content
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TABS - Organize content across different screens
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TabsExample() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")

    Column {
        // Primary Tabs
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        // Scrollable Tabs - for many tabs
        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) },
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) }
                )
            }
        }
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                            5. SELECTION
═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// CHECKBOX - Select one or more items, or toggle on/off
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun CheckboxExamples() {
    var checked by remember { mutableStateOf(false) }
    var triState by remember { mutableStateOf(ToggleableState.Indeterminate) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Simple Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
            Text("Accept terms and conditions")
        }

        // Tri-state Checkbox - checked, unchecked, indeterminate
        TriStateCheckbox(
            state = triState,
            onClick = {
                triState = when (triState) {
                    ToggleableState.On -> ToggleableState.Off
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                }
            }
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// CHIPS - Enter information, make selections, filter, trigger actions
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipExamples() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Assist Chip - triggers actions
        AssistChip(
            onClick = { /* action */ },
            label = { Text("Assist Chip") },
            leadingIcon = { Icon(Icons.Filled.Add, contentDescription = null) }
        )

        // Filter Chip - filter content
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Filter Chip") },
            leadingIcon = if (selected) {
                { Icon(Icons.Filled.Check, contentDescription = null) }
            } else null
        )

        // Input Chip - represents user input (like tags)
        InputChip(
            selected = false,
            onClick = { /* action */ },
            label = { Text("Input Chip") },
            trailingIcon = {
                IconButton(onClick = { /* remove */ }) {
                    Icon(Icons.Filled.Close, contentDescription = "Remove", modifier = Modifier.size(16.dp))
                }
            }
        )

        // Suggestion Chip - suggest options
        SuggestionChip(
            onClick = { /* action */ },
            label = { Text("Suggestion Chip") }
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DATE PICKER - Select a date or range
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerExample() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// MENUS - Display list of choices on temporary surface
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun MenuExample() {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Filled.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = { expanded = false },
                leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Share") },
                onClick = { expanded = false },
                leadingIcon = { Icon(Icons.Filled.Share, contentDescription = null) }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = { expanded = false },
                leadingIcon = { Icon(Icons.Filled.Delete, contentDescription = null) }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// RADIO BUTTON - Select one option from a set
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun RadioButtonExample() {
    val options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.padding(16.dp)) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { selectedOption = option }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SLIDERS - Make selections from range of values
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun SliderExamples() {
    var sliderValue by remember { mutableStateOf(0.5f) }
    var rangeSliderValue by remember { mutableStateOf(0.3f..0.7f) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Standard Slider
        Text("Volume: ${(sliderValue * 100).toInt()}%")
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it }
        )

        // Slider with steps
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            steps = 9, // Creates 11 values (0-10)
            valueRange = 0f..1f
        )

        // Range Slider - select a range
        Text("Range: ${(rangeSliderValue.start * 100).toInt()}% - ${(rangeSliderValue.endInclusive * 100).toInt()}%")
        RangeSlider(
            value = rangeSliderValue,
            onValueChange = { rangeSliderValue = it },
            valueRange = 0f..1f
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SWITCH - Toggle selection on/off
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun SwitchExample() {
    var checked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Spacer(Modifier.width(8.dp))
        Text("Enable notifications")
    }

    // Switch with custom thumb icon
    Switch(
        checked = checked,
        onCheckedChange = { checked = it },
        thumbContent = if (checked) {
            { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
        } else null
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// TIME PICKER - Select and set specific time
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerExample() {
    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(onClick = { showTimePicker = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) {
                    Text("Cancel")
                }
            },
            text = {
                TimePicker(state = timePickerState)
            }
        )
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                            6. TEXT INPUTS
═══════════════════════════════════════════════════════════════════════════════
*/

// ─────────────────────────────────────────────────────────────────────────────
// SEARCH - Enter keyword/phrase for relevant information
// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarExample() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = remember { mutableListOf("Previous search 1", "Previous search 2") }

    SearchBar(
        query = query,
        onQueryChange = { query = it },
        onSearch = {
            searchHistory.add(query)
            active = false
            // Perform search
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text("Search...") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { query = "" }) {
                    Icon(Icons.Filled.Close, contentDescription = "Clear")
                }
            }
        }
    ) {
        // Search suggestions when active
        searchHistory.forEach { item ->
            ListItem(
                headlineContent = { Text(item) },
                leadingContent = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier.clickable {
                    query = item
                    active = false
                }
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TEXT FIELDS - Enter text into UI
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun TextFieldExamples() {
    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Filled TextField (default)
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            placeholder = { Text("Enter text") }
        )

        // Outlined TextField
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Outlined") },
            supportingText = { Text("Supporting text") }
        )

        // TextField with icons
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { text = "" }) {
                    Icon(Icons.Filled.Close, contentDescription = "Clear")
                }
            }
        )

        // Password field
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.Lock,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )

        // TextField with error
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                isError = it.length < 3
            },
            label = { Text("Username") },
            isError = isError,
            supportingText = {
                if (isError) {
                    Text("Username must be at least 3 characters")
                }
            }
        )
    }
}

/*
═══════════════════════════════════════════════════════════════════════════════
                        IMPORTANT CONCEPTS & NOTES
═══════════════════════════════════════════════════════════════════════════════

1. MATERIAL DESIGN 3 (Material You)
   - Dynamic color scheme based on user's wallpaper
   - More rounded corners and spacious layouts
   - Enhanced personalization

2. COMPONENT VARIANTS
   Most components have multiple variants:
   - Filled, Outlined, Tonal, Elevated
   - Choose based on emphasis level needed

3. STATE MANAGEMENT IN COMPONENTS
   - Use remember { mutableStateOf() } for component state
   - State hoisting for reusable components
   - Pass callbacks up, data down

4. ACCESSIBILITY
   - Always provide contentDescription for icons
   - Use semantic properties for screen readers
   - Ensure adequate touch target sizes (48dp minimum)

5. THEMING
   All components respect MaterialTheme:
   - colorScheme - colors
   - typography - text styles
   - shapes - corner rounding

6. SCAFFOLD ARCHITECTURE
   - Central component for complex UIs
   - Provides slots for app bars, FAB, snackbar, drawer, bottom bar
   - Handles padding automatically to prevent overlaps

7. NAVIGATION COMPONENTS
   - NavigationBar - for phones (bottom)
   - NavigationRail - for tablets (side)
   - NavigationDrawer - for large screens
   - Adaptive navigation based on screen size

8. SELECTION COMPONENTS DIFFERENCES
   - Checkbox: Multiple selections, on/off toggle
   - Radio Button: Single selection from group
   - Switch: Binary on/off state with immediate effect
   - Chips: Tags, filters, or quick selections

9. DIALOG TYPES
   - AlertDialog: Standard Material Design dialog
   - BasicAlertDialog: Custom content dialog
   - ModalBottomSheet: Dialog from bottom

10. PERFORMANCE TIPS
    - Use LazyColumn/LazyRow for long lists
    - Avoid creating unnecessary composables in loops
    - Use keys in lists for better recomposition
    - Extract static content outside of composables

═══════════════════════════════════════════════════════════════════════════════
