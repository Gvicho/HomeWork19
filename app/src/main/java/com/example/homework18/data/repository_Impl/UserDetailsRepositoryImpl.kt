package com.example.homework18.data.repository_Impl

import android.util.Log
import com.example.homework18.data.mapper.toDomain
import com.example.homework18.data.services.UserDetailsService
import com.example.homework18.domain.user_details_repository.UserDetailsRepository
import com.example.homework18.presenter.user_details.model.UserDetails
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailsRepositoryImpl@Inject constructor(private val userDetailsService: UserDetailsService): UserDetailsRepository {
    override suspend fun getUsersDetails(id: Int): Flow<ResultWrapper<UserDetails>> {
        return flow {
            emit(ResultWrapper.Loading(loading = true))
            try {
                val response = userDetailsService.getUserDetails(id)

                if(response.isSuccessful){

                    val responseBody = response.body()

                    responseBody?.let {
                        emit( ResultWrapper.Success(
                            UserDetails(
                                data = it.data.toDomain(),
                                support = it.support.toDomain()
                            )
                        ) )
                    }

                }else{

                    emit( ResultWrapper.Error(errorMessage = response.errorBody()?.string() ?: "emptyError") )
                }
            }catch (t:Throwable){

                Log.e("RequestFailed", "${t.message}")
            }
            emit(ResultWrapper.Loading(loading = false))
        }
    }
}