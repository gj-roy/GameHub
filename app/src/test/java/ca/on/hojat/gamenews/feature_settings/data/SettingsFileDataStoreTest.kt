package ca.on.hojat.gamenews.feature_settings.data

import androidx.datastore.core.DataStore
import ca.on.hojat.gamenews.feature_settings.DOMAIN_SETTINGS
import com.google.common.truth.Truth.assertThat
import com.paulrybitskyi.gamedge.feature_settings.data.datastores.ProtoSettings
import com.paulrybitskyi.gamedge.feature_settings.data.datastores.ProtoSettingsMapper
import com.paulrybitskyi.gamedge.feature_settings.data.datastores.SettingsFileDataStore
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
    .build()

internal class SettingsFileDataStoreTest {

    @MockK
    private lateinit var protoDataStore: DataStore<ProtoSettings>

    private lateinit var SUT: SettingsFileDataStore

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        SUT = SettingsFileDataStore(
            protoDataStore = protoDataStore,
            protoMapper = ProtoSettingsMapper(),
        )
    }

    @Test
    fun `Saves settings successfully`() {
        runTest {
            coEvery { protoDataStore.updateData(any()) } returns PROTO_SETTINGS

            SUT.saveSettings(DOMAIN_SETTINGS)

            coVerify { protoDataStore.updateData(any()) }
        }
    }

    @Test
    fun `Observes settings successfully`() {
        runTest {
            coEvery { protoDataStore.data } returns flowOf(PROTO_SETTINGS)

            assertThat(SUT.observeSettings().first()).isEqualTo(DOMAIN_SETTINGS)
        }
    }
}
