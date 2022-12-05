package com.paulrybitskyi.gamedge.igdb.api.games.requests

import com.paulrybitskyi.gamedge.igdb.common.ApiRequest

data class SearchGamesRequest(
    val searchQuery: String,
    override val offset: Int,
    override val limit: Int,
) : ApiRequest
