package com.example.FullComposeOfficialDocumentation.Text_Typography_5.DisplayAndStyleText_1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextDecoration

/**
 * Display HTML with Links in Text
 *
 * Use AnnotatedString.fromHtml() to display HTML-formatted text
 * with clickable links in your Jetpack Compose application.
 *
 * This function converts a string with HTML tags into an AnnotatedString,
 * allowing for styling and link handling.
 */

/**
 * Example 1: HTML with Styled Link
 *
 * Renders HTML-formatted text with a link,
 * applying specific styling to the link
 */
@Composable
fun AnnotatedHtmlStringWithLink(
    modifier: Modifier = Modifier,
    htmlText: String = """
       <h1>Jetpack Compose</h1>
       <p>
           Build <b>better apps</b> faster with <a href="https://www.android.com">Jetpack Compose</a>
       </p>
    """.trimIndent()
) {
    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontStyle = FontStyle.Italic,
                    color = Color.Blue
                )
            )
        ),
        modifier
    )
}

/**
 * Example 2: Simple HTML with Default Link Styling
 */
@Composable
fun SimpleHtmlWithLink() {
    val htmlText = """
        <p>Visit <a href="https://developer.android.com">Android Developers</a> for more info</p>
    """.trimIndent()

    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue
                )
            )
        )
    )
}

/**
 * Example 3: HTML with Multiple Links
 */
@Composable
fun HtmlWithMultipleLinks() {
    val htmlText = """
        <p>
            Learn about <a href="https://developer.android.com/jetpack/compose">Compose</a>,
            <a href="https://developer.android.com/kotlin">Kotlin</a>, and
            <a href="https://developer.android.com">Android Development</a>
        </p>
    """.trimIndent()

    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF1976D2)
                )
            )
        )
    )
}

/**
 * Example 4: HTML with Formatting and Links
 */
@Composable
fun FormattedHtmlWithLinks() {
    val htmlText = """
        <h2>Getting Started</h2>
        <p>
            Welcome to <b>Jetpack Compose</b>! 
            Check out the <a href="https://developer.android.com/jetpack/compose/tutorial">official tutorial</a>
            or read the <a href="https://developer.android.com/jetpack/compose/documentation">documentation</a>.
        </p>
        <p><i>Happy coding!</i></p>
    """.trimIndent()

    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF6200EE)
                )
            )
        )
    )
}

/**
 * Example 5: Custom Link Styling - Bold and Colored
 */
@Composable
fun CustomStyledHtmlLinks() {
    val htmlText = """
        <p>
            Download from <a href="https://play.google.com">Google Play Store</a>
        </p>
    """.trimIndent()

    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color(0xFF00C853)
                )
            )
        )
    )
}

/**
 * Example 6: Link with Hover Style (for different states)
 */
@Composable
fun HtmlLinkWithHoverStyle() {
    val htmlText = """
        <p>
            Click <a href="https://www.example.com">here</a> to learn more
        </p>
    """.trimIndent()

    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue
                ),
                hoveredStyle = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF1976D2),
                    background = Color(0xFFE3F2FD)
                )
            )
        )
    )
}

/**
 * Example 7: No Underline Link Style
 */
@Composable
fun NoUnderlineHtmlLink() {
    val htmlText = """
        <p>
            Read more on <a href="https://medium.com">Medium</a>
        </p>
    """.trimIndent()

    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    color = Color(0xFF00AB55),
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )
        )
    )
}

/**
 * Key Points about the Code:
 *
 * 1. AnnotatedString.fromHtml():
 *    - Converts htmlText string into an AnnotatedString
 *    - linkStyles parameter customizes link appearance
 *
 * 2. TextLinkStyles:
 *    - Defines the style for links within the HTML
 *    - style: Default link appearance
 *    - hoveredStyle: Appearance when hovered (optional)
 *    - pressedStyle: Appearance when pressed (optional)
 *
 * 3. SpanStyle:
 *    - Sets text decoration, font style, and color for links
 *    - Can include: textDecoration, fontStyle, color, fontWeight, etc.
 *
 * 4. The Text composable:
 *    - Displays the resulting AnnotatedString
 *    - Links are automatically clickable
 *
 * Supported HTML Tags:
 * - <a href="">: Links
 * - <b>: Bold text
 * - <i>: Italic text
 * - <h1>, <h2>, etc.: Headings
 * - <p>: Paragraphs
 * - And more...
 */

