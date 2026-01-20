package com.example.a1_composedocumentationuiarchitecture.Architecture_7

/*
It is a design pattern where state flows down and events flow up.

The UI update loop for an app using unidirectional data flow looks like this

Event-->parts of UI generates an event and passes it upward such as button click
passed to the viewmodel to handle or an event is passed from other layers of the app


update state-->an event handler might change the state

Display state-->The state holder passes down the state and the UI displays it




This pattern provides several advantages


1-->Testability-->Decoupling state from the UI that display it makes it easier to test both in isolation

2-->State encapsulation-->Because  state can only be updated in one place and there is only one source of truth
for the state of a composable it is less likely that you will create bugs due to inconsistent states

3-->UI consistency-->All state updates are immediately reflected in the UI by the use of observable state holders like stateFlow or LiveData
*/


/*
Unidirectional data flow in jetpack compose-->

just like the above code snippet and explanation it is the same thing
*/