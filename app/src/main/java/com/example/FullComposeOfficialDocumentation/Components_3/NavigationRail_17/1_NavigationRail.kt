package com.example.FullComposeOfficialDocumentation.Components_3.NavigationRail_17

- Large screen devices (tablets, desktops)
Navigation Rails provide access to destinations in apps that run on devices with large screens.
- Persistent navigation access
API Surface:
- NavigationRail: Main composable with NavigationRailItem to implement a rail in the application
- NavigationRailItem: Represents a single rail item in the rail column

NavigationRailItem key parameters:
- selected: Determines whether the current rail item is visually highlighted
- onClick(): Required lambda function that defines the action when user clicks the rail item
- label: Displays text within the rail item (optional)
- icon: Displays an icon within the rail item (optional)

Use NavigationRail for:
- Large screen devices (tablets, desktops)
- Apps with 3-7 top-level destinations
- Persistent navigation access

