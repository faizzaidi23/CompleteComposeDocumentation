package com.example.FullComposeOfficialDocumentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.FullComposeOfficialDocumentation.ui.theme._1composeDocumentationUIArchitectureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _1composeDocumentationUIArchitectureTheme {

            }
        }
    }
}
