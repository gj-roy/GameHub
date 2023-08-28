package ca.on.hojat.gamehub.core.domain.games

import ca.on.hojat.gamehub.core.domain.DomainResult
import ca.on.hojat.gamehub.core.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamehub.core.domain.games.common.ObserveUseCaseParams
import ca.on.hojat.gamehub.core.domain.games.common.RefreshUseCaseParams
import ca.on.hojat.gamehub.core.domain.entities.Game

/** These 2 use cases will return lists of [Game]. They are specific to times that you want to handle games. */
typealias ObservableGamesUseCase = ObservableUseCase<ObserveUseCaseParams, List<Game>>
typealias RefreshableGamesUseCase = ObservableUseCase<RefreshUseCaseParams, DomainResult<List<Game>>>
