package com.example.FullComposeOfficialDocumentation.Theming_4.MaterialDesign2_3

/**
 * Scaffold in Material Design 2
 *
 * NOTE: This is conceptual documentation. Requires M2 dependency to compile.
 * implementation "androidx.compose.material:material:$version"
 *
 * Scaffold provides the basic Material Design layout structure:
 * - topBar: Top app bar
 * - bottomBar: Bottom app bar or navigation
 * - floatingActionButton: FAB
 * - drawerContent: Navigation drawer (removed in M3)
 * - backgroundColor: Background color (renamed in M3)
 * - scaffoldState: State for snackbars and drawer (changed in M3)
 *
 * Scaffold handles proper positioning and padding automatically
 *
 * EXAMPLE CODE (requires M2 dependency):
 *
 * @Composable
 * fun M2ScaffoldExample() {
 *     var selectedItem by rememberSaveable { mutableIntStateOf(0) }
 *     val items = listOf("Home", "Favorites", "Settings")
 *     val icons = listOf(Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Settings)
 *
 *     Scaffold(
 *         topBar = {
 *             TopAppBar(
 *                 title = { Text("M2 Scaffold") },
 *                 backgroundColor = MaterialTheme.colors.primarySurface,
 *                 contentColor = MaterialTheme.colors.onPrimary
 *             )
 *         },
 *         bottomBar = {
 *             BottomNavigation {
 *                 items.forEachIndexed { index, item ->
 *                     BottomNavigationItem(
 *                         icon = { Icon(icons[index], contentDescription = item) },
 *                         label = { Text(item) },
 *                         selected = selectedItem == index,
 *                         onClick = { selectedItem = index }
 *                     )
 *                 }
 *             }
 *         },
 *         backgroundColor = MaterialTheme.colors.background
 *     ) { paddingValues ->
 *         Column(modifier = Modifier.padding(paddingValues)) {
 *             Text("Scaffold Content")
 *         }
 *     }
 * }
 *
 * M2 to M3 Migration:
 * - backgroundColor -> containerColor
 * - ScaffoldState -> SnackbarHostState (for snackbars)
 * - drawerContent -> Use ModalNavigationDrawer as separate wrapper
 * - BottomNavigation -> NavigationBar (renamed)
 */
