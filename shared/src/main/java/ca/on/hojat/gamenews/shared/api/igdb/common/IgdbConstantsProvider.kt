package ca.on.hojat.gamenews.shared.api.igdb.common

import ca.on.hojat.gamenews.core.data.api.igdb.common.Constants
import javax.inject.Inject

internal interface IgdbConstantsProvider {
    val apiBaseUrl: String
}

internal class ProdIgdbConstantsProvider @Inject constructor() : IgdbConstantsProvider {
    override val apiBaseUrl: String = Constants.IGDB_API_BASE_URL
}
