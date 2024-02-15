package com.krishnan.cleanarchitecture.di

import android.app.Application
import androidx.room.Room
import com.krishnan.cleanarchitecture.data.source.local.UserDao
import com.krishnan.cleanarchitecture.data.source.local.UserDatabase
import com.krishnan.cleanarchitecture.domain.usecase.local.DeleteUserEntities
import com.krishnan.cleanarchitecture.domain.usecase.local.GetUserEntities
import com.krishnan.cleanarchitecture.domain.usecase.local.InsertUserEntities
import com.krishnan.cleanarchitecture.domain.usecase.local.UserLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao
    }

    @Provides
    @Singleton
    fun providesDeleteUserEntities(userDao: UserDao) = DeleteUserEntities(userDao)


    @Provides
    @Singleton
    fun providesGetUserEntities(userDao: UserDao) = GetUserEntities(userDao)


    @Provides
    @Singleton
    fun providesInsertUserEntities(userDao: UserDao) = InsertUserEntities(userDao)

    @Provides
    @Singleton
    fun providesUserLocalUseCase(
        getUserEntities: GetUserEntities,
        deleteUserEntities: DeleteUserEntities,
        insertUserEntities: InsertUserEntities,
    ) = UserLocalUseCase(getUserEntities, deleteUserEntities, insertUserEntities)


}