package ca.on.hojat.gamehub.feature_settings.data

import androidx.datastore.core.DataStore
import ca.on.hojat.gamehub.feature_settings.DOMAIN_SETTINGS
import ca.on.hojat.gamehub.feature_settings.data.datastores.ProtoSettings
import com.google.common.truth.Truth.assertThat
import ca.on.hojat.gamehub.feature_settings.data.datastores.ProtoSettingsMapper
import ca.on.hojat.gamehub.feature_settings.data.datastores.SettingsFileDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

private val PROTO_SETTINGS = ProtoSettings.newBuilder()
    .setThemeName(DOMAIN_SETTINGS.theme.name)
    .setLanguageName(DOMAIN_SETTINGS.language.name)
    .build()

internal class SettingsFileDataSourceTest {

    @MockK
    private lateinit var protoDataStore: DataStore<ProtoSettings>

    private lateinit var sut: SettingsFileDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = SettingsFileDataSource(
            protoDataSource = protoDataStore,
            protoMapper = ProtoSettingsMapper(),
        )
    }

    @Test
    fun `Saves settings successfully`() {
        runTest {
            coEvery { protoDataStore.updateData(any()) } returns PROTO_SETTINGS

            sut.saveSettings(DOMAIN_SETTINGS)

            coVerify { protoDataStore.updateData(any()) }
        }
    }

    @Test
    fun `Observes settings successfully`() {
        runTest {
            coEvery { protoDataStore.data } returns flowOf(PROTO_SETTINGS)

            assertThat(sut.observeSettings().first()).isEqualTo(DOMAIN_SETTINGS)
        }
    }
}
