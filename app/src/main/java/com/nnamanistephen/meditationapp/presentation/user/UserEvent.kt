package com.nnamanistephen.meditationapp.presentation.user

import com.nnamanistephen.meditationapp.domain.model.DomainUser
import com.nnamanistephen.meditationapp.domain.usecases.SortType

sealed class UserEvent {
    data class DeleteUser(val user: DomainUser): UserEvent()
    data class AddUser(val user: DomainUser): UserEvent()
    data class SortUser(val sortType: SortType): UserEvent()
}

