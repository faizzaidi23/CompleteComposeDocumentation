package com.example.FullComposeOfficialDocumentation.Components_3.Chip_10

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun FilterChipExample(){
    var selected by remember{ mutableStateOf(false) }

    FilterChip(
        onClick = {selected=!selected},
        label={Text("Filter Chip")},
        selected = selected,
        leadingIcon =if(selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done Icon",
                    modifier=Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        }else{
           null
        },
    )
}