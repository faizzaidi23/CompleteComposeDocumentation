package com.example.FullComposeOfficialDocumentation.Graphics_7.ShapesInCompose_4

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath

/*
COMPREHENSIVE SHAPES IN COMPOSE EXAMPLES

This file demonstrates all the major Shapes concepts in Jetpack Compose:

1. Creating Basic Polygons (Triangle, Square, Pentagon, Hexagon, etc.)
2. Rounding Corners (Radius and Smoothing)
3. Size and Position (Adjusting radius, centerX, centerY)
4. Morphing Shapes (Animating between shapes)
5. Using Polygons as Clips
6. Interactive Morph Buttons
7. Infinite Morph Animations
8. Custom Polygons (Heart, Diamond, Arrow)

This serves as a comprehensive reference and showcase for all Shape features
*/

/*
Main Showcase Composable

Displays all shape examples in a scrollable layout organized by category
*/

@Composable
fun ComprehensiveShapesShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Title
        Text(
            "Shapes in Compose - Complete Guide",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Using the graphics-shapes library for custom polygons",
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Section 1: Basic Polygons
        SectionTitle("1. Basic Polygons")
        BasicPolygonsSection()

        // Section 2: Rounded Corners
        SectionTitle("2. Rounded Corners")
        RoundedCornersSection()

        // Section 3: Shape Morphing
        SectionTitle("3. Shape Morphing")
        ShapeMorphingSection()

        // Section 4: Clipping Shapes
        SectionTitle("4. Using as Clips")
        ClippingShapesSection()

        // Section 5: Star Shapes
        SectionTitle("5. Star Shapes")
        StarShapesSection()
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1976D2),
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun ExampleCard(
    title: String,
    description: String = "",
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            content()
        }
    }
}

/*
Section 1: Basic Polygons
*/

@Composable
fun BasicPolygonsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Triangle
            ExampleCard("Triangle", "3 vertices") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .drawWithCache {
                            val triangle = RoundedPolygon(
                                numVertices = 3,
                                radius = size.minDimension / 2,
                                centerX = size.width / 2,
                                centerY = size.height / 2
                            )
                            val path = triangle.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFF4CAF50))
                            }
                        }
                )
            }

            // Square
            ExampleCard("Square", "4 vertices") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .drawWithCache {
                            val square = RoundedPolygon(
                                numVertices = 4,
                                radius = size.minDimension / 2,
                                centerX = size.width / 2,
                                centerY = size.height / 2
                            )
                            val path = square.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFF2196F3))
                            }
                        }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Hexagon
            ExampleCard("Hexagon", "6 vertices") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .drawWithCache {
                            val hexagon = RoundedPolygon(
                                numVertices = 6,
                                radius = size.minDimension / 2,
                                centerX = size.width / 2,
                                centerY = size.height / 2
                            )
                            val path = hexagon.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFFFF9800))
                            }
                        }
                )
            }

            // Octagon
            ExampleCard("Octagon", "8 vertices") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .drawWithCache {
                            val octagon = RoundedPolygon(
                                numVertices = 8,
                                radius = size.minDimension / 2,
                                centerX = size.width / 2,
                                centerY = size.height / 2
                            )
                            val path = octagon.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFF9C27B0))
                            }
                        }
                )
            }
        }
    }
}

/*
Section 2: Rounded Corners
*/

@Composable
fun RoundedCornersSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ExampleCard("Sharp vs Rounded", "Comparing corner styles") {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                // Sharp
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .drawWithCache {
                            val sharp = RoundedPolygon(
                                numVertices = 6,
                                radius = size.minDimension / 2,
                                centerX = size.width / 2,
                                centerY = size.height / 2
                            )
                            val path = sharp.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFF2196F3))
                            }
                        }
                )

                // Rounded
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .drawWithCache {
                            val rounded = RoundedPolygon(
                                numVertices = 6,
                                radius = size.minDimension / 2,
                                centerX = size.width / 2,
                                centerY = size.height / 2,
                                rounding = CornerRounding(size.minDimension / 6f)
                            )
                            val path = rounded.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFFFF9800))
                            }
                        }
                )
            }
        }
    }
}

