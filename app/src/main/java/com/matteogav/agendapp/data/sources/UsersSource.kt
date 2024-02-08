package com.matteogav.agendapp.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.matteogav.agendapp.data.models.UserModel
import com.matteogav.agendapp.domain.models.User
import com.matteogav.agendapp.domain.models.toDomain
import com.matteogav.agendapp.domain.usecases.GetUsersUseCase

class UsersSource (private val getUsersUseCase: GetUsersUseCase)
    : PagingSource<Int, User>() {

    private var seed: String? = null
    private val loadedItems = mutableSetOf<UserModel>()

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val key = params.key ?: 1
            val result = getUsersUseCase.getUsers(key, seed)

            val data = result.results.filterNot { loadedItems.contains(it) }
            loadedItems.addAll(data)

            val prevKey = if (key == 1) null else key - 1
            val nextKey = if (result.results.isEmpty()) null else key + 1
            if(seed != "") seed = result.info.seed

            LoadResult.Page(
                data = data.map { it.toDomain() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (error: Throwable) {
            LoadResult.Error(error)
        }
    }
}