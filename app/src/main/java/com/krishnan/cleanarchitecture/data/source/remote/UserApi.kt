package com.krishnan.cleanarchitecture.data.source.remote

import com.krishnan.cleanarchitecture.data.model.UserDto
import com.krishnan.cleanarchitecture.data.model.UserInsertDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserApi {

    @GET("krishnan")
    suspend fun getUsers(): Response<List<UserDto>>

    @POST("krishnan")
    suspend fun postUser(@Body userDto: UserInsertDto): Response<Any>

    @DELETE("krishnan/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Any>

    companion object {
        const val BASE_URL = "https://crudcrud.com/api/232eb3ea6aad43d086c1f404b2de73d0/"
    }
}