package com.example.homework18.presenter.model_presenter

data class UserPresenter(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    var isSelected:Boolean
)