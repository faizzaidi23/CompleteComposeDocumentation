package com.example.FullComposeOfficialDocumentation.Components_3.Buttons_5

import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

/*
Segmented button--->

We use a segmented button to let the users choose from a set of options, side by side
There are two types of segmented buttons;


Single-select button:Lets the user to choose one option

Multi-select button:Lets the user choose between two and five items.
For more complex choices, or more than five items

*/

//Creating single select segmented button

//below code shows how to create a single-select segmented button

@Composable
fun SingleChoiceSegmentedButton(modifier:Modifier=Modifier){
    var selectedIndex by remember{mutableIntStateOf(0)}

    val options=listOf("Day","Month","Week")

    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index,label->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index=index,
                    count=options.size
                ),
                onClick = {selectedIndex=index},
                selected = index==selectedIndex,
                label={Text(label)}
            )
        }
    }
}