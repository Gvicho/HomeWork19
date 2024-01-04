package com.example.homework18.data.services

import com.example.homework18.data.model.UserDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsService {
    @GET("api/users/{id}")
    suspend fun getUserDetails(@Path("id") userId: Int): Response<UserDetailDto>
}