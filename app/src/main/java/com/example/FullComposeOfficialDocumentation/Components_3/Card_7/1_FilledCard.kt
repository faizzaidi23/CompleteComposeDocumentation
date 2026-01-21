package com.example.FullComposeOfficialDocumentation.Components_3.Card_7

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
The card composable acts as a material design container for the UI
cards typically present a single coherent piece of content


Card is generally a smaller UI element inside a larger layout, whereas a layout component such as Column or Row provides a simpler and more generic API
*/


/*
APIs that are present in the card composable


elevation-->Adds a shadow to the component that makes it appear elevated above the background

colors-->Uses the CardColors type to set the default color of both the container and any children

enabled-->if we pass false for this parameter the card appears as disabled and does not respond to the user input

onClick--> Originally a card does not accept click events. As such the primary overload we would like
to note is that which defines an onClick parameter.
we should use this overload if we would like our implementation of Card to respond to presses from the user
*/



@Composable
fun FilledCardExample(){
    Card(
        colors= CardDefaults.cardColors(
            containerColor= MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier=Modifier.size(width=240.dp,height=100.dp)
    ){
        Text(
            text="Filled",
            modifier=Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}