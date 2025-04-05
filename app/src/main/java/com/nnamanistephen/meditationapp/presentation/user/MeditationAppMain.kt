package com.nnamanistephen.meditationapp.presentation.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nnamanistephen.meditationapp.domain.model.DomainUser
import com.nnamanistephen.meditationapp.navigation.MeditationScreen
import com.nnamanistephen.meditationapp.presentation.component.AddUserDialog
import com.nnamanistephen.meditationapp.presentation.component.BottomNavigationBar
import com.nnamanistephen.meditationapp.presentation.component.GeneralTopBar
import kotlinx.coroutines.launch

@Composable
fun MeditationAppMain(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
    ){
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UserViewModel.UIEvent.showSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                }
            }
        }
    }

    var showAddUserDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        GeneralTopBar(title = "MeditationApp", navController = navController)
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddUserDialog = true }
            ) {
                Icon(
                    Icons.Default.Add ,
                    contentDescription = "Add user"
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }) {innerPadding ->
        //Main content goes here
        Surface(modifier = Modifier.padding(innerPadding)) {
            if (state.isLoading){
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    items(state.users) { user ->
                        UserCard(
                            user = user,
                            onDelete = {
                                viewModel.onEvent(UserEvent.DeleteUser(user))
                            },
                            onClick = {
                                navController.navigate("${MeditationScreen.ProfileScreen.name}/${user.id}")
                            }
                        )
                    }
                }
            }
        }
    }
    if (showAddUserDialog){
        AddUserDialog(
            onDismiss = { showAddUserDialog = false },
            onAddUser = { user ->
                viewModel.onEvent(UserEvent.AddUser(user))
                showAddUserDialog = false
            }

        )
    }
}

@Composable
fun UserCard(
    user: DomainUser,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(modifier = Modifier
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.titleMedium)

                Text(text = "${user.email} ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))

            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red)
            }

        }

    }
}
