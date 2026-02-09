package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
Alerts and Pop-ups:

If your component is an alert or a pop up, like a Snackbar, you might want to signal to
accessibility services that a new structure or updates to content can be conveyed to users.

Alert-like components can be marked with the liveRegion semantics property. This allows
accessibility services to automatically notify the user of changes to this component, or its
children.
*/

@Composable
fun PopupAlert(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(16.dp)
            .semantics {
                liveRegion = LiveRegionMode.Polite
            }
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onErrorContainer,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
LiveRegionMode.Polite:

You should use liveRegionMode.Polite in most cases where users' attention should only briefly
be drawn to alerts or important changing content on-screen.

This mode waits for a natural pause in speech before announcing the change.
*/

@Composable
fun PoliteAlertExample() {
    PopupAlert(
        message = "You have a new message",
        modifier = Modifier.semantics {
            liveRegion = LiveRegionMode.Polite
        }
    )
}

/*
LiveRegionMode.Assertive:

You should use liveRegion.Assertive sparingly to avoid disruptive feedback. It should be used
for situations where it's crucial that users are made aware of time-sensitive content.

This mode interrupts any ongoing speech to announce the change immediately.
*/

@Composable
fun AssertiveAlertExample() {
    PopupAlert(
        message = "Emergency alert incoming",
        modifier = Modifier.semantics {
            liveRegion = LiveRegionMode.Assertive
        }
    )
}

/*
Important Notes:

- Live regions shouldn't be used on content that updates frequently, such as countdown timers,
  to avoid overwhelming users with constant feedback.

- Use Polite mode for most notifications and alerts
- Use Assertive mode only for critical, time-sensitive information
- Avoid using live regions on rapidly changing content
*/

