package ca.on.hojat.gamenews.shared.api.utils

import ca.on.hojat.gamenews.core.data.api.igdb.common.IgdbConstantsProvider
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Inject

internal class FakeIgdbConstantsProvider @Inject constructor(
    mockWebServer: MockWebServer
) : IgdbConstantsProvider {

    override val apiBaseUrl: String = mockWebServer.url("/").toString()
}
