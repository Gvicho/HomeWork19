package com.example.homework18.domain.usecases

import com.example.homework18.presenter.model_presenter.UserPresenter
import com.example.homework18.presenter.model_presenter.UsersPresenter
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangeUsersFlowUseCase @Inject constructor() {
    operator fun invoke(listOfUsers:List<UserPresenter>, delete: Boolean): Flow<ResultWrapper<UsersPresenter>> {

        var newListOfUsers = listOfUsers

        if(delete){
            newListOfUsers = newListOfUsers.filterNot { it.isSelected }
        }

        return flow {
            emit(ResultWrapper.Success(
                data = UsersPresenter(
                    usersList = newListOfUsers
                )
            ))
        }
    }
}