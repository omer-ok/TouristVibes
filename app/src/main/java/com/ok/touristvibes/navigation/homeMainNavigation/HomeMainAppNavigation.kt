package com.ok.touristvibes.navigation.homeMainNavigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ok.touristvibes.addProduct.AddProduct
import com.ok.touristvibes.cameraXScreen.CameraXScreen
import com.ok.touristvibes.home.HomeMainApp
import com.ok.touristvibes.home.ProductDetailScreen

@Composable
fun HomeMainAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeMainAppScreens.HomeMainScreen.name){
        composable(HomeMainAppScreens.HomeMainScreen.name){
            HomeMainApp(navController = navController)
        }
        composable(
            HomeMainAppScreens.ProductDetailScreen.name+"/{foodId}", arguments = listOf(
                navArgument(name = "foodId") {type = NavType.IntType}
            ),  enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }){
                backStackEntry ->
            backStackEntry.arguments?.getInt("foodId")
                ?.let { ProductDetailScreen(navController = navController, it) }
        }

        composable(
            HomeMainAppScreens.AddProductScreen.name,  enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }){
                backStackEntry -> backStackEntry.arguments?.let { AddProduct(navController = navController) }
        }

        composable(
            HomeMainAppScreens.CameraXScreen.name,  enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }){
                backStackEntry -> backStackEntry.arguments?.let { CameraXScreen(navController = navController) }
        }
    }
}