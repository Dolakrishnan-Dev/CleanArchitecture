package com.krishnan.cleanarchitecture.domain.usecase.local

import com.krishnan.cleanarchitecture.data.source.local.UserDao
import com.krishnan.cleanarchitecture.data.source.local.UserEntity
import kotlinx.coroutines.flow.Flow

class GetUserEntities(private val userDao: UserDao) {

    suspend operator fun invoke(): Flow<List<UserEntity>> {
        return userDao.getUsers()
    }

}