
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            // Start refresh
            scope.launch {
                isRefreshing = true
                // Simulate network request
                delay(2000)
                // Update items
                items = List(20) { "Refreshed Item ${it + 1}" }
                isRefreshing = false
            }
        }
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(items) { item ->
                ListItem({ Text(text = item) })
            }
        }
    }
}
package com.example.FullComposeOfficialDocumentation.Components_3.PullToRefresh_19

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBasicUsageExample() {
    // State for items and refresh status
    var items by remember { mutableStateOf(List(20) { "Item ${it + 1}" }) }
    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

