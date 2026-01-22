package com.example.FullComposeOfficialDocumentation.Components_3.PullToRefresh_19

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBasicSample(
    items: List<String>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    // PullToRefreshBox wraps scrollable content
    PullToRefreshBox(
        // Indicates if refresh is in progress
        isRefreshing = isRefreshing,
        // Called when user initiates refresh
        onRefresh = onRefresh,
        modifier = modifier
    ) {
        // Scrollable content - LazyColumn displaying list items
        LazyColumn(Modifier.fillMaxSize()) {
            items(items) {
                ListItem({ Text(text = it) })
            }
        }
    }
}

