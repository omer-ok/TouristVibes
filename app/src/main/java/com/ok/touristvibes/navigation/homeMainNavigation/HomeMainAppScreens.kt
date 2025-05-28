package com.ok.touristvibes.navigation.homeMainNavigation

import com.ok.touristvibes.cameraXScreen.CameraXScreen

enum class HomeMainAppScreens {
    HomeMainScreen,
    AddProductScreen,
    CameraXScreen,
    ProductDetailScreen;
    companion object {
        fun fromRoute(route:String?): HomeMainAppScreens = when(route?.substringBefore("/")){
            HomeMainScreen.name -> HomeMainScreen
            AddProductScreen.name -> AddProductScreen
            CameraXScreen.name -> CameraXScreen
            ProductDetailScreen.name -> ProductDetailScreen
            null -> HomeMainScreen
            else -> throw IllegalArgumentException("Route $route ia not recognized")
        }
    }
}