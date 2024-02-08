package com.matteogav.agendapp

import androidx.paging.PagingSource
import com.matteogav.agendapp.data.sources.UsersSource
import com.matteogav.agendapp.domain.models.toDomain
import com.matteogav.agendapp.utils.FakeGetUsersUseCase
import com.matteogav.agendapp.utils.FakeUserResponse.Companion.createUser
import com.matteogav.agendapp.utils.FakeUsersRepositoryError
import com.matteogav.agendapp.utils.FakeUsersRepositorySuccess
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UsersSourceTest {

    private val testScope = TestScope()
    private val testDispatcher = StandardTestDispatcher(testScope.testScheduler)

    private val users = createUser().results

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when users are given then users paging source returns success load result`() =
        testScope.runTest {
            // given
            val getUsersUseCase = FakeGetUsersUseCase(FakeUsersRepositorySuccess())
            val usersSource = UsersSource(
                getUsersUseCase
            )
            val params = PagingSource
                .LoadParams
                .Refresh(
                    key = 1,
                    loadSize = 1,
                    placeholdersEnabled = false
                )

            val expected = PagingSource
                .LoadResult
                .Page(
                    data = users.map { it.toDomain() },
                    prevKey = null,
                    nextKey = 2
                )

            // when
            val actual = usersSource.load(params = params)

            // then
            assertEquals(expected, actual)
        }

    @Test
    fun `when users are given then users paging source returns error load result`() =
        testScope.runTest {
            // given
            val getUsersUseCase = FakeGetUsersUseCase(FakeUsersRepositoryError())
            val usersSource = UsersSource(getUsersUseCase)
            val params = PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )

            val expectedError = RuntimeException("Uh oh, something has gone wrong. Please tweet us @randomapi about the issue. Thank you.")

            // when
            val actual = usersSource.load(params = params)

            // then
            assertTrue(actual is PagingSource.LoadResult.Error)
            assertEquals(expectedError.toString(), (actual as PagingSource.LoadResult.Error).throwable.toString())
        }

}