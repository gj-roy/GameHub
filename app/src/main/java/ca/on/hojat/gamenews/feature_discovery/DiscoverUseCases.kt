package ca.on.hojat.gamenews.feature_discovery

import ca.on.hojat.gamenews.core.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.RefreshableGamesUseCase
import ca.on.hojat.gamenews.feature_discovery.widgets.DiscoverScreen
import ca.on.hojat.gamenews.feature_discovery.di.DiscoverKey.Type
import javax.inject.Inject

/**
 * An amalgamation of all the different use cases that will be used in [DiscoverScreen].
 */
class DiscoverUseCases @Inject constructor(
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
