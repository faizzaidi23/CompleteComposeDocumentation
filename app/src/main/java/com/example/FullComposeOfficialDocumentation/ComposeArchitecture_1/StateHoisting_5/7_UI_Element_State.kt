package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.StateHoisting_5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

/*
Normally, you keep UI state (like TextField text) in the Composable using rememberSaveable. But if your ViewModel needs to read that text in real-time to perform a business task (like fetching suggestions from a server), you must hoist that state all the way up to the ViewModel.

The ViewModel becomes the "owner" of the TextField's state because that state is critical for its job.


*/

/*
Continuing the example of a chat app, the app displays user suggestions in a
group chat when the user types @ and a hint. Those suggestions come from the
data layer and the logic to calculate a list of user suggestions
is considered business logic. The feature looks like this:


*/

data class Suggestion(val name: String)

class FakeRepository{
    private val users=listOf("andrea","andrew","andy","john")

    fun getSuggestion(query:String):List<Suggestion>{
        if(query.isEmpty())return emptyList()
        return users.filter{it.startsWith(query, ignoreCase = true)}
            .map{ Suggestion(it) }
    }
}


@OptIn(FlowPreview::class)

class ConversationViewModels(private val repository: FakeRepository):ViewModel(){
    var inputMessage by mutableStateOf("") // since we are not inside the composable function so we do not have to use remember here we have to use remember in the composable function if we want to remember that variable across recomposition
        private set /*it means
                      public get--> Anyone outside the viewModel can read the value of inputMessage.
                      private set-->Only code inside the viewModel class is allowed to change the value of the inputMessage*/

    val suggestions:StateFlow<List<Suggestion>> =
        snapshotFlow{inputMessage}/*It converts a compose state variable i.e inputMessage into a kotlin flow. A flow is a stream of data that emits a new value every time the original state changes.
                                    The purpose is to allow the viewModel's non UI logic to react to changes happening in the UI staate*/
            .filter{it.startsWith("@") && it.length>1}
            .debounce(300) // waits for the user to stop typing for 300ms before it proceeds. if the user types a new character within that time the timer resets
            .distinctUntilChanged() // it prevents the flow from continuing if the new value is the same  as the previous one
            .mapLatest { text->   /*
                                    This is where the actual work happens. It takes the final, validated text,
                                     performs a task, and transforms it into a new result.
                                     The Latest part is important: if a new piece of text comes
                                      in while it's still working on the old one,
                                      it cancels the old work and starts fresh with the latest text.*/
                val query=text.substring(1)
                repository.getSuggestion(query)
            }.stateIn(  // it converts the final stream of List<Suggestion>  into a StateFlow.
                        // A stateFlow is a special type of Flow that always holds the latest value
                // and efficiently shares it with any UI component that are listening
                scope=viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )


    fun updateInput(newInput:String){
        inputMessage=newInput
    }
}

// The UI part

@Composable
fun SuggestionScreen(
    viewModel: ConversationViewModels
){
    val inputMessage = viewModel.inputMessage

    val suggestions by viewModel.suggestions.collectAsState()

    Column(modifier=Modifier.padding(16.dp)){
        TextField(
            value=inputMessage,
            onValueChange = {viewModel.updateInput(it)},
            modifier=Modifier.fillMaxWidth(),
            label={Text("Type @ to get suggestions")}
        )

        //The list of suggestions is displayed based on the ViewModel state

        if(suggestions.isNotEmpty()){
            LazyColumn {
                items(suggestions){ suggestion->
                    Text(
                        text=suggestion.name,
                        modifier=Modifier.fillMaxWidth()
                            .padding(vertical=8.dp)

                    )
                }
            }
        }
    }
}