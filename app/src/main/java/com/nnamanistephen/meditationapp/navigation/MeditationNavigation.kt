package com.nnamanistephen.meditationapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nnamanistephen.meditationapp.Screen.MeditationAppMain
import com.nnamanistephen.meditationapp.Screen.MeditationAppSplash
import com.nnamanistephen.meditationapp.Screen.MeditationScreen

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

    }
}