package ca.on.hojat.gamenews.core.data.api.igdb.games

import ca.on.hojat.gamenews.core.data.api.common.ApiResult
import ca.on.hojat.gamenews.core.data.api.igdb.games.entities.ApiGame
import retrofit2.http.Body
import retrofit2.http.POST

interface GamesService {

    @POST("games")
    suspend fun getGames(@Body body: String): ApiResult<List<ApiGame>>
}
