package com.example.homework18.domain.user_list_repository

import com.example.homework18.presenter.homepage.model.Users
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface UsersListRepository {
    suspend fun getUsersList() : Flow<ResultWrapper<Users>>
}