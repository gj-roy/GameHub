package ca.on.hojat.gamenews.shared.data.auth.file

import androidx.datastore.core.DataStore
import ca.on.hojat.gamenews.shared.core.providers.TimestampProvider
import ca.on.hojat.gamenews.shared.data.DOMAIN_OAUTH_CREDENTIALS
import ca.on.hojat.gamenews.shared.data.auth.datastores.file.AuthExpiryTimeCalculator
import ca.on.hojat.gamenews.shared.data.auth.datastores.file.AuthFileDataStore
import ca.on.hojat.gamenews.shared.data.auth.datastores.file.ProtoAuthMapper
import ca.on.hojat.gamenews.shared.data.auth.datastores.file.ProtoOauthCredentials
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

private val PROTO_OAUTH_CREDENTIALS = ProtoOauthCredentials.newBuilder()
    .setAccessToken("access_token")
    .setTokenType("token_type")
    .setTokenTtl(5000L)
    .setExpirationTime(10_000L)
    .build()

internal class AuthFileDataStoreTest {

    @MockK
    private lateinit var protoDataStore: DataStore<ProtoOauthCredentials>
    @MockK
    private lateinit var timestampProvider: TimestampProvider

    private lateinit var sut: AuthFileDataStore

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        sut = AuthFileDataStore(
            protoDataStore = protoDataStore,
            protoAuthMapper = ProtoAuthMapper(AuthExpiryTimeCalculator(timestampProvider)),
        )
    }

    @Test
    fun `Saves credentials successfully`() {
        runTest {
            coEvery { protoDataStore.updateData(any()) } returns PROTO_OAUTH_CREDENTIALS

            sut.saveOauthCredentials(DOMAIN_OAUTH_CREDENTIALS)

            coVerify { protoDataStore.updateData(any()) }
        }
    }

    @Test
    fun `Retrieves credentials successfully`() {
        runTest {
            every { protoDataStore.data } returns flowOf(PROTO_OAUTH_CREDENTIALS)

            assertThat(sut.getOauthCredentials()).isEqualTo(DOMAIN_OAUTH_CREDENTIALS)
        }
    }

    @Test
    fun `Retrieves null credentials successfully`() {
        runTest {
            every { protoDataStore.data } returns flowOf()

            assertThat(sut.getOauthCredentials()).isNull()
        }
    }
}
