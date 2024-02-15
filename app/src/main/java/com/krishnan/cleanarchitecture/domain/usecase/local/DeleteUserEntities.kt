package com.krishnan.cleanarchitecture.domain.usecase.local

import com.krishnan.cleanarchitecture.data.source.local.UserDao


class DeleteUserEntities(private val userDao: UserDao) {

    suspend operator fun invoke() {
        return userDao.deleteUsers()
    }

}