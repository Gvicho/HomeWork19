package com.example.homework18.di

import com.example.homework18.data.services.UserDetailsService
import com.example.homework18.data.services.UsersListService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL_UsersList_Mocky = "https://run.mocky.io/"
    private const val BASE_URL_UserDetails_Reqres = "https://reqres.in/"

    @Singleton
    @Provides
    @MockyRetrofitClient
    fun provideReqresRetrofitClient() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL_UsersList_Mocky)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    @ReqresRetrofitClient
    fun provideMockyRetrofitClient() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL_UserDetails_Reqres)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }


    @Singleton
    @Provides
    fun provideUsersListService(@MockyRetrofitClient retrofit: Retrofit): UsersListService {
        return retrofit.create(UsersListService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDetailsService(@ReqresRetrofitClient retrofit: Retrofit): UserDetailsService {
        return retrofit.create(UserDetailsService::class.java)
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockyRetrofitClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ReqresRetrofitClient