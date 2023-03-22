package com.example.freetoplaygames.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.freetoplaygames.common.Constants
import com.example.freetoplaygames.data.remote.FreeToPlayApi
import com.example.freetoplaygames.data.remote.dto.GameDto
import com.example.freetoplaygames.data.remote.dto.toGame
import com.example.freetoplaygames.data.remote.dto.toGameDetails
import com.example.freetoplaygames.domain.model.Game
import com.example.freetoplaygames.domain.model.GameDetails
import com.example.freetoplaygames.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api : FreeToPlayApi
) : GameRepository
{
    override fun getGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize= Constants.PAGE_SIZE),
            pagingSourceFactory = {GameRemotePagingSource(api = api)}
        ).flow
    }

    override fun getGamesByCategory(category: String): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(pageSize= Constants.PAGE_SIZE),
            pagingSourceFactory = {GameByCatRemotePagingSource(api = api, category = category)}
        ).flow
    }

    override suspend fun getGameDetails(id: Int): GameDetails {
        return api.getGameDetails(id).toGameDetails()
    }

}