package com.nnamanistephen.meditationapp.navigation

enum class MeditationScreen {
    SplashScreen,
    MainScreen,
    AboutScreen,
    SettingScreen,
    LoginScreen,
    CreateAccountScreen;

    companion object {
        fun fromRoute(route: String?): MeditationScreen
        = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            MainScreen.name -> MainScreen
            AboutScreen.name -> AboutScreen
            SettingScreen.name -> SettingScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}