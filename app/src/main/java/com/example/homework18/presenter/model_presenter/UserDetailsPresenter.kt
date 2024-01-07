package com.example.homework18.presenter.model_presenter

data class UserDetailsPresenter(
    val data: UserPresenter,
    val support: SupportPresenter
)

data class SupportPresenter(
    val url:String,
    val text:String
)