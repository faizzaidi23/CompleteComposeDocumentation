package com.example.FullComposeOfficialDocumentation.Graphics_7.ShapesInCompose_4

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.FullComposeOfficialDocumentation.R

/*
ANIMATE SHAPE MORPHING INFINITELY

To endlessly animate a morph shape, use rememberInfiniteTransition
This is useful for profile pictures or decorative elements that change shape continuously

This approach uses a custom rotating morph shape that:
- Morphs between two shapes
- Rotates the shape over time
- Animates infinitely
*/

/*
CustomRotatingMorphShape

A custom Shape that morphs and rotates simultaneously
Combines morphing progress with rotation angle
*/

class CustomRotatingMorphShape(
    private val morph: Morph,
    private val percentage: Float,
    private val rotation: Float
) : Shape {

    private val matrix = Matrix()

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Scale to fit container
        matrix.scale(size.width / 2f, size.height / 2f)
        matrix.translate(1f, 1f)
        // Apply rotation
        matrix.rotateZ(rotation)

        val path = morph.toPath(progress = percentage).asComposePath()
        path.transform(matrix)

        return Outline.Generic(path)
    }
}

/*
Rotating Scalloped Profile Picture

This example shows a profile picture with an infinitely morphing and rotating border
The shape morphs between a regular polygon and a star while rotating
*/

@Composable
fun RotatingScallopedProfilePicExample() {
    val shapeA = remember {
        RoundedPolygon(
            12,
            rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            12,
            rounding = CornerRounding(0.2f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "infinite outline movement")

    val animatedProgress = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animatedMorphProgress"
    )

    val animatedRotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animatedRotation"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    CustomRotatingMorphShape(
                        morph,
                        animatedProgress.value,
                        animatedRotation.value
                    )
                )
                .size(200.dp)
        )
    }
}

/*
Slower Rotation Example

Same concept but with different timing for a more subtle effect
*/

@Composable
fun SlowRotatingMorphExample() {
    val shapeA = remember {
        RoundedPolygon(
            8,
            rounding = CornerRounding(0.3f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            8,
            rounding = CornerRounding(0.15f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "slow rotation")

    val animatedProgress = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morphProgress"
    )

    val animatedRotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(
                CustomRotatingMorphShape(
                    morph,
                    animatedProgress.value,
                    animatedRotation.value
                )
            )
            .background(Color(0xFF2196F3))
    ) {
        Text(
            "Slow\nRotation",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Fast Pulsing Morph

Quick morphing with moderate rotation for energetic effect
*/

@Composable
fun FastPulsingMorphExample() {
    val shapeA = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.1f)
        )
    }
    val shapeB = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.5f, smoothing = 0.8f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "fast pulse")

    val animatedProgress = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morphProgress"
    )

    val animatedRotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(
                CustomRotatingMorphShape(
                    morph,
                    animatedProgress.value,
                    animatedRotation.value
                )
            )
            .background(Color(0xFFFF9800))
    ) {
        Text(
            "Fast\nPulse",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Morph Without Rotation

Just the morph animation without rotation for comparison
*/

@Composable
fun MorphWithoutRotationExample() {
    val shapeA = remember {
        RoundedPolygon(
            5,
            rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.star(
            5,
            rounding = CornerRounding(0.15f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "no rotation")

    val animatedProgress = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morphProgress"
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(
                CustomRotatingMorphShape(
                    morph,
                    animatedProgress.value,
                    0f  // No rotation
                )
            )
            .background(Color(0xFF9C27B0))
    ) {
        Text(
            "No\nRotation",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
Infinite Morph Animation Showcase

Displays various infinite morphing animations with different configurations
*/

@Composable
fun InfiniteMorphAnimationShowcase() {
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
            "Infinite Morph Animations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "These shapes continuously morph and rotate",
            fontSize = 14.sp,
            color = Color.Gray
        )

        // Rotating scalloped (12-point)
        Text("12-Point Scalloped Border", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("Morph: 2s | Rotation: 6s", fontSize = 12.sp, color = Color.Gray)
        Box(
            modifier = Modifier.size(220.dp),
            contentAlignment = Alignment.Center
        ) {
            val shapeA = remember {
                RoundedPolygon(12, rounding = CornerRounding(0.2f))
            }
            val shapeB = remember {
                RoundedPolygon.star(12, rounding = CornerRounding(0.2f))
            }
            val morph = remember { Morph(shapeA, shapeB) }

            val infiniteTransition = rememberInfiniteTransition(label = "12-point")
            val progress = infiniteTransition.animateFloat(
                0f, 1f,
                infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Reverse),
                label = "progress"
            )
            val rotation = infiniteTransition.animateFloat(
                0f, 360f,
                infiniteRepeatable(tween(6000, easing = LinearEasing), RepeatMode.Reverse),
                label = "rotation"
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CustomRotatingMorphShape(morph, progress.value, rotation.value))
                    .background(Color(0xFF00BCD4))
            )
        }

        // Slow rotation
        Text("Slow Rotation", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("Morph: 3s | Rotation: 10s", fontSize = 12.sp, color = Color.Gray)
        SlowRotatingMorphExample()

        // Fast pulsing
        Text("Fast Pulsing", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("Morph: 0.8s | Rotation: 4s", fontSize = 12.sp, color = Color.Gray)
        FastPulsingMorphExample()

        // No rotation
        Text("Morph Only (No Rotation)", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text("Morph: 1.5s", fontSize = 12.sp, color = Color.Gray)
        MorphWithoutRotationExample()
    }
}

