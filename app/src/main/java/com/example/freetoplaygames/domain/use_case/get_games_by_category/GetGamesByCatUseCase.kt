package com.example.freetoplaygames.domain.use_case.get_games_by_category

import com.example.freetoplaygames.common.Resource
import com.example.freetoplaygames.data.remote.dto.toGame
import com.example.freetoplaygames.domain.model.Game
import com.example.freetoplaygames.domain.repository.GameRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGamesByCatUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(category: String) = gameRepository.getGamesByCategory(category)
}