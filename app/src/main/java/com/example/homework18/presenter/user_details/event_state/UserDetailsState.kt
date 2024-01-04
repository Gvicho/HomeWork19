package com.example.homework18.presenter.user_details.event_state

import com.example.homework18.presenter.user_details.model.UserDetails
import com.example.test6.data.common.ResultWrapper

data class UserDetailsState(
    val user:ResultWrapper<UserDetails>
) {
}