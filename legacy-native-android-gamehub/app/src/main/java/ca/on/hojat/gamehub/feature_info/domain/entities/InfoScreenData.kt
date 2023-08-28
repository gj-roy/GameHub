package ca.on.hojat.gamehub.feature_info.domain.entities

import ca.on.hojat.gamehub.core.domain.entities.Game

data class InfoScreenData(
    val game: Game,
    val isGameLiked: Boolean,
    val companyGames: List<Game>,
    val similarGames: List<Game>,
)
