package com.abc.premierleagueapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "clubs")
data class ClubEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "image")
    var img: String,

    @ColumnInfo(name = "stadium")
    var stadium: String,

    @ColumnInfo(name = "description")
    var desc: String,

    @ColumnInfo(name = "isFavorited")
    var isFavorited: Boolean = false
): Parcelable