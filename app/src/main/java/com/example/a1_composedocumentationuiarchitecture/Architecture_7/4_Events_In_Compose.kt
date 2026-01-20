package com.example.a1_composedocumentationuiarchitecture.Architecture_7

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.ui.text.style.TextAlign

/*
Every input to your app should be represented as an event: taps, text changes, and even timers or other updates. As these events change the state of your UI, the ViewModel should be the one to handle them and update the UI state.

The UI layer should never change state outside of an event handler because this can introduce inconsistencies and bugs in your application.

Prefer passing immutable values for state and event handler lambdas. This approach has the following benefits:

You improve reusability.
You ensure that your UI doesn't change the value of the state directly.
You avoid concurrency issues because you make sure that the state isn't mutated from another thread.
Often, you reduce code complexity.
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopAppBar(topAppBarText:String,onBackPressed:()-> Unit){
    TopAppBar(
        title={
            Text(
                text=topAppBarText,
                textAlign = TextAlign.Center,
                modifier=Modifier.fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed){
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "this is an icon"
                )
            }
        }
    )
}