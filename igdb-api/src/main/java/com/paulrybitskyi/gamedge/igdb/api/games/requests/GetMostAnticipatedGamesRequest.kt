package com.paulrybitskyi.gamedge.igdb.api.games.requests

import com.paulrybitskyi.gamedge.igdb.common.ApiRequest

data class GetMostAnticipatedGamesRequest(
    val minReleaseDateTimestamp: Long,
    override val offset: Int,
    override val limit: Int,
) : ApiRequest
