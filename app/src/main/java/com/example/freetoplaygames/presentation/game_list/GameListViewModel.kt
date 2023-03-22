package com.example.freetoplaygames.presentation.game_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.freetoplaygames.domain.model.Game
import com.example.freetoplaygames.domain.use_case.get_games.GetGamesUseCase
import com.example.freetoplaygames.domain.use_case.get_games_by_category.GetGamesByCatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val getGamesByCatUseCase: GetGamesByCatUseCase
) : ViewModel() {
    private val _state : MutableStateFlow<PagingData<Game>> = MutableStateFlow(PagingData.empty())
    val state : StateFlow<PagingData<Game>> = _state

    init {
        getGames()
    }

    fun getGames(){
        viewModelScope.launch {
            getGamesUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect{
                    _state.emit(it)
                }
        }
    }
    fun getGamesByCategory(category: String){
        viewModelScope.launch {
            getGamesByCatUseCase(category).distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect{
                    _state.emit(it)
                }
        }
    }
}