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
import androidx.compose.runtime.remember
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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/*
CUSTOM POLYGONS

If shapes created from regular polygons don't cover your use case,
you can create a more custom shape with a list of vertices

Instead of using numVertices parameter, use the RoundedPolygon overload that
takes a float array of x, y coordinates

Example: Creating a heart shape by specifying individual vertices
Using polar coordinates (angle and radius) makes it easier than cartesian (x, y)

Polar coordinate system:
- 0° starts on the right hand side
- Proceeds clockwise
- 270° is at the 12 o'clock position
*/

/*
Helper Classes and Functions

PointF represents a 2D point
*/

internal data class PointF(val x: Float, val y: Float) {
    operator fun plus(other: PointF) = PointF(x + other.x, y + other.y)
    operator fun times(factor: Float) = PointF(x * factor, y * factor)
}

/*
Extension function to convert degrees to radians
*/

internal fun Float.toRadians() = this * PI.toFloat() / 180f

/*
Zero point constant
*/

internal val PointZero = PointF(0f, 0f)

/*
Convert polar coordinates (radius, angle) to cartesian coordinates (x, y)
*/

internal fun radialToCartesian(
    radius: Float,
    angleRadians: Float,
    center: PointF = PointZero
) = directionVectorPointF(angleRadians) * radius + center

/*
Get direction vector from angle
*/

internal fun directionVectorPointF(angleRadians: Float) =
    PointF(cos(angleRadians), sin(angleRadians))

/*
Heart Shape Vertices

Define the vertices of a heart shape using polar coordinates
The heart is composed of 6 points:
- Two side curves (radiusSides)
- Two top humps (radius)
- One bottom point (innerRadius)
*/

@Composable
fun HeartShapeExample() {
    val vertices = remember {
        val radius = 1f
        val radiusSides = 0.8f
        val innerRadius = 0.1f

        floatArrayOf(
            // Right side (0°)
            radialToCartesian(radiusSides, 0f.toRadians()).x,
            radialToCartesian(radiusSides, 0f.toRadians()).y,
            // Right top hump (90°)
            radialToCartesian(radius, 90f.toRadians()).x,
            radialToCartesian(radius, 90f.toRadians()).y,
            // Left side (180°)
            radialToCartesian(radiusSides, 180f.toRadians()).x,
            radialToCartesian(radiusSides, 180f.toRadians()).y,
            // Left bottom curve (250°)
            radialToCartesian(radius, 250f.toRadians()).x,
            radialToCartesian(radius, 250f.toRadians()).y,
            // Bottom point (270°)
            radialToCartesian(innerRadius, 270f.toRadians()).x,
            radialToCartesian(innerRadius, 270f.toRadians()).y,
            // Right bottom curve (290°)
            radialToCartesian(radius, 290f.toRadians()).x,
            radialToCartesian(radius, 290f.toRadians()).y,
        )
    }

    // Define corner rounding for each vertex
    val rounding = remember {
        val roundingNormal = 0.6f
        val roundingNone = 0f
        listOf(
            CornerRounding(roundingNormal),     // Right side - rounded
            CornerRounding(roundingNone),       // Right top - sharp
            CornerRounding(roundingNormal),     // Left side - rounded
            CornerRounding(roundingNormal),     // Left bottom - rounded
            CornerRounding(roundingNone),       // Bottom point - sharp
            CornerRounding(roundingNormal),     // Right bottom - rounded
        )
    }

    val polygon = remember(vertices, rounding) {
        RoundedPolygon(
            vertices = vertices,
            perVertexRounding = rounding
        )
    }

    Box(
        modifier = Modifier
            .size(400.dp)
            .drawWithCache {
                val roundedPolygonPath = polygon.toPath().asComposePath()
                onDrawBehind {
                    val scaleFactor = size.width * 0.5f
                    translate(size.width * 0.5f, size.height * 0.5f) {
                        scale(scaleFactor, scaleFactor) {
                            drawPath(roundedPolygonPath, color = Color(0xFFF15087))
                        }
                    }
                }
            }
    )
}

/*
Sharp Heart (No Rounding)

Same vertices but without corner rounding
*/

@Composable
fun SharpHeartExample() {
    val vertices = remember {
        val radius = 1f
        val radiusSides = 0.8f
        val innerRadius = 0.1f

        floatArrayOf(
            radialToCartesian(radiusSides, 0f.toRadians()).x,
            radialToCartesian(radiusSides, 0f.toRadians()).y,
            radialToCartesian(radius, 90f.toRadians()).x,
            radialToCartesian(radius, 90f.toRadians()).y,
            radialToCartesian(radiusSides, 180f.toRadians()).x,
            radialToCartesian(radiusSides, 180f.toRadians()).y,
            radialToCartesian(radius, 250f.toRadians()).x,
            radialToCartesian(radius, 250f.toRadians()).y,
            radialToCartesian(innerRadius, 270f.toRadians()).x,
            radialToCartesian(innerRadius, 270f.toRadians()).y,
            radialToCartesian(radius, 290f.toRadians()).x,
            radialToCartesian(radius, 290f.toRadians()).y,
        )
    }

    val polygon = remember(vertices) {
        RoundedPolygon(vertices = vertices)
    }

    Box(
        modifier = Modifier
            .size(300.dp)
            .drawWithCache {
                val roundedPolygonPath = polygon.toPath().asComposePath()
                onDrawBehind {
                    val scaleFactor = size.width * 0.5f
                    translate(size.width * 0.5f, size.height * 0.5f) {
                        scale(scaleFactor, scaleFactor) {
                            drawPath(roundedPolygonPath, color = Color(0xFFE91E63))
                        }
                    }
                }
            }
    )
}

