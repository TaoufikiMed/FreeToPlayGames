package com.example.freetoplaygames.domain.model

import com.example.freetoplaygames.data.remote.dto.MinimumSystemRequirements
import com.example.freetoplaygames.data.remote.dto.Screenshot

data class GameDetails (
    val description: String,
    val developer: String,
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val minimumSystemRequirements: MinimumSystemRequirements,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val screenshots: List<Screenshot>,
    val status: String,
    val thumbnail: String,
    val title: String
)