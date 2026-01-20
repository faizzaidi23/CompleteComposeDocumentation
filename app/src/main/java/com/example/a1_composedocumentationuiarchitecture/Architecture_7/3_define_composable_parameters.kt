package com.example.a1_composedocumentationuiarchitecture.Architecture_7

import androidx.compose.runtime.Composable

/*
Define composable parameters

When defining the state parameters of a composable you should keep the following thing in mind

1-->How reusable or flexible is this composable
2-->How do the state parameters affect this composable  performance
*/

/*
To encourage decoupling and reuse, each composable should hold the least amount of
information possible. For example, when building a composable to hold
 the header of a news article, prefer passing in only the information
 that needs to be displayed, rather than the entire news article:
*/

data class News(val content: String)

@Composable
fun Header(title: String, subtitle: String) {
    // Recomposes when title or subtitle have changed.
}

@Composable
fun Header(news: News) {
    // Recomposes when a new instance of News is passed in.
}