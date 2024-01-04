package com.example.homework18.presenter.homepage.event_state

import com.example.homework18.presenter.homepage.model.Users
import com.example.test6.data.common.ResultWrapper

data class HomePageState(
    val users:ResultWrapper<Users>
) {
}