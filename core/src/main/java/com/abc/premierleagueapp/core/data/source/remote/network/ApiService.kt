package com.abc.premierleagueapp.core.data.source.remote.network

import com.abc.premierleagueapp.core.data.source.remote.response.ListClubResponse
import retrofit2.http.GET

interface ApiService {
    @GET("search_all_teams.php?l=English%20Premier%20League")
    suspend fun getAllClubs(): ListClubResponse
}