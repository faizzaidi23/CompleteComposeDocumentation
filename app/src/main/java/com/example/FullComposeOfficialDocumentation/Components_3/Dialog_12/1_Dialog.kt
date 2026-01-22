package com.example.FullComposeOfficialDocumentation.Components_3.Dialog_12

/*
Dialog component displays dialog messages or requests user input on a layer above the main app content.
It creates an interruptive UI experience to capture user attention.

Use cases:
- Confirming user action (e.g., deleting a file)
- Requesting user input (e.g., in a to-do list app)
- Presenting a list of options for user selection

Types:
- AlertDialog: Convenient API for Material Design themed dialog
- Dialog: Basic composable for custom dialog layouts

AlertDialog Parameters:
- title: Text that appears along the top of the dialog
- text: Text that appears centered within the dialog
- icon: Graphic that appears at the top of the dialog
- onDismissRequest: Function called when user dismisses the dialog
- dismissButton: Composable that serves as the dismiss button
- confirmButton: Composable that serves as the confirm button

Dialog Parameters:
- onDismissRequest: Lambda called when user closes the dialog
- properties: DialogProperties for additional customization
*/
