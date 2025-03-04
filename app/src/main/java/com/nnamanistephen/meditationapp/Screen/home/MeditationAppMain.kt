package com.nnamanistephen.meditationapp.Screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.nnamanistephen.meditationapp.component.BottomNavigationBar
import com.nnamanistephen.meditationapp.component.FABTab
import com.nnamanistephen.meditationapp.component.GeneralTopBar

@Composable
fun MeditationAppMain(navController: NavController){
    Scaffold(topBar = {
        GeneralTopBar(title = "MeditationApp", navController = navController)
    },
        floatingActionButton = {
            FABTab {
                
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }) {innerPadding ->
        //Main content goes here
        Surface(modifier = Modifier.padding(innerPadding)) {

        }
    }
}