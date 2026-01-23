package com.example.FullComposeOfficialDocumentation.Components_3.Slider_24

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

/*
Slider-->


The slider composable allows users to make the selections from a range of values.
we might use a slider to let the users do the things like adjusting settings, filter data in graphs and user input like setting a rating in a review
*/

/*
API definition


value-->The current value of the slider
onValueChange-->A lambda that gets called every time tha value is changed
enabled-->A boolean value that indicates if the user can interact with the slider or not
*/


@Composable
fun SliderminimalExample(){
    var sliderPosition by remember{ mutableFloatStateOf(0f) }
    Column{
        Slider(
            value = sliderPosition,
            onValueChange = {sliderPosition=it}
        )
        Text(text=sliderPosition.toString())
    }
}