package com.nnamanistephen.meditationapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String?,
    val lastName: String?,
    val dob: Long,
    val gender: String,
    val phoneNumber: String,
    val email: String,
    val address: String
)
