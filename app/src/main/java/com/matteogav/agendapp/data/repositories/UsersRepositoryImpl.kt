package com.matteogav.agendapp.data.repositories

import com.matteogav.agendapp.data.models.Info
import com.matteogav.agendapp.data.models.UserResponse
import com.matteogav.agendapp.data.services.APIBaseService
import com.matteogav.agendapp.domain.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class UsersRepositoryImpl: UsersRepository {

    private val userService = APIBaseService.userService

    override suspend fun getUsersFromAPI(page: Int, seed: String?): UserResponse {
        return withContext(Dispatchers.IO) {
            val result = userService.getUsers(page, 20, seed,"es", "login,dob,phone,nat")
            return@withContext result ?: UserResponse(emptyList(), Info("", 0, 1, "1.1"))
        }
    }
}