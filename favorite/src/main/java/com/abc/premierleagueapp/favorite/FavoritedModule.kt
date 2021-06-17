package com.abc.premierleagueapp.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritedModule = module {
    viewModel { FavoritedViewModel(get()) }
}