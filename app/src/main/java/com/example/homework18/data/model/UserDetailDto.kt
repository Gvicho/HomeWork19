package com.example.homework18.data.model

import com.squareup.moshi.Json

data class UserDetailDto(
    @Json(name = "data")
    val data : UserDto,
    @Json(name = "support")
    val support: SupportDto
) {

}

data class SupportDto(
    @Json(name = "url")
    val url:String,
    @Json(name = "text")
    val text:String
)