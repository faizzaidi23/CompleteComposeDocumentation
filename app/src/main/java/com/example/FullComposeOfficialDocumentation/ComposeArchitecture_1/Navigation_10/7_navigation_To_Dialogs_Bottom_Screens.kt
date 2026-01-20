package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Navigation_10

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

/*
Normally, you might show a dialog by managing a mutableStateOf(false) and setting it to true. This works, but it has drawbacks:

State Management: The state for showing the dialog is tied to the screen that launches it. If you navigate away and come back, that state might be lost.

No Back Button Support: The system's back button won't dismiss the dialog by default.

No Deep Linking: You can't create a URL that directly opens a specific dialog in your app.
*/

object ApppRoutes{
    const val HOME="home"
    const val INFO_DIALOG="info_dialog/{message}"
    const val OPTIONS_SHEET="options_sheet/{itemId}"

    fun infoDialogRoute(message:String)="info_dialog/$message"
    fun optionsSheetRoute(itemId:String)="options_sheet/$itemId"
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun AppWithDialogAndSheetNav(){
    //For bottom sheet navigation we need a special navigator

    val bottomSheetNavigator= rememberBottomSheetNavigator()
    val navController= rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        AppNavHost(navController = navController)
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavHosts(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = ApppRoutes.HOME
    ){
        composable(ApppRoutes.HOME){
            HomeScreenn(onShowDialog = {
                val message="This is a dynamic message"
                navController.navigate(ApppRoutes.infoDialogRoute(message =message))
            },
                onShowBottomSheet = {
                    val itemId="item-456"
                    navController.navigate(ApppRoutes.optionsSheetRoute(itemId = itemId))
                })

        }

        dialog(
            route=ApppRoutes.INFO_DIALOG,
            arguments = listOf(navArgument("message"){
                type= NavType.StringType
            })
        ){
            backStackEntry->
            val message=backStackEntry.arguments?.getString("message")?:"No message"
            InfoDialog(
                message,
                onDismiss = {navController.popBackStack()}
            )
        }

        bottomSheet(
            route = ApppRoutes.OPTIONS_SHEET,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "N/A"
            OptionsSheet(
                itemId = itemId,
                onDismiss = { navController.popBackStack() }
            )
        }
    }
}

// Screen and destination composable

@Composable
fun HomeScreenn(onShowDialog:()->Unit, onShowBottomSheet:()-> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", fontSize = 24.sp)
        Spacer(Modifier.height(20.dp))
        Button(onClick = onShowDialog) { Text("Show Info Dialog") }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onShowBottomSheet) { Text("Show Options Sheet") }
    }

}


@Composable
fun InfoDialog(message: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Information", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(16.dp))
                Text(message)
                Spacer(Modifier.height(24.dp))
                Button(onClick = onDismiss) { Text("OK") }
            }
        }
    }
}

@Composable
fun OptionsSheet(itemId: String, onDismiss: () -> Unit) {
    Surface(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Options", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Text("You have selected item ID:")
            Text(itemId, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onDismiss) { Text("Close Sheet") }
        }
    }
}