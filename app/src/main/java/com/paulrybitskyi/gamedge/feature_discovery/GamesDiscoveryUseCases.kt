package com.paulrybitskyi.gamedge.feature_discovery

import ca.on.hojat.gamenews.shared.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamenews.shared.domain.games.RefreshableGamesUseCase
import com.paulrybitskyi.gamedge.feature_discovery.di.GamesDiscoveryKey.Type
import javax.inject.Inject

class GamesDiscoveryUseCases @Inject constructor(
    private val observeGamesUseCasesMap: Map<Type, @JvmSuppressWildcards ObservableGamesUseCase>,
    private val refreshGamesUseCasesMap: Map<Type, @JvmSuppressWildcards RefreshableGamesUseCase>
) {

    fun getObservableUseCase(keyType: Type): ObservableGamesUseCase {
        return observeGamesUseCasesMap.getValue(keyType)
    }

    fun getRefreshableUseCase(keyType: Type): RefreshableGamesUseCase {
        return refreshGamesUseCasesMap.getValue(keyType)
    }
}
