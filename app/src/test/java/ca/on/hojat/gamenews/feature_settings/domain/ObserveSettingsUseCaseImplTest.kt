package ca.on.hojat.gamenews.feature_settings.domain

import app.cash.turbine.test
import ca.on.hojat.gamenews.feature_settings.DOMAIN_SETTINGS
import ca.on.hojat.gamenews.shared.domain.common.extensions.execute
import ca.on.hojat.gamenews.shared.testing.domain.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import com.paulrybitskyi.gamedge.feature_settings.domain.datastores.SettingsLocalDataStore
import com.paulrybitskyi.gamedge.feature_settings.domain.usecases.ObserveSettingsUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ObserveSettingsUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var settingsLocalDataStore: SettingsLocalDataStore

    private lateinit var sut: ObserveSettingsUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = ObserveSettingsUseCaseImpl(
            localDataStore = settingsLocalDataStore,
            dispatcherProvider = mainCoroutineRule.dispatcherProvider,
        )
    }

    @Test
    fun `Emits settings from local data store`() {
        runTest {
            every { settingsLocalDataStore.observeSettings() } returns flowOf(DOMAIN_SETTINGS)

            sut.execute().test {
                assertThat(awaitItem()).isEqualTo(DOMAIN_SETTINGS)
                awaitComplete()
            }
        }
    }
}
