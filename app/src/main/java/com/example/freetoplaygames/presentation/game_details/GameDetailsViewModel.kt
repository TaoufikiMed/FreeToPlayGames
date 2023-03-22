package com.example.freetoplaygames.presentation.game_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetoplaygames.common.Resource
import com.example.freetoplaygames.domain.model.GameDetails
import com.example.freetoplaygames.domain.use_case.get_game_details.GetGameDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel(){

    private val _state : MutableLiveData<GameDetailsState> = MutableLiveData(GameDetailsState())
    val state:LiveData<GameDetailsState> = _state

    fun getGameDetails(id: Int){
        getGameDetailsUseCase(id).onEach {
            when(it){
                is Resource.Loading ->{
                    _state.value = GameDetailsState(isLoading = true)
                }
                is Resource.Success ->{
                    _state.value = GameDetailsState(gameDetails = it.data)
                }
                is Resource.Error ->{
                    _state.value = GameDetailsState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}