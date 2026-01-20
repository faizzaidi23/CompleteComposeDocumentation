package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.StateHoisting_5

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

/*
Hoisting state is not always required. State can be kept internal in a composable
when no other composable need to control it.


If a piece of state isn't needed by any other part of your UI and the
logic to change it is simple (like flipping a switch from on to off),
it's easier and cleaner to keep it inside that one Composable.
*/

data class Message(val content:String,val timeStamp:String)

@Composable
fun ChatBubble(
    message:Message
){
    var showDetails by rememberSaveable { mutableStateOf(false) }

    Column{
        Text(
            text=message.content,
            modifier=Modifier.clickable{showDetails=!showDetails}
        )

        if(showDetails){
            Text(message.timeStamp)
        }
    }
}

