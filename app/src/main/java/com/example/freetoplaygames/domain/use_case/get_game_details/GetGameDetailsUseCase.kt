package com.example.freetoplaygames.domain.use_case.get_game_details

import com.example.freetoplaygames.common.Resource
import com.example.freetoplaygames.domain.model.GameDetails
import com.example.freetoplaygames.domain.repository.GameRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    operator fun invoke(id:Int) = flow{
        try {
            emit(Resource.Loading<GameDetails>())
            val gameDetails=gameRepository.getGameDetails(id)
            emit(Resource.Success<GameDetails>(gameDetails))
        }
        catch (e: HttpException){
            emit(Resource.Error<GameDetails>(e.localizedMessage ?: "An unexepted error occured"))
        }
        catch (e: IOException){
            emit(Resource.Error<GameDetails>("Couldn't reach the server, please check your connexion internet"))
        }
    }
}