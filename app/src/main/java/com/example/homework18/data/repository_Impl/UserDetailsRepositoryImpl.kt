package com.example.homework18.data.repository_Impl

import com.example.homework18.data.common.HandleResponse
import com.example.homework18.data.mapper.toDomain
import com.example.homework18.data.services.UserDetailsService
import com.example.homework18.domain.user_details_repository.UserDetailsRepository
import com.example.homework18.presenter.user_details.model.UserDetails
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDetailsRepositoryImpl@Inject constructor(
    private val userDetailsService: UserDetailsService,
    private val handleResponse: HandleResponse
): UserDetailsRepository {
    override suspend fun getUsersDetails(id: Int): Flow<ResultWrapper<UserDetails>> {
        return handleResponse.safeApiCall {
            userDetailsService.getUserDetails(id)
        }.map {
            when(it){
                is ResultWrapper.Success -> {
                    ResultWrapper.Success( data =
                    UserDetails(
                        data = it.data!!.data.toDomain(),
                        support = it.data.support.toDomain()
                    ))
                }

                is ResultWrapper.Error -> {
                    ResultWrapper.Error(errorMessage = it.errorMessage ?: "emptyError")
                }
                is ResultWrapper.Loading -> {
                    ResultWrapper.Loading(loading = it.loading)
                }
            }
        }
    }
}