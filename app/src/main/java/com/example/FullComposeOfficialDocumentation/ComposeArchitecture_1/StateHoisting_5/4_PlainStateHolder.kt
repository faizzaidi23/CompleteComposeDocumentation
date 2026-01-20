package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.StateHoisting_5

/*
This concept is the next logical step up from "Hoisting within composables."

Hoisting within composables answers the question: Where do I put the state?

Answer: Lift it up to a common parent composable.

A Plain State Holder Class answers the question: How do I organize complex state once I've hoisted it?

Answer: Bundle all the related state variables and logic into a neat, reusable helper class.

Think of it like this: "Hoisting" is like deciding to put all your car-related items (keys, registration, insurance card) in one place in your house. A "State Holder Class" is like creating a special CarKit box to put all those items in, so they are perfectly organized and easy to grab.

You use a state holder class when the UI state and logic become too complex for a parent composable to manage with just a handful of remember variables. It's a pattern for organization and separation of concerns.
*/


/*
/ 1. @Stable: An optimization hint for the Compose compiler, telling it
//    that this class is "stable" and won't change in unexpected ways.
@Stable
// 2. This is the plain state holder class. It's not a ViewModel. Its only job
//    is to manage the state and logic for a scrolling list.
class LazyListState constructor(
    // 3. It takes initial values for where the list should start.
    firstVisibleItemIndex: Int = 0,
    firstVisibleItemScrollOffset: Int = 0
) : ScrollableState { // It implements an interface for scrollable things

    // 4. INTERNAL STATE: This is the complex part. It holds the precise scroll
    //    position. This complexity is hidden from you, the developer. You don't
    //    need to know how it works, just that it's being managed inside.
    private val scrollPosition = LazyListScrollPosition(
        firstVisibleItemIndex, firstVisibleItemScrollOffset
    )

    // 5. PUBLIC METHODS (UI LOGIC): The class exposes simple, clean functions
    //    that you can call to perform actions. This is the UI logic. Instead of
    //    doing complex calculations yourself, you just call a function.
    suspend fun scrollToItem(/*...*/) { /*...*/ }

    override suspend fun scroll() { /*...*/ }

    suspend fun animateScrollToItem() { /*...*/ }
}
*/