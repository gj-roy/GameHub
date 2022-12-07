package ca.on.hojat.gamenews.shared.domain.games.common

import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination

data class ObserveGamesUseCaseParams(
    val pagination: Pagination = Pagination()
)
