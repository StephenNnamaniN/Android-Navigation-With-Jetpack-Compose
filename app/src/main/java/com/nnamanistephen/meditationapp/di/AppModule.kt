package com.nnamanistephen.meditationapp.di

import android.content.Context
import androidx.room.Room
import com.nnamanistephen.meditationapp.data.local.UserDao
import com.nnamanistephen.meditationapp.data.local.UserDatabase
import com.nnamanistephen.meditationapp.data.repository.UserRepoImplementation
import com.nnamanistephen.meditationapp.domain.model.UserMapper
import com.nnamanistephen.meditationapp.domain.repository.UserRepository
import com.nnamanistephen.meditationapp.domain.usecases.AddUserUseCase
import com.nnamanistephen.meditationapp.domain.usecases.DeleteUserUseCase
import com.nnamanistephen.meditationapp.domain.usecases.GetUserByIdUseCase
import com.nnamanistephen.meditationapp.domain.usecases.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase{
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao, mapper: UserMapper): UserRepository{
        return UserRepoImplementation(userDao, mapper)
    }

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }

    @Provides
    @Singleton
    fun provideAddUserUseCases(repository: UserRepository): AddUserUseCase {
        return AddUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(repository: UserRepository): DeleteUserUseCase {
        return DeleteUserUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserByIdUseCase(repository: UserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(repository: UserRepository): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }

}