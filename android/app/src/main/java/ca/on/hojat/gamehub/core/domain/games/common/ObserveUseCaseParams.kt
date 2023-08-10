package ca.on.hojat.gamehub.core.domain.games.common

import ca.on.hojat.gamehub.core.domain.entities.Pagination

data class ObserveUseCaseParams(
    val pagination: Pagination = Pagination()
)
