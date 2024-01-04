package com.example.homework18.presenter.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework18.domain.user_list_repository.UsersListRepository
import com.example.homework18.presenter.homepage.event_state.HomePageState
import com.example.homework18.presenter.homepage.event_state.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val usersListRepository: UsersListRepository):ViewModel() {

    private val _usersStateFlow = MutableStateFlow<HomePageState?>(null)
    val usersStateFlow: StateFlow<HomePageState?> = _usersStateFlow

    private val _navigationEventFlow = MutableSharedFlow<NavigationEvent>()
    val navigationEventFlow: SharedFlow<NavigationEvent> get() = _navigationEventFlow.asSharedFlow()

    fun LoadUsers(){
        viewModelScope.launch {
            usersListRepository.getUsersList().collect{
                _usersStateFlow.value = HomePageState(
                    users = it
                )
            }
        }
    }

    fun navigateToDetailsPage(userId: Int) {
        viewModelScope.launch {
            _navigationEventFlow.emit(NavigationEvent.NavigateToDetailsPage(userId))
        }
    }
}