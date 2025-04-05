package com.nnamanistephen.meditationapp.presentation.userdetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nnamanistephen.meditationapp.R
import com.nnamanistephen.meditationapp.presentation.component.BottomNavigationBar
import com.nnamanistephen.meditationapp.presentation.component.GeneralTopBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfileScreen(
    userId: Int,
    navController: NavController,
    viewModel: UserDetailsViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
        GeneralTopBar(
            title = "Profile",
            icon = Icons.Default.ArrowBack,
            navController = navController
        )
    },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }) {innerPadding ->
        //Main content goes here
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LaunchedEffect(key1 = userId) {
                    viewModel.loadUserDetails(userId)
                }

                val state by viewModel.state.collectAsState()

                if(state.isLoading){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                } else {
                    state.user?.let { user ->
                       Column(
                           modifier = Modifier
                               .padding(16.dp)
                               .fillMaxSize(),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Top
                       ) {
                           Surface(
                               modifier = Modifier
                                   .padding(0.dp)
                                   .size(200.dp),
                               shape = CircleShape,
                               color = Color.White,
                               border = BorderStroke(width = 2.dp, color = Color.LightGray)
                           ) {
                               Image(
                                   painter = painterResource(id = R.drawable.professor),
                                   contentDescription = null
                               )
                           }

                           Card(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(10.dp),
                               colors = CardDefaults.cardColors(Color(0xFFFF9800)),
                               elevation = CardDefaults.elevatedCardElevation()
                           ) {
                               Column(modifier = Modifier.padding(16.dp)
                               ) {
                                   Text(text = "Name: ${user.firstName} ${user.lastName}",
                                       style = MaterialTheme.typography.titleMedium,
                                       fontWeight = FontWeight.Bold
                                   )
                                   HorizontalDivider(modifier = Modifier
                                       .padding(top = 4.dp, bottom = 4.dp)
                                       .fillMaxWidth())

                                   Text(text = "Date of Birth: ${SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(
                                       Date(user.dob)
                                   )}",
                                       style = MaterialTheme.typography.titleMedium,

                                   )
                                   HorizontalDivider(modifier = Modifier
                                       .padding(top = 4.dp, bottom = 4.dp)
                                       .fillMaxWidth())

                                   Text(text = "Gender: ${user.gender}",
                                       style = MaterialTheme.typography.titleMedium,
                                   )
                                   HorizontalDivider(modifier = Modifier
                                       .padding(top = 4.dp, bottom = 4.dp)
                                       .fillMaxWidth())

                                   Text(text = "Phone Number: ${user.phoneNumber} ",
                                       style = MaterialTheme.typography.titleMedium,
                                   )
                                   HorizontalDivider(modifier = Modifier
                                       .padding(top = 4.dp, bottom = 4.dp)
                                       .fillMaxWidth())

                                   Text(text = "Email Address: ${user.email} ",
                                       style = MaterialTheme.typography.titleMedium,
                                   )
                                   HorizontalDivider(modifier = Modifier
                                       .padding(top = 4.dp, bottom = 4.dp)
                                       .fillMaxWidth())

                                   Text(text = "Home Address: ${user.address} ",
                                       style = MaterialTheme.typography.titleMedium,
                                   )
                                   HorizontalDivider(modifier = Modifier
                                       .padding(top = 4.dp, bottom = 4.dp)
                                       .fillMaxWidth())

                               }
                           }
                       }
                    }
                }
            }
        }
    }
}