package com.krishnan.cleanarchitecture.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val _id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val gender: String,
)