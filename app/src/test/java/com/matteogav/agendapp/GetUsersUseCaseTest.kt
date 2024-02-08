package com.matteogav.agendapp

import com.matteogav.agendapp.domain.repositories.UsersRepository
import com.matteogav.agendapp.domain.usecases.GetUsersUseCase
import com.matteogav.agendapp.utils.FakeUserResponse.Companion.createUser
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private lateinit var usersRepository: UsersRepository
    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setup() {
        usersRepository = mockk()
        getUsersUseCase = GetUsersUseCase(usersRepository)
    }

    val userResponse = createUser()

    @Test
    fun `getUsers returns correct response`() = runBlocking {

        // given
        val page = 1
        val seed = "56d27f4a53bd5441"
        val expectedResponse = userResponse

        coEvery { usersRepository.getUsersFromAPI(page, seed) } returns expectedResponse

        // when
        val result = getUsersUseCase.getUsers(page, seed)

        // then
        assertEquals(expectedResponse, result)
    }
}