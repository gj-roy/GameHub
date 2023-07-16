package ca.on.hojat.gamenews.core.data.api.gamespot.common

import ca.on.hojat.gamenews.BuildConfig
import javax.inject.Inject

interface GamespotConstantsProvider {
    val apiKey: String
    val apiBaseUrl: String
}

class ProdGamespotConstantsProvider @Inject constructor() : GamespotConstantsProvider {
    override val apiKey: String = BuildConfig.GAMESPOT_API_KEY
    override val apiBaseUrl: String = Constants.GAMESPOT_API_BASE_URL
}
