package com.krishnan.cleanarchitecture.domain.usecase.remote

import com.krishnan.cleanarchitecture.data.mapper.toUserDto
import com.krishnan.cleanarchitecture.data.model.UserDto
import com.krishnan.cleanarchitecture.data.source.remote.ApiResult
import com.krishnan.cleanarchitecture.domain.model.User
import com.krishnan.cleanarchitecture.domain.repository.remote.UserRepo


class InsertUserDto(private val userRepo: UserRepo, private val getUsers: GetUsers) {
    suspend operator fun invoke(user: User) {
        val userDto = user.toUserDto()
        val result = userRepo.addUser(userDto)
        if (result is ApiResult.Success) {
            getUsers.invoke()
        } else {
            //TODO Error Case
        }
    }
}