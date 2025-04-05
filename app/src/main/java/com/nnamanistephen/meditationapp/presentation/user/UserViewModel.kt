package com.nnamanistephen.meditationapp.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnamanistephen.meditationapp.domain.usecases.AddUserUseCase
import com.nnamanistephen.meditationapp.domain.usecases.DeleteUserUseCase
import com.nnamanistephen.meditationapp.domain.usecases.GetUsersUseCase
import com.nnamanistephen.meditationapp.domain.usecases.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val addUserUseCase: AddUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentSortType = SortType.FIRST_NAME

    init {
        loadUsers()
    }

    fun onEvent(event: UserEvent){
        when (event){
            is UserEvent.DeleteUser -> {
                viewModelScope.launch {
                    try {
                        deleteUserUseCase(event.user)
                        loadUsers()
                        _eventFlow.emit(UIEvent.showSnackBar("User deleted"))
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UIEvent.showSnackBar("Couldn't delete. ${e.message}")
                        )
                    }
                }
            }
            is  UserEvent.AddUser -> {
                viewModelScope.launch {
                    try {
                        addUserUseCase(event.user)
                        loadUsers()
                        _eventFlow.emit(UIEvent.showSnackBar("User added"))
                    }catch (e: Exception){
                        _eventFlow.emit(UIEvent.showSnackBar("Couldn't add user. ${e.message}"))
                    }
                }
            }
            is UserEvent.SortUser -> {
                currentSortType = event.sortType
                loadUsers()
            }
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true,
                users = emptyList()
            )
            try {
                val users = getUsersUseCase(currentSortType)
                _state.value = state.value.copy(
                    users = users,
                    isLoading = false
                )
            }catch (e: Exception) {
                _state.value = state.value.copy(
                    isLoading = false,
                    error = e.message
                )
                _eventFlow.emit(UIEvent.showSnackBar("Error loading users"))
            }
        }
    }

    sealed class UIEvent {
        data class showSnackBar(val message: String): UIEvent()
    }
}

