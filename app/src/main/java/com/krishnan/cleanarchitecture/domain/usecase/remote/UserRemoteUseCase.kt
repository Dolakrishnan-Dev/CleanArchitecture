package com.krishnan.cleanarchitecture.domain.usecase.remote


data class UserRemoteUseCase(val getUsers: GetUsers, val insertUserDto: InsertUserDto,val deleteUserDto : DeleteUserDto)
