package com.example.homework18.data.repository_Impl

import com.example.homework18.data.common.HandleResponse
import com.example.homework18.data.mapper.toDomain
import com.example.homework18.data.services.UsersListService
import com.example.homework18.domain.user_list_repository.UsersListRepository
import com.example.homework18.presenter.common.User
import com.example.homework18.presenter.homepage.model.Users
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(
    private val usersListService: UsersListService,
    private val handleResponse: HandleResponse
) : UsersListRepository {

    override suspend fun getUsersList(): Flow<ResultWrapper<Users>> {
        return handleResponse.safeApiCall {
            usersListService.getUserDetails()
        }.map {
            when(it){
                is ResultWrapper.Success -> {
                    var userList :List<User> = emptyList()

                    it.data!!.forEach{userDto ->  // data will never be null
                        userList+= userDto.toDomain()
                    }
                    ResultWrapper.Success( data = Users(
                        usersList = userList
                    ) )
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