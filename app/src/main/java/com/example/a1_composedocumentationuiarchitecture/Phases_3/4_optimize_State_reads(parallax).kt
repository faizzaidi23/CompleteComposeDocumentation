package com.example.a1_composedocumentationuiarchitecture.Phases_3



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.example.a1_composedocumentationuiarchitecture.R

// Note: You'd also need imports for whatever you put in your LazyColumn

//Inefficient code

/*
This code creates a parallax effect where a background Image scrolls at a different speed than a foreground LazyColumn.


*/

@Composable
fun Inefficient(){
    Box {
        val listState = rememberLazyListState() // This creates and remembers a special state object that can control and observe a LazyColumn
        Image(
            painter =painterResource(id=R.drawable.ic_launcher_background),
            contentDescription = "lazy image",
            // Inefficient!
            Modifier.offset(  //This modifier is used to manually shift the Image from its original position. The value we provide will move it up or down
                with(LocalDensity.current) { /*This is a helper that gets the current device's screen density. It's needed because the scroll offset is in raw pixels, but this version of the offset modifier expects a Dp value. This block allows us to easily convert between pixels and Dp.*/
                    // State read happens in COMPOSITION phase.
                    (listState.firstVisibleItemScrollOffset / 2).toDp()
                }
                /*
                (listState.firstVisibleItemScrollOffset / 2).toDp()
This is the core calculation.

listState.firstVisibleItemScrollOffset: This is where we read the state. It gives us the current scroll position of the list in pixels. This value changes constantly as the user scrolls.

/ 2: We divide the scroll amount by two. This makes the Image move at half the speed of the list, creating the parallax effect.

.toDp(): We convert the final pixel value back to Dp to satisfy this version of the offset modifier.

LazyColumn(state = listState) { /* ... */ }
This is the scrollable list that appears in the foreground.

state = listState: This is the crucial link. We are telling the LazyColumn to use our "spy" object. Now, whenever the user scrolls this list, the listState object will be updated.


                */
            )
        )
        LazyColumn(state = listState) { /* ... */ }
    }
}

/*
Why This Code Is Inefficient
This implementation works, but it's inefficient because of when and where it reads the scroll state.

State Read in Composition Phase: The value of listState.firstVisibleItemScrollOffset is read directly inside the body of the Box composable. This is known as reading state during the Composition phase.

Constant Recomposition: As the user scrolls, firstVisibleItemScrollOffset changes for every single pixel of movement. Because this state is read during Composition, Compose thinks the entire "blueprint" for the Box has changed.

Unnecessary Work: This forces Compose to recompose the Box and everything inside it on every frame of the scroll. Recomposition is the most expensive phase. The system re-runs your composable functions, re-creates the UI description, then re-runs the Layout and Drawing phases.

The problem is that the Image itself isn't changing,
 and the LazyColumn content isn't changing—only their positions are.
 We are forcing a full, expensive recomposition just to move something,
 which is like tearing down and rebuilding a house
 just to move a painting on the wall. This can lead to lag or stuttering,
 especially on less powerful devices.
*/




// Efficient code

@Composable
fun Efficient(){
    Box{
        val listState=rememberLazyListState()
        Image(
            painter=painterResource(R.drawable.ic_launcher_background),
            contentDescription = "background image",
            modifier=Modifier.offset{
                IntOffset(x=0,y=listState.firstVisibleItemScrollOffset/2)
            }
        )
        LazyColumn(state=listState) {
            /*....*/
        }
    }
}
/*
The Solution: We switch to the lambda version, Modifier.offset { ... }.

Why it's good: Now,
the listState.firstVisibleItemScrollOffset is only read inside the lambda block.
 As we learned, this block runs during the Layout phase.
 As the user scrolls, Compose skips the Composition phase entirely
  and only re-runs the Layout and Drawing phases.
  This makes the scroll animation much smoother.
*/



/*
When to Use the Optimization
(Using derivedStateOf or the offset lambda)

Use the optimized version when you are reading a scroll property to make a simple decision that affects something else on the screen.

Showing/Hiding a "Scroll to Top" Button: You read listState.firstVisibleItemIndex > 0. This value changes from false to true only once, so derivedStateOf is perfect here to prevent recomposition on every scroll.

Creating a Parallax Effect: You read listState.firstVisibleItemScrollOffset to move a background image. Using the Modifier.offset lambda defers this read to the Layout phase, which is much more efficient.

Changing a Toolbar's Color/Elevation: You want the toolbar to become solid only after the user has scrolled a certain amount. This is another perfect case for derivedStateOf.

The Rule of Thumb: If the state changes on every frame, but the UI you're controlling should only change occasionally, you need the optimization.
*/
