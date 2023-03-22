package com.example.freetoplaygames.data.remote.dto

import com.example.freetoplaygames.domain.model.GameDetails
import com.google.gson.annotations.SerializedName

data class GameDetailsDto(
    val description: String,
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val id: Int,
    @SerializedName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirements,
    val platform: String,
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val screenshots: List<Screenshot>,
    @SerializedName("short_description")
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
)

fun GameDetailsDto.toGameDetails() : GameDetails{
    return GameDetails(
        description, developer, gameUrl, genre, id, minimumSystemRequirements, platform, publisher, releaseDate, screenshots, status, thumbnail, title
    )
}