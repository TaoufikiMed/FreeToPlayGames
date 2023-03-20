package com.example.freetoplaygames.domain.model

data class Game(
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val shortDescription: String,
    val thumbnail: String,
    val title: String
)