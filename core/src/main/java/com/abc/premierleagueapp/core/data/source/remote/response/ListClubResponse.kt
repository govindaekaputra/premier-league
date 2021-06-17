package com.abc.premierleagueapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListClubResponse(
    @field:SerializedName("teams")
    var teams: List<ClubResponse>
)
