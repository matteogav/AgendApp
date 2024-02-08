package com.matteogav.agendapp.domain.repositories

import com.matteogav.agendapp.data.models.UserResponse

interface UsersRepository {

    suspend fun getUsersFromAPI(page: Int, seed: String? = ""): UserResponse

}