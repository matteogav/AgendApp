package com.matteogav.agendapp.utils

import com.matteogav.agendapp.data.models.UserResponse
import com.matteogav.agendapp.data.repositories.UsersRepositoryImpl
import com.matteogav.agendapp.utils.FakeUserResponse.Companion.createUser

class FakeUsersRepositorySuccess : UsersRepositoryImpl() {
    override suspend fun getUsersFromAPI(page: Int, seed: String?): UserResponse {
        return createUser()
    }
}

class FakeUsersRepositoryError : UsersRepositoryImpl() {
    override suspend fun getUsersFromAPI(page: Int, seed: String?): UserResponse =
        throw RuntimeException("Uh oh, something has gone wrong. Please tweet us @randomapi about the issue. Thank you.")
}