package com.abc.premierleagueapp.di

import com.abc.premierleagueapp.core.domain.usecase.ClubInteractor
import com.abc.premierleagueapp.core.domain.usecase.ClubUseCase
import com.abc.premierleagueapp.ui.detail.DetailViewModel
import com.abc.premierleagueapp.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ClubUseCase> { ClubInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}