package com.example.FullComposeOfficialDocumentation.Graphics_7.ShapesInCompose_4

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath

/*
MORPH BUTTON ON CLICK

You can use the graphics-shape library to create a button that morphs between
two shapes on press

Steps:
1. Create a MorphPolygonShape that extends Shape
2. Scale and translate the morph to fit appropriately
3. Pass in the progress so that the shape can be animated
4. Use interactionSource to detect press state
5. Animate the morph based on the press state
*/

/*
MorphPolygonShape Class

A custom Shape that represents a morph between two polygons at a given progress
This assumes default radius of 1f and center at (0, 0)
*/

class MorphPolygonShape(
    private val morph: Morph,
    private val percentage: Float
) : Shape {

    private val matrix = Matrix()

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Scale the path to fit the container
        // By default this stretches to size
        // For non-stretching, use same value for x and y
        matrix.scale(size.width / 2f, size.height / 2f)
        matrix.translate(1f, 1f)

        val path = morph.toPath(progress = percentage).asComposePath()
        path.transform(matrix)
        return Outline.Generic(path)
    }
}

/*
Basic Morph Button on Click

This button morphs from a hexagon to a star when pressed
Uses interactionSource to detect press state
*/

@Composable
fun MorphButtonOnClickExample() {
    val shapeA = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            6,
            rounding = CornerRounding(0.1f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(
            dampingRatio = 0.4f,
            stiffness = Spring.StiffnessMedium
        )
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp)
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .background(Color(0xFF80DEEA))
            .clickable(interactionSource = interactionSource, indication = null) {}
    ) {
        Text(
            "Click Me",
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold
        )
    }
}

/*
Triangle to Star Morph Button

Morphs from triangle to star on press with spring animation
*/

@Composable
fun TriangleToStarButtonExample() {
    val triangle = remember {
        RoundedPolygon(
            3,
            rounding = CornerRounding(0.2f)
        )
    }
    val star = remember {
        RoundedPolygon.star(
            5,
            rounding = CornerRounding(0.15f)
        )
    }
    val morph = remember {
        Morph(triangle, star)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(
            dampingRatio = 0.5f,
            stiffness = Spring.StiffnessLow
        )
    )

    Box(
        modifier = Modifier
            .size(180.dp)
            .padding(8.dp)
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .background(Color(0xFFFFD700))
            .clickable(interactionSource = interactionSource, indication = null) {}
    ) {
        Text(
            "Press",
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

/*
Square to Circle Morph Button

Morphs from square to circle (approximated with rounded polygon)
*/

@Composable
fun SquareToCircleButtonExample() {
    val square = remember {
        RoundedPolygon(
            4,
            rounding = CornerRounding(0.1f)
        )
    }
    val circle = remember {
        RoundedPolygon(
            12,
            rounding = CornerRounding(0.5f, smoothing = 1f)
        )
    }
    val morph = remember {
        Morph(square, circle)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(
            dampingRatio = 0.3f,
            stiffness = Spring.StiffnessMediumLow
        )
    )

    Box(
        modifier = Modifier
            .size(160.dp)
            .padding(8.dp)
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .background(Color(0xFF9C27B0))
            .clickable(interactionSource = interactionSource, indication = null) {}
    ) {
        Text(
            "Tap",
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/*
Pentagon to Octagon Morph Button

Different polygon vertex counts with smooth animation
*/

@Composable
fun PentagonToOctagonButtonExample() {
    val pentagon = remember {
        RoundedPolygon(
            5,
            rounding = CornerRounding(0.2f)
        )
    }
    val octagon = remember {
        RoundedPolygon(
            8,
            rounding = CornerRounding(0.2f)
        )
    }
    val morph = remember {
        Morph(pentagon, octagon)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = Spring.StiffnessMedium
        )
    )

    Box(
        modifier = Modifier
            .size(180.dp)
            .padding(8.dp)
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .background(Color(0xFF4CAF50))
            .clickable(interactionSource = interactionSource, indication = null) {}
    ) {
        Text(
            "Click",
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

/*
Morph Button Showcase

Displays various interactive morph buttons
*/

@Composable
fun MorphButtonShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Morph Button on Click",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Click/Press the buttons to see them morph!",
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Hexagon to Star
        Text("Hexagon ↔ Star", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        MorphButtonOnClickExample()

        // Triangle to Star
        Text("Triangle ↔ Star", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        TriangleToStarButtonExample()

        // Square to Circle
        Text("Square ↔ Circle", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        SquareToCircleButtonExample()

        // Pentagon to Octagon
        Text("Pentagon ↔ Octagon", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        PentagonToOctagonButtonExample()
    }
}

