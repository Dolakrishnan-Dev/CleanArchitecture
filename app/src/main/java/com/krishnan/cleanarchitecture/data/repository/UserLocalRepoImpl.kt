package com.krishnan.cleanarchitecture.data.repository

import com.krishnan.cleanarchitecture.data.source.local.UserDao
import com.krishnan.cleanarchitecture.data.source.local.UserEntity
import com.krishnan.cleanarchitecture.domain.repository.local.UserLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserLocalRepoImpl @Inject constructor(private val userDao: UserDao) : UserLocalRepository {
    override suspend fun getUsers(): Flow<List<UserEntity>> = userDao.getUsers()

    override suspend fun insertUserEntities(userEntities: List<UserEntity>) =
        userDao.insertUsers(userEntities)

    override suspend fun deleteUsers() = userDao.deleteUsers()

}