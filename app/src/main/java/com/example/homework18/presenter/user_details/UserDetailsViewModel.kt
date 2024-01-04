package com.example.homework18.presenter.user_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework18.domain.user_details_repository.UserDetailsRepository
import com.example.homework18.presenter.user_details.event_state.UserDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(private val userDetailsRepository: UserDetailsRepository): ViewModel() {

    private val _userFlow = MutableStateFlow<UserDetailsState?>(null)
    val userFlow: StateFlow<UserDetailsState?> = _userFlow

    fun loadUserInfo(id:Int){
        viewModelScope.launch {
            userDetailsRepository.getUsersDetails(id).collect{
                _userFlow.value = UserDetailsState(
                    user = it
                )
            }
        }
    }
}