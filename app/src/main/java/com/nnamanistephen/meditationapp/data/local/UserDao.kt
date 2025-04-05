package com.nnamanistephen.meditationapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nnamanistephen.meditationapp.data.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users ORDER BY firstname ASC")
    suspend fun getUserSortedByFirstName(): List<UserEntity>

    @Query("SELECT * FROM users ORDER BY lastName ASC")
    suspend fun getUserSortedByLastName(): List<UserEntity>

    @Query("SELECT * FROM users ORDER BY gender ASC")
    suspend fun getUserSortedByGender(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserEntity?

    @Delete
    suspend fun deleteUser(user: UserEntity)
}