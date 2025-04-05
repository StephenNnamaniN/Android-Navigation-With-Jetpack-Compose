package com.nnamanistephen.meditationapp.presentation.screens.home

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.nnamanistephen.meditationapp.R
import com.nnamanistephen.meditationapp.navigation.MeditationScreen
import kotlinx.coroutines.delay

@Composable
fun MeditationAppSplash(navController: NavController){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.8f, animationSpec = tween(
            durationMillis = 800, easing = {
                OvershootInterpolator(8f)
                    .getInterpolation(it)
            }
        ))
        delay(2000L)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(MeditationScreen.LoginScreen.name)
        }
        navController.navigate(MeditationScreen.MainScreen.name)
    })
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(300.dp)
        .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Column(modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.meditation),
                contentDescription = "Meditation icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(95.dp)
                    .padding(10.dp))
            Text(text = "Deep Meditation", style = MaterialTheme.typography.titleMedium,
                color = Color.Blue)
            
        }
        
    }
}