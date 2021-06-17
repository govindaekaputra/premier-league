package com.abc.premierleagueapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abc.premierleagueapp.core.domain.usecase.ClubUseCase

class HomeViewModel(clubUseCase: ClubUseCase) : ViewModel() {
    val clubs = clubUseCase.getAllClubs().asLiveData()
}