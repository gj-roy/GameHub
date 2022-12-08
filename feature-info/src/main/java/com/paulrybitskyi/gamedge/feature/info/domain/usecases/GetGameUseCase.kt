package com.paulrybitskyi.gamedge.feature.info.domain.usecases

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.entities.Error
import ca.on.hojat.gamenews.shared.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.shared.domain.games.datastores.GamesLocalDataStore
import ca.on.hojat.gamenews.shared.domain.games.entities.Game
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.paulrybitskyi.gamedge.feature.info.domain.usecases.GetGameUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject
import javax.inject.Singleton

internal interface GetGameUseCase : UseCase<Params, Flow<DomainResult<Game>>> {

    data class Params(val gameId: Int)
}

@Singleton
@BindType
internal class GetGameUseCaseImpl @Inject constructor(
    private val gamesLocalDataStore: GamesLocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
) : GetGameUseCase {

    override suspend fun execute(params: Params): Flow<DomainResult<Game>> {
        return flow { gamesLocalDataStore.getGame(params.gameId)?.let { emit(it) } }
            .map<Game, DomainResult<Game>>(::Ok)
            .onEmpty { emit(Err(Error.NotFound("Could not find the game with ID = ${params.gameId}"))) }
            .flowOn(dispatcherProvider.main)
    }
}
