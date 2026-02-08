package com.example.FullComposeOfficialDocumentation.Accessibility_9.ApiDefaults_1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp


/*
when implementing selection controls like Switch, RadioButtton or checkbox
we typically lift the clickable behavior to a parent container by seltting the click
callback on the composable to null and adding a toggleable or selectable modifier to the parent composable
*/


/*
below what we are doing is this we have this code inside the MaterialTheme{}

so why because we want to to use the things like MaterialTheme.colorScheme and many things

but here the checkbox we do not explicityly specify the colors and all because it is
done by the checkbox internally


but we need to do this for other composables


this is usually used at the top of the app when we create an application
*/

@Composable
fun CheckBoxRow(){
    MaterialTheme{
        var checked by remember{mutableStateOf(false)}
        Row(
            Modifier.toggleable(
                value = checked,
                role=Role.Checkbox,
                onValueChange = {checked=!checked}
            ).padding(16.dp).fillMaxWidth()
        ){
            Text("Option:",Modifier.weight(1f))
            Checkbox(checked=checked, onCheckedChange = null)
        }
    }
}


/*
when the size of a clickable composable is smaller than the minimum touch target size
compose still increase touch target size.
It does so by expanding the touch target size outside of the boundaries of the composable

The below example contains a very small clickable box. The touch target area is automatically expanded
beyond the boundaries of the box, so tapping next to the box still triggers the click event
*/

@Composable
fun SmallBox(){
    var clicked by remember{mutableStateOf(false)}

    Box(
        Modifier.size(100.dp).background(if(clicked)Color.DarkGray else Color.LightGray)
    ){
        Box(Modifier.align(Alignment.Center)
            .clickable{clicked=!clicked}
            .background(Color.Black)
            .size(1.dp)
        )
    }
}

/*
To prevent possible overlap between touch areas of different composable always use a large enough
minimum size for the composable,

in the below example that would mean using the sizeIn modifier to set the minimum size for the inner box
*/

@Composable
private fun LargeBox() {
    var clicked by remember { mutableStateOf(false) }
    Box(
        Modifier
            .size(100.dp)
            .background(if (clicked) Color.DarkGray else Color.LightGray)
    ) {
        Box(
            Modifier
                .align(Alignment.Center)
                .clickable { clicked = !clicked }
                .background(Color.Black)
                .sizeIn(minWidth = 48.dp, minHeight = 48.dp)
        )
    }
}