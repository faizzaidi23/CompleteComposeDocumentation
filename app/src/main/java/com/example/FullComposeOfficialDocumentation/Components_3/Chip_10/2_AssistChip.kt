package com.example.FullComposeOfficialDocumentation.Components_3.Chip_10

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AssistChipExample(){
    AssistChip(
        onClick = {Log.d("Assist chip","hello world")},
        label={Text("Assist Chip")},
        leadingIcon = {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Localized desciption",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}