package ca.on.hojat.gamenews.feature_settings.domain.usecases

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.feature_settings.domain.datastores.SettingsLocalDataStore
import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

internal interface ObserveSettingsUseCase : ObservableUseCase<Unit, Settings>

@Singleton
@BindType
internal class ObserveSettingsUseCaseImpl @Inject constructor(
    private val localDataStore: SettingsLocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveSettingsUseCase {

    override fun execute(params: Unit): Flow<Settings> {
        return localDataStore.observeSettings()
            .flowOn(dispatcherProvider.main)
    }
}
