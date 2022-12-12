package ca.on.hojat.gamenews.feature_settings.domain

import ca.on.hojat.gamenews.feature_settings.DOMAIN_SETTINGS
import ca.on.hojat.gamenews.feature_settings.domain.datastores.SettingsLocalDataStore
import ca.on.hojat.gamenews.feature_settings.domain.usecases.SaveSettingsUseCaseImpl
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SaveSettingsUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var settingsLocalDataStore: SettingsLocalDataStore
    private lateinit var sut: SaveSettingsUseCaseImpl

    @Before
    fun setup() {
        settingsLocalDataStore = FakeSettingsLocalDataStore()
        sut = SaveSettingsUseCaseImpl(
            localDataStore = settingsLocalDataStore,
        )
    }

    @Test
    fun `Saves settings into local data store`() {
        runTest {
            val settings = DOMAIN_SETTINGS

            sut.execute(settings)

            assertThat(settingsLocalDataStore.observeSettings().first()).isEqualTo(settings)
        }
    }

    private class FakeSettingsLocalDataStore : SettingsLocalDataStore {

        private var settings: DomainSettings? = null

        override suspend fun saveSettings(settings: DomainSettings) {
            this.settings = settings
        }

        override fun observeSettings(): Flow<DomainSettings> {
            return if (settings == null) emptyFlow() else flowOf(checkNotNull(settings))
        }
    }
}
