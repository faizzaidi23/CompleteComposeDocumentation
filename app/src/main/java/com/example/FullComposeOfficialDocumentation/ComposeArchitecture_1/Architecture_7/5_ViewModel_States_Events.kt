package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Architecture_7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed class UiState{
    object SignedOut : UiState()
    object InProgress:UiState()
    object SignedIn:UiState()
    data class Error(val message: String): UiState()
}
class MyViewModel : ViewModel() {
    // Private, mutable state that can only be changed within the ViewModel
    private val _uiState = mutableStateOf<UiState>(UiState.SignedOut)

    // Public, immutable state that the UI can observe
    val uiState: State<UiState>
        get() = _uiState

    // Public function to handle the sign-in event from the UI
    fun onSignIn() {
        // Use viewModelScope to launch a coroutine that is lifecycle-aware
        viewModelScope.launch {
            // Set state to "In Progress" to show a loading indicator
            _uiState.value = UiState.InProgress
            try {
                // Simulate a network call that takes 2 seconds
                delay(2000)
                // If successful, update the state to "Signed In"
                _uiState.value = UiState.SignedIn
            } catch (e: Exception) {
                // If an error occurs, update the state with an error message
                _uiState.value = UiState.Error("Something went wrong!")
            }
        }
    }
}

@Composable

fun SignInScreen(viewModel: MyViewModel = MyViewModel()) {
    // Get the current state from the ViewModel.
    // Compose will automatically recompose when this state changes.
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Use a when statement to display the correct UI for the current state
        when (uiState) {
            is UiState.SignedOut -> {
                Text("Please sign in")
                Button(onClick = { viewModel.onSignIn() }) {
                    Text("Sign In")
                }
            }
            is UiState.InProgress -> {
                CircularProgressIndicator()
                Text("Signing in...")
            }
            is UiState.SignedIn -> {
                Text("Successfully signed in!")
            }
            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).message
                Text(errorMessage)
                Button(onClick = { viewModel.onSignIn() }) {
                    Text("Try Again")
                }
            }
        }
    }
}