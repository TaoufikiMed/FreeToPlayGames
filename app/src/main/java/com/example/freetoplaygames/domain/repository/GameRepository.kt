package com.example.freetoplaygames.domain.repository

import androidx.paging.PagingData
import com.example.freetoplaygames.domain.model.Game
import com.example.freetoplaygames.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames() : Flow<PagingData<Game>>
    fun getGamesByCategory(category: String) : Flow<PagingData<Game>>
    suspend fun getGameDetails(id:Int) : GameDetails
}