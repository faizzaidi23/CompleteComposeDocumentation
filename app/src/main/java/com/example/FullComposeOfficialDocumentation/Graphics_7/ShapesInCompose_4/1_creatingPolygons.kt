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
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

/*
CREATE POLYGONS

The graphics-shapes library provides tools to create and manipulate polygons in Compose
A RoundedPolygon holds the geometry representing a requested shape

To draw a polygon in Compose:
1. Create a RoundedPolygon with desired parameters
2. Convert it to a Path using toPath()
3. Convert the Path to Compose Path using asComposePath()
4. Draw the path using DrawScope
*/

/*
Basic Hexagon Example

This creates a basic polygon shape with 6 points (hexagon) in the center of the drawing area
The polygon is created with:
- numVertices: Number of corners/sides (6 for hexagon)
- radius: Distance from center to vertices
- centerX, centerY: Position of the polygon center
*/

@Composable
fun BasicHexagonExample() {
    Box(
        modifier = Modifier
            .drawWithCache {
                // Create a hexagon (6 vertices)
                val roundedPolygon = RoundedPolygon(
                    numVertices = 6,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )

                // Convert to Compose Path
                val roundedPolygonPath = roundedPolygon.toPath().asComposePath()

                // Draw the path
                onDrawBehind {
                    drawPath(roundedPolygonPath, color = Color.Blue)
                }
            }
            .fillMaxSize()
    )
}

/*
Different Polygon Shapes

You can create various polygon shapes by changing the numVertices parameter
*/

@Composable
fun TriangleExample() {
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
}

@Composable
fun SquareExample() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .drawWithCache {
                val square = RoundedPolygon(
                    numVertices = 4,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )
                val squarePath = square.toPath().asComposePath()

                onDrawBehind {
                    drawPath(squarePath, color = Color(0xFF2196F3))
                }
            }
    )
}

@Composable
fun PentagonExample() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .drawWithCache {
                val pentagon = RoundedPolygon(
                    numVertices = 5,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )
                val pentagonPath = pentagon.toPath().asComposePath()

                onDrawBehind {
                    drawPath(pentagonPath, color = Color(0xFFFF9800))
                }
            }
    )
}

@Composable
fun OctagonExample() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .drawWithCache {
                val octagon = RoundedPolygon(
                    numVertices = 8,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )
                val octagonPath = octagon.toPath().asComposePath()

                onDrawBehind {
                    drawPath(octagonPath, color = Color(0xFF9C27B0))
                }
            }
    )
}

/*
Showcase of Different Polygon Shapes

Displays various polygon types side by side
*/

@Composable
fun PolygonShapesShowcase() {
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
            "Creating Polygons",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Triangle (3 vertices)
        Text("Triangle (3 vertices)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
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

        // Square (4 vertices)
        Text("Square (4 vertices)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val square = RoundedPolygon(
                        numVertices = 4,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val squarePath = square.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(squarePath, color = Color(0xFF2196F3))
                    }
                }
        )

        // Pentagon (5 vertices)
        Text("Pentagon (5 vertices)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val pentagon = RoundedPolygon(
                        numVertices = 5,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val pentagonPath = pentagon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(pentagonPath, color = Color(0xFFFF9800))
                    }
                }
        )

        // Hexagon (6 vertices)
        Text("Hexagon (6 vertices)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val hexagon = RoundedPolygon(
                        numVertices = 6,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val hexagonPath = hexagon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(hexagonPath, color = Color.Blue)
                    }
                }
        )

        // Octagon (8 vertices)
        Text("Octagon (8 vertices)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val octagon = RoundedPolygon(
                        numVertices = 8,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val octagonPath = octagon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(octagonPath, color = Color(0xFF9C27B0))
                    }
                }
        )

        // Dodecagon (12 vertices)
        Text("Dodecagon (12 vertices)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val dodecagon = RoundedPolygon(
                        numVertices = 12,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val dodecagonPath = dodecagon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(dodecagonPath, color = Color(0xFFE91E63))
                    }
                }
        )
    }
}
