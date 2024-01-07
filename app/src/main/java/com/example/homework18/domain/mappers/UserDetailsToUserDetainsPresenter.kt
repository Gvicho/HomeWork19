package com.example.homework18.domain.mappers

import com.example.homework18.domain.model_entities.Support
import com.example.homework18.domain.model_entities.UserDetails
import com.example.homework18.presenter.model_presenter.SupportPresenter
import com.example.homework18.presenter.model_presenter.UserDetailsPresenter

fun UserDetails.toPresenter():UserDetailsPresenter{
    return UserDetailsPresenter(
        data = data.toPresenter(),
        support = support.toPresenter()
    )
}

fun Support.toPresenter():SupportPresenter{
    return SupportPresenter(
        url = url,
        text = text
    )
}