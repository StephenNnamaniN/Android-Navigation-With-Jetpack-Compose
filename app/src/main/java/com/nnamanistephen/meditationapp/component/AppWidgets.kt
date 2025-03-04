package com.nnamanistephen.meditationapp.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.firebase.auth.FirebaseAuth
import com.nnamanistephen.meditationapp.R
import com.nnamanistephen.meditationapp.navigation.MeditationScreen
import java.io.Reader

// Re-usable design elements
@Preview
@Composable
fun MeditationAppLogo(modifier: Modifier = Modifier){
    Column(modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.meditation),
            contentDescription = "meditation icon",
            Modifier.size(90.dp), contentScale = ContentScale.Fit)
        Text(text = "Deep Meditation", style = MaterialTheme.typography.titleMedium,
            color = Color.Blue.copy(0.7f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralTopBar(
    title: String,
    icon: ImageVector? = null,
    onBackArrowPressed: () -> Unit = {},
    navController: NavController
){
    var expanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(title = {
        Text(text = title)},
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                if (icon != null){
                    Icon(imageVector = icon,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable { onBackArrowPressed.invoke() })
                }
            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert , contentDescription = "More options")
            }
            DropdownMenu(expanded = expanded,
                    onDismissRequest = {
                        expanded = false }) {
                DropdownMenuItem(text = { Text(text = "Profile")},
                    onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text(text = "About Us")},
                    onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text(text = "Settings")},
                    onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text(text = "Privacy policy")},
                    onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text(text = "Logout")},
                    onClick = { FirebaseAuth.getInstance().signOut().run {
                        navController.navigate(MeditationScreen.LoginScreen.name)
                    } })


            }
        })
}

@Composable
fun FABTab(onTap: () -> Unit){
    FloatingActionButton(onClick = { /*TODO*/ },
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.background) {
        Icon(imageVector = Icons.Filled.Search,
            contentDescription = "Search icon",
            tint = MaterialTheme.colorScheme.onBackground)
    }
}

sealed class BottomNavItems(
    val title: String,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector,
    val route: String)
{
    object Home: BottomNavItems("Home", Icons.Filled.Home, Icons.Outlined.Home, MeditationScreen.MainScreen.name)
    object Profile: BottomNavItems("Profile", Icons.Filled.Person, Icons.Outlined.Person, MeditationScreen.ProfileScreen.name)
    object About: BottomNavItems("About", Icons.Filled.Info, Icons.Outlined.Info, MeditationScreen.AboutScreen.name)
    object Settings: BottomNavItems("Settings", Icons.Filled.Settings, Icons.Outlined.Settings, MeditationScreen.SettingScreen.name)
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Profile,
        BottomNavItems.About,
        BottomNavItems.Settings
    )
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(selected = isSelected,
                onClick = {
                    if (!isSelected){
                        navController.navigate(item.route)
                    }
                },
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Icon(imageVector = if (isSelected) item.filledIcon else item.outlinedIcon,
                        contentDescription = item.title)
                })

        }
    }

}

@Composable
fun UserNameInput(usernameState: MutableState<String>,
                  labelId: String = "Username",
                  enabled: Boolean = true,
                  imeAction: ImeAction = ImeAction.Next,
                  onAction: KeyboardActions = KeyboardActions.Default
){
    OutlinedTextField(value = usernameState.value,
        onValueChange = { usernameState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = imeAction), keyboardActions = onAction)
}

@Composable
fun EmailInput(emailState: MutableState<String>,
                  labelId: String = "Email",
                  enabled: Boolean = true,
                  imeAction: ImeAction = ImeAction.Next,
                  onAction: KeyboardActions = KeyboardActions.Default
){
    OutlinedTextField(value = emailState.value,
        onValueChange = { emailState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email, imeAction = imeAction), keyboardActions = onAction)
}

@Composable
fun PasswordInput(passwordState: MutableState<String>,
                  labelId: String,
                  enabled: Boolean,
                  passwordVisibility: MutableState<Boolean>,
                  imeAction: ImeAction = ImeAction.Done,
                  onAction: KeyboardActions = KeyboardActions.Default
){
     val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
         PasswordVisualTransformation()

    OutlinedTextField(value = passwordState.value,
        onValueChange = { passwordState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility =passwordVisibility)},
        keyboardActions = onAction)
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value

    IconButton(onClick = { passwordVisibility.value = !visible}) {
        Icon(
            if (passwordVisibility.value){
                Icons.Filled.Visibility
            }else {
                Icons.Filled.VisibilityOff
            }, contentDescription = "Toggle password visibility icon"
        )
    }
}

@Composable
fun SubmitButton(textId: String,
                 loading: Boolean,
                 validInputs: Boolean,
                 onClick: () -> Unit) {
    Button(onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loading && validInputs,
        shape = CircleShape) {
        if (loading) CircularProgressIndicator(Modifier.size(25.dp))
        else Text(text = textId, Modifier.padding(5.dp))
    }
}
