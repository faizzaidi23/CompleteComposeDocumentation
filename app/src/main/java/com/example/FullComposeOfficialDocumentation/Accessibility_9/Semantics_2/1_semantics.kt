package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/*
Semantics Overview:

Aside from the primary information that a composable carries, like a text string of a Text
composable, it can be helpful to have more supplemental information about UI elements.

Information about the meaning and role of a component in Compose is called semantics, which are
a way to provide additional context about composables to services like accessibility, autofill,
and testing. For example, a camera icon might visually just be an image, but the semantic meaning
could be "Take a photo".

By combining the appropriate semantics with the appropriate Compose APIs, you provide as much
information about your component as possible to accessibility services, which then decide how to
represent it to the user.

Material and Compose UI and Foundation APIs come with built-in semantics that follow their
specific role and function, but you can also modify these semantics for existing APIs or set new
ones for custom components, according to your specific requirements.
*/

/*
Semantic properties convey the meaning of the corresponding composable. For example, the Text
composable contains a semantic property text, because that's the meaning of that composable.
An Icon contains a contentDescription property (if set by the developer) that conveys in text
what the meaning of the icon is.
*/

/*
Consider how semantics properties convey the meaning of a composable. Consider a Switch.
This is how it looks to the user: a toggleable element in its "On" or "Off" state.

To describe the meaning of this element, you could say:
"This is a Switch, which is a toggleable element in its 'On' state. You can click it to interact with it."

This is exactly what the semantics properties are used for. The semantics node of this Switch
element contains the following properties:

- Role: indicates the type of element
- StateDescription: describes how the "On" state should be referenced
- ToggleableState: the current state of the Switch
- OnClick: references the method used to interact with this element

Keeping track of the semantics properties of each composable in your app unlocks powerful possibilities:

1. Accessibility services use the properties to represent UI shown on the screen and allow users
   to interact with it. For the Switch composable, TalkBack might announce: "On; Switch; double
   tap to toggle". The user can double tap their screen to toggle the Switch off.

2. The Testing framework uses the properties to find nodes, interact with them, and make assertions
*/

@Composable
fun BasicSwitchExample() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}

/*
The Switch automatically has semantics properties set:
- Role.Switch
- StateDescription (On/Off)
- ToggleableState
- OnClick action

Accessibility services like TalkBack will announce: "On; Switch; double tap to toggle"
*/