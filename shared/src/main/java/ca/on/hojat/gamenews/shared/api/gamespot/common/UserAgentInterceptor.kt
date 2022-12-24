package ca.on.hojat.gamenews.shared.api.gamespot.common

import ca.on.hojat.gamenews.core.data.api.common.HttpHeaders
import ca.on.hojat.gamenews.core.data.api.common.UserAgentProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class UserAgentInterceptor @Inject constructor(
    private val userAgentProvider: UserAgentProvider
) : Interceptor {

    // Just a string containing app's name and version
    private val userAgent by lazy {
        userAgentProvider.getUserAgent()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val modifiedRequest = chain.request()
            .newBuilder()
            .header(HttpHeaders.USER_AGENT, userAgent)
            .build()

        return chain.proceed(modifiedRequest)
    }
}
