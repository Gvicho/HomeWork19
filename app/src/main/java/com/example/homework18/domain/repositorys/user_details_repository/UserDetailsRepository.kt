package com.example.homework18.domain.repositorys.user_details_repository

import com.example.homework18.domain.model_entities.UserDetails
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    suspend fun getUsersDetails(id:Int) : Flow<ResultWrapper<UserDetails>>
}