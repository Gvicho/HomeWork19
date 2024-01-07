package com.example.homework18.di

import com.example.homework18.data.common.HandleResponse
import com.example.homework18.data.repository_Impl.UserDetailsRepositoryImpl
import com.example.homework18.data.repository_Impl.UserListRepositoryImpl
import com.example.homework18.data.services.UserDetailsService
import com.example.homework18.data.services.UsersListService
import com.example.homework18.domain.repositorys.user_details_repository.UserDetailsRepository
import com.example.homework18.domain.repositorys.user_list_repository.UsersListRepository
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
    fun provideUserListRepository(usersListService: UsersListService , handleResponse: HandleResponse): UsersListRepository {
        return UserListRepositoryImpl(
            usersListService = usersListService,
            handleResponse = handleResponse
        )
    }

    @Singleton
    @Provides
    fun provideUserDetailsRepository(userDetailsService: UserDetailsService, handleResponse: HandleResponse): UserDetailsRepository {
        return UserDetailsRepositoryImpl(
            userDetailsService = userDetailsService,
            handleResponse = handleResponse
        )
    }
}