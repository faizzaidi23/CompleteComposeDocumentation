package com.example.a1_composedocumentationuiarchitecture.ManagingStates_4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier


/*
rememberSaveable{....}  (Basic Version)-->Remembers a value and keeps it, no matter what. It only changes when the user directly interacts with it.

rememberSaveable(input){...}  (advanced version)--> Rememebrs a value,but it is also watching the input key.
If that input ever changes, rememberSaveable will throw away its old value and reset itself using the new input
*/

/*
## Why You Need This Feature
You need this for situations where the state held by a Composable needs to be updated or reset based on an external event or data change, not just by direct user interaction.

The most common scenario is a search or filter screen.

Scenario: You have a list of suggested search queries. When the user taps a suggestion (e.g., "Compose basics"), you want the text in the search bar to update to "Compose basics".

The Problem: The search bar is using rememberSaveable to hold what the user is typing. How do you tell it to throw away its current text and accept the new "Compose basics" text from the outside?

The Solution: You pass the suggested query as an input key to the search bar's rememberSaveable. When the key changes, it forces the search bar's state to reset to that new value.
*/
@Composable
fun example(){


    // This variable pretends to be an external piece of data,like a search suggestion the user taps on. Its only purpose here is to be trigger that can reset the TextField

    var typedQuery by remember{mutableStateOf("initial query ")}

    //This variable represents the actual text inside the TextField. It is what the user is currently typing


    var userTypedQuery by rememberSaveable(
        typedQuery,
        stateSaver = TextFieldValue.Saver
    ){
        mutableStateOf(TextFieldValue(
            text=typedQuery,
            selection = TextRange(typedQuery.length)
        )
        )

    }
    Button(onClick={typedQuery="new value from outside"}){
        Text("Reset with new query")
    }

    // Add a textField to show the user's typing

    TextField(value=userTypedQuery, onValueChange = {userTypedQuery=it})
}


/*
Where can we use this concept in the android application development


## 1. Search Bar with Suggestions
This is the classic example. The user can type freely, but they can also tap a suggestion to populate the search bar.

The Scenario: You have a search screen. Below the search bar, there's a list of popular search terms (e.g., "T-shirts", "Jeans", "Shoes").

The States:

userQuery: The text the user is currently typing. This is stored in rememberSaveable so it survives rotation.

tappedSuggestion: The text from the suggestion the user just tapped. This comes from your ViewModel or parent composable.

Why you need the concept: When the user taps "Jeans", you need a way to force the TextField to discard whatever the user was typing and reset its content to "Jeans".

How it works: You pass tappedSuggestion as an input key to the rememberSaveable that holds userQuery. When tappedSuggestion changes, rememberSaveable throws away its old state and re-initializes with the new value.

## 2. Editing a Form with a "Reset" or "Revert" Button
This is very common in profile editing or settings screens.

The Scenario: A user is editing their profile bio. They make some changes but then decide they want to go back to the original bio they had saved. They tap a "Revert Changes" button.

The States:

editedBio: The text the user is currently editing in the TextField, stored in rememberSaveable.

originalBio: The user's original bio, loaded from your ViewModel. This is the "reset point".

Why you need the concept: The TextField is managing its own state as the user types. The "Revert" button is an external event. You need that external event to force the TextField to discard its current state (editedBio) and reset to the originalBio.

How it works: You can use the originalBio from the ViewModel as the input key. When the "Revert" button is clicked, you can trigger a state change in the ViewModel that causes the rememberSaveable to reset.

## 3. A Form with Pre-fillable Templates
This is common in support ticket apps, email clients, or any app with repetitive data entry.

The Scenario: A user is writing a support ticket. There are buttons like "Bug Report Template" and "Feature Request Template" that automatically fill the main text area with pre-written text.

The States:

ticketContent: The text inside the main TextField, managed by rememberSaveable. The user can edit this freely.

selectedTemplateText: The content of the template the user just clicked. This state is controlled by the template buttons.

Why you need the concept: The user might have already typed something. When they click a template button, you need to override their input and replace it with the template's content.

How it works: You pass selectedTemplateText as an input key to the rememberSaveable holding ticketContent. When a new template is selected, the key changes, and the TextField's content is reset to the new template.

## 4. Dependent Dropdowns or Form Fields
This pattern is essential for forms where one choice affects the options or values of another.

The Scenario: A user is selecting their location. They have two dropdown menus: "Country" and "State/Province".

The States:

selectedCountry: The value from the first dropdown.

selectedState: The value from the second dropdown, stored in rememberSaveable.

Why you need the concept: If the user first selects "USA" and "California", and then goes back and changes the country to "Canada", the "California" selection is now invalid. The "State/Province" field should automatically reset to a blank or default state.

How it works: You pass selectedCountry as an input key to the rememberSaveable for selectedState. When the country changes, the state field is automatically invalidated and reset.
*/


