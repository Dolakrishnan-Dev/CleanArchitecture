package com.krishnan.cleanarchitecture.domain.repository.remote

import com.krishnan.cleanarchitecture.data.model.UserDto
import com.krishnan.cleanarchitecture.data.model.UserInsertDto
import com.krishnan.cleanarchitecture.data.source.remote.ApiResult


interface UserRepo {

    suspend fun getUsers(): ApiResult<List<UserDto>>

    suspend fun addUser(userDto: UserInsertDto): ApiResult<Any>

    suspend fun deleteUser(userId: String): ApiResult<Any>

}