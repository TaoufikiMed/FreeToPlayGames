package com.example.freetoplaygames.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.freetoplaygames.common.Constants
import com.example.freetoplaygames.data.remote.FreeToPlayApi
import com.example.freetoplaygames.data.remote.dto.GameDto
import com.example.freetoplaygames.data.remote.dto.toGame
import com.example.freetoplaygames.domain.model.Game
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GameRemotePagingSource @Inject constructor(
    private val api : FreeToPlayApi,
    ) : PagingSource<Int, Game>() {
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val currentPage = params.key ?: Constants.FIRST_PAGE
        return try {
            val response = api.getGames()
            val endOfPaginationReached = response.isEmpty()
            LoadResult.Page(
                data = response.map { it.toGame() },
                prevKey = if(currentPage==1) null else currentPage-1,
                nextKey = if(endOfPaginationReached) null else currentPage+1
            )
        }
        catch (exp : HttpException){
            LoadResult.Error(Exception("An Http error occured"))
        }
        catch (exp : IOException){
            LoadResult.Error(Exception("Couldn't reach the server, please check your connexion internet "))
        }
        catch (exp : Exception){
            LoadResult.Error(exp)
        }
    }
}