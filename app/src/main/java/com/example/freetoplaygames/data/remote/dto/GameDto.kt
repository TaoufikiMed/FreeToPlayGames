package com.example.freetoplaygames.data.remote.dto

import com.example.freetoplaygames.domain.model.Game
import com.google.gson.annotations.SerializedName

data class GameDto(
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    @SerializedName("releaseDate")
    val release_date: String,
    @SerializedName("short_description")
    val shortDescription: String,
    val thumbnail: String,
    val title: String
)

fun GameDto.toGame() : Game {
    return Game(
        gameUrl=gameUrl,
        genre=genre,
        id=id,
        platform=platform,
        shortDescription=shortDescription,
        thumbnail=thumbnail,
        title=title
    )
}