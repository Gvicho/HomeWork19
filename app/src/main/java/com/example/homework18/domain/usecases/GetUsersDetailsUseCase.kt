package com.example.homework18.domain.usecases

import com.example.homework18.data.common.mapToDomain
import com.example.homework18.domain.mappers.toPresenter
import com.example.homework18.domain.repositorys.user_details_repository.UserDetailsRepository
import com.example.homework18.presenter.model_presenter.UserDetailsPresenter
import com.example.test6.data.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersDetailsUseCase @Inject constructor(
    private val userDetailsRepository: UserDetailsRepository
) {

    suspend operator fun invoke(id:Int): Flow<ResultWrapper<UserDetailsPresenter>> {
        return userDetailsRepository.getUsersDetails(id).mapToDomain {userDetails ->
            userDetails.toPresenter()
        }
    }

}