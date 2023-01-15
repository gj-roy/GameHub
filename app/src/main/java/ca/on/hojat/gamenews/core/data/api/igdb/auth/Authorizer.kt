package ca.on.hojat.gamenews.core.data.api.igdb.auth

import ca.on.hojat.gamenews.core.data.api.igdb.auth.entities.ApiAuthorizationType
import javax.inject.Inject

class Authorizer @Inject constructor() {

    fun buildAuthorizationHeader(type: ApiAuthorizationType, token: String): String {
        return buildString {
            append(type.rawType)
            append(" ")
            append(token)
        }
    }
}
