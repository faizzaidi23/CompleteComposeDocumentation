package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.StateHoisting_5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class Messsage(val id:Int, val text: String)

class MessageRepository{
    private val _messages=MutableStateFlow(
        listOf(
            Messsage(1,"Hello"),
            Messsage(2,"How are you doing")
        )
    )

    val messages:StateFlow<List<Messsage>> = _messages.asStateFlow()

    fun addMessage(messageText:String){
        _messages.update {
            thisList->
            val newId=(thisList.maxOfOrNull{it.id}?:0)+1
            thisList+Messsage(newId,messageText)
        }
    }
}

class ConversationViewModel(private val messagesRepository: MessageRepository) : ViewModel() {

    // Exposes the list of messages from the repository as Screen UI State.
    val messages  = messagesRepository.messages

    // Business Logic: The function to send a new message.
    // It delegates the actual work to the repository.
    fun sendMessage(messageText: String) {
        viewModelScope.launch {
            messagesRepository.addMessage(messageText)
        }
    }
}


@Composable
fun ConversationScreen(
    // The viewModel() function gets the correct ViewModel instance for this screen.
    conversationViewModel: ConversationViewModel = viewModel {
        // This is a simple way to provide the repository to the ViewModel for this example.
        // In a real app, you would use a dependency injection library like Hilt.
        ConversationViewModel(MessageRepository())
    }
) {
    // Collect the state from the ViewModel in a lifecycle-aware way.
    val messages by conversationViewModel.messages.collectAsState()

    // Call the stateless version of the screen, passing down the state
    // and passing up the events.
    StatelessConversationScreen(
        messages = messages,
        onSendMessage = { text -> conversationViewModel.sendMessage(text) }
    )
}
@Composable
fun StatelessConversationScreen(
    messages: List<Messsage>,
    onSendMessage: (String) -> Unit
) {
    Column {
        // The list of messages takes up most of the screen.
        MesssagesList(messages = messages, modifier = Modifier.weight(1f))

        // The input area is at the bottom.
        UserInputs(onSendMessage = onSendMessage)
    }
}

// A simple composable to display the list of messages.
@Composable
fun MesssagesList(messages: List<Messsage>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(messages, key = { it.id }) { message ->
            Text(text = message.text, modifier = Modifier.padding(16.dp))
        }
    }
}

// A simple composable for the text field and send button.
@Composable
fun UserInputs(onSendMessage: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    Row(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f)
        )
        Button(onClick = {
            onSendMessage(text)
            text = "" // Clear the field after sending
        }) {
            Text("Send")
        }
    }
}



