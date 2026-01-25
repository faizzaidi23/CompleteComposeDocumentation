package com.example.FullComposeOfficialDocumentation.Text_Typography_5.HandleUserInput_4

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/*
 Input Validation and Filtering

 Validation can happen in two places:
 1. In onValueChange - Filter/reject input as user types (immediate)
 2. After user finishes - Validate on submit/focus loss (delayed)

 Immediate validation = Better UX for preventing invalid input
 Delayed validation = Better for complex rules that need full context
*/

/*
 Filter input - Only allow specific characters

 Example: Only allow digits (for phone, credit card, etc)
*/
@Composable
fun DigitsOnlyTextField() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newValue ->
            // Only update if all characters are digits
            if (newValue.all { it.isDigit() }) {
                text = newValue
            }
        },
        label = { Text("Digits only") }
    )
}

/*
 Limit input length

 Prevent user from typing more than max length
*/
@Composable
fun LimitedLengthTextField() {
    var text by rememberSaveable { mutableStateOf("") }
    val maxLength = 10

    TextField(
        value = text,
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) {
                text = newValue
            }
        },
        label = { Text("Max $maxLength chars") },
        supportingText = { Text("${text.length}/$maxLength") }
    )
}

/*
 Combine multiple filters

 Example: Only digits AND max length
 This is common for phone numbers, PIN codes, etc
*/
@Composable
fun PhoneNumberInput() {
    var phone by rememberSaveable { mutableStateOf("") }
    val maxLength = 10

    TextField(
        value = phone,
        onValueChange = { newValue ->
            // Filter 1: Only digits
            // Filter 2: Max 10 characters
            if (newValue.all { it.isDigit() } && newValue.length <= maxLength) {
                phone = newValue
            }
        },
        label = { Text("Phone (10 digits)") },
        supportingText = { Text("${phone.length}/$maxLength digits") }
    )
}

/*
 Allow only letters (no numbers or special characters)
*/
@Composable
fun LettersOnlyTextField() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newValue ->
            if (newValue.all { it.isLetter() || it.isWhitespace() }) {
                text = newValue
            }
        },
        label = { Text("Letters only") }
    )
}

/*
 Email validation - Validate as user types

 Shows error state while invalid
 This is real-time validation
*/
@Composable
fun EmailValidationTextField() {
    var email by rememberSaveable { mutableStateOf("") }

    // Simple email validation (contains @ and .)
    val isValid = email.contains("@") && email.contains(".")
    val isError = email.isNotEmpty() && !isValid

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        isError = isError,
        supportingText = {
            if (isError) {
                Text("Invalid email format")
            }
        },
        singleLine = true
    )
}

/*
 Block specific characters

 Example: No spaces allowed (for username)
*/
@Composable
fun NoSpacesTextField() {
    var username by rememberSaveable { mutableStateOf("") }

    TextField(
        value = username,
        onValueChange = { newValue ->
            // Reject if contains spaces
            if (!newValue.contains(" ")) {
                username = newValue
            }
        },
        label = { Text("Username (no spaces)") }
    )
}

/*
 Regex-based validation

 Example: Alphanumeric only (letters and numbers, no special chars)
*/
@Composable
fun AlphanumericTextField() {
    var text by rememberSaveable { mutableStateOf("") }
    val alphanumericRegex = Regex("^[a-zA-Z0-9]*$")

    TextField(
        value = text,
        onValueChange = { newValue ->
            if (newValue.matches(alphanumericRegex)) {
                text = newValue
            }
        },
        label = { Text("Alphanumeric only") }
    )
}

/*
 Auto-format as user types

 Example: Auto-uppercase for postal codes
*/
@Composable
fun UppercaseTextField() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newValue ->
            text = newValue.uppercase()
        },
        label = { Text("Postal Code (auto uppercase)") }
    )
}

/*
 Trim whitespace automatically

 Useful for preventing accidental spaces in usernames, emails
*/
@Composable
fun TrimmedTextField() {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newValue ->
            // Remove leading/trailing spaces
            text = newValue.trim()
        },
        label = { Text("Auto-trimmed input") }
    )
}

/*
 Complex validation - Password strength

 Shows different error messages based on validation rules
*/
@Composable
fun PasswordStrengthTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    val hasMinLength = password.length >= 8
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }

    val isValid = hasMinLength && hasUpperCase && hasLowerCase && hasDigit
    val isError = password.isNotEmpty() && !isValid

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        isError = isError,
        supportingText = {
            when {
                !hasMinLength -> Text("At least 8 characters")
                !hasUpperCase -> Text("Add uppercase letter")
                !hasLowerCase -> Text("Add lowercase letter")
                !hasDigit -> Text("Add a number")
                else -> Text("Strong password ✓")
            }
        }
    )
}

