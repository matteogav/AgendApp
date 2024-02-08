package com.matteogav.agendapp.data.services

import com.matteogav.agendapp.data.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api")
    suspend fun getUsers(@Query("page") page: Int,
                         @Query("results") results: Int,
                         @Query("seed") seed: String? = "",
                         @Query("nat") nat: String,
                         @Query("exc") exc: String): UserResponse?
}