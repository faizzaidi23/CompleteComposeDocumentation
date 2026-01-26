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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath

/*
MORPH SHAPES

A Morph object represents an animation between two polygonal shapes
To morph between shapes:
1. Create two RoundedPolygons (start and end shapes)
2. Create a Morph object that takes these two shapes
3. Provide a progress value between 0 and 1 to calculate the intermediate shape
   - progress = 0: Start shape
   - progress = 0.5: Halfway between shapes
   - progress = 1: End shape

In most scenarios, morphing is done as part of an animation
Use Compose Animation APIs to change the progress value over time
*/

/*
Static Morph Example

This shows a static morph at 50% progress between a triangle and square
*/

@Composable
fun StaticMorphExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val triangle = RoundedPolygon(
                    numVertices = 3,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.1f
                    )
                )
                val square = RoundedPolygon(
                    numVertices = 4,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f
                )

                val morph = Morph(start = triangle, end = square)
                val morphPath = morph
                    .toPath(progress = 0.5f)
                    .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color.Black)
                }
            }
    )
}

/*
Animated Morph Example

This infinitely animates between a triangle and square using rememberInfiniteTransition
*/

@Composable
fun AnimatedMorphExample() {
    val infiniteAnimation = rememberInfiniteTransition(label = "infinite animation")
    val morphProgress by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morph"
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val triangle = RoundedPolygon(
                    numVertices = 3,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.1f
                    )
                )
                val square = RoundedPolygon(
                    numVertices = 4,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f
                )

                val morph = Morph(start = triangle, end = square)
                val morphPath = morph
                    .toPath(progress = morphProgress)
                    .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color.Black)
                }
            }
    )
}

/*
Hexagon to Star Morph

Animates between a hexagon and a star shape
*/

@Composable
fun HexagonToStarMorphExample() {
    val infiniteAnimation = rememberInfiniteTransition(label = "hexagon to star")
    val morphProgress by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morph progress"
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val hexagon = RoundedPolygon(
                    numVertices = 6,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(size.minDimension / 12f)
                )
                val star = RoundedPolygon.star(
                    numVerticesPerRadius = 6,
                    radius = size.minDimension / 2f,
                    innerRadius = size.minDimension / 4f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(size.minDimension / 12f)
                )

                val morph = Morph(start = hexagon, end = star)
                val morphPath = morph
                    .toPath(progress = morphProgress)
                    .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color(0xFF2196F3))
                }
            }
    )
}

/*
Pentagon to Octagon Morph

Demonstrates morphing between shapes with different vertex counts
*/

@Composable
fun PentagonToOctagonMorphExample() {
    val infiniteAnimation = rememberInfiniteTransition(label = "pentagon to octagon")
    val morphProgress by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morph progress"
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val pentagon = RoundedPolygon(
                    numVertices = 5,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.3f
                    )
                )
                val octagon = RoundedPolygon(
                    numVertices = 8,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.3f
                    )
                )

                val morph = Morph(start = pentagon, end = octagon)
                val morphPath = morph
                    .toPath(progress = morphProgress)
                    .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color(0xFFFF9800))
                }
            }
    )
}

/*
Morph Shapes Showcase

Displays various morphing animations
*/

@Composable
fun MorphShapesShowcase() {
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
            "Shape Morphing",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Static morph at 50%
        Text("Static Morph (50% Progress)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("Triangle → Square", fontSize = 14.sp, color = Color.Gray)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val triangle = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2f,
                        centerX = size.width / 2f,
                        centerY = size.height / 2f,
                        rounding = CornerRounding(
                            size.minDimension / 10f,
                            smoothing = 0.1f
                        )
                    )
                    val square = RoundedPolygon(
                        numVertices = 4,
                        radius = size.minDimension / 2f,
                        centerX = size.width / 2f,
                        centerY = size.height / 2f
                    )

                    val morph = Morph(start = triangle, end = square)
                    val morphPath = morph.toPath(progress = 0.5f).asComposePath()

                    onDrawBehind {
                        drawPath(morphPath, color = Color.Black)
                    }
                }
        )

        // Animated triangle to square
        Text("Animated Morph", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("Triangle ↔ Square", fontSize = 14.sp, color = Color.Gray)
        AnimatedMorphExample()

        // Hexagon to star
        Text("Hexagon ↔ Star", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        HexagonToStarMorphExample()

        // Pentagon to octagon
        Text("Pentagon ↔ Octagon", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        PentagonToOctagonMorphExample()

        // Circle to square morph
        Text("Circle ↔ Square", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        CircleToSquareMorphExample()
    }
}

@Composable
fun CircleToSquareMorphExample() {
    val infiniteAnimation = rememberInfiniteTransition(label = "circle to square")
    val morphProgress by infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morph progress"
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                // Approximate circle with many vertices
                val circle = RoundedPolygon(
                    numVertices = 12,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 3f,
                        smoothing = 1f
                    )
                )
                val square = RoundedPolygon(
                    numVertices = 4,
                    radius = size.minDimension / 2f,
                    centerX = size.width / 2f,
                    centerY = size.height / 2f,
                    rounding = CornerRounding(
                        size.minDimension / 20f,
                        smoothing = 0.1f
                    )
                )

                val morph = Morph(start = circle, end = square)
                val morphPath = morph
                    .toPath(progress = morphProgress)
                    .asComposePath()

                onDrawBehind {
                    drawPath(morphPath, color = Color(0xFF9C27B0))
                }
            }
    )
}

