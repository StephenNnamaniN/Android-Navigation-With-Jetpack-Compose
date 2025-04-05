package com.nnamanistephen.meditationapp.domain.usecases

import com.nnamanistephen.meditationapp.domain.model.DomainUser
import com.nnamanistephen.meditationapp.domain.repository.UserRepository

class AddUserUseCase(private val repository: UserRepository){
    suspend operator  fun invoke(user: DomainUser): Long = repository.addUser(user)
}

class DeleteUserUseCase(private val repository: UserRepository){
    suspend operator  fun invoke(user: DomainUser) = repository.deleteUser(user)
}

class GetUsersUseCase(private val repository: UserRepository){
    suspend operator  fun invoke(sortType: SortType): List<DomainUser>
    = repository.getUsersSortedBy(sortType)
}
class GetUserByIdUseCase(private val repository: UserRepository){
    suspend operator  fun invoke(id: Int): DomainUser? = repository.getUserById(id)
}