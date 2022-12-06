package ca.on.hojat.gamenews.api.igdb.games.requests

import ca.on.hojat.gamenews.api.common.ApiRequest

data class GetGamesRequest(
    val gameIds: List<Int>,
    override val offset: Int,
    override val limit: Int,
) : ApiRequest
