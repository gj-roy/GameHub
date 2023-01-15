package ca.on.hojat.gamenews.core.domain.games.common

import ca.on.hojat.gamenews.core.domain.entities.Pagination

data class RefreshGamesUseCaseParams(
    val pagination: Pagination = Pagination()
)
