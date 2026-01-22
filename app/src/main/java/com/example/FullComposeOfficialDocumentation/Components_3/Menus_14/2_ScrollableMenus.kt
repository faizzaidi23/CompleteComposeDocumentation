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

//This creates a longer scrollable drop down menu


@Composable
fun LongDropDownMenu(){
    var expanded by remember{mutableStateOf(false)}

    val menuItemData=List(100){"Option ${it+1}"}
    /*
    val menuItemData = List(100, { index ->
    "Option ${index + 1}"
})


above code can also be written like this since this is the standard library we are implementing of the list
    */


    Box(modifier=Modifier.padding(16.dp)){
        IconButton(onClick = {expanded=!expanded}){
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(expanded=expanded, onDismissRequest = {expanded=false}){
            menuItemData.forEach { option->
                DropdownMenuItem(
                    text={Text(option)},
                    onClick = {/*To do something here*/}
                )
            }
        }
    }
}