package com.krishnan.cleanarchitecture.data.mapper

import com.krishnan.cleanarchitecture.data.model.UserDto
import com.krishnan.cleanarchitecture.data.source.local.UserEntity
import com.krishnan.cleanarchitecture.domain.model.User


fun UserDto.toUserEntity() = UserEntity(id = 0, name = name, email = email, mobile = mobile, gender = gender)

fun User.toUserDto() = UserDto(name = name, email = email, mobile = mobile, gender = gender)

fun UserEntity.toUser() = User(name = name, email = email, mobile = mobile, gender = gender)