package com.nnamanistephen.meditationapp.domain.model

import com.nnamanistephen.meditationapp.data.model.UserEntity

class UserMapper {
    fun mapFromEntity(entity: UserEntity): DomainUser {
        return DomainUser(
            id = entity.id,
            firstName = entity.firstName,
            lastName = entity.lastName,
            dob = entity.dob,
            gender = entity.gender,
            phoneNumber = entity.phoneNumber,
            email = entity.email,
            address = entity.address
        )
    }

    fun mapToEntity(domain: DomainUser): UserEntity {
        return UserEntity(
            id = domain.id,
            firstName = domain.firstName,
            lastName = domain.lastName,
            dob = domain.dob,
            gender = domain.gender,
            phoneNumber = domain.phoneNumber,
            email = domain.email,
            address = domain.address
        )
    }
}