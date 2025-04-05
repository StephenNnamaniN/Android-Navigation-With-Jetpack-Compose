package com.nnamanistephen.meditationapp.presentation.user

import com.nnamanistephen.meditationapp.domain.model.DomainUser

data class UserState(
    val users: List<DomainUser> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
