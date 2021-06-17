package com.abc.premierleagueapp.ui.detail

import androidx.lifecycle.ViewModel
import com.abc.premierleagueapp.core.domain.model.Club
import com.abc.premierleagueapp.core.domain.usecase.ClubUseCase

class DetailViewModel(private val clubUseCase: ClubUseCase): ViewModel() {
    fun setFavoriteClub(club: Club, newState: Boolean) = clubUseCase.setFavoriteClub(club,newState)
}