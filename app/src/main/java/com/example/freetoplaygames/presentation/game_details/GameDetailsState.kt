package com.example.freetoplaygames.presentation.game_details

import com.example.freetoplaygames.domain.model.GameDetails

data class GameDetailsState(
    val isLoading:Boolean=false,
    val gameDetails: GameDetails?=null,
    val error: String=""
)