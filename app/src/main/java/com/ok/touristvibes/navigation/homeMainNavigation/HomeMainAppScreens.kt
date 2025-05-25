package com.ok.touristvibes.navigation.homeMainNavigation

enum class HomeMainAppScreens {
    HomeMainScreen,
    ProductDetailScreen;
    companion object {
        fun fromRoute(route:String?): HomeMainAppScreens = when(route?.substringBefore("/")){
            HomeMainScreen.name -> HomeMainScreen
            ProductDetailScreen.name -> ProductDetailScreen
            null -> HomeMainScreen
            else -> throw IllegalArgumentException("Route $route ia not recognized")
        }
    }
}