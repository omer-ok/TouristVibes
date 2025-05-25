package com.ok.touristvibes.navigation.onBoardingNavigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ok.touristvibes.onboarding.CountryInfoScreen
import com.ok.touristvibes.onboarding.LoginWelcomeScreen
import com.ok.touristvibes.onboarding.OtpVerificationScreen
import com.ok.touristvibes.onboarding.SignUpScreen

@Composable
fun TouristVibeOnBoardingNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TouristVibeOnBoardingScreens.LoginWelcomeScreen.name){
        composable(TouristVibeOnBoardingScreens.LoginWelcomeScreen.name){
            LoginWelcomeScreen(navController = navController)
        }
        composable(TouristVibeOnBoardingScreens.SignUpScreen.name,  enterTransition = {
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
            SignUpScreen(navController = navController)
        }
        composable(TouristVibeOnBoardingScreens.CountryCodeSelectionScreen.name,  enterTransition = {
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
            }){backStackEntry ->
            val countryCode = backStackEntry.savedStateHandle.getStateFlow(
                "countryCode",
                initialValue = false,
            ).collectAsState().value
            CountryInfoScreen(navController = navController)
        }
        composable(TouristVibeOnBoardingScreens.OtpVerificationScreen.name,  enterTransition = {
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
            OtpVerificationScreen(navController = navController)
        }
    }
}

