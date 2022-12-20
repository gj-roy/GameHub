package ca.on.hojat.gamenews.shared.domain.games

import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.shared.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.shared.domain.games.common.RefreshGamesUseCaseParams
import ca.on.hojat.gamenews.shared.domain.games.entities.Game

typealias ObservableGamesUseCase = ObservableUseCase<ObserveGamesUseCaseParams, List<Game>>
typealias RefreshableGamesUseCase = ObservableUseCase<RefreshGamesUseCaseParams, DomainResult<List<Game>>>
