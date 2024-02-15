package com.krishnan.cleanarchitecture.domain.usecase.local

import com.krishnan.cleanarchitecture.data.source.local.UserDao
import com.krishnan.cleanarchitecture.data.source.local.UserEntity


class InsertUserEntities(private val userDao: UserDao) {

    suspend operator fun invoke(userEntities: List<UserEntity>) = userDao.insertUsers(userEntities)


}