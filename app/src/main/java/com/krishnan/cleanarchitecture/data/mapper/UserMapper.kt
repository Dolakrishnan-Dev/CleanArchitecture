package com.krishnan.cleanarchitecture.data.mapper

import com.krishnan.cleanarchitecture.data.model.UserDto
import com.krishnan.cleanarchitecture.data.model.UserInsertDto
import com.krishnan.cleanarchitecture.data.source.local.UserEntity
import com.krishnan.cleanarchitecture.domain.model.User


fun UserDto.toUserEntity() = UserEntity(id = 0,_id, name = name, email = email, mobile = mobile, gender = gender)

fun User.toUserInsertDto() = UserInsertDto(name = name, email = email, mobile = mobile, gender = gender)

fun UserEntity.toUser() = User(id = _id,name = name, email = email, mobile = mobile, gender = gender)