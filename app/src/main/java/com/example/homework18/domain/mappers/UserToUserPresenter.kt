package com.example.homework18.domain.mappers

import com.example.homework18.domain.model_entities.UserEntity
import com.example.homework18.presenter.model_presenter.UserPresenter

fun UserEntity.toPresenter(): UserPresenter {
    return UserPresenter(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar,
        isSelected = false
    )
}