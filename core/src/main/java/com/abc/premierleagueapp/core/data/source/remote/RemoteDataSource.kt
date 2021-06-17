package com.abc.premierleagueapp.core.data.source.remote

import android.util.Log
import com.abc.premierleagueapp.core.data.source.remote.network.ApiResponse
import com.abc.premierleagueapp.core.data.source.remote.network.ApiService
import com.abc.premierleagueapp.core.data.source.remote.response.ClubResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllClubs(): Flow<ApiResponse<List<ClubResponse>>> {
        return flow {
            try {
                val response = apiService.getAllClubs()
                val dataArray = response.teams
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.teams))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}