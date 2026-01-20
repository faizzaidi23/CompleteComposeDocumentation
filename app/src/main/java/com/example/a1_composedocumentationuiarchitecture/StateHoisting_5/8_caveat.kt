package com.example.a1_composedocumentationuiarchitecture.StateHoisting_5

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 simple terms, the problem is that some UI animations can only be started from the UI itself, not from a background-like context like a ViewModel.

A ViewModel is like an office manager—it handles logic and data.
The UI (the Composition) is like an animation studio—it has the special clocks
and tools (MonotonicFrameClock) needed to draw things smoothly on the screen.

If the office manager (ViewModel) tries to run an animation (drawerState.close())
using their own office tools (viewModelScope), the system crashes
because the required animation equipment isn't there.

The solution is for the ViewModel to ask the UI for help. The UI passes its
"animation studio's tools" (its CoroutineScope) to the ViewModel, which then
 temporarily uses those tools just to run the animation, before switching back to its own tools to do the rest of its work.
*/


data class DrawerContent(val title:String ,val items:List<String>){
    companion object{
        val Empty=DrawerContent("Loading.....",emptyList())
    }
}


class ConversationnViewModel:ViewModel(){

    val drawerState= DrawerState(initialValue = DrawerValue.Closed)

    private val _drawerContent=MutableStateFlow(DrawerContent.Empty)

    /*
    asStateFlow() → Used when you already have a MutableStateFlow and you want to expose it as a read-only StateFlow.

    stateIn() → Used when you have a regular Flow (could be flow {}, map, etc.) and you want to turn it into a StateFlow (which caches latest value and is always hot).
    */

    val drawerContent:StateFlow<DrawerContent> = _drawerContent.asStateFlow()

    fun openDrawer(uiScope: CoroutineScope){
        uiScope.launch{
            drawerState.open()
        }
    }

    fun closeDrawerAndRefresh(uiScope: CoroutineScope) {
        viewModelScope.launch {
            // THE FIX: Use the UI's scope to run the animation.
            withContext(uiScope.coroutineContext) {
                drawerState.close()
            }
            // After the animation is done, we are back in the viewModelScope.
            // Now we can safely perform our business logic.
            _drawerContent.update { DrawerContent("Refreshing...", emptyList()) }
            delay(1000) // Simulate a network request.
            _drawerContent.update {
                DrawerContent("Updated Content", listOf("Item A", "Item B", "Item C"))
            }
        }
    }
}

@Composable
fun ConversationnScreen(
    viewModel: ConversationnViewModel = viewModel()
) {
    // Get the CoroutineScope tied to the UI's lifecycle. This has the animation clock.
    val uiScope = rememberCoroutineScope()
    val drawerContent by viewModel.drawerContent.collectAsState()

    // Call the stateless version of the screen.
    StatelessConversationScreen(
        drawerState = viewModel.drawerState,
        drawerContent = drawerContent,
        onOpenDrawer = { viewModel.openDrawer(uiScope) },
        onCloseDrawerAndRefresh = { viewModel.closeDrawerAndRefresh(uiScope) }
    )
}

@Composable
fun StatelessConversationScreen(
    drawerState: DrawerState,
    drawerContent: DrawerContent,
    onOpenDrawer: () -> Unit,
    onCloseDrawerAndRefresh: () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(drawerContent.title, style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    drawerContent.items.forEach { item ->
                        Text(item)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(onClick = onCloseDrawerAndRefresh) {
                        Text("Close and Refresh")
                    }
                }
            }
        }
    ) {
        // Main screen content
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onOpenDrawer) {
                Text("Open Drawer")
            }
        }
    }
}