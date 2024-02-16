package com.krishnan.cleanarchitecture.data.model

data class UserDto(
    val _id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val gender: String,
)

data class UserInsertDto(val name: String,
                         val email: String,
                         val mobile: String,
                         val gender: String,)
