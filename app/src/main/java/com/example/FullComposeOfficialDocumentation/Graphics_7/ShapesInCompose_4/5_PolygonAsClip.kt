package com.example.FullComposeOfficialDocumentation.Graphics_7.ShapesInCompose_4

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.star
import androidx.graphics.shapes.toPath
import com.example.FullComposeOfficialDocumentation.R
import kotlin.math.max

/*
USE POLYGON AS CLIP

It's common to use the clip modifier in Compose to change how a composable is rendered
The polygon can be used as a clip shape to leverage features like shadows

Steps:
1. Create an extension function to get bounds from RoundedPolygon
2. Create a custom Shape class (RoundedPolygonShape) that implements Shape
3. In createOutline, convert the polygon to a Path and apply transformations
4. Use the shape with clip() modifier
*/

/*
Extension Function: Get Bounds from Polygon

This helper function calculates the bounding rectangle of a polygon
*/

fun RoundedPolygon.getBounds() = calculateBounds().let {
    Rect(it[0], it[1], it[2], it[3])
}

/*
Custom Shape: RoundedPolygonShape

This class wraps a RoundedPolygon to make it usable as a Compose Shape
It handles scaling and translating the polygon to fit the composable size
*/

class RoundedPolygonShape(
    private val polygon: RoundedPolygon,
    private var matrix: Matrix = Matrix()
) : Shape {
    private var path = Path()

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        path.rewind()
        path = polygon.toPath().asComposePath()
        matrix.reset()

        val bounds = polygon.getBounds()
        val maxDimension = max(bounds.width, bounds.height)

        // Scale to fit the size
        matrix.scale(size.width / maxDimension, size.height / maxDimension)

        // Translate to remove any offset
        matrix.translate(-bounds.left, -bounds.top)

        path.transform(matrix)
        return Outline.Generic(path)
    }
}

/*
Basic Hexagon Clip Example

Clips a Box with text using a hexagon shape
*/

@Composable
fun BasicHexagonClipExample() {
    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon)
    }

    Box(
        modifier = Modifier
            .clip(clip)
            .background(MaterialTheme.colorScheme.secondary)
            .size(200.dp)
    ) {
        Text(
            "Hello Compose",
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Image Clipped with Polygon

Clips an image using a hexagon shape and applies a shadow around the clipped region
This demonstrates leveraging graphicsLayer for shadows with custom shapes
*/

@Composable
fun ImageClippedWithPolygonExample() {
    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Clipped Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .graphicsLayer {
                    this.shadowElevation = 6.dp.toPx()
                    this.shape = clip
                    this.clip = true
                    this.ambientShadowColor = Color.Black
                    this.spotShadowColor = Color.Black
                }
                .size(200.dp)
        )
    }
}

/*
Different Polygon Clips

Examples of various polygon shapes used as clips
*/

@Composable
fun TriangleClipExample() {
    val triangle = remember {
        RoundedPolygon(
            3,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(triangle) {
        RoundedPolygonShape(polygon = triangle)
    }

    Box(
        modifier = Modifier
            .clip(clip)
            .background(Color(0xFF4CAF50))
            .size(150.dp)
    ) {
        Text(
            "Triangle",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun StarClipExample() {
    val star = remember {
        RoundedPolygon.star(
            numVerticesPerRadius = 5,
            rounding = CornerRounding(0.15f)
        )
    }
    val clip = remember(star) {
        RoundedPolygonShape(polygon = star)
    }

    Box(
        modifier = Modifier
            .clip(clip)
            .background(Color(0xFFFFD700))
            .size(150.dp)
    ) {
        Text(
            "Star",
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun OctagonClipExample() {
    val octagon = remember {
        RoundedPolygon(
            8,
            rounding = CornerRounding(0.15f)
        )
    }
    val clip = remember(octagon) {
        RoundedPolygonShape(polygon = octagon)
    }

    Box(
        modifier = Modifier
            .clip(clip)
            .background(Color(0xFF9C27B0))
            .size(150.dp)
    ) {
        Text(
            "Octagon",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Polygon Clip with Shadow Example

Demonstrates using graphicsLayer to apply shadows to custom polygon shapes
*/

@Composable
fun PolygonClipWithShadowExample() {
    val pentagon = remember {
        RoundedPolygon(
            5,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(pentagon) {
        RoundedPolygonShape(polygon = pentagon)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    this.shadowElevation = 8.dp.toPx()
                    this.shape = clip
                    this.clip = true
                    this.ambientShadowColor = Color.Black
                    this.spotShadowColor = Color.Black
                }
                .background(Color(0xFF2196F3))
                .size(180.dp)
        ) {
            Text(
                "Pentagon\nwith Shadow",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

/*
Polygon Clip Showcase

Displays various polygon shapes used as clips
*/

@Composable
fun PolygonClipShowcase() {
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
            "Polygon as Clip",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Basic hexagon clip
        Text("Hexagon Clip", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        val hexagon = remember {
            RoundedPolygon(6, rounding = CornerRounding(0.2f))
        }
        val hexClip = remember(hexagon) {
            RoundedPolygonShape(polygon = hexagon)
        }
        Box(
            modifier = Modifier
                .clip(hexClip)
                .background(Color(0xFF2196F3))
                .size(150.dp)
        ) {
            Text(
                "Hexagon",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Triangle clip
        Text("Triangle Clip", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        TriangleClipExample()

        // Star clip
        Text("Star Clip", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        StarClipExample()

        // Octagon clip
        Text("Octagon Clip", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        OctagonClipExample()

        // Pentagon with shadow
        Text("Pentagon with Shadow", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            val pentagon = remember {
                RoundedPolygon(5, rounding = CornerRounding(0.2f))
            }
            val pentClip = remember(pentagon) {
                RoundedPolygonShape(polygon = pentagon)
            }
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        this.shadowElevation = 8.dp.toPx()
                        this.shape = pentClip
                        this.clip = true
                        this.ambientShadowColor = Color.Black
                        this.spotShadowColor = Color.Black
                    }
                    .background(Color(0xFFFF9800))
                    .size(140.dp)
            ) {
                Text(
                    "Shadow",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

