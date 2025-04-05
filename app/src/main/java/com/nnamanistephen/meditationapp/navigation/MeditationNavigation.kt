package com.nnamanistephen.meditationapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nnamanistephen.meditationapp.presentation.screens.about.AboutScreen
import com.nnamanistephen.meditationapp.presentation.screens.createAccount.CreateAccountScreen
import com.nnamanistephen.meditationapp.presentation.screens.login.LoginScreen
import com.nnamanistephen.meditationapp.presentation.user.MeditationAppMain
import com.nnamanistephen.meditationapp.presentation.screens.home.MeditationAppSplash
import com.nnamanistephen.meditationapp.presentation.userdetails.ProfileScreen
import com.nnamanistephen.meditationapp.presentation.screens.settings.SettingsScreen

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
        composable("${MeditationScreen.ProfileScreen.name}/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.IntType
            })
        ){ backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: return@composable
            ProfileScreen(navController = navController, userId = userId)
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