package com.abc.premierleagueapp.core.util

import com.abc.premierleagueapp.core.data.source.local.entity.ClubEntity
import com.abc.premierleagueapp.core.data.source.remote.response.ClubResponse
import com.abc.premierleagueapp.core.domain.model.Club

object DataMapper {
    fun mapResponsesToEntities(input: List<ClubResponse>): List<ClubEntity> {
        val clubList = ArrayList<ClubEntity>()
        input.map {
            val tourism = ClubEntity(
                it.id,
                it.name,
                it.img,
                it.stadium,
                it.desc
            )
            clubList.add(tourism)
        }
        return clubList
    }
    fun mapEntitiesToDomain(input: List<ClubEntity>): List<Club> =
        input.map {
            Club(
                id = it.id,
                name = it.name,
                img = it.img,
                desc = it.desc,
                stadium = it.stadium,
                isFavorited = it.isFavorited
            )
        }
    fun mapDomainToEntity(input: Club) = ClubEntity(
        id = input.id,
        name = input.name,
        img = input.img,
        desc = input.desc,
        stadium = input.stadium,
        isFavorited = input.isFavorited
    )
}