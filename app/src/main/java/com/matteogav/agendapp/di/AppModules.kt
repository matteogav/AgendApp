package com.matteogav.agendapp.di

import com.matteogav.agendapp.data.repositories.UsersRepositoryImpl
import com.matteogav.agendapp.domain.repositories.UsersRepository
import com.matteogav.agendapp.domain.usecases.GetUsersUseCase
import com.matteogav.agendapp.ui.viewmodels.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        UsersViewModel(get())
    }
}

val useCaseModule = module {
    single {
        GetUsersUseCase(get())
    }
}

val repositoryModule = module {
    single<UsersRepository> {
        UsersRepositoryImpl()
    }
}