package com.example.a2_composedocumentation_app_layout.Modifiers_2

import android.R.attr.onClick
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*Modifiers are standard kotlin objects.*/

@Composable
private fun Greeting(name: String) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(text = "Hello,")
        Text(text = name)
    }
}

//Order of modifiers

/*
The order of modifier functions is significant.
Since each function makes changes to the Modifierreturned
by the previous function, the sequence affects the final result.
*/

@Composable
fun ArtistCard(/*...*/) {
    val padding = 16.dp
    Column(
        Modifier
            .clickable(onClick = {})
            .padding(padding)
            .fillMaxWidth()
    ) {
        // rest of the implementation
    }
}

/*
In the code above the whole area is clickable, including the surrounding padding,
because the padding modifier has been applied after the clickable modifier.
If the modifiers order is reversed, the space added by padding does not react to user input:
*/

/*
 If the modifiers order is reversed,
 the space added by padding does not react to user input:
*/

@Composable
fun ArtiistCard(/*...*/) {
    val padding = 16.dp
    Column(
        Modifier
            .padding(padding)
            .clickable(onClick ={})
            .fillMaxWidth()
    ) {
        // rest of the implementation
    }
}
