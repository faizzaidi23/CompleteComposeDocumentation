package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Navigation_10

/*
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

// --- 1. Define routes and the URI for the deep link ---
object AppRoutes {
    const val PRODUCT_LIST = "product_list"
    const val PRODUCT_DETAILS = "product_details/{productId}"

    // This is the base URI your app will respond to.
    // It should be a domain you control.
    const val URI = "https://www.example.com"
}


// --- 2. Set up the NavHost ---
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppRoutes.PRODUCT_LIST
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // --- Product List Screen (No changes needed here) ---
        composable(AppRoutes.PRODUCT_LIST) {
            ProductListScreen(
                onProductClick = { productId ->
                    navController.navigate("product_details/$productId")
                }
            )
        }

        // --- Product Details Screen (with deep link definition) ---
        composable(
            route = AppRoutes.PRODUCT_DETAILS,
            arguments = listOf(navArgument("productId") {
                type = NavType.StringType
            }),
            // --- THIS IS THE DEEP LINK LOGIC ---
            deepLinks = listOf(navDeepLink {
                // The URI pattern that this screen will respond to.
                // It must match the URI in your AndroidManifest.xml.
                uriPattern = "${AppRoutes.URI}/products/{productId}"
                // The action for the intent filter (standard for web links).
                action = Intent.ACTION_VIEW
            })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")

            ProductDetailsScreen(
                productId = productId ?: "ID not found via deep link",
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}


// --- 3. Create your screens as Composables (simplified for clarity) ---

@Composable
fun ProductListScreen(onProductClick: (productId: String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Product List Screen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onProductClick("prod-101") }) {
            Text("View Product 101")
        }
    }
}

@Composable
fun ProductDetailsScreen(productId: String, onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Product Details", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Product ID from URL:", fontSize = 18.sp)
        Text(productId, fontSize = 22.sp, color = Color.Blue, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onNavigateBack) {
            Text("Go Back")
        }
    }
}

*/