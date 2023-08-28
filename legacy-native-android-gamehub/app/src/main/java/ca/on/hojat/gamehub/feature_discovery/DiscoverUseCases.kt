package ca.on.hojat.gamehub.feature_discovery

import ca.on.hojat.gamehub.core.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamehub.core.domain.games.RefreshableGamesUseCase
import ca.on.hojat.gamehub.feature_discovery.widgets.DiscoverScreen
import ca.on.hojat.gamehub.feature_discovery.di.DiscoverKey.Type
import javax.inject.Inject

/**
 * An amalgamation of all the different use cases that will be used in [DiscoverScreen].
 */
class DiscoverUseCases @Inject constructor(
    private val observeGamesUseCasesMap: Map<Type, @JvmSuppressWildcards ObservableGamesUseCase>,
    private val refreshGamesUseCasesMap: Map<Type, @JvmSuppressWildcards RefreshableGamesUseCase>
) {

    fun getObservableGamesUseCase(keyType: Type): ObservableGamesUseCase {
        return observeGamesUseCasesMap.getValue(keyType)
    }

    fun getRefreshableGamesUseCase(keyType: Type): RefreshableGamesUseCase {
        return refreshGamesUseCasesMap.getValue(keyType)
    }
}
