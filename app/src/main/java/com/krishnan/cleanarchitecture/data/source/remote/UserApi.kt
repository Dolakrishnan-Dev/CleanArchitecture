package com.krishnan.cleanarchitecture.data.source.remote

import com.krishnan.cleanarchitecture.data.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST


interface UserApi {

    @GET("krishnan")
    suspend fun getUsers(): Response<List<UserDto>>

    @POST("krishnan")
    suspend fun postUser(@Body userDto: UserDto): Response<Any>

    @DELETE
    suspend fun deleteUser()

    companion object {
        const val BASE_URL = "https://crudcrud.com/api/9a0c30326ee4449eaa718642618ff797/"
    }
}