package com.example.a1_composedocumentationuiarchitecture.ManagingStates_4

/*
observeAsState-->Used earlier

It specifically observes LiveData not a Flow and turns its value into State for Compose

Why it's needed: Before Flow became the standard for asynchronous
data streams in Kotlin, LiveData was the primary tool used
in Android ViewModels. Many large, existing codebases are built
entirely on LiveData. observeAsState allows developers to adopt
Jetpack Compose for their UI without having to rewrite all their
existing ViewModel logic from LiveData to Flow immediately.
*/