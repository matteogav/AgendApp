package com.matteogav.agendapp.utils

import com.matteogav.agendapp.data.models.UserResponse
import com.matteogav.agendapp.domain.repositories.UsersRepository
import com.matteogav.agendapp.domain.usecases.GetUsersUseCase

class FakeGetUsersUseCase(private val usersRepository: UsersRepository): GetUsersUseCase(usersRepository) {
    override suspend fun getUsers(page: Int, seed: String?): UserResponse {
        return usersRepository.getUsersFromAPI(page, seed)
    }
}