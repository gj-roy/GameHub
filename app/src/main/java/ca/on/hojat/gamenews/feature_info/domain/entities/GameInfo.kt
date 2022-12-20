package ca.on.hojat.gamenews.feature_info.domain.entities

import ca.on.hojat.gamenews.core.domain.entities.Game

data class GameInfo(
    val game: Game,
    val isGameLiked: Boolean,
    val companyGames: List<Game>,
    val similarGames: List<Game>,
)
