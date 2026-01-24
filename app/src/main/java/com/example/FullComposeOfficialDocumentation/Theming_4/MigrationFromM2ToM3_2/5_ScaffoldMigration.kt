package com.example.FullComposeOfficialDocumentation.Theming_4.MigrationFromM2ToM3_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Scaffold Migration from M2 to M3
 *
 * Key changes in M3 Scaffold:
 * - backgroundColor -> containerColor
 * - No more ScaffoldState (use SnackbarHostState directly)
 * - No drawer parameters (use ModalNavigationDrawer separately)
 * - SnackbarData split into SnackbarData and SnackbarVisuals
 */

// M2 approach (old):
// val scaffoldState = rememberScaffoldState()
// Scaffold(
//     scaffoldState = scaffoldState,
//     backgroundColor = MaterialTheme.colors.background,
//     drawerContent = { ... }
// )

// M3 approach (new):
@Composable
fun ScaffoldMigrationExample() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,  // Was backgroundColor in M2
        snackbarHost = { SnackbarHost(snackbarHostState) },  // Direct state instead of ScaffoldState
        topBar = {
            TopAppBar(
                title = { Text("M3 Scaffold") }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "Bottom App Bar",
                    modifier = Modifier.padding(16.dp)
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    scope.launch {
                        // Show snackbar using SnackbarHostState
                        snackbarHostState.showSnackbar("Snackbar shown!")
                    }
                },
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Show Snackbar") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "M3 Scaffold Content",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Scaffold in M3 no longer has drawer parameters. " +
                        "Use ModalNavigationDrawer as a separate wrapper instead.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

