package com.example.homework18.domain.usecases

import com.example.homework18.domain.common.mapToPresenter
import com.example.homework18.domain.mappers.toPresenter
import com.example.homework18.domain.repositorys.user_list_repository.UsersListRepository
import com.example.homework18.presenter.model_presenter.UsersPresenter
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(
    private val usersListRepository: UsersListRepository
){

    suspend operator fun invoke(): Flow<ResultWrapper<UsersPresenter>> {
        return usersListRepository.getUsersList().mapToPresenter {users->
            users.toPresenter()
        }
    }

}
