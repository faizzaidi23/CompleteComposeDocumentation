package com.example.FullComposeOfficialDocumentation.Accessibility_9.MergingAndClearing_5

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
WHEN MERGING DOESN'T HAPPEN - NESTED CLICKABLES

There are scenarios where you expect children semantics to be merged into a parent,
but that doesn't happen. This is often due to nested interactive elements.

THE RULE:
A parent cannot merge its children when the children are also merging, either from:
- Setting mergeDescendants = true explicitly
- Being components which merge themselves (like buttons or clickables)

EXAMPLE SCENARIO:
A list item with:
- A clickable parent Row (opens article)
- An image thumbnail
- Article details text
- A nested BookmarkButton (bookmarks article)

WHAT HAPPENS:
- The image and article details merge with the parent Row
- The BookmarkButton defies the merge due to its own clickable modifier
- Result: Two separate focusable elements for accessibility services
*/

/*
Example: Article list item with nested clickable button
*/

@Composable
fun ArticleListItem(
    openArticle: () -> Unit,
    addToBookmarks: () -> Unit,
) {
    /*
    The parent Row is clickable - it will try to merge its children
    */
    Row(modifier = Modifier.clickable { openArticle() }) {
        /*
        This Icon merges with parent clickable
        It becomes part of the larger clickable article item
        */
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Article thumbnail"
        )

        /*
        Article details also merge with the parent
        They're part of the main article clickable area
        */
        ArticleDetails()

        /*
        This BookmarkButton defies the merge due to its own clickable modifier
        It remains a separate focusable element for accessibility services
        Why? Because clickable modifiers automatically set mergeDescendants = true
        */
        BookmarkButton(onClick = addToBookmarks)
    }
}

@Composable
fun ArticleDetails() {
    /*
    Implementation of article details
    This would contain title, excerpt, date, etc.
    */
    Text("Article Title")
}

@Composable
fun BookmarkButton(onClick: () -> Unit) {
    /*
    This button has its own clickable behavior
    It will NOT merge with the parent Row
    */
    Icon(
        imageVector = Icons.Filled.Star,
        contentDescription = "Bookmark",
        modifier = Modifier.clickable { onClick() }
    )
}

/*
SEMANTICS TREE RESULT:

MERGED TREE:
Row (clickable - opens article)
  ├─ Icon: "Article thumbnail"
  ├─ ArticleDetails (merged texts)

BookmarkButton (separate clickable - bookmarks article)
  └─ Icon: "Bookmark"

UNMERGED TREE:
Row
  ├─ Icon: "Article thumbnail"
  ├─ Column (ArticleDetails)
  │   ├─ Text: "Article Title"
  │   ├─ Text: "Article excerpt..."
  │   └─ Text: "5 min read"
  └─ Icon (BookmarkButton)
      └─ contentDescription: "Bookmark"

ACCESSIBILITY EXPERIENCE:
- First focus: "Article thumbnail, Article Title, Article excerpt, 5 min read. Double tap to activate"
- Second focus: "Bookmark. Double tap to activate"

This separation makes sense because users need to:
1. Open the article (tap the row)
2. OR bookmark it (tap the bookmark button)
These are two distinct actions that shouldn't be merged.
*/

/*
KEY TAKEAWAY:
Knowing how certain APIs merge or defy merging can help you debug some potentially
unexpected behaviors. Use merging when children elements constitute a logical and
sensible group under their parent. But if the nested children need manual adjusting
or removal of their own semantics, other APIs might better suit your needs
(for example, clearAndSetSemantics).
*/
