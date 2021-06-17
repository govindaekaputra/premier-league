package com.abc.premierleagueapp.core.data.source.local.room

import androidx.room.*
import com.abc.premierleagueapp.core.data.source.local.entity.ClubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {
    @Query("SELECT * FROM clubs")
    fun getAllClubs(): Flow<List<ClubEntity>>

    @Query("SELECT * FROM clubs WHERE isFavorited = 1")
    fun getAllFavoritedClubs(): Flow<List<ClubEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClubs(clubs: List<ClubEntity>)

    @Update
    fun updateClub(club: ClubEntity)
}