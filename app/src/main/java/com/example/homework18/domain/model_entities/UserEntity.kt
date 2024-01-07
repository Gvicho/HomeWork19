package com.example.homework18.domain.model_entities

data class UserEntity(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
) {
}