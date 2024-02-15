package com.krishnan.cleanarchitecture.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend inline fun <T : Any> safeApiCall(crossinline request: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = withContext(Dispatchers.IO) {
            request.invoke()
        }
        if (response.isSuccessful) {
            response.body()?.let {
                ApiResult.Success(it)
            } ?: ApiResult.Error("Server Response is Empty.")
        } else {
            ApiResult.Error("Something went wrong! Please try again.")
        }
    } catch (e: Exception) {
        ApiResult.Error(e.message.toString())
    }
}