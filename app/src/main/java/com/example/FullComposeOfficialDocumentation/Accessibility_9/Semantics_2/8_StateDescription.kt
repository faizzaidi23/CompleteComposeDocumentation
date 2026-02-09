package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp

/*
State Description:

A composable can define a stateDescription for semantics which the Android framework uses to
read out the state that the composable is in.

For example, a toggleable composable can be in either a "checked" or an "unchecked" state.
In some cases, you might want to override the default state description labels that Compose uses.
*/

@Composable
fun TopicItem(
    itemTitle: String,
    selected: Boolean,
    onToggle: () -> Unit
) {
    val stateSubscribed = "Subscribed"
    val stateNotSubscribed = "Not subscribed"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                stateDescription = if (selected) stateSubscribed else stateNotSubscribed
            }
            .toggleable(
                value = selected,
                role = Role.Checkbox,
                onValueChange = { onToggle() }
            )
            .padding(16.dp)
    ) {
        Text(
            text = itemTitle,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = selected,
            onCheckedChange = null
        )
    }
}

/*
How it works:

You can explicitly specify the state description labels before defining a composable as toggleable.
Instead of TalkBack announcing "Checked" or "Unchecked", it will announce:
- "Subscribed" when selected is true
- "Not subscribed" when selected is false

This provides more context-specific feedback to users.
*/

@Composable
fun TopicListExample() {
    var topic1Selected by remember { mutableStateOf(false) }
    var topic2Selected by remember { mutableStateOf(true) }

    TopicItem(
        itemTitle = "Android Development",
        selected = topic1Selected,
        onToggle = { topic1Selected = !topic1Selected }
    )

    TopicItem(
        itemTitle = "Compose UI",
        selected = topic2Selected,
        onToggle = { topic2Selected = !topic2Selected }
    )
}

/*
Benefits:

- More meaningful state descriptions for users
- Context-specific feedback instead of generic "checked/unchecked"
- Better user experience for accessibility services
- Clearer communication of the component's purpose
*/

@Composable
fun NotificationToggle(
    enabled: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                stateDescription = if (enabled) "Notifications enabled" else "Notifications disabled"
            }
            .toggleable(
                value = enabled,
                role = Role.Switch,
                onValueChange = { onToggle() }
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Push Notifications",
            modifier = Modifier.weight(1f)
        )
    }
}

/*
Use cases:

- Subscription toggles
- Settings switches
- Feature enable/disable
- Any toggleable state that benefits from custom descriptions
*/

