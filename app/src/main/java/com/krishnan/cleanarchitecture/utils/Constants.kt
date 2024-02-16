package com.krishnan.cleanarchitecture.utils


object Constants {
    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")
        return emailRegex.matches(email)
    }
}