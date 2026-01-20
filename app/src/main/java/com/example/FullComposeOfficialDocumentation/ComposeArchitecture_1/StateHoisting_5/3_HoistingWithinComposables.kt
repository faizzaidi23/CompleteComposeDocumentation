package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.StateHoisting_5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
This concept means that when multiple, separate parts of your screen
need to control or read the same piece of UI state,
you must lift that state up to their first shared parent.
*/

data class Messages(val id:Int,val text: String)

@Composable
fun ConversationScreen(){
    val scope=rememberCoroutineScope()
    val lazyListState=rememberLazyListState()

    val message=remember{ mutableStateListOf(
        Messages(1,"hi"),
        Messages(2,"Hello"),
        Messages(3,"Kaise ho")
    ) }

    Column{

        MessagesList(
            messages = message,
            lazyListState = lazyListState,
            modifier = Modifier.weight(1f) // Make the list take up most of the space
        )


        UserInput(
            onMessageSent = { text ->
                // Add the new message to the list
                message.add(Messages(message.size + 1, text))
                // UI Logic: Scroll to the new message at the bottom of the list
                scope.launch {
                    lazyListState.animateScrollToItem(message.size - 1)
                }
            }
        )

    }
}

@Composable
private fun MessagesList(
    messages: List<Messages>,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.weight(1f)
        ) {
            // Display the actual messages
            items(messages, key = { it.id }) { message ->
                Text(message.text, modifier = Modifier.padding(16.dp))
            }
        }

        // The JumpToBottom button is part of this child
        JumpToBottom(
            onClicked = {
                // UI Logic: Scroll to the last item when clicked
                scope.launch {
                    lazyListState.animateScrollToItem(messages.size - 1)
                }
            }
        )
    }
}

@Composable
fun JumpToBottom(onClicked: () -> Unit) {
    Button(onClick = onClicked, modifier = Modifier.padding(16.dp)) {
        Text("Jump to Bottom")
    }
}

@Composable
fun UserInput(onMessageSent: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f)
        )
        Button(onClick = {
            onMessageSent(text)
            text = "" // Clear the text field after sending
        }) {
            Text("Send")
        }
    }
}


// Above code is for the send messages only if I want to create a chatbot in which  the layout of both the sent and the received message are same then


/*
data class Message(
    val id: Int,
    val text: String,
    val isFromMe: Boolean // Add this property
)


@Composable
private fun MessagesList(messages: List<Message>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(messages, key = { it.id }) { message ->
            // This is the key logic
            val alignment = if (message.isFromMe) Alignment.End else Alignment.Start
            val backgroundColor = if (message.isFromMe) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer

            // Use a Box to control the alignment of the chat bubble
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = alignment
            ) {
                // The chat bubble itself
                Text(
                    text = message.text,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(backgroundColor)
                        .padding(12.dp)
                )
            }
        }
    }
}
*/



/*
Where can I use this pattern

You use this pattern whenever multiple UI components on the same screen need to coordinate by reading or changing the same piece of UI-specific state. It's for when the state is more complex than a single element needs but not important enough to live in the ViewModel.

The core idea is to move the state up to the first parent that can "see" all the children that need to interact with it.

## 1. Coordinated List Scrolling (The Chat App Example)
This is the classic case you've already seen.

Scenario: A chat screen has a list of messages, a "Jump to Bottom" button, and a text input field that auto-scrolls when a new message is sent.

The Shared State: The scroll position, managed by LazyListState.

The Components:

The LazyColumn (reads the state to position itself).

The JumpToBottom button (writes to the state to change the scroll position).

The UserInput area (writes to the state to scroll after sending a message).

Why Hoist: If the LazyColumn kept the state to itself, the button and the input field would have no way to tell it to scroll. Hoisting the LazyListState to the parent ConversationScreen allows it to be shared and controlled by all three children.

## 2. Interactive Media Player Controls
Scenario: A video or music player screen.

The Shared State: The current playback position (e.g., currentTime: Float) and the playing status (isPlaying: Boolean).

The Components:

A Slider that shows and seeks the playback position.

A Text element displaying the current time (e.g., "1:32 / 3:45").

A Play/Pause Button.

Why Hoist: All three components must be perfectly in sync. The Slider's position must match the Text display, and both must update when the video plays. The Play/Pause button needs to read the isPlaying state to show the correct icon and write to it to start or stop playback. The parent VideoPlayer composable owns this state and distributes it to the controls.

## 3. Complex Forms with Interdependent Fields
Scenario: A form where one selection affects another. For example, a "Shipping Address" form with a checkbox that says "Use billing address".

The Shared State: The state of the checkbox (isBillingAddressSame: Boolean).

The Components:

The Checkbox itself.

The address input TextFields (Street, City, Zip Code).

Why Hoist: When the checkbox is checked, the address fields need to become disabled and be populated with data from the billing address. The parent Form composable needs to know the state of the checkbox so it can enable/disable and change the text in the other fields.

## 4. Custom UI Components with Multiple Parts
Scenario: You build a custom, interactive map component. It has the map view itself, plus separate "+" and "-" zoom buttons.

The Shared State: The current zoom level and camera position (cameraState).

The Components:

The main MapView composable.

The ZoomInButton (+).

The ZoomOutButton (-).

Why Hoist: The zoom buttons don't control the map directly. They simply tell their parent, "increase the zoom level" or "decrease the zoom level." The parent InteractiveMap composable owns the cameraState and is responsible for actually applying the zoom change to the MapView.

## 5. Multi-Step Wizards or Onboarding Flows
Scenario: An onboarding screen that takes the user through three steps.

The Shared State: The current step index (currentStep: Int).

The Components:

The content area that displays the UI for the current step.

A "Next" button.

A "Previous" button.

A step indicator (e.g., "Step 1 of 3").

Why Hoist: The "Next" and "Previous" buttons need to change the currentStep. The content area and the step indicator need to read the currentStep to know what to display. The parent Wizard composable must own the currentStep state to coordinate all these elements
*/