package com.example.FullComposeOfficialDocumentation.Accessibility_9.ApiDefaults_1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics

/*
Material and Foundation Compose APIs make UI elements that users can interact with through
the clickable and toggleable modifier APIs. Because interactable components might consist of
multiple elements, clickable and toggleable merge their children's semantics by default,
so that the component is treated as one logical entity.
*/

/*
Using the clickable modifier causes a composable to merge its descendants' semantics into a single entity,
which is sent to accessibility services with a corresponding action representation
*/
@Composable
fun ClickableArticleRow(openArticle: () -> Unit) {
    Row(
        modifier = Modifier.clickable { openArticle() }
    ) {
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_info_details),
            contentDescription = "Open",
        )
        Text("Accessibility in Compose")
    }
}

/*
You can set a specific onClickLabel on the parent clickable to provide additional information
to accessibility services. Using TalkBack, this would enable TalkBack to provide an action hint
of "Double tap to open this article" instead of "Double tap to activate"
*/
@Composable
fun ClickableArticleRowWithLabel(openArticle: () -> Unit) {
    Row(
        modifier = Modifier.clickable(onClickLabel = "Open this article") {
            openArticle()
        }
    ) {
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_info_details),
            contentDescription = "Open"
        )
        Text("Accessibility in Compose")
    }
}

/*
A long click would provide a TalkBack hint of "Double tap and hold to", followed by a label
*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedClickableArticleRow(
    openArticle: () -> Unit,
    addToBookmarks: () -> Unit
) {
    Row(
        modifier = Modifier.combinedClickable(
            onLongClickLabel = "Bookmark this article",
            onLongClick = { addToBookmarks() },
            onClickLabel = "Open this article",
            onClick = { openArticle() },
        )
    ) {
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_info_details),
            contentDescription = "Open"
        )
        Text("Accessibility in Compose")
    }
}

/*
In some cases, you may not have direct access to the clickable modifier, but still want to change
the announcement label. You can use the semantics modifier to modify the action representation.
The merging logic verifies that the outermost modifier label is taken.
*/
@Composable
fun ArticleListWithSemantics(openArticle: () -> Unit) {
    NestedArticleListItem(
        onClickAction = openArticle,
        modifier = Modifier.semantics {
            onClick(
                label = "Open this article",
                action = { true }
            )
        }
    )
}

@Composable
private fun NestedArticleListItem(
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable { onClickAction() }
    ) {
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_info_details),
            contentDescription = "Open"
        )
        Text("Accessibility in Compose")
    }
}
