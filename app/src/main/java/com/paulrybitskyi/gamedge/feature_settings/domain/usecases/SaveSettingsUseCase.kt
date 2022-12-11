package com.paulrybitskyi.gamedge.feature_settings.domain.usecases

import ca.on.hojat.gamenews.shared.domain.common.usecases.UseCase
import com.paulrybitskyi.gamedge.feature_settings.domain.datastores.SettingsLocalDataStore
import com.paulrybitskyi.gamedge.feature_settings.domain.entities.Settings
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject
import javax.inject.Singleton

internal interface SaveSettingsUseCase : UseCase<Settings, Unit>

@Singleton
@BindType
internal class SaveSettingsUseCaseImpl @Inject constructor(
    private val localDataStore: SettingsLocalDataStore,
) : SaveSettingsUseCase {

    override suspend fun execute(params: Settings) {
        localDataStore.saveSettings(params)
    }
}
