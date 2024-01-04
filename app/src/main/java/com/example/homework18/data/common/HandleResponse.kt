package com.example.homework18.data.common

import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HandleResponse @Inject constructor() {
    fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Flow<ResultWrapper<T>> = flow {
        try {
            emit(ResultWrapper.Loading(loading = true))
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResultWrapper.Success(data = body))
            } else {
                emit(ResultWrapper.Error(errorMessage = response.errorBody()?.string() ?: ""))
            }
        } catch (e: Throwable) {
            emit(ResultWrapper.Error(errorMessage = e.message ?: ""))
        } finally {
            emit(ResultWrapper.Loading(loading = false))
        }
    }
}