package ca.on.hojat.gamehub.core.data.api.igdb.games.requests

import ca.on.hojat.gamehub.core.data.api.common.ApiRequest

data class GetGamesRequest(
    val gameIds: List<Int>,
    override val offset: Int,
    override val limit: Int,
) : ApiRequest
