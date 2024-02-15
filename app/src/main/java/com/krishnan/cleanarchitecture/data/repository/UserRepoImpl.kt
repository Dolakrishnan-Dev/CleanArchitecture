package com.krishnan.cleanarchitecture.data.repository

import com.krishnan.cleanarchitecture.data.model.UserDto
import com.krishnan.cleanarchitecture.data.source.remote.ApiResult
import com.krishnan.cleanarchitecture.data.source.remote.UserApi
import com.krishnan.cleanarchitecture.data.source.remote.safeApiCall
import com.krishnan.cleanarchitecture.domain.repository.remote.UserRepo
import javax.inject.Inject


class UserRepoImpl @Inject constructor(private val userApi: UserApi) : UserRepo {

    override suspend fun getUsers(): ApiResult<List<UserDto>> = safeApiCall {
        userApi.getUsers()
    }

    override suspend fun addUser(userDto: UserDto): ApiResult<Any> = safeApiCall {
        userApi.postUser(userDto)
    }
}