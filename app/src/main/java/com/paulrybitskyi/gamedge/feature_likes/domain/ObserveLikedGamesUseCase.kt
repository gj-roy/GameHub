package com.paulrybitskyi.gamedge.feature_likes.domain

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.shared.domain.games.datastores.LikedGamesLocalDataStore
import ca.on.hojat.gamenews.shared.domain.games.entities.Game
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

internal interface ObserveLikedGamesUseCase : ObservableGamesUseCase

@Singleton
@BindType
internal class ObserveLikedGamesUseCaseImpl @Inject constructor(
    private val likedGamesLocalDataStore: LikedGamesLocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveLikedGamesUseCase {

    override fun execute(params: ObserveGamesUseCaseParams): Flow<List<Game>> {
        return likedGamesLocalDataStore.observeLikedGames(params.pagination)
            .flowOn(dispatcherProvider.main)
    }
}
