package com.paulrybitskyi.gamedge.feature_info.domain.entities

import ca.on.hojat.gamenews.shared.domain.games.entities.Game

data class GameInfo(
    val game: Game,
    val isGameLiked: Boolean,
    val companyGames: List<Game>,
    val similarGames: List<Game>,
)
