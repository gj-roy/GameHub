package ca.on.hojat.gamenews.api.igdb.games

import ca.on.hojat.gamenews.api.common.ApiResult
import ca.on.hojat.gamenews.api.igdb.games.entities.ApiGame
import retrofit2.http.Body
import retrofit2.http.POST

internal interface GamesService {

    @POST("games")
    suspend fun getGames(@Body body: String): ApiResult<List<ApiGame>>
}
