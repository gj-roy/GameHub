package ca.on.hojat.gamenews.feature_settings.domain.usecases

import ca.on.hojat.gamenews.core.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.feature_settings.domain.datastores.SettingsLocalDataSource
import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject
import javax.inject.Singleton

internal interface SaveSettingsUseCase : UseCase<Settings, Unit>

@Singleton
@BindType
internal class SaveSettingsUseCaseImpl @Inject constructor(
    private val localDataStore: SettingsLocalDataSource,
) : SaveSettingsUseCase {

    override suspend fun execute(params: Settings) {
        localDataStore.saveSettings(params)
    }
}
