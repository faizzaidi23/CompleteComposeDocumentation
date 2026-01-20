package com.example.a1_composedocumentationuiarchitecture.Life_Cycle_1


import android.util.Log

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/*
composition--> The current picture of your UI created by running the composable functions

This happens once when your UI is first shown... Initial composition


Recomposition-->Redrawing only the parts of UI that need updating  when some data changes instead of redrawing everything

This can happen 0 or many times while your UI is on the screen


Lifecycle of a composable--> Enter the composition i.e first time it is drawn
Recompose 0 or emore times i.e redraw because data changed

Leave the composition i.e removed from screen


Trigger for recomposition--> Usually when a State<T> value changes like a counter going from one to two

jetpack compose automatically tracks which composable are dreading that state and only updates those


If a composable is called multiple times, multiple instances are placed in the Composition. Each call has its own lifecycle in the Composition.

*/


@Composable
fun MyComposable() {
    Column {
        Text("Hello")   // there are two text composable and both of them have their own lifecycle in the composition
        Text("World")
    }
}


/*
2--->Anatomy of a composable function



"Anatomy of a composable in Composition" means we are looking inside how a composable lives and behaves inside the Composition —

Where it’s placed

How Compose identifies it (by call site)

How it’s kept or discarded during recomposition

How its “identity” is preserved

Call site-> Exact place in the code were you call a composable
compose uses this location to track and match UI elements across recompositions
*/


/*
In below example the call site is same for both the composable i.e preserving it will
keeps UI state intact across recomposition

Prevents unnecessary recomposition hence improving performance
*/


@Composable
fun LoginScreen(showError:Boolean){
    if(showError){
        LoginError()
    }
    LoginInput()
}

@Composable
fun LoginInput(){
    /*UI for username and password fields*/
}

@Composable
fun LoginError(){
    /*UI for an error message*/
}



// Add extra information to help smart recomposition

/*
This section is about how Jetpack Compose keeps
track of multiple copies of the same composable
when you call it inside a loop.
*/

/*
In below example when we are calling MovieOverView since the call site is same
compose uses the order in which they appear  i.e the index of the list to identify them

First iteration → first instance

Second iteration → second instance

And so on



This is good when we add movies at the end of the list that means it will not recompose for a very long time it will just add another composition for the added movie

but when the movie is added at the front of the list  or at the middle of the list or anywhere else

the composition starts from the beginning or from where the  change has occurred
*/

data class Movies(
    val id: Int,          // unique identifier
    val title: String     // you can add more properties
)

@Composable
fun MovieOverView(movie: Movies){
    /* some code*/
}

@Composable
fun MoviesScreen(movies:List<Movies>){
    Column{
        for(movie in movies){

            // MovieOverview composable are placed in Composition given its
            // index position in the for loop
             MovieOverView(movie)
        }
    }

}



/*
If items in a list move around or new ones are added, Compose can get confused and think they’re new,
causing unnecessary recomposition and resetting their state.
key() (or key in LazyColumn)
tells Compose “this item is uniquely identified by this value”,
so it can reuse the same composable for that item.
*/

@Composable
fun MovieList(movies:List<Movies>){
    Column(){}
    for(movie in movies){
        key(movie.id){ //// Use movie.id as unique identifier
            MovieOverView(movie)
        }
    }
}



/*

SKIPPING.......



Skipping is a performance optimization.
Compose is smart enough to say,
"Hey, the inputs for this function are exactly the same as last time.
I don't need to re-run it; I'll just skip it and leave the UI as it is."

*/


/*
Stable-->
An input type is considered stable if it follows these simple rules:

It's Honest: If you compare two instances with equals(), the result will always be the same.

It Communicates: If a public part of it changes, it must tell Compose.

Its Parts are Stable: All of its public properties must also be stable types.



The easiest way to be stable is to be immutable (unchangeable).

Automatically Stable :

Basic types like Int, Float, Boolean.

String.

Functions (lambdas).

data class where all properties are val and are also stable types.

Automatically Unstable :

Interfaces: Compose can't know what the final implementation will be. It might be an unstable class, so it plays it safe and assumes it's unstable.

Most regular classes with var (variable) properties.
*/



@Stable
interface UiState<T:Result<T>>{
    val value:T?
    val exception:Throwable?
}

/*
Without @Stable: UiState is an interface. Compose sees it and thinks, "This is an unknown, unstable type. If a composable takes UiState as a parameter, I must always recompose it, just in case something changed. No skipping allowed!" This can hurt performance.

With @Stable: You add the annotation. Now, Compose sees it and thinks, "Okay, the developer has promised me this UiState type is stable. If a composable gets a new UiState object that is equal to the old one, I can trust that nothing important has changed, and I will skip its execution."

In short, you use @Stable to manually mark a type as trustworthy, which allows Compose to perform its "smart recomposition" and skip unnecessary work, leading to a faster and smoother app.
*/




class UserViewModel : ViewModel() {

    // Private mutable state that can be updated within the ViewModel
    private val _uiState = MutableStateFlow(UserScreenState(users = emptyList()))

    // Public immutable state that the UI can observe
    val uiState = _uiState.asStateFlow()

    init {
        // Fetch users when the ViewModel is first created
        fetchUsers()
    }

    // Function to simulate fetching users from a network or database
    fun fetchUsers() {
        viewModelScope.launch {
            // 1. Set state to Loading
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, error = null)
            }

            // Simulate a network delay
            delay(2000)

            // 2. Update state with the result (Success)
            // In a real app, this data would come from an API call
            val fetchedUsers = listOf(
                User(id = 1, name = "Alice"),
                User(id = 2, name = "Bob"),
                User(id = 3, name = "Charlie")
            )

            _uiState.update {
                it.copy(isLoading = false, users = fetchedUsers)
            }

            // To simulate an error, you could do this instead:
            // _uiState.update {
            //     it.copy(isLoading = false, error = "Could not load users.")
            // }
        }
    }
}

// We need a 'copy' method for easy state updates, so let's make it a data class.
// Or, if we keep it as a regular class with @Stable, we'd create a copy method manually.
// Using a data class is generally easier.


class UserScreenState(
    val users:List<User>,
    val isLoading:Boolean=false,
    val error:String?=null
)

data class User(val id:Int,val name: String)

@Composable
fun UserProfileScreen(viewModel:UserViewModel){
    val uiState by viewModel.uiState.collectAsState()

    UserList(state=uiState)
}

@Composable
fun UserList(state: UserScreenState) {
    // A Log to see when this function runs
    Log.d("Recomposition", "UserList is being composed")

    if (state.isLoading) {
        // Show a loading spinner
    } else if (state.error != null) {
        // Show an error message
    } else {
        // Show the list of users
    }
}

/*
Here's the problem: Because UserScreenState is a regular class, Compose doesn't know for sure if it's stable.

Now, imagine the user pulls to refresh, and the ViewModel fetches the exact same list of users. The ViewModel creates a new UserScreenState object to represent this state.

Even though the users, isLoading, and error properties are identical to what they were before,
Compose sees a brand new UserScreenState object.
Because it doesn't trust the type, it will play it safe and recompose UserList,
printing "UserList is being composed" to the log again. This is a wasted operation.
*/








