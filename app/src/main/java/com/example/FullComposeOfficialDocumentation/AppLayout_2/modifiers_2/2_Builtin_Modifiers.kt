package com.example.a2_composedocumentation_app_layout.Modifiers_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
Jetpack compose provides a list of build in modifiers to help in decorating a
composable
*/

// 1-->Padding and size

@Composable
fun ArtisttCard(/*...*/) {
    Row(
        modifier = Modifier.size(width = 400.dp, height = 100.dp)
    ) {
        //Image(/*...*/)
        Column { /*...*/ }
    }
}

//2-->required size
/*
The size we specify might not be respected if it does not
satisfy the constraints coming from the layout's parent.
If we require the composable size to be fixed regardless of the incoming constraints,
we have to use the requiredSize composable
*/

@Composable
fun ArrrrtisticCard(){
    Row(
        modifier=Modifier.size(width=400.dp,height=100.dp)
    ){
        /*Image(
            modifier=Modifier.requiredSize(150.dp),
            imageVector =
        )*/
    }
}

/*
In the above example even with the parent height set to 100 dp the height of the
image will be 150 dp as the required size  modifier takes precedence
*/

// fillMaxHeight and fillMaxWidth

/*
if we want a child layout to fill all the available height allowed by the parent
add the fillMaxHeight modifier (Compose also provides fillMaxSize and fillMaxWidth):
*/

/*
@Composable
fun ArtistCard(/*...*/) {
    Row(
        modifier = Modifier.size(width = 400.dp, height = 100.dp)
    ) {
        Image(
            /*...*/
            modifier = Modifier.fillMaxHeight()
        )
        Column { /*...*/ }
    }
}
*/


/*
Offset--->

To position a layout relative to its original position,
add the offset modifier and set the offset in the x and y axis.
 Offsets can be positive as well as non-positive.
The difference between padding and offset is that adding an
offset to a composable does not change its measurements:

@Composable
fun ArtistCard(artist: Artist) {
    Row(/*...*/) {
        Column {
            Text(artist.name)
            Text(
                text = artist.lastSeenOnline,
                modifier = Modifier.offset(x = 4.dp)
            )
        }
    }
}

*/