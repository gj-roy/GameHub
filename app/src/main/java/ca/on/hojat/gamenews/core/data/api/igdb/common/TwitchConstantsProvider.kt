package ca.on.hojat.gamenews.core.data.api.igdb.common

import ca.on.hojat.gamenews.core.data.api.ApiKeys
import javax.inject.Inject

interface TwitchConstantsProvider {
    val clientId: String
    val clientSecret: String
    val apiBaseUrl: String
}

class ProdTwitchConstantsProvider @Inject constructor() : TwitchConstantsProvider {
    override val clientId: String = ApiKeys["TWITCH_APP_CLIENT_ID"]!!
    override val clientSecret: String = ApiKeys["TWITCH_APP_CLIENT_SECRET"]!!
    override val apiBaseUrl: String = Constants.TWITCH_API_BASE_URL
}
