package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.ManagingStates_4

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

/*
The Stateful version is for convenience. When someone just needs a simple text field and doesn't care about managing its state from far away, they can use the easy "secret recipe" chef.

The Stateless version is for control and reusability.
When you need to read the text field's value from somewhere else,
save it, or have multiple parts of your app react to it, you need the
"follows your instructions" chef so you can control the recipe from a higher level.
*/


// Stateful---->It manages its own 'name'

@Composable
fun StatefulGreeting(){
    var name :String  by remember{mutableStateOf("")}

    TextField(value=name, onValueChange = {name=it})

    /*
    “I don’t want to work with the box, I just want to work with what’s inside it.”

mutableStateOf("") creates a box (MutableState<String>) that holds a String.

If you write

kotlin
Copy code
var name: MutableState<String> by remember { mutableStateOf("") }
you’re saying "name is a MutableState<String>", but by unwraps the box automatically — so the type should be String, not MutableState<String>.

Correct:

kotlin
Copy code
var name by remember { mutableStateOf("") } // name is a String, box is hidden
If you want to store the box itself, don’t use by:

kotlin
Copy code
var name: MutableState<String> = remember { mutableStateOf("") } // must use .value
Basically:

With by → You get the value directly (String).

Without by → You get the state object (MutableState<String>).
    */
}



// StateLess
/*
## Stateless: The "Follows Your Instructions" Chef
A stateless composable is like a chef who has no notebook.

When you ask this chef to make a cake, you have to give them the recipe.

If you want to add more sugar, you change the recipe you gave them, and they will follow the new instructions.

You are in complete control. The chef just does what they're told and doesn't remember anything on their own.

This is more work for you (the caller), but it's extremely flexible and reusable. You can give the chef any recipe you want (cake, cupcakes, cookies) and test that they follow it correctly.

This is achieved through state hoisting, which just means "lifting the state up" to the person calling the function.
*/

@Composable
fun StateLessGreeting(
    name:String,
    onNameChange:(String)-> Unit
){
    TextField(value=name, onValueChange = onNameChange)
}


/*
where to use these stateFul and StateLess


This is a fundamental concept you'll use constantly in app development. The choice between stateful and stateless isn't random; it's a deliberate architectural decision.

Here’s a simple rule of thumb:

Stateless: Use for components that are part of your app's core data or need to be controlled from the outside. This should be your default choice for most components.

Stateful: Use for simple, "fire-and-forget" UI elements whose state doesn't matter to the rest of your app.

## When to Use Stateless Composables (State Hoisting)
You use stateless composables when the state needs to be controlled, read, or shared by other parts of your app. This is the most common and recommended approach.

1. Connecting to a ViewModel (The #1 Use Case)
Scenario: You have a "Profile Screen" where a user can edit their name. The user's name is business data that needs to be saved to a database.

Why Stateless: The ViewModel must be the source of truth for the user's name. The screen (the UI) should not have its own secret copy of the name. You "hoist" the state to the ViewModel.

Implementation:

The ViewModel holds the name: val name by mutableStateOf("Alice").

The ProfileScreen takes the name and a function to change it: ProfileScreen(name = viewModel.name, onNameChange = { viewModel.updateName(it) }).

The TextField inside is stateless: TextField(value = name, onValueChange = onNameChange).

2. Creating Reusable Design System Components
Scenario: You are building a custom PrimaryButton or StyledTextField that will be used on many different screens in your app.

Why Stateless: A button in a login form needs to do something different than a button in a settings menu. If the button managed its own state and actions, it would be useless. By making it stateless, the screen that uses the button gets to decide what happens when it's clicked.

Implementation: PrimaryButton(text = "Login", onClick = { /* do login logic */ }). The onClick is hoisted.

3. When Multiple Composables Need to React to the Same State
Scenario: You have a screen with a Slider and a Text field that should always show the Slider's current value.

Why Stateless: If the Slider was stateful, the Text field would have no way of knowing its value. By hoisting the slider's value to their parent, the parent can give that value to both the Slider and the Text field, keeping them in sync.

Implementation:

Kotlin

// Parent composable
var sliderValue by remember { mutableStateOf(50f) }
Slider(value = sliderValue, onValueChange = { sliderValue = it })
Text(text = "Value: $sliderValue")
4. Making Composables Easy to Test
Scenario: You want to write a unit test for your RegistrationForm composable.

Why Stateless: It's much easier to test a stateless function. You can give it specific inputs (e.g., name = "test", password = "") and verify that it displays them correctly without worrying about any hidden internal state.

## When to Use Stateful Composables
You use stateful composables for state that is purely related to the UI and has no impact on your app's business logic. This state lives and dies with the UI element.

1. UI-Specific Animation State
Scenario: You want a button to animate its size (e.g., shrink slightly) when it's pressed.

Why Stateful: Whether the button is currently shrunk or not is irrelevant to your ViewModel or the rest of your app. It's a purely visual detail. Managing this animation state from the ViewModel would be unnecessary boilerplate.

Implementation: The button itself can use remember to store its animation state internally.

2. Simple, Self-Contained UI Elements
Scenario: You have a "Show More / Show Less" toggle for a long block of text.

Why Stateful: The expanded/collapsed state of this text block doesn't need to be saved or known by any other part of the app. It's a temporary UI state. Making the parent manage this would add clutter for no real benefit.

Implementation: The ExpandableText composable can have its own internal var isExpanded by remember { mutableStateOf(false) }.

3. Transient State in Complex Components
Scenario: A Scaffold composable that manages the state of its Drawer (whether it's open or closed).

Why Stateful: While you can control the drawer state from the outside, the default Scaffold is stateful. It manages this for you for convenience, as often you just want a working drawer without manually controlling its open/closed state from your ViewModel.

In summary, think about who needs to know. If only the composable itself needs to know about a piece of state, keep it stateful. If anything outside the composable (the ViewModel, a parent composable, your tests) needs to know or control it, make it stateless.
*/