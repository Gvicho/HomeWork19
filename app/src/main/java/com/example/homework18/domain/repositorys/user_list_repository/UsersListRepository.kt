package com.example.homework18.domain.repositorys.user_list_repository

import com.example.homework18.domain.model_entities.UsersEntity
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface UsersListRepository {
    suspend fun getUsersList() : Flow<ResultWrapper<UsersEntity>>
}