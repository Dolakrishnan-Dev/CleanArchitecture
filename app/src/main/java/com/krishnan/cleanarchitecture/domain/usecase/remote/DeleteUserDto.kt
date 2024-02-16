package com.krishnan.cleanarchitecture.domain.usecase.remote

import com.krishnan.cleanarchitecture.domain.repository.remote.UserRepo

class DeleteUserDto(private val userRepo: UserRepo, private val getUsers: GetUsers) {
    suspend operator fun invoke(userId: String) {
        userRepo.deleteUser(userId)
        getUsers.invoke()
    }
}