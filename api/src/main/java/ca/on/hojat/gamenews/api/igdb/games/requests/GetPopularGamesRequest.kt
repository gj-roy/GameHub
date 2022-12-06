package ca.on.hojat.gamenews.api.igdb.games.requests

import ca.on.hojat.gamenews.api.common.ApiRequest

data class GetPopularGamesRequest(
    val minReleaseDateTimestamp: Long,
    override val offset: Int,
    override val limit: Int,
) : ApiRequest
