package com.example.FullComposeOfficialDocumentation.Accessibility_9.Semantics_2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

/*
Error Components:

For other content types, such as error-like components, you might want to expand on the main
semantic information for users with accessibility needs.

When defining error states, you can inform the accessibility services of its error semantics,
and provide expanded error messaging.
*/

@Composable
fun Error(
    errorText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(16.dp)
            .semantics {
                error("Please add both email and password")
            }
    ) {
        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.onErrorContainer,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

/*
How it works:

In this example, TalkBack reads the main error text information, followed by additional,
expanded messaging.

TalkBack will announce: "Fields cannot be empty. Please add both email and password. Error."
*/

@Composable
fun ErrorExample() {
    Error(
        errorText = "Fields cannot be empty",
        modifier = Modifier
            .semantics {
                error("Please add both email and password")
            }
    )
}

/*
Benefits:

- Provides more context about what went wrong
- Gives users actionable information to fix the error
- Helps users understand the requirements
- Makes error messages more accessible and helpful
*/

@Composable
fun FormErrorExample(
    emailError: Boolean,
    passwordError: Boolean
) {
    if (emailError || passwordError) {
        val errorMessage = when {
            emailError && passwordError -> "Email and password are required"
            emailError -> "Email is required"
            else -> "Password is required"
        }

        val detailedError = when {
            emailError && passwordError -> "Please enter a valid email address and a password with at least 8 characters"
            emailError -> "Please enter a valid email address"
            else -> "Please enter a password with at least 8 characters"
        }

        Error(
            errorText = errorMessage,
            modifier = Modifier.semantics {
                error(detailedError)
            }
        )
    }
}

/*
Best practices:

- Use clear, concise error messages
- Provide actionable guidance in the error semantics
- Explain how to fix the error
- Keep the main error text short and the semantic error more detailed
*/

