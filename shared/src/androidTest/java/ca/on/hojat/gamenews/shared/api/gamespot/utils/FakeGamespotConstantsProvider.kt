package ca.on.hojat.gamenews.shared.api.gamespot.utils

import ca.on.hojat.gamenews.shared.api.gamespot.common.GamespotConstantsProvider
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Inject

internal class FakeGamespotConstantsProvider @Inject constructor(
    mockWebServer: MockWebServer
) : GamespotConstantsProvider {

    override val apiKey: String = "api_key"
    override val apiBaseUrl: String = mockWebServer.url("/").toString()
}
