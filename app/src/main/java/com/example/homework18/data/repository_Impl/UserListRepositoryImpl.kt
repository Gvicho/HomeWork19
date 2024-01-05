package com.example.homework18.data.repository_Impl

import com.example.homework18.data.common.HandleResponse
import com.example.homework18.data.common.mapToDomain
import com.example.homework18.data.mapper.toDomain
import com.example.homework18.data.services.UsersListService
import com.example.homework18.domain.user_list_repository.UsersListRepository
import com.example.homework18.presenter.homepage.model.Users
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(
    private val usersListService: UsersListService,
    private val handleResponse: HandleResponse
) : UsersListRepository {

    override suspend fun getUsersList(): Flow<ResultWrapper<Users>> {
        return handleResponse.safeApiCall {
            usersListService.getUserDetails()
        }.mapToDomain {dtoList ->
            Users(usersList = dtoList.map { it.toDomain() })
        }


    }
}