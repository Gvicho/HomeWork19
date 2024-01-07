package com.example.homework18.data.repository_Impl

import com.example.homework18.data.common.HandleResponse
import com.example.homework18.data.common.mapToDomain
import com.example.homework18.data.mapper.toDomain
import com.example.homework18.data.services.UsersListService
import com.example.homework18.domain.repositorys.user_list_repository.UsersListRepository
import com.example.homework18.domain.model_entities.UsersEntity
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(
    private val usersListService: UsersListService,
    private val handleResponse: HandleResponse
) : UsersListRepository {

    override suspend fun getUsersList(): Flow<ResultWrapper<UsersEntity>> {
        return handleResponse.safeApiCall {
            usersListService.getUserDetails()
        }.mapToDomain {dtoList ->
            UsersEntity(usersList = dtoList.map { it.toDomain() })
        }


    }
}