package com.abc.premierleagueapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abc.premierleagueapp.core.domain.usecase.ClubUseCase

class FavoritedViewModel(clubUseCase: ClubUseCase) : ViewModel() {
    val favoritedClubs = clubUseCase.getAllFavoritedClubs().asLiveData()
}