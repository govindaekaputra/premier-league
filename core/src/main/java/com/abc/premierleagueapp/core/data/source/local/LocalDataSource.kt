package com.abc.premierleagueapp.core.data.source.local

import com.abc.premierleagueapp.core.data.source.local.entity.ClubEntity
import com.abc.premierleagueapp.core.data.source.local.room.ClubDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val clubDao: ClubDao) {

    fun getAllClubs(): Flow<List<ClubEntity>> = clubDao.getAllClubs()

    fun getAllFavoritedClubs(): Flow<List<ClubEntity>> = clubDao.getAllFavoritedClubs()

    suspend fun insertClubs(clubList: List<ClubEntity>) = clubDao.insertClubs(clubList)

    fun setFavoriteClub(club: ClubEntity, newState: Boolean) {
        club.isFavorited = newState
        clubDao.updateClub(club)
    }
}