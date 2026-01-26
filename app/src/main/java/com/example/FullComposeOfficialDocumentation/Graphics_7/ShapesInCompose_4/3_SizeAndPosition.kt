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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

/*
SIZE AND POSITION

By default, a shape is created with a radius of 1 around the center (0, 0)
This radius represents the distance between the center and the exterior vertices of the polygon

Note: Rounding the corners results in a smaller shape since the rounded corners
will be closer to the center than the vertices being rounded

To adjust the polygon:
- Size: Adjust the radius value
- Position: Change centerX or centerY
- Alternative: Use DrawScope transformation functions like translate(), scale(), rotate()
*/

/*
Default Size and Position

Shape with radius of 1 at center (0, 0)
This needs to be scaled and translated to be visible
*/

@Composable
fun DefaultSizePositionExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val polygon = RoundedPolygon(
                    numVertices = 6,
                    // Default radius = 1, center = (0, 0)
                )
                val polygonPath = polygon.toPath().asComposePath()

                onDrawBehind {
                    // Scale and translate to make it visible
                    val scaleFactor = size.width / 2f
                    translate(size.width / 2f, size.height / 2f) {
                        scale(scaleFactor, scaleFactor) {
                            drawPath(polygonPath, color = Color(0xFF2196F3))
                        }
                    }
                }
            }
    )
}

/*
Custom Radius Example

Adjusting the radius parameter to control the size of the polygon
*/

@Composable
fun CustomRadiusExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Small Radius", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .drawWithCache {
                    val smallPolygon = RoundedPolygon(
                        numVertices = 5,
                        radius = size.minDimension / 4,  // Small radius
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val smallPath = smallPolygon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(smallPath, color = Color(0xFF4CAF50))
                    }
                }
        )

        Text("Large Radius", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .drawWithCache {
                    val largePolygon = RoundedPolygon(
                        numVertices = 5,
                        radius = size.minDimension / 2,  // Large radius
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val largePath = largePolygon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(largePath, color = Color(0xFFFF9800))
                    }
                }
        )
    }
}

/*
Custom Position Example

Adjusting centerX and centerY to position the polygon
*/

@Composable
fun CustomPositionExample() {
    Box(
        modifier = Modifier
            .size(300.dp)
            .drawWithCache {
                // Top-left positioned polygon
                val topLeftPolygon = RoundedPolygon(
                    numVertices = 4,
                    radius = 50f,
                    centerX = 75f,
                    centerY = 75f
                )

                // Bottom-right positioned polygon
                val bottomRightPolygon = RoundedPolygon(
                    numVertices = 6,
                    radius = 50f,
                    centerX = size.width - 75f,
                    centerY = size.height - 75f
                )

                // Center positioned polygon
                val centerPolygon = RoundedPolygon(
                    numVertices = 8,
                    radius = 60f,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )

                val topLeftPath = topLeftPolygon.toPath().asComposePath()
                val bottomRightPath = bottomRightPolygon.toPath().asComposePath()
                val centerPath = centerPolygon.toPath().asComposePath()

                onDrawBehind {
                    drawPath(topLeftPath, color = Color(0xFF2196F3))
                    drawPath(bottomRightPath, color = Color(0xFFFF9800))
                    drawPath(centerPath, color = Color(0xFF9C27B0))
                }
            }
    )
}

/*
Using DrawScope Transformations

Alternative approach using translate() and scale() instead of centerX/centerY
*/

@Composable
fun TransformationExample() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val polygon = RoundedPolygon(
                    numVertices = 6,
                    radius = 1f,  // Default radius
                    rounding = CornerRounding(0.2f)
                )
                val polygonPath = polygon.toPath().asComposePath()

                onDrawBehind {
                    // Use scale and translate transformations
                    val scaleFactor = size.minDimension / 2.5f
                    translate(size.width / 2f, size.height / 2f) {
                        scale(scaleFactor, scaleFactor) {
                            drawPath(polygonPath, color = Color(0xFFE91E63))
                        }
                    }
                }
            }
    )
}

/*
Effect of Rounding on Size

Demonstrates how corner rounding makes the shape smaller
*/

@Composable
fun RoundingEffectOnSizeExample() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Sharp Corners (Larger)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val sharpPolygon = RoundedPolygon(
                        numVertices = 6,
                        radius = size.minDimension / 2,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val sharpPath = sharpPolygon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(sharpPath, color = Color(0xFF2196F3))
                    }
                }
        )

        Text("Rounded Corners (Smaller)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(150.dp)
                .drawWithCache {
                    val roundedPolygon = RoundedPolygon(
                        numVertices = 6,
                        radius = size.minDimension / 2,  // Same radius
                        centerX = size.width / 2,
                        centerY = size.height / 2,
                        rounding = CornerRounding(size.minDimension / 6f)  // But rounded
                    )
                    val roundedPath = roundedPolygon.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(roundedPath, color = Color(0xFFFF9800))
                    }
                }
        )
    }
}

/*
Size and Position Showcase

Comprehensive examples of sizing and positioning polygons
*/

@Composable
fun SizeAndPositionShowcase() {
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
            "Size and Position",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text("Small, Medium, Large Sizes", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(300.dp)
                .drawWithCache {
                    val small = RoundedPolygon(
                        numVertices = 5,
                        radius = 30f,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val medium = RoundedPolygon(
                        numVertices = 5,
                        radius = 60f,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )
                    val large = RoundedPolygon(
                        numVertices = 5,
                        radius = 90f,
                        centerX = size.width / 2,
                        centerY = size.height / 2
                    )

                    val smallPath = small.toPath().asComposePath()
                    val mediumPath = medium.toPath().asComposePath()
                    val largePath = large.toPath().asComposePath()

                    onDrawBehind {
                        drawPath(largePath, color = Color(0xFFE3F2FD))
                        drawPath(mediumPath, color = Color(0xFF90CAF9))
                        drawPath(smallPath, color = Color(0xFF2196F3))
                    }
                }
        )

        Text("Multiple Positioned Polygons", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(300.dp)
                .drawWithCache {
                    val positions = listOf(
                        Pair(75f, 75f),
                        Pair(size.width - 75f, 75f),
                        Pair(75f, size.height - 75f),
                        Pair(size.width - 75f, size.height - 75f),
                        Pair(size.width / 2, size.height / 2)
                    )

                    val colors = listOf(
                        Color(0xFF4CAF50),
                        Color(0xFF2196F3),
                        Color(0xFFFF9800),
                        Color(0xFFE91E63),
                        Color(0xFF9C27B0)
                    )

                    val paths = positions.mapIndexed { index, (x, y) ->
                        val polygon = RoundedPolygon(
                            numVertices = 3 + index,
                            radius = 40f,
                            centerX = x,
                            centerY = y,
                            rounding = CornerRounding(5f)
                        )
                        polygon.toPath().asComposePath()
                    }

                    onDrawBehind {
                        paths.forEachIndexed { index, path ->
                            drawPath(path, color = colors[index])
                        }
                    }
                }
        )

        Text("Using Transformations", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .drawWithCache {
                    val polygon = RoundedPolygon(
                        numVertices = 6,
                        radius = 1f,
                        rounding = CornerRounding(0.2f)
                    )
                    val polygonPath = polygon.toPath().asComposePath()

                    onDrawBehind {
                        val scaleFactor = size.minDimension / 2.2f
                        translate(size.width / 2f, size.height / 2f) {
                            scale(scaleFactor, scaleFactor) {
                                drawPath(polygonPath, color = Color(0xFF00BCD4))
                            }
                        }
                    }
                }
        )
    }
}
