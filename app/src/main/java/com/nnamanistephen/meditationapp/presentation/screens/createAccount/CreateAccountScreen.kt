package com.nnamanistephen.meditationapp.presentation.screens.createAccount

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nnamanistephen.meditationapp.presentation.screens.login.LoginViewModel
import com.nnamanistephen.meditationapp.presentation.component.EmailInput
import com.nnamanistephen.meditationapp.presentation.component.MeditationAppLogo
import com.nnamanistephen.meditationapp.presentation.component.PasswordInput
import com.nnamanistephen.meditationapp.presentation.component.SubmitButton
import com.nnamanistephen.meditationapp.presentation.component.UserNameInput
import com.nnamanistephen.meditationapp.navigation.MeditationScreen

@Composable
fun CreateAccountScreen(navController: NavController, viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(top = 45.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            MeditationAppLogo()
            CreateUserForm(loading = false){username, email, password ->
                viewModel.createNewUser(email, password){
                    navController.navigate(MeditationScreen.MainScreen.name)
                }

            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                Modifier.padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Already have account?")
                Text(text = "Login",
                    Modifier
                        .clickable {
                            navController.navigate(MeditationScreen.LoginScreen.name)
                        }
                        .padding(start = 5.dp), fontWeight = FontWeight.Bold,
                    color = Color.Blue)
            }
        }

    }
}

@Composable
fun CreateUserForm(loading: Boolean = false,
                  onDone: (String, String, String) -> Unit = {
                          username, email, pwd -> }){
    val focusManager = LocalFocusManager.current

    val username = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(username.value, email.value, password.value) {
        username.value.trim().isNotEmpty() && email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }


    val modifier = Modifier
        .height(320.dp)
        .background(MaterialTheme.colorScheme.background)

    Column (modifier, horizontalAlignment = Alignment.CenterHorizontally){
        UserNameInput(usernameState = username,
            enabled = !loading,
            onAction = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Down)
            })

        EmailInput(emailState = email,
            enabled = !loading,
            onAction = KeyboardActions {
                focusManager.moveFocus(FocusDirection.Down)
            })

        PasswordInput(passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(username.value.trim(), email.value.trim(), password.value.trim())
            })

        SubmitButton(textId = "Create Account",
            loading = loading,
            validInputs = valid) {
            onDone(username.value.trim(), email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }

}