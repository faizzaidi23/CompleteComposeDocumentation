package com.example.FullComposeOfficialDocumentation.Accessibility_9.MergingAndClearing_5

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.foundation.selection.toggleable
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.semantics.toggleableState

/*
CLEAR AND SET SEMANTICS

If semantic information needs to be completely cleared or overwritten, a powerful
API to use is clearAndSetSemantics.

WHEN TO USE:
- When a component needs its own and descendant semantics cleared
- When semantics must be overwritten with custom information
- When creating custom components with specific accessibility needs
- When merging is not sufficient and you need to provide additional information

HOW IT WORKS:
1. Clear semantics: Use with an empty lambda
   clearAndSetSemantics {}

2. Overwrite semantics: Include new content inside the lambda
   clearAndSetSemantics { /* new semantic information */ }

IMPORTANT NOTES:
- When clearing with empty lambda: No semantics are sent to ANY consumer
  (accessibility, autofill, testing)
- When overwriting: New semantics replace ALL previous semantics of the
  element and its descendants
- Use sparingly: Services receive no original information when this API is set
- Ordering matters: This API clears all semantics that are after where it is applied,
  regardless of other merging strategies
- Semantics info can be used by AI agents and similar services to understand the screen,
  so only clear when necessary
*/

/*
Example: Custom toggle component
A row with an icon and text that acts as a toggleable element

THE PROBLEM:
Although the icon and text have some semantic information, together they don't
indicate that this component is a toggleable. Merging is not sufficient because
we must provide additional information about the component.

THE SOLUTION:
Use clearAndSetSemantics to:
1. Clear the original semantics (icon + text)
2. Set new semantics that properly describe this as a toggleable component
3. Add stateDescription, toggleableState, and role
*/

@Composable
fun FavoriteToggle() {
    val checked = remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            /*
            First, make the row toggleable with the toggleable modifier
            This provides the basic interaction behavior
            */
            .toggleable(
                value = checked.value,
                onValueChange = { checked.value = it }
            )
            /*
            Then, clear original semantics and set custom ones
            This ensures accessibility services understand this is a toggle/switch
            */
            .clearAndSetSemantics {
                /*
                stateDescription: Describes the current state in human-readable form
                Better than just "On/Off" - provides context
                */
                stateDescription = if (checked.value) "Favorited" else "Not favorited"

                /*
                toggleableState: Indicates this is a toggleable component
                and its current state (on/off)
                */
                toggleableState = ToggleableState(checked.value)

                /*
                role: Tells accessibility services what type of component this is
                Role.Switch makes TalkBack announce "Double tap to toggle"
                instead of the generic "Double tap to activate"
                */
                role = Role.Switch
            },
    ) {
        /*
        Icon semantics are cleared by clearAndSetSemantics above
        No need for contentDescription here - it would be ignored anyway
        */
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null
        )

        /*
        Text semantics are also cleared by clearAndSetSemantics above
        The text is still visible but its semantics are replaced
        */
        Text("Favorite?")
    }
}

/*
RESULT:
By clearing the original semantics and setting new, more descriptive ones,
accessibility services can now see that this is a toggleable component
that can alternate state.

ACCESSIBILITY ANNOUNCEMENT (TalkBack):
When focused: "Favorited, Switch, Double tap to toggle"
or
When focused: "Not favorited, Switch, Double tap to toggle"

COMPARISON:
WITHOUT clearAndSetSemantics:
- TalkBack might announce: "Favorite icon, Favorite?, Double tap to activate"
- Users don't know it's toggleable or what state it's in

WITH clearAndSetSemantics:
- TalkBack announces: "Favorited, Switch, Double tap to toggle"
- Clear indication it's a toggleable switch with current state
*/

/*
CONSIDERATIONS WHEN USING clearAndSetSemantics:

1. Use sparingly - only when necessary
   Because services receive no information when this API is set

2. Custom semantics may be set within the API lambda
   You can add any relevant semantics properties

3. Ordering of modifiers matters
   This API clears all semantics that are after where it is applied

4. Think about all use cases
   - Accessibility services (TalkBack, Switch Access)
   - Testing framework
   - Future AI agents
   - Autofill services
*/

