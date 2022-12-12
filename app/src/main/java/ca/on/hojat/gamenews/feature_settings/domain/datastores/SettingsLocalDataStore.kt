package ca.on.hojat.gamenews.feature_settings.domain.datastores

import ca.on.hojat.gamenews.feature_settings.domain.entities.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataStore {
    suspend fun saveSettings(settings: Settings)
    fun observeSettings(): Flow<Settings>
}