/*
Section 3: Shape Morphing
*/

@Composable
fun ShapeMorphingSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ExampleCard("Triangle ↔ Square", "Animated morph") {
            val infiniteAnimation = rememberInfiniteTransition(label = "morph")
            val progress = infiniteAnimation.animateFloat(
                0f, 1f,
                infiniteRepeatable(tween(1000), RepeatMode.Reverse),
                label = "progress"
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .drawWithCache {
                        val triangle = RoundedPolygon(
                            numVertices = 3,
                            radius = size.minDimension / 2f,
                            centerX = size.width / 2f,
                            centerY = size.height / 2f,
                            rounding = CornerRounding(size.minDimension / 10f)
                        )
                        val square = RoundedPolygon(
                            numVertices = 4,
                            radius = size.minDimension / 2f,
                            centerX = size.width / 2f,
                            centerY = size.height / 2f
                        )
                        val morph = Morph(triangle, square)
                        val morphPath = morph.toPath(progress.value).asComposePath()

                        onDrawBehind {
                            drawPath(morphPath, color = Color(0xFF9C27B0))
                        }
                    }
            )
        }
    }
}

/*
Section 4: Clipping Shapes
*/

@Composable
fun ClippingShapesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ExampleCard("Hexagon Clip", "Shape used as clip") {
            val hexagon = remember {
                RoundedPolygon(6, rounding = CornerRounding(0.2f))
            }
            val clip = remember(hexagon) {
                RoundedPolygonShape(hexagon)
            }

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(clip)
                    .background(Color(0xFF00BCD4))
            ) {
                Text(
                    "Clipped",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

/*
Section 5: Star Shapes
*/

@Composable
fun StarShapesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ExampleCard("5-Point Star", "") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .drawWithCache {
                            val star = RoundedPolygon.star(
                                numVerticesPerRadius = 5,
                                radius = size.minDimension / 2,
                                innerRadius = size.minDimension / 4,
                                centerX = size.width / 2,
                                centerY = size.height / 2,
                                rounding = CornerRounding(size.minDimension / 20f)
                            )
                            val path = star.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFFFFD700))
                            }
                        }
                )
            }

            ExampleCard("6-Point Star", "") {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .drawWithCache {
                            val star = RoundedPolygon.star(
                                numVerticesPerRadius = 6,
                                radius = size.minDimension / 2,
                                innerRadius = size.minDimension / 4,
                                centerX = size.width / 2,
                                centerY = size.height / 2,
                                rounding = CornerRounding(size.minDimension / 20f)
                            )
                            val path = star.toPath().asComposePath()
                            onDrawBehind {
                                drawPath(path, color = Color(0xFFE91E63))
                            }
                        }
                )
            }
        }
    }
}

/*
SUMMARY

This file provides a comprehensive overview of all Shapes features in Compose:

FILE STRUCTURE:
1_creatingPolygons.kt - Basic polygon creation with different vertex counts
2_RoundedCorners.kt - Corner rounding with radius and smoothing
3_SizeAndPosition.kt - Adjusting size and position of polygons
4_MorphShapes.kt - Morphing between shapes with animation
5_PolygonAsClip.kt - Using polygons as clip shapes with shadows
6_MorphButtonOnClick.kt - Interactive morph buttons on press
7_InfiniteMorphAnimation.kt - Continuously morphing and rotating shapes
8_CustomPolygons.kt - Creating custom shapes like hearts and arrows
9_ComprehensiveExamples.kt - This file, showcasing all concepts

KEY CONCEPTS:
- RoundedPolygon creates polygon shapes with configurable vertices
- CornerRounding controls radius and smoothing of corners
- Morph enables smooth animation between two shapes
- Shapes can be used as clips with shadow support
- Custom vertices allow creating any polygon shape
- Infinite animations create dynamic visual effects
*/

