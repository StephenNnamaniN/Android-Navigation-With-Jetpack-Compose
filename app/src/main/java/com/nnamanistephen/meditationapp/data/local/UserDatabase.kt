package com.nnamanistephen.meditationapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nnamanistephen.meditationapp.data.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
    )
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}