package com.abc.premierleagueapp.core.domain.usecase

import com.abc.premierleagueapp.core.domain.model.Club
import com.abc.premierleagueapp.core.domain.repository.IClubRepository

class ClubInteractor(private val clubRepository: IClubRepository): ClubUseCase {
    override fun getAllClubs() =
        clubRepository.getAllClubs()

    override fun getAllFavoritedClubs() =
        clubRepository.getAllFavoritedClubs()

    override fun setFavoriteClub(club: Club, state: Boolean) {
        clubRepository.setFavoriteClub(club, state)
    }
}