package ca.on.hojat.gamenews.core.domain.games

import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.core.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.common.RefreshGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.entities.Game

/** These 2 use cases will return lists of [Game]. They are specific to times that you want to handle games. */
typealias ObservableGamesUseCase = ObservableUseCase<ObserveGamesUseCaseParams, List<Game>>
typealias RefreshableGamesUseCase = ObservableUseCase<RefreshGamesUseCaseParams, DomainResult<List<Game>>>
