package com.abc.premierleagueapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ClubResponse(
    @field:SerializedName("idTeam")
    var id: String,
    @field:SerializedName("strTeam")
    var name: String,
    @field:SerializedName("strTeamBadge")
    var img: String,
    @field:SerializedName("strStadium")
    var stadium: String,
    @field:SerializedName("strDescriptionEN")
    var desc: String
)