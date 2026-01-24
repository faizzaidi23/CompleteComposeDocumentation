package com.example.FullComposeOfficialDocumentation.Text_Typography_5.DisplayAndStyleText_1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Apply Marquee Effect to Text
 *
 * The basicMarquee modifier can be applied to any composable
 * to produce an animated scrolling effect.
 *
 * The marquee effect occurs if the content is too wide to fit
 * in the available constraints.
 *
 * By default, basicMarquee has certain configurations like
 * velocity and initial delay, but you can modify these parameters
 * to customize the effect.
 */

/**
 * Example 1: Basic Marquee Effect
 *
 * Marquee only animates when content doesn't fit in max width
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicMarqueeSample() {
    // Marquee only animates when the content doesn't fit in the max width.
    Column(Modifier.width(400.dp)) {
        Text(
            "Learn about why it's great to use Jetpack Compose",
            modifier = Modifier.basicMarquee(),
            fontSize = 50.sp
        )
    }
}

/**
 * Example 2: Simple Marquee with Default Settings
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleMarqueeText() {
    Column(Modifier.width(300.dp)) {
        Text(
            text = "This is a very long text that will scroll horizontally when it doesn't fit",
            modifier = Modifier.basicMarquee(),
            fontSize = 24.sp,
            maxLines = 1
        )
    }
}

/**
 * Example 3: Marquee with Custom Iterations
 *
 * Control how many times the animation repeats
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarqueeWithIterations() {
    Column(Modifier.width(250.dp)) {
        Text(
            text = "This text will scroll 3 times and then stop",
            modifier = Modifier.basicMarquee(
                iterations = 3  // Scroll 3 times then stop
            ),
            fontSize = 20.sp
        )
    }
}

/**
 * Example 4: Infinite Marquee (Default)
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteMarquee() {
    Column(Modifier.width(300.dp)) {
        Text(
            text = "This will scroll continuously forever and ever",
            modifier = Modifier.basicMarquee(
                iterations = Int.MAX_VALUE  // Infinite scrolling (default)
            ),
            fontSize = 22.sp
        )
    }
}

/**
 * Example 5: Marquee with Custom Velocity
 *
 * Control the scrolling speed
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FastMarquee() {
    Column(Modifier.width(300.dp)) {
        Text(
            text = "Fast scrolling marquee text goes zoom!",
            modifier = Modifier.basicMarquee(
                velocity = 100.dp  // Faster scrolling (default is 30.dp)
            ),
            fontSize = 24.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlowMarquee() {
    Column(Modifier.width(300.dp)) {
        Text(
            text = "Slow and steady marquee scrolling",
            modifier = Modifier.basicMarquee(
                velocity = 10.dp  // Slower scrolling
            ),
            fontSize = 24.sp
        )
    }
}

/**
 * Example 6: Marquee with Initial Delay
 *
 * Add delay before scrolling starts
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DelayedMarquee() {
    Column(Modifier.width(300.dp)) {
        Text(
            text = "This text waits 3 seconds before starting to scroll",
            modifier = Modifier.basicMarquee(
                initialDelayMillis = 3000  // Wait 3 seconds before starting
            ),
            fontSize = 20.sp
        )
    }
}

/**
 * Example 7: Marquee with Delay Between Iterations
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarqueeWithDelayBetweenIterations() {
    Column(Modifier.width(300.dp)) {
        Text(
            text = "Pauses for 2 seconds between each scroll cycle",
            modifier = Modifier.basicMarquee(
                repeatDelayMillis = 2000  // 2 second pause between iterations
            ),
            fontSize = 20.sp
        )
    }
}

/**
 * Example 8: Fully Customized Marquee
 *
 * Combine all parameters for full control
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FullyCustomizedMarquee() {
    Column(Modifier.width(350.dp)) {
        Text(
            text = "Customized marquee with all parameters configured",
            modifier = Modifier.basicMarquee(
                iterations = 5,              // Scroll 5 times
                velocity = 50.dp,            // Medium speed
                initialDelayMillis = 1000,   // Wait 1 second before starting
                repeatDelayMillis = 1500     // 1.5 second pause between cycles
            ),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Example 9: Marquee with Styling
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StyledMarquee() {
    Column(
        Modifier
            .width(300.dp)
            .background(Color(0xFF1A237E))
            .padding(16.dp)
    ) {
        Text(
            text = "Styled marquee text with background and colors",
            modifier = Modifier.basicMarquee(),
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Example 10: News Ticker Style Marquee
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsTickerMarquee() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF6F00))
            .padding(8.dp)
    ) {
        Text(
            text = "⚡ BREAKING NEWS: Jetpack Compose makes UI development easier and faster! ⚡",
            modifier = Modifier.basicMarquee(
                velocity = 40.dp,
                repeatDelayMillis = 0  // No pause between iterations
            ),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Example 11: Multiple Marquee Texts
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MultipleMarqueeTexts() {
    Column(Modifier.width(300.dp)) {
        Text(
            text = "First scrolling text line",
            modifier = Modifier.basicMarquee(),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Second independent scrolling text",
            modifier = Modifier.basicMarquee(velocity = 50.dp),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Third scrolling text with different speed",
            modifier = Modifier.basicMarquee(velocity = 20.dp),
            fontSize = 20.sp
        )
    }
}

/**
 * All Marquee Examples Together
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllMarqueeExamples() {
    Column {
        BasicMarqueeSample()
        Spacer(modifier = Modifier.height(16.dp))

        SimpleMarqueeText()
        Spacer(modifier = Modifier.height(16.dp))

        FastMarquee()
        Spacer(modifier = Modifier.height(16.dp))

        SlowMarquee()
        Spacer(modifier = Modifier.height(16.dp))

        StyledMarquee()
        Spacer(modifier = Modifier.height(16.dp))

        NewsTickerMarquee()
    }
}

/**
 * Key Points:
 *
 * Marquee Parameters:
 * - iterations: How many times to scroll (default: Int.MAX_VALUE = infinite)
 * - velocity: Scrolling speed in dp per second (default: 30.dp)
 * - initialDelayMillis: Delay before first scroll (default: 0)
 * - delayMillis: Delay between iterations (default: 1200ms)
 *
 * When Marquee Activates:
 * - Only when content is wider than available space
 * - If text fits, no scrolling occurs
 * - Use maxLines = 1 to ensure single-line behavior
 *
 * Use Cases:
 * - News tickers
 * - Breaking news banners
 * - Stock price displays
 * - Long titles that don't fit
 * - Notifications
 * - Song titles in music players
 *
 * Best Practices:
 * - Set appropriate width constraint
 * - Use reasonable velocity (not too fast)
 * - Consider user accessibility
 * - Add initial delay for readability
 * - Test with different content lengths
 *
 * Note:
 * - API is experimental (@OptIn required)
 * - Performance: Marquee is optimized for text
 * - Works with any composable, not just Text
 */
