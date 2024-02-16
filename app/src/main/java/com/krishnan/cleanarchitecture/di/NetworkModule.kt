package com.krishnan.cleanarchitecture.di

import com.krishnan.cleanarchitecture.data.repository.UserRepoImpl
import com.krishnan.cleanarchitecture.data.source.remote.UserApi
import com.krishnan.cleanarchitecture.data.source.remote.UserApi.Companion.BASE_URL
import com.krishnan.cleanarchitecture.domain.repository.remote.UserRepo
import com.krishnan.cleanarchitecture.domain.usecase.local.UserLocalUseCase
import com.krishnan.cleanarchitecture.domain.usecase.remote.DeleteUserDto
import com.krishnan.cleanarchitecture.domain.usecase.remote.GetUsers
import com.krishnan.cleanarchitecture.domain.usecase.remote.InsertUserDto
import com.krishnan.cleanarchitecture.domain.usecase.remote.UserRemoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): UserApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create()

    @Provides
    @Singleton
    fun provideClient() =
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()

    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi): UserRepo {
        return UserRepoImpl(userApi)
    }

    @Provides
    @Singleton
    fun provideGetUserDto(userRepo: UserRepo, userLocalUseCase: UserLocalUseCase): GetUsers {
        return GetUsers(userRepo, userLocalUseCase)
    }

    @Provides
    @Singleton
    fun provideInsertUserDto(userRepo: UserRepo, getUsers: GetUsers): InsertUserDto {
        return InsertUserDto(userRepo, getUsers)
    }

    @Provides
    @Singleton
    fun provideDeleteUserDto(userRepo: UserRepo, getUsers: GetUsers): DeleteUserDto {
        return DeleteUserDto(userRepo, getUsers)
    }

    @Provides
    @Singleton
    fun providesUserRemoteUseCase(getUsers: GetUsers, insertUserDto: InsertUserDto,deleteUserDto: DeleteUserDto) =
        UserRemoteUseCase(getUsers = getUsers, insertUserDto = insertUserDto, deleteUserDto = deleteUserDto)

}