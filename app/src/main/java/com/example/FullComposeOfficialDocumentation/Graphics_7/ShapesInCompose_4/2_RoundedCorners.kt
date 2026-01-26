package com.example.FullComposeOfficialDocumentation.Graphics_7.ShapesInCompose_4

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

/*
ROUND THE CORNERS OF A POLYGON

To round the corners of a polygon, use the CornerRounding parameter
This takes two parameters: radius and smoothing

Each rounded corner is made up of 1-3 cubic curves:
- The center has a circular arc shape
- Two side ("flanking") curves transition from the shape's edge to the center curve

RADIUS:
The radius is the radius of the circle used to round a vertex
It determines the circular rounding size of rounded corners

SMOOTHING:
Smoothing is a factor which determines how long it takes to get from the circular
rounding portion of the corner to the edge
- smoothing = 0 (default): Purely circular corner rounding (single cubic curve)
- smoothing > 0 (up to 1.0): Corner rounded by three separate curves
*/

/*
Basic Rounded Triangle

This example shows a triangle with rounded corners using CornerRounding
*/

@Composable
fun BasicRoundedTriangleExample() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .drawWithCache {
                val roundedPolygon = RoundedPolygon(
                    numVertices = 3,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2,
                    rounding = CornerRounding(
                        size.minDimension / 10f,
                        smoothing = 0.1f
                    )
                )
                val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

                onDrawBehind {
                    drawPath(roundedPolygonPath, color = Color.Black)
                }
            }
    )
}

/*
Smoothing Comparison

Demonstrates the difference between smoothing = 0 and smoothing = 1
*/

@Composable
fun SmoothingComparisonExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Smoothing = 0 (Circular)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val roundedPolygon = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 10f,
                            smoothing = 0f
                        )
                    )
                    val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(roundedPolygonPath, color = Color(0xFF2196F3))
                    }
                }
        )

        Text("Smoothing = 1 (Maximum)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val roundedPolygon = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 10f,
                            smoothing = 1f
                        )
                    )
                    val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(roundedPolygonPath, color = Color(0xFFFF9800))
                    }
                }
        )
    }
}

/*
Different Rounding Radii

Shows how different rounding radius values affect the shape
*/

@Composable
fun RoundingRadiusExample() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .drawWithCache {
                val roundedPolygon = RoundedPolygon(
                    numVertices = 6,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2,
                    rounding = CornerRounding(
                        size.minDimension / 5f,  // Larger radius
                        smoothing = 0.3f
                    )
                )
                val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

                onDrawBehind {
                    drawPath(roundedPolygonPath, color = Color(0xFF9C27B0))
                }
            }
    )
}

/*
Rounded Shapes Showcase

Displays various shapes with different corner rounding configurations
*/

@Composable
fun RoundedShapesShowcase() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Rounded Corners",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Sharp triangle (no rounding)
        Text("Sharp Triangle (No Rounding)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val triangle = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val trianglePath = triangle.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(trianglePath, color = Color(0xFF4CAF50))
                    }
                }
        )

        // Rounded triangle (smoothing = 0)
        Text("Rounded Triangle (Smoothing = 0)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val roundedTriangle = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 10f,
                            smoothing = 0f
                        )
                    )
                    val roundedPath = roundedTriangle.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(roundedPath, color = Color(0xFF2196F3))
                    }
                }
        )

        // Smoothed triangle (smoothing = 0.5)
        Text("Smoothed Triangle (Smoothing = 0.5)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val smoothedTriangle = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 10f,
                            smoothing = 0.5f
                        )
                    )
                    val smoothedPath = smoothedTriangle.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(smoothedPath, color = Color(0xFFFF9800))
                    }
                }
        )

        // Maximum smoothing (smoothing = 1)
        Text("Maximum Smoothing (Smoothing = 1)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val maxSmoothedTriangle = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 10f,
                            smoothing = 1f
                        )
                    )
                    val maxSmoothedPath = maxSmoothedTriangle.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(maxSmoothedPath, color = Color(0xFFE91E63))
                    }
                }
        )

        // Rounded hexagon
        Text("Rounded Hexagon", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val roundedHexagon = RoundedPolygon(
                        numVertices = 6,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 8f,
                            smoothing = 0.2f
                        )
                    )
                    val hexagonPath = roundedHexagon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(hexagonPath, color = Color(0xFF9C27B0))
                    }
                }
        )

        // Heavily rounded square
        Text("Heavily Rounded Square", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val roundedSquare = RoundedPolygon(
                        numVertices = 4,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(
                            size.minDimension / 4f,
                            smoothing = 0.8f
                        )
                    )
                    val squarePath = roundedSquare.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(squarePath, color = Color(0xFF00BCD4))
                    }
                }
        )
    }
}

