package com.example.FullComposeOfficialDocumentation.Components_3.NavigationDrawer_16

/*
Navigation drawer component is a slide-in menu that lets users navigate to various sections of the app.
Users can activate it by swiping from the side or tapping a menu icon.

Use cases:
- Content organization: Switch between different categories (news, blogging apps)
- Account management: Quick links to account settings and profile sections
- Feature discovery: Organize multiple features and settings in complex apps

Types of navigation drawers:
- Standard: Share space within a screen with other content
- Modal: Appears over the top of other content within a screen

API Surface:
- ModalNavigationDrawer: Main composable for implementing navigation drawer
- ModalDrawerSheet: Provides Material Design styling for drawer content
- NavigationDrawerItem: Individual items in the drawer
- DrawerState: Controls drawer open/close behavior
- rememberDrawerState(): Creates and remembers drawer state

Key Parameters:
- drawerContent: Slot to provide drawer's contents
- drawerState: Controls drawer behavior
- gesturesEnabled: Toggle whether drawer responds to drags
*/