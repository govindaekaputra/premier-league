package com.abc.premierleagueapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Club(
    var id: String,
    var name: String,
    var img: String,
    var stadium: String,
    var desc: String,
    var isFavorited: Boolean
): Parcelable
