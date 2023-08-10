package ca.on.hojat.gamehub.core.domain.games.common

import ca.on.hojat.gamehub.core.domain.entities.Pagination

data class RefreshUseCaseParams(
    val pagination: Pagination = Pagination()
)
