package ca.on.hojat.gamenews.shared.api.igdb.auth

import ca.on.hojat.gamenews.shared.api.igdb.auth.entities.ApiAuthorizationType
import javax.inject.Inject

internal class AuthHeaderParser @Inject constructor() {

    private companion object {
        const val SEPARATOR = ' '
    }

    fun parse(header: String): AuthHeaderParsingResult? {
        val stringParts = header.split(SEPARATOR)

        if (stringParts.size != 2) return null

        val rawType = stringParts[0]
        val token = stringParts[1]

        return AuthHeaderParsingResult(
            type = ApiAuthorizationType.forRawType(rawType),
            token = token,
        )
    }
}

internal data class AuthHeaderParsingResult(
    val type: ApiAuthorizationType,
    val token: String?,
)
