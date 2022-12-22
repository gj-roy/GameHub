package ca.on.hojat.gamenews.feature_category

import ca.on.hojat.gamenews.core.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.RefreshableGamesUseCase
import ca.on.hojat.gamenews.feature_category.di.GamesCategoryKey.Type
import javax.inject.Inject
import javax.inject.Provider

internal class GamesCategoryUseCases @Inject constructor(
    private val observeGamesUseCasesMap: Map<Type, @JvmSuppressWildcards Provider<ObservableGamesUseCase>>,
    private val refreshGamesUseCasesMap: Map<Type, @JvmSuppressWildcards Provider<RefreshableGamesUseCase>>
) {

    fun getObservableUseCase(keyType: Type): ObservableGamesUseCase {
        return observeGamesUseCasesMap.getValue(keyType).get()
    }

    fun getRefreshableUseCase(keyType: Type): RefreshableGamesUseCase {
        return refreshGamesUseCasesMap.getValue(keyType).get()
    }
}
