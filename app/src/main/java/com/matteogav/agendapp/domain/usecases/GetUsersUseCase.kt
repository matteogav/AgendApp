package com.matteogav.agendapp.domain.usecases

import com.matteogav.agendapp.data.models.UserResponse
import com.matteogav.agendapp.domain.repositories.UsersRepository

open class GetUsersUseCase constructor(private val userRepository: UsersRepository) {

    open suspend fun getUsers(page: Int, seed: String?): UserResponse {
        return userRepository.getUsersFromAPI(page, seed)
    }
}