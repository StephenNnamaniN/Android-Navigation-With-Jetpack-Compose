package com.nnamanistephen.meditationapp.Screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun emailAndPasswordSignIn(email: String, password: String, mainScreen: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){
                            mainScreen()
                        } else {
                            Log.d("Firebase", "emailAndPasswordSignIn: ${task.result.toString()}")
                        }

                    }
            }catch (ex: Exception){
                Log.d("Firebase", "emailAndPasswordSignIn: ${ex.message}")
            }
        }

    fun createNewUser(email: String, password: String, mainScreen: () -> Unit){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        mainScreen()
                    }else {
                        Log.d("Firebase", "emailAndPasswordSignIn: ${task.result.toString()}")
                    }

                }
        }
    }
}