package com.example.FullComposeOfficialDocumentation.Components_3.Divider_13

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
Divider--->


Divider are the thin lines that separate items in lists or other containers
we can implement dividers in the app using the HorizontalDivider and VerticalDivider composable


HorizontalDivider--->Separate items in a column
VerticalDivider--->Separate items in a row
*/


/*
API surface-->

Both the components provide parameters for modifying their appearance


thickness-->Use this parameter to specify the thickness of the divider line

color-->Use this parameter to specify the color of the divider line

*/



//Horizontal divider

@Composable
fun HorizontalDividerExample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text("First item in list")
        HorizontalDivider(thickness = 2.dp)
        Text("Second item in list")
    }
}




//Vertical Divider


@Composable
fun VerticalDividerExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("First item in row")
        VerticalDivider(color = MaterialTheme.colorScheme.secondary)
        Text("Second item in row")
    }
}