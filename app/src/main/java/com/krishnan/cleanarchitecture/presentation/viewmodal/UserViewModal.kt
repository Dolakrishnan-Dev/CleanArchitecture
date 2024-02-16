package com.krishnan.cleanarchitecture.presentation.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishnan.cleanarchitecture.data.mapper.toUser
import com.krishnan.cleanarchitecture.domain.model.User
import com.krishnan.cleanarchitecture.domain.usecase.local.UserLocalUseCase
import com.krishnan.cleanarchitecture.domain.usecase.remote.UserRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModal @Inject constructor(
    private val userRemoteUseCase: UserRemoteUseCase,
    private val userLocalUseCase: UserLocalUseCase,
) : ViewModel() {

    private val _userInfo = MutableStateFlow<List<User>?>(null)
    var userInfo = _userInfo.asStateFlow()
    var showShimmer = MutableStateFlow<Boolean>(true)

    fun init() {
        observerUser()
        fetchUsers()
    }

    fun insertUserInfo(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRemoteUseCase.insertUserDto.invoke(user)
        }
    }

    fun deleteUser(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRemoteUseCase.deleteUserDto.invoke(id)
        }
    }

    private fun observerUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userLocalUseCase.getUserEntities.invoke().collectLatest { userEntities ->
                val usersList = userEntities.map { it.toUser() }
                showShimmer.update { false }
                _userInfo.update {
                    usersList
                }
            }
        }
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userRemoteUseCase.getUsers.invoke()
        }
    }
}