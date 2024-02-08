package com.matteogav.agendapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.matteogav.agendapp.data.sources.UsersSource
import com.matteogav.agendapp.domain.models.User
import com.matteogav.agendapp.domain.usecases.GetUsersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class UsersViewModel constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val allUsers = Pager(config = PagingConfig(1),
        pagingSourceFactory = { UsersSource(getUsersUseCase) }
    ).flow.cachedIn(viewModelScope)

    private val currentQuery = MutableStateFlow("")

    val users: Flow<PagingData<User>> =
        currentQuery.flatMapLatest { query ->
            allUsers.map { pagingData ->
                pagingData.filter {
                    it.name?.startsWith(query, ignoreCase = true) ?: false || it.email?.startsWith(query, ignoreCase = true) ?: false
                }
            }
        }
    fun searchUsers(query: String) {
        currentQuery.value = query
    }
}