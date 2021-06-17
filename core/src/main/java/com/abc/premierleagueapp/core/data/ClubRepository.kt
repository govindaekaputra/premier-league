package com.abc.premierleagueapp.core.data

import com.abc.premierleagueapp.core.data.source.local.LocalDataSource
import com.abc.premierleagueapp.core.data.source.remote.RemoteDataSource
import com.abc.premierleagueapp.core.data.source.remote.network.ApiResponse
import com.abc.premierleagueapp.core.data.source.remote.response.ClubResponse
import com.abc.premierleagueapp.core.domain.model.Club
import com.abc.premierleagueapp.core.domain.repository.IClubRepository
import com.abc.premierleagueapp.core.util.AppExecutors
import com.abc.premierleagueapp.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IClubRepository {

    override fun getAllClubs(): Flow<Resource<List<Club>>> =
        object : NetworkBoundResource<List<Club>, List<ClubResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Club>> {
                return localDataSource.getAllClubs().map{
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Club>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ClubResponse>>> =
                remoteDataSource.getAllClubs()

            override suspend fun saveCallResult(data: List<ClubResponse>) {
                val clubList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertClubs(clubList)
            }
        }.asFlow()

    override fun getAllFavoritedClubs(): Flow<List<Club>> {
        return localDataSource.getAllFavoritedClubs().map{
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteClub(club: Club, state: Boolean) {
        val clubEntity = DataMapper.mapDomainToEntity(club)
        appExecutors.diskIO().execute { localDataSource.setFavoriteClub(clubEntity, state) }
    }
}
