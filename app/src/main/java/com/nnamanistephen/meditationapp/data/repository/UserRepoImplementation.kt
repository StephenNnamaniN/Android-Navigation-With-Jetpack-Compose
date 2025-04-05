package com.nnamanistephen.meditationapp.data.repository

import com.nnamanistephen.meditationapp.data.local.UserDao
import com.nnamanistephen.meditationapp.domain.model.DomainUser
import com.nnamanistephen.meditationapp.domain.model.UserMapper
import com.nnamanistephen.meditationapp.domain.repository.UserRepository
import com.nnamanistephen.meditationapp.domain.usecases.SortType
import javax.inject.Inject

class UserRepoImplementation @Inject constructor(
    private val userDao: UserDao,
    private val mapper: UserMapper
): UserRepository {
    override suspend fun addUser(user: DomainUser): Long {
        return userDao.insertUser(mapper.mapToEntity(user))
    }

    override suspend fun deleteUser(user: DomainUser) {
        userDao.deleteUser(mapper.mapToEntity(user))
    }

    override suspend fun getUsersSortedBy(sortType: SortType): List<DomainUser> {
        return when(sortType){
            SortType.FIRST_NAME -> userDao.getUserSortedByFirstName()
            SortType.LAST_NAME -> userDao.getUserSortedByLastName()
            SortType.GENDER -> userDao.getUserSortedByGender()
        }.map { mapper.mapFromEntity(it) }
    }

    override suspend fun getUserById(id: Int): DomainUser? {
        return userDao.getUserById(id)?.let { mapper.mapFromEntity(it) }
    }
}