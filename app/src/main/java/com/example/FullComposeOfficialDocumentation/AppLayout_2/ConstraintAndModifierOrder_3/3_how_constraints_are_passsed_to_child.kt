package com.example.a2_composedocumentation_app_layout.Constraint_And_Modifier_Order_3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
Think of the layout system as asking questions and getting answers, starting from the top:

Parent Passes Rules Down: The parent layout gives a set of size rules (constraints) to its child. For example, "You can be up to 200 pixels wide and 150 pixels tall."

Modifiers Adjust the Rules: If the child has modifiers, the rules are passed down the modifier chain. A modifier like .padding(10.dp) will take the rules it received and pass down new, stricter rules to the next thing in line. For example, it would say, "The rules I got were 200x150, but since I need 10dp of space on each side, the component inside me can only be 180x130."

The Innermost Component Decides: This continues until the actual UI element (like Text or Image) is reached. This element has no children of its own. It looks at the final rules it received and its own content (e.g., the text "Hello") and decides on its final size. For example, "Okay, my text fits in 50x20 pixels, which is within the rules I was given."

Size is Reported Back Up: This final size is then reported back up the chain. The padding modifier takes the 50x20 size and adds its 10dp padding back, reporting a total size of 70x40 to the parent.

Parent Places the Child: Once the parent knows the final size of its child, it can position that child within its own layout.

This process happens for every component, going all the way down to the innermost child and then coming back up, before moving on to the next sibling.
*/


@Composable
fun SimpleLayout(){
    Box(
        modifier=Modifier.fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray)
    ){

        Text(
            text="Helllo",
            modifier=Modifier.padding(10.dp).background(Color.Blue)
        )
    }
}