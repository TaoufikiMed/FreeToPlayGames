package com.example.freetoplaygames.domain.use_case.get_games

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.freetoplaygames.common.Resource
import com.example.freetoplaygames.data.remote.dto.toGame
import com.example.freetoplaygames.domain.model.Game
import com.example.freetoplaygames.domain.repository.GameRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke() = gameRepository.getGames()
}