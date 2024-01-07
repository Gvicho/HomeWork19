package com.example.homework18.presenter.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework18.domain.usecases.ChangeUsersFlowUseCase
import com.example.homework18.domain.usecases.GetUsersListUseCase
import com.example.homework18.presenter.homepage.event_state.HomePageState
import com.example.homework18.presenter.homepage.event_state.NavigationEvent
import com.example.homework18.presenter.model_presenter.UserPresenter
import com.example.test6.data.common.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val changeUsersFlowUseCase: ChangeUsersFlowUseCase
):ViewModel() {

    //HomePage state
    private var _usersStateFlow = MutableStateFlow<HomePageState?>(null)
    val usersStateFlow: StateFlow<HomePageState?> = _usersStateFlow

    //Navigation Events
    private val _navigationEventFlow = MutableSharedFlow<NavigationEvent>()
    val navigationEventFlow: SharedFlow<NavigationEvent> get() = _navigationEventFlow.asSharedFlow()

    var usersLastSuccessLoadedList = emptyList<UserPresenter>()

    init {
        loadUsers()
    }

    fun loadUsers(){
        viewModelScope.launch {
            getUsersListUseCase().collect{
                _usersStateFlow.value = HomePageState(
                    users = it
                )
                if(it is ResultWrapper.Success){
                    usersLastSuccessLoadedList = it.data!!.usersList
                }
            }
        }
    }

    fun navigateToDetailsPage(userId: Int) {
        viewModelScope.launch {
            _navigationEventFlow.emit(NavigationEvent.NavigateToDetailsPage(userId))
        }
    }

    fun changeUserInDeletedList(id:Int,isSelected:Boolean){
        var atLeastOneSelected = false
        usersLastSuccessLoadedList.map {
            if(it.id == id){
                it.isSelected = isSelected
            }
            if(it.isSelected)atLeastOneSelected = true
        }
        viewModelScope.launch {
            changeUsersFlowUseCase(usersLastSuccessLoadedList,false).collect{
                _usersStateFlow.value = HomePageState(users = it,deleteUsersButtonIsVisible =atLeastOneSelected )
            }
        }
    }

    fun deleteAllSelected(){
        viewModelScope.launch {
            changeUsersFlowUseCase(usersLastSuccessLoadedList,true).collect{
                _usersStateFlow.value = HomePageState(users = it)
                usersLastSuccessLoadedList = it.data!!.usersList
            }
        }
    }

}