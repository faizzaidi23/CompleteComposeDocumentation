package com.example.FullComposeOfficialDocumentation.Components_3.RadioButton_20

/*
Radio button lets a user select an option from a set of options.
Use radio button when only one item can be selected from a list.
If users need to select more than one item, use a switch instead.

API Surface:
- RadioButton: Main composable to create radio button options

Key Parameters:
- selected: Indicates whether the radio button is selected
- onClick: Lambda function executed when user clicks the radio button (null for accessibility)
- enabled: Controls whether radio button is enabled or disabled
- interactionSource: Observe interaction state (pressed, hovered, focused)

Important Modifiers:
- Modifier.selectableGroup(): Essential for correct accessibility behavior on parent container
- Modifier.selectable(): Makes entire Row act as single selectable item with role = Role.RadioButton

Best Practices:
- Set onClick = null on RadioButton for better screen reader support
- Use Modifier.selectable() on the Row instead
- Always wrap radio button group in Modifier.selectableGroup()
*/

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RadioButtoonSingleSelection(modifier: Modifier = Modifier) {
    val radioOptions = listOf("Calls", "Missed", "Friends")

}