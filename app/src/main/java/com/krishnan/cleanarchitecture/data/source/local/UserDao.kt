package com.krishnan.cleanarchitecture.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getUsers(): Flow<List<UserEntity>>

    @Upsert
    suspend fun insertUsers(userEntities: List<UserEntity>)

    @Query("Delete from UserEntity")
    suspend fun deleteUsers()
}