package ca.on.hojat.gamenews.core.domain.games.common

import ca.on.hojat.gamenews.core.domain.entities.Pagination

data class ObserveUseCaseParams(
    val pagination: Pagination = Pagination()
)
