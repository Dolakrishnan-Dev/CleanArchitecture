package com.krishnan.cleanarchitecture.domain.repository.local

import com.krishnan.cleanarchitecture.data.source.local.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalRepository {

    suspend fun getUsers(): Flow<List<UserEntity>>


    suspend fun insertUserEntities(userEntities: List<UserEntity>)

    suspend fun deleteUsers()
}