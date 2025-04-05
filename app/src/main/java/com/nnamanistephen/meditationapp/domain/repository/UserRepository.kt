package com.nnamanistephen.meditationapp.domain.repository

import com.nnamanistephen.meditationapp.domain.model.DomainUser
import com.nnamanistephen.meditationapp.domain.usecases.SortType

interface UserRepository {
    suspend fun addUser(user: DomainUser): Long
    suspend fun deleteUser(user: DomainUser)
    suspend fun getUsersSortedBy(sortType: SortType): List<DomainUser>
    suspend fun getUserById(id: Int): DomainUser?
}