/*
Custom Diamond Shape

A diamond shape defined with custom vertices
*/

@Composable
fun CustomDiamondExample() {
    val vertices = remember {
        floatArrayOf(
            // Top point
            radialToCartesian(1f, 270f.toRadians()).x,
            radialToCartesian(1f, 270f.toRadians()).y,
            // Right point
            radialToCartesian(0.7f, 0f.toRadians()).x,
            radialToCartesian(0.7f, 0f.toRadians()).y,
            // Bottom point
            radialToCartesian(1f, 90f.toRadians()).x,
            radialToCartesian(1f, 90f.toRadians()).y,
            // Left point
            radialToCartesian(0.7f, 180f.toRadians()).x,
            radialToCartesian(0.7f, 180f.toRadians()).y,
        )
    }

    val rounding = remember {
        listOf(
            CornerRounding(0.1f),
            CornerRounding(0.1f),
            CornerRounding(0.1f),
            CornerRounding(0.1f),
        )
    }

    val polygon = remember(vertices, rounding) {
        RoundedPolygon(
            vertices = vertices,
            perVertexRounding = rounding
        )
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val roundedPolygonPath = polygon.toPath().asComposePath()
                onDrawBehind {
                    val scaleFactor = size.width * 0.5f
                    translate(size.width * 0.5f, size.height * 0.5f) {
                        scale(scaleFactor, scaleFactor) {
                            drawPath(roundedPolygonPath, color = Color(0xFF00BCD4))
                        }
                    }
                }
            }
    )
}

/*
Custom Arrow Shape

An arrow pointing right
*/

@Composable
fun CustomArrowExample() {
    val vertices = remember {
        floatArrayOf(
            // Arrow shaft left
            radialToCartesian(0.5f, 180f.toRadians()).x,
            radialToCartesian(0.3f, 180f.toRadians()).y - 0.2f,
            // Arrow shaft right (before point)
            radialToCartesian(0.5f, 0f.toRadians()).x - 0.3f,
            radialToCartesian(0.3f, 0f.toRadians()).y - 0.2f,
            // Arrow point top
            radialToCartesian(0.5f, 0f.toRadians()).x - 0.3f,
            radialToCartesian(0.5f, 270f.toRadians()).y,
            // Arrow tip
            radialToCartesian(1f, 0f.toRadians()).x,
            0f,
            // Arrow point bottom
            radialToCartesian(0.5f, 0f.toRadians()).x - 0.3f,
            radialToCartesian(0.5f, 90f.toRadians()).y,
            // Arrow shaft right (after point)
            radialToCartesian(0.5f, 0f.toRadians()).x - 0.3f,
            radialToCartesian(0.3f, 0f.toRadians()).y + 0.2f,
            // Arrow shaft left
            radialToCartesian(0.5f, 180f.toRadians()).x,
            radialToCartesian(0.3f, 180f.toRadians()).y + 0.2f,
        )
    }

    val rounding = remember {
        listOf(
            CornerRounding(0.1f),
            CornerRounding(0.1f),
            CornerRounding(0.0f),
            CornerRounding(0.0f),
            CornerRounding(0.0f),
            CornerRounding(0.1f),
            CornerRounding(0.1f),
        )
    }

    val polygon = remember(vertices, rounding) {
        RoundedPolygon(
            vertices = vertices,
            perVertexRounding = rounding
        )
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .drawWithCache {
                val roundedPolygonPath = polygon.toPath().asComposePath()
                onDrawBehind {
                    val scaleFactor = size.width * 0.5f
                    translate(size.width * 0.5f, size.height * 0.5f) {
                        scale(scaleFactor, scaleFactor) {
                            drawPath(roundedPolygonPath, color = Color(0xFF4CAF50))
                        }
                    }
                }
            }
    )
}

/*
Custom Polygons Showcase

Displays various custom polygon shapes
*/

@Composable
fun CustomPolygonsShowcase() {
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
            "Custom Polygons",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Shapes created with custom vertices using polar coordinates",
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Heart shape (rounded)
        Text("Heart Shape (Rounded)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("6 vertices with selective rounding", fontSize = 12.sp, color = Color.Gray)
        HeartShapeExample()

        // Sharp heart
        Text("Heart Shape (Sharp)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("Same vertices, no rounding", fontSize = 12.sp, color = Color.Gray)
        SharpHeartExample()

        // Custom diamond
        Text("Custom Diamond", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("4 vertices with varying radii", fontSize = 12.sp, color = Color.Gray)
        CustomDiamondExample()

        // Custom arrow
        Text("Custom Arrow", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("7 vertices forming arrow shape", fontSize = 12.sp, color = Color.Gray)
        CustomArrowExample()

        // Note about custom shapes
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "Note: For arbitrary shapes beyond rounded polygons, " +
                "consider using the Path class or loading ImageVector files. " +
                "The graphics-shapes library is specifically designed for rounded polygons " +
                "and morph animations between them.",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
