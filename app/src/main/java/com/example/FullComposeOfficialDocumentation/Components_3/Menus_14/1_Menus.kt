package com.example.FullComposeOfficialDocumentation.Components_3.Menus_14

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
Drop down menus let users click an icon, text field, or other component and then select
from a list of options on a temporary surface
*/


/*
API Surface--->

Use DropdownMenu, DropdownMenuItem and the IconButton components to implement a custom drop down menu
The DropdownMeny and the DropdownMenuItem components are  used to display the menu items while the IconButton is the trigger to display or hide the drop down menu


The key parameter for the DropdownMeny components include the following properties:

expanded-->Indicates whether the menu is visible or not
onDismissRequest-->Used to handle the menu dismissal
content--->The composable content of the menu typically containing DropdownMenuItem composable

*/


@Composable
fun MinimalDropdownMenu(){
    var expanded by remember {mutableStateOf(false)}

    Box(modifier=Modifier.padding(16.dp)){
        IconButton(
            onClick = {expanded=!expanded}
        ){
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "More options"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded=false}
        ){
            DropdownMenuItem(
                text={Text("Option 1")},
                onClick = {/* Do Something */}
            )

            DropdownMenuItem(
                text={Text("Option 2")},
                onClick = {/*To do something*/}
            )
        }
    }
}