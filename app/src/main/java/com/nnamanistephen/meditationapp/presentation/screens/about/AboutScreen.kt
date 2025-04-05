package com.nnamanistephen.meditationapp.presentation.screens.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.nnamanistephen.meditationapp.presentation.component.BottomNavigationBar
import com.nnamanistephen.meditationapp.presentation.component.GeneralTopBar

@Composable
fun AboutScreen(navController: NavController){
    Scaffold(topBar = {
        GeneralTopBar(title = "About Us", navController = navController)
    },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }) {innerPadding ->
        //Main content goes here
        Surface(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Learn about us...")
        }
    }
}