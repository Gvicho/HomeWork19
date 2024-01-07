package com.example.homework18.domain.mappers

import com.example.homework18.domain.model_entities.UsersEntity
import com.example.homework18.presenter.model_presenter.UsersPresenter

fun UsersEntity.toPresenter():UsersPresenter{
    return UsersPresenter(
        usersList = usersList.map {
            it.toPresenter()
        }
    )
}