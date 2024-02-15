package com.krishnan.cleanarchitecture.domain.usecase.remote

import com.krishnan.cleanarchitecture.data.mapper.toUserEntity
import com.krishnan.cleanarchitecture.data.source.remote.ApiResult
import com.krishnan.cleanarchitecture.domain.repository.remote.UserRepo
import com.krishnan.cleanarchitecture.domain.usecase.local.UserLocalUseCase


class GetUsers(private val userRepo: UserRepo, private val userLocalUseCase: UserLocalUseCase) {

    suspend operator fun invoke() {
        when (userRepo.getUsers()) {
            is ApiResult.Success -> {
                val usersList = (userRepo.getUsers() as ApiResult.Success).data
                userLocalUseCase.deleteUserEntities.invoke()
                val userEntities = usersList.map { it.toUserEntity() }
                userLocalUseCase.insertUserEntities.invoke(userEntities)
            }

            is ApiResult.Error -> {
                //TODO Error Case
            }
        }
    }
}