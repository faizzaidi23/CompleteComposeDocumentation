package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.ManagingStates_4

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/*
CollectAsStateWithLifeCycle---> is the recommended way to turn a data stream i.e Flow into a Compose State that the UI can display

Think of Flow as a live news feed from the ViewModel. It constantly pushes out new updates i.e a new list of users a loading status an error message

collectAsStateWithLifecycle is the smart TV receiver that tunes into this news feed.

It takes the latest update from the feed (Flow) and displays it on the screen (State).

It's "lifecycle-aware," which means it's smart about saving battery.
When you put the app in the background, it stops listening to the feed.
When you bring the app back, it automatically starts listening again.
This prevents unnecessary work and potential app crashes.
*/

data class TaskUiState(
    val tasks:List<String> = emptyList(),
    val isLoading:Boolean=false
)

class TasksViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(TaskUiState(isLoading = true))
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            delay(2000)
            _uiState.value= TaskUiState(tasks=listOf("Buy Milk","Walk the dog"))

        }
    }
}

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel){
    val uiState by tasksViewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        if(uiState.isLoading){
            CircularProgressIndicator()
        }else{
            Text(text=uiState.tasks.joinToString("\n"))
        }
    }
}

/*
I defined the type explicitly with = to make it clear what the function actually returns. When you use by, the type is inferred differently, and you usually don't need to write it out.

Here’s the breakdown:

With by (Property Delegation)
Kotlin

// The type of 'uiState' is inferred as 'TasksUiState'
val uiState by tasksViewModel.uiState.collectAsStateWithLifecycle()
When you use the by keyword, you are telling Kotlin: "Don't give me the State container itself. I want you to delegate the 'get' access directly to the .value inside it."

Because of this delegation, the variable uiState behaves as if it is the TasksUiState object. The compiler infers its type as TasksUiState, not State<TasksUiState>.

With = (Standard Assignment)
Kotlin

// The type of 'uiState' is 'State<TasksUiState>'
val uiState: State<TasksUiState> = tasksViewModel.uiState.collectAsStateWithLifecycle()
When you use the = sign, you are performing a standard variable assignment. The collectAsStateWithLifecycle() function returns an object of type State<TasksUiState>, and you are simply storing that object in the uiState variable.

In this case, uiState is the "box" itself. To get the TasksUiState data, you must manually open the box by accessing uiState.value.

I wrote out the type State<TasksUiState> explicitly to make it very clear
 what kind of object you are holding when you use the equals sign,
 which helps in understanding why you need the extra .value step.
 With the by keyword, this is all handled for you automatically,
 so explicitly stating the type is redundant.
*/


/*


STATE vs MUTABLESTATE when we define the things



What is the State<T> Type?
State<T> is a simple interface in Compose that represents a read-only, observable value holder.

Think of it as a sealed glass box 📦.

It holds a value: The <T> means it can hold a value of any type (String, Int, Boolean, etc.).

It's read-only: You can look inside the glass box to see the value using its single property: val value: T. You cannot change what's inside.

It's observable: Compose is constantly watching this box. If the value inside it ever changes, Compose knows it needs to redraw (recompose) any UI that was looking at it.

The MutableState<T> we've been using is just a more powerful version of State<T>. It's a glass box that you can open and change the value of because its value property is a var (variable) instead of a val (read-only value).

How to Define the Type for remember { mutableStateOf(...) }
You are exactly right; you can define the type explicitly, just like in the other example. You do it when using the standard assignment operator (=).

The function remember { mutableStateOf(...) } returns an object of type MutableState<T>.

Here’s how you would define it for a simple counter:

Kotlin

@Composable
fun Counter() {
    // Here, we explicitly define the type of the 'count' variable.
    // It is a MutableState that holds an Int.
    val count: MutableState<Int> = remember { mutableStateOf(0) }

    Button(
        onClick = {
            // To change the value, we must access the .value property
            // because 'count' is the state object (the box) itself.
            count.value++
        }
    ) {
        // To read the value, we also access .value
        Text("Count is: ${count.value}")
    }
}
As you can see, this is more verbose than using the by delegate,
which is why var count by remember { mutableStateOf(0) } is the preferred
 syntax. However, understanding that you are creating a MutableState<Int>
 object is key to understanding how Compose works under the hood.
*/

/*
You use the read-only State<T> to protect your data and control how it's changed. It's about making your code predictable.

Think of it like the control room for a TV news broadcast 📺.

MutableState<T> is the private control panel. Only the producer inside the control room can press the buttons to change what graphic appears on screen. This is kept private to prevent anyone from accidentally putting the wrong information on air.

State<T> is the public TV screen that viewers watch. The viewers (your UI) can see the information, but they can't reach through the screen and change it themselves.

Why This Is Necessary
In a real app, you don't want your UI (a Button's onClick, for example) to be able to directly write any value it wants into your app's central state. This would lead to chaos.

Instead, you follow a pattern called Unidirectional Data Flow:

A central place, like a ViewModel, holds the private, mutable state (e.g., private val _uiState = MutableStateFlow(...)).

It exposes a public, read-only version of that state to the UI (e.g., val uiState: StateFlow<T> = _uiState.asStateFlow()). When the UI collects this, it treats it as a read-only State<T>.

If the UI wants to make a change, it can't write to the state directly. It must call a function on the ViewModel (like viewModel.onLoginClicked()).

This pattern ensures that all state changes happen in one predictable place (the ViewModel), which makes your app much easier to debug and prevents a whole class of bugs. You are stating to the rest of your app:
*/
