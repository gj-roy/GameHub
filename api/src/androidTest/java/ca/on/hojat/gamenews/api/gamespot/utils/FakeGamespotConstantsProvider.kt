package ca.on.hojat.gamenews.api.gamespot.utils

import com.paulrybitskyi.gamedge.igdb.gamespot.common.GamespotConstantsProvider
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Inject

internal class FakeGamespotConstantsProvider @Inject constructor(
    mockWebServer: MockWebServer
) : GamespotConstantsProvider {

    override val apiKey: String = "api_key"
    override val apiBaseUrl: String = mockWebServer.url("/").toString()
}
