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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
MODIFIER.DRAWWITHCACHE - Drawing and caching draw objects

Modifier.drawWithCache keeps the objects that are created inside of it cached
The objects are cached as long as the size of the drawing area is the same
or any state objects that are read have not changed

This modifier is useful for improving performance of drawing calls
as it avoids the need to reallocate objects such as Brush Shader Path etc that are created on draw

Alternatively you could also cache objects using remember outside of the modifier
However this is not always possible as you do not always have access to the composition
It can be more performant to use drawWithCache if the objects are only used for drawing
*/

/*
Note: Only use Modifier.drawWithCache when you are creating objects that must be cached
Using this modifier without needing to cache objects can result in unnecessary lambda allocations
*/

/*
Caching a Brush object

For example if you create a Brush to draw a gradient behind a Text
using drawWithCache caches the Brush object until the size of the drawing area changes

This is more efficient than creating a new Brush on every draw call
*/

@Composable
fun CachingBrushExample() {
    Text(
        "Hello Compose!",
        modifier = Modifier
            .drawWithCache {
                /* Create the brush once and cache it */
                val brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF9E82F0),
                        Color(0xFF42A5F5)
                    )
                )

                /* Use onDrawBehind to draw behind the content */
                onDrawBehind {
                    drawRoundRect(
                        brush,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
            }
            .padding(8.dp)
    )
}

/*
Caching radial gradient brush

This caches a radial gradient brush which is more expensive to create
*/

@Composable
fun CachingRadialGradientExample() {
    Text(
        "Radial",
        modifier = Modifier
            .drawWithCache {
                val brush = Brush.radialGradient(
                    listOf(
                        Color(0xFFFFEB3B),
                        Color(0xFFFF5722)
                    )
                )

                onDrawBehind {
                    drawCircle(brush)
                }
            }
            .padding(16.dp),
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

/*
Caching vertical gradient

Vertical gradient from top to bottom
*/

@Composable
fun CachingVerticalGradientExample() {
    Text(
        "Vertical Gradient",
        modifier = Modifier
            .drawWithCache {
                val brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFFE91E63),
                        Color(0xFF9C27B0)
                    )
                )

                onDrawBehind {
                    drawRoundRect(
                        brush,
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                }
            }
            .padding(12.dp),
        fontSize = 18.sp,
        color = Color.White
    )
}

/*
Caching horizontal gradient

Horizontal gradient from left to right
*/

@Composable
fun CachingHorizontalGradientExample() {
    Text(
        "Horizontal",
        modifier = Modifier
            .drawWithCache {
                val brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF00BCD4),
                        Color(0xFF4CAF50)
                    )
                )

                onDrawBehind {
                    drawRoundRect(
                        brush,
                        cornerRadius = CornerRadius(12.dp.toPx())
                    )
                }
            }
            .padding(10.dp),
        fontSize = 18.sp,
        color = Color.White
    )
}

/*
Using onDrawWithContent in drawWithCache

You can also use onDrawWithContent to draw before and after content
*/

@Composable
fun CachingWithContentExample() {
    Text(
        "Content",
        modifier = Modifier
            .drawWithCache {
                val backgroundBrush = Brush.linearGradient(
                    listOf(
                        Color(0xFFFFB74D),
                        Color(0xFFFFA726)
                    )
                )

                val overlayBrush = Brush.radialGradient(
                    listOf(
                        Color.White.copy(alpha = 0.3f),
                        Color.Transparent
                    )
                )

                onDrawWithContent {
                    /* Draw background */
                    drawRoundRect(
                        backgroundBrush,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )

                    /* Draw content */
                    drawContent()

                    /* Draw overlay */
                    drawCircle(overlayBrush)
                }
            }
            .padding(16.dp),
        fontSize = 20.sp
    )
}

/*
Caching sweep gradient

Sweep gradient creates a circular gradient around a center point
*/

@Composable
fun CachingSweepGradientExample() {
    Text(
        "Sweep",
        modifier = Modifier
            .drawWithCache {
                val brush = Brush.sweepGradient(
                    listOf(
                        Color(0xFFFF6B6B),
                        Color(0xFF4ECDC4),
                        Color(0xFF45B7D1),
                        Color(0xFFFFA07A),
                        Color(0xFFFF6B6B)
                    )
                )

                onDrawBehind {
                    drawCircle(brush)
                }
            }
            .padding(20.dp),
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

/*
When to use drawWithCache

Use drawWithCache when you are creating expensive objects like:
- Brush objects with gradients
- Path objects with complex shapes
- Shader objects
- Paint configurations

Do not use it if you are just drawing simple shapes with solid colors
as the caching overhead might not be worth it
*/

/*
Display all drawWithCache examples
*/

@Composable
fun AllDrawWithCacheExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "drawWithCache Examples",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Cache expensive objects like Brush for better performance",
            fontSize = 14.sp
        )

        Text(text = "Linear Gradient", fontWeight = FontWeight.Medium)
        CachingBrushExample()

        Text(text = "Radial Gradient", fontWeight = FontWeight.Medium)
        CachingRadialGradientExample()

        Text(text = "Vertical Gradient", fontWeight = FontWeight.Medium)
        CachingVerticalGradientExample()

        Text(text = "Horizontal Gradient", fontWeight = FontWeight.Medium)
        CachingHorizontalGradientExample()

        Text(text = "Sweep Gradient", fontWeight = FontWeight.Medium)
        CachingSweepGradientExample()

        Text(text = "With Content Drawing", fontWeight = FontWeight.Medium)
        CachingWithContentExample()
    }
}

