package com.example.homework18.data.mapper

import com.example.homework18.data.model.UserDto
import com.example.homework18.presenter.common.User


fun UserDto.toDomain(): User {
    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )
}