/*
So, while the specific examples might differ, the fundamental reason is always the same: a parent data source needs to force a child UI state to reset.
*/



@Composable
fun BioEditScreen() {
    // 1. This is the original, "correct" data. In a real app, this
    //    would come from your ViewModel or database.
    val originalBio = "I am a software developer who loves Jetpack Compose!"

    // 2. This is our trigger. We'll change this value to force the reset.
    //    Using a simple counter is an easy way to create a trigger.
    var resetTrigger by remember { mutableStateOf(0) }

    // 3. This is the state for our TextField. It holds what the user is currently typing.
    var editedBio by rememberSaveable(
        // 4. THE KEY: We are watching the 'resetTrigger'. If this key's value
        //    ever changes, the block below will re-run, resetting the state.
        resetTrigger
    ) {
        // 5. This is the initialization block. It sets the initial state
        //    to be the original bio. It re-runs whenever 'resetTrigger' changes.
        mutableStateOf(originalBio)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Edit your bio:")

        OutlinedTextField(
            value = editedBio,
            onValueChange = { editedBio = it },
            modifier = Modifier.height(150.dp)
        )

        Spacer(modifier =Modifier.height(16.dp))

        // 6. The "Reset" button.
        Button(
            onClick = {
                // 7. When clicked, we increment our trigger. This change is
                //    detected by rememberSaveable, which then resets the 'editedBio' state.
                resetTrigger++
            }
        ) {
            Text("Reset to Original")
        }
    }
}


/*
Think of rememberSaveable as a security guard who is guarding a value. The guard will only give you the same value back as long as you provide the same secret key.

Here’s the step-by-step process:

1. The Initial State
When the screen first loads, resetTrigger has a value of 0.

rememberSaveable sees the key 0 for the first time.

It runs its code block: mutableStateOf(originalBio).

It stores the result (the user's bio) and links it to the key 0.

2. While the User is Typing
The user types in the TextField. The editedBio variable changes.

The screen recomposes, but the value of resetTrigger is still 0.

rememberSaveable checks its key. Since the key (0) has not changed, the guard says, "Same key, same value," and it continues to hold onto the user's edited text. It does not re-run its code block.

3. The "Reset" Button is Clicked
This is the critical moment.

The onClick lambda runs: resetTrigger++.

The value of resetTrigger changes from 0 to 1.

This state change causes the screen to recompose.

4. The Reset is Forced
During recomposition, rememberSaveable runs again.

It looks at its new key, which is now 1.

It compares this to the key it remembers from the last time, which was 0.

The guard sees that the key is different (1 is not equal to 0). It says, "New key! I must discard the old value and get a new one."

Because the key has changed, rememberSaveable invalidates its stored state (it throws away the user's edited text).

It is now forced to re-execute its code block: mutableStateOf(originalBio).

The editedBio variable is reset to the originalBio. The TextField updates on the screen to show the original text again.

So, by changing the value of the resetTrigger key, you are essentially telling rememberSaveable to forget everything it was holding onto and start over from scratch.
*/
