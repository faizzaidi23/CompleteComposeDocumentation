package com.example.FullComposeOfficialDocumentation.Graphics_7.GraphicsModifiers_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.FullComposeOfficialDocumentation.R

/*
TRANSLATION

translationX and translationY can be changed with graphicsLayer
translationX moves the composable left or right
translationY moves the composable up or down

These transformations do not change the layout size or position
They only affect where the content is drawn
*/

@Composable
fun TranslationExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.translationX = 100.dp.toPx()
                this.translationY = 10.dp.toPx()
            }
    )
}

/*
Negative translation

Moving left and up using negative values
*/

@Composable
fun NegativeTranslationExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.translationX = -50.dp.toPx()
                this.translationY = -30.dp.toPx()
            }
    )
}

/*
Large translation

Moving the image significantly from its original position
*/

@Composable
fun LargeTranslationExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.translationX = 150.dp.toPx()
                this.translationY = 100.dp.toPx()
            }
    )
}

/*
Horizontal only translation

Moving only horizontally
*/

@Composable
fun HorizontalTranslationExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.translationX = 80.dp.toPx()
                this.translationY = 0f
            }
    )
}

/*
Vertical only translation

Moving only vertically
*/

@Composable
fun VerticalTranslationExample() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog",
        modifier = Modifier
            .size(150.dp)
            .graphicsLayer {
                this.translationX = 0f
                this.translationY = 80.dp.toPx()
            }
    )
}

/*
Display all translation examples
*/

@Composable
fun AllTranslationExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Translation Transformations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "translationX and translationY move content without changing layout",
            fontSize = 14.sp
        )

        Text(text = "Positive Translation (right 100, down 10)", fontWeight = FontWeight.Medium)
        TranslationExample()

        Text(text = "Negative Translation (left 50, up 30)", fontWeight = FontWeight.Medium)
        NegativeTranslationExample()

        Text(text = "Large Translation (right 150, down 100)", fontWeight = FontWeight.Medium)
        LargeTranslationExample()

        Text(text = "Horizontal Only (right 80)", fontWeight = FontWeight.Medium)
        HorizontalTranslationExample()

        Text(text = "Vertical Only (down 80)", fontWeight = FontWeight.Medium)
        VerticalTranslationExample()
    }
}

