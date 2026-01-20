package com.example.a1_composedocumentationuiarchitecture.Architecture_Layering_8

/*
Layered Architecture: Jetpack Compose is not one single library but a stack of layers built on top of each other. This allows you to use only the layers you need.

Runtime: The core foundation (remember, mutableStateOf, @Composable).

UI: The fundamental UI toolkit (Modifier, layouts, drawing).

Foundation: Basic, design-agnostic components (Row, Column, LazyColumn).

Material: An implementation of the Material Design system (themed buttons, icons, ripples).

Principle 1: Control: If a high-level, simple component doesn't offer enough control, you can "drop down" and use the lower-level APIs it's built on for more power.

Principle 2: Customization: High-level components are just assemblies of lower-level ones. If you need a feature a component doesn't expose (like a gradient background on a Button), you can copy its implementation and modify it using the same building blocks.

Goal: This layered approach lets you choose between convenience (high-level components) and control (low-level building blocks), making the system highly flexible.

Code Explanations
Code Example 1: Control (animateColorAsState vs. Animatable)
This example shows dropping down a level to get more control over an animation.

Kotlin

// High-level API: Simple but less control
val color = animateColorAsState(if (condition) Color.Green else Color.Red)

// Lower-level API: More complex but more control
val color = remember { Animatable(Color.Gray) } // 1. Create and remember Animatable
LaunchedEffect(condition) {                     // 2. Launch an effect when `condition` changes
    color.animateTo(if (condition) Color.Green else Color.Red) // 3. Animate to the new color
}
Line 1: remember { Animatable(Color.Gray) } creates a state object that can animate its value. Unlike animateColorAsState, you can set an initial value (Color.Gray) that is independent of the condition.

Line 2: LaunchedEffect(condition) is a coroutine that runs whenever the condition value changes.

Line 3: color.animateTo(...) is the function that actually performs the animation from the current color to the target color.

Code Example 2: Customization (Forking a Button)
This example shows how to create a custom button with a gradient background, a feature the standard Material Button doesn't offer.

Kotlin

@Composable
fun GradientButton(
    // ...
    background: List<Color>,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row( // 1. Use a basic Row instead of a Material Surface
        // ...
        modifier = modifier
            .clickable(onClick = {}) // 2. Add click handling
            .background( // 3. Apply a custom gradient background
                Brush.horizontalGradient(background)
            )
    ) {
        // 4. Reuse Material concepts for text style and alpha
        CompositionLocalProvider(/* ... */) {
            ProvideTextStyle(MaterialTheme.typography.button) {
                content()
            }
        }
    }
}
Line 1: Instead of using the Material Surface component, this custom button uses a more fundamental Row from the foundation layer.

Line 2: The clickable modifier is added to make the Row interactive, just like a real button.

Line 3: The background modifier is used with a Brush to create the gradient effect, which is the core customization.

Line 4: It still uses CompositionLocalProvider and ProvideTextStyle from the Material layer to ensure the button's text style and content alpha (for enabled/disabled states) behave consistently with other Material components.

What is the Use of These Concepts?
The core purpose of this layered architecture is to give you flexibility and power without sacrificing convenience.

For Rapid Development: You can quickly build standard UIs using the high-level Material components (Button, Card, Scaffold). These components handle a lot of work for you, including theming, accessibility, and animations.

For Custom Design Systems: If your app has a unique design that isn't Material, you can ignore the Material layer and build your own component library directly on top of the Foundation layer (Row, Column, LazyColumn, gesture modifiers).

For Maximum Control: If you need to create a highly specific UI element or a custom interaction that doesn't exist, you can drop all the way down to the UI layer and work with low-level concepts like custom layouts and drawing commands.

This prevents you from ever getting "stuck." If a high-level component doesn't do exactly what you need, you are never forced to abandon Compose; you just move one layer down to get the control you require.
*/