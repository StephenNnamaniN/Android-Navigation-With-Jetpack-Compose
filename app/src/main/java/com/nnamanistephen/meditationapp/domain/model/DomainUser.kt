package com.nnamanistephen.meditationapp.domain.model

data class DomainUser(
    val id: Int = 0,
    val firstName: String?,
    val lastName: String?,
    val dob: Long,
    val gender: String,
    val phoneNumber: String,
    val email: String,
    val address: String
)
