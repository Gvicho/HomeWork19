package com.example.homework18.data.repository_Impl

import android.util.Log
import com.example.homework18.data.mapper.toDomain
import com.example.homework18.data.services.UsersListService
import com.example.homework18.domain.user_list_repository.UsersListRepository
import com.example.homework18.presenter.common.User
import com.example.homework18.presenter.homepage.model.Users
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(private val usersListService: UsersListService) : UsersListRepository {

    override suspend fun getUsersList(): Flow<ResultWrapper<Users>> {
        return flow {
            emit(ResultWrapper.Loading(loading = true))
            try {
                val response = usersListService.getUserDetails()

                if(response.isSuccessful){

                    var userList :List<User> = emptyList()

                    response.body()!!.forEach{userDto ->
                        userList+= userDto.toDomain()
                    }
                    emit( ResultWrapper.Success( data = Users(
                        usersList = userList
                    ) ) )
                }else{

                    emit( ResultWrapper.Error(errorMessage = response.errorBody()?.string() ?: "emptyError") )
                }
            }catch (t:Throwable){

                Log.e("RequestFailed", "Transactions ${t.message}")
            }
            emit(ResultWrapper.Loading(loading = false))
        }
    }
}