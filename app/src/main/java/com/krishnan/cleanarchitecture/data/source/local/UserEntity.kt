package com.krishnan.cleanarchitecture.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val mobile: String,
    val gender: String,
)