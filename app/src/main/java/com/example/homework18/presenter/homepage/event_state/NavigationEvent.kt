package com.example.homework18.presenter.homepage.event_state



sealed class NavigationEvent() {
    data class NavigateToDetailsPage(val userId: Int) : NavigationEvent()
}