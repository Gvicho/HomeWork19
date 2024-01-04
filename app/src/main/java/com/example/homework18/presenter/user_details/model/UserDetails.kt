package com.example.homework18.presenter.user_details.model

import com.example.homework18.presenter.common.User

data class UserDetails(
    val data: User,
    val support: Support
)

data class Support(
    val url:String,
    val text:String
)