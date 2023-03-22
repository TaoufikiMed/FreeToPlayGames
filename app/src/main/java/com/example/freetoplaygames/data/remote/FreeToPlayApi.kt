package com.example.freetoplaygames.data.remote

import com.example.freetoplaygames.data.remote.dto.GameDetailsDto
import com.example.freetoplaygames.data.remote.dto.GameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeToPlayApi {
    @GET("games")
    suspend fun getGames() : List<GameDto>

    @GET("games")
    suspend fun getGamesByCategory(@Query("category") category: String) : List<GameDto>

    @GET("game")
    suspend fun getGameDetails(@Query("id") id:Int) : GameDetailsDto
}