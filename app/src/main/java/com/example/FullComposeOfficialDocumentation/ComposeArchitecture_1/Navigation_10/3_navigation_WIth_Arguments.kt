package com.example.FullComposeOfficialDocumentation.ComposeArchitecture_1.Navigation_10

import androidx.compose.foundation.clickable
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


object AppppRoutes{
    const val PRODUCT_LIST="product_list"
    const val PRODUCT_DETAILS="product_details/{productId}/{itemCount}/{inStack}"

    //Helper function to build the complex routes safely

    fun productDetailsRoute(productId:String,itemCount:Int,inStack:Boolean):String{
        return "product_details/$productId/$itemCount/$inStack"
    }
}
//Setting up the navHost
@Composable
fun AppppNavHost(
    modifier:Modifier=Modifier,
    navController: NavHostController= rememberNavController(),
    startDestination:String= AppppRoutes.PRODUCT_LIST
){
    NavHost(
        modifier=modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(AppppRoutes.PRODUCT_LIST){
            ProductListScreen(
                onProductClick = {productId,itemCount,inStack->
                    navController.navigate(
                        AppppRoutes.productDetailsRoute(productId,itemCount,inStack)
                    )
                }
            )
        }

        composable(
            route= AppppRoutes.PRODUCT_DETAILS,
            arguments=listOf(
                navArgument("productId"){type=NavType.StringType},
                navArgument("itemCount"){type=NavType.IntType},
                navArgument("inStack"){type=NavType.BoolType}
            )
        ){backStackEntry->
            val productId=backStackEntry.arguments?.getString("productId")
            val itemCount=backStackEntry.arguments?.getInt("itemCount")
            val inStack=backStackEntry.arguments?.getBoolean("inStack")

            ProductDetailScreen(
                productId=productId?:"N/A",
                itemCount=itemCount?:0,
                inStack=inStack?:false,
                onNavigateBack={navController.popBackStack()}
            )

        }
    }

}
@Composable
fun ProductListScreen(onProductClick:(id:String,count:Int,stack: Boolean)->Unit){
    val products=listOf(Triple("prod-101",5,true),
        Triple("prod-102",0,false),
        Triple("prod-103",12,true)
    )

    Column(
        modifier=Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text="Select a product",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier=Modifier.height(24.dp))
        products.forEach{
            (id,count,stack)->
            Text(
                text="Product id:$id",
                modifier=Modifier.clickable{onProductClick(id,count,stack)}
                    .padding(16.dp),
                fontSize=18.sp
            )

        }

    }
}

@Composable

fun ProductDetailScreen(
    productId:String,
    itemCount:Int,
    inStack:Boolean,
    onNavigateBack:()-> Unit
){
    Column(
        modifier=Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Product Details", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier=Modifier.height(24.dp))

        Text("Product Id (String):", fontSize = 18.sp)
        Text(productId, fontSize =22.sp,color=Color.Blue, fontWeight = FontWeight.Bold)

        Spacer(modifier=Modifier.height(16.dp))

        Text("Items in Cart (Int):", fontSize = 18.sp)
        Text(itemCount.toString(), fontSize = 22.sp, color = Color.Blue, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))

        Text("In Stock (Boolean):", fontSize = 18.sp)
        Text(inStack.toString(), fontSize = 22.sp, color = if (inStack) Color.Green else Color.Red, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateBack){
            Text("Go Back")
        }

    }

}