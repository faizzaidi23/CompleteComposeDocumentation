package com.example.FullComposeOfficialDocumentation.Components_3.Chip_10

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/*
We can use the InputChip composable to create chips that result from user interaction.
For example, in an email client when the user is writing an email an input chip might represent a contact the user has added to the "To" field
*/

@Composable
fun InputChipExample(
    text: String,
    onDismiss:()-> Unit
){
    var enabled by remember{mutableStateOf(true)}
    if(!enabled)return


    InputChip(
        onClick = {
            onDismiss()
            enabled=!enabled
        },
        label={Text(text)},
        selected = enabled,
        avatar={
            Icon(
                Icons.Filled.Person,
                contentDescription = "Localized desciption",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}