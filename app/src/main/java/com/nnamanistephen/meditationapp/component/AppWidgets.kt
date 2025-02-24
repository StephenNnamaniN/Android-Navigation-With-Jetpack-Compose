package com.nnamanistephen.meditationapp.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.nnamanistephen.meditationapp.R

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
