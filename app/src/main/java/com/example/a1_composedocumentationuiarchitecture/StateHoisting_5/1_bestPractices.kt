package com.example.a1_composedocumentationuiarchitecture.StateHoisting_5

/*
where to put your state

Lowest common ancestor-->the rule is to lift state up to the first parent that is shhared by all the
ui components that need to read or change it



keep it close--> do not lift state hight than necessary. Keep it as close as possible to where it is being used

ViewModel for Business Logic--> If the state involves business logic like saving the data its lowest common ancestor is often the viewModel

*/


/*
Types of States and Logic


Screen UI state i.e the What--->This is the main data your screen needs to show
e.g A list of news articles to display


UI element state (The how it looks)--->This is the state of a specific UI piece, like its color, size or whether it is visible

e.g The scroll position of a list or whether a search bar is focused or not

Business Logic State (The why)---->These are the important actions related to the app's data, like saving, deleting or updating something.
This logic usually in your ViewModel not the UI

e.g The code that saves a bookmarked article to a database

UI logic (The how it behaves)--->This is the logic that decides how the UI should look or behave on screen

e.g Showing Saved message after the user clicks the bookmarks button or navigating to a new screen
*/