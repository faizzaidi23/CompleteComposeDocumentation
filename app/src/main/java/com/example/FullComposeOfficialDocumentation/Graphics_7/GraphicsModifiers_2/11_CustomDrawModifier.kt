package com.example.FullComposeOfficialDocumentation.Graphics_7.GraphicsModifiers_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
CUSTOM DRAWING MODIFIER

To create your own custom modifier implement the DrawModifier interface
This gives you access to a ContentDrawScope
which is the same as what is exposed when using Modifier.drawWithContent

You can then extract common drawing operations to custom drawing modifiers
to clean up the code and provide convenient wrappers

For example Modifier.background is a convenient DrawModifier
*/

/*
Custom FlippedModifier

This creates a Modifier that vertically flips content
by scaling with -1 on the Y axis
*/

class FlippedModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        scale(1f, -1f) {
            this@draw.drawContent()
        }
    }
}

/*
Extension function to use the FlippedModifier

This provides a convenient way to apply the custom modifier
*/

fun Modifier.flipped() = this.then(FlippedModifier())

/*
Using the custom flipped modifier

Apply the flipped modifier to any composable to flip it vertically
*/

@Composable
fun FlippedModifierExample() {
    Text(
        "Hello Compose!",
        modifier = Modifier.flipped(),
        fontSize = 24.sp
    )
}

/*
Custom HorizontalFlipModifier

This flips content horizontally by scaling with -1 on the X axis
*/

class HorizontalFlipModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        scale(-1f, 1f) {
            this@draw.drawContent()
        }
    }
}

fun Modifier.horizontalFlip() = this.then(HorizontalFlipModifier())

@Composable
fun HorizontalFlipExample() {
    Text(
        "Flipped Horizontally",
        modifier = Modifier.horizontalFlip(),
        fontSize = 20.sp
    )
}

/*
Custom MirrorModifier

This flips content both horizontally and vertically
*/

class MirrorModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        scale(-1f, -1f) {
            this@draw.drawContent()
        }
    }
}

fun Modifier.mirror() = this.then(MirrorModifier())

@Composable
fun MirrorExample() {
    Text(
        "Mirrored",
        modifier = Modifier.mirror(),
        fontSize = 20.sp
    )
}

/*
When to create custom DrawModifiers

Create custom DrawModifiers when you have:
- Reusable drawing operations that you use across multiple composables
- Complex drawing logic that you want to encapsulate
- Drawing effects that should be easily applicable to any composable

Benefits:
- Code reusability
- Clean and readable modifier chains
- Encapsulation of drawing logic
- Easy to test and maintain
*/

/*
Display all custom drawing modifier examples
*/

@Composable
fun AllCustomDrawModifierExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Custom Drawing Modifiers",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Implement DrawModifier interface to create reusable drawing effects",
            fontSize = 14.sp
        )

        Text(text = "Normal Text", fontWeight = FontWeight.Medium)
        Text("Hello Compose!", fontSize = 24.sp)

        Text(text = "Vertically Flipped", fontWeight = FontWeight.Medium)
        FlippedModifierExample()

        Text(text = "Horizontally Flipped", fontWeight = FontWeight.Medium)
        HorizontalFlipExample()

        Text(text = "Mirrored (Both Directions)", fontWeight = FontWeight.Medium)
        MirrorExample()
    }
}

