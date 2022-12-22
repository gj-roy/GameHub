package ca.on.hojat.gamenews.core.domain.games

import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.core.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.common.RefreshGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.entities.Game

typealias ObservableGamesUseCase = ObservableUseCase<ObserveGamesUseCaseParams, List<Game>>
typealias RefreshableGamesUseCase = ObservableUseCase<RefreshGamesUseCaseParams, DomainResult<List<Game>>>
