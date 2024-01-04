package com.example.homework18.data.mapper

import com.example.homework18.data.model.SupportDto
import com.example.homework18.presenter.user_details.model.Support

fun SupportDto.toDomain():Support{
    return Support(
        url = url,
        text = text
    )
}