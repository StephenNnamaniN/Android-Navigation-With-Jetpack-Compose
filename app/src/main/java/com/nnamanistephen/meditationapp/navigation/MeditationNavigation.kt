package com.nnamanistephen.meditationapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nnamanistephen.meditationapp.Screen.about.AboutScreen
import com.nnamanistephen.meditationapp.Screen.createAccount.CreateAccountScreen
import com.nnamanistephen.meditationapp.Screen.login.LoginScreen
import com.nnamanistephen.meditationapp.Screen.home.MeditationAppMain
import com.nnamanistephen.meditationapp.Screen.home.MeditationAppSplash
import com.nnamanistephen.meditationapp.Screen.profile.ProfileScreen
import com.nnamanistephen.meditationapp.Screen.settings.SettingsScreen

@Composable
fun MeditationNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = MeditationScreen.SplashScreen.name) {
        composable(MeditationScreen.SplashScreen.name){
            MeditationAppSplash(navController = navController)
        }

        composable(MeditationScreen.MainScreen.name){
            MeditationAppMain(navController = navController)
        }
        composable(MeditationScreen.ProfileScreen.name){
            ProfileScreen(navController = navController)
        }
        composable(MeditationScreen.AboutScreen.name){
            AboutScreen(navController = navController)
        }
        composable(MeditationScreen.SettingScreen.name){
            SettingsScreen(navController = navController)
        }
        composable(MeditationScreen.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(MeditationScreen.CreateAccountScreen.name){
            CreateAccountScreen(navController = navController)
        }

    }
}