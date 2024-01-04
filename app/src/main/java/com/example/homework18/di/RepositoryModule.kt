package com.example.homework18.di

import com.example.homework18.data.repository_Impl.UserDetailsRepositoryImpl
import com.example.homework18.data.repository_Impl.UserListRepositoryImpl
import com.example.homework18.data.services.UserDetailsService
import com.example.homework18.data.services.UsersListService
import com.example.homework18.domain.user_details_repository.UserDetailsRepository
import com.example.homework18.domain.user_list_repository.UsersListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserListRepository(usersListService: UsersListService): UsersListRepository{
        return UserListRepositoryImpl(
            usersListService = usersListService
        )
    }

    @Singleton
    @Provides
    fun provideUserDetailsRepository(userDetailsService: UserDetailsService): UserDetailsRepository{
        return UserDetailsRepositoryImpl(
            userDetailsService = userDetailsService
        )
    }
}