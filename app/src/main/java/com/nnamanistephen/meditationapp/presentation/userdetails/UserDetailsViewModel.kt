package com.nnamanistephen.meditationapp.presentation.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnamanistephen.meditationapp.domain.model.DomainUser
import com.nnamanistephen.meditationapp.domain.usecases.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel(){
    private val _state = MutableStateFlow(UserDetailState())
    val state: StateFlow<UserDetailState> = _state.asStateFlow()

    fun loadUserDetails(userId: Int){
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            try {
                val user = getUserByIdUseCase(userId)
                _state.value = state.value.copy(
                    user = user,
                    isLoading = false
                )
            } catch (e: Exception){
                _state.value = state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

data class UserDetailState(
    val user: DomainUser? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
