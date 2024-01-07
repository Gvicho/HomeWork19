package com.example.homework18.domain.model_entities

data class UserDetails(
    val data: UserEntity,
    val support: Support
)

data class Support(
    val url:String,
    val text:String
)