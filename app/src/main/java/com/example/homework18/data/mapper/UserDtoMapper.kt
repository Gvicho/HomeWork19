package com.example.homework18.data.mapper

import com.example.homework18.data.model_dtos.UserDto
import com.example.homework18.domain.model_entities.UserEntity


fun UserDto.toDomain(): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}