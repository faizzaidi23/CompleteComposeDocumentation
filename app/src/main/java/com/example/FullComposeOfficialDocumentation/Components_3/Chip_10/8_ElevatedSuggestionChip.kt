package com.example.FullComposeOfficialDocumentation.Components_3.Chip_10

import android.util.Log
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ElevatedSuggestionChipExample(){
    ElevatedSuggestionChip(
        onClick = {Log.d("Elevated Suggestion chip","hello world")},
        label={Text("Elevated Suggestion Chip")}
    )
}

