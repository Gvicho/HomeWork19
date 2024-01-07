package com.example.homework18.presenter.homepage.event_state

import com.example.homework18.presenter.model_presenter.UsersPresenter
import com.example.test6.data.common.ResultWrapper

data class HomePageState(
    val users:ResultWrapper<UsersPresenter>,
    val deleteUsersButtonIsVisible :Boolean = false
) {
}