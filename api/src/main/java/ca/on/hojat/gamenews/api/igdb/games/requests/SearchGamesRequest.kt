package ca.on.hojat.gamenews.api.igdb.games.requests

import ca.on.hojat.gamenews.api.common.ApiRequest

data class SearchGamesRequest(
    val searchQuery: String,
    override val offset: Int,
    override val limit: Int,
) : ApiRequest
