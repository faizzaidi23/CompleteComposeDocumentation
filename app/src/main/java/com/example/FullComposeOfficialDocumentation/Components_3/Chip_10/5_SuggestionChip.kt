package com.example.FullComposeOfficialDocumentation.Components_3.Chip_10

import android.util.Log
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/*
The SuggestionChip composable is the most basic of the composable listed on the page

both in its API definition and its common use cases.
Suggestion chips present dynamically generated hints.

In an AI chat app, we might use suggestion chips to present possible responses to the most recent message

*/


@Composable
fun SuggestionChipExample(){
    SuggestionChip(
        onClick = {Log.d("Suggestion chip","hello world")},
        label={Text("Suggestion Chip")}
    )
}