package com.example.FullComposeOfficialDocumentation.Accessibility_9.MergingAndClearing_5

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.material3.Text

/*
HIDE SEMANTICS

In some scenarios, elements don't need to be sent to accessibility services.
Perhaps their additional information is redundant for accessibility, or it is
purely visually decorative and non-interactive.

WHEN TO USE invisibleToUser:
- Decorative elements that don't convey important information
- Redundant elements that duplicate information already available
- Visual separators (dots, lines) used only for aesthetics
- Watermarks or background elements
- Elements that would clutter the accessibility experience

HOW IT WORKS:
Use the invisibleToUser() function within a semantics modifier:
Modifier.semantics { invisibleToUser() }

KEY DIFFERENCE FROM clearAndSetSemantics:
- invisibleToUser: Hides from accessibility services but keeps semantics
  for other use cases (like testing)
- clearAndSetSemantics{}: Clears semantics for ALL services including testing

BENEFITS:
- Reduces noise for accessibility service users
- Keeps testing capabilities intact
- Improves navigation efficiency
- Focuses attention on meaningful content
*/

/*
EXAMPLE 1: WATERMARK
A watermark that spans a component is purely visual and redundant.
Users don't need to know about it when navigating with accessibility services.
*/

@Composable
fun WatermarkExample(
    watermarkText: String,
) {
    Box {
        /*
        Main content that users should interact with
        This remains fully accessible
        */
        WatermarkedContent()

        /*
        Watermark text is marked as hidden to accessibility services
        It's still rendered visually and can be tested, but accessibility
        services won't announce it or focus on it
        */
        WatermarkText(
            text = watermarkText,
            color = Color.Gray.copy(alpha = 0.5f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .semantics { invisibleToUser() }
        )
    }
}

@Composable
fun WatermarkedContent() {
    /*
    Main content implementation
    This is what users actually interact with
    */
    Text("Important content that users need to access")
}

@Composable
fun WatermarkText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    /*
    Watermark text implementation
    Visible on screen but hidden from accessibility services
    */
    Text(
        text = text,
        color = color,
        modifier = modifier
    )
}

/*
EXAMPLE 2: DECORATIVE SEPARATOR
A character used to decoratively separate information, like "•" or "|"
This is purely visual and doesn't need to be announced
*/

@Composable
fun DecorativeExample() {
    Text(
        modifier = Modifier.semantics {
            invisibleToUser()
        },
        text = "A dot character that is used to decoratively separate information, like •"
    )
}

/*
PRACTICAL EXAMPLE: Article metadata with decorative separator
*/

@Composable
fun ArticleMetadataWithSeparator(date: String, readTime: String) {
    /*
    Without hiding the separator:
    TalkBack might announce: "February 13, 2026, bullet, 5 min read"

    With hiding the separator:
    TalkBack announces: "February 13, 2026, 5 min read"
    (Much cleaner and more natural)
    */
    Text(date)
    Text(
        text = " • ",
        modifier = Modifier.semantics { invisibleToUser() }
    )
    Text(readTime)
}

/*
COMMON USE CASES FOR invisibleToUser:

1. DECORATIVE ICONS
   - Icons that are purely visual and duplicate adjacent text
   - Background patterns or textures

2. VISUAL SEPARATORS
   - Bullets (•), pipes (|), slashes (/)
   - Decorative divider lines
   - Spacing elements

3. WATERMARKS AND OVERLAYS
   - Copyright notices
   - Draft watermarks
   - Background branding

4. REDUNDANT INFORMATION
   - Icons next to text that describes the same thing
   - Example: Star icon next to "Favorite" text
   - The text alone is sufficient for accessibility

5. DECORATIVE IMAGES
   - Background images
   - Texture overlays
   - Design elements with no semantic meaning

WHEN NOT TO USE:
- Don't hide interactive elements (buttons, links)
- Don't hide informative images (use contentDescription instead)
- Don't hide text that conveys unique information
- Don't hide status indicators or badges
*/

/*
COMPARISON TABLE:

┌─────────────────────┬────────────────┬─────────┬──────────┐
│ API                 │ Accessibility  │ Testing │ Use Case │
├─────────────────────┼────────────────┼─────────┼──────────┤
│ invisibleToUser     │ Hidden         │ Visible │ Decorative│
│ clearAndSetSemantics{}│ Cleared      │ Cleared │ Remove all│
│ clearAndSetSemantics{...}│ Custom   │ Custom  │ Override  │
│ semantics(mergeDescendants)│ Merged │ Merged  │ Group     │
└─────────────────────┴────────────────┴─────────┴──────────┘

ACCESSIBILITY RESULT:
Using invisibleToUser ensures decorative elements are hidden from
accessibility services, but still keep their semantics for other use cases,
like testing. This creates a cleaner, more focused experience for users
relying on accessibility services.
*/
