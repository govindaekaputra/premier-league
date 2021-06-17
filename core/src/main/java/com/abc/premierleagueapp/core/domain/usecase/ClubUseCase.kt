package com.abc.premierleagueapp.core.domain.usecase

import com.abc.premierleagueapp.core.data.Resource
import com.abc.premierleagueapp.core.domain.model.Club
import kotlinx.coroutines.flow.Flow

interface ClubUseCase {
    fun getAllClubs(): Flow<Resource<List<Club>>>
    fun getAllFavoritedClubs(): Flow<List<Club>>
    fun setFavoriteClub(club: Club, state: Boolean)
}