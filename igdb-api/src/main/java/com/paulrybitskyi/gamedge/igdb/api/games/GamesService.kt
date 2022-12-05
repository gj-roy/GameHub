package com.paulrybitskyi.gamedge.igdb.api.games

import com.paulrybitskyi.gamedge.igdb.api.games.entities.ApiGame
import com.paulrybitskyi.gamedge.igdb.common.ApiResult
import retrofit2.http.Body
import retrofit2.http.POST

internal interface GamesService {

    @POST("games")
    suspend fun getGames(@Body body: String): ApiResult<List<ApiGame>>
}
