package com.ok.touristvibes.navigation.onBoardingNavigation

enum class TouristVibeOnBoardingScreens {
    LoginWelcomeScreen,
    SignUpScreen,
    CountryCodeSelectionScreen,
    OtpVerificationScreen;

    companion object {
        fun fromRoute(route:String?): TouristVibeOnBoardingScreens
        = when(route?.substringBefore("/")){
            LoginWelcomeScreen.name -> LoginWelcomeScreen
            SignUpScreen.name -> SignUpScreen
            CountryCodeSelectionScreen.name -> CountryCodeSelectionScreen
            OtpVerificationScreen.name -> OtpVerificationScreen
            null -> LoginWelcomeScreen
            else -> throw IllegalArgumentException("Route $route ia not recognized")
        }
    }
}