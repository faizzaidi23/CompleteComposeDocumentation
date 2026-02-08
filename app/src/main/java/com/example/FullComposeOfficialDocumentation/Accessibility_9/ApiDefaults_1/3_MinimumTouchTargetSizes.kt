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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/*
Any on-screen element that someone can click, touch, or interact with must be large enough for
reliable interaction. When sizing these elements, make sure to set the minimum size to 48dp to
correctly follow the Material Design accessibility guidelines.

Material components like Checkbox, RadioButton, Switch, Slider, and Surface set this minimum size
internally, but only when the component can receive user actions.
*/

/*
When a Checkbox has its onCheckedChange parameter set to a non-null value,
the checkbox includes padding to have a width and height of at least 48 dp
*/
@Composable
fun CheckableCheckbox() {
    Checkbox(checked = true, onCheckedChange = {})
}

/*
When the onCheckedChange parameter is set to null, the padding is not included,
because the component cannot be interacted with directly
*/
@Composable
fun NonClickableCheckbox() {
    Checkbox(checked = true, onCheckedChange = null)
}

/*
When implementing selection controls, you typically lift the clickable behavior to a parent container
by setting the click callback on the composable to null, and adding a toggleable or selectable
modifier to the parent composable
*/
@Composable
fun CheckableRow() {
    MaterialTheme {
        var checked by remember { mutableStateOf(false) }
        Row(
            Modifier
                .toggleable(
                    value = checked,
                    role = Role.Checkbox,
                    onValueChange = { checked = !checked }
                )
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Option", Modifier.weight(1f))
            Checkbox(checked = checked, onCheckedChange = null)
        }
    }
}

/*
When the size of a clickable composable is smaller than the minimum touch target size,
Compose still increases the touch target size by expanding it outside of the boundaries of the composable
*/
@Composable
fun SmalllBox() {
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
                .size(1.dp)
        )
    }
}

/*
To prevent possible overlap between touch areas of different composables,
always use a large enough minimum size for the composable using the sizeIn modifier
*/
@Composable
fun LargeBox() {
